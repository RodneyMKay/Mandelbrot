package me.jangroen.mandelbrot;

import java.awt.image.BufferedImage;

public final class MandelbrotImage {
    private final BufferedImage image;

    private final MandelbrotNumber xFactor;
    private final MandelbrotNumber yFactor;
    private final MandelbrotNumber xOffset;
    private final MandelbrotNumber yOffset;

    private final MandelbrotColorResolution colorScale;

    public MandelbrotImage(int width, int height, MandelbrotColorResolution colorScale, MandelbrotNumber scale, MandelbrotNumber xPos, MandelbrotNumber yPos) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        this.colorScale = colorScale;

        this.xFactor = MandelbrotNumber.SIX.divide(scale.multiply(new MandelbrotNumber(width)));
        this.yFactor = MandelbrotNumber.SIX.divide(scale.multiply(new MandelbrotNumber(height)));
        this.xOffset = new MandelbrotNumber(-width).multiply(xFactor).divide(MandelbrotNumber.TWO).add(xPos);
        this.yOffset = new MandelbrotNumber(-height).multiply(yFactor).divide(MandelbrotNumber.TWO).add(yPos);
    }

    public BufferedImage calculateImage() {
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                calculatePixel(x, y);
            }
        }

        return image;
    }

    private void calculatePixel(int px, int py) {
        MandelbrotNumber offsetX = new MandelbrotNumber(px).multiply(xFactor).add(xOffset);
        MandelbrotNumber offsetY = new MandelbrotNumber(py).multiply(yFactor).add(yOffset);

        MandelbrotNumber x;
        MandelbrotNumber y;

        MandelbrotNumber realSquare = MandelbrotNumber.ZERO;
        MandelbrotNumber imaginarySquare = MandelbrotNumber.ZERO;
        MandelbrotNumber doubleProduct = MandelbrotNumber.ZERO;

        int iteration = 0;
        int maxIterations = colorScale.getMaxIterations();

        while (realSquare.add(imaginarySquare).compareTo(MandelbrotNumber.FOUR) <= 0 && iteration < maxIterations) {
            x = realSquare.subtract(imaginarySquare).add(offsetX);
            y = doubleProduct.add(offsetY);

            realSquare = x.multiply(x);
            imaginarySquare = y.multiply(y);

            doubleProduct = MandelbrotNumber.TWO.multiply(x).multiply(y);

            iteration++;
        }

        image.setRGB(px, py, colorScale.getRGB(iteration));
    }
}
