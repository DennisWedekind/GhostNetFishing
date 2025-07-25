package controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.Geisternetz;
import model.Person;
import service.GeisternetzService;

@Named
@RequestScoped
public class GeisternetzMeldenController {

    // Injected Service zur Verarbeitung der Geisternetzmeldung
    @Inject
    private GeisternetzService service;

    // Objekte für Formularbindung
    private Person person = new Person();
    private Geisternetz geisternetz = new Geisternetz();

    // Methode zur Auslösung der Service-Logik bei Formular-Absenden
    public String geisternetzMelden() {
        service.meldenGeisternetz(geisternetz, person);
        return "startseite.xhtml";
    }

    // Getter und Setter für Formularbindung
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Geisternetz getGeisternetz() {
        return geisternetz;
    }

    public void setGeisternetz(Geisternetz geisternetz) {
        this.geisternetz = geisternetz;
    }

}
