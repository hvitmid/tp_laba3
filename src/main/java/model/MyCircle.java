package model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyCircle extends MyShape{

    double length;
    double width;

    public MyCircle(Color color, double x, double y, double length, double width) {
        super(color, x, y);
        this.length = length;
        this.width = width;  }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x, y, length, width);
    }
    public boolean contains(double x, double y) {
        double a = length / 2;
        double b = width / 2;
        return ((x - this.x) * (x - this.x)) / (a * a) +
                ((y - this.y) * (y - this.y)) / (b * b) <= 1;
    }

}