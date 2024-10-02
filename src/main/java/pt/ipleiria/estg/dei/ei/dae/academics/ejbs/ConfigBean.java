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

    @PostConstruct
    public void populateDB() {
        courseBean.create(9119, "Licenciatura em Engenharia Informática (D)");
        courseBean.create(9885, "Licenciatura em Engenharia Informática (PL)");
        studentBean.create("2221868", "123456789", "Afonso Ramos", "2221868@ipleiria.pt", 9119);
        studentBean.create("2221842", "123456789", "Francisco Francisco", "2221842@ipleiria.pt", 9119);
        studentBean.create("2221849", "123456789", "Marcelo Santos", "2221849@ipleiria.pt", 9119);
        studentBean.create("2221864", "123456789", "Tiago Gaspar", "2221864@ipleiria.pt", 9119);
    }
}
