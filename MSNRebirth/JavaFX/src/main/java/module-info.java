module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.selesnya.upv.Javafx to javafx.fxml;
    exports com.selesnya.upv.Javafx;
}