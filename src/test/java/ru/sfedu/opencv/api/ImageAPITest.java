package ru.sfedu.opencv.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import ru.sfedu.opencv.utils.ConfigurationUtil;

import static org.opencv.core.CvType.CV_8UC3;
import static ru.sfedu.opencv.Constants.*;

public class ImageAPITest {
    private  static final Logger logger = LogManager.getLogger(ImageAPITest.class);

    //    @Before
//    public void setUp() throws Exception {
//        System.setProperty(CFG_KEY, CFG_DEFAULT_VALUE);
//    }

    @Test
    public void testCreateTempFile() throws Exception {
        try {
            ImageAPI img = new ImageAPI();
//            Assert.fail("Something wrong :(");
        } catch (Exception exception) {
            Assert.assertNotEquals("", exception.getMessage());
        }
    }

    public Mat createNoiseImage() {
        int width = 300, height = 300;
        Mat mat = new Mat(new Size(width, height), CV_8UC3, new Scalar(0, 0, 0)); // создаем трехканальное изображение
        Core.randn(mat, 20, 50);
        Core.add(mat, mat, mat);
        return mat;
    }

    @Test
    public void nullifyImageChannelTest() throws  Exception {
        try {
            ImageAPI img = new ImageAPI();
            logger.debug("Instance created successfully");
            Mat defaultMat = createNoiseImage();
//            img.showImage(defaultMat);

            Mat removedBlueChannelImg = img.nullifyImageChannel(defaultMat, 0);
//            img.showImage(removedBlueChannelImg);

            Mat removedGreenChannelImg = img.nullifyImageChannel(removedBlueChannelImg, 1);
//            img.showImage(removedGreenChannelImg);

            Mat removedRedChannelImg = img.nullifyImageChannel(removedBlueChannelImg, 2);
//            img.showImage(removedRedChannelImg);


            img.saveImageOnDisk(removedRedChannelImg,
                    ConfigurationUtil.getConfigurationEntry(PATH_TO_SAVED_IMAGES),
                    "test3",
                    ".png");

        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}