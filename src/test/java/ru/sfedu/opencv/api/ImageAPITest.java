package ru.sfedu.opencv.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import ru.sfedu.opencv.Main;

import static org.junit.Assert.*;
import static org.opencv.core.CvType.CV_8UC3;
import static ru.sfedu.opencv.Constants.CFG_KEY;
import static ru.sfedu.opencv.Constants.PATH_TO_SAVED_IMAGES;

public class ImageAPITest {
    private  static final Logger logger = LogManager.getLogger(ImageAPITest.class);

//    @Before
//    public void setUp() throws Exception {
//        System.setProperty(CFG_KEY, "src/main/resources/config.properties");
//    }

    public Mat createNoize() {
        int width = 300, height = 300;
        Mat mat = new Mat(new Size(width, height), CV_8UC3, new Scalar(0, 0, 0)); // создаем трехканальное изображение
        Core.randn(mat, 20, 50);
        Core.add(mat, mat, mat);
        return mat;
    }

    @Test
    public void Test() throws  Exception {
        try {
            ImageAPI img = new ImageAPI();
            logger.debug("Instance created successfully");

            Mat defaultMat = createNoize();
//            img.showImage(defaultMat);

            Mat removedBlueChannelImg = img.nullifyImageChannel(defaultMat, 0);
//            img.showImage(removedBlueChannelImg);

            Mat removedGreenChannelImg = img.nullifyImageChannel(removedBlueChannelImg, 1);
//            img.showImage(removedGreenChannelImg);

            Mat removedRedChannelImg = img.nullifyImageChannel(removedBlueChannelImg, 2);
//            img.showImage(removedRedChannelImg);

            img.saveImageOnDisk(removedRedChannelImg, "D:\\GitProjects\\JavaOpenCV\\images\\test.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}