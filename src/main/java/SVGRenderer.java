import java.util.List;
import java.awt.Color;

import java.io.PrintWriter;

public class SVGRenderer implements Renderer {
    private final double size;
    private Color color;
    
    private PrintWriter writer;

    public SVGRenderer(double size, PrintWriter writer) {
        this.size = size;
        color = null;

        this.writer = writer;
    }

    public SVGRenderer(double size) {
        this(size, new PrintWriter(System.out, true));
    }

    public void start() {
        writer.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%s\" height=\"%s\" >\n", size, size);
    }

    public void end() {
        writer.println("</svg>");
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private String getColorString() {
        if (color == null) {
            return "rgb(0, 0, 0)";  // black
        } else {
            return String.format("rgb(%s, %s, %s)",
                    color.getRed(), color.getBlue(), color.getGreen());
        }
    }

    public void drawPolygon(List<Point> points) {
        StringBuilder pointString = new StringBuilder();
        for (Point point : points) {
            pointString.append(point.x * size);
            pointString.append(',');
            pointString.append(point.y * size);
            pointString.append(' ');
        }
        pointString.deleteCharAt(pointString.length() - 1);

        writer.format("<polygon points=\"%s\" fill=\"%s\" />\n",
                pointString, getColorString());
    }

    public void drawCircle(Point position, double radius) {
        writer.format("<circle cx=\"%s\" cy=\"%s\" r=\"%s\" fill=\"%s\" />\n", 
                position.x * size, position.y * size,
                radius * size, getColorString());
    }
}