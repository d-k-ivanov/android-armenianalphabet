package com.pupupon.armenianalphabet;

import java.util.Random;

class Tools {

    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @param exclude list of excluded numbers in range.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max, int[] exclude) {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        // for each number in excluded list check if it isn't the same as random number.
        for (int i : exclude) {
            while (randomNum == i) {
                randomNum = rand.nextInt((max - min) + 1) + min;
            }
            
        }

        return randomNum;
    }
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
    /*public static int randInt(int min, int max) {
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }*/

    public static String[] randLetters(int min, int max) {
        String[] letters = {"","","",""};
        int[] exclude = {-1,-1,-1};
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
