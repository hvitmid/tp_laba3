module org.example.tp_laba3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.tp_laba3 to javafx.fxml;
    exports org.example.tp_laba3;
}