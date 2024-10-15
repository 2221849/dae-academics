package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.User;

@Stateless
public class UserBean {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(String username, String password, String name, String email) {
        var user = new User(username, password, name, email);
        entityManager.persist(user);
    }
}
