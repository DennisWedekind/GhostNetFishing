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

    @Inject
    private GeisternetzService service;

    private Person person = new Person();
    private Geisternetz geisternetz = new Geisternetz();

    // Getter und Setter
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

    // Business-Methode: Geisternetz melden
    public String geisternetzMelden() {
        service.meldenGeisternetz(geisternetz, person);
        return "startseite.xhtml";
    }
}
