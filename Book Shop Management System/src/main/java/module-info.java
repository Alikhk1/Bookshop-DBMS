module com.example.bookshopmanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.bookshopmanagementsystem to javafx.fxml;
    exports com.example.bookshopmanagementsystem;
}