package controller;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import model.*;

import java.util.List;

@Named
@RequestScoped
public class VerschollenController {

    private Long geisternetzId;
    private Person person = new Person();

    // Getter/Setter für Geisternetz-ID
    public Long getGeisternetzId() {
        return geisternetzId;
    }

    public void setGeisternetzId(Long geisternetzId) {
        this.geisternetzId = geisternetzId;
    }

    // Getter für die eingegebene Person
    public Person getPerson() {
        return person;
    }

    // Liefert alle Geisternetze mit Status MELDUNG_ERFASST oder BERGUNG_BEVORSTEHEND
    public List<Geisternetz> getOffeneGeisternetze() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostNetPU");
        EntityManager em = emf.createEntityManager();

        List<Geisternetz> result = em.createQuery(
                        "SELECT g FROM Geisternetz g WHERE g.status = :status1 OR g.status = :status2", Geisternetz.class)
                .setParameter("status1", Status.GEMELDET)
                .setParameter("status2", Status.BERGUNG_BEVORSTEHEND)
                .getResultList();

        em.close();
        emf.close();

        return result;
    }

    // Aktion zum Melden als "verschollen" – Status wird auf VERSCHOLLEN gesetzt
    public String alsVerschollenMelden() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostNetPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Geisternetz laden
        Geisternetz geisternetz = em.find(Geisternetz.class, geisternetzId);

        // Neue Person speichern
        em.persist(person);

        // Status auf VERSCHOLLEN setzen und Person zuordnen
        geisternetz.setStatus(Status.VERSCHOLLEN);
        geisternetz.setVerschollenMeldendePerson(person);

        em.merge(geisternetz);

        em.getTransaction().commit();
        em.close();
        emf.close();

        return "startseite.xhtml"; // Nachdem Speichern zur Startseite umleiten
    }
}
