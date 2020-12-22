package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;

    public static String getConfigProperty(String filename, String key) {
        try {
            if (filename == null) {
                throw new IllegalArgumentException("The capabilities filename cannot be null!");
            }
            fileInputStream = new FileInputStream("src/test/resources/capabilities/" + filename + ".properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return PROPERTIES.getProperty(key);
    }

}