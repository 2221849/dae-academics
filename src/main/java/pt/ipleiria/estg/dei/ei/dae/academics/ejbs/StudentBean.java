package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.Hibernate;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.academics.security.Hasher;

import java.util.List;

@Stateless
public class StudentBean {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private CourseBean courseBean;

    @EJB
    private SubjectBean subjectBean;

    @Inject
    private Hasher hasher;

    public boolean exists(String username) {
        Query query = entityManager.createQuery(
                "SELECT COUNT(s.username) FROM Student s WHERE s.username = :username",
                Long.class
        );
        query.setParameter("username", username);
        return (Long) query.getSingleResult() > 0L;
    }

    public Student create(String username, String password, String name, String email, long code) throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        if (exists(username)) {
            throw new MyEntityExistsException("Student with username '" + username + "' already exists");
        }

        var course = courseBean.find(code);
        Student student = null;

        try {
            student = new Student(username, hasher.hash(password), name, email, course);
            entityManager.persist(student);
            entityManager.flush();
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }

        course.addStudent(student);

        return student;
    }

    public List<Student> findAll() {
        return entityManager.createNamedQuery("getAllStudents", Student.class).getResultList();
    }

    public Student find(String username) throws MyEntityNotFoundException {
        var student = entityManager.find(Student.class, username);
        if (student == null) {
            throw new MyEntityNotFoundException("Student with username '" + username + "' not found");
        }
        return student;
    }

    public Student findWithSubjects(String username) throws MyEntityNotFoundException {
        var student = this.find(username);
        Hibernate.initialize(student.getSubjects());
        return student;
    }

    public void update(String username, String password, String name, String email, long courseCode) throws MyEntityNotFoundException {
        var student = this.find(username);

        entityManager.lock(student, LockModeType.OPTIMISTIC);

        student.setPassword(hasher.hash(password));
        student.setName(name);
        student.setEmail(email);

        if (student.getCourse().getCode() != courseCode) {
            var course = courseBean.find(courseCode);
            student.setCourse(course);
        }
    }

    public Student remove(String username) throws MyEntityNotFoundException {
        var student = this.find(username);
        entityManager.remove(student);
        if (entityManager.find(Student.class, username) != null) {
            throw new MyEntityNotFoundException("Student with username '" + username + "' not removed");
        }
        return student;
    }

    public void enrollStudentInSubject(String username, long code) throws MyEntityNotFoundException {
        var student = this.find(username);
        var subject = subjectBean.find(code);

        if (student.getCourse().equals(subject.getCourse())) {
            subject.addStudent(student);
            student.addSubject(subject);
        }
    }

    public void unrollStudentFromSubject(String username, long code) throws MyEntityNotFoundException {
        var student = this.find(username);
        var subject = subjectBean.find(code);

        student.removeSubject(subject);
        subject.removeStudent(student);
    }
}
