package ch.bzz.StadtLand.Model;

/**
 * ein Benutzer der Applikation
 */
public class Benutzer {
    private String uuid;
    private String benutzername;
    private String passwort;
    private String role;

    /**
     * Konstruktor
     */
    public Benutzer() {
        setRole("guest");
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
