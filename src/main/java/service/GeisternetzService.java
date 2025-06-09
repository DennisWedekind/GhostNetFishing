package service;

import DAO.PersonDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Geisternetz;
import model.Person;
import model.Status;
import DAO.GeisternetzDAO;

import java.util.List;

@ApplicationScoped
@Transactional
public class GeisternetzService {

    @Inject
    private GeisternetzDAO geisternetzDAO;
    @Inject
    private PersonDAO personDAO;
    @Inject
    private PersonService personService;


    // Prüft, ob Geisternetz schon existiert, sonst speichern
    public Geisternetz findeOderErstelleGeisternetz(Geisternetz geisternetz) {
        Geisternetz vorhandenesGeisternetz = geisternetzDAO.findeGeisternetz(
                geisternetz.getBreitengrad(),
                geisternetz.getLaengengrad(),
                geisternetz.getGeschaetzteGroesse()
        );

        if (vorhandenesGeisternetz != null) {
            return vorhandenesGeisternetz;
        } else {
            geisternetzDAO.speichereGeisternetz(geisternetz);
            return geisternetz;
        }
    }

    // Geisternetz melden
    public void meldenGeisternetz(Geisternetz geisternetz, Person person) {
        Person gespeichertePerson = personService.findeOderErstellePerson(person);
        geisternetz.setMeldendePerson(gespeichertePerson);
        geisternetz.setStatus(Status.Gemeldet);
        findeOderErstelleGeisternetz(geisternetz);
    }

    // Bergung anmelden
    public void bergungAnmelden(Long geisternetzId, Person person) {
        Geisternetz geisternetz = geisternetzDAO.findeGeisternetzNachId(geisternetzId);
        Person gespeichertePerson = personService.findeOderErstellePerson(person);
        geisternetz.setBergendePerson(gespeichertePerson);
        geisternetz.setStatus(Status.BergungBevorstehend);
        geisternetzDAO.aktualisiereGeisternetz(geisternetz);
    }

    // Als geborgen melden
    public void alsGeborgenMelden(Long geisternetzId, Person person) {
        Geisternetz geisternetz = geisternetzDAO.findeGeisternetzNachId(geisternetzId);
        Person gespeichertePerson = personService.findeOderErstellePerson(person);
        geisternetz.setGeborgenPerson(gespeichertePerson);
        geisternetz.setStatus(Status.Geborgen);
        geisternetzDAO.aktualisiereGeisternetz(geisternetz);
    }

    // Als verschollen melden
    public void alsVerschollenMelden(Long geisternetzId, Person person) {
        Geisternetz geisternetz = geisternetzDAO.findeGeisternetzNachId(geisternetzId);
        Person gespeichertePerson = personService.findeOderErstellePerson(person);
        geisternetz.setVerschollenPerson(gespeichertePerson);
        geisternetz.setStatus(Status.Verschollen);
        geisternetzDAO.aktualisiereGeisternetz(geisternetz);
    }

    // Liste von Geisternetzen mit bestimmtem Status abrufen
    public List<Geisternetz> findeGeisternetzeMitStatus(Status status) {
        return geisternetzDAO.findeGeisternetzNachStatus(status);
    }

    // Geisternetz per ID finden
    public Geisternetz findeGeisternetzNachId(Long id) {
        return geisternetzDAO.findeGeisternetzNachId(id);
    }
}
