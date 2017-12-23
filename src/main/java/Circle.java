public class Circle implements Shape {
    private final Point position;
    private final double radius;

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

    public Shape quickRotate(double boxSize) {
        return new Circle(position.quickRotate(boxSize), radius);
    }

    public void draw(Renderer target) {
        target.drawCircle(position, radius);
    }
}
