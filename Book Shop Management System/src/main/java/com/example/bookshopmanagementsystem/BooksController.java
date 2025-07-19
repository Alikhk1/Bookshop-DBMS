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

public class BooksController {
    @FXML
    private TextField BID;
    @FXML
    private TextField Author;
    @FXML
    private TextField Title;
    @FXML
    private TextField Publisher;
    @FXML
    private TextField Year;
    @FXML
    private TextField Pages;
    @FXML
    private TextField Language;
    @FXML
    private TextField Topic;
    @FXML
    private TextField ISBN;
    @FXML
    private TextField DateAdded;
    @FXML
    private TextField DDC;
    @FXML
    private TextField Quantity;
    @FXML
    private TextField Price;
    @FXML
    private TextArea booksResultConsole;
    @FXML
    private TextField searchBID;
    @FXML
    private TextField searchQuantity;
    @FXML
    private TableColumn<Books, String> columnBID;
    @FXML
    private TableColumn<Books, String> columnAuthor;
    @FXML
    private TableColumn<Books, String> columnTitle;
    @FXML
    private TableColumn<Books, String> columnPublisher;
    @FXML
    private TableColumn<Books, String> columnYear;
    @FXML
    private TableColumn<Books, Integer> columnPages;
    @FXML
    private TableColumn<Books, String> columnLanguage;
    @FXML
    private TableColumn<Books, String> columnTopic;
    @FXML
    private TableColumn<Books, String> columnISBN;
    @FXML
    private TableColumn<Books, String> columnDateAdded;
    @FXML
    private TableColumn<Books, String> columnClassification;
    @FXML
    private TableColumn<Books, Integer> columnQuantity;
    @FXML
    private TableColumn<Books, Float> columnPrice;
    @FXML
    private TableView booksTable;


    //Code For Data Insertion Into Books Table
    public void insertBooks(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Books (BID, Author, Title, Publisher, Year, Pages, Language, Topic, ISBN, DateAdded, DeweyDecimalClassification, Quantity, Price) " +
                "VALUES ('" + BID.getText() + "', '" + Author.getText() + "', '" + Title.getText() + "', '" + Publisher.getText() + "', '" +
                Year.getText() + "', " + Pages.getText() + ", '" + Language.getText() + "', '" +
                Topic.getText() + "', '" + ISBN.getText() + "', '" + DateAdded.getText() + "', '" + DDC.getText() + "', " +
                Quantity.getText() + ", " + Price.getText() + ")";


        try (Connection connection = DatabaseOperations.establishConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            booksResultConsole.setText("Values have been added to the Database Successfully");
            ObservableList<Books> BookList = getAllRecords();
            populateTable(BookList);

        } catch (SQLException error) {
            System.out.println("Exception occurred while Inserting data into Books Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Updating Books table
    public void updateBooks(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE BOOKS SET Quantity = '" + searchQuantity.getText() + "' WHERE BID = '" + searchBID.getText() + "' ";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            booksResultConsole.setText("The Quantity has been successfully Updated");
            ObservableList<Books> BookList = getAllRecords();
            populateTable(BookList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Updating Quantity in Books Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code for Deleting a Record from Books Table
    public void deleteBooks(ActionEvent e) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM Books WHERE BID = '" + Integer.parseInt(searchBID.getText()) + "'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            statement.executeUpdate(sql);
            booksResultConsole.setText("The Record has been successfully Deleted");
            ObservableList<Books> BookList = getAllRecords();
            populateTable(BookList);

        }catch (SQLException error){
            System.out.println("Exception occurred while Deleting Record from Books Table");
            error.printStackTrace();
            throw error;
        }
    }

    // Code to Display Values in TableView

    public ObservableList<Books> getAllRecords() throws ClassNotFoundException, SQLException{
        String sql = "SELECT BID,Author,Title,Publisher,Year,Pages,Language,Topic,ISBN,DateAdded,DeweyDecimalClassification,Quantity,Price FROM BOOKS";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<Books> BookList = getBookObjects(rsSet);
            return BookList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }

    }


    public ObservableList<Books> getBookObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException {

        try {
            ObservableList<Books> BookList = FXCollections.observableArrayList();
            while(rsSet.next()){
                Books book = new Books();
                book.setBIDProperty(rsSet.getString("BID"));
                book.setAuthorProperty(rsSet.getString("Author"));
                book.setTitleProperty(rsSet.getString("Title"));
                book.setPublisherProperty(rsSet.getString("Publisher"));
                book.setYearProperty(rsSet.getString("Year"));
                book.setPagesProperty(rsSet.getInt("Pages"));
                book.setLanguageProperty(rsSet.getString("Language"));
                book.setTopicProperty(rsSet.getString("Topic"));
                book.setISBNProperty(rsSet.getString("ISBN"));
                book.setDateAddedProperty(rsSet.getString("DateAdded"));
                book.setClassificationProperty(rsSet.getString("DeweyDecimalClassification"));
                book.setQuantityProperty(rsSet.getInt("Quantity"));
                book.setPriceProperty(rsSet.getFloat("Price"));
                BookList.add(book);
            }
            return BookList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Fetching Records from DB " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        columnBID.setCellValueFactory(cellData -> cellData.getValue().BIDPropertyProperty());
        columnAuthor.setCellValueFactory(cellData -> cellData.getValue().authorPropertyProperty());
        columnTitle.setCellValueFactory(cellData -> cellData.getValue().titlePropertyProperty());
        columnPublisher.setCellValueFactory(cellData -> cellData.getValue().publisherPropertyProperty());
        columnYear.setCellValueFactory(cellData -> cellData.getValue().yearPropertyProperty());
        columnPages.setCellValueFactory(cellData -> cellData.getValue().pagesPropertyProperty().asObject());
        columnLanguage.setCellValueFactory(cellData -> cellData.getValue().languagePropertyProperty());
        columnTopic.setCellValueFactory(cellData -> cellData.getValue().topicPropertyProperty());
        columnISBN.setCellValueFactory(cellData -> cellData.getValue().ISBNPropertyProperty());
        columnDateAdded.setCellValueFactory(cellData -> cellData.getValue().dateAddedPropertyProperty());
        columnClassification.setCellValueFactory(cellData -> cellData.getValue().classificationPropertyProperty());
        columnQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityPropertyProperty().asObject());
        columnPrice.setCellValueFactory(cellData -> cellData.getValue().pricePropertyProperty().asObject());

        ObservableList<Books> BookList = getAllRecords();
        populateTable(BookList);

    }

    @FXML
    private void populateTable(ObservableList<Books> BookList) {
        booksTable.setItems(BookList);
    }

    // Code for Searching Records


    public ObservableList<Books> searchBooks(String BID) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM Books WHERE BID = '"+Integer.parseInt(searchBID.getText())+"'";

        try(Connection connection = DatabaseOperations.establishConnection();
            Statement statement = connection.createStatement()){

            ResultSet rsSet = statement.executeQuery(sql);
            ObservableList<Books> BooksList = getBookObjects(rsSet);
            return BooksList;

        }catch (SQLException error){
            System.out.println("Error Occurred While Searching Records " + error);
            error.printStackTrace();
            throw error;
        }
    }

    @FXML
    private void searchBooks (ActionEvent event) throws SQLException, ClassNotFoundException {
        ObservableList<Books> BookList = searchBooks(searchBID.getText());
        if(BookList.size() > 0){
            populateTable(BookList);
            booksResultConsole.setText("Record has been found!");
        }else {
            booksResultConsole.setText("Record has NOT been found!");
        }
    }

    @FXML
    private void searchAllBooks(ActionEvent event) throws ClassNotFoundException, SQLException {
        ObservableList<Books> BookList = getAllRecords();
        booksResultConsole.setText("All Records");
        populateTable(BookList);
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
