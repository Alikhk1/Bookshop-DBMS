package com.example.bookshopmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;


public class CustomerBill {
    private StringProperty C_BNO_CustomerBillProperty;
    private StringProperty CIDProperty;
    private StringProperty DateProperty;
    private FloatProperty Total_PriceProperty;

    public CustomerBill() {
        this.C_BNO_CustomerBillProperty = new SimpleStringProperty();
        this.CIDProperty = new SimpleStringProperty();
        this.DateProperty = new SimpleStringProperty();
        this.Total_PriceProperty = new SimpleFloatProperty();

    }


    public String getC_BNO_CustomerBillProperty() {
        return C_BNO_CustomerBillProperty.get();
    }

    public StringProperty c_BNO_CustomerBillPropertyProperty() {
        return C_BNO_CustomerBillProperty;
    }

    public void setC_BNO_CustomerBillProperty(String c_BNO_CustomerBillProperty) {
        this.C_BNO_CustomerBillProperty.set(c_BNO_CustomerBillProperty);
    }

    public String getCIDProperty() {
        return CIDProperty.get();
    }

    public StringProperty CIDPropertyProperty() {
        return CIDProperty;
    }

    public void setCIDProperty(String CIDProperty) {
        this.CIDProperty.set(CIDProperty);
    }

    public String getDateProperty() {
        return DateProperty.get();
    }

    public StringProperty datePropertyProperty() {
        return DateProperty;
    }

    public void setDateProperty(String dateProperty) {
        this.DateProperty.set(dateProperty);
    }

    public float getTotal_PriceProperty() {
        return Total_PriceProperty.get();
    }

    public FloatProperty total_PricePropertyProperty() {
        return Total_PriceProperty;
    }

    public void setTotal_PriceProperty(float total_PriceProperty) {
        this.Total_PriceProperty.set(total_PriceProperty);
    }
}
