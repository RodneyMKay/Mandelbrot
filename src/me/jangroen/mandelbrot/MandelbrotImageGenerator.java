package me.jangroen.mandelbrot;

import java.awt.image.BufferedImage;
import java.util.Queue;
import java.util.concurrent.*;

public final class MandelbrotImageGenerator {
    private static final int MIN_QUEUE_SIZE = 128;
    private static final int IMAGES_PER_BADGE = 64;

    private final Queue<Future<BufferedImage>> imageQueue;
    private final MandelbrotColorResolution colorResolution;

    private MandelbrotNumber scale;
    private final MandelbrotNumber scaleModifier;
    private final MandelbrotNumber xPos;
    private final MandelbrotNumber yPos;

    private final ExecutorService executorService;
    private final Object notifier = new Object();

    public MandelbrotImageGenerator(MandelbrotColorResolution colorResolution,
                                    MandelbrotNumber scale, MandelbrotNumber scaleModifier,
                                    MandelbrotNumber xPos, MandelbrotNumber yPos) {
        this.imageQueue = new ConcurrentLinkedQueue<>();

        this.colorResolution = colorResolution;

        this.scale = scale.divide(scaleModifier);
        this.scaleModifier = scaleModifier;
        this.xPos = xPos;
        this.yPos = yPos;

        this.executorService = Executors.newWorkStealingPool();
    }

    public BufferedImage getNextImage() {
        Future<BufferedImage> imageFuture = imageQueue.poll();

        try {
            while (imageFuture == null) {
                synchronized (notifier) {
                    notifier.wait();
                }

                imageFuture = imageQueue.poll();
            }

            return imageFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new Error(e);
        }
    }

    public void startGenerator() {
        new Thread(() -> {
            while (true) {
                if (imageQueue.size() < MIN_QUEUE_SIZE) {
                    for (int i = 0; i < IMAGES_PER_BADGE; i++) {
                        generateNewImage();
                    }
                }
            }
        }).start();
    }

    private void generateNewImage() {
        imageQueue.offer(this.executorService.submit(() -> newImage().calculateImage()));

        synchronized (notifier) {
            notifier.notify();
        }
    }

    private MandelbrotImage newImage() {
        this.scale = scale.multiply(scaleModifier);
        return new MandelbrotImage(800, 800, colorResolution, scale, xPos, yPos);
    }
}
