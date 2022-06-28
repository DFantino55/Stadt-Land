package ch.bzz.StadtLand.Service;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * konfiguriert web services und properties
 */
@ApplicationPath("/resource")
public class Config extends Application {
    private static final String PROPERTIES_PATH = "/home/bzz/webapp/stadtlist.properties";
    private static Properties properties = null;

    /**
     * definiert alle provider Klassen
     *
     * @return set mit Klassen
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet providers = new HashSet<Class<?>>();
        providers.add(TestService.class);
        providers.add(StadtService.class);
        providers.add(LandService.class);
        providers.add(BenutzerService.class);
        return providers;
    }

    /**
     * Getter für Property
     *
     * @param property welche gelesen wird
     * @return wert der property
     */
    public static String getProperty(String property) {
        if (Config.properties == null) {
            setProperties(new Properties());
            readProperties();
        }
        String value = Config.properties.getProperty(property);
        if (value == null) return "";
        return value;
    }

    /**
     * liest property files
     */
    private static void readProperties() {

        InputStream inputStream;
        try {
            inputStream = new FileInputStream(PROPERTIES_PATH);
            properties.load(inputStream);
            if (inputStream != null) inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * Setter für Properties
     *
     * @param properties zu setzende Properties
     */
    private static void setProperties(Properties properties) {
        Config.properties = properties;
    }
}