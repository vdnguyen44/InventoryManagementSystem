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

/**
 * @author Vincent Nguyen
 * Controller for AddPartForm
 */
public class AddPartFormController {

    /**
     * The radio button that indicates a part is made in house.
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
     * The text field to enter a part's name.
     */
    @FXML
    private TextField partNameTextField;

    /**
     * The text field to enter a part's inventory level.
     */
    @FXML
    private TextField partStockTextField;

    /**
     * The text field to enter a part's price.
     */
    @FXML
    private TextField partPriceTextField;

    /**
     * The text field to enter a part's maximum inventory.
     */
    @FXML
    private TextField partMaxTextField;

    /**
     * The text field to enter a part's minimum inventory.
     */
    @FXML
    private TextField partMinTextField;

    /**
     * The text field to enter a part's machine ID or outsourced company's name.
     */
    @FXML
    private TextField mIDcompanyNameTextField;

    /**
     * <p>This variable keeps track of how many parts have been created, but increments each time a part is created so
     * no part will have the same ID.</p>
     * <p>RUNTIME ERROR - Variable wasn't updating each time a part was created, so I initialized it outside the
     * method's scope.</p>
     */
    private static int partCount = 1;

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
     * <p>This method attempts to create the part by extracting values from the text fields and checking whether the part is
     * valid. If the error list is not empty, the user is prompted with an error dialog with all errors to fix. If all values
     * are valid, the appropriate values are parsed and the part object is instantiated with those values. The part count is
     * then incremented.</p>
     * <p>FUTURE ENHANCEMENT - Generate a random unique ID of 6 or more digits. </p>
     * @param event When save button is pressed
     * @throws IOException Exception thrown if main form fxml cannot be located.
     */
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
            // Create the part with parsed values and add it to inventory
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

        // Return to Main form
        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    /**
     * This method changes the scene back to the main form.
     * @param event When cancel button is pressed.
     * @throws IOException Exception thrown if main form fxml cannot be located.
     */
    @FXML
    void addPartCancelBtn(ActionEvent event) throws IOException {
        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }
}
