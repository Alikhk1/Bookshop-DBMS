package com.example.bookshopmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;

public class StationeryCustomerBill {
    private StringProperty SIDProperty;
    private StringProperty C_BNO_StationeryProperty;
    private IntegerProperty QuantityStationeryProperty;
    private FloatProperty PriceStationeryProperty;

    public StationeryCustomerBill() {
        this.SIDProperty = new SimpleStringProperty();
        this.C_BNO_StationeryProperty = new SimpleStringProperty();
        this.QuantityStationeryProperty = new SimpleIntegerProperty();
        this.PriceStationeryProperty = new SimpleFloatProperty();

    }

    public String getSIDProperty() {
        return SIDProperty.get();
    }

    public StringProperty SIDPropertyProperty() {
        return SIDProperty;
    }

    public void setSIDProperty(String SIDProperty) {
        this.SIDProperty.set(SIDProperty);
    }

    public String getC_BNO_StationeryProperty() {
        return C_BNO_StationeryProperty.get();
    }

    public StringProperty c_BNO_StationeryPropertyProperty() {
        return C_BNO_StationeryProperty;
    }

    public void setC_BNO_StationeryProperty(String c_BNO_StationeryProperty) {
        this.C_BNO_StationeryProperty.set(c_BNO_StationeryProperty);
    }

    public int getQuantityStationeryProperty() {
        return QuantityStationeryProperty.get();
    }

    public IntegerProperty quantityStationeryPropertyProperty() {
        return QuantityStationeryProperty;
    }

    public void setQuantityStationeryProperty(int quantityStationeryProperty) {
        this.QuantityStationeryProperty.set(quantityStationeryProperty);
    }

    public float getPriceStationeryProperty() {
        return PriceStationeryProperty.get();
    }

    public FloatProperty priceStationeryPropertyProperty() {
        return PriceStationeryProperty;
    }

    public void setPriceStationeryProperty(float priceStationeryProperty) {
        this.PriceStationeryProperty.set(priceStationeryProperty);
    }
}