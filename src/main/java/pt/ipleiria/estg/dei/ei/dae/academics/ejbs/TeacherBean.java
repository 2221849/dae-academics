package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Teacher;

import java.util.List;

@Stateless
public class TeacherBean {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(String username, String password, String name, String email, String office) {
        var teacher = new Teacher(username, password, name, email, office);
        entityManager.persist(teacher);
    }

    public Teacher find(String username) {
        var teacher = entityManager.find(Teacher.class, username);
        if (teacher == null) {
            throw new RuntimeException("teacher " + username + " not found");
        }
        return teacher;
    }

    public List<Teacher> findAll() {
        return entityManager.createNamedQuery("getAllTeachers", Teacher.class).getResultList();
    }

    public Teacher findWithSubjects(String username) {
        var teacher = this.find(username);
        Hibernate.initialize(teacher.getSubjects());
        return teacher;
    }

    public Teacher remove(String username) {
        var teacher = entityManager.find(Teacher.class, username);
        entityManager.remove(teacher);
        return teacher;
    }

    public void associateTeacherToSubject(String username, long code) {
        var teacher = entityManager.find(Teacher.class, username);
        var subject = entityManager.find(Subject.class, code);

        teacher.addSubject(subject);
        subject.addTeacher(teacher);
    }

    public void dissociateTeacherFromSubject(String username, long code) {
        var teacher = entityManager.find(Teacher.class, username);
        var subject = entityManager.find(Subject.class, code);

        teacher.removeSubject(subject);
        subject.removeTeacher(teacher);
    }
}
