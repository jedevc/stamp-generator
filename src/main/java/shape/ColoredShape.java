package shape;

import java.awt.Color;

import renderer.Renderer;

public class ColoredShape implements Shape {
    public final Shape shape;
    public final Color color;

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

    public void render(Renderer target) {
        Color oldColor = target.getColor();
        target.setColor(color);

        shape.render(target);

        target.setColor(oldColor);
    }
}
