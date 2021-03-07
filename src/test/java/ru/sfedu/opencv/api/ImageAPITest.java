package ru.sfedu.opencv.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.opencv.Main;

import static org.junit.Assert.*;

public class ImageAPITest {
    private  static final Logger logger = LogManager.getLogger(Main.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void Test() throws  Exception {
        try {
            ImageAPI img = new ImageAPI();
            logger.debug("Instance created successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}