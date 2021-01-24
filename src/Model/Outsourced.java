package Model;

public class Outsourced extends Part {

    private String companyName;

    public Outsourced(int partID, String partName, double partPrice, int partStock, int partMin, int partMax, String companyName) {
        super(partID, partName, partPrice, partStock, partMin, partMax);
        this.companyName = companyName;
    }


    public String getCompanyName() {

        return companyName;
    }

    public void setCompanyName(String companyName) {

        this.companyName = companyName;

    }
}
