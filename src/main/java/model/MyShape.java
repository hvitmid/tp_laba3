package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//абстрактный класс, из которого наследуются все остальные.
//в нём должны быть только методы, которые необходимы реализации.

abstract public class MyShape {
    public Color color;
    protected double x, y, width, height ;

    public MyShape(Color color, double x, double y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.height = 5;
        this.width = 5;
    }

    public abstract void draw( GraphicsContext gc);  // конструктор

    public Color getColor() {
        return this.color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public void setPosition(double newheight, double newwidth) {
        this.height=newheight;
        this.width=newwidth;
    }
}