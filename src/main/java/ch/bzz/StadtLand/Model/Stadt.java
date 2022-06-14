package ch.bzz.StadtLand.Model;
import ch.bzz.StadtLand.Data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.math.BigDecimal;

/**
 * eine Stadt in einem Land
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
     * Getter für laendercode des Landes
     * @return laendercode den Laendercode des Landes
     */
    public String getLaendercode() {
        if (getLand()== null) return null;
        return getLand().getLaenderCode();
    }

    /**
     * erstellt ein Land ohne die stadtList
     * @param laendercode laendercode des Landes
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
     * Getter für Land
     *
     * @return Land das Land
     */
    public Land getLand() {
        return land;
    }

    /**
     * Setter für Land
     *
     * @param land das Land das gesetzt wird
     */
    public void setLand(Land land) {
        this.land = land;
    }

    /**
     * Getter für uuid
     *
     * @return Wert der uuid der Stadt
     */

    public String getUuid() {
        return uuid;
    }

    /**
     * Setter für uuid
     *
     * @param uuid uuid der Stadt
     */

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Getter für bezeichnung
     *
     * @return bezeichnung der Stadt
     */

    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * Setter für bezeichnung
     *
     * @param bezeichnung der gesetzte Wert
     */

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    /**
     * Getter für bevoelkerung
     *
     * @return bevoelkerung der Stadt
     */

    public Integer getBevoelkerung() {
        return bevoelkerung;
    }

    /**
     * Setter für bevoelkerung
     *
     * @param bevoelkerung der gesetzte Wert
     */

    public void setBevoelkerung(Integer bevoelkerung) {
        this.bevoelkerung = bevoelkerung;
    }


    /**
     * Getter für flaeche
     *
     * @return flaeche der Stadt
     */
    public BigDecimal getFlaeche() {
        return flaeche;
    }

    /**
     * Setter für flaeche
     *
     * @param flaeche der gesetzte Wert
     */

    public void setFlaeche(BigDecimal flaeche) {
        this.flaeche = flaeche;
    }
}