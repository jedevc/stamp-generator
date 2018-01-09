package renderer;

import java.util.List;
import java.awt.Color;

import shape.Point;

public interface Renderer {
    public void start();
    public void end();

    public Color getColor();
    public void setColor(Color color);

    public void drawRectangle(Point position, double width, double height);
    public void drawPolygon(List<Point> points);
    public void drawCircle(Point position, double radius);
}
