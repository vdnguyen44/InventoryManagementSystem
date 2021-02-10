package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
// java docs files located in /InventoryManagementSystem

/**
 * @author Vincent Nguyen
 */

public class Main extends Application {

    /**
     * <p>The start class loads the fxml markup of the main form, and sets the title/window size of the javafx application.</p>
     * @param primaryStage The main stage where scenes are set.
     * @throws Exception Exception thrown if the main form fxml cannot be located.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainForm.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 1270, 555));
        primaryStage.show();
    }

    /**
     * Initiates the javafx application
     * @param args Array of string objects
     */
    public static void main(String[] args) {
        launch(args);}
}
