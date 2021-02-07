package View_Controller;

import Model.InHouse;
import Model.Inventory;
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
import java.util.ArrayList;
import java.util.List;


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
    void inHouseSelected(ActionEvent event) {

        mIDcompanyNameLabel.setText("Machine ID");

    }

    @FXML
    void outSourcedSelected(ActionEvent event) {

        mIDcompanyNameLabel.setText("Company Name");
    }

    @FXML
    void addPartBtn(ActionEvent event) throws IOException {

        int partID = partCount;
        String partName = partNameTextField.getText();
        String partStock = partStockTextField.getText();
        String partPrice = partPriceTextField.getText();
        String partMax = partMaxTextField.getText();
        String partMin = partMinTextField.getText();
        String mIDcompanyName = mIDcompanyNameTextField.getText();
        int machineID = 0;
        String errorMessage = "Part input is invalid. Please fix the following errors: \n";

        List<String> partErrors = Part.partValidationCheck(partName, partPrice, partStock, partMin, partMax, mIDcompanyName);

        if (inHouseSelection.isSelected() && !mIDcompanyName.isEmpty()) {
            try {
                machineID = Integer.parseInt(mIDcompanyName);
            }
            catch (NumberFormatException e) {
                partErrors.add("The Machine ID entered is invalid.");
            }
        }

        for (String error : partErrors) {
            errorMessage = errorMessage + error + "\n";
        }

        if (partErrors.size() > 0) {
            Alert partErrorAlert = new Alert(Alert.AlertType.ERROR);
            partErrorAlert.setTitle("");
            partErrorAlert.setHeaderText("Part Validation Error");
            partErrorAlert.setContentText(errorMessage);
            partErrorAlert.showAndWait();
            return;
        }
        else {
            double partPriceParsed = Double.parseDouble(partPrice);
            int partStockParsed = Integer.parseInt(partStock);
            int partMinParsed = Integer.parseInt(partMin);
            int partMaxParsed = Integer.parseInt(partMax);

            if (inHouseSelection.isSelected()) {
                Inventory.addPart(new InHouse(partID, partName, partPriceParsed, partStockParsed, partMinParsed, partMaxParsed, machineID));
            } else if (outSourcedSelection.isSelected()) {
                String companyName = mIDcompanyNameTextField.getText();
                Inventory.addPart(new Outsourced(partID, partName, partPriceParsed, partStockParsed, partMinParsed, partMaxParsed, companyName));
            }
            partCount++;
        }


        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();

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
