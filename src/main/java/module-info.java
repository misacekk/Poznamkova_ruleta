module com.example.poznamkovaruleta {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.poznamkovaruleta to javafx.fxml;
    exports com.example.poznamkovaruleta;
}