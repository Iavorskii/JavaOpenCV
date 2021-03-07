package ru.sfedu.opencv.api;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.Locale;

import ru.sfedu.opencv.Constants;
import ru.sfedu.opencv.Main;
import static ru.sfedu.opencv.utils.ConfigurationUtil.getConfigurationEntry;

import org.opencv.core.Core;

public class ImageAPI {
    private  static final Logger logger = LogManager.getLogger(Main.class);

    public ImageAPI() throws Exception {
        logger.info("Checking OS.....");
        // init the API with curent os..
        Constants.OSType CurrentOSTypeName = getOperatingSystemType();
        logger.info("Current OS type name - " + CurrentOSTypeName);
        switch (CurrentOSTypeName) {
            case LINUX:
                System.load(getConfigurationEntry(Constants.PATH_TO_NATIVE_LIB_LINUX));
                break;
            case WINDOWS:
                System.load(getConfigurationEntry(Constants.PATH_TO_NATIVE_LIB_WINDOWS));
                break;
            case MACOS:
                System.load(getConfigurationEntry(Constants.PATH_TO_NATIVE_LIB_MAC_OS));
            case OTHER:
                throw new Exception("Current OS does not support!!!!!");
            default:
                throw new Exception("Your OS does not support!!!");
        }
        logger.info("OpenCV version" + Core.getVersionString());
    }

    public Constants.OSType getOperatingSystemType() {
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if (OS.contains("mac") || OS.contains("darwin")) {
            return Constants.OSType.MACOS;
        } else if (OS.contains("win")) {
            return Constants.OSType.WINDOWS;
        } else if (OS.contains("nux")) {
            return Constants.OSType.LINUX;
        } else {
            return Constants.OSType.OTHER;
        }
    }
}
