package org.example.tp_laba3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
   //создание холста и граф.контекста
    public TextField text_choose_shape;
    @FXML
    public Canvas canvas;
    public GraphicsContext gc;
    //public ListView listview;

    //выборщ цвета
    @FXML
    ColorPicker picker = new ColorPicker();
    //ListView
   // ListView listView = new ListView();

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

    @FXML
    private ListView<MyShape> listView=null;
    ObservableList<MyShape> items;

    //инициализация, получение граф.контекста
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        items = FXCollections.observableArrayList();
        //GraphicsContext gc = canvas.getGraphicsContext2D();
        // Обработка событий мыши
        canvas.setOnMousePressed(event -> onMouse(event, gc));
        canvas.setOnMouseDragged(event -> handleMouseDragged(event, gc));
        canvas.setOnMouseReleased(event -> handleMouseReleased(gc));



        int[] shapeTypes = {1, 2, 3}; // 1 - Line, 2 - Circle, 3 - Rectangle

        for (int shapeType : shapeTypes) {
            items.add(shapeFactory.createShape(shapeType, 20, 20));
        }

        // Установка элементов в ListView
        listView.setItems(items);

        listView.setCellFactory(new Callback<ListView<MyShape>, ListCell<MyShape>>() {
            @Override
            public ListCell<MyShape> call(ListView<MyShape> list) {
                return new ShapeCell();
            }
        });

        //попытки сделать прототип и хэш-таблицу

//        MyCircle myCircle = new MyCircle(picker.getValue(), x, y, height, width);
//        MyRectangle myrectangle = new MyRectangle(picker.getValue(), x, y, height, width);
//        MyLine myLine = new MyLine(picker.getValue(), x, y, height, width);
//
//        shape = shapeFactory.createShape(Integer.parseInt(text_choose_shape.getText()),picker.getValue(), x, y , height, width);

//        ObservableList<Object> items = FXCollections.observableArrayList(myCircle, myrectangle, myLine);
//        listview.setItems(items);
//
//        //ВОТ ЭТО
//        listview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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
        if (Integer.parseInt(text_choose_shape.getText())!=0){
            x = mouseEvent.getX();
            y = mouseEvent.getY();
            currentWidth = 0;
            currentHeight = 0;
            drawing = true;

            int index = listView.getSelectionModel().getSelectedIndex(); //получение индекса выбора из списка
            MyShape shape = (MyShape) items.get(index).clone();// создание копии фигуры
            shape.setColor(picker.getValue());// установка цвета заполнения фигуры по значению элемента управления colorPicker
            shape.setX(x);
            shape.setY(y);
            shape.draw(gc);// рисование копии фигуры в точке, полученной из события MouseEvent x

            this.gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight() );

            shape.setColor(picker.getValue());

            shape = shapeFactory.createShape(Integer.parseInt(text_choose_shape.getText()),x, y );
            this.gc = canvas.getGraphicsContext2D();
            shape.draw(this.gc);
            newMemento();
        }
        drawListShape(mouseEvent);

    }

//    public void onMouse(MouseEvent mouseEvent, GraphicsContext gc) {
//        x = mouseEvent.getX();
//        y = mouseEvent.getY();
//        currentWidth = 0;
//        currentHeight = 0;
//        drawing = true;
//        this.gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight() );
//        shape = shapeFactory.createShape(Integer.parseInt(text_choose_shape.getText()),picker.getValue(), x, y , height, width);
//        this.gc = canvas.getGraphicsContext2D();
//        shape.draw(this.gc);
//
//        //И ВОТ ЭТО выглядят одинаковыми, надо что-то сделать
//        int index = listView.getSelectionModel().getSelectedIndex(); //получение индекса выбора из списка
//        MyShape shape = (MyShape) content.get(index).clone();// создание копии фигуры
//        shape.setColor(picker.getValue());// установка цвета заполнения фигуры по значению элемента управления colorPicker
//        shape.draw(gc, mouseEvent.getX(), mouseEvent.getY());// рисование копии фигуры в точке, полученной из события MouseEvent x
//
//        newMemento();
//    }

    public void drawListShape(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        int index = listView.getSelectionModel().getSelectedIndex(); //получение индекса выбора из списка
        MyShape shape = (MyShape) items.get(index).clone();// создание копии фигуры
        shape.setColor(picker.getValue());
        shape.setX(x);
        shape.setY(y);
        shape.draw(gc);// рисование копии фигуры в точке, полученной из события MouseEvent x
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
            shape.setPosition(currentWidth, currentHeight);
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            shape.draw(gc);
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

    public void onListview(MouseEvent mouseEvent) {

    }
}