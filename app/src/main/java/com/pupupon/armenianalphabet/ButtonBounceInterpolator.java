package com.pupupon.armenianalphabet;

class ButtonBounceInterpolator implements android.view.animation.Interpolator {
    private final double mAmplitude;
    private final double mFrequency;

    ButtonBounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time / mAmplitude) * Math.cos(mFrequency * time) + 1);
    }
}
