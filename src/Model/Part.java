package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent Nguyen
 * The Part abstract class that is not instantiated directly.
 */
public abstract class Part {

    /**
     * The unique ID of a part.
     */
    private int partID;

    /**
     * The name of a part.
     */
    private String partName;

    /**
     * The price of a part.
     */
    private double partPrice;

    /**
     * The amount of inventory for a part.
     */
    private int partStock;

    /**
     * The minimum amount of the part required.
     */
    private int partMin;

    /**
     * The maximum amount of a part allowed.
     */
    private int partMax;

    /**
     * The constructor for the part class. The part is initialized with the parameter values.
     * @param partID The part is assigned a partID during instantiation, the user does not enter their own ID.
     * @param partName The part name is required during instantiation.
     * @param partPrice The part price is required during instantiation and must be parsable as a double.
     * @param partStock The part stock is required during instantiation and must be parsable as an integer.
     * @param partMin The part minimum is required during instantiation and must be parsable as an integer.
     * @param partMax The part maximum is required during instantiation and must be parsable as an integer.
     */
    public Part(int partID, String partName, double partPrice, int partStock, int partMin, int partMax) {
        this.partID = partID;
        this.partName = partName;
        this.partPrice = partPrice;
        this.partStock = partStock;
        this.partMin = partMin;
        this.partMax = partMax;
    }

    /**
     * This method gets the partID value from a part object.
     * @return Returns the value stored in partID field of a part object.
     */
    public int getPartID() {
        return partID;
    }

    /**
     * This method sets the partID value of an object.
     * @param partID Sets the partID value of an object to that of the parameter passed in.
     */
    public void setPartID(int partID) {
        this.partID = partID;
    }

    /**
     * This method gets the partName value from a part object.
     * @return Returns the value stored in the partName field of a part object.
     */
    public String getPartName() {
        return partName;
    }

    /**
     * This method sets the partName value of an object.
     * @param partName Sets the partName value of an object to that of the parameter passed in.
     */
    public void setPartName(String partName) {
        this.partName = partName;
    }

    /**
     * This method gets the partPrice value from a part object.
     * @return Returns the value stored in the partPrice field of a part object.
     */

    public double getPartPrice() {
        return partPrice;
    }

    /**
     * This method sets the partPrice value of an object.
     * @param partPrice Sets the partPrice value of an object to that of the parameter passed in.
     */
    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }

    /**
     * This method gets the partStock value from a part object.
     * @return Returns the value stored in the partStock field of a part object.
     */
    public int getPartStock() {
        return partStock;
    }

    /**
     * This method sets the partStock value of an object.
     * @param partStock Sets the partStock value of an object to that of the parameter passed in.
     */
    public void setPartStock(int partStock) {
        this.partStock = partStock;
    }

    /**
     * This method gets the partMin value from a part object.
     * @return Returns the value stored in the partMin field of a part object.
     */
    public int getPartMin() {
        return partMin;
    }

    /**
     * This method sets the partMin value of an object.
     * @param partMin Sets the partMin value of an object to that of the parameter passed in.
     */
    public void setPartMin(int partMin) {
        this.partMin = partMin;
    }

    /**
     * This method gets the partMax value from a part object.
     * @return Returns the value stored in the partMax field of a part object.
     */
    public int getPartMax() {
        return partMax;
    }

    /**
     * This method sets the partMax value of an object.
     * @param partMax Sets the partMax value of an object to that of the parameter passed in.
     */
    public void setPartMax(int partMax) {
        this.partMax = partMax;
    }

    /**
     * <p>A method written to check whether the part's input is valid before it is created. Error messages will be
     * added for empty inputs and invalid number inputs. The maximum entered must be greater than the minimum.
     * The inventory level entered must be between the minimum and maximum. </p>
     * @param partName The value in the part name's text field to be checked.
     * @param partPrice The value in the part price's text field to be checked.
     * @param partStock The value in the part stock's text field to be checked.
     * @param partMin The value in the part minimum's text field to be checked.
     * @param partMax The value in the part maximum's text field to be checked.
     * @param mIDcompanyName The value in the machineID/Company Name's text field to be checked.
     * @return Returns an array of error messages generated.
     */
    public static List<String> partValidationCheck(String partName, String partPrice, String partStock, String partMin, String partMax, String mIDcompanyName) {
        List<String> errorList = new ArrayList<String>();

        if (partName.isEmpty()) {
            errorList.add("The name field cannot be empty.");
        }

        if (partPrice.isEmpty()) {
            errorList.add("The price field cannot be empty.");
        }
        else {
            try {
                Double.parseDouble(partPrice);
            }
            catch (NumberFormatException e) {
                errorList.add("The price entered is invalid.");
            }
        }

        if (partStock.isEmpty()) {
            errorList.add("The inventory field cannot be empty.");
        }
        else {
            try {
                Integer.parseInt(partStock);
            }
            catch (NumberFormatException e) {
                errorList.add("The amount of inventory entered is invalid.");
            }
        }

        if (partMin.isEmpty()) {
            errorList.add("The minimum field cannot be empty.");
        }
        else {
            try {
                Integer.parseInt(partMin);
            }
            catch (NumberFormatException e) {
                errorList.add("The minimum entered is invalid.");
            }
        }

        if (partMax.isEmpty()) {
            errorList.add("The maximum field cannot be empty.");
        }
        else {
            try {
                Integer.parseInt(partMax);
            }
            catch (NumberFormatException e) {
                errorList.add("The maximum entered is invalid.");
            }
        }

        if (mIDcompanyName.isEmpty()) {
            errorList.add("The MachineID/Company Name field cannot be empty.");
        }

        try {
            int min = Integer.parseInt(partMin);
            int max = Integer.parseInt(partMax);
            int stock = Integer.parseInt(partStock);

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


