package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.util.List;

@Stateless
public class StudentBean {
    @PersistenceContext
    private EntityManager entityManager;

    @EJB
    private CourseBean courseBean;  // Inject CourseBean here

    public void create(String username, String password, String name, String email, long code) {
        Course course = courseBean.find(code);
        var student = new Student(username, password, name, email, course);
        course.addStudent(student);
        entityManager.persist(student);
    }

    public List<Student> findAll() {
        // remember, maps to: “SELECT s FROM Student s ORDER BY s.name”
        return entityManager.createNamedQuery("getAllStudents", Student.class).getResultList();
    }

    public Student find(String username) {
        var student = entityManager.find(Student.class, username);
        if (student == null){
            throw new RuntimeException("student " + student + " not found");
        }
        return student;
    }
}
