package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

import java.util.logging.Logger;

@Startup
@Singleton
public class ConfigBean {

    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");
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

        try {
            courseBean.create(9119, "Licenciatura em Engenharia Informática (D)");
            courseBean.create(9885, "Licenciatura em Engenharia Informática (PL)");
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }

        // </editor-fold>

        // <editor-fold desc="Students">

        try {
            studentBean.create("2221868", "123456789", "Afonso Ramos", "2221868@ipleiria.pt", 9119);
            studentBean.create("2221842", "123456789", "Francisco Francisco", "2221842@ipleiria.pt", 9119);
            studentBean.create("2221849", "123456789", "Marcelo Santos", "2221849@ipleiria.pt", 9119);
            studentBean.create("2221864", "123456789", "Tiago Gaspar", "2221864@ipleiria.pt", 9119);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }

        // </editor-fold>

        // <editor-fold desc="Subjects">

        try {
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
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }

        // </editor-fold>

        // <editor-fold desc="Student Enrollment">

        try {
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
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }

        // </editor-fold>

        // <editor-fold desc="Administrators and Teachers">

        administratorBean.create("admin", "123456789", "Administrator", "admin@my.ipleiria.pt");

        try {
            teacherBean.create("carlos.j.ferreira", "123456789", "Carlos Ferreira", "carlos.j.ferreira@my.ipleiria.pt", "????");
            teacherBean.associateTeacherToSubject("carlos.j.ferreira", 9119218);
            teacherBean.associateTeacherToSubject("carlos.j.ferreira", 37);
            teacherBean.dissociateTeacherFromSubject("carlos.j.ferreira", 9119218);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
        // </editor-fold>

    }
}
