package service;

import DAO.PersonDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Person;

@ApplicationScoped
@Transactional
public class PersonService {

    @Inject
    private PersonDAO personDAO;

    public Person findeOderErstellePerson(Person person) {
        Person vorhandenePerson = personDAO.findePerson(
                person.getVorname(), person.getNachname(), person.getTelefonnummer()
        );
        if (vorhandenePerson != null) {
            return vorhandenePerson;
        } else {
            personDAO.speicherePerson(person);
            return person;
        }
    }

}
