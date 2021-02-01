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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
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
    void addPartToAssociatedParts(ActionEvent event) {
        Part selectedPart = availablePartsTable.getSelectionModel().getSelectedItem();

        if (associatedParts.contains(selectedPart)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part already added");
            alert.setHeaderText("The part has already been added, can only be added once.");
            alert.show();
        }
        else {
            associatedParts.add(selectedPart);
        }
    }

    @FXML
    void addProductBtn(ActionEvent event) throws IOException {

        int productID = productCount;
        String productName = productNameTextField.getText();
        int productStock = Integer.parseInt(productStockTextField.getText());
        double productPrice = Double.parseDouble(productPriceTextField.getText());
        int productMin = Integer.parseInt(productMinTextField.getText());
        int productMax = Integer.parseInt(productMaxTextField.getText());

        Product newProduct = new Product(productID, productName, productPrice, productStock, productMin, productMax);
        newProduct.getAllAssociatedParts().addAll(associatedParts);
        Inventory.addProduct(newProduct);

        productCount ++;


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

    @FXML
    void removeAssociatedPartBtn(ActionEvent event) {

    }

    @FXML
    void searchAvailablePartsBtn(ActionEvent event) {

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

