package com.example.bookshopmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;


public class Supplier {
    private StringProperty SPIDProperty;
    private StringProperty FirstNameProperty;
    private StringProperty LastNameProperty;
    private StringProperty ContactNumberProperty;
    private StringProperty AddressProperty;
    private StringProperty EmailProperty;

    public Supplier() {
        this.SPIDProperty = new SimpleStringProperty();
        this.FirstNameProperty = new SimpleStringProperty();
        this.LastNameProperty = new SimpleStringProperty();
        this.ContactNumberProperty = new SimpleStringProperty();
        this.AddressProperty = new SimpleStringProperty();
        this.EmailProperty = new SimpleStringProperty();

    }

    public String getSPIDProperty() {
        return SPIDProperty.get();
    }

    public StringProperty SPIDPropertyProperty() {
        return SPIDProperty;
    }

    public void setSPIDProperty(String SPIDProperty) {
        this.SPIDProperty.set(SPIDProperty);
    }

    public String getFirstNameProperty() {
        return FirstNameProperty.get();
    }

    public StringProperty firstNamePropertyProperty() {
        return FirstNameProperty;
    }

    public void setFirstNameProperty(String firstNameProperty) {
        this.FirstNameProperty.set(firstNameProperty);
    }

    public String getLastNameProperty() {
        return LastNameProperty.get();
    }

    public StringProperty lastNamePropertyProperty() {
        return LastNameProperty;
    }

    public void setLastNameProperty(String lastNameProperty) {
        this.LastNameProperty.set(lastNameProperty);
    }

    public String getContactNumberProperty() {
        return ContactNumberProperty.get();
    }

    public StringProperty contactNumberPropertyProperty() {
        return ContactNumberProperty;
    }

    public void setContactNumberProperty(String contactNumberProperty) {
        this.ContactNumberProperty.set(contactNumberProperty);
    }

    public String getAddressProperty() {
        return AddressProperty.get();
    }

    public StringProperty addressPropertyProperty() {
        return AddressProperty;
    }

    public void setAddressProperty(String addressProperty) {
        this.AddressProperty.set(addressProperty);
    }

    public String getEmailProperty() {
        return EmailProperty.get();
    }

    public StringProperty emailPropertyProperty() {
        return EmailProperty;
    }

    public void setEmailProperty(String emailProperty) {
        this.EmailProperty.set(emailProperty);
    }
}