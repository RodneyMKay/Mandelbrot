package me.jangroen.mandelbrot;

import javax.swing.*;
import java.awt.*;

public class Mandelbrot extends JFrame {
    private MandelbrotColorResolution colorResolution;
    private MandelbrotImageGenerator imageGenerator;
    private MandelbrotPane pane;

    public Mandelbrot() {
        MandelbrotNumber scale = new MandelbrotNumber(4);
        MandelbrotNumber scaleModifier = new MandelbrotNumber(1.04);
        MandelbrotNumber xPos = new MandelbrotNumber(-0.743643887037158704752191506114774);
        MandelbrotNumber yPos = new MandelbrotNumber(0.131825904205311970493132056385139);

        this.colorResolution = new MandelbrotColorResolution(512);
        this.imageGenerator = new MandelbrotImageGenerator(colorResolution, scale, scaleModifier, xPos, yPos);

        this.imageGenerator.startGenerator();

        pane = new MandelbrotPane(imageGenerator);
        GridBagConstraints c = new GridBagConstraints();
        setLayout(new GridBagLayout());

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        add(pane, c);

        setBounds(0, 0, 800, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Mandelbrot().setVisible(true));
    }
}
