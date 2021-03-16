package ru.sfedu.opencv.api;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Locale;

import org.opencv.imgcodecs.Imgcodecs;
import ru.sfedu.opencv.Constants;
import static ru.sfedu.opencv.utils.ConfigurationUtil.getConfigurationEntry;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import javax.swing.*;

public class ImageAPI {
    private  static final Logger logger = LogManager.getLogger(ImageAPI.class);

    public ImageAPI() throws Exception {
        logger.info("Checking OS.....");
        // init the API with curent os..
        Constants.OSType CurrentOSTypeName = getOperatingSystemType();
        logger.info("Current OS type name - " + CurrentOSTypeName);
        try {
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
        } catch (Exception e) {
            throw new Exception("OOPS, we have some problem...");
        }
    }

    public Constants.OSType getOperatingSystemType() {
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        logger.info("OS" + OS);
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


    public Mat loadImage(String absolutePath) {
        return Imgcodecs.imread(absolutePath);
    }

    public void showImage(Mat m) throws InterruptedException {
        int type = BufferedImage.TYPE_BYTE_GRAY;

        if ( m.channels() > 1 ) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }

        int bufferSize = m.channels()*m.cols()*m.rows();
        byte [] b = new byte[bufferSize];
        m.get(0,0,b);
        BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
        ImageIcon icon = new ImageIcon(image);

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(image.getWidth(null)+50, image.getHeight(null)+50);
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        Thread.sleep(2000);
    }

    // total - общее количество элементов массива
    // elemSize - размер элемента матрицы в байтах.
    public Mat nullifyImageChannel(Mat mat, int channel) {
        int totalBytes = (int) (mat.total() * mat.elemSize());
        byte[] buffer = new byte[totalBytes];
        mat.get(0, 0, buffer);

        for (int i = channel; i < totalBytes; i += mat.elemSize()) {
            buffer[i] = 0;
        }

        mat.put(0, 0, buffer);
        return mat;
    }

    public void saveImageOnDisk (Mat mat, String path, String fileName, String extension) {
        String fullName = path + fileName + extension;
        Imgcodecs.imwrite(fullName, mat);
    }
}
