package me.jangroen.mandelbrot;

public final class MandelbrotNumber implements Comparable<MandelbrotNumber> {
    public static final MandelbrotNumber MINUS_ONE = new MandelbrotNumber(-1);
    public static final MandelbrotNumber ZERO = new MandelbrotNumber(0);
    public static final MandelbrotNumber ONE = new MandelbrotNumber(1);
    public static final MandelbrotNumber TWO = new MandelbrotNumber(2);
    public static final MandelbrotNumber THREE = new MandelbrotNumber(3);
    public static final MandelbrotNumber FOUR = new MandelbrotNumber(4);
    public static final MandelbrotNumber FIVE = new MandelbrotNumber(5);
    public static final MandelbrotNumber SIX = new MandelbrotNumber(6);
    public static final MandelbrotNumber SEVEN = new MandelbrotNumber(7);
    public static final MandelbrotNumber EIGHT = new MandelbrotNumber(8);
    public static final MandelbrotNumber NINE = new MandelbrotNumber(9);
    public static final MandelbrotNumber TEN = new MandelbrotNumber(10);

    private final double value;

    public MandelbrotNumber(double number) {
        this.value = number;
    }

    public MandelbrotNumber add(MandelbrotNumber number) {
        return new MandelbrotNumber(this.value + number.value);
    }

    public MandelbrotNumber subtract(MandelbrotNumber number) {
        return new MandelbrotNumber(this.value - number.value);
    }

    public MandelbrotNumber multiply(MandelbrotNumber number) {
        return new MandelbrotNumber(this.value * number.value);
    }

    public MandelbrotNumber divide(MandelbrotNumber number) {
        return new MandelbrotNumber(this.value / number.value);
    }

    @Override
    public int compareTo(MandelbrotNumber number) {
        return (int) (this.value - number.value);
    }
}
