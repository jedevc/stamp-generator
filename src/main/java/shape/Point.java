package shape;

import renderer.Renderer;

public class Point {
    public final double x;
    public final double y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point transform(double dx, double dy) {
        return new Point(x + dx, y + dy);
    }

    public Point scale(double q) {
        return new Point(x * q, y * q);
    }

    public Point flip() {
        return new Point(y, x);
    }

    public Point quickRotate(double boxSize) {
        /*
         * This is a tricky little technique that rotates a point 90 degrees
         * inside a square, assuming that the top-left corner is the origin.
         *
         * This technique is probably not so portable or useful outside of this
         * program, but it does work fairly nicely here as it is very simple,
         * and also quite efficient.
         */
        return new Point(boxSize - y, x);
    }
}
