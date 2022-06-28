package ch.bzz.StadtLand.Model;

/**
 * ein Benutzer der Applikation
 */
public class Benutzer {
    private String uuid;
    private String benutzername;
    private String passwort;
    private String rolle;

    /**
     * Konstruktor
     */
    public Benutzer() {
        setRolle("guest");
    }

    /**
     * Getter für uuid
     *
     * @return wert der uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Setter für uuid
     *
     * @param uuid die gesetzt wird
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Getter für Benutzername
     *
     * @return den Benutzernamen
     */
    public String getBenutzername() {
        return benutzername;
    }

    /**
     * Setter für Benutzername
     *
     * @param benutzername der gesetzt wird
     */
    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    /**
     * Getter für passwort
     *
     * @return das passwort
     */
    public String getPasswort() {
        return passwort;
    }

    /**
     * Setter für passwort
     *
     * @param passwort das gesetzt wird
     */
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    /**
     * Getter für Rolle
     *
     * @return die Rolle
     */
    public String getRolle() {
        return rolle;
    }

    /**
     * Setter für Rolle
     *
     * @param rolle die gesetzt wird
     */
    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
}
