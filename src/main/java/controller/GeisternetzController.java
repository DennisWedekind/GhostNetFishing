package controller;
import java.util.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import model.*;

// CDI-Bean für die Verarbeitung von Geisternetzen
@Named
@RequestScoped
public class GeisternetzController {

    //Modellobjekt für das zu meldende Geisternetz und die zugehörige Person
    private Geisternetz geisternetz = new Geisternetz();
    private Person person = new Person();

    // Getter für das verwendete Geisternetz
    public Geisternetz getGeisternetz() {
        return geisternetz;
    }

    // Getter für die verwendete Person
    public Person getPerson() {
        return person;
    }

    // Gibt alle möglichen Statuswerte zurück
    public List<Status> getStatusList() {
        return Arrays.asList(Status.values());
    }

    // Methode zur Speicherung des Geisternetz-Objektes in der Datenbank
    public String speichern() {
        // EntityManagerFactory und EntityManager initialisieren
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostNetPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin(); // Transaktion beginnen

        em.persist(person); // meldende Person speichern
        geisternetz.setMeldendePerson(person); // Geisternetz mit Person verknüpfen
        em.persist(geisternetz); // Geisternetz speichern

        em.getTransaction().commit();  // Transaktion abschließen
        emf.close();  //  Factory schließen
        em.close(); // EntityManager schließen

        return "startseite.xhtml"; // Nachdem Speichern zur melden.xhtml umleiten
    }
}
