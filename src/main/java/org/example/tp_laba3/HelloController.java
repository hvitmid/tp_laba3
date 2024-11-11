package org.example.tp_laba3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import model.*;
import java.awt.geom.Point2D;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public javafx.scene.control.TextField text_choose_shape;
    @FXML
    private Label welcomeText;
    public Canvas canvas;
    MyShape shape = null;
    ShapeFactory shapeFactory = new ShapeFactory();
    public GraphicsContext gc;
    double x;
    double y;
    double height=200;
    double width=200;

    private MemoSelect memoSelect = new MemoSelect();
    private Memento prevMemento = null;
    private Memento temp = null;

    private double dragOffsetX = 0.0;
    private double dragOffsetY = 0.0;
    private boolean isDragging = false;

    @FXML
    ColorPicker picker = new ColorPicker();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
    }

    @FXML
    protected void onColorButtonClick() {
        if(shape!=null){
            shape.setColor(picker.getValue());
            shape.draw(gc);
            newMemento();
        }
    }

    public void onMouse(MouseEvent mouseEvent) {    //рисование фигуры
        x = mouseEvent.getX();
        y = mouseEvent.getY();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight() );

        shape = shapeFactory.createShape(Integer.parseInt(text_choose_shape.getText()),picker.getValue(), x, y , height, width);
        gc = canvas.getGraphicsContext2D();
        shape.draw(gc);

        newMemento();
    }

    private double startX, startY, startWidth, startHeight;
    private boolean isResizing = false;

    @FXML
    public void onMouseDragged(MouseEvent mouseEvent) {     //начало перемещения фигуры
        if (shape.contains(x, y)) {
            // Начало перемещения фигуры
            dragOffsetX = this.x - shape.getHeight();
            dragOffsetY= this.y - shape.getWidth();     //куда передавать конечные результаты? конечные х у, то есть height width
            shape.setPosition(dragOffsetX, dragOffsetY);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            shape.draw(gc);
        } else {
            // Начало растяжения фигуры
            isResizing = true;
            startX = shape.getX();
            startY = shape.getY();
            startHeight = shape.getHeight();
            startWidth = shape.getWidth();
        }
        newMemento();
    }

    public void onMouseReleased(MouseEvent mouseEvent) {    //конец перемещения фигуры
       if (isResizing) {
            // Завершение растяжения фигуры
            isResizing = false;
           dragOffsetX = this.x - shape.getHeight();
           dragOffsetY= this.y - shape.getWidth();     //куда передавать конечные результаты? конечные х у, то есть height width
           shape.setPosition(dragOffsetX, dragOffsetY);
           gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
           shape.draw(gc);
        }
    }

    public void newMemento(){
        temp = new Memento(shape);
        memoSelect.push(temp);
    }
}