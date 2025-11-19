module it.cineca.hr.desktop_application {
   requires javafx.controls;
   requires javafx.fxml;
   requires java.desktop;

    opens it.cineca.hr.desktop_application to javafx.graphics;
   opens it.cineca.hr.desktop_application.filter_app to javafx.graphics;
   opens it.cineca.hr.desktop_application.button_example to javafx.graphics;
   opens it.cineca.hr.desktop_application.hello_world_basic to javafx.graphics;

}