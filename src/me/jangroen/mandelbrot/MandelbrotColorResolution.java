package me.jangroen.mandelbrot;

import java.awt.*;

public final class MandelbrotColorResolution {
    private int[] rgbValues;

    public MandelbrotColorResolution(int depth) {
        rgbValues = new int[depth];

        double scale = 512 / (double) rgbValues.length;

        for (int i = 0; i < rgbValues.length; i++) {
            int color = (int) (i * scale);

            int r = color < 256 ? color : 0;
            int g = color >= 128 && color < 384 ? color - 128 : 0;
            int b = color >= 256 ? color - 256 : 0;

            rgbValues[i] = new Color(r, g, b).getRGB();
        }
    }

    public int getMaxIterations() {
        return rgbValues.length - 1;
    }

    public int getRGB(int iterations) {
        return rgbValues[iterations];
    }
}
