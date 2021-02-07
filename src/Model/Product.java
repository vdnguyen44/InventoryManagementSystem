package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;


public class Product {
// associatedParts shouldn't be static
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();;
    private int productID;
    private String productName;
    private double productPrice;
    private int productStock;
    private int productMin;
    private int productMax;

    public Product(int productID, String productName, double productPrice, int productStock, int productMin, int productMax) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productMin = productMin;
        this.productMax = productMax;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public int getProductMin() {
        return productMin;
    }

    public void setProductMin(int productMin) {
        this.productMin = productMin;
    }

    public int getProductMax() {
        return productMax;
    }

    public void setProductMax(int productMax) {
        this.productMax = productMax;
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part part) {
        return associatedParts.remove(part);
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public static List<String> productValidationCheck (String productName, String productPrice, String productStock, String productMin, String productMax) {
        List<String> errorList = new ArrayList<String>();

        if (productName.isEmpty()) {
            errorList.add("The name field cannot be empty.");
        }

        if (productPrice.isEmpty()) {
            errorList.add("The price field cannot be empty.");
        }
        else {
            try {
                Double.parseDouble(productPrice);
            }
            catch (NumberFormatException e) {
                errorList.add("The price entered is invalid.");
            }
        }

        if (productStock.isEmpty()) {
            errorList.add("The inventory field cannot be empty.");
        }
        else {
            try {
                Integer.parseInt(productStock);
            }
            catch (NumberFormatException e) {
                errorList.add("The amount of inventory entered is invalid.");
            }
        }

        if (productMin.isEmpty()) {
            errorList.add("The minimum field cannot be empty.");
        }
        else {
            try {
                Integer.parseInt(productMin);
            }
            catch (NumberFormatException e) {
                errorList.add("The minimum entered is invalid.");
            }
        }

        if (productMax.isEmpty()) {
            errorList.add("The maximum field cannot be empty.");
        }
        else {
            try {
                Integer.parseInt(productMax);
            }
            catch (NumberFormatException e) {
                errorList.add("The maximum entered is invalid.");
            }
        }

//        try {
//            int min = Integer.parseInt(productMin);
//            int max = Integer.parseInt(productMax);
//            int stock = Integer.parseInt(productStock);
//
//            if (!(stock >= min && stock <= max)) {
//                errorList.add("The amount of inventory must be between the minimum and maximum.");
//            }
//        }
//        catch (NumberFormatException ignored) {
//        }

        try {
            int min = Integer.parseInt(productMin);
            int max = Integer.parseInt(productMax);
            int stock = Integer.parseInt(productStock);

            if (max < min) {
                errorList.add("The maximum must be greater than the minimum.");
            }
            else if (max > min && !(stock >= min && stock <= max)) {
                errorList.add("The amount of inventory must be between the minimum and maximum.");
            }
        }
        catch (NumberFormatException ignored) {
        }

        return errorList;
    }

}

