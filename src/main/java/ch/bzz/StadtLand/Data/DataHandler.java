package ch.bzz.StadtLand.Data;
import ch.bzz.StadtLand.Model.Stadt;
import ch.bzz.StadtLand.Model.Land;
import ch.bzz.StadtLand.Service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static List<Stadt> stadtList;
    private static List<Land> landList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
    }


    /**
     * reads all staedte
     * @return list of staedte
     */
    public static List<Stadt> readAllStaedte() {
        return getStadtList();
    }

    /**
     * reads a stadt by its uuid
     * @param stadtUUID uuid of stadt
     * @return stadt (null=not found)
     */
    public static Stadt readStadtByUUID(String stadtUUID) {
        Stadt stadt = null;
        for (Stadt entry : getStadtList()) {
            if (entry.getUuid().equals(stadtUUID)) {
                stadt = entry;
            }
        }
        return stadt;
    }

    /**
     * reads all laender
     * @return list of laender
     */
    public static List<Land> readAllLaender() {
        return getLandList();
    }

    /**
     * reads a land by its laendercode
     * @param laendercode specific code for each land
     * @return the land (null=not found)
     */
    public static Land readLandByLaendercode(String laendercode) {
        Land land = null;
        for (Land entry : getLandList()) {
            if (entry.getLaenderCode().equals(laendercode)) {
                land = entry;
            }
        }
        return land;
    }

    /**
     * reads the stadt from the JSON-file
     */
    private static void readStadtJSON() {
        try {
            String path = Config.getProperty("stadtJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Stadt[] staedte = objectMapper.readValue(jsonData, Stadt[].class);
            for (Stadt stadt : staedte) {
                getStadtList().add(stadt);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the land from the JSON-file
     */
    private static void readLandJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("landJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Land[] laender = objectMapper.readValue(jsonData, Land[].class);
            for (Land land : laender) {
                getLandList().add(land);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //* Getter and Setter

    /**
     * gets stadtList
     *
     * @return value of stadtList
     */

    private static List<Stadt> getStadtList() {
        if (stadtList == null) {
            setStadtList(new ArrayList<>());
            readLandJSON();
        }
        return stadtList;
    }

    /**
     * sets stadtList
     *
     * @param stadtList the value to set
     */
    private static void setStadtList(List<Stadt> stadtList) { DataHandler.stadtList = stadtList; }

    /**
     * gets landList
     *
     * @return value of landList
     */
    private static List<Land> getLandList() {
        if (landList == null) {
            setLandList(new ArrayList<>());
            readStadtJSON();
        }
        return landList;
    }

    /**
     * sets landList
     *
     * @param landList the value to set
     */
    private static void setLandList(List<Land> landList) {DataHandler.landList = landList; }
}