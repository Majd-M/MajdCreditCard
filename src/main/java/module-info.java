module assignment.majdcreditcard {
    requires javafx.controls;
    requires javafx.fxml;


    opens assignment.majdcreditcard to javafx.fxml;
    exports assignment.majdcreditcard;
}