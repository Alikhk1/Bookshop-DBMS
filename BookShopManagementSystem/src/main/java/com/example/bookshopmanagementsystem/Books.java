package com.example.bookshopmanagementsystem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;


public class Books {

    private StringProperty BIDProperty;
    private StringProperty AuthorProperty;
    private StringProperty TitleProperty;
    private StringProperty PublisherProperty;
    private StringProperty YearProperty;
    private IntegerProperty PagesProperty;
    private StringProperty LanguageProperty;
    private StringProperty TopicProperty;
    private StringProperty ISBNProperty;
    private StringProperty DateAddedProperty;
    private StringProperty ClassificationProperty;
    private IntegerProperty QuantityProperty;
    private FloatProperty PriceProperty;

    public Books (){
        this.BIDProperty = new SimpleStringProperty();
        this.AuthorProperty = new SimpleStringProperty();
        this.TitleProperty = new SimpleStringProperty();
        this.PublisherProperty = new SimpleStringProperty();
        this.YearProperty = new SimpleStringProperty();
        this.PagesProperty = new SimpleIntegerProperty();
        this.LanguageProperty = new SimpleStringProperty();
        this.TopicProperty = new SimpleStringProperty();
        this.ISBNProperty = new SimpleStringProperty();
        this.DateAddedProperty = new SimpleStringProperty();
        this.ClassificationProperty = new SimpleStringProperty();
        this.QuantityProperty = new SimpleIntegerProperty();
        this.PriceProperty = new SimpleFloatProperty();

    }

    // SETTER AND GETTER METHODS:




    public String getAuthorProperty() {
        return AuthorProperty.get();
    }

    public StringProperty authorPropertyProperty() {
        return AuthorProperty;
    }

    public void setAuthorProperty(String authorProperty) {
        this.AuthorProperty.set(authorProperty);
    }

    public String getTitleProperty() {
        return TitleProperty.get();
    }

    public StringProperty titlePropertyProperty() {
        return TitleProperty;
    }

    public void setTitleProperty(String titleProperty) {
        this.TitleProperty.set(titleProperty);
    }

    public String getPublisherProperty() {
        return PublisherProperty.get();
    }

    public StringProperty publisherPropertyProperty() {
        return PublisherProperty;
    }

    public void setPublisherProperty(String publisherProperty) {
        this.PublisherProperty.set(publisherProperty);
    }

    public String getYearProperty() {
        return YearProperty.get();
    }

    public StringProperty yearPropertyProperty() {
        return YearProperty;
    }

    public void setYearProperty(String yearProperty) {
        this.YearProperty.set(yearProperty);
    }

    public int getPagesProperty() {
        return PagesProperty.get();
    }

    public IntegerProperty pagesPropertyProperty() {
        return PagesProperty;
    }

    public void setPagesProperty(int pagesProperty) {
        this.PagesProperty.set(pagesProperty);
    }

    public String getLanguageProperty() {
        return LanguageProperty.get();
    }

    public StringProperty languagePropertyProperty() {
        return LanguageProperty;
    }

    public void setLanguageProperty(String languageProperty) {
        this.LanguageProperty.set(languageProperty);
    }

    public String getTopicProperty() {
        return TopicProperty.get();
    }

    public StringProperty topicPropertyProperty() {
        return TopicProperty;
    }

    public void setTopicProperty(String topicProperty) {
        this.TopicProperty.set(topicProperty);
    }

    public String getISBNProperty() {
        return ISBNProperty.get();
    }

    public StringProperty ISBNPropertyProperty() {
        return ISBNProperty;
    }

    public void setISBNProperty(String ISBNProperty) {
        this.ISBNProperty.set(ISBNProperty);
    }

    public String getDateAddedProperty() {
        return DateAddedProperty.get();
    }

    public StringProperty dateAddedPropertyProperty() {
        return DateAddedProperty;
    }

    public void setDateAddedProperty(String dateAddedProperty) {
        this.DateAddedProperty.set(dateAddedProperty);
    }

    public String getClassificationProperty() {
        return ClassificationProperty.get();
    }

    public StringProperty classificationPropertyProperty() {
        return ClassificationProperty;
    }

    public void setClassificationProperty(String classificationProperty) {
        this.ClassificationProperty.set(classificationProperty);
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

    public String getBIDProperty() {
        return BIDProperty.get();
    }

    public StringProperty BIDPropertyProperty() {
        return BIDProperty;
    }

    public void setBIDProperty(String BIDProperty) {
        this.BIDProperty.set(BIDProperty);
    }
}
