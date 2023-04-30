module org.dolta.artifact {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.google.gson;
    requires java.sql;
    requires org.apache.logging.log4j;
    opens org.dolta.hmp to javafx.fxml;
    exports org.dolta.hmp;
    exports org.dolta.hmp.utils;
    opens org.dolta.hmp.utils to javafx.fxml;
}
