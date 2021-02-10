package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;


/**
 * @author Vincent Nguyen
 * Controller for ModifyProductForm
 */
public class ModifyProductFormController {

    /**
     * The text field initialized with the product's ID.
     */
    @FXML
    private TextField productIDTextField;

    /**
     * The text field to edit a product's name.
     */
    @FXML
    private TextField productNameTextField;

    /**
     * The text field to edit a product's inventory level.
     */
    @FXML
    private TextField productStockTextField;

    /**
     * The text field to edit a product's price.
     */
    @FXML
    private TextField productPriceTextField;

    /**
     * The text field to edit a product's max inventory.
     */
    @FXML
    private TextField productMaxTextField;

    /**
     * The text field to edit a product's min inventory.
     */
    @FXML
    private TextField productMinTextField;

    /**
     * The table view of all available parts in inventory.
     */
    @FXML
    private TableView<Part> availablePartsTable;

    /**
     * The column in the available parts table that represents a part's ID.
     */
    @FXML
    private TableColumn<Part, Integer> availablePartIDCol;

    /**
     * The column in the available parts table that represents a part's name.
     */
    @FXML
    private TableColumn<Part, String> availablePartNameCol;

    /**
     * The column in the available parts table that represents a part's inventory level.
     */
    @FXML
    private TableColumn<Part, Integer> availablePartStockCol;

    /**
     * The column in the available parts table that represents a part's price.
     */
    @FXML
    private TableColumn<Part, Double> availablePartPriceCol;

    /**
     * The text field used to search for a part.
     */
    @FXML
    private TextField searchPartTextField;

    /**
     * The table view that holds the initial product's associated parts.
     */
    @FXML
    private TableView<Part> associatedPartsTable;

    /**
     * The column in the associated parts table that represents a part's ID.
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartIDCol;

    /**
     * The column in the associated parts table that represents a part's name.
     */
    @FXML
    private TableColumn<Part, String> associatedPartNameCol;

    /**
     * The column in the associated parts table that represents a part's inventory level.
     */
    @FXML
    private TableColumn<Part, Integer> associatedPartStockCol;

    /**
     * The column in the associated parts table that represents a part's price.
     */
    @FXML
    private TableColumn<Part, Double> associatedPartPriceCol;

    /**
     * The variable that holds the product's ID.
     */
    private int productID;

    /**
     * The variable that references the product's position in inventory.
     */
    private int productIndex;

    /**
     * The variable that holds the existing product's associated parts.
     */
    public ObservableList<Part> productAssociatedParts;

    /**
     * A variable that holds the same contents as the existing product's associated parts on initialization.
     */
    public ObservableList<Part> copyAssociatedParts = FXCollections.observableArrayList();


    /**
     * This method searches the available parts table by ID or name after the search button is pressed.
     * @param event When the search button is pressed.
     */
    @FXML
    void searchAvailablePartsBtn(ActionEvent event) {
        searchAvailablePartsTable();
    }

