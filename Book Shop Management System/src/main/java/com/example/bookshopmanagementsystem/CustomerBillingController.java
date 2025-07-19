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

public class CustomerBillingController {

    @FXML
    private TextField BID;
    @FXML
    private TextField C_BNO_Books;
    @FXML
    private TextField QuantityBooks;
    @FXML
    private TextField PriceBooks;
    @FXML
    private TextArea customerBillingResultConsole;
    @FXML
    private TextField searchBID;
    @FXML
    private TextField searchQuantityBooks;
    @FXML
    private TextField searchC_BNO_Books;

    // For Stationery:
    @FXML
    private TextField SID;
    @FXML
    private TextField C_BNO_Stationery;
    @FXML
    private TextField QuantityStationery;
    @FXML
    private TextField PriceStationery;
    @FXML
    private TextField searchSID;
    @FXML
    private TextField searchQuantityStationery;
    @FXML
    private TextField searchC_BNO_Stationery;

    // For CustomerBill:
    @FXML
    private TextField C_BNO_CustomerBill;
    @FXML
    private TextField CID;
    @FXML
    private TextField Date;
    @FXML
    private TextField Total_Price;
    @FXML
    private TextField searchC_BNO;
    @FXML
    private TextField searchTotal_Price;


    //For Books
    @FXML
    private TableColumn<BooksCustomerBill, String> columnBID;
    @FXML
    private TableColumn<BooksCustomerBill, String> columnC_BNO_Books;
    @FXML
    private TableColumn<BooksCustomerBill, Integer> columnQuantityBooks;
    @FXML
    private TableColumn<BooksCustomerBill, Float> columnPriceBooks;

    @FXML
    private TableView BooksCustomerBillTable;

    // For Stationery
    @FXML
    private TableColumn<StationeryCustomerBill, String> columnSID;
    @FXML
    private TableColumn<StationeryCustomerBill, String> columnC_BNO_Stationery;
    @FXML
    private TableColumn<StationeryCustomerBill, Integer> columnQuantityStationery;
    @FXML
    private TableColumn<StationeryCustomerBill, Float> columnPriceStationery;

    @FXML
    private TableView StationeryCustomerBillTable;

    // For CustomerBill
    @FXML
    private TableColumn<CustomerBill, String> columnC_BNO;
    @FXML
    private TableColumn<CustomerBill, String> columnCID;
    @FXML
    private TableColumn<CustomerBill, String> columnDate;
    @FXML
    private TableColumn<CustomerBill, Float> columnTotalPrice;

    @FXML
    private TableView CustomerBillTable;



    //Code For Data Insertion Into BooksCustomerBill Table

    public void insertBooks(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Books_Customer_Bill " +
                "VALUES ('" + BID.getText() + "', '" + C_BNO_Books.getText() + "', " + QuantityBooks.getText() + ", " + PriceBooks.getText() + ")";

        String updateQuery = "CREATE TRIGGER UpdateCustomerBillTotalPrice " +
                "ON Books_Customer_Bill " +
                "AFTER UPDATE " +
                "AS " +
                "BEGIN " +
                "    UPDATE cb " +
                "    SET Total_Price = Total_Price + (i.Quantity * (SELECT Price FROM Books WHERE BID = i.BID)) " +
                "    FROM Customer_Bill cb " +
                "    JOIN inserted i ON cb.C_BNO = i.C_BNO; " +
                "END;";

        try (Connection connection = DatabaseOperations.establishConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            statement.executeUpdate(updateQuery);
            customerBillingResultConsole.setText("Values have been added to the Database Successfully");
            ObservableList<BooksCustomerBill> BooksCustomerList = getAllRecords();
            populateTableBooks(BooksCustomerList);

        } catch (SQLException error) {
            System.out.println("Exception occurred while Inserting data into Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Updating Books table
    public void updateBooks(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Books_Customer_Bill SET Quantity = " + searchQuantityBooks.getText() + " WHERE BID = '" + searchBID.getText() + "' AND C_BNO = '" + searchC_BNO_Books.getText() + "'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            customerBillingResultConsole.setText("The Quantity has been successfully Updated");
            ObservableList<BooksCustomerBill> BooksCustomerList = getAllRecords();
            populateTableBooks(BooksCustomerList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Updating the Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Deleting a Record from Books Table
    public void deleteBooks(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Books_Customer_Bill WHERE BID = '" + Integer.parseInt(searchBID.getText()) + "' AND C_BNO = '" + searchC_BNO_Books.getText() + "'";


        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            customerBillingResultConsole.setText("The Record has been successfully Deleted");
            ObservableList<BooksCustomerBill> BooksCustomerList = getAllRecords();
            populateTableBooks(BooksCustomerList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Deleting Record from Table");
            error.printStackTrace();
            throw error;
        }
    }


    // Code to Display Values in TableView

    public ObservableList<BooksCustomerBill> getAllRecords() throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM Books_Customer_Bill";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<BooksCustomerBill> BooksCustomerList = getBooksCustomerBillObjects(rsSet);
            return BooksCustomerList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }

    }


    public ObservableList<BooksCustomerBill> getBooksCustomerBillObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException {

        try {
            ObservableList<BooksCustomerBill> BooksCustomerList = FXCollections.observableArrayList();
            while(rsSet.next()){
                BooksCustomerBill bookscustomerbill = new BooksCustomerBill();
                bookscustomerbill.setBIDProperty(rsSet.getString("BID"));
                bookscustomerbill.setC_BNO_BooksProperty(rsSet.getString("C_BNO"));
                bookscustomerbill.setQuantityBooksProperty(rsSet.getInt("Quantity"));
                bookscustomerbill.setPriceBooksProperty(rsSet.getFloat("Price"));

                BooksCustomerList.add(bookscustomerbill);
            }
            return BooksCustomerList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        columnBID.setCellValueFactory(cellData -> cellData.getValue().BIDPropertyProperty());
        columnC_BNO_Books.setCellValueFactory(cellData -> cellData.getValue().c_BNO_BooksPropertyProperty());
        columnQuantityBooks.setCellValueFactory(cellData -> cellData.getValue().quantityBooksPropertyProperty().asObject());
        columnPriceBooks.setCellValueFactory(cellData -> cellData.getValue().priceBooksPropertyProperty().asObject());

        ObservableList<BooksCustomerBill> BooksCustomerList = getAllRecords();
        populateTableBooks(BooksCustomerList);


        columnSID.setCellValueFactory(cellData -> cellData.getValue().SIDPropertyProperty());
        columnC_BNO_Stationery.setCellValueFactory(cellData -> cellData.getValue().c_BNO_StationeryPropertyProperty());
        columnQuantityStationery.setCellValueFactory(cellData -> cellData.getValue().quantityStationeryPropertyProperty().asObject());
        columnPriceStationery.setCellValueFactory(cellData -> cellData.getValue().priceStationeryPropertyProperty().asObject());

        ObservableList<StationeryCustomerBill> StationeryCustomerList = getAllStationeryRecords();
        populateTableStationery(StationeryCustomerList);

        columnC_BNO.setCellValueFactory(cellData -> cellData.getValue().c_BNO_CustomerBillPropertyProperty());
        columnCID.setCellValueFactory(cellData -> cellData.getValue().CIDPropertyProperty());
        columnDate.setCellValueFactory(cellData -> cellData.getValue().CIDPropertyProperty());
        columnTotalPrice.setCellValueFactory(cellData -> cellData.getValue().total_PricePropertyProperty().asObject());

        ObservableList<CustomerBill> CustomerBillList = getAllCustomerBillRecords();
        populateTableCustomerBill(CustomerBillList);
    }

    @FXML
    private void populateTableBooks(ObservableList<BooksCustomerBill> BooksCustomerList) {
        BooksCustomerBillTable.setItems(BooksCustomerList);
    }


// Code for Searching Records


    public ObservableList<BooksCustomerBill> searchBooks(String BID) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Books_Customer_Bill WHERE BID = '"+Integer.parseInt(searchBID.getText())+"'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<BooksCustomerBill> BooksCustomerList = getBooksCustomerBillObjects(rsSet);
            return BooksCustomerList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Searching Records " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void searchBooks (ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<BooksCustomerBill> BooksCustomerList = searchBooks(searchBID.getText());
        if(BooksCustomerList.size() > 0){
            populateTableBooks(BooksCustomerList);
            customerBillingResultConsole.setText("Record has been found!");
        }else {
            customerBillingResultConsole.setText("Record has NOT been found!");
        }
    }

    @FXML
    private void searchAllBooks(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<BooksCustomerBill> BooksCustomerList = getAllRecords();
        customerBillingResultConsole.setText("All Records");
        populateTableBooks(BooksCustomerList);
    }

//****************************************************************************************

    //CODE FOR STATIONERY:

    public void insertStationery(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Stationery_Customer_Bill " +
                "VALUES ('" + SID.getText() + "', '" + C_BNO_Stationery.getText() + "', " + QuantityStationery.getText() + ", " + PriceStationery.getText() + ")";

        String updateQuery = "CREATE TRIGGER UpdateCustomerBillTotalPriceStationery " +
                "ON Stationery_Customer_Bill " +
                "AFTER UPDATE " +
                "AS " +
                "BEGIN " +
                "UPDATE cb " +
                "SET Total_Price = Total_Price + (i.Quantity * (SELECT Price FROM Stationery WHERE SID = i.SID)) " +
                "FROM Customer_Bill cb " +
                "JOIN inserted i ON cb.C_BNO = i.C_BNO; " +
                "END;";


        try (Connection connection = DatabaseOperations.establishConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            statement.executeUpdate(updateQuery);
            customerBillingResultConsole.setText("Values have been added to the Database Successfully");
            ObservableList<StationeryCustomerBill> StationeryCustomerList = getAllStationeryRecords();
            populateTableStationery(StationeryCustomerList);

        } catch (SQLException error) {
            System.out.println("Exception occurred while Inserting data into Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Updating Stationery table
    public void updateStationery(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Stationery_Customer_Bill SET Quantity = " + searchQuantityStationery.getText() + " WHERE SID = '" + searchSID.getText() + "' AND C_BNO = '" + searchC_BNO_Stationery.getText() + "'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            customerBillingResultConsole.setText("The Quantity has been successfully Updated");
            ObservableList<StationeryCustomerBill> StationeryBooksCustomerList = getAllStationeryRecords();
            populateTableStationery(StationeryBooksCustomerList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Updating the Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Deleting a Record from Stationery Table
    public void deleteStationery(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Stationery_Customer_Bill WHERE SID = '" + Integer.parseInt(searchSID.getText()) + "' AND C_BNO = '" + searchC_BNO_Stationery.getText() + "'";


        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            customerBillingResultConsole.setText("The Record has been successfully Deleted");
            ObservableList<StationeryCustomerBill> StationeryCustomerList = getAllStationeryRecords();
            populateTableStationery(StationeryCustomerList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Deleting Record from Table");
            error.printStackTrace();
            throw error;
        }
    }


    // Code to Display Values in TableView

    public ObservableList<StationeryCustomerBill> getAllStationeryRecords() throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM Stationery_Customer_Bill";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<StationeryCustomerBill> StationeryCustomerList = getStationeryCustomerBillObjects(rsSet);
            return StationeryCustomerList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }

    }


    public ObservableList<StationeryCustomerBill> getStationeryCustomerBillObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException {

        try {
            ObservableList<StationeryCustomerBill> StationeryCustomerList = FXCollections.observableArrayList();
            while(rsSet.next()){
                StationeryCustomerBill stationeryscustomerbill = new StationeryCustomerBill();
                stationeryscustomerbill.setSIDProperty(rsSet.getString("SID"));
                stationeryscustomerbill.setC_BNO_StationeryProperty(rsSet.getString("C_BNO"));
                stationeryscustomerbill.setQuantityStationeryProperty(rsSet.getInt("Quantity"));
                stationeryscustomerbill.setPriceStationeryProperty(rsSet.getFloat("Price"));

                StationeryCustomerList.add(stationeryscustomerbill);
            }
            return StationeryCustomerList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }
    }


    @FXML
    private void populateTableStationery(ObservableList<StationeryCustomerBill> StationeryCustomerList) {
        StationeryCustomerBillTable.setItems(StationeryCustomerList);
    }


// Code for Searching Records


    public ObservableList<StationeryCustomerBill> searchStationery(String SID) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Stationery_Customer_Bill WHERE SID = '"+Integer.parseInt(searchSID.getText())+"'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<StationeryCustomerBill> StationeryCustomerList = getStationeryCustomerBillObjects(rsSet);
            return StationeryCustomerList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Searching Records " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void searchStationery (ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<StationeryCustomerBill> StationeryCustomerList = searchStationery(searchSID.getText());
        if(StationeryCustomerList.size() > 0){
            populateTableStationery(StationeryCustomerList);
            customerBillingResultConsole.setText("Record has been found!");
        }else {
            customerBillingResultConsole.setText("Record has NOT been found!");
        }
    }

    @FXML
    private void searchAllStationery(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<StationeryCustomerBill> StationeryCustomerList = getAllStationeryRecords();
        customerBillingResultConsole.setText("All Records");
        populateTableStationery(StationeryCustomerList);
    }


// CUSTOMER BILL *********************************************************************



    public void insertBill(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Customer_Bill " +
                "VALUES ('" + C_BNO_CustomerBill.getText() + "', '" + CID.getText() + "', '" + Date.getText() + "', " + Total_Price.getText() + ")";


        try (Connection connection = DatabaseOperations.establishConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            customerBillingResultConsole.setText("Values have been added to the Database Successfully");
            ObservableList<CustomerBill> CustomerBillList = getAllCustomerBillRecords();
            populateTableCustomerBill(CustomerBillList);

        } catch (SQLException error) {
            System.out.println("Exception occurred while Inserting data into Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Updating Stationery table
    public void updateCustomerBill(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Customer_Bill SET Total_Price = " + searchTotal_Price.getText() + " WHERE C_BNO = '" + searchC_BNO.getText() + "'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            customerBillingResultConsole.setText("The Quantity has been successfully Updated");
            ObservableList<CustomerBill> CustomerBillList = getAllCustomerBillRecords();
            populateTableCustomerBill(CustomerBillList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Updating the Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Deleting a Record from Stationery Table
    public void deleteCustomerBill(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Customer_Bill WHERE C_BNO = '" + Integer.parseInt(searchSID.getText()) + "'";


        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            customerBillingResultConsole.setText("The Record has been successfully Deleted");
            ObservableList<CustomerBill> CustomerBillList = getAllCustomerBillRecords();
            populateTableCustomerBill(CustomerBillList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Deleting Record from Table");
            error.printStackTrace();
            throw error;
        }
    }


    // Code to Display Values in TableView

    public ObservableList<CustomerBill> getAllCustomerBillRecords() throws ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM Customer_Bill";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<CustomerBill> CustomerBillList = getCustomerBillObjects(rsSet);
            return CustomerBillList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }

    }


    public ObservableList<CustomerBill> getCustomerBillObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException {

        try {
            ObservableList<CustomerBill> CustomerBillList = FXCollections.observableArrayList();
            while(rsSet.next()){
                CustomerBill customerbill = new CustomerBill();
                customerbill.setC_BNO_CustomerBillProperty(rsSet.getString("C_BNO"));
                customerbill.setCIDProperty(rsSet.getString("CID"));
                customerbill.setDateProperty(rsSet.getString("Date"));
                customerbill.setTotal_PriceProperty(rsSet.getFloat("Total_Price"));

                CustomerBillList.add(customerbill);
            }
            return CustomerBillList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }
    }


    @FXML
    private void populateTableCustomerBill(ObservableList<CustomerBill> CustomerList) {
        CustomerBillTable.setItems(CustomerList);
    }


// Code for Searching Records


    public ObservableList<CustomerBill> searchCustomerBill(String C_BNO) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Customer_Bill WHERE C_BNO = '"+Integer.parseInt(searchC_BNO.getText())+"'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<CustomerBill> CustomerList = getCustomerBillObjects(rsSet);
            return CustomerList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Searching Records " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void searchCustomerBill (ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<CustomerBill> CustomerBillList = searchCustomerBill(searchC_BNO.getText());
        if(CustomerBillList.size() > 0){
            populateTableCustomerBill(CustomerBillList);
            customerBillingResultConsole.setText("Record has been found!");
        }else {
            customerBillingResultConsole.setText("Record has NOT been found!");
        }
    }

    @FXML
    private void searchAllCustomberBill(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<CustomerBill> CustomerBillList = getAllCustomerBillRecords();
        customerBillingResultConsole.setText("All Records");
        populateTableCustomerBill(CustomerBillList);
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
