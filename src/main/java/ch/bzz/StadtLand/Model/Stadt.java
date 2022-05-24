package ch.bzz.StadtLand.Model;
import ch.bzz.StadtLand.Model.Land;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * a city (stadt) in a country (land)
 */
public class Stadt {
    private String uuid;
    private String bezeichnung;
    @JsonIgnore
    private Land land;
    private Integer bevoelkerung;
    private Double flaeche;

    //* Getter and Setter

    /**
     * gets UUID
     *
     * @return value of UUID
     */
    public String getUuid() { return uuid; }

    /**
     * sets uuid
     *
     * @param uuid the value to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * gets bezeichnung
     *
     * @return value of bezeichnung
     */
    public String getBezeichnung() { return bezeichnung; }

    /**
     * sets bezeichnung
     *
     * @param bezeichnung the value to set
     */
    public void setBezeichnung(String bezeichnung) { this.bezeichnung = bezeichnung; }

    /**
     * gets Land
     *
     * @return Land
     *
     */
    public Land getLand() {
        return land;
    }

    /**
     * sets Land
     *
     * @param land the value to set
     */
    public void setLand(Land land) {
        this.land = land;
    }

    /**
     * gets bevoelkerung
     *
     * @return value of bevoelkerung
     */
    public Integer getBevoelkerung() {
        return bevoelkerung;
    }

    /**
     * sets bevoelkerung
     *
     * @param bevoelkerung the value to set
     */
    public void setBevoelkerung(Integer bevoelkerung) {
        this.bevoelkerung = bevoelkerung;
    }

    /**
     * gets flaeche
     *
     * @return value of flaeche
     */
    public Double getFlaeche() {
        return flaeche;
    }

    /**
     * sets flaeche
     *
     * @param flaeche the value to set
     */
    public void setFlaeche(Double flaeche) {
        this.flaeche = flaeche;
    }

}