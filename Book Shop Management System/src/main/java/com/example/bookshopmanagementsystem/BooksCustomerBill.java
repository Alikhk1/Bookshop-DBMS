package com.example.bookshopmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;


public class BooksCustomerBill {
    private StringProperty BIDProperty;
    private StringProperty C_BNO_BooksProperty;
    private IntegerProperty QuantityBooksProperty;
    private FloatProperty PriceBooksProperty;

    public BooksCustomerBill() {
        this.BIDProperty = new SimpleStringProperty();
        this.C_BNO_BooksProperty = new SimpleStringProperty();
        this.QuantityBooksProperty = new SimpleIntegerProperty();
        this.PriceBooksProperty = new SimpleFloatProperty();

    }


    public String getBIDProperty() {
        return BIDProperty.get();
    }

    public StringProperty BIDPropertyProperty() {
        return BIDProperty;
    }

    public void setBIDProperty(String BIDProperty) {
        this.BIDProperty.set(BIDProperty);
    }

    public String getC_BNO_BooksProperty() {
        return C_BNO_BooksProperty.get();
    }

    public StringProperty c_BNO_BooksPropertyProperty() {
        return C_BNO_BooksProperty;
    }

    public void setC_BNO_BooksProperty(String c_BNO_BooksProperty) {
        this.C_BNO_BooksProperty.set(c_BNO_BooksProperty);
    }

    public float getPriceBooksProperty() {
        return PriceBooksProperty.get();
    }

    public FloatProperty priceBooksPropertyProperty() {
        return PriceBooksProperty;
    }

    public void setPriceBooksProperty(float priceBooksProperty) {
        this.PriceBooksProperty.set(priceBooksProperty);
    }


    public int getQuantityBooksProperty() {
        return QuantityBooksProperty.get();
    }

    public IntegerProperty quantityBooksPropertyProperty() {
        return QuantityBooksProperty;
    }

    public void setQuantityBooksProperty(int quantityBooksProperty) {
        this.QuantityBooksProperty.set(quantityBooksProperty);
    }
}
