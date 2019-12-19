package hurtmeplenty.datakeepers;


public class InputKeeperOfCalculator {

    private String machineClassExpected;
    private String machineTypeExpected;
    private String datacenterLocationExpected;
    private String localSSDExpected;
    private String committedUsageExpected;

    public void setMachineClassExpected(String machineClassExpected) {
        this.machineClassExpected = machineClassExpected;
    }

    public void setMachineTypeExpected(String machineTypeExpected) {
        this.machineTypeExpected = machineTypeExpected;
    }

    public void setDatacenterLocationExpected(String datacenterLocationExpected) {
        this.datacenterLocationExpected = datacenterLocationExpected;
    }

    public void setLocalSSDExpected(String localSSDExpected) {
        this.localSSDExpected = localSSDExpected;
    }

    public void setCommittedUsageExpected(String committedUsageExpected) {
        this.committedUsageExpected = committedUsageExpected;
    }

    public String getMachineClassExpected() {
        return machineClassExpected;
    }

    public String getMachineTypeExpected() {
        return machineTypeExpected;
    }

    public String getDatacenterLocationExpected() {
        return datacenterLocationExpected;
    }

    public String getLocalSSDExpected() {
        return localSSDExpected;
    }

    public String getCommittedUsageExpected() {
        return committedUsageExpected;
    }
}
