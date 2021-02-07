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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProductFormController implements Initializable {

    @FXML
    private TitledPane addProductForm;

    @FXML
    private TextField productIDTextField;

    @FXML
    private TextField productNameTextField;

    @FXML
    private TextField productStockTextField;

    @FXML
    private TextField productPriceTextField;

    @FXML
    private TextField productMaxTextField;

    @FXML
    private TextField productMinTextField;

    @FXML
    private TableView<Part> availablePartsTable;

    @FXML
    private TableColumn<Part, Integer> availablePartIDCol;

    @FXML
    private TableColumn<Part, String> availablePartNameCol;

    @FXML
    private TableColumn<Part, Integer> availablePartStockCol;

    @FXML
    private TableColumn<Part, Double> availablePartPriceCol;

    @FXML
    private TextField searchPartTextField;

    @FXML
    private Button searchPartButton;

    @FXML
    private Button addPartButton;

    @FXML
    private TableView<Part> associatedPartsTable;

    @FXML
    private TableColumn<Part, Integer> associatedPartIDCol;

    @FXML
    private TableColumn<Part, String> associatedPartNameCol;

    @FXML
    private TableColumn<Part, Integer> associatedPartStockCol;

    @FXML
    private TableColumn<Part, Double> associatedPartPriceCol;

    @FXML
    private Button removeAssociatedPartButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private static int productCount = 1;

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();


    @FXML
    void searchAvailablePartsBtn(ActionEvent event) {
        searchAvailablePartsTable();
    }

    @FXML
    void searchAvailablePartsTableEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)  {
            searchAvailablePartsTable();
        }
    }

    @FXML
    void addPartToAssociatedParts(ActionEvent event) {

            Part selectedPart = availablePartsTable.getSelectionModel().getSelectedItem();

            if (availablePartsTable.getSelectionModel().isEmpty()) {
                Alert noneSelectedAlert = new Alert(Alert.AlertType.ERROR);
                noneSelectedAlert.setTitle("Part Selection Error");
                noneSelectedAlert.setHeaderText("No part is selected.");
                noneSelectedAlert.show();
            }
            else if (associatedParts.contains(selectedPart)) {
                Alert duplicateAlert = new Alert(Alert.AlertType.ERROR);
                duplicateAlert.setTitle("Duplicate Part Error");
                duplicateAlert.setHeaderText("The part has already been added, can only be added once.");
                duplicateAlert.show();
            }
            else {
                associatedParts.add(selectedPart);
            }
            availablePartsTable.getSelectionModel().clearSelection();
    }

    @FXML
    void removeAssociatedPartBtn(ActionEvent event) {

        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();

        if (associatedParts.isEmpty()) {
            Alert emptyListAlert = new Alert(Alert.AlertType.ERROR);
            emptyListAlert.setTitle("Empty List Error");
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
                associatedParts.remove(selectedPart);
            }



        }
    }

    @FXML
    void addProductBtn(ActionEvent event) throws IOException {

        int productID = productCount;
        String productName = productNameTextField.getText();
        String productStock = productStockTextField.getText();
        String productPrice = productPriceTextField.getText();
        String productMin = productMinTextField.getText();
        String productMax = productMaxTextField.getText();
        String errorMessage = "Product input is invalid. Please fix the following errors: \n";

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
            double productPriceParsed = Double.parseDouble(productPrice);
            int productStockParsed = Integer.parseInt(productStock);
            int productMinParsed = Integer.parseInt(productMin);
            int productMaxParsed = Integer.parseInt(productMax);

            Product newProduct = new Product(productID, productName, productPriceParsed, productStockParsed, productMinParsed, productMaxParsed);

            for (Part currentPart : associatedParts) {
                newProduct.addAssociatedPart(currentPart);
            }
            Inventory.addProduct(newProduct);
            productCount++;
        }

        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    @FXML
    void addProductCancelBtn(ActionEvent event) throws IOException {

//        ObservableList<Part> associatedParts = Product.getAllAssociatedParts();
//        associatedParts.clear();
        // associatedParts.removeAll();

        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        availablePartsTable.setItems(Inventory.getAllParts());
        availablePartIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        availablePartNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
        availablePartStockCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        availablePartPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

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

        availablePartsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        associatedPartsTable.setItems(associatedParts);
        associatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
        associatedPartStockCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        associatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

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

