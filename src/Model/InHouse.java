package Model;

public class InHouse extends Part {

    private int machineID;

    public InHouse(int partID, String partName, double partPrice, int partStock,  int partMin, int partMax, int machineID) {
        setPartID(partID);
        setPartName(partName);
        setPartPrice(partPrice);
        setPartStock(partStock);
        setPartMin(partMin);
        setPartMax(partMax);
        this.machineID = machineID;
    }

    public int getMachineID() {
        return machineID;
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
