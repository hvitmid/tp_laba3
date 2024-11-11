package model;

import javafx.scene.paint.Color;

public class Memento {
    private MyShape shape;
    private double x;
    private double y;
    private double height;
    private double width;
    private Color color;

    public Memento(MyShape state) {
        this.shape = state;
        this.x = this.shape.getX();
        this.y = this.shape.getY();
        this.color = this.shape.getColor();
    }

    public MyShape getState() {
        this.shape.setPosition(this.height, this.width);
        this.shape.setColor(this.color);
        return this.shape;
    }
}
