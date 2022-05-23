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
    private static DataHandler instance = null;
    private List<Stadt> stadtList;
    private List<Land> landList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setStadtList(new ArrayList<>());
        //readPublisherJSON();
        readLandJSON();
        setLandList(new ArrayList<>());
        //readBookJSON();
        readStadtJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all books
     * @return list of books
     */
    public List<Stadt> readAllStaedte() {
        return getStadtList();
    }

    /**
     * reads a book by its uuid
     * @param stadtUUID
     * @return the Book (null=not found)
     */
    public Stadt readStadtByUUID(String stadtUUID) {
        Stadt stadt = null;
        for (Stadt entry : getStadtList()) {
            if (entry.getUuid().equals(stadtUUID)) {
                stadt = entry;
            }
        }
        return stadt;
    }

    /**
     * reads all Publishers
     * @return list of publishers
     */
    public List<Land> readAllLaender() {

        return getLandList();
    }

    /**
     * reads a publisher by its uuid
     * @param laendercode
     * @return the Publisher (null=not found)
     */
    public Land readLandByLaendercode(String laendercode) {
        Land land = null;
        for (Land entry : getLandList()) {
            if (entry.getLaenderCode().equals(laendercode)) {
                land = entry;
            }
        }
        return land;
    }

    /**
     * reads the books from the JSON-file
     */
    private void readStadtJSON() {
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
     * reads the publishers from the JSON-file
     */
    private void readLandJSON() {
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

    //Done

    /**
     * gets stadtList
     *
     * @return value of stadtList
     */

    private List<Stadt> getStadtList() { return stadtList; }

    /**
     * sets stadtList
     *
     * @param stadtList the value to set
     */
    private void setStadtList(List<Stadt> stadtList) { this.stadtList = stadtList; }

    /**
     * gets landList
     *
     * @return value of landList
     */
    private List<Land> getLandList() { return landList; }

    /**
     * sets landList
     *
     * @param landList the value to set
     */
    private void setLandList(List<Land> landList) {this.landList = landList; }
}