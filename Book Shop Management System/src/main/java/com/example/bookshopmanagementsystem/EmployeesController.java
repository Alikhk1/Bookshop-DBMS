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

public class EmployeesController {

    @FXML
    private TextField EID;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField LastName;
    @FXML
    private TextField DateJoined;
    @FXML
    private TextField Designation;
    @FXML
    private TextField Salary;
    @FXML
    private TextField DOB;
    @FXML
    private TextField Address;
    @FXML
    private TextField Email;
    @FXML
    private TextArea employeesResultConsole;
    @FXML
    private TextField searchEID;
    @FXML
    private TextField searchSalary;

    @FXML
    private TableColumn<Employee, Integer> columnEID;
    @FXML
    private TableColumn<Employee, String> columnFirstName;
    @FXML
    private TableColumn<Employee, String> columnLastName;
    @FXML
    private TableColumn<Employee, String> columnDateJoined;
    @FXML
    private TableColumn<Employee, String> columnDesignation;
    @FXML
    private TableColumn<Employee, Float> columnSalary;
    @FXML
    private TableColumn<Employee, String> columnDOB;
    @FXML
    private TableColumn<Employee, String> columnAddress;
    @FXML
    private TableColumn<Employee, String> columnEmail;
    @FXML
    private TableView employeesTable;


    //Code For Data Insertion Into Employees Table

    public void insertEmployees(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Employee " +
                "VALUES ('" + EID.getText() + "', '" + FirstName.getText() + "', '" + LastName.getText() + "', '" + DateJoined.getText() + "', '" +
                Designation.getText() + "', " + Salary.getText() + ", '" + DOB.getText() + "', '" +
                Address.getText() + "', '" + Email.getText() + "')";


        try (Connection connection = DatabaseOperations.establishConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            employeesResultConsole.setText("Values have been added to the Database Successfully");
            ObservableList<Employee> EmployeeList = getAllRecords();
            populateTable(EmployeeList);

        } catch (SQLException error) {
            System.out.println("Exception occurred while Inserting data into Employeess Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Updating Books table
    public void updateEmployees(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Employee SET Salary = '" + searchSalary.getText() + "' WHERE EID = '" + searchEID.getText() + "' ";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            employeesResultConsole.setText("The Salary has been successfully Updated");
            ObservableList<Employee> EmployeeList = getAllRecords();
            populateTable(EmployeeList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Updating Quantity in Employees Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Deleting a Record from Books Table
    public void deleteEmployees(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Employee WHERE EID = '" + Integer.parseInt(searchEID.getText()) + "'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            employeesResultConsole.setText("The Record has been successfully Deleted");
            ObservableList<Employee> EmployeeList = getAllRecords();
            populateTable(EmployeeList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Deleting Record from employees Table");
            error.printStackTrace();
            throw error;
        }
    }


    // Code to Display Values in TableView

    public ObservableList<Employee> getAllRecords() throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM Employee";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<Employee> EmployeeList = getEmployeeObjects(rsSet);
            return EmployeeList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }

    }


    public ObservableList<Employee> getEmployeeObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException {

        try {
            ObservableList<Employee> EmployeeList = FXCollections.observableArrayList();
            while(rsSet.next()){
                Employee employee = new Employee();
                employee.setEIDProperty(rsSet.getInt("EID"));
                employee.setFirstNameProperty(rsSet.getString("FirstName"));
                employee.setLastNameProperty(rsSet.getString("LastName"));
                employee.setDateJoinedProperty(rsSet.getString("DateJoined"));
                employee.setDesignationProperty(rsSet.getString("Designation"));
                employee.setSalaryProperty(rsSet.getFloat("Salary"));
                employee.setDOBProperty(rsSet.getString("DOB"));
                employee.setAddressProperty(rsSet.getString("Address"));
                employee.setEmailProperty(rsSet.getString("Email"));

                EmployeeList.add(employee);
            }
            return EmployeeList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        columnEID.setCellValueFactory(cellData -> cellData.getValue().EIDPropertyProperty().asObject());
        columnFirstName.setCellValueFactory(cellData -> cellData.getValue().firstNamePropertyProperty());
        columnLastName.setCellValueFactory(cellData -> cellData.getValue().lastNamePropertyProperty());
        columnDateJoined.setCellValueFactory(cellData -> cellData.getValue().dateJoinedPropertyProperty());
        columnSalary.setCellValueFactory(cellData -> cellData.getValue().salaryPropertyProperty().asObject());
        columnDOB.setCellValueFactory(cellData -> cellData.getValue().DOBPropertyProperty());
        columnAddress.setCellValueFactory(cellData -> cellData.getValue().addressPropertyProperty());
        columnEmail.setCellValueFactory(cellData -> cellData.getValue().emailPropertyProperty());

        ObservableList<Employee> EmployeeList = getAllRecords();
        populateTable(EmployeeList);

    }

    @FXML
    private void populateTable(ObservableList<Employee> EmployeeList) {
        employeesTable.setItems(EmployeeList);
    }


// Code for Searching Records


    public ObservableList<Employee> searchEmployees(String EID) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Employee WHERE EID = '"+Integer.parseInt(searchEID.getText())+"'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<Employee> EmployeeList = getEmployeeObjects(rsSet);
            return EmployeeList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Searching Records " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void searchEmployees (ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Employee> EmployeeList = searchEmployees(searchEID.getText());
        if(EmployeeList.size() > 0){
            populateTable(EmployeeList);
            employeesResultConsole.setText("Record has been found!");
        }else {
            employeesResultConsole.setText("Record has NOT been found!");
        }
    }

    @FXML
    private void searchAllEmployees(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<Employee> EmployeeList = getAllRecords();
        employeesResultConsole.setText("All Records");
        populateTable(EmployeeList);
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
