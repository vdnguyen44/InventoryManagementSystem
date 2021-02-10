package Model;

/**
 * @author Vincent Nguyen
 * A subclass of Part. Inherits all fields of Part.
 */
public class InHouse extends Part {

    /**
     * The machine ID of a part.
     */
    private int machineID;

    /**
     * The constructor for the InHouse class. The InHouse class takes in the same parameters as Part but requires an
     * additional machine ID parameter.
     * @param partID The part is assigned a partID during instantiation, the user does not enter their own ID.
     * @param partName The part name is required during instantiation.
     * @param partPrice The part price is required during instantiation and must be parsable as a double.
     * @param partStock The part stock is required during instantiation and must be parsable as an integer.
     * @param partMin The part minimum is required during instantiation and must be parsable as an integer.
     * @param partMax The part maximum is required during instantiation and must be parsable as an integer.
     * @param machineID The machine ID is required during instantiation and must be parsable as an integer.
     */
    public InHouse(int partID, String partName, double partPrice, int partStock, int partMin, int partMax, int machineID) {
        super(partID, partName, partPrice, partStock, partMin, partMax);
        this.machineID = machineID;
    }

    /**
     * This method gets the machineID value from a part object.
     * @return Returns the value stored in machineID field of a part object.
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     * This method sets the machineID value of an object.
     * @param machineID Sets the machineID value of an object to that of the parameter passed in.
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
