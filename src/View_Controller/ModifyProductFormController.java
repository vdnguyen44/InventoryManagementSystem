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
import java.util.ResourceBundle;


public class ModifyProductFormController implements Initializable {

    @FXML
    private TitledPane modifyProductForm;

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

    private int productID;

    private int productIndex;

    private ObservableList<Part> productAssociatedParts = FXCollections.observableArrayList();

    void searchPartsTable() {
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

    @FXML
    void addPartToAssociatedParts(ActionEvent event) {
        Part selectedPart = availablePartsTable.getSelectionModel().getSelectedItem();

        if (availablePartsTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part Selection Error");
            alert.setHeaderText("No part is selected.");
            alert.show();
        }

        else if (productAssociatedParts.contains(selectedPart)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Duplicate Part Error");
            alert.setHeaderText("The part has already been added, can only be added once.");
            alert.show();
        }
        else {
            productAssociatedParts.add(selectedPart);
        }
        availablePartsTable.getSelectionModel().clearSelection();
    }

    @FXML
    void modifyProductBtn(ActionEvent event) {

    }

    @FXML
    void modifyProductCancelBtn(ActionEvent event) throws IOException {

        Parent mainLoader = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Scene mainScene = new Scene(mainLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();

    }

    @FXML
    void removeAssociatedPartBtn(ActionEvent event) {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();

        if (productAssociatedParts.isEmpty()) {
            Alert notFoundAlert = new Alert(Alert.AlertType.ERROR);
            notFoundAlert.setTitle("Delete Part Error");
            notFoundAlert.setHeaderText("No part was deleted.");
            notFoundAlert.show();
        }

        else if (associatedPartsTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part Selection Error");
            alert.setHeaderText("No part is selected.");
            alert.show();
        }
        else {
            productAssociatedParts.remove(selectedPart);
        }
    }

    @FXML
    void searchAvailablePartsBtn(ActionEvent event) {
        searchPartsTable();
    }

    @FXML
    void searchAvailablePartsTableEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)  {
            searchPartsTable();
        }
    }

    public void initializeProductData(Product product) {
        productID = product.getProductID();
        productIndex = Inventory.getAllProducts().indexOf(product);
        productAssociatedParts = product.getAllAssociatedParts();
        productIDTextField.setText(String.valueOf(product.getProductID()));
        productNameTextField.setText(product.getProductName());
        productStockTextField.setText(String.valueOf(product.getProductStock()));
        productPriceTextField.setText(String.valueOf(product.getProductPrice()));
        productMaxTextField.setText(String.valueOf(product.getProductMax()));
        productMinTextField.setText(String.valueOf(product.getProductMin()));

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

        associatedPartsTable.setItems(product.getAllAssociatedParts());
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

}
