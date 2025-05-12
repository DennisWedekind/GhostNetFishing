package controller;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import model.*;

import java.util.List;

@Named
@RequestScoped
public class GeborgenController {

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

    // Liefert alle Geisternetze mit Status "BERGUNG_BEVORSTEHEND"
    public List<Geisternetz> getZurBergungAngemeldeteGeisternetze() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostNetPU");
        EntityManager em = emf.createEntityManager();

        List<Geisternetz> result = em.createQuery(
                        "SELECT g FROM Geisternetz g WHERE g.status = :status", Geisternetz.class)
                .setParameter("status", Status.BERGUNG_BEVORSTEHEND)
                .getResultList();

        em.close();
        emf.close();

        return result;
    }

    // Aktion zum Melden als "geborgen"
    public String alsGeborgenMelden() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostNetPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // Geisternetz laden
        Geisternetz geisternetz = em.find(Geisternetz.class, geisternetzId);

        // Neue Person speichern
        em.persist(person);

        // Geisternetz aktualisieren
        geisternetz.setStatus(Status.GEBORGEN);
        geisternetz.setBergendePerson(person); // ersetzt ggf. bestehende Zuordnung

        em.merge(geisternetz);

        em.getTransaction().commit();
        em.close();
        emf.close();

        return "startseite.xhtml"; // Nachdem Speichern zur Startseite umleiten
    }
}
