package Model;

public class InHouse extends Part {

    private int machineID;

    public InHouse(int partID, String partName, double partPrice, int partStock, int partMin, int partMax, int machineID) {
        super(partID, partName, partPrice, partStock, partMin, partMax);
        this.machineID = machineID;
    }

    public int getMachineID() {

        return machineID;
    }

    public void setMachineID(int machineID) {

        this.machineID = machineID;
    }
}
