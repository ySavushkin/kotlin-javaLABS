package com.example.lab5;

import com.example.lab5.model.Person;
import com.example.lab5.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField groupCodeField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField averageGradeField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Ініціалізує клас-контролер. Цей метод викликається автоматично
     * після того, як файл fxml буде завантажений.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Встановлює сцену цього вікна.
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Задає студента, інформацію про якого змінюватимемо.
     */
    public void setPerson(Person person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        groupCodeField.setText(String.valueOf(person.getGroupCode()));
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
        averageGradeField.setText(String.valueOf(person.getAverageGrade()));
    }

    /**
     * Повертає true, якщо користувач клацнув OK, інакше false.
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Викликається, коли користувач клацнув по кнопці OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setGroupCode(Integer.parseInt(groupCodeField.getText()));
            person.setBirthday(DateUtil.parse(birthdayField.getText()));
            person.setAverageGrade(Double.parseDouble(averageGradeField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Викликається, коли користувач клацнув по кнопці Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Перевіряє введення тексту в текстових полях.
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().isEmpty()) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().isEmpty()) {
            errorMessage += "No valid last name!\n";
        }
        if (groupCodeField.getText() == null || groupCodeField.getText().isEmpty()) {
            errorMessage += "No valid group code!\n";
        } else {
            try {
                Integer.parseInt(groupCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid group code (must be an integer)!\n";
            }
        }
        if (birthdayField.getText() == null || birthdayField.getText().isEmpty()) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }
        if (averageGradeField.getText() == null || averageGradeField.getText().isEmpty()) {
            errorMessage += "No valid average grade!\n";
        } else {
            try {
                Double.parseDouble(averageGradeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid average grade (must be a double)!\n";
            }
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
