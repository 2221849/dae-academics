package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;

import java.util.List;

@Stateless
public class CourseBean {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(long code, String name) {
        var course = new Course(code, name);
        entityManager.persist(course);
    }

    public List<Course> findAll() {
        return entityManager.createNamedQuery("getAllCourses", Course.class).getResultList();
    }

    public Course find(long code) {
        var course = entityManager.find(Course.class, code);
        if (course == null){
            throw new RuntimeException("course " + course + " not found");
        }
        return course;
    }
}
