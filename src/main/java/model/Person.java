package model;

import jakarta.persistence.*;

/**
 * Repräsentiert eine Person mit den Attributen ID, Vorname, Nachname und Telefonnummer
 */
@Entity // JPA-Annotation zur Kennzeichnung als persistente Entität
public class Person {

    @Id // Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatisch generierte ID
    private Long id;              // Eindeutige ID Person
    private String vorname;       // Vorname der Person
    private String nachname;      // Nachname der Person
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
