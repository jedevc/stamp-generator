public interface Shape {
    public Shape transform(double dx, double dy);
    public Shape scale(double q);
    public Shape flip();
    public Shape quickRotate(double boxSize);

    public void render(Renderer target);
}
