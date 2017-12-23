public interface Shape {
    public Shape transform(double dx, double dy);
    public Shape scale(double q);
    public Shape quickRotate(double boxSize);

    public void draw(Renderer target);
}
