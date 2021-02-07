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


    @FXML
    void displayAddPartBtn(ActionEvent event) throws IOException {
        Parent addPartFormLoader = FXMLLoader.load(getClass().getResource("AddPartForm.fxml"));
        Scene addPartScene = new Scene(addPartFormLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

    @FXML
    void displayModifyPartBtn(ActionEvent event) throws IOException {
        try
        {
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

    @FXML
    void deletePartBtn(ActionEvent event) {

//          if (Inventory.getAllParts().contains(selectedPart)) {
//              Inventory.deletePart(selectedPart);
//          }
//          else {
//              Alert noneSelectedAlert = new Alert(Alert.AlertType.ERROR);
//                noneSelectedAlert.setTitle("Part Selection Error");
//                noneSelectedAlert.setHeaderText("No part is selected.");
//                noneSelectedAlert.show();
//          }
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

//        if (!Inventory.deletePart(selectedPart)) {
//                Alert noneSelectedAlert = new Alert(Alert.AlertType.ERROR);
//                noneSelectedAlert.setTitle("Part Deletion Error");
//                noneSelectedAlert.setHeaderText("No part was selected, nothing was deleted.");
//                noneSelectedAlert.show();
//        }
//        else {
//            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
//            deleteAlert.setTitle("Deletion Confirmation");
//            deleteAlert.setHeaderText("Are you sure you want to delete this part?");
//            deleteAlert.showAndWait();
//            Inventory.deletePart(selectedPart);
//        }



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
    void displayAddProductBtn(ActionEvent event) throws IOException {

        Parent addProductFormLoader = FXMLLoader.load(getClass().getResource("AddProductForm.fxml"));
        Scene addProductScene = new Scene(addProductFormLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addProductScene);
        window.show();

    }

    @FXML
    void displayModifyProductBtn(ActionEvent event) throws IOException {

        try
        {
            // created fxmlloader object and let it know which view to use
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ModifyProductForm.fxml"));
            Parent productsTableParent = loader.load();

            Scene modifyProductScene = new Scene(productsTableParent);

            // letting object know which controller to use
            ModifyProductFormController ModProductController = loader.getController();
            ModProductController.initializeProductData(productsTable.getSelectionModel().getSelectedItem());
            // sending to modifyPartBtn didn't work because that method requires output of event but part is provided


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


    @FXML
    void searchProductsTable(ActionEvent event) {
        searchProductsTable();
    }

    @FXML
    void searchProductsTableEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchProductsTable();
        }
    }

    @FXML
    void exitBtn(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

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

//    Optional<ButtonType> deleteConfirmation(String item) {
//        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
//        deleteAlert.setTitle("Deletion Confirmation");
//        deleteAlert.setHeaderText("Are you sure you want to delete this " + item + "?");
//        return deleteAlert.showAndWait();
//
//        // return result.isPresent() && result.get() == ButtonType.OK;
//
//        // return deleteAlert.showAndWait();
//        // if (result.isPresent() && result.get() == ButtonType.OK)
//    }

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

                    } else {
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
