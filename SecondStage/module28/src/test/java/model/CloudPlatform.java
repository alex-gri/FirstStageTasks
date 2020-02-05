package model;

public class CloudPlatform {

    // Platform attributes.
    private String numberOfInstance;
    private String operatingSystemSoftware;
    private String machineClass;
    private String machineType;
    private String numberOfGPU;
    private String typeOfGPU;
    private String localSSD;
    private String datacenterLocation;
    private String committedUsage;


    // Simple version of constructor without checkbox flag.
    public CloudPlatform(String numberOfInstance, String operatingSystemSoftware, String machineClass,
                         String machineType, String numberOfGPU, String typeOfGPU, String localSSD,
                         String datacenterLocation, String committedUsage) {

        this.numberOfInstance = numberOfInstance;
        this.operatingSystemSoftware = operatingSystemSoftware;
        this.machineClass = machineClass;
        this.machineType = machineType;
        this.numberOfGPU = numberOfGPU;
        this.typeOfGPU = typeOfGPU;
        this.localSSD = localSSD;
        this.datacenterLocation = datacenterLocation;
        this.committedUsage = committedUsage;
    }

    public String getNumberOfInstance() {
        return numberOfInstance;
    }

    public String getOperatingSystemSoftware() {
        return operatingSystemSoftware;
    }

    public String getMachineClass() {
        return machineClass;
    }

    public String getMachineType() { return machineType;    }

    public String getNumberOfGPU() {
        return numberOfGPU;
    }

    public String getTypeOfGPU() {
        return typeOfGPU;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public String getDatacenterLocation() {
        return datacenterLocation;
    }

    public String getCommittedUsage() { return committedUsage; }
}
