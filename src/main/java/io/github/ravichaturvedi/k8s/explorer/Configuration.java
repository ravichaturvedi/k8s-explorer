package io.github.ravichaturvedi.k8s.explorer;


import java.io.StringReader;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Configuration {

    private static volatile Properties PROPERTIES;

    private static final Path BASE_PATH = Paths.get("/usr/share/k8s-explorer");

    private static final Configuration INSTANCE = new Configuration();


    static {
        try {
            PROPERTIES = new Properties();
            PROPERTIES.load(Configuration.class.getResourceAsStream("/app.properties"));
        } catch (Exception e) {
            throw new IllegalStateException("Unable to load properties file from resources.");
        }
    }

    public static Configuration getInstance() {
        return INSTANCE;
    }

    public String getMessage() throws Exception {
        return "Hostname: " + InetAddress.getLocalHost().getHostName() + "\n\nMessage: " + getMessageInternal();
    }

    private String getMessageInternal() throws Exception {
        if (!Files.exists(BASE_PATH.resolve("message"))) {
            return "Hello to K8s Explorers!";
        }
        return new String(Files.readAllBytes(BASE_PATH.resolve("message")));
    }

    public int sleepTimeInSecondsBeforeGreet() {
        return Integer.parseInt(PROPERTIES.getProperty("sleeptimeInSecondsBeforeGreet"));
    }

    public void updateProperties(String propStr) throws Exception {
        Properties props = new Properties();
        props.load(new StringReader(propStr));

        if (!props.containsKey("sleeptimeInSecondsBeforeGreet")) return;

        PROPERTIES = props;
    }
}
