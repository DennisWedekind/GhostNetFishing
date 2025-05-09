package model;

import jakarta.persistence.*;

@Entity //Kennzeichnet Datenbank-Entity Geisternetz
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primärschlüssel, automatisch generiert

    private String vorname; // Vorname der Person
    private String nachname; // Nachname der Person
    private String telefonnummer; // Telefonnummer der Person

    // Standard-Konstruktor
    public Person() {}

    // Getter und Setter ...
    public Long getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
}
