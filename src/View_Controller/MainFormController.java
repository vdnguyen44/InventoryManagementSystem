package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class MainFormController {

    @FXML
    private TitledPane mainForm;

    @FXML
    private TableView<?> partsTable;

    @FXML
    private Button addPartButton;

    @FXML
    private Button modifyPartButton;

    @FXML
    private Button deletePartButton;

    @FXML
    private TextField searchPartTextField;

    @FXML
    private Button searchPartButton;

    @FXML
    private TableView<?> productsTable;

    @FXML
    private Button addProductButton;

    @FXML
    private Button modifyProductButton;

    @FXML
    private Button deleteProductButton;

    @FXML
    private TextField searchProductTextField;

    @FXML
    private Button searchProductButton;

    @FXML
    private Button exitButton;

    @FXML
    void addPartButtonPressed(ActionEvent event) {

    }

    @FXML
    void addProductButtonPressed(ActionEvent event) {

    }

    @FXML
    void deletePartButtonPressed(ActionEvent event) {

    }

    @FXML
    void deleteProductButtonPressed(ActionEvent event) {

    }

    @FXML
    void exitButtonPressed(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void modifyPartButtonPressed(ActionEvent event) {

    }

    @FXML
    void modifyProductButtonPressed(ActionEvent event) {

    }

    @FXML
    void searchPartButtonPressed(ActionEvent event) {

    }

    @FXML
    void searchProductButtonPressed(ActionEvent event) {

    }

}
