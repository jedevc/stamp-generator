import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MultiShape implements Shape {
    private final List<Shape> shapes;

    public MultiShape(List<Shape> shapes) {
        this.shapes = new ArrayList<>(shapes);
    }

    public MultiShape(Shape... shapes) {
        this(Arrays.asList(shapes));
    }

    public Shape transform(double dx, double dy) {
        List<Shape> newShapes = shapes.stream()
            .map(shape -> shape.transform(dx, dy))
            .collect(Collectors.toList());
        return new MultiShape(newShapes);
    }

    public Shape scale(double q) {
        List<Shape> newShapes = shapes.stream()
            .map(shape -> shape.scale(q))
            .collect(Collectors.toList());
        return new MultiShape(newShapes);
    }

    public Shape flip() {
        List<Shape> newShapes = shapes.stream()
            .map(shape -> shape.flip())
            .collect(Collectors.toList());
        return new MultiShape(newShapes);
    }

    public Shape quickRotate(double boxSize) {
        List<Shape> newShapes = shapes.stream()
            .map(shape -> shape.quickRotate(boxSize))
            .collect(Collectors.toList());
        return new MultiShape(newShapes);
    }

    public void draw(Renderer target) {
        for (Shape shape : shapes) {
            shape.draw(target);
        }
    }
}