    /**
     * This method searches the available parts table by ID or name after the enter key is pressed.
     * @param event When the enter key is pressed.
     */
    @FXML
    void searchAvailablePartsTableEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)  {
            searchAvailablePartsTable();
        }
    }

    /**
     * <p>This method adds the selected part from the available parts table to the copy of associated parts table.
     * Error dialogs are generated if no part is selected or if the part is already associated with the product.</p>
     * @param event When the add button is pressed.
     */
    @FXML
    void addPartToAssociatedParts(ActionEvent event) {
        Part selectedPart = availablePartsTable.getSelectionModel().getSelectedItem();

        if (availablePartsTable.getSelectionModel().isEmpty()) {
            Alert noneSelectedAlert = new Alert(Alert.AlertType.ERROR);
            noneSelectedAlert.setTitle("Part Selection Error");
            noneSelectedAlert.setHeaderText("No part is selected.");
            noneSelectedAlert.show();
        }
        else if (copyAssociatedParts.contains(selectedPart)) {
            Alert duplicateAlert = new Alert(Alert.AlertType.ERROR);
            duplicateAlert.setTitle("Duplicate Part Error");
            duplicateAlert.setHeaderText("The part has already been added, can only be added once.");
            duplicateAlert.show();
        }
        else {
            copyAssociatedParts.add(selectedPart);
        }
        availablePartsTable.getSelectionModel().clearSelection();
    }

    /**
     * This method removes the selected part from the copy of associated parts table. An error dialog is generated if
     * no part is selected, and a confirmation dialog to confirm the removal of the part.
     * @param event When the remove button is pressed.
     */
    @FXML
    void removeAssociatedPartBtn(ActionEvent event) {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();

        if (copyAssociatedParts.isEmpty()) {
            Alert emptyListAlert = new Alert(Alert.AlertType.ERROR);
            emptyListAlert.setTitle("Delete Part Error");
            emptyListAlert.setHeaderText("No part was deleted.");
            emptyListAlert.show();
        }

        else if (associatedPartsTable.getSelectionModel().isEmpty()) {
            Alert noneSelectedAlert = new Alert(Alert.AlertType.ERROR);
            noneSelectedAlert.setTitle("Part Selection Error");
            noneSelectedAlert.setHeaderText("No part is selected.");
            noneSelectedAlert.show();
        }
        else {
            Alert removeAlert = new Alert(Alert.AlertType.CONFIRMATION);
            removeAlert.setTitle("Removal Confirmation");
            removeAlert.setHeaderText("Are you sure you want to remove this part?");
            Optional<ButtonType> result = removeAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                copyAssociatedParts.remove(selectedPart);
            }
        }
    }

    /**
     *<p>This method attempts to create the product by extracting values from the text fields and checking whether the product is
     * valid. If the error list is not empty, the user is prompted with an error dialog with all errors to fix. The product
     * is updated with the new values and the existing product's associated parts is cleared before adding to it the copy
     * of associated parts. The product is updated at its position in inventory.</p>
     * <p>RUNTIME ERROR - Initially did not create a copy of the existing product's associated parts. This meant whenever changes were
     * made to the associated parts table were saved whether save/cancel were pressed. This is unintended as changes should
     * only be committed if save is pressed. Also the associated parts list had to be cleared before the contents of the
     * new list were added. If not cleared, there could be duplicate parts once saved.</p>
     * @param event When the save button is pressed.
     * @throws IOException Exception thrown if the main form fxml cannot be located.
     */
    @FXML
    void modifyProductBtn(ActionEvent event) throws IOException {

        String productName = productNameTextField.getText();
        String productStock = productStockTextField.getText();
        String productPrice = productPriceTextField.getText();
        String productMin = productMinTextField.getText();
        String productMax = productMaxTextField.getText();
        String errorMessage = "Product input is invalid. Please fix the following errors: \n";

        // Check for product errors
        List<String> productErrors = Product.productValidationCheck(productName, productPrice, productStock, productMin, productMax);

        for (String error : productErrors) {
            errorMessage = errorMessage + error + "\n";
        }

        if (productErrors.size() > 0) {
            Alert productErrorAlert = new Alert(Alert.AlertType.ERROR);
            productErrorAlert.setTitle("");
            productErrorAlert.setHeaderText("Product Validation Error");
            productErrorAlert.setContentText(errorMessage);
            productErrorAlert.showAndWait();
            return;
        }
        else {
            // Update the product with new values
            double productPriceParsed = Double.parseDouble(productPrice);
            int productStockParsed = Integer.parseInt(productStock);
            int productMinParsed = Integer.parseInt(productMin);
            int productMaxParsed = Integer.parseInt(productMax);

            Product updatedProduct = Inventory.lookupProduct(productID);
            assert updatedProduct != null;
            updatedProduct.setProductName(productName);
            updatedProduct.setProductStock(productStockParsed);
            updatedProduct.setProductPrice(productPriceParsed);
            updatedProduct.setProductMin(productMinParsed);
            updatedProduct.setProductMax(productMaxParsed);
            updatedProduct.getAllAssociatedParts().clear();
            updatedProduct.getAllAssociatedParts().addAll(copyAssociatedParts);
            Inventory.updateProduct(productIndex, updatedProduct);
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
     * @throws IOException Exception thrown if main form fxml cannot be located.
     */
    @FXML
    void modifyProductCancelBtn(ActionEvent event) throws IOException {
        // Discard changes to the copy part list
        copyAssociatedParts.clear();

        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    /**
     * <p>This method searches the available parts table by ID or name. It first searches by ID by attempting to parse the search query
     * into an integer. If a part's ID matches the parsed search query, it is added to the search result list and the available
     * parts table displays that list. If parsing the integer doesn't work, it attempts to search by string (partial/complete name).
     * Any partial/complete matches are added to a search result list and the table view displays those items. If the search query is empty
     * and the search button/enter is pressed, the available parts table is repopulated with all parts in inventory.</p>
     */
    void searchAvailablePartsTable() {
        String searchQuery = searchPartTextField.getText();
        ObservableList<Part> searchResult = FXCollections.observableArrayList();

        try {
            int queryInt = Integer.parseInt(searchQuery);

            if (Inventory.lookupPart(queryInt) == null) {
                Alert emptyResultAlert = new Alert(Alert.AlertType.ERROR);
                emptyResultAlert.setTitle("Search Empty");
                emptyResultAlert.setHeaderText("No results found.");
                emptyResultAlert.show();
            }
            else {
                searchResult.add(Inventory.lookupPart(queryInt));
                availablePartsTable.setItems(searchResult);
            }

        }
        catch (NumberFormatException e) {
            availablePartsTable.setItems(Inventory.lookupPart(searchQuery));

            if (Inventory.lookupPart(searchQuery).isEmpty()) {
                Alert emptyResultAlert = new Alert(Alert.AlertType.ERROR);
                emptyResultAlert.setTitle("Empty Search");
                emptyResultAlert.setHeaderText("No results found.");
                emptyResultAlert.show();
            }
        }
        finally {
            if (searchQuery.equals("")) {
                availablePartsTable.setItems(Inventory.getAllParts());
            }
        }
    }

    /**
     * This method initializes the values in the text fields and associated parts table and the user can decide whether
     * to edit the values or add/remove associated parts.
     * @param product The selected product from the products table view
     */
    public void initializeProductData(Product product) {

        productID = product.getProductID();
        productIndex = Inventory.getAllProducts().indexOf(product);
        productAssociatedParts = product.getAllAssociatedParts();
        copyAssociatedParts.addAll(productAssociatedParts);
        productIDTextField.setText(String.valueOf(product.getProductID()));
        productNameTextField.setText(product.getProductName());
        productStockTextField.setText(String.valueOf(product.getProductStock()));
        productPriceTextField.setText(String.valueOf(product.getProductPrice()));
        productMaxTextField.setText(String.valueOf(product.getProductMax()));
        productMinTextField.setText(String.valueOf(product.getProductMin()));

        // Initialize available parts table
        availablePartsTable.setItems(Inventory.getAllParts());
        // Set value of the cells
        availablePartIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        availablePartNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
        availablePartStockCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        availablePartPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        // Formats the cells to display prices accurately
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        availablePartPriceCol.setCellFactory(price -> new TableCell<Part, Double>() {

            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                }
                else {
                    setText(currencyFormatter.format(price));
                }
            }
        });
        // Initialize copy of associated parts table
        associatedPartsTable.setItems(copyAssociatedParts);
        // Set value of the cells
        associatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
        associatedPartStockCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        associatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        // Formats the cells to display prices accurately
        associatedPartPriceCol.setCellFactory(price -> new TableCell<Part, Double>() {

            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                }
                else {
                    setText(currencyFormatter.format(price));
                }
            }
        });
    }
}
