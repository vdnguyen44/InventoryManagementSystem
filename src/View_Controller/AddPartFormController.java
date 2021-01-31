package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
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
    private TextField partMinTextField;

    @FXML
    private TextField mIDcompanyNameTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private static int partCount = 1;

    @FXML
    void addPartBtn(ActionEvent event) throws IOException {

        int partID = partCount;
        String partName = partNameTextField.getText();
        int partStock = Integer.parseInt(partStockTextField.getText());
        double partPrice = Double.parseDouble(partPriceTextField.getText());
        int partMax = Integer.parseInt(partMaxTextField.getText());
        int partMin = Integer.parseInt(partMinTextField.getText());
        int machineID;
        String companyName;

        if (inHouseSelection.isSelected()) {
            machineID = Integer.parseInt(mIDcompanyNameTextField.getText());
            Inventory.addPart(new InHouse(partID, partName, partPrice, partStock, partMin, partMax, machineID));
        }
        else if (outSourcedSelection.isSelected()) {
            companyName = mIDcompanyNameTextField.getText();
            Inventory.addPart(new Outsourced(partID, partName, partPrice, partStock, partMin, partMax, companyName));
        }

        partCount ++;


        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();




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
    void addPartCancelBtn(ActionEvent event) throws IOException {
        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

}
