package ru.sfedu.opencv.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.opencv.Constants;
import ru.sfedu.opencv.Main;

import static ru.sfedu.opencv.Constants.CFG_KEY;
import static ru.sfedu.opencv.Constants.PATH_TO_NATIVE_LIB_WINDOWS;
import static ru.sfedu.opencv.utils.ConfigurationUtil.getConfigurationEntry;

public class ConfigurationUtilTest {
    private  static final Logger logger = LogManager.getLogger(Main.class);

    @Before
    public void setUp() throws Exception {
        System.setProperty(CFG_KEY, "src/test/resources/config1.properties");
    }

//    @Test
//    public  void test() throws Exception {
//        logger.info("PATH_TO_NATIVE_LIB_WINDOWS" + PATH_TO_NATIVE_LIB_WINDOWS);
//        Assert.assertEquals(getConfigurationEntry(Constants.PATH_TO_NATIVE_LIB_WINDOWS), "ru.sfedu.cv.PathToNativeLibs.Windows");
//    }
}