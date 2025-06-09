package controller;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.Geisternetz;
import model.Person;
import model.Status;
import service.GeisternetzService;

@Named
@RequestScoped
public class GeisternetzVerschollenController {

    private Long geisternetzId;
    private Person person = new Person();

    @Inject
    private GeisternetzService service;

    // Hier z.B. Geisternetze, die noch gemeldet sind und evtl. verschollen gemeldet werden können
    public List<Geisternetz> getGemeldeteGeisternetze() {
        return service.findeGeisternetzeMitStatus(Status.Gemeldet);
    }

    public String geisternetzVerschollenMelden() {
        service.alsVerschollenMelden(geisternetzId, person);
        return "startseite.xhtml";
    }

    // Getter und Setter
    public Long getGeisternetzId() {
        return geisternetzId;
    }

    public void setGeisternetzId(Long geisternetzId) {
        this.geisternetzId = geisternetzId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
