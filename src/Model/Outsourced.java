package Model;

public class Outsourced extends Part {

    private String companyName;

    public Outsourced(int partID, String partName, double partPrice, int partStock,  int partMin, int partMax,String companyName) {
        setPartID(partID);
        setPartName(partName);
        setPartPrice(partPrice);
        setPartStock(partStock);
        setPartMin(partMin);
        setPartMax(partMax);
        this.companyName = companyName;
    }

    public String getCompanyName() {
         return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
