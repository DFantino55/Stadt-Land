package ch.bzz.StadtLand.Data;

import ch.bzz.StadtLand.Model.Benutzer;
import ch.bzz.StadtLand.Service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static final UserData instance = new UserData();

    public static Benutzer findUser(String benutzername, String passwort) {
        Benutzer benutzer = new Benutzer();
        List<Benutzer> benutzerList = readJson();

        for (Benutzer entry : benutzerList) {
            if (entry.getBenutzername().equals(benutzername) &&
                entry.getPasswort().equals(passwort)) {
                benutzer = entry;
            }
        }
        return benutzer;
    }


    private static List<Benutzer> readJson() {
        List<Benutzer> benutzerList = new ArrayList<>();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(Config.getProperty("benutzerJSON")));
            ObjectMapper objectMapper = new ObjectMapper();
            Benutzer[] benutzer = objectMapper.readValue(jsonData, Benutzer[].class);

            for (Benutzer benutzers : benutzer) {
                benutzerList.add(benutzers);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return benutzerList;
    }
}
