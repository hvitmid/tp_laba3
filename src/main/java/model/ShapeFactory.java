package model;

import javafx.scene.paint.Color;

public class ShapeFactory {

    public MyShape createShape(int sides, Color color, double x, double y, double height, double width) {

        if (sides == 1) {
            return new MyLine(color, x, y, height, width);

        } else if (sides == 0) {

          return new MyCircle(color, x, y, height, width);

        } else if (sides == 4) {

            return new MyRectangle(color, x, y, height, width);
        }
        else return null;
    }
}
