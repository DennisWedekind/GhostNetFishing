package controller;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.*;
import model.*;
import java.util.List;

@Named
@RequestScoped
public class BergungBevorstehendController {

    // ID des ausgewählten Geisternetzes aus dem Dropdown
    private Long geisternetzId;

    // Neue bergende Person
    private Person person = new Person();

    // Getter/Setter für das ausgewählte Geisternetz (über ID)
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

    // Liefert alle Geisternetze mit Status "GEMELDET"
    public List<Geisternetz> getOffeneGeisternetze() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostNetPU");
        EntityManager em = emf.createEntityManager();

        List<Geisternetz> result = em.createQuery(
                        "SELECT g FROM Geisternetz g WHERE g.status = :status", Geisternetz.class)
                .setParameter("status", Status.GEMELDET)
                .getResultList();

        em.close();
        emf.close();

        return result;
    }

    // Aktion zum Anmelden der Bergung
    public String bergungAnmelden() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostNetPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // ausgewähltes Geisternetz laden
        Geisternetz geisternetz = em.find(Geisternetz.class, geisternetzId);

        // neue Person speichern
        em.persist(person);

        // Geisternetz mit bergender Person verknüpfen und Status ändern
        geisternetz.setBergendePerson(person);
        geisternetz.setStatus(Status.BERGUNG_BEVORSTEHEND);

        em.merge(geisternetz); // Änderungen speichern

        em.getTransaction().commit();

        em.close();
        emf.close();

        return "startseite.xhtml"; // Nachdem Speichern zur Startseite umleiten
    }
}
