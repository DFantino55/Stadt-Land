package ch.bzz.StadtLand.Data;
import ch.bzz.StadtLand.Model.Land;
import ch.bzz.StadtLand.Model.Stadt;
import ch.bzz.StadtLand.Service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * schreibt und liest daten aus/in JSON-Files
 */
public final class DataHandler {
    private static List<Stadt> stadtList;
    private static List<Land> landList;

    /**
     * privater Konstruktor verhindert Instanzierung
     */
    private DataHandler() {
    }

    /**
     * setzt die listen zum wert null
     */
    public static void initLists() {
        DataHandler.setStadtList(null);
        DataHandler.setLandList(null);
    }

    /**
     * liest alle stadte
     * @return liste aller städte
     */
    public static List<Stadt> readAllStaedte() {
        return getStadtList();
    }

    /**
     * liest eine stadt durch uuid
     * @param stadtUUID uuid der stadt
     * @return die stadt oder null
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
     * führt eine neue stadt in list ein
     *
     * @param stadt die gespeichert wird
     */
    public static void insertStadt(Stadt stadt) {
        getStadtList().add(stadt);
        writeStadtJSON();
    }

    /**
     * aktualisiert stadtList
     */
    public static void updateStadt() {
        writeStadtJSON();
    }

    /**
     * deletes a book identified by the bookUUID
     * löscht stadt durch uuid
     * @param stadtUUID uuid der stadt
     * @return  erfolgreich oder nicht
     */
    public static boolean deleteStadt(String stadtUUID) {
        Stadt stadt = readStadtByUUID(stadtUUID);
        if (stadt != null) {
            getStadtList().remove(stadt);
            writeStadtJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * liest alle länder
     * @return liste aller länder
     */
    public static List<Land> readAllLaender() {
        return getLandList();
    }

    /**
     * liest ein land durch laendercode
     * @param laendercode des landes
     * @return land oder null
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
     * fügt ein neues land in die stadtlist ein
     *
     * @param land zu speicherndes land
     */
    public static void insertLand(Land land) {
        getLandList().add(land);
        writeLandJSON();
    }

    /**
     * aktualisiert landlist
     */
    public static void updateLand() {
        writeLandJSON();
    }

    /**
     * löscht land durch laendercode
     * @param laendercode des landes
     * @return  erfolgreich oder nicht
     */
    public static boolean deleteLand(String laendercode) {
        Land land = readLandByLaendercode(laendercode);
        if (land != null) {
            getLandList().remove(land);
            writeLandJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * liest die Städte vom JSON-File
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
     * schreibt die stadlist in JSON-File
     */
    private static void writeStadtJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String stadtPath = Config.getProperty("stadtJSON");
        try {
            fileOutputStream = new FileOutputStream(stadtPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getStadtList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * liest die länder von der landList
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

    /**
     * schreibt die Länder in JSON-File
     */
    private static void writeLandJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String landPath = Config.getProperty("landJSON");
        try {
            fileOutputStream = new FileOutputStream(landPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getLandList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Getter für stadtList
     *
     * @return stadlist liste der städte
     */

    private static List<Stadt> getStadtList() {

        if (stadtList == null) {
            setStadtList(new ArrayList<>());
            readStadtJSON();
        }
        return stadtList;
    }

    /**
     * Setter für stadList
     *
     * @param stadtList die gesetzt wird
     */

    private static void setStadtList(List<Stadt> stadtList) {
        DataHandler.stadtList = stadtList;
    }

    /**
     * Getter für landlist
     *
     * @return landListe liste der länder
     */

    private static List<Land> getLandList() {
        if (landList == null) {
            setLandList(new ArrayList<>());
            readLandJSON();
        }

        return landList;
    }

    /**
     * Getter für landList
     *
     * @param landList liste der länder
     */

    private static void setLandList(List<Land> landList) {
        DataHandler.landList = landList;
    }


}