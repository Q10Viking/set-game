package com.tom_e_white.set_game;

import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CardDetectorTest {

    private CardDetector cardDetector = new CardDetector();

    @Test
    public void testNoCards() throws IOException {
        List<BufferedImage> images = cardDetector.scan("data/egg.jpg");
        assertTrue(images.isEmpty());
    }

    @Test
    public void testSingleCard() throws IOException {
        List<BufferedImage> images = cardDetector.scan("data/one-card-20161230_192626.jpg");
        assertEquals(1, images.size());
    }

    @Test
    public void testErrantBorder() throws IOException {
        // this image has a border with some artifacts that may be detected as quadrilaterals
        List<BufferedImage> images = cardDetector.scan("data/purple-1-20170101_124559.jpg");
        assertEquals(9, images.size());
    }

    @Test
    public void testRotated() throws IOException {
        // this image is rotated through 90 degrees
        List<BufferedImage> images = cardDetector.scan("data/green-2-rotated-20161231_114543.jpg");
        assertEquals(9, images.size());
    }

    @Test
    public void testTrainingImagesContainNineCards() {
        Arrays.stream(new File("data/train").listFiles((dir, name) -> name.matches(".*\\.jpg"))).forEach(
                file -> {
                    try {
                        System.out.println(file);
                        List<BufferedImage> images = cardDetector.scan(file.getAbsolutePath());
                        assertEquals(9, images.size());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }
}