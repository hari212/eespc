package Test;


public class TestFacility {

	private static int facilityId;

	public TestFacility() {
	}

	public void setCurrentFacilityID(int facilityID) {
		facilityId = facilityID;
		System.out.println("Test Facility ID :1 " + facilityId);
	}

	public static int getCurrentFacilityID() {
		System.out.println("Test Facility ID :2 " + facilityId);
		return facilityId;
	}
}
