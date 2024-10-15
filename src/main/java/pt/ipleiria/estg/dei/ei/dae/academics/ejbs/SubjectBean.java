package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import java.util.List;

@Stateless
public class SubjectBean {

    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private CourseBean courseBean;

    public void create(long code, String name, String schoolYear, int courseYear, long courseCode) {
        Course course = courseBean.find(courseCode);
        var subject = new Subject(code, name, schoolYear, courseYear, course);
        entityManager.persist(subject);
    }

    public Subject find(long code) {
        var subject = entityManager.find(Subject.class, code);
        if (subject == null) {
            throw new RuntimeException("subject with code " + code + " not found");
        }
        return subject;
    }

    public List<Subject> findAll() {
        return entityManager.createNamedQuery("getAllSubjects", Subject.class).getResultList();
    }

    public Subject findWithStudents(long code) {
        var subject = this.find(code);
        Hibernate.initialize(subject.getStudents());
        return subject;
    }

    public Subject findWithTeachers(long code) {
        var subject = this.find(code);
        Hibernate.initialize(subject.getTeachers());
        return subject;
    }

    public Subject remove(long code) {
        var subject = entityManager.find(Subject.class, code);
        entityManager.remove(subject);
        return subject;
    }
}
