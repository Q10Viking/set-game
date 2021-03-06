package com.tom_e_white.set_game.predict;

import com.tom_e_white.set_game.model.Card;
import com.tom_e_white.set_game.preprocess.CardDetector;
import com.tom_e_white.set_game.preprocess.CardImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Use {@link CardPredictor} to predict each card in the test set.
 */
public class PredictCardsOnTestData {

    public static double predict(File testFile, CardPredictor cardPredictor) throws IOException, ParseException {
        CardDetector cardDetector = new CardDetector();
        List<CardImage> images = cardDetector.detect(testFile.getAbsolutePath(), false);
        List<String> testDescriptions = Files.lines(Paths.get(testFile.getAbsolutePath().replace(".jpg", ".txt"))).collect(Collectors.toList());

        int correct = 0;
        int total = 0;
        for (int i = 0; i < testDescriptions.size(); i++) {
            CardPrediction cardPrediction = cardPredictor.predict(images.get(i));
            Card predictedCard = cardPrediction.getCard();
            Card actualCard = new Card(testDescriptions.get(i));
            if (predictedCard.equals(actualCard)) {
                correct++;
            } else {
                System.out.println("Incorrect, predicted " + cardPrediction + " but was " + actualCard + " for card " + (i + 1));
            }
            total++;
        }
        System.out.println("Correct: " + correct);
        System.out.println("Total: " + total);
        double accuracy = ((double) correct)/total * 100;
        System.out.println("Accuracy: " + ((int) accuracy) + " percent");
        System.out.println("------------------------------------------");
        return accuracy;
    }

    public static void main(String[] args) throws Exception {
        predict(new File(args[0]), PredictCards.getCardPredictor(args.length > 1 ? args[1] : null));
    }
}
