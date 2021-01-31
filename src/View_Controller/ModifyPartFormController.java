package View_Controller;

import Model.InHouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static Model.Inventory.getAllParts;
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

    @FXML
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
        int partStock = Integer.parseInt(partStockTextField.getText());
        double partPrice = Double.parseDouble(partPriceTextField.getText());
        int partMin = Integer.parseInt(partMinTextField.getText());
        int partMax = Integer.parseInt(partMaxTextField.getText());
        String mIDcompanyName = mIDcompanyNameTextField.getText();

        if (inHouseSelection.isSelected()) {

            if (Inventory.lookupPart(partID) instanceof InHouse) {
                InHouse updatedPart = (InHouse) Inventory.lookupPart(partID);
                assert updatedPart != null;
                updatedPart.setPartName(partName);
                updatedPart.setPartPrice(partPrice);
                updatedPart.setPartStock(partStock);
                updatedPart.setPartMin(partMin);
                updatedPart.setPartMax(partMax);
                updatedPart.setMachineID(Integer.parseInt(mIDcompanyName));
            }
            else {
                InHouse updatedPart = new InHouse (partID, partName, partPrice, partStock, partMin, partMax, Integer.parseInt(mIDcompanyName));
                updatePart(partIndex, updatedPart);
            }
        }
        else if (outSourcedSelection.isSelected()) {

            if (Inventory.lookupPart(partID) instanceof Outsourced) {
                Outsourced updatedPart = (Outsourced) Inventory.lookupPart(partID);
                assert updatedPart != null;
                updatedPart.setPartName(partName);
                updatedPart.setPartPrice(partPrice);
                updatedPart.setPartStock(partStock);
                updatedPart.setPartMin(partMin);
                updatedPart.setPartMin(partMax);
                updatedPart.setCompanyName(mIDcompanyName);
            }
            else {
                Outsourced updatedPart = new Outsourced(partID, partName, partPrice, partStock, partMin, partMax, mIDcompanyName);
                updatePart(partIndex, updatedPart);
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
