package model;

import jakarta.persistence.*;


@Entity  //Kennzeichnet Datenbank-Entity Geisternetz
public class Geisternetz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primärschlüssel, automatisch generiert

    private Double laengengrad;        // Längengrad des Standortes
    private Double breitengrad;        // Breitengrad des Standortes
    private Double geschaetzteGroesse; // Geschätzte Fläche in Quadratmetern

    @Enumerated(EnumType.STRING)       // Enum wird als String gespeichert
    private Status status;

    @ManyToOne
    private Person meldendePerson;

    @ManyToOne
    private Person bergendePerson;

    @ManyToOne
    private Person bergendMeldendePerson;

    @ManyToOne
    private Person verschollenMeldendePerson;

    public Geisternetz() {} // Standard-Konstruktor

    // Getter und Setter ...
    public Long getId() {
        return id;
    }

    public Double getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(Double laengengrad) {
        this.laengengrad = laengengrad;
    }

    public Double getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(Double breitengrad) {
        this.breitengrad = breitengrad;
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

    public Person getBergendMeldendePerson() {
        return bergendMeldendePerson;
    }

    public void setBergendMeldendePerson(Person bergendePerson) {
        this.bergendMeldendePerson = bergendePerson;
    }

    public Person getVerschollenMeldendePerson() {
        return verschollenMeldendePerson;
    }

    public void setVerschollenMeldendePerson(Person verschollenPerson) {
        this.verschollenMeldendePerson = verschollenPerson;
    }
}
