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
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;
    requires jakarta.persistence;
    requires static lombok;
    requires org.slf4j;
    requires annotations;

    opens com.sms.sms to javafx.fxml;
    exports com.sms.sms;
    exports com.sms.sms.security;
    opens com.sms.sms.security to javafx.fxml;
    exports com.sms.sms.user;
    opens com.sms.sms.user to javafx.fxml;
    exports com.sms.sms.admin;
    exports com.sms.sms.admin.entity;
    opens com.sms.sms.admin.entity to javafx.fxml, org.hibernate.orm.core;
    exports com.sms.sms.user.entity;
    opens com.sms.sms.user.entity to javafx.fxml, org.hibernate.orm.core;
    exports com.sms.sms.db.db_init;
    opens com.sms.sms.db.db_init to javafx.fxml;
    exports com.sms.sms.security.service;
    opens com.sms.sms.security.service to javafx.fxml;
    exports com.sms.sms.user.repository;
    opens com.sms.sms.user.repository to javafx.fxml;
}
