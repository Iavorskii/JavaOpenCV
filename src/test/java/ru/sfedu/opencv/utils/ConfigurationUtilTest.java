package ru.sfedu.opencv.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static ru.sfedu.opencv.Constant.CFG_KEY;
import static ru.sfedu.opencv.Constant.PATH_TO_NATIVE_LIB;

public class ConfigurationUtilTest {

    @Before
    public void setUp() throws Exception {
        System.setProperty(CFG_KEY, "src/test/resources/config1.properties");
    }

    @Test
    public  void test() throws Exception {
//        System.out.println(ConfigurationUtil.getConfigurationEntry(PATH_TO_NATIVE_LIB));
        Assert.assertEquals(ConfigurationUtil.getConfigurationEntry(PATH_TO_NATIVE_LIB), "667");
    }
}