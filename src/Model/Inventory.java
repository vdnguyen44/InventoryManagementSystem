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

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static Part lookUpPartByID(int partID) {

//        String searchQuery = String.valueOf(partID).toLowerCase();
//        ObservableList<Part> searchResult = FXCollections.observableArrayList();
//
//        for (Part part : allParts) {
//           String partIDString = String.valueOf(part.getPartID()).toLowerCase();
//           if (partIDString.contains(searchQuery)) {
//                searchResult.add(part);
//           }
//        }
//
//        return searchResult;

        for (Part part : allParts) {
            if (partID == part.getPartID()) {
                return part;
            }
        }
        return null;
    }

    public static ObservableList<Part> lookUpPartByName(String partName) {
        ObservableList<Part> searchResult = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getPartName().toLowerCase().contains(partName.toLowerCase())) {
                searchResult.add(part);
            }
        }
        return searchResult;

    }



}
