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
import java.util.List;

import static Model.Inventory.updatePart;


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

    private int partID;

    private int partIndex;

    @FXML
    void inHouseSelected(ActionEvent event) {

        mIDcompanyNameLabel.setText("Machine ID");

    }

    @FXML
    void outSourcedSelected(ActionEvent event) {

        mIDcompanyNameLabel.setText("Company Name");

    }

    @FXML
    void modifyPartBtn(ActionEvent event) throws IOException {

        String partName = partNameTextField.getText();
        String partStock = partStockTextField.getText();
        String partPrice = partPriceTextField.getText();
        String partMin = partMinTextField.getText();
        String partMax = partMaxTextField.getText();
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

                if (Inventory.lookupPart(partID) instanceof InHouse) {
                    InHouse updatedPart = (InHouse) Inventory.lookupPart(partID);
                    assert updatedPart != null;
                    updatedPart.setPartName(partName);
                    updatedPart.setPartPrice(partPriceParsed);
                    updatedPart.setPartStock(partStockParsed);
                    updatedPart.setPartMin(partMinParsed);
                    updatedPart.setPartMax(partMaxParsed);
                    updatedPart.setMachineID(machineID);
                } else {
                    InHouse updatedPart = new InHouse(partID, partName, partPriceParsed, partStockParsed, partMinParsed, partMaxParsed, machineID);
                    updatePart(partIndex, updatedPart);
                }
            } else if (outSourcedSelection.isSelected()) {
                String companyName = mIDcompanyNameTextField.getText();

                if (Inventory.lookupPart(partID) instanceof Outsourced) {
                    Outsourced updatedPart = (Outsourced) Inventory.lookupPart(partID);
                    assert updatedPart != null;
                    updatedPart.setPartName(partName);
                    updatedPart.setPartPrice(partPriceParsed);
                    updatedPart.setPartStock(partStockParsed);
                    updatedPart.setPartMin(partMinParsed);
                    updatedPart.setPartMin(partMaxParsed);
                    updatedPart.setCompanyName(companyName);
                } else {
                    Outsourced updatedPart = new Outsourced(partID, partName, partPriceParsed, partStockParsed, partMinParsed, partMaxParsed, companyName);
                    updatePart(partIndex, updatedPart);
                }
            }
        }

        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();



    }

    @FXML
    void modifyPartCancelBtn(ActionEvent event) throws IOException {

        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();

    }

    public void initializePartData (Part part) {
        partID = part.getPartID();
        partIndex = Inventory.getAllParts().indexOf(part);
        partIDTextField.setText(String.valueOf(part.getPartID()));
        partNameTextField.setText(part.getPartName());
        partPriceTextField.setText(String.valueOf(part.getPartPrice()));
        partStockTextField.setText(String.valueOf(part.getPartStock()));
        partMaxTextField.setText(String.valueOf(part.getPartMax()));
        partMinTextField.setText(String.valueOf(part.getPartMin()));


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
