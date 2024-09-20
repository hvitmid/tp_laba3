package model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyCircle extends MyShape{

    double length;
    double width;
    public MyCircle(Color color, double length, double width) {
        // calling Shape constructor
        super(color);
        this.length = length;
        this.width = width;  }

    @Override
    double area() {
        return Math.PI*length*width;   }        //формула площади круга

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(0, 0, length, width);
    }

    @Override
    public String toString() {
        return "Цвет круга: " + super.color +  "и площадь: " + area();
    }

}