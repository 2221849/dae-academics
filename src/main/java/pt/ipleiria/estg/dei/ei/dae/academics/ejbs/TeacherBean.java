package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.Hibernate;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Teacher;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.academics.security.Hasher;

import java.util.List;

@Stateless
public class TeacherBean {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private SubjectBean subjectBean;

    @Inject
    private Hasher hasher;

    public boolean exists(String username) {
        Query query = entityManager.createQuery(
                "SELECT COUNT(t.username) FROM Teacher t WHERE t.username = :username",
                Long.class
        );
        query.setParameter("username", username);
        return (Long)query.getSingleResult() > 0L;
    }

    public Teacher create(String username, String password, String name, String email, String office) throws MyEntityExistsException {
        if (exists(username)) {
            throw new MyEntityExistsException("Teacher with username '" + username + "' already exists");
        }

        var teacher = new Teacher(username, hasher.hash(password), name, email, office);
        entityManager.persist(teacher);

        return teacher;
    }

    public Teacher find(String username) throws MyEntityNotFoundException {
        var teacher = entityManager.find(Teacher.class, username);
        if (teacher == null) {
            throw new MyEntityNotFoundException("Teacher with username '" + username + "' not found");
        }
        return teacher;
    }

    public List<Teacher> findAll() {
        return entityManager.createNamedQuery("getAllTeachers", Teacher.class).getResultList();
    }

    public Teacher findWithSubjects(String username) throws MyEntityNotFoundException {
        var teacher = this.find(username);
        Hibernate.initialize(teacher.getSubjects());
        return teacher;
    }

    public void update(String username, String password, String name, String email, String office) throws MyEntityNotFoundException {
        var teacher = this.find(username);

        teacher.setPassword(hasher.hash(password));
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setOffice(office);
    }

    public Teacher remove(String username) throws MyEntityNotFoundException {
        var teacher = this.find(username);
        entityManager.remove(teacher);
        if (entityManager.find(Teacher.class, username) != null) {
            throw new MyEntityNotFoundException("Teacher with username '" + username + "' not removed");
        }
        return teacher;
    }

    public void associateTeacherToSubject(String username, long code) throws MyEntityNotFoundException {
        var teacher = this.find(username);
        var subject = subjectBean.find(code);

        teacher.addSubject(subject);
        subject.addTeacher(teacher);
    }

    public void dissociateTeacherFromSubject(String username, long code) throws MyEntityNotFoundException {
        var teacher = this.find(username);
        var subject = subjectBean.find(code);

        teacher.removeSubject(subject);
        subject.removeTeacher(teacher);
    }
}
