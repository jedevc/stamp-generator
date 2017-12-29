public class Rectangle implements Shape {
    public final Point position;
    public final double width;
    public final double height;

    public Rectangle(Point position, double width, double height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public Shape transform(double dx, double dy) {
        return new Rectangle(position.transform(dx, dy), width, height);
    }

    public Shape scale(double q) {
        return new Rectangle(position.scale(q), width * q, height * q);
    }

    public Shape flip() {
        return new Rectangle(position.flip(), height, width);
    }

    public Shape quickRotate(double boxSize) {
        return new Rectangle(position.transform(0, height).quickRotate(boxSize), height, width);
    }

    public void draw(Renderer target) {
        target.drawRectangle(position, width, height);
    }
}
