import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class Identicon {
    public final int size;
    private List<Shape> shapes;

    public Identicon(int size) {
        this.size = size;
        shapes = new ArrayList<Shape>(size * size);
    }

    public void generate() {
        Random rand = new Random();
        for (int i = 0; i < size / 2; i++) {
            for (int j = 0; j < size / 2; j++) {
                // select random shape
                Shape shape = shapeChoices[rand.nextInt(shapeChoices.length)];

                // rotate to random position
                for (int r = 0; r < rand.nextInt(4); r++) {
                    shape = shape.quickRotate(1);
                }

                // scale shape and move to correct grid position
                shape = shape.scale(1.0 / size);
                shape = shape.transform((double) i / size, (double) j / size);

                // color the shape
                Color color = new Color(rand.nextInt(255),
                        rand.nextInt(255), rand.nextInt(255));
                shape = new ColoredShape(shape, color);

                // add shape and it's rotations
                shapes.add(shape);
                for (int k = 0; k < 3; k++) {
                    shape = shape.quickRotate(1);
                    shapes.add(shape);
                }
            }
        }
    }

    public void render(Renderer target) {
        target.start();
        for (Shape shape : shapes) {
            shape.draw(target);
        }
        target.end();
    }

    private static final Shape[] shapeChoices = new Shape[]{
        // corner square
        new Polygon(new Point(0, 0),
                    new Point(0.5, 0),
                    new Point(0.5, 0.5),
                    new Point(0, 0.5)),

        // center square
        new Polygon(new Point(0.25, 0.25),
                    new Point(0.75, 0.25),
                    new Point(0.75, 0.75),
                    new Point(0.25, 0.75)),

        // rect
        new Polygon(new Point(0, 0),
                    new Point(1, 0),
                    new Point(1, 0.5),
                    new Point(0, 0.5)),

        // skewed rect
        new Polygon(new Point(0, 0),
                    new Point(1, 0.5),
                    new Point(1, 1),
                    new Point(0, 0.5)),

        // diamond
        new Polygon(new Point(0.5, 0.25),
                    new Point(0.75, 0.5),
                    new Point(0.5, 0.75),
                    new Point(0.25, 0.5)),

        // simple triangle
        new Polygon(new Point(0, 0),
                    new Point(0.5, 0),
                    new Point(0, 0.5)),

        // stretched triangles
        new Polygon(new Point(0, 0),
                    new Point(1, 0),
                    new Point(0, 0.5)),
        new Polygon(new Point(0, 0),
                    new Point(0.5, 0),
                    new Point(0, 1)),

        // arrow
        new Polygon(new Point(0, 0),
                    new Point(0.5, 0.5),
                    new Point(1, 0),
                    new Point(1, 0.5),
                    new Point(0.5, 1),
                    new Point(0, 0.5)),

        // multi-triangles
        new MultiShape(new Polygon(new Point(0, 0),
                                   new Point(0.5, 0),
                                   new Point(0.25, 0.5)),
                       new Polygon(new Point(0.5, 0),
                                   new Point(1, 0),
                                   new Point(0.75, 0.5)),
                       new Polygon(new Point(0.25, 0.5),
                                   new Point(0.75, 0.5),
                                   new Point(0.5, 1))),
        new MultiShape(new Polygon(new Point(0, 0),
                                   new Point(0.5, 0.25),
                                   new Point(0.25, 0.5)),
                       new Polygon(new Point(1, 0),
                                   new Point(0.75, 0.5),
                                   new Point(0.5, 0.25)),
                       new Polygon(new Point(1, 1),
                                   new Point(0.5, 0.75),
                                   new Point(0.75, 0.5)),
                       new Polygon(new Point(0, 1),
                                   new Point(0.25, 0.5),
                                   new Point(0.5, 0.75))),

        //circle
        new Circle(new Point(0.5, 0.5), 0.25)
    };
}
