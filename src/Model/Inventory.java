package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Vincent Nguyen
 * The Inventory class that holds all parts and products.
 */
public class Inventory {

    /**
     * An observable list that keeps track of all parts as they are added and removed.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * An observable list that keeps track of all products as they are added and removed.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * This method adds a part to inventory.
     * @param newPart The part to be added to inventory.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * This method adds a product to inventory.
     * @param newProduct The product to be added to inventory.
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * This method searches for a part in inventory by it's ID.
     * @param partID The part ID to search by.
     * @return Returns the part if it's ID matches the ID searched. Null is added if no part IDs match the ID searched.
     */
    public static Part lookupPart(int partID) {
        for (Part part : allParts) {
            if (partID == part.getPartID()) {
                return part;
            }
        }
        return null;
    }

    /**
     * This method searches for a product in inventory by it's ID.
     * @param productID The product ID to search by.
     * @return Returns the product if it's ID matches the ID searched. Null is added if no product IDs match the ID searched.
     */
    public static Product lookupProduct(int productID) {
        for (Product product : allProducts) {
            if (productID == product.getProductID()) {
                return product;
            }
        }
        return null;
    }

    /**
     * This method searches for a part in inventory by it's name.
     * @param partName The part's name to search by. Can be searched by partial name or complete name.
     * @return Returns an observable list of matches.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> searchResult = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getPartName().toLowerCase().contains(partName.toLowerCase())) {
                searchResult.add(part);
            }
        }
        return searchResult;
    }

    /**
     * This method searches for a product in inventory by it's name.
     * @param productName The product's name to search by. Can be searched by partial or complete name.
     * @return Returns an observable list of matches.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> searchResult = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getProductName().toLowerCase().contains(productName.toLowerCase())) {
                searchResult.add(product);
            }
        }
        return searchResult;
    }

    /**
     * This method deletes a part from inventory.
     * @param selectedPart The part to be deleted.
     * @return Returns whether the part is deleted.
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }

    /**
     * This method deletes a product from inventory.
     * @param selectedProduct The product to be deleted.
     * @return Returns whether the product is deleted.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }

    /**
     * This method updates the parts list when a part is modified.
     * @param index The position of the part in inventory.
     * @param selectedPart The part to replace the previous part.
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * This method updates the products list when a product is modified.
     * @param index The position of the product in inventory.
     * @param selectedProduct The product to replace the previous product.
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /**
     * This method gets the list of all parts in inventory.
     * @return Returns a list of all parts in inventory.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * This method gets the list of all products in inventory.
     * @return Returns a list of all products in inventory.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
