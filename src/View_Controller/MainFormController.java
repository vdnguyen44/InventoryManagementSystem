package View_Controller;
import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.application.Platform;
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
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {

    @FXML
    private TitledPane mainForm;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partStockCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;


    @FXML
    private Button addPartButton;

    @FXML
    private Button modifyPartButton;

    @FXML
    private Button deletePartButton;

    @FXML
    private TextField searchPartTextField;

    @FXML
    private Button searchPartButton;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, Integer> productIDCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productStockCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;


    @FXML
    private Button addProductButton;

    @FXML
    private Button modifyProductButton;

    @FXML
    private Button deleteProductButton;

    @FXML
    private TextField searchProductTextField;

    @FXML
    private Button searchProductButton;

    @FXML
    private Button exitButton;

//    int getPartIndex() {
//       Part selectedRow = partsTable.getSelectionModel().getSelectedItem();
//       ObservableList<Part> allParts = Inventory.getAllParts();
//       return allParts.indexOf(selectedRow);
//    }

    void searchPartsTable() {
        String searchQuery = searchPartTextField.getText();
        ObservableList<Part> searchResult = FXCollections.observableArrayList();

        try {
            int queryInt = Integer.parseInt(searchQuery);
            searchResult.add(Inventory.lookupPart(queryInt));
            partsTable.setItems(searchResult);

            if (searchResult.get(0) == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Search Empty");
                alert.setHeaderText("No results found.");
                alert.showAndWait();
            }
        }
        catch (NumberFormatException e) {
            partsTable.setItems(Inventory.lookupPart(searchQuery));

            if (Inventory.lookupPart(searchQuery).isEmpty()) {
                Alert emptyResultAlert = new Alert(Alert.AlertType.ERROR);
                emptyResultAlert.setTitle("Empty Search");
                emptyResultAlert.setHeaderText("No results found.");
                emptyResultAlert.showAndWait();
            }
        }
        finally {
            if (searchQuery.equals("")) {
                partsTable.setItems(Inventory.getAllParts());
            }
        }
    }

    @FXML
    void deletePartBtn(ActionEvent event) {

        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (Inventory.deletePart(selectedPart)) {
            partsTable.setItems(Inventory.getAllParts());
        }
        else {
            Alert notFoundAlert = new Alert(Alert.AlertType.ERROR);
            notFoundAlert.setTitle("Delete Part Error");
            notFoundAlert.setHeaderText("No part was deleted.");
            notFoundAlert.showAndWait();
        }

    }

    @FXML
    void deleteProductBtn(ActionEvent event) {

    }

    @FXML
    void displayAddPartBtn(ActionEvent event) throws IOException {
        Parent addPartFormLoader = FXMLLoader.load(getClass().getResource("AddPartForm.fxml"));
        Scene addPartScene = new Scene(addPartFormLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

    @FXML
    void displayAddProductBtn(ActionEvent event) {

    }

    @FXML
    void displayModifyPartBtn(ActionEvent event) throws IOException {
        try {
            // created fxmlloader object and let it know which view to use
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyPartForm.fxml"));
            Parent partsTableParent = loader.load();

            Scene modifyPartScene = new Scene(partsTableParent);

            // letting object know which controller to use
            ModifyPartFormController ModPartController = loader.getController();
            ModPartController.initializePartData(partsTable.getSelectionModel().getSelectedItem());
            // sending to modifyPartBtn didn't work because that method requires output of event but part is provided


            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(modifyPartScene);
            window.show();
        } catch (RuntimeException e) {
            Alert notSelectedAlert = new Alert(Alert.AlertType.ERROR);
            notSelectedAlert.setTitle("Part Selection Error");
            notSelectedAlert.setHeaderText("No part is selected.");
            notSelectedAlert.showAndWait();
        }

    }

    @FXML
    void displayModifyProductBtn(ActionEvent event) {

    }

    @FXML
    void exitBtn(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void searchPartsTableBtn(ActionEvent event) {
    // Part = type of class being scanned in list, part = element variable, after the colons is the list, getAllParts returns that list
//        for (Part part : Inventory.getAllParts()) {
//
//        }
//        // too specific, come up with method to check for whether partID or partName is being searched, 36:49
//        int searchQuery = Integer.parseInt(searchPartTextField.getText());
//        ObservableList<Part> searchResult = FXCollections.observableArrayList();
//        searchResult.add(Inventory.lookUpPartByID(searchQuery));
//        partsTable.setItems(searchResult);

        searchPartsTable();
    }

    @FXML
    void searchPartsTableEnter (KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)  {
            searchPartsTable();
        }
    }

    @FXML
    void searchProductsTable(ActionEvent event) {

    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //this is the first method called anytime this controller is instantiated
        //at first program wouldn't run because "implements Initializable" wasn't specified at beginning of class
        // this code initializes the table and displays all available parts when main screen is loaded
        partsTable.setItems(Inventory.getAllParts());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));

        partNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));

        partStockCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));

        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));

        // add input to only allow two decimal places
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();

        partPriceCol.setCellFactory(price -> new TableCell<Part, Double>() {

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

        partsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // initializes the products table
        productsTable.setItems(Inventory.getAllProducts());

        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));

        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));

        productStockCol.setCellValueFactory(new PropertyValueFactory<>("productStock"));

        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));


    }

}
