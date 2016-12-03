package com.pupupon.armenianalphabetquiz;

import java.util.Random;

public class Tools {

    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max, int[] exclude) {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = 0;
        for (int i : exclude) {
            randomNum = rand.nextInt((max - min) + 1) + min;
            while (randomNum == i) {
                randomNum = rand.nextInt((max - min) + 1) + min;
            }
            
        }

        return randomNum;
    }
    public static int randInt(int min, int max) {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static String[] randLetters(int min, int max) {
        String[] letters = {"","","",""};
        int[] exclude = {0,0,0};
        int a = randInt(min,max, exclude);
        exclude[0] = a;
        int b = randInt(min,max, exclude);
        exclude[1] = b;
        int c = randInt(min,max, exclude);
        exclude[2] = c;
        int d = randInt(min,max, exclude);
        letters[0] = "letter" + a;
        letters[1] = "letter" + b;
        letters[2] = "letter" + c;
        letters[3] = "letter" + d;
        return letters;
    }

}
