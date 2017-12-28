import java.awt.Color;

public class ColoredShape implements Shape {
    private final Shape shape;
    private final Color color;

    public ColoredShape(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public Shape transform(double dx, double dy) {
        return new ColoredShape(shape.transform(dx, dy), color);
    }

    public Shape scale(double q) {
        return new ColoredShape(shape.scale(q), color);
    }

    public Shape flip() {
        return new ColoredShape(shape.flip(), color);
    }

    public Shape quickRotate(double boxSize) {
        return new ColoredShape(shape.quickRotate(boxSize), color);
    }

    public void draw(Renderer target) {
        Color oldColor = target.getColor();
        target.setColor(color);

        shape.draw(target);

        target.setColor(oldColor);
    }
}
