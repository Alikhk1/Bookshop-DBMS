package com.example.bookshopmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;


public class Employee {
    private IntegerProperty EIDProperty;
    private StringProperty FirstNameProperty;
    private StringProperty LastNameProperty;
    private StringProperty DateJoinedProperty;
    private StringProperty DesignationProperty;
    private FloatProperty SalaryProperty;
    private StringProperty DOBProperty;
    private StringProperty AddressProperty;
    private StringProperty EmailProperty;

    public Employee (){
        this.EIDProperty = new SimpleIntegerProperty();
        this.FirstNameProperty = new SimpleStringProperty();
        this.LastNameProperty = new SimpleStringProperty();
        this.DateJoinedProperty = new SimpleStringProperty();
        this.DesignationProperty = new SimpleStringProperty();
        this.SalaryProperty = new SimpleFloatProperty();
        this.DOBProperty = new SimpleStringProperty();
        this.AddressProperty = new SimpleStringProperty();
        this.EmailProperty = new SimpleStringProperty();


    }

    public int getEIDProperty() {
        return EIDProperty.get();
    }

    public IntegerProperty EIDPropertyProperty() {
        return EIDProperty;
    }

    public void setEIDProperty(int EIDProperty) {
        this.EIDProperty.set(EIDProperty);
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

    public String getDateJoinedProperty() {
        return DateJoinedProperty.get();
    }

    public StringProperty dateJoinedPropertyProperty() {
        return DateJoinedProperty;
    }

    public void setDateJoinedProperty(String dateJoinedProperty) {
        this.DateJoinedProperty.set(dateJoinedProperty);
    }

    public String getDesignationProperty() {
        return DesignationProperty.get();
    }

    public StringProperty designationPropertyProperty() {
        return DesignationProperty;
    }

    public void setDesignationProperty(String designationProperty) {
        this.DesignationProperty.set(designationProperty);
    }

    public float getSalaryProperty() {
        return SalaryProperty.get();
    }

    public FloatProperty salaryPropertyProperty() {
        return SalaryProperty;
    }

    public void setSalaryProperty(float salaryProperty) {
        this.SalaryProperty.set(salaryProperty);
    }

    public String getDOBProperty() {
        return DOBProperty.get();
    }

    public StringProperty DOBPropertyProperty() {
        return DOBProperty;
    }

    public void setDOBProperty(String DOBProperty) {
        this.DOBProperty.set(DOBProperty);
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