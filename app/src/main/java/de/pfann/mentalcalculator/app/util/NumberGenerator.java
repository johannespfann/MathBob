package de.pfann.mentalcalculator.app.util;

/**
 * Created by jopfann on 25.08.14.
 */
public class NumberGenerator {

    public static int getRandomInteger(int aLow, int aHigh){
        return (int) (Math.random() * (aHigh - aLow) + aLow);
    }

    // Rundet auf zwei Nachkommastellen
    public static double getRandomDouble(int low, int high){
        return (Math.round(100.0 * (double) (Math.random() * (high - low) + low)) / 100.0); // Ergebnis: 1.23
    }
}
