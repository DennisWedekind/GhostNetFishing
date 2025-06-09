package DAO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import model.Person;

@ApplicationScoped

public class PersonDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostNetPU");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Person findePerson(String vorname, String nachname, String telefonnummer) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                            "SELECT p FROM Person p WHERE p.vorname = :vorname AND p.nachname = :nachname AND p.telefonnummer = :telefonnummer",
                            Person.class)
                    .setParameter("vorname", vorname)
                    .setParameter("nachname", nachname)
                    .setParameter("telefonnummer", telefonnummer)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public void speicherePerson(Person person) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        em.close();
    }

}

