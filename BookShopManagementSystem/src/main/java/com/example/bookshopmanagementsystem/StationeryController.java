package com.example.bookshopmanagementsystem;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.*;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class StationeryController {

    @FXML
    private TextField SID;
    @FXML
    private TextField StationeryName;
    @FXML
    private TextField Quantity;
    @FXML
    private TextField Price;
    @FXML
    private TextArea stationeryResultConsole;
    @FXML
    private TextField searchSID;
    @FXML
    private TextField searchQuantity;
    @FXML
    private TableColumn<Stationery, String> columnSID;
    @FXML
    private TableColumn<Stationery, String> columnStationeryName;
    @FXML
    private TableColumn<Stationery, Integer> columnQuantity;
    @FXML
    private TableColumn<Stationery, Float> columnPrice;
    @FXML
    private TableView stationeryTable;


    //Code For Data Insertion Into Stationery Table

    public void insertStationery(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Stationery " +
                "VALUES ('" + SID.getText() + "', '" + StationeryName.getText() + "', '" + Quantity.getText() + "', '" + Price.getText() + "')";

        try (Connection connection = DatabaseOperations.establishConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            stationeryResultConsole.setText("Values have been added to the Database Successfully");
            ObservableList<Stationery> StationeryList = getAllRecords();
            populateTable(StationeryList);

        } catch (SQLException error) {
            System.out.println("Exception occurred while Inserting data into Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Updating Stationery table
    public void updateStationery(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Stationery SET Quantity = '" + searchQuantity.getText() + "' WHERE SID = '" + searchSID.getText() + "' ";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            stationeryResultConsole.setText("The Quantity has been successfully Updated");
            ObservableList<Stationery> StationeryList = getAllRecords();
            populateTable(StationeryList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Updating the Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Deleting a Record from Stationery Table
    public void deleteStationery(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Stationery WHERE SID = '" + Integer.parseInt(searchSID.getText()) + "'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            stationeryResultConsole.setText("The Record has been successfully Deleted");
            ObservableList<Stationery> StationeryList = getAllRecords();
            populateTable(StationeryList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Deleting Record from Table");
            error.printStackTrace();
            throw error;
        }
    }


    // Code to Display Values in TableView

    public ObservableList<Stationery> getAllRecords() throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM Stationery";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<Stationery> StationeryList = getStationeryObjects(rsSet);
            return StationeryList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }

    }


    public ObservableList<Stationery> getStationeryObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException {

        try {
            ObservableList<Stationery> StationeryList = FXCollections.observableArrayList();
            while(rsSet.next()){
                Stationery stationery = new Stationery();
                stationery.setSIDProperty(rsSet.getString("SID"));
                stationery.setStationeryNameProperty(rsSet.getString("StationaryName"));
                stationery.setQuantityProperty(rsSet.getInt("Quantity"));
                stationery.setPriceProperty(rsSet.getFloat("Price"));

                StationeryList.add(stationery);
            }
            return StationeryList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        columnSID.setCellValueFactory(cellData -> cellData.getValue().SIDPropertyProperty());
        columnStationeryName.setCellValueFactory(cellData -> cellData.getValue().stationeryNamePropertyProperty());
        columnQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityPropertyProperty().asObject());
        columnPrice.setCellValueFactory(cellData -> cellData.getValue().pricePropertyProperty().asObject());

        ObservableList<Stationery> StationeryList = getAllRecords();
    }
    @FXML
    private void populateTable(ObservableList<Stationery> StationeryList) {
        stationeryTable.setItems(StationeryList);
    }


// Code for Searching Records


    public ObservableList<Stationery> searchStationery(String SID) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Stationery WHERE SID = '"+Integer.parseInt(searchSID.getText())+"'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<Stationery> StationeryList = getStationeryObjects(rsSet);
            return StationeryList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Searching Records " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void searchStationery (ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Stationery> StationeryList = searchStationery(searchSID.getText());
        if(StationeryList.size() > 0){
            populateTable(StationeryList);
            stationeryResultConsole.setText("Record has been found!");
        }else {
            stationeryResultConsole.setText("Record has NOT been found!");
        }
    }

    @FXML
    private void searchAllStationery(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<Stationery> StationeryList = getAllRecords();
        stationeryResultConsole.setText("All Records");
        populateTable(StationeryList);
    }


    // Switching Between Scenes Code:
    private Stage stage;
    private Scene scene;
    public void switchtoMenu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Menu.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 612, 408);
        stage.setScene(scene);
        stage.show();
    }
}
