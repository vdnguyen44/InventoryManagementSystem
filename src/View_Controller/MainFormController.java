package View_Controller;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Vincent Nguyen
 * Controller for the main form.
 */
public class MainFormController implements Initializable {

    /**
     * The table view that displays all parts in inventory.
     */
    @FXML
    private TableView<Part> partsTable;

    /**
     * The column in the parts table that represents the part's ID.
     */
    @FXML
    private TableColumn<Part, Integer> partIDCol;

    /**
     * The column in the parts table that represents the part's name.
     */
    @FXML
    private TableColumn<Part, String> partNameCol;

    /**
     * The column in the parts table that represents the part's inventory level.
     */
    @FXML
    private TableColumn<Part, Integer> partStockCol;

    /**
     * The column in the parts table that represents the part's price.
     */
    @FXML
    private TableColumn<Part, Double> partPriceCol;

    /**
     * The text field used to search for a part.
     */
    @FXML
    private TextField searchPartTextField;

    /**
     * The table view that displays all products in inventory.
     */
    @FXML
    private TableView<Product> productsTable;

    /**
     * The column in the products table that represents the product's ID.
     */
    @FXML
    private TableColumn<Product, Integer> productIDCol;

    /**
     * The column in the products table that represents the product's name.
     */
    @FXML
    private TableColumn<Product, String> productNameCol;

    /**
     * The column in the products table that represents the product's inventory level.
     */
    @FXML
    private TableColumn<Product, Integer> productStockCol;

    /**
     * The column in the products table that represents the product's price.
     */
    @FXML
    private TableColumn<Product, Double> productPriceCol;

    /**
     * The text field used to search for a product.
     */
    @FXML
    private TextField searchProductTextField;

    /**
     * The button that closes the application.
     */
    @FXML
    private Button exitButton;

