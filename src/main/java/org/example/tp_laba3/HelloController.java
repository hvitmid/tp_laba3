package org.example.tp_laba3;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.MyRectangle;
import model.MyLine;
import model.MyCircle;

import java.awt.*;


public class HelloController {
    public javafx.scene.control.TextField text_choose_shape;
    @FXML
    private Label welcomeText;
    public Canvas canvas;

    public GraphicsContext gc;
    @FXML
    ColorPicker picker = new ColorPicker();

    @FXML
    protected void onButtonClick() {
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight() );

        try {
            String userAnswer = (text_choose_shape.getText());
            if (userAnswer.equals("прямоугольник")) {
                MyRectangle myrectangle = new MyRectangle(picker.getValue(), 100, 150);
                myrectangle.draw(gc);
                System.out.println(myrectangle.toString());
            }
            if (userAnswer.equals("круг")) {
                MyCircle mycircle=new MyCircle(picker.getValue(), 200, 200);
                mycircle.draw(gc);
                System.out.println(mycircle.toString());
            }
            if (userAnswer.equals("линия")) {
                MyLine myline=new MyLine(picker.getValue(), 100, 150);
                myline.draw(gc);
                System.out.println(myline.toString());
            }
            if (userAnswer.equals("диаграмма классов")) {
//                MyRectangle myrectangle=new MyRectangle(picker.getValue(), 100, 150);
//                myrectangle.draw(gc);
//                System.out.println(myrectangle.toString());
            }

        } catch (Exception e) {
            welcomeText.setText("Ошибка: Некорректное выражение");
        }

    }

    @FXML
    protected void onColorButtonClick() {
    }


}