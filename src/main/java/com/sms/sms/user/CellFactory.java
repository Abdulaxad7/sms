package com.sms.sms.user;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

public interface CellFactory {
    static <T> void cellFactory(TableColumn<T, String> column) {
        column.setCellFactory(columnFactory -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });
    }
}
