package Model;
import javafx.collections.ObservableList;


public class Inventory {

    protected ObservableList<Part> allParts;
    protected ObservableList<Product> allProducts;

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }



}
