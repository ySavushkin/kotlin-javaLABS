package com.example.lab5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.lab5.model.Person;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Person> personData=
            FXCollections.observableArrayList();
    /**
     * Конструктор
     */
    public MainApp(){
        //Як зразок додаємо деякі дані
        personData.add(new Person("Петро","П'яточкін"));
        personData.add(new Person("Іван","Зайців"));
        personData.add(new Person("Катерина","Васильченка"));
        personData.add(new Person("Ольга","Жук"));
        personData.add(new Person("Людміла","Алексєєва"));
        personData.add(new Person("Даніл","Кац"));
        personData.add(new Person("Євген","Васнецов"));
        personData.add(new Person("Дмитро","Жуликів"));
        personData.add(new Person("Мрат","Алібов"));
        personData.add(new Person("Martin","Mueller"));
    }
    /**
     * Повертає дані у вигляді спостережуваного списку студентів.
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.primaryStage.setTitle("StudentGroupApp");
        initRootLayout();
        showPersonOverview();
    }

    public void initRootLayout() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("RootLayout.fxml"));
        try {
            rootLayout = (BorderPane) fxmlLoader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showPersonOverview() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(MainApp.class.getResource("PersonOverview.fxml"));
        try {
            AnchorPane personOverview = fxmlLoader.load();
            rootLayout.setCenter(personOverview);
            PersonOverviewController controller=fxmlLoader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Відкриває діалогове вікно зміни даних зазначеного студента.
     * Якщо користувач натиснув OK, то зміни зберігаються у наданому
     * об'єкт студента і повертається значення true.
     *
     * @paramperson – об'єкт студента, який треба змінити
     * @returntrue, якщо користувач натиснув OK, інакше false.
     */
    public boolean showPersonEditDialog(Person person) {
        try{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonEditDialog.fxml"));
            AnchorPane page= loader.load();
            Stage dialogStage=new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene=new Scene(page);
            dialogStage.setScene(scene);
            PersonEditDialogController controller=loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        }catch(IOException e) {e.printStackTrace(); return false;
        }
    }

}