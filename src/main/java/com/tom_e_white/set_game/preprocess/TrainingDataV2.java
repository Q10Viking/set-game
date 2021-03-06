package com.tom_e_white.set_game.preprocess;

import java.io.File;

public interface TrainingDataV2 {
    File RAW_NEW_DIRECTORY = new File("data/train-v2/raw-new");
    File RAW_SORTED_DIRECTORY = new File("data/train-v2/raw-sorted");
    File RAW_LABELLED_DIRECTORY = new File("data/train-v2/raw-labelled");
    File LABELLED_DIRECTORY = new File("data/train-v2/labelled");
}
