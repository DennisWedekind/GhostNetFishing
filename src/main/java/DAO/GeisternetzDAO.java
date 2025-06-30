package DAO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import model.Geisternetz;
import model.Status;

import java.util.List;

@ApplicationScoped
public class GeisternetzDAO {private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostNetPU");

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Sucht ein Geisternetz anhand von Breitengrad, Längengrad und geschätzter Größe.
    // Gibt das gefundene Objekt zurück oder null, falls kein Ergebnis existiert.
    public Geisternetz findeGeisternetz(Double breitengrad, Double laengengrad, Double geschaetzteGroesse) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                            "SELECT g FROM Geisternetz g WHERE g.breitengrad = :breitengrad " +
                                    "AND g.laengengrad = :laengengrad AND g.geschaetzteGroesse = :groesse",
                            Geisternetz.class)
                    .setParameter("breitengrad", breitengrad)
                    .setParameter("laengengrad", laengengrad)
                    .setParameter("groesse", geschaetzteGroesse)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Kein Ergebnis gefunden
        } finally {
            em.close();
        }
    }

    // Speichert ein neues Geisternetz in der Datenbank.
    // Beginnt eine Transaktion, führt persist() aus und schließt die Transaktion ab
    public void speichereGeisternetz(Geisternetz geisternetz) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin(); // Transaktion starten
        em.persist(geisternetz); // Entität speichern
        em.getTransaction().commit();// Transaktion abschließen
        em.close(); // EntityManager schließen
    }

    public Geisternetz findeGeisternetzNachId(Long id) {
        EntityManager em = getEntityManager();
        Geisternetz geisternetz = em.find(Geisternetz.class, id);
        em.close();
        return geisternetz;
    }

    public List<Geisternetz> findeGeisternetzNachStatus(Status status) {
        EntityManager em = getEntityManager();
        List<Geisternetz> result = em.createQuery(
                        "SELECT g FROM Geisternetz g WHERE g.status = :status",
                        Geisternetz.class)
                .setParameter("status", status)
                .getResultList();
        em.close();
        return result;
    }

    public void aktualisiereGeisternetz(Geisternetz geisternetz) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(geisternetz);
        em.getTransaction().commit();
        em.close();
    }
}