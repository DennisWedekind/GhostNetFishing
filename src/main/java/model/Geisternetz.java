package model;

import jakarta.persistence.*;

/**
 * Repräsentiert ein Geisternetz mit den Attributen ID, Längengrad, Breitengrad, Größe, Status
 * und den beteiligten Personen.
 */
@Entity // JPA-Annotation zur Kennzeichnung als persistente Entität
public class Geisternetz {

    @Id // Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatisch generierte ID
    private Long id;                   // Eindeutige ID Geisternetz
    private Double breitengrad;        // Breitengrad des Standortes
    private Double laengengrad;        // Längengrad des Standortes
    private Double geschaetzteGroesse; // Geschätzte Fläche in Quadratmetern

    @Enumerated(EnumType.STRING)       // Status wird als String in der DB gespeichert
    private Status status;             // Aktueller Status

    @ManyToOne
    private Person meldendePerson;     // Person, welche das Geisternetz gemeldet hat

    @ManyToOne
    private Person bergendePerson;     // Person, welche die Bergung ankündigt

    @ManyToOne
    private Person geborgenPerson;     // Person, welche das Geisternetz als geborgen meldet

    @ManyToOne
    private Person verschollenPerson;  // Person, welche das Geisternetz als verschollen meldet

    public Geisternetz() {}            // Parameterloser Standard-Konstruktor

    // Getter und Setter ...
    public Long getId() {
        return id;
    }

    public Double getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(Double breitengrad) {
        this.breitengrad = breitengrad;
    }

    public Double getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(Double laengengrad) {
        this.laengengrad = laengengrad;
    }

    public Double getGeschaetzteGroesse() {
        return geschaetzteGroesse;
    }

    public void setGeschaetzteGroesse(Double geschaetzteGroesse) {
        this.geschaetzteGroesse = geschaetzteGroesse;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Person getMeldendePerson() {
        return meldendePerson;
    }

    public void setMeldendePerson(Person meldendePerson) {
        this.meldendePerson = meldendePerson;
    }

    public Person getBergendePerson() {
        return bergendePerson;
    }

    public void setBergendePerson(Person bergendePerson) {
        this.bergendePerson = bergendePerson;
    }

    public Person getGeborgenPerson() {
        return geborgenPerson;
    }

    public void setGeborgenPerson(Person geborgenPerson) {
        this.geborgenPerson = geborgenPerson;
    }

    public Person getVerschollenPerson() {
        return verschollenPerson;
    }

    public void setVerschollenPerson(Person verschollenPerson) {
        this.verschollenPerson = verschollenPerson;
    }
}