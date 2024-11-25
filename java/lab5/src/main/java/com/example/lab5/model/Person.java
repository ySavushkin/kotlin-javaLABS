package com.example.lab5.model;

import javafx.beans.property.*;
import java.time.LocalDate;

/**
 * Клас модель для студента (Person).
 */
public class Person {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final IntegerProperty groupCode;
    private final ObjectProperty<LocalDate> birthday;
    private final DoubleProperty averageGrade;  //Додав ще одне поле з оцінкою студента

    /**
     * Конструктор за замовчуванням.
     */
    public Person() {
        this(null, null);
    }

    /**
     * Конструктор із деякими початковими даними.
     *
     * @param firstName Ім'я
     * @param lastName  Прізвище
     */
    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        this.groupCode = new SimpleIntegerProperty(1747);
        this.birthday = new SimpleObjectProperty<>(LocalDate.of(2000, 2, 21));
        this.averageGrade = new SimpleDoubleProperty(85.0);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public int getGroupCode() {
        return groupCode.get();
    }

    public void setGroupCode(int groupCode) {
        this.groupCode.set(groupCode);
    }

    public IntegerProperty groupCodeProperty() {
        return groupCode;
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public double getAverageGrade() {
        return averageGrade.get();
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade.set(averageGrade);
    }

    public DoubleProperty averageGradeProperty() {
        return averageGrade;
    }
}
