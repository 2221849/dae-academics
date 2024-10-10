package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import java.util.List;

@Stateless
public class SubjectBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(long code, String name, String studentYear, int courseYear, Course course) {
        var subject = new Subject(code, name, studentYear, courseYear, course);
        entityManager.persist(subject);
    }

    public List<Subject> findAll() {
        return entityManager.createNamedQuery("getAllSubjects", Subject.class).getResultList();
    }

    public Subject find(long code) {
        var subject = entityManager.find(Subject.class, code);
        if (subject == null) {
            throw new RuntimeException("subject with code " + code + " not found");
        }
        return subject;
    }
}
