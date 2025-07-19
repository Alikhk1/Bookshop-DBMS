package com.example.bookshopmanagementsystem;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.*;

// Controls Login, Menu
public class HelloController {


    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    // ************************MAIN SCREEN********************************
    // Cancel Button Method For Login Screen
    public void cancelButtonAction(ActionEvent e){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    //Login Button Method for Login Screen
    public void loginButtonAction(ActionEvent e){
        try (Connection connection = DatabaseOperations.establishConnection()) {
            System.out.println("Connected to SQL Server database using Windows Authentication.");
            loginMessageLabel.setText("Connection Established");
            switchtoMenu(e);
            // You can perform additional database operations here if needed
        } catch (Exception exception) {
            System.out.println("Error connecting to SQL Server database.");
            exception.printStackTrace();
        }
    }


    // *************************************BOOKS********************************************


    //Switching Between Scenes Code
    private Stage stage;
    private Scene scene;
    public void switchToHelloView(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 604, 400);
        stage.setScene(scene);
        stage.show();

    }

    public void switchtoMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 612, 408);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoBooks(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Books.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1080, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoEmployees(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Employees.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1080, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoStationery(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Stationery.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1080, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoSupplier(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Supplier.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1080, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoCustomer(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Customer.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1080, 700);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoCustomerBilling(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerBilling.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 1080, 700);
        stage.setScene(scene);
        stage.show();
    }






}