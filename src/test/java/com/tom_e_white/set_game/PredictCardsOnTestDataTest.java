package com.tom_e_white.set_game;

import com.tom_e_white.set_game.predict.PredictCardsOnTestData;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class PredictCardsOnTestDataTest {
    @Test
    public void test() throws IOException, ParseException {
        double accuracy = PredictCardsOnTestData.predict(new File("data/20170106_205743.jpg"));
        assertEquals(60, accuracy, 1);
    }
}
