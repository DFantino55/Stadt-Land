package ch.bzz.StadtLand.Model;
import ch.bzz.StadtLand.Data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;

//import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/*
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
 */

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * a book in the bookshelf
 */
public class Stadt {
    @JsonIgnore
    private Land land;

    @FormParam("uuid")
    @NotEmpty
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String uuid;

    @FormParam("bezeichnung")
    @NotEmpty
    @Size(min=3, max=30)
    private String bezeichnung;

    @FormParam("bevoelkerung")
    @NotNull
    @Min(1)
    private Integer bevoelkerung;

    @FormParam("flaeche")
    @NotNull
    @DecimalMin(value="1.00")
    private BigDecimal flaeche;

    /**
     * gets the publisherUUID from the Publisher-object
     * @return
     */
    public String getLaendercode() {
        if (getLand()== null) return null;
        return getLand().getLaenderCode();
    }

    /**
     * creates a Publisher-object without the booklist
     * @param publisherUUID the key
     */
    public void setLaendercode(String laendercode) {
        setLand(new Land());
        getLand().setLaenderCode(laendercode);
        if (DataHandler.readLandByLaendercode(laendercode) != null) {
            Land land = DataHandler.readLandByLaendercode(laendercode);
            getLand().setBezeichnung(land.getBezeichnung());
            //Pottentielle Fehlerquelle
            getLand().setBevoelkerung(land.getBevoelkerung());
            getLand().setFlaeche(land.getFlaeche());
            getLand().setGruendungsJahr(land.getGruendungsJahr());
        }

    }

    /**
     * gets publisher
     *
     * @return value of publisher
     */
    public Land getLand() {
        return land;
    }

    /**
     * sets publisher
     *
     * @param land the value to set
     */
    public void setLand(Land land) {
        this.land = land;
    }

    /**
     * gets bookUUID
     *
     * @return value of bookUUID
     */

    public String getUuid() {
        return uuid;
    }

    /**
     * sets bookUUID
     *
     * @param bookUUID the value to set
     */

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * gets title
     *
     * @return value of title
     */

    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * sets title
     *
     * @param bezeichnung the value to set
     */

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    /**
     * gets author
     *
     * @return value of author
     */

    public Integer getBevoelkerung() {
        return bevoelkerung;
    }

    /**
     * sets author
     *
     * @param bevoelkerung the value to set
     */

    public void setBevoelkerung(Integer bevoelkerung) {
        this.bevoelkerung = bevoelkerung;
    }


    /**
     * gets price
     *
     * @return value of price
     */
    public BigDecimal getFlaeche() {
        return flaeche;
    }

    /**
     * sets price
     *
     * @param flaeche the value to set
     */

    public void setFlaeche(BigDecimal flaeche) {
        this.flaeche = flaeche;
    }
}