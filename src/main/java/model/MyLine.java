package model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyLine extends MyShape{

    public double length;
    public double angle;

    public MyLine(Color color, double x, double y, double length, double angle) {
        super(color, x, y);
        this.length = length;
        this.angle = angle;
        }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(color);      //цвет линии меняется с помощью Stroke
        gc.strokeLine(x, y, length, angle);
    }
}