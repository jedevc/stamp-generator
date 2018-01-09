package shape;

import renderer.Renderer;

public class Circle implements Shape {
    public final Point position;
    public final double radius;

    public Circle(Point position, double radius) {
        this.position = position;
        this.radius = radius;
    }

    public Shape transform(double dx, double dy) {
        return new Circle(position.transform(dx, dy), radius);
    }

    public Shape scale(double q) {
        return new Circle(position.scale(q), radius * q);
    }

    public Shape flip() {
        return new Circle(position.flip(), radius);
    }

    public Shape quickRotate(double boxSize) {
        return new Circle(position.quickRotate(boxSize), radius);
    }

    public void render(Renderer target) {
        target.drawCircle(position, radius);
    }
}
