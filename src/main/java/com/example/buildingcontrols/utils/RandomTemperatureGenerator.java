package com.example.buildingcontrols.utils;

import java.util.Random;

public class RandomTemperatureGenerator {
    public static double generate(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }
}
