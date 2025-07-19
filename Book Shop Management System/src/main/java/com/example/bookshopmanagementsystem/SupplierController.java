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

public class SupplierController {

    @FXML
    private TextField SPID;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField LastName;
    @FXML
    private TextField ContactNumber;
    @FXML
    private TextField Address;
    @FXML
    private TextField Email;
    @FXML
    private TextArea supplierResultConsole;
    @FXML
    private TextField searchSPID;
    @FXML
    private TextField searchEmail;
    @FXML
    private TableColumn<Supplier, String> columnSPID;
    @FXML
    private TableColumn<Supplier, String> columnFirstName;
    @FXML
    private TableColumn<Supplier, String> columnLastName;
    @FXML
    private TableColumn<Supplier, String> columnContactNumber;
    @FXML
    private TableColumn<Supplier, String> columnAddress;
    @FXML
    private TableColumn<Supplier, String> columnEmail;
    @FXML
    private TableView supplierTable;


    //Code For Data Insertion Into Supplier Table

    public void insertSupplier(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Supplier " +
                "VALUES ('" + SPID.getText() + "', '" + FirstName.getText() + "', '" + LastName.getText() + "', '" + ContactNumber.getText() + "','" + Address.getText() + "', '" + Email.getText() + "')";

        try (Connection connection = DatabaseOperations.establishConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            supplierResultConsole.setText("Values have been added to the Database Successfully");
            ObservableList<Supplier> SupplierList = getAllRecords();
            populateTable(SupplierList);

        } catch (SQLException error) {
            System.out.println("Exception occurred while Inserting data into Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Updating Supplier table
    public void updateSupplier(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Supplier SET Email = '" + searchEmail.getText() + "' WHERE SPID = '" + searchSPID.getText() + "' ";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            supplierResultConsole.setText("The Quantity has been successfully Updated");
            ObservableList<Supplier> SupplierList = getAllRecords();
            populateTable(SupplierList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Updating the Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Deleting a Record from Supplier Table
    public void deleteSupplier(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Supplier WHERE SPID = '" + Integer.parseInt(searchSPID.getText()) + "'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            supplierResultConsole.setText("The Record has been successfully Deleted");
            ObservableList<Supplier> SupplierList = getAllRecords();
            populateTable(SupplierList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Deleting Record from Table");
            error.printStackTrace();
            throw error;
        }
    }


    // Code to Display Values in TableView

    public ObservableList<Supplier> getAllRecords() throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM Supplier";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<Supplier> SupplierList = getSupplierObjects(rsSet);
            return SupplierList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }

    }


    public ObservableList<Supplier> getSupplierObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException {

        try {
            ObservableList<Supplier> SupplierList = FXCollections.observableArrayList();
            while(rsSet.next()){
                Supplier supplier = new Supplier();
                supplier.setSPIDProperty(rsSet.getString("SPID"));
                supplier.setFirstNameProperty(rsSet.getString("FirstName"));
                supplier.setContactNumberProperty(rsSet.getString("ContactNumber"));
                supplier.setAddressProperty(rsSet.getString("Address"));
                supplier.setEmailProperty(rsSet.getString("Email"));

                SupplierList.add(supplier);
            }
            return SupplierList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        columnSPID.setCellValueFactory(cellData -> cellData.getValue().SPIDPropertyProperty());
        columnFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNamePropertyProperty());
        columnLastName.setCellValueFactory(cellData -> cellData.getValue().lastNamePropertyProperty());
        columnContactNumber.setCellValueFactory(cellData -> cellData.getValue().contactNumberPropertyProperty());
        columnAddress.setCellValueFactory(cellData -> cellData.getValue().addressPropertyProperty());
        columnEmail.setCellValueFactory(cellData -> cellData.getValue().emailPropertyProperty());


        ObservableList<Supplier> SupplierList = getAllRecords();
        populateTable(SupplierList);

    }

    @FXML
    private void populateTable(ObservableList<Supplier> SupplierList) {
        supplierTable.setItems(SupplierList);
    }


// Code for Searching Records


    public ObservableList<Supplier> searchSupplier(String SPID) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Supplier WHERE SPID = '"+Integer.parseInt(searchSPID.getText())+"'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<Supplier> SupplierList = getSupplierObjects(rsSet);
            return SupplierList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Searching Records " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void searchSupplier (ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Supplier> SupplierList = searchSupplier(searchSPID.getText());
        if(SupplierList.size() > 0){
            populateTable(SupplierList);
            supplierResultConsole.setText("Record has been found!");
        }else {
            supplierResultConsole.setText("Record has NOT been found!");
        }
    }

    @FXML
    private void searchAllSupplier(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<Supplier> SupplierList = getAllRecords();
        supplierResultConsole.setText("All Records");
        populateTable(SupplierList);
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
