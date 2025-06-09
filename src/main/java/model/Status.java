package model;

/**
 * Enum zur Darstellung des Status eines Geisternetzes.
 * Ein Enum ist eine feste Liste von möglichen Werten.
 */
public enum Status {
    Gemeldet,             // Das Geisternetz wurde gemeldet.
    BergungBevorstehend, // Die Bergung des Geisternetzes wurde angekündigt.
    Geborgen,             // Das Geisternetz wurde erfolgreich geborgen.
    Verschollen           // Das Geisternetz gilt als verschollen.
}

