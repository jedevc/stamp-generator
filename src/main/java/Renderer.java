import java.util.List;
import java.awt.Color;

public interface Renderer {
    public void start();
    public void end();

    public Color getColor();
    public void setColor(Color color);

    public void drawPolygon(List<Point> points);
    public void drawCircle(Point position, double radius);
}
