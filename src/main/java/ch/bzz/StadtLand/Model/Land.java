package ch.bzz.StadtLand.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.util.List;

/**
 * ein Land mit Staedten
 */
public class Land {
    @FormParam("laendercode")
    @NotEmpty
    @Pattern(regexp = "[A-Z]{2}-[A-Z]{3}-[0-9]{3}")
    private String laenderCode;

    @FormParam("bezeichnung")
    @NotEmpty
    @Size(min=3, max=30)
    private String bezeichnung;

    @JsonIgnore
    private List<Stadt> stadtList;

    @FormParam("gruendungsJahr")
    @NotNull
    @Max(2022)
    private Integer gruendungsJahr;

    @FormParam("bevoelkerung")
    @NotNull
    @Min(1)
    private Integer bevoelkerung;

    @FormParam("flaeche")
    @NotNull
    @Min(1)
    private Double flaeche;

    /**
     * Getter für laenderCode
     *
     * @return den laenderCode
     */
    public String getLaenderCode() {
        return laenderCode;
    }

    /**
     * Setter für laenderCode
     *
     * @param laenderCode Wert der gesetzt wird
     */
    public void setLaenderCode(String laenderCode) {
        this.laenderCode = laenderCode;
    }

    /**
     * Getter für bezeichnung
     *
     * @return die bezeichnung
     */
    public String getBezeichnung() { return bezeichnung; }

    /**
     * Setter für bezeichnung
     *
     * @param bezeichnung der gesetzte Wert
     */
    public void setBezeichnung(String bezeichnung) { this.bezeichnung = bezeichnung; }

    /**
     * Getter für stadtList
     *
     * @return Wert der stadtList
     */
    public List<Stadt> getStadtList() { return stadtList; }

    /**
     * Setter für stadtList
     *
     * @param stadtList der gesetzte Wert
     */
    public void setStadtList(List<Stadt> stadtList) { this.stadtList = stadtList; }

    /**
     * Getter für gruendungsjahr
     *
     * @return Wert gruendungsjahr
     */

    public Integer getGruendungsJahr() {
        return gruendungsJahr;
    }

    /**
     * Setter für gruendungsjahr
     *
     * @param gruendungsjahr der gesetzte Wert
     */

    public void setGruendungsJahr(Integer gruendungsjahr) {
        this.gruendungsJahr = gruendungsjahr;
    }

    /**
     * Getter für bevoelkerung
     *
     * @return Wert bevoelkerung
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
     * @return Wert der flaeche
     */
    public Double getFlaeche() {
        return flaeche;
    }

    /**
     * Setter für flaeche
     *
     * @param flaeche der gesetzte Wert
     */
    public void setFlaeche(Double flaeche) {
        this.flaeche = flaeche;
    }

}
