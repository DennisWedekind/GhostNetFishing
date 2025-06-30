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
public class GeisternetzGeborgenController {

    private Long geisternetzId;
    private Person person = new Person();

    @Inject
    private GeisternetzService service;

    // Liste der Geisternetze mit Status "Bergung bevorstehend"
    public List<Geisternetz> getAngekuendigteGeisternetze() {
        return service.findeGeisternetzeMitStatus(Status.BergungBevorstehend);
    }

    // Methode zum Melden der Bergung als abgeschlossen
    public String geisternetzGeborgenMelden() {
        service.alsGeborgenMelden(geisternetzId, person);
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
