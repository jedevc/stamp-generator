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
                Shape shape = generateShape(rand);

                // rotate to random position
                for (int r = 0; r < rand.nextInt(4); r++) {
                    shape = shape.quickRotate(1);
                }

                // optionally flip shape
                if (rand.nextBoolean()) {
                    shape = shape.flip();
                }

                // scale shape and move to correct grid position
                double xShift = (double) i / size;
                double yShift = (double) j / size;
                double scale = 1.0 / size;
                shape = shape.scale(scale);
                shape = shape.transform(xShift, yShift);

                // color the shape and optionally invert it
                Color fillColor = new Color(rand.nextInt(255),
                                            rand.nextInt(255),
                                            rand.nextInt(255));
                if (rand.nextBoolean()) {
                    Shape rect = new Rectangle(new Point(xShift, yShift), scale, scale);
                    rect = new ColoredShape(rect, fillColor);

                    shape = new ColoredShape(shape, new Color(255, 255, 255));
                    shape = new MultiShape(rect, shape);
                } else {
                    shape = new ColoredShape(shape, fillColor);
                }

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
            shape.render(target);
        }
        target.end();
    }

    private static Shape generateShape(Random rand) {
        int option = rand.nextInt(6);
        switch (option) {
            case 0: {
                // 4 quarters
                Shape base = halfByHalfs[rand.nextInt(halfByHalfs.length)];
                return new MultiShape(base,
                                      base.transform(0.5, 0),
                                      base.transform(0, 0.5),
                                      base.transform(0.5, 0.5));
            } case 1: {
                // 2 quarters
                Shape base = halfByHalfs[rand.nextInt(halfByHalfs.length)];
                return new MultiShape(base,
                                      base.transform(0.5, 0.5));
            } case 2: {
                // 2 halfs
                Shape base = halfByWholes[rand.nextInt(halfByWholes.length)];
                return new MultiShape(base,
                                      base.transform(0.5, 0));
            } case 3: {
                // 3 quarters
                Shape base = halfByHalfs[rand.nextInt(halfByWholes.length)];
                return new MultiShape(base,
                                      base.transform(0.5, 0),
                                      base.transform(0, 0.5));
            } case 4: {
                // quarter and 2 halfs
                Shape base1 = halfByWholes[rand.nextInt(halfByWholes.length)];
                Shape base2 = halfByHalfs[rand.nextInt(halfByHalfs.length)];
                return new MultiShape(base1,
                                      base2.transform(0.5, 0),
                                      base2.transform(0.5, 0.5));
            } case 5: {
                // whole
                Shape base = wholeByWholes[rand.nextInt(wholeByWholes.length)];
                return base;
            }
        }

        return null; // should not occur
    }

    private static final Shape[] halfByHalfs = new Shape[] {
        // right-angled triangle
        new Polygon(new Point(0, 0),
                    new Point(0.5, 0),
                    new Point(0, 0.5)),

        // equilateral triangle
        new Polygon(new Point(0.25, 0),
                    new Point(0.5, 0.5),
                    new Point(0, 0.5)),

        // diamond
        new Polygon(new Point(0.25, 0),
                    new Point(0.5, 0.25),
                    new Point(0.25, 0.5),
                    new Point(0, 0.25)),

        // circle
        new Circle(new Point(0.25, 0.25), 0.25),
    };

    private static final Shape[] halfByWholes = new Shape[]{
        // skewed triangles
        new Polygon(new Point(0, 0),
                    new Point(0.5, 0),
                    new Point(0, 1)),
        new Polygon(new Point(0, 0),
                    new Point(0.5, 0.5),
                    new Point(0, 1)),
    };

    private static final Shape[] wholeByWholes = new Shape[]{
        // arrow
        new Polygon(new Point(0, 0),
                    new Point(0.5, 0.5),
                    new Point(1, 0),
                    new Point(1, 0.5),
                    new Point(0.5, 1),
                    new Point(0, 0.5)),

        // diagonal kite
        new Polygon(new Point(0, 0),
                    new Point(0.5, 0),
                    new Point(1, 1),
                    new Point(0, 0.5)),

        // stripe
        new Polygon(new Point(0, 0),
                    new Point(0.5, 0),
                    new Point(1, 0.5),
                    new Point(1, 1),
                    new Point(0.5, 1),
                    new Point(0, 0.5)),

        // uneven stripe
        new MultiShape(new Polygon(new Point(0, 0),
                                   new Point(0.5, 0.5),
                                   new Point(0.25, 0.75),
                                   new Point(0, 0.5)),
                       new Polygon(new Point(0.75, 0.25),
                                   new Point(1, 0.5),
                                   new Point(1, 1),
                                   new Point(0.5, 0.5))),
    };
}
