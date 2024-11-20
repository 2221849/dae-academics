package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.academics.security.Hasher;

import java.util.List;

@Stateless
public class AdministratorBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Hasher hasher;

    public boolean exists(String username) {
        Query query = entityManager.createQuery(
                "SELECT COUNT(a.username) FROM Administrator a WHERE a.username = :username",
                Long.class
        );
        query.setParameter("username", username);
        return (Long)query.getSingleResult() > 0L;
    }

    public Administrator create(String username, String password, String name, String email) throws MyEntityExistsException {
        if (exists(username)) {
            throw new MyEntityExistsException("Administrator with username '" + username + "' already exists");
        }

        var administrator = new Administrator(username, hasher.hash(password), name, email);
        entityManager.persist(administrator);

        return administrator;
    }

    public Administrator find(String username) throws MyEntityNotFoundException {
        var administrator = entityManager.find(Administrator.class, username);
        if (administrator == null) {
            throw new MyEntityNotFoundException("Administrator with username '" + username + "' not found");
        }
        return administrator;
    }

    public List<Administrator> findAll() {
        return entityManager.createNamedQuery("getAllAdministrators", Administrator.class).getResultList();
    }

    public void update(String username, String password, String name, String email) throws MyEntityNotFoundException {
        var administrator = this.find(username);

        administrator.setUsername(name);
        administrator.setPassword(hasher.hash(password));
        administrator.setEmail(email);
    }

    public Administrator remove(String username) throws MyEntityNotFoundException {
        var administrator = this.find(username);
        entityManager.remove(administrator);
        if (entityManager.find(Administrator.class, username) != null) {
            throw new MyEntityNotFoundException("Administrator with username '" + username + "' not removed");
        }
        return administrator;
    }
}
