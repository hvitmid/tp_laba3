package model;

import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ShapeFactory {
    private Map<Integer, BiFunction<Double, Double, MyShape>> shapeMap;

    public ShapeFactory() {
        shapeMap = new HashMap<>();
        shapeMap.put(1, (x, y) -> new MyLine( 20,10, 50, 45));
        shapeMap.put(2, (x, y) -> new MyRectangle(20,20,  50, 30));
        shapeMap.put(3, (x, y) -> new MyCircle(30,30, 30,30));
    }

    public MyShape createShape(int num, double x, double y) {
        BiFunction<Double, Double, MyShape> shapeCreator = shapeMap.get(num);
        if (shapeCreator != null) {
            return shapeCreator.apply(x, y);
        } else {
            return null;
        }
    }


//    public MyShape createShape(int sides, double x, double y, double height, double width) {
//
//        if (sides == 1) {
//            return new MyLine(x, y, height, width);
//
//        } else if (sides == 2) {
//
//          return new MyCircle(x, y, height, width);
//
//        } else if (sides == 3) {
//
//            return new MyRectangle(x, y, height, width);
//        }
//        else return null;
//    }
}