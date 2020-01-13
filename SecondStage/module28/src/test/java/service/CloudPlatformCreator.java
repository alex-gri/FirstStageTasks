package service;

import model.CloudPlatform;
import test.TestBase;

public class CloudPlatformCreator {
    public static final String TESTDATA_NUMBER_OF_INSTANCE = "testdata.platform.numberofinstance";
    public static final String TESTDATA_OPERATING_SYSTEM_SOFTWARE = "testdata.platform.operatingsystemsoftware";
    public static final String TESTDATA_MACHINE_CLASS = "testdata.platform.machineclass";
    public static final String TESTDATA_MACHINE_TYPE = "testdata.platform.machinetype";
    public static final String TESTDATA_NUMBER_OF_GPU = "testdata.platform.numberofgpu";
    public static final String TESTDATA_TYPE_OF_GPU = "testdata.platform.typeofgpu";
    public static final String TESTDATA_LOCAL_SSD = "testdata.platform.localssd";
    public static final String TESTDATA_DATACENTER_LOCATION = "testdata.platform.datacenterlocation";
    public static final String TESTDATA_COMMITED_USAGE = "testdata.platform.commitedusage";

    public static CloudPlatform withParametersFromProperties() {
        return new CloudPlatform(TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCE),
                TestDataReader.getTestData(TESTDATA_OPERATING_SYSTEM_SOFTWARE),
                TestDataReader.getTestData(TESTDATA_MACHINE_CLASS),
                TestDataReader.getTestData(TESTDATA_MACHINE_TYPE),
                TestDataReader.getTestData(TESTDATA_NUMBER_OF_GPU),
                TestDataReader.getTestData(TESTDATA_TYPE_OF_GPU),
                TestDataReader.getTestData(TESTDATA_LOCAL_SSD),
                TestDataReader.getTestData(TESTDATA_DATACENTER_LOCATION),
                TestDataReader.getTestData(TESTDATA_COMMITED_USAGE));
    }
}
