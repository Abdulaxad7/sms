module com.sms.sms {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.sms.sms to javafx.fxml;
    exports com.sms.sms;
    exports com.sms.sms.achanges;
    exports com.sms.sms.kamron;
    exports com.sms.sms.Bobur;
    exports com.sms.sms.muhammadiso;

    opens com.sms.sms.achanges to javafx.fxml;
}