package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partID) {
        for (Part part : allParts) {
            if (partID == part.getPartID()) {
                return part;
            }
        }
        return null;
    }

    public static Product lookupProduct(int productID) {
        for (Product product : allProducts) {
            if (productID == product.getProductID()) {
                return product;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> searchResult = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getPartName().toLowerCase().contains(partName.toLowerCase())) {
                searchResult.add(part);
            }
        }
        return searchResult;
    }

    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> searchResult = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getProductName().toLowerCase().contains(productName.toLowerCase())) {
                searchResult.add(product);
            }
        }
        return searchResult;
    }

    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);

    }

    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);

    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }





}
