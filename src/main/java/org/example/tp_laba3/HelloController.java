package org.example.tp_laba3;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import model.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
   //создание холста и граф.контекста
    public javafx.scene.control.TextField text_choose_shape;
    @FXML
    public Canvas canvas;
    public GraphicsContext gc;

    //выборщ цвета
    @FXML
    ColorPicker picker = new ColorPicker();

     //создание фигуры
    MyShape shape = null;
    ShapeFactory shapeFactory = new ShapeFactory();

    //координаты фигуры
    double x;
    double y;
    double height=200;
    double width=200;

    //запоминание фигуры
    private MemoSelect memoSelect = new MemoSelect();
    private Memento prevMemento = null;
    private Memento temp = null;

    //инициализация, получение граф.контекста
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // Обработка событий мыши
        canvas.setOnMousePressed(event -> onMouse(event, gc));
        canvas.setOnMouseDragged(event -> handleMouseDragged(event, gc));
        canvas.setOnMouseReleased(event -> handleMouseReleased(gc));
    }

    //нажатие на кнопку изменения цвета
    @FXML
    protected void onColorButtonClick() {
        if(shape!=null){
            shape.setColor(picker.getValue());
            shape.draw(gc);
            newMemento();
        }
    }

    private double startX = 0;
    private double startY = 0;
    private double currentWidth = 0;
    private double currentHeight = 0;
    private boolean drawing = false;

    //обработка рисования фигуры - щелчок 1
    public void onMouse(MouseEvent mouseEvent, GraphicsContext gc) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
        currentWidth = 0;
        currentHeight = 0;
        drawing = true;
        this.gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight() );
        shape = shapeFactory.createShape(Integer.parseInt(text_choose_shape.getText()),picker.getValue(), x, y , height, width);
        this.gc = canvas.getGraphicsContext2D();
        shape.draw(this.gc);
        newMemento();
    }

    //обработка начала перемещения
    private void handleMouseDragged(MouseEvent event, GraphicsContext gc) {
        if (drawing) {
            // рассчитываем ширину и высоту прямоугольника
            currentWidth = event.getX() - startX;
            currentHeight = event.getY() - startY;
            // очищаем Canvas и рисуем фигуру
            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
            double x = startX + (currentWidth < 0 ? currentWidth : 0);
            double y = startY + (currentHeight < 0 ? currentHeight : 0);
            shape = shapeFactory.createShape(Integer.parseInt(text_choose_shape.getText()),picker.getValue(), x, y , Math.abs(currentWidth), Math.abs(currentHeight));
            this.gc = canvas.getGraphicsContext2D();
            shape.draw(this.gc);
        }
    }

    //обработка конца перемещения
    private void handleMouseReleased(GraphicsContext gc) {
        drawing = false;
    }

    //запоминание фигуры
    public void newMemento(){
        temp = new Memento(shape);
        memoSelect.push(temp);
    }
}