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

    @PostConstruct
    public void populateDB() {
        courseBean.create(2, "UCs comuns a vários cursos (ESTG)");
        courseBean.create(1000, "Ensino Generalizado do Inglês");
        courseBean.create(2100, "Matemáticas Gerais");
        courseBean.create(9119, "Licenciatura em Engenharia Informática (D)");
        courseBean.create(9885, "Licenciatura em Engenharia Informática (PL)");

        studentBean.create("2221868", "123456789", "Afonso Ramos", "2221868@ipleiria.pt", 9119);
        studentBean.create("2221842", "123456789", "Francisco Francisco", "2221842@ipleiria.pt", 9119);
        studentBean.create("2221849", "123456789", "Marcelo Santos", "2221849@ipleiria.pt", 9119);
        studentBean.create("2221864", "123456789", "Tiago Gaspar", "2221864@ipleiria.pt", 9119);

        subjectBean.create(9885202, "Álgebra Linear", "2022-23", 1, courseBean.find(9885));
        subjectBean.create(9, "Análise Matemática EI (D+PL)", "2022-23", 1, courseBean.find(2));
        subjectBean.create(9885207, "Estatística", "2022-23", 1, courseBean.find(1000));
        subjectBean.create(55, "Física Aplicada . EI(D+PL)", "2022-23", 1, courseBean.find(9885));
        subjectBean.create(210001, "Matemática Geral A", "2022-23", 1, courseBean.find(2100));
        subjectBean.create(210002, "Matemática Gerais", "2022-23", 1, courseBean.find(2100));
        subjectBean.create(62, "Programação I . EI(D+PL)", "2022-23", 1, courseBean.find(2));
        subjectBean.create(16, "Sistemas Computacionais EI(D+PL)", "2022-23", 1, courseBean.find(2));

        subjectBean.create(9885202, "Análise Matemática EC+EGI+EEC(D+PL)+EI(D+PL)+EM(D+PL)+EENA+EA", "2022-23", 1, courseBean.find(2));
        subjectBean.create(9, "C2 English", "2022-23", 1, courseBean.find(1000));
        subjectBean.create(9885206, "Matemática Discreta", "2022-23", 1, courseBean.find(9885));
        subjectBean.create(174, "Programação II . EI(D+PL)", "2022-23", 1, courseBean.find(2));
        subjectBean.create(240, "Sistemas Operativos EI(D+PL)", "2022-23", 1, courseBean.find(2));
        subjectBean.create(221, "Tecnologias de Internet EI(D+PL)", "2022-23", 1, courseBean.find(2));

        subjectBean.create(9119218, "Engenharia de Software", "2023-24", 2, courseBean.find(9119));
        subjectBean.create(9119225, "Sistemas de Bases de Dados", "2023-24", 2, courseBean.find(9119));
        subjectBean.create(178, "Aplicações para a Internet", "2023-24", 2, courseBean.find(9119));
        subjectBean.create(191, "Segurança da Informação", "2023-24", 2, courseBean.find(9119));
        subjectBean.create(24, "Inteligência Artificial", "2023-24", 2, courseBean.find(9119));

        studentBean.enrollStudentInSubject("2221849", 9119218);
        studentBean.enrollStudentInSubject("2221849", 9119225);
        studentBean.enrollStudentInSubject("2221849", 178);
        studentBean.enrollStudentInSubject("2221849", 191);
        studentBean.enrollStudentInSubject("2221849", 24);
    }
}
