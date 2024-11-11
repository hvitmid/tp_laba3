package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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


    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean contains(double x, double y) {   //содержится ли в shape х и у
        return false;
    }



    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setPosition(double newheight, double newwidth) {
        this.height=newheight;
        this.width=newwidth;
    }
}