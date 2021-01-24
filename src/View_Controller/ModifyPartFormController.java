package View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class ModifyPartFormController {

    @FXML
    private TitledPane modifyPartForm;

    @FXML
    private RadioButton inHouseSelection;

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
    void inHouseSelected(ActionEvent event) {

    }

    @FXML
    void outSourcedSelected(ActionEvent event) {

    }

    @FXML
    void modifyPart(ActionEvent event) {

    }

    @FXML
    void returnToMain(ActionEvent event) {

    }

}
