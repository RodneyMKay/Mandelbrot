package me.jangroen.mandelbrot;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class MandelbrotPane extends JPanel {
    private MandelbrotImageGenerator generator;
    private long millisLastFrame;

    public MandelbrotPane(MandelbrotImageGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void paint(Graphics g) {
        long timeLeft = (millisLastFrame + 1000 / 30) - System.currentTimeMillis();

        try {
            TimeUnit.MILLISECONDS.sleep(timeLeft);
        } catch (InterruptedException e) {
            throw new Error(e);
        }

        g.drawImage(generator.getNextImage(), 0, 0, null);
        millisLastFrame = System.currentTimeMillis();
        SwingUtilities.invokeLater(this::repaint);
    }
}