    /**
     *<p>This method changes the scene and displays the Add Part Form.</p>
     *<p>RUNTIME ERROR - Controller class had to be specified in fxml file in order to connect view and controller.</p>
     * @param event When the add button is pressed.
     * @throws IOException Exception thrown if the AddPartForm fxml cannot be located.
     */
    @FXML
    void displayAddPartBtn(ActionEvent event) throws IOException {
        Parent addPartFormLoader = FXMLLoader.load(getClass().getResource("AddPartForm.fxml"));
        Scene addPartScene = new Scene(addPartFormLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

    /**
     * <p>This method changes the scene and displays the Modify Part Form. The view and it's controller are obtained. Then
     * the selected part's data is sent to the form to initialize it's fields. An error dialog is provided if no part
     * is selected from the parts table view.</p>
     * @param event When the modify button is pressed.
     * @throws IOException Exception thrown if the ModifyPartForm fxml cannot be located.
     */
    @FXML
    void displayModifyPartBtn(ActionEvent event) throws IOException {
        try
        {
            // Obtain View
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyPartForm.fxml"));
            Parent partsTableParent = loader.load();
            Scene modifyPartScene = new Scene(partsTableParent);

            // Obtain respective controller
            // sending to modifyPartBtn didn't work because that method requires output of event but part is provided
            ModifyPartFormController ModPartController = loader.getController();
            ModPartController.initializePartData(partsTable.getSelectionModel().getSelectedItem());

            // Display the modify part form
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(modifyPartScene);
            window.show();
        }
        catch (NullPointerException e)
        {
            Alert noneSelectedAlert = new Alert(Alert.AlertType.ERROR);
            noneSelectedAlert.setTitle("");
            noneSelectedAlert.setHeaderText("Part Selection Error");
            noneSelectedAlert.setContentText("No part is selected.");
            noneSelectedAlert.show();
        }
    }

    /**
     * <p>This method deletes the selected part from the parts table. It checks for whether a part is selected and gives
     * an error dialog if no part is selected. If a part is selected, the user is asked to confirm whether they would
     * like to proceed and delete the part. Only if the user presses OK, is the part deleted.</p>
     * <p>RUNTIME ERROR - Initially had an if statement that checked the value of deletePart. It ended up being too
     * ambiguous and when setting up confirmation dialog to delete part, the part would be deleted regardless of whether
     * the user presses OK or cancel. Fixed the issue by having more clear cases in the if statements.</p>
     * @param event When the delete button is pressed.
     */
    @FXML
    void deletePartBtn(ActionEvent event) {

        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();

        if (partsTable.getSelectionModel().isEmpty()) {
                Alert noneSelectedAlert = new Alert(Alert.AlertType.ERROR);
                noneSelectedAlert.setTitle("");
                noneSelectedAlert.setHeaderText("Part Selection Error");
                noneSelectedAlert.setContentText("No part is selected.");
                noneSelectedAlert.show();
        }
        else  {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("");
            deleteAlert.setHeaderText("Deletion Confirmation");
            deleteAlert.setContentText("Are you sure you want to delete this part?");
            Optional<ButtonType> result = deleteAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }

    /**
     * This method searches the part table by ID or name after the search button is pressed.
     * @param event When search button is pressed.
     */
    @FXML
    void searchPartsTableBtn(ActionEvent event) {
        searchPartsTable();
    }

    /**
     * This method searches the part table by ID or name after the enter key is pressed.
     * @param event When the enter key is pressed.
     */
    @FXML
    void searchPartsTableEnter (KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)  {
            searchPartsTable();
        }
    }

    /**
     * This method changes the scene and displays the add product form.
     * @param event When the add button is pressed.
     * @throws IOException Exception thrown if the AddProductForm fxml cannot be located.
     */
    @FXML
    void displayAddProductBtn(ActionEvent event) throws IOException {

        Parent addProductFormLoader = FXMLLoader.load(getClass().getResource("AddProductForm.fxml"));
        Scene addProductScene = new Scene(addProductFormLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();

    }

    /**
     * <p>This method changes the scene and displays the Modify Product Form. The view and it's controller are obtained.
     * Then the selected product's data is sent to the form to initialize it's fields. An error dialog is provided if no product
     * is selected from the products table view.</p>
     * @param event When the modify button is pressed.
     * @throws IOException Exception thrown if the ModifyProductForm fxml cannot be located.
     */
    @FXML
    void displayModifyProductBtn(ActionEvent event) throws IOException {

        try
        {
            // Obtain the view
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyProductForm.fxml"));
            Parent productsTableParent = loader.load();
            Scene modifyProductScene = new Scene(productsTableParent);

            // Obtain respective controller
            ModifyProductFormController ModProductController = loader.getController();
            ModProductController.initializeProductData(productsTable.getSelectionModel().getSelectedItem());

            // Display the modify product form
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(modifyProductScene);
            window.show();
        }
        catch (NullPointerException e)
        {
            Alert noneSelectedAlert = new Alert(Alert.AlertType.ERROR);
            noneSelectedAlert.setTitle("");
            noneSelectedAlert.setHeaderText("Product Selection Error");
            noneSelectedAlert.setContentText("No product is selected.");
            noneSelectedAlert.show();
        }

    }

    /**
     * <p>This method deletes a product from the products table. It checks for whether a product is selected and gives
     * an error dialog if no product is selected. If a product is selected, the user is asked to confirm whether they would
     * like to proceed and delete the product. If the user presses OK, one more check is executed to see whether the product
     * has any parts associated with it. If it has parts associated with it, an error dialog pops up and asks the user to remove
     * the part/s before trying again. Else, a product without any associated parts is deleted.</p>
     * @param event When the delete button is pressed.
     */
    @FXML
    void deleteProductBtn(ActionEvent event) {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        if (productsTable.getSelectionModel().isEmpty()) {
            Alert noneSelectedAlert = new Alert(Alert.AlertType.ERROR);
            noneSelectedAlert.setTitle("");
            noneSelectedAlert.setHeaderText("Product Selection Error");
            noneSelectedAlert.setContentText("No product is selected.");
            noneSelectedAlert.show();
        }
        else  {
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setTitle("");
            deleteAlert.setHeaderText("Deletion Confirmation");
            deleteAlert.setContentText("Are you sure you want to delete this product?");
            Optional<ButtonType> result = deleteAlert.showAndWait();


            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (selectedProduct.getAllAssociatedParts().isEmpty()) {
                    Inventory.deleteProduct(selectedProduct);
                }
                else {
                    Alert nonemptyProductAlert = new Alert(Alert.AlertType.ERROR);
                    nonemptyProductAlert.setTitle("");
                    nonemptyProductAlert.setHeaderText("Product has associated parts.");
                    nonemptyProductAlert.setContentText("Products associated with a part cannot be deleted. Please remove the part and try again.");
                    nonemptyProductAlert.show();
                }
            }
        }
    }


    /**
     * This method searches the product table by ID or name after the search button is pressed.
     * @param event When the search button is pressed.
     */
    @FXML
    void searchProductsTable(ActionEvent event) {
        searchProductsTable();
    }

    /**
     * This method searches the product table by ID or name after the enter key is pressed.
     * @param event When the enter key is pressed.
     */
    @FXML
    void searchProductsTableEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchProductsTable();
        }
    }

