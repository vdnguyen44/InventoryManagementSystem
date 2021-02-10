package Model;

/**
 * @author Vincent Nguyen
 * A subclass of Part. Inherits all fields of Part.
 */
public class Outsourced extends Part {

    /**
     * The company name of a part.
     */
    private String companyName;

    /**
     * The constructor for the Outsourced class. The Outsourced class takes in the same parameters as Part but requires an
     * additional company name parameter.
     * @param partID The part is assigned a partID during instantiation, the user does not enter their own ID.
     * @param partName The part name is required during instantiation.
     * @param partPrice The part price is required during instantiation and must be parsable as a double.
     * @param partStock The part stock is required during instantiation and must be parsable as an integer.
     * @param partMin The part minimum is required during instantiation and must be parsable as an integer.
     * @param partMax The part maximum is required during instantiation and must be parsable as an integer.
     * @param companyName The company name is required during instantiation.
     */

    public Outsourced(int partID, String partName, double partPrice, int partStock, int partMin, int partMax, String companyName) {
        super(partID, partName, partPrice, partStock, partMin, partMax);
        this.companyName = companyName;
    }


    /**
     * This method gets the companyName value from a part object.
     * @return Returns the value stored in companyName field of a part object.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method sets the companyName value of an object.
     * @param companyName Sets the companyName value of an object to that of the parameter passed in.
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
