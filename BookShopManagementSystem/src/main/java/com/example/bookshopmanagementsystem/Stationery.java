package com.example.bookshopmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;


public class Stationery {
    private StringProperty SIDProperty;
    private StringProperty StationeryNameProperty;
    private IntegerProperty QuantityProperty;
    private FloatProperty PriceProperty;

    public Stationery() {
        this.SIDProperty = new SimpleStringProperty();
        this.StationeryNameProperty = new SimpleStringProperty();
        this.QuantityProperty = new SimpleIntegerProperty();
        this.PriceProperty = new SimpleFloatProperty();


    }



    public String getStationeryNameProperty() {
        return StationeryNameProperty.get();
    }

    public StringProperty stationeryNamePropertyProperty() {
        return StationeryNameProperty;
    }

    public void setStationeryNameProperty(String stationeryNameProperty) {
        this.StationeryNameProperty.set(stationeryNameProperty);
    }

    public int getQuantityProperty() {
        return QuantityProperty.get();
    }

    public IntegerProperty quantityPropertyProperty() {
        return QuantityProperty;
    }

    public void setQuantityProperty(int quantityProperty) {
        this.QuantityProperty.set(quantityProperty);
    }

    public float getPriceProperty() {
        return PriceProperty.get();
    }

    public FloatProperty pricePropertyProperty() {
        return PriceProperty;
    }

    public void setPriceProperty(float priceProperty) {
        this.PriceProperty.set(priceProperty);
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
}