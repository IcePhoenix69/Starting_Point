module it.cineca.hr.desktop_application {
   requires javafx.controls;
   requires javafx.fxml;
   requires java.desktop;

    opens it.cineca.hr.desktop_application to javafx.graphics;

}