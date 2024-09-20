package model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyRectangle extends MyShape{

    double length;
    double width;
    public MyRectangle(Color color, double length, double width) {
        // calling Shape constructor
        super(color);
        this.length = length;
        this.width = width;  }

    @Override
    double area() {
        return length*width;   }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(0, 0, length, width);
    }

    @Override
    public String toString() {
        return "Цвет прямоугольника: " + super.color +  "и площадь: " + area();
    }

}