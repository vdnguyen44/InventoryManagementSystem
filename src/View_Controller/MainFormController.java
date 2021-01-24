package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

import java.io.IOException;

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
    void deletePartFromTable(ActionEvent event) {

    }

    @FXML
    void deleteProductFromTable(ActionEvent event) {

    }

    @FXML
    void displayAddPartForm(ActionEvent event) throws IOException {
        Parent addPartFormLoader = FXMLLoader.load(getClass().getResource("AddPartForm.fxml"));
        Scene addPartScene = new Scene(addPartFormLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

    @FXML
    void displayAddProductForm(ActionEvent event) {

    }

    @FXML
    void displayModifyPartForm(ActionEvent event) {

    }

    @FXML
    void displayModifyProductForm(ActionEvent event) {

    }

    @FXML
    void exitApplication(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void searchPartsTable(ActionEvent event) {

    }

    @FXML
    void searchProductsTable(ActionEvent event) {

    }

}
