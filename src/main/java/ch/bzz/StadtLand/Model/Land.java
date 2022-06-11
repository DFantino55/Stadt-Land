package ch.bzz.StadtLand.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

/**
 * a country (land) with citys (stadt)
 */
public class Land {
    private String laenderCode;
    private String bezeichnung;
    @JsonIgnore
    private List<Stadt> stadtList;
    private Integer gruendungsJahr;
    private Integer bevoelkerung;
    private Double flaeche;

    //* Getter and Setter

    /**
     * gets laenderCode
     *
     * @return value of laenderCode
     */
    public String getLaenderCode() {
        return laenderCode;
    }

    /**
     * sets laenderCode
     *
     * @param laenderCode the value to set
     */
    public void setLaenderCode(String laenderCode) {
        this.laenderCode = laenderCode;
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
     * gets stadtList
     *
     * @return value of stadtList
     */
    public List<Stadt> getStadtList() { return stadtList; }

    /**
     * sets stadtList
     *
     * @param stadtList the value to set
     */
    public void setStadtList(List<Stadt> stadtList) { this.stadtList = stadtList; }

    /**
     * gets gruendungsjahr
     *
     * @return value of gruendungsjahr
     */

    public Integer getGruendungsJahr() {
        return gruendungsJahr;
    }

    /**
     * sets gruendungsjahr
     *
     * @param gruendungsjahr the value to set
     */

    public void setGruendungsJahr(Integer gruendungsjahr) {
        this.gruendungsJahr = gruendungsjahr;
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
