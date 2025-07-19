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

public class CustomerController {

    @FXML
    private TextField CID;
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
    private TextArea customerResultConsole;
    @FXML
    private TextField searchCID;
    @FXML
    private TextField searchEmail;
    @FXML
    private TableColumn<Customer, String> columnCID;
    @FXML
    private TableColumn<Customer, String> columnFirstName;
    @FXML
    private TableColumn<Customer, String> columnLastName;
    @FXML
    private TableColumn<Customer, String> columnContactNumber;
    @FXML
    private TableColumn<Customer, String> columnAddress;
    @FXML
    private TableColumn<Customer, String> columnEmail;
    @FXML
    private TableView customerTable;


    //Code For Data Insertion Into Customer Table

    public void insertCustomer(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Customer " +
                "VALUES ('" + CID.getText() + "', '" + FirstName.getText() + "', '" + LastName.getText() + "', '" + ContactNumber.getText() + "','" + Address.getText() + "', '" + Email.getText() + "')";

        try (Connection connection = DatabaseOperations.establishConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            customerResultConsole.setText("Values have been added to the Database Successfully");
            ObservableList<Customer> CustomerList = getAllRecords();
            populateTable(CustomerList);

        } catch (SQLException error) {
            System.out.println("Exception occurred while Inserting data into Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Updating Customer table
    public void updateCustomer(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Customer SET Email = '" + searchEmail.getText() + "' WHERE CID = '" + searchCID.getText() + "' ";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            customerResultConsole.setText("The Quantity has been successfully Updated");
            ObservableList<Customer> CustomerList = getAllRecords();
            populateTable(CustomerList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Updating the Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Deleting a Record from Customer Table
    public void deleteCustomer(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Customer WHERE CID = '" + Integer.parseInt(searchCID.getText()) + "'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            customerResultConsole.setText("The Record has been successfully Deleted");
            ObservableList<Customer> CustomerList = getAllRecords();
            populateTable(CustomerList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Deleting Record from Table");
            error.printStackTrace();
            throw error;
        }
    }


    // Code to Display Values in TableView

    public ObservableList<Customer> getAllRecords() throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM Customer";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<Customer> CustomerList = getCustomerObjects(rsSet);
            return CustomerList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }

    }


    public ObservableList<Customer> getCustomerObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException {

        try {
            ObservableList<Customer> CustomerList = FXCollections.observableArrayList();
            while(rsSet.next()){
                Customer customer = new Customer();
                customer.setCIDProperty(rsSet.getString("CID"));
                customer.setFirstNameProperty(rsSet.getString("FirstName"));
                customer.setContactNumberProperty(rsSet.getString("ContactNumber"));
                customer.setAddressProperty(rsSet.getString("Address"));
                customer.setEmailProperty(rsSet.getString("Email"));

                CustomerList.add(customer);
            }
            return CustomerList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        columnCID.setCellValueFactory(cellData -> cellData.getValue().CIDPropertyProperty());
        columnFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNamePropertyProperty());
        columnLastName.setCellValueFactory(cellData -> cellData.getValue().lastNamePropertyProperty());
        columnContactNumber.setCellValueFactory(cellData -> cellData.getValue().contactNumberPropertyProperty());
        columnAddress.setCellValueFactory(cellData -> cellData.getValue().addressPropertyProperty());
        columnEmail.setCellValueFactory(cellData -> cellData.getValue().emailPropertyProperty());


        ObservableList<Customer> CustomerList = getAllRecords();
        populateTable(CustomerList);

    }

    @FXML
    private void populateTable(ObservableList<Customer> CustomerList) {
        customerTable.setItems(CustomerList);
    }


// Code for Searching Records


    public ObservableList<Customer> searchCustomer(String CID) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Customer WHERE CID = '"+Integer.parseInt(searchCID.getText())+"'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<Customer> CustomerList = getCustomerObjects(rsSet);
            return CustomerList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Searching Records " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void searchCustomer (ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Customer> CustomerList = searchCustomer(searchCID.getText());
        if(CustomerList.size() > 0){
            populateTable(CustomerList);
            customerResultConsole.setText("Record has been found!");
        }else {
            customerResultConsole.setText("Record has NOT been found!");
        }
    }

    @FXML
    private void searchAllCustomer(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<Customer> CustomerList = getAllRecords();
        customerResultConsole.setText("All Records");
        populateTable(CustomerList);
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
