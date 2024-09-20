package model;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

abstract class MyShape {
    //параметры фигуры - приватные поля
    protected Color color;
    protected double x, y;
    protected Canvas canvas;
    protected GraphicsContext graphicsContext;

    // объявление абстрактных методов
    abstract double area();

    abstract void draw( GraphicsContext gr);
    // конструктор

    public MyShape(Color color) {
        System.out.println("Вызван конструктор формы");
        this.color = color;
    }

    // реализация методов
    public void setColor(Color color) {
        this.color=color;
    }

}