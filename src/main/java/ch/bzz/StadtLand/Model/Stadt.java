package ch.bzz.StadtLand.Model;
import ch.bzz.StadtLand.Data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;

//import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;


/**
 * a city (stadt) in a country (land)
 */
public class Stadt {
    @JsonIgnore
    private Land land;

    @FormParam("uuid")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    @NotEmpty
    private String uuid;

    @FormParam("bezeichnung")
    @NotEmpty
    @Size(min=3, max=30)
    private String bezeichnung;

    @FormParam("bevoelkerung")
    @Min(1)
    @NotNull
    private Integer bevoelkerung;

    @FormParam("flaeche")
    @Min(1)
    @NotNull
    private Double flaeche;

    //* Getter and Setter

    //test
    public String getLaendercode() {
        if (getLand()== null) return null;
        return getLand().getLaenderCode();
    }

    public void setLandByLaendercode(String laendercode) {
        setLand(new Land());
        Land land = DataHandler.readLandByLaendercode(laendercode);
        //setLand(land);
        getLand().setLaenderCode(laendercode);
        //getLand().setBezeichnung(land.getBezeichnung());
        /* if (land != null) {
            getLand().setBezeichnung(land.getBezeichnung());
            getLand().setGruendungsJahr(land.getGruendungsJahr());
            getLand().setBevoelkerung(land.getBevoelkerung());
            getLand().setFlaeche(land.getFlaeche());
        }
         */
    }

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