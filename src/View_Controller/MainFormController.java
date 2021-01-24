package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.beans.property.Property;
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
import java.util.ResourceBundle;

public class MainFormController  {

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
    void deletePartFromTable(ActionEvent event) {

    }

    @FXML
    void deleteProductFromTable(ActionEvent event) {

    }

    @FXML
    void displayAddPartForm(ActionEvent event) throws IOException {
        Parent addPartFormLoader = FXMLLoader.load(getClass().getResource("AddPartForm.fxml"));
        Scene addPartScene = new Scene(addPartFormLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addPartScene);
        window.show();
    }

    @FXML
    void displayAddProductForm(ActionEvent event) {

    }

    @FXML
    void displayModifyPartForm(ActionEvent event) throws IOException {

        Parent modifyPartFormLoader = FXMLLoader.load(getClass().getResource("AddPartForm.fxml"));
        Scene modifyPartScene = new Scene(modifyPartFormLoader);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(modifyPartScene);
        window.show();

    }

    @FXML
    void displayModifyProductForm(ActionEvent event) {

    }

    @FXML
    void exitApplication(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void searchPartsTable(ActionEvent event) {

    }

    @FXML
    void searchProductsTable(ActionEvent event) {

    }

//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        //this is the first method called anytime this controller is instantiated
//
//        partsTable.setItems(Inventory.getAllParts());
//
//        partIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
//
//        partNameCol.setCellValueFactory(new PropertyValueFactory<>("partName"));
//
//        partStockCol.setCellValueFactory(new PropertyValueFactory<>("partStock"));
//
//        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
//
//
//        productsTable.setItems(Inventory.getAllProducts());
//
//        productIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
//
//        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
//
//        productStockCol.setCellValueFactory(new PropertyValueFactory<>("productStock"));
//
//        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
//
//
//    }

}
