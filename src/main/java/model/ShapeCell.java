package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListCell;

public class ShapeCell extends ListCell<MyShape> {
    @Override
    public void updateItem(MyShape item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        } else {
            //нвоый холст для отрисовки фигур
            Canvas canvas = new Canvas(60, 60); //размер ячейки
            GraphicsContext gc = canvas.getGraphicsContext2D();

            //клон фигуры для отрисовки
            MyShape item1 = (MyShape) item.clone();
            item1.draw(gc); // Отрисовка фигуры на канвасе

            //холст как графическое представление ячейки
            setGraphic(canvas);
        }
    }
}
