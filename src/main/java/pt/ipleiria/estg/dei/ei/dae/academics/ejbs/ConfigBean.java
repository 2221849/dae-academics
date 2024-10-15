package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Startup
@Singleton
public class ConfigBean {

    @EJB
    private StudentBean studentBean;

    @EJB
    private CourseBean courseBean;

    @EJB
    private SubjectBean subjectBean;

    @EJB
    private AdministratorBean administratorBean;

    @EJB
    private TeacherBean teacherBean;

    @PostConstruct
    public void populateDB() {

        // <editor-fold desc="Courses">

        courseBean.create(2, "UCs comuns a vários cursos (ESTG)");
        courseBean.create(1000, "Ensino Generalizado do Inglês");
        courseBean.create(2100, "Matemáticas Gerais");
        courseBean.create(9119, "Licenciatura em Engenharia Informática (D)");
        courseBean.create(9885, "Licenciatura em Engenharia Informática (PL)");

        // </editor-fold>

        // <editor-fold desc="Students">

        studentBean.create("2221868", "123456789", "Afonso Ramos", "2221868@ipleiria.pt", 9119);
        studentBean.create("2221842", "123456789", "Francisco Francisco", "2221842@ipleiria.pt", 9119);
        studentBean.create("2221849", "123456789", "Marcelo Santos", "2221849@ipleiria.pt", 9119);
        studentBean.create("2221864", "123456789", "Tiago Gaspar", "2221864@ipleiria.pt", 9119);

        // </editor-fold>

        // <editor-fold desc="Subjects">

        subjectBean.create(9885202, "Álgebra Linear", "2022-23", 1, 9885);
        subjectBean.create(9885206, "Matemática Discreta", "2022-23", 1, 9885);

        subjectBean.create(9119218, "Engenharia de Software", "2023-24", 2, 9119);
        subjectBean.create(9119225, "Sistemas de Bases de Dados", "2023-24", 2, 9119);
        subjectBean.create(178, "Aplicações para a Internet", "2023-24", 2, 9119);
        subjectBean.create(191, "Segurança da Informação", "2023-24", 2, 9119);
        subjectBean.create(24, "Inteligência Artificial", "2023-24", 2, 9119);

        subjectBean.create(37, "Desenvolvimento de Aplicações Empresariais", "2024-25", 3, 9119);
        subjectBean.create(51, "Integração de Sistemas", "2024-25", 3, 9119);
        subjectBean.create(54, "Desenvolvimento de Aplicações Distribuídas", "2024-25", 3, 9119);
        subjectBean.create(622, "Tópicos Avançados de Engenharia de Software", "2024-25", 3, 9119);
        subjectBean.create(72, "Sistemas de Apoio à Decisão", "2024-25", 3, 9119);

        // <editor-fold desc="Student Enrollment">

        studentBean.enrollStudentInSubject("2221849", 9119218);
        studentBean.enrollStudentInSubject("2221849", 9119225);
        studentBean.enrollStudentInSubject("2221849", 178);
        studentBean.enrollStudentInSubject("2221849", 191);
        studentBean.enrollStudentInSubject("2221849", 24);

        studentBean.unrollStudentFromSubject("2221849", 9119218);
        studentBean.unrollStudentFromSubject("2221849", 9119225);
        studentBean.unrollStudentFromSubject("2221849", 178);
        studentBean.unrollStudentFromSubject("2221849", 191);
        studentBean.unrollStudentFromSubject("2221849", 24);

        studentBean.enrollStudentInSubject("2221849", 37);
        studentBean.enrollStudentInSubject("2221849", 51);
        studentBean.enrollStudentInSubject("2221849", 54);
        studentBean.enrollStudentInSubject("2221849", 622);
        studentBean.enrollStudentInSubject("2221849", 72);

        // </editor-fold>

        // <editor-fold desc="Administrators and Teachers">

        administratorBean.create("admin", "123456789", "Administrator", "admin@my.ipleiria.pt");

        teacherBean.create("carlos.j.ferreira", "123456789", "Carlos Ferreira", "carlos.j.ferreira@my.ipleiria.pt", "????");

        teacherBean.associateTeacherToSubject("carlos.j.ferreira",9119218);

        teacherBean.associateTeacherToSubject("carlos.j.ferreira",37);

        teacherBean.dissociateTeacherFromSubject("carlos.j.ferreira",9119218);

        // </editor-fold>

    }
}
