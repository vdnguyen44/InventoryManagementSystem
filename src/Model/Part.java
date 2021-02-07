package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Part {

    private int partID;
    private String partName;
    private double partPrice;
    private int partStock;
    private int partMin;
    private int partMax;

    public Part(int partID, String partName, double partPrice, int partStock, int partMin, int partMax) {
        this.partID = partID;
        this.partName = partName;
        this.partPrice = partPrice;
        this.partStock = partStock;
        this.partMin = partMin;
        this.partMax = partMax;
    }

    public int getPartID() {
        return partID;
    }

    public void setPartID(int partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(double partPrice) {
        this.partPrice = partPrice;
    }

    public int getPartStock() {
        return partStock;
    }

    public void setPartStock(int partStock) {
        this.partStock = partStock;
    }

    public int getPartMin() {
        return partMin;
    }

    public void setPartMin(int partMin) {
        this.partMin = partMin;
    }

    public int getPartMax() {
        return partMax;
    }

    public void setPartMax(int partMax) {
        this.partMax = partMax;
    }

    public static List<String> partValidation(String partName, String partPrice, String partStock, String partMin, String partMax, String mIDcompanyName) {
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

            if (!(stock >= min && stock <= max)) {
                errorList.add("The amount of inventory must be between the minimum and maximum.");
            }
        }
        catch (NumberFormatException ignored) {
        }

        try {
            int min = Integer.parseInt(partMin);
            int max = Integer.parseInt(partMax);

            if (max < min) {
                errorList.add("The maximum must be greater than the minimum.");
            }
        }
        catch (NumberFormatException ignored) {

        }

        return errorList;



    }
}


