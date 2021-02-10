package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent Nguyen
 * The Product class that has objects of the Part class associated with it.
 */
public class Product {

    /**
     * The parts associated with a product. The observable list keeps track of all parts as they are added and removed.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * The unique ID of a product.
     */
    private int productID;

    /**
     * The name of a product.
     */
    private String productName;

    /**
     * The price of a product.
     */
    private double productPrice;

    /**
     * The amount of inventory for a product.
     */
    private int productStock;

    /**
     * The minimum amount of the product required.
     */
    private int productMin;

    /**
     * The maximum amount of a product allowed.
     */
    private int productMax;

    /**
     * The constructor for the product class. The product is initialized with the parameter values.
     * @param productID The product is assigned a productID during instantiation, the user does not enter their own ID.
     * @param productName The product name is required during instantiation.
     * @param productPrice The product price is required during instantiation and must be parsable as a double.
     * @param productStock The product stock is required during instantiation and must be parsable as an integer.
     * @param productMin The product minimum is required during instantiation and must be parsable as an integer.
     * @param productMax The product maximum is required during instantiation and must be parsable as an integer.
     */
    public Product(int productID, String productName, double productPrice, int productStock, int productMin, int productMax) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productMin = productMin;
        this.productMax = productMax;
    }

    /**
     * This method gets the productID value from a product object.
     * @return Returns the value stored in productID field of a product object.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * This method sets the productID value of an object.
     * @param productID Sets the productID value of an object to that of the parameter passed in.
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * This method gets the productName value from a product object.
     * @return Returns the value stored in productName field of a product object.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * This method sets the productName value of an object.
     * @param productName Sets the productID value of an object to that of the parameter passed in.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * This method gets the productPrice value from a product object.
     * @return Returns the value stored in productPrice field of a product object.
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * This method sets the productPrice value of an object.
     * @param productPrice Sets the productPrice value of an object to that of the parameter passed in.
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * This method gets the productStock value from a product object.
     * @return Returns the value stored in productStock field of a product object.
     */
    public int getProductStock() {
        return productStock;
    }

    /**
     * This method sets the productStock value of an object.
     * @param productStock Sets the productStock value of an object to that of the parameter passed in.
     */
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    /**
     * This method gets the productMin value from a product object.
     * @return Returns the value stored in productMin field of a product object.
     */
    public int getProductMin() {
        return productMin;
    }

    /**
     * This method sets the productMin value of an object.
     * @param productMin Sets the productMin value of an object to that of the parameter passed in.
     */
    public void setProductMin(int productMin) {
        this.productMin = productMin;
    }

    /**
     * This method gets the productMax value from a product object.
     * @return Returns the value stored in productMax field of a product object.
     */
    public int getProductMax() {
        return productMax;
    }

    /**
     * This method sets the productMax value of an object.
     * @param productMax Sets the productMax value of an object to that of the parameter passed in.
     */
    public void setProductMax(int productMax) {
        this.productMax = productMax;
    }

    /**
     * This method adds a part to the product's associated parts list.
     * @param part The Part object to be added to the associated parts list.
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * This method removes a part from a product's associated parts list.
     * @param part The Part object to be removed from the associated parts list.
     * @return Returns whether a part was deleted from the product's associated parts list.
     */
    public boolean deleteAssociatedPart(Part part) {
        return associatedParts.remove(part);
    }

    /**
     * This method gets the list of associated parts from a product object.
     * @return Returns a list of a product's associated parts.
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    /**
     * <p>A method written to check whether the product's input is valid before it is created. Error messages will be
     * added for empty inputs and invalid number inputs. The maximum entered must be greater than the minimum.
     * The inventory level entered must be between the minimum and maximum. </p>
     * @param productName The value in the product name's text field to be checked.
     * @param productPrice The value in the product price's text field to be checked.
     * @param productStock The value in the product stock's text field to be checked.
     * @param productMin The value in the product minimum's text field to be checked.
     * @param productMax The value in the product maximum's text field to be checked.
     * @return Returns an array of error messages generated.
     */
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

