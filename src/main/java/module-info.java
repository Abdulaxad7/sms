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
    requires java.desktop;
    requires jbcrypt;
    requires java.persistence;
    requires static lombok;

    opens com.sms.sms to javafx.fxml;
    exports com.sms.sms;

    exports com.sms.sms.security;
    opens com.sms.sms.security to javafx.fxml;
    exports com.sms.sms.User;
    opens com.sms.sms.User to javafx.fxml;
    exports com.sms.sms.Admin;
}