package View_Controller;

import Model.InHouse;
import Model.Outsourced;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifyPartFormController {

    @FXML
    private TitledPane modifyPartForm;

    @FXML
    private ToggleGroup sourceToggleGroup;

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
    private TextField partMinTextField;

    @FXML
    private TextField mIDcompanyNameTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    void inHouseSelected(ActionEvent event) {

        mIDcompanyNameLabel.setText("Machine ID");

    }

    @FXML
    void outSourcedSelected(ActionEvent event) {

        mIDcompanyNameLabel.setText("Company Name");

    }

    @FXML
    void modifyPart(ActionEvent event) {

    }

    @FXML
    void returnToMain(ActionEvent event) throws IOException {

        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();

    }

    public void sendPart (Part part) {
        partIDTextField.setText(String.valueOf(part.getPartID()));
        partNameTextField.setText(part.getPartName());
        partPriceTextField.setText(String.valueOf(part.getPartPrice()));
        partStockTextField.setText(String.valueOf(part.getPartStock()));
        partMinTextField.setText(String.valueOf(part.getPartMin()));
        partMaxTextField.setText(String.valueOf(part.getPartMax()));

        if (part instanceof InHouse) {
            inHouseSelection.setSelected(true);
            mIDcompanyNameLabel.setText("Machine ID");
            mIDcompanyNameTextField.setText(String.valueOf(((InHouse) part).getMachineID()));
        }
        else if (part instanceof Outsourced) {
            outSourcedSelection.setSelected(true);
            mIDcompanyNameLabel.setText("Company Name");
            mIDcompanyNameTextField.setText(((Outsourced) part).getCompanyName());
        }

    }

}
