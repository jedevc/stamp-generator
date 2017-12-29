import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Polygon implements Shape {
    public final List<Point> points;

    public Polygon(List<Point> points) {
        this.points = new ArrayList<>(points);
    }

    public Polygon(Point... points) {
        this(Arrays.asList(points));
    }

    public Shape transform(double dx, double dy) {
        List<Point> newPoints = points.stream()
            .map(point -> point.transform(dx, dy))
            .collect(Collectors.toList());
        return new Polygon(newPoints);
    }

    public Shape scale(double q) {
        List<Point> newPoints = points.stream()
            .map(point -> point.scale(q))
            .collect(Collectors.toList());
        return new Polygon(newPoints);
    }

    public Shape flip() {
        List<Point> newPoints = points.stream()
            .map(point -> point.flip())
            .collect(Collectors.toList());
        return new Polygon(newPoints);
    }

    public Shape quickRotate(double boxSize) {
        List<Point> newPoints = points.stream()
            .map(point -> point.quickRotate(boxSize))
            .collect(Collectors.toList());
        return new Polygon(newPoints);
    }

    public void draw(Renderer target) {
        target.drawPolygon(points);
    }
}
