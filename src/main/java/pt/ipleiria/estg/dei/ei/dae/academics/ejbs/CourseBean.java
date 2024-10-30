package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;

import java.util.List;

@Stateless
public class CourseBean {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean exists(long code) {
        Query query = entityManager.createQuery(
                "SELECT COUNT(c.code) FROM Course c WHERE c.code = :code",
                Long.class
        );
        query.setParameter("code", code);
        return (Long) query.getSingleResult() > 0L;
    }

    public void create(long code, String name) throws MyEntityExistsException {
        if (exists(code)) {
            throw new MyEntityExistsException("Course with code '" + code + "' already exists");
        }

        var course = new Course(code, name);
        entityManager.persist(course);
    }

    public List<Course> findAll() {
        return entityManager.createNamedQuery("getAllCourses", Course.class).getResultList();
    }

    public Course find(long code) throws MyEntityNotFoundException {
        var course = entityManager.find(Course.class, code);
        if (course == null) {
            throw new MyEntityNotFoundException("Course with code '" + code + "' not found");
        }
        return course;
    }

    public void update(long code, String name) throws MyEntityNotFoundException {
        Course course = this.find(code);
        course.setName(name);
    }

    public Course remove(long code) throws MyEntityNotFoundException {
        var course = this.find(code);
        entityManager.remove(course);
        if (entityManager.find(Course.class, code) != null) {
            throw new MyEntityNotFoundException("Course with code '" + code + "' not removed");
        }
        return course;
    }
}
