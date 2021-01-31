package View_Controller;

import Model.Inventory;
import Model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
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

    @FXML
    void addPartToAssociatedParts(ActionEvent event) {

    }

    @FXML
    void addProductBtn(ActionEvent event) {

    }

    @FXML
    void addProductCancelBtn(ActionEvent event) {

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
    }

}

