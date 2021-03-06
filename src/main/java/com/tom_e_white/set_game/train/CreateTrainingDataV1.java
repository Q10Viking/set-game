package com.tom_e_white.set_game.train;

import boofcv.io.image.UtilImageIO;
import com.tom_e_white.set_game.preprocess.CreateTrainingSetV1;
import com.tom_e_white.set_game.train.FeatureFinder;
import com.tom_e_white.set_game.train.FindCardColourFeatures;
import com.tom_e_white.set_game.train.FindCardShadingFeatures;
import com.tom_e_white.set_game.train.FindCardShapeFeatures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Take the images created by {@link CreateTrainingSetV1} and extract useful features from them.
 * The resulting data can be used to train a model for predicting the shapes on a card.
 */
public class CreateTrainingDataV1 {
    public static void main(String[] args) throws IOException {
        FeatureFinder[] finders = new FeatureFinder[] {
                new FindCardColourFeatures(),
                new FindCardShadingFeatures(),
                new FindCardShapeFeatures()
        };
        for (FeatureFinder finder : finders) {
            List<String> summaries = new ArrayList<>();
            for (File file : new File("data/train-out").listFiles((dir, name) -> name.matches(".*\\.jpg"))) {
                BufferedImage image = UtilImageIO.loadImage(file.getAbsolutePath());
                double[] features = finder.find(image, false);
                if (features != null) {
                    summaries.add(finder.getSummaryLine(file.getAbsolutePath(), features));
                }
            }
            Path p = Paths.get("data/train-out-" + finder.getFileSuffix());
            Files.write(p, summaries);
        }
    }

}
