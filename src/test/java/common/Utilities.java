package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Utilities {
    private static Logger logger = Logger.getLogger(Utilities.class.getSimpleName());
    public static String sDirPath = System.getProperty("user.dir");
    public static String sUserPath = System.getProperty("user.home");

    public static String getConfigValue(String sFile, String sKey) {
        logger.info("**** Read Configuration file ****");
        Properties prop = new Properties();
        String sValue = null;
        try {
            InputStream input = new FileInputStream(sFile);
            prop.load(input);
            sValue = prop.getProperty(sKey);
            logger.info("***** Value from Properties file of Parameter: " + sKey + ": " + sValue);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.info("***** Can not find the properties file ****" + sValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sValue;
    }
}
