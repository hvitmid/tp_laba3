package model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyRectangle extends MyShape{

    double height;
    double width;

    public MyRectangle(double x, double y, double height, double width) {
        super(x, y);
        this.color = getColor();
        this.height = height;
        this.width = width;  }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, height, width);
    }
    public boolean contains(double x, double y) {
        return x >= this.x && x <= this.x + height &&
                y >= this.y && y <= this.y + width;
    }
}