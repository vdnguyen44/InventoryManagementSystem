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

/**
 * @author Vincent Nguyen
 * Controller for ModifyPartForm
 */
public class ModifyPartFormController {

    /**
     *The radio button that indicates a part is made in house.
     */
    @FXML
    private RadioButton inHouseSelection;

    /**
     * The radio button that indicates a part was outsourced.
     */
    @FXML
    private RadioButton outSourcedSelection;

    /**
     * The text label that changes depending on the radio button selected.
     */
    @FXML
    private Label mIDcompanyNameLabel;

    /**
     * The text field initialized with the part's ID.
     */
    @FXML
    private TextField partIDTextField;

    /**
     * The text field to edit a part's name.
     */
    @FXML
    private TextField partNameTextField;

    /**
     * The text field to edit a part's inventory level.
     */
    @FXML
    private TextField partStockTextField;

    /**
     * The text field to edit a part's price.
     */
    @FXML
    private TextField partPriceTextField;

    /**
     * The text field to edit a part's maximum inventory.
     */
    @FXML
    private TextField partMaxTextField;

    /**
     * The text field to edit a part's minimum inventory.
     */
    @FXML
    private TextField partMinTextField;

    /**
     * The text field to edit a part's machine ID or outsourced company's name.
     */
    @FXML
    private TextField mIDcompanyNameTextField;

    /**
     * The variable that holds the part's ID.
     */
    private int partID;

    /**
     * The variable that references the part's position in inventory.
     */
    private int partIndex;

    /**
     * This method changes the label's text to "Machine ID".
     * @param event When the inHouse radio button is selected.
     */
    @FXML
    void inHouseSelected(ActionEvent event) {
        mIDcompanyNameLabel.setText("Machine ID");
    }

    /**
     * This method changes the label's text to "Company Name".
     * @param event When the outSourced radio button is selected.
     */
    @FXML
    void outSourcedSelected(ActionEvent event) {
        mIDcompanyNameLabel.setText("Company Name");
    }

    /**
     * <p>This method extracts the values from the text fields and checks whether the part is valid. If the error list is
     * not empty, the user is prompted with an error dialog with all errors to fix. If all values are valid, the part's
     * new values are set. Depending on the subclass the part belonged to, and the subclass of it's edited version,
     * the existing part is edited or replaced with an instance of a new part object.</p>
     * @param event When the save button is pressed.
     * @throws IOException Exception thrown if the main form fxml cannot be located.
     */
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

        // Check for part errors
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

            // Edit the existing part, or create a new part and replace the old part
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

        // Return to main form
        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    /**
     * This method changes the scene back to the main form.
     * @param event When the cancel button is pressed.
     * @throws IOException Exception thrown if the main form fxml cannot be located.
     */
    @FXML
    void modifyPartCancelBtn(ActionEvent event) throws IOException {

        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();

    }

    /**
     * This method initializes the values in the text fields and the user can decide whether to edit the values.
     * @param part The selected part from the parts table view
     */
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
