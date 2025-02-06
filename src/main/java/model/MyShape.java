package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//абстрактный класс, из которого наследуются все остальные.
//в нём должны быть только методы, которые необходимы для реализации.

abstract public class MyShape implements Cloneable{
    public Color color;
    protected double x, y, width, height ;

    public MyShape(double x, double y) {
        this.x = x;
        this.y = y;
//        this.height = 5;
//        this.width = 5;
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
        this.height = newheight;
        this.width = newwidth;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();   }
        return clone;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}