package ch.bzz.StadtLand.Data;


import ch.bzz.StadtLand.Model.Land;
import ch.bzz.StadtLand.Model.Stadt;
import ch.bzz.StadtLand.Service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.xml.crypto.Data;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public final class DataHandler {
    private static List<Stadt> stadtList;
    private static List<Land> landList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
    }

    /**
     * initialize the lists with the data
     */
    public static void initLists() {
        DataHandler.setStadtList(null);
        DataHandler.setLandList(null);
    }

    /**
     * reads all books
     * @return list of books
     */
    public static List<Stadt> readAllStaedte() {
        return getStadtList();
    }

    /**
     * reads a book by its uuid
     * @param bookUUID
     * @return the Book (null=not found)
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
     * inserts a new book into the bookList
     *
     * @param book the book to be saved
     */
    public static void insertStadt(Stadt stadt) {
        getStadtList().add(stadt);
        writeStadtJSON();
    }

    /**
     * updates the bookList
     */
    public static void updateStadt() {
        writeStadtJSON();
    }

    /**
     * deletes a book identified by the bookUUID
     * @param bookUUID  the key
     * @return  success=true/false
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
     * reads all publishers
     * @return list of books
     */
    public static List<Land> readAllLaender() {
        return getLandList();
    }

    /**
     * reads a publisher by its uuid
     * @param publisherUUID
     * @return the Publisher (null=not found)
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
     * inserts a new publisher into the bookList
     *
     * @param publisher the publisher to be saved
     */
    public static void insertLand(Land land) {
        getLandList().add(land);
        writeLandJSON();
    }

    /**
     * updates the publisherList
     */
    public static void updateLand() {
        writeLandJSON();
    }

    /**
     * deletes a publisher identified by the publisherUUID
     * @param publisherUUID  the key
     * @return  success=true/false
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
     * reads the books from the JSON-file
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
     * writes the bookList to the JSON-file
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
     * reads the publishers from the JSON-file
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
     * writes the publisherList to the JSON-file
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
     * gets bookList
     *
     * @return value of bookList
     */

    private static List<Stadt> getStadtList() {

        if (stadtList == null) {
            setStadtList(new ArrayList<>());
            readStadtJSON();
        }
        return stadtList;
    }

    /**
     * sets bookList
     *
     * @param bookList the value to set
     */

    private static void setStadtList(List<Stadt> stadtList) {
        DataHandler.stadtList = stadtList;
    }

    /**
     * gets publisherList
     *
     * @return value of publisherList
     */

    private static List<Land> getLandList() {
        if (landList == null) {
            setLandList(new ArrayList<>());
            readLandJSON();
        }

        return landList;
    }

    /**
     * sets publisherList
     *
     * @param publisherList the value to set
     */

    private static void setLandList(List<Land> landList) {
        DataHandler.landList = landList;
    }


}