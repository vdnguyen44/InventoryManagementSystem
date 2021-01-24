package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;


public class AddPartFormController {

    @FXML
    private TitledPane addPartForm;

    @FXML
    private RadioButton inHouseSelection;

    @FXML
    private ToggleGroup sourceToggleGroup;

    @FXML
    private RadioButton outSourcedSelection;

    @FXML
    private Label mIDcompanyNameLabel;

    @FXML
    private TextField partIDTextField;

    @FXML
    private TextField partNameTextField;

    @FXML
    private TextField partStockTextField;

    @FXML
    private TextField partPriceTextField;

    @FXML
    private TextField partMaxTextField;

    @FXML
    private TextField mIDcompanyNameTextField;

    @FXML
    private TextField partMinTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    void addPart(ActionEvent event) {

    }

    @FXML
    void inHouseSelected(ActionEvent event) {
        mIDcompanyNameLabel.setText("Machine ID");
    }

    @FXML
    void outSourcedSelected(ActionEvent event) {
        mIDcompanyNameLabel.setText("Company Name");
    }

    @FXML
    void returnToMain(ActionEvent event) throws IOException {
        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene addPartScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

}
