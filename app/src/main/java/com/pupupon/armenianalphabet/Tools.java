package com.pupupon.armenianalphabet;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Handler;

import java.io.IOException;
import java.util.Random;

class Tools {

    public static final String STRING = "string";
    public static final String RAW = "raw";


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
    static int randInt(int min, int max, int[] exclude) {
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

    static String[] randLetters(int min, int max) {
        final String letter = getFileResourceName();
        String[] letters = {"","","",""};
        int[] exclude = {-1,-1,-1};
        int a = randInt(min,max, exclude);
        exclude[0] = a;
        int b = randInt(min,max, exclude);
        exclude[1] = b;
        int c = randInt(min,max, exclude);
        exclude[2] = c;
        int d = randInt(min,max, exclude);
        letters[0] = letter + a;
        letters[1] = letter + b;
        letters[2] = letter + c;
        letters[3] = letter + d;
        return letters;
    }

    static String getFileResourceName() {
        return (Storage.getEasternArmenian() ? "" : "w") + "letter";
    }

    static void playSound(Context context, int resource){
        final MediaPlayer m = MediaPlayer.create(context, resource);
        m.start();
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                m.release();
            }
        }, 1000);
    }

    public static void playSoundFromAsset(Context context, String fullPath){
        final MediaPlayer m = new MediaPlayer();
        try {
            AssetFileDescriptor descriptor = context.getAssets().openFd(fullPath);
            m.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            m.prepare();
            m.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                m.release();
            }
        }, 1000);
    }

    static Typeface setFont(Context context){
        Typeface droidSansArmenian = Typeface.createFromAsset(context.getAssets(), "fonts/DroidSansArmenian.ttf");
        Typeface sylfaen = Typeface.createFromAsset(context.getAssets(), "fonts/Sylfaen.ttf");

        return sylfaen;
    }
}