    /**
     * This method closes the application.
     * @param event When the exit button is pressed.
     */
    @FXML
    void exitBtn(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * <p>This method searches the parts table by ID or name. It first searches by ID by attempting to parse the search query
     * into an integer. If a part's ID matches the parsed search query, it is added to the search result list and the
     * parts table displays that list. If parsing the integer doesn't work, it attempts to search by string (partial/complete name).
     * Any partial/complete matches are added to a search result list and the table view displays those items. If the search query is empty
     * and the search button/enter is pressed, the parts table is repopulated with all parts in inventory.</p>
     * <p>FUTURE ENHANCEMENT - Ability to filter search results dynamically as the user types out search query, search
     * suggestions, search history</p>
     */
    void searchPartsTable() {
        String searchQuery = searchPartTextField.getText();
        ObservableList<Part> searchResult = FXCollections.observableArrayList();

        try {
            int queryInt = Integer.parseInt(searchQuery);

            if (Inventory.lookupPart(queryInt) == null) {
                Alert emptyResultAlert = new Alert(Alert.AlertType.ERROR);
                emptyResultAlert.setTitle("");
                emptyResultAlert.setHeaderText("Empty Search");
                emptyResultAlert.setContentText("No results found.");
                emptyResultAlert.show();
            }
            else {
                searchResult.add(Inventory.lookupPart(queryInt));
                partsTable.setItems(searchResult);
            }
        }
        catch (NumberFormatException e) {
            partsTable.setItems(Inventory.lookupPart(searchQuery));

            if (Inventory.lookupPart(searchQuery).isEmpty()) {
                Alert emptyResultAlert = new Alert(Alert.AlertType.ERROR);
                emptyResultAlert.setTitle("");
                emptyResultAlert.setHeaderText("Empty Search");
                emptyResultAlert.setContentText("No results found.");
                emptyResultAlert.show();
            }
        }
        finally {
            if (searchQuery.equals("")) {
                partsTable.setItems(Inventory.getAllParts());
            }
        }
    }

    /**
     * <p>This method searches the products table by ID or name. It first searches by ID by attempting to parse the search query
     * into an integer. If a product's ID matches the parsed search query, it is added to the search result list and the
     * products table displays that list. If parsing the integer doesn't work, it attempts to search by string (partial/complete name).
     * Any partial/complete matches are added to a search result list and the table view displays those items. If the search query is empty
     * and the search button/enter is pressed, the parts table is repopulated with all parts in inventory.</p>
     * <p>FUTURE ENHANCEMENT - Ability to filter search results dynamically as the user types out search query, search
     * suggestions, search history.</p>
     */
    void searchProductsTable() {
        String searchQuery = searchProductTextField.getText();
        ObservableList<Product> searchResult = FXCollections.observableArrayList();

        try {
            int queryInt = Integer.parseInt(searchQuery);

            if (Inventory.lookupProduct(queryInt) == null) {
                Alert emptyResultAlert = new Alert(Alert.AlertType.ERROR);
                emptyResultAlert.setTitle("");
                emptyResultAlert.setHeaderText("Empty Search");
                emptyResultAlert.setContentText("No results found.");
                emptyResultAlert.show();
            }
            else {
                searchResult.add(Inventory.lookupProduct(queryInt));
                productsTable.setItems(searchResult);
            }
        }
        catch (NumberFormatException e) {
            productsTable.setItems(Inventory.lookupProduct(searchQuery));

            if (Inventory.lookupProduct(searchQuery).isEmpty()) {
                Alert emptyResultAlert = new Alert(Alert.AlertType.ERROR);
                emptyResultAlert.setTitle("");
                emptyResultAlert.setHeaderText("Empty Search");
                emptyResultAlert.setContentText("No results found.");
                emptyResultAlert.show();
            }
        }
        finally {
            if (searchQuery.equals("")) {
                productsTable.setItems(Inventory.getAllProducts());
            }
        }
    }

    /**
     * <p>This method initializes the controller with the parts/products table view and formats their cells.</p>
     * <p>RUNTIME ERROR - At first, the tables weren't showing anything without initializing the controller. Had to
     * add an "implements Initializable at beginning of class for this method to work. Prices would display but without
     * trailing zeroes if applicable. Used setCellFactory to format the prices as intended.</p>
     * @param url Location
     * @param rb Resources
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize Parts Table
        partsTable.setItems(Inventory.getAllParts());

        // Set value of the cells
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        // Formats the cells to display prices accurately
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
            partPriceCol.setCellFactory(price -> new TableCell<Part, Double>() {
                protected void updateItem(Double price, boolean empty) {
                    super.updateItem(price, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(currencyFormatter.format(price));
                    }
                    }
            });

        // Initialize Products Table
        productsTable.setItems(Inventory.getAllProducts());

        // Set values of the cells
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productStockCol.setCellValueFactory(new PropertyValueFactory<>("productStock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));

        // Formats the cells to display prices accurately
        productPriceCol.setCellFactory(price -> new TableCell<Product, Double>() {
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
