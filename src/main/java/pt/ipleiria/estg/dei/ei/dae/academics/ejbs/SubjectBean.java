package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.Hibernate;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;

import java.util.List;

@Stateless
public class SubjectBean {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private CourseBean courseBean;

    public boolean exists(long code) {
        Query query = entityManager.createQuery(
                "SELECT COUNT(s.code) FROM Subject s WHERE s.code = :code",
                Long.class
        );
        query.setParameter("code", code);
        return (Long) query.getSingleResult() > 0L;
    }

    public void create(long code, String name, String schoolYear, int courseYear, long courseCode) throws MyEntityNotFoundException, MyEntityExistsException {
        if (exists(code)) {
            throw new MyEntityExistsException("Subject with code '" + code + "' already exists");
        }

        var course = courseBean.find(courseCode);

        var subject = new Subject(code, name, schoolYear, courseYear, course);
        entityManager.persist(subject);
    }

    public List<Subject> findAll() {
        return entityManager.createNamedQuery("getAllSubjects", Subject.class).getResultList();
    }

    public Subject find(long code) throws MyEntityNotFoundException {
        var subject = entityManager.find(Subject.class, code);
        if (subject == null) {
            throw new MyEntityNotFoundException("Subject with code '" + code + "' not found");
        }
        return subject;
    }

    public Subject findWithStudents(long code) throws MyEntityNotFoundException {
        var subject = this.find(code);
        Hibernate.initialize(subject.getStudents());
        return subject;
    }

    public Subject findWithTeachers(long code) throws MyEntityNotFoundException {
        var subject = this.find(code);
        Hibernate.initialize(subject.getTeachers());
        return subject;
    }

    public Subject remove(long code) throws MyEntityNotFoundException {
        var subject = this.find(code);
        entityManager.remove(subject);
        if (entityManager.find(Subject.class, code) != null) {
            throw new MyEntityNotFoundException("Subject with code '" + code + "' not removed");
        }
        return subject;
    }
}
