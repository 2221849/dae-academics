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

    @PostConstruct
    public void populateDB() {
        studentBean.create(2221868L, "123456789", "Afonso Ramos", "2221868@ipleiria.pt");
        studentBean.create(2221842L, "123456789", "Francisco Francisco", "2221842@ipleiria.pt");
        studentBean.create(2221849L, "123456789", "Marcelo Santos", "2221849@ipleiria.pt");
        studentBean.create(2221864L, "123456789", "Tiago Gaspar", "2221864@ipleiria.pt");
    }
}
