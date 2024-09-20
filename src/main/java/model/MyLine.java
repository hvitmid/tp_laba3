package model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import static java.lang.Math.sqrt;

public class MyLine extends MyShape{

    double x;
    double y;
    //double width;
    public MyLine(Color color, double x, double y) {
        // calling Shape constructor
        super(color);
        this.x = x;
        this.y = y;
        }

    @Override
    double area() {
     return sqrt((x*x)+(y*y));   }

@Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);      //цвет линии не меняется
        //gc.setLineWidth(2.0);кргу
        gc.strokeLine(0, 0, 300, 150);
    }

    @Override
    public String toString() {
        return "Цвет линии: " + super.color + "и длина: " + area();
    }

}