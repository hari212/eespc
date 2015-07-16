// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FacilityManagerTest.java

package com.eespc.tracking.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.apache.log4j.PropertyConfigurator;

// Referenced classes of package com.eespc.tracking.bo:
//            AddressVo, FacilityVo, ContactVo, FacilityManager, 
//            DropDown

public class FacilityManagerTest extends TestCase {

	public FacilityManagerTest(String s) {
		super(s);
	}

	public void testAddFacility() throws Exception {
		AddressVo addressvo = new AddressVo();
		addressvo.setAddress1("ADDRESS1");
		addressvo.setAddress2("Address2");
		addressvo.setAddress3("Address3");
		addressvo.setCity("Sayreville");
		addressvo.setState("NJ");
		addressvo.setZipCode("08872");
		FacilityVo facilityvo = new FacilityVo();
		String s = (new StringBuilder()).append("Fac Dec")
				.append(Math.random()).toString();
		facilityvo.setBorough(1);
		facilityvo.setClientName("Raj Inc.");
		facilityvo.setDecId(s);
		facilityvo.setFacilityType(1);
		facilityvo.setVicePresident("Rajkanth");
		facilityvo.setFacilityAddress(addressvo);
		facilityvo.setPhone("732-651-4153");
		facilityvo.setFax("732-651-4153");
		ContactVo contactvo = new ContactVo();
		contactvo.setFirstName("Raj");
		contactvo.setLastName("Kanth");
		contactvo.setContactType(1);
		contactvo.setAddress(addressvo);
		contactvo.setPhone("732-256-1122");
		contactvo.setAlternatePhone("123-456-1234");
		contactvo.setPager("789-456-1234");
		contactvo.setFax("456-789-1256");
		ArrayList arraylist = new ArrayList();
		arraylist.add(contactvo);
		facilityvo.setContactList(arraylist);
		FacilityManager facilitymanager = new FacilityManager(facilityvo);
		facilitymanager.addNewFacility();
		List list = facilitymanager.searchBy("", s, -99, -99);
		assertTrue(list.size() > 0);
		for (int i = 0; i < list.size(); i++) {
			FacilityVo facilityvo1 = (FacilityVo) list.get(i);
			List list1 = facilityvo1.getContactList();
			int j = list1.size();
			System.out.println((new StringBuilder()).append("Size=").append(j)
					.toString());
			for (int k = 0; k < j; k++) {
				ContactVo contactvo1 = (ContactVo) list1.get(k);
				System.out.println(contactvo1);
			}

		}

		System.out.println((new StringBuilder())
				.append("Search list for decid size =").append(list.size())
				.toString());
	}

	public void testUpdate() throws Exception {
		FacilityManager facilitymanager = new FacilityManager(new FacilityVo());
		FacilityVo facilityvo = facilitymanager.findById(39);
		FacilityVo facilityvo1 = new FacilityVo();
		facilityvo1.setBorough(1);
		facilityvo1.setClientName("Raj Inc2.");
		facilityvo1.setDecId("123456");
		facilityvo1.setFacilityType(2);
		facilitymanager = new FacilityManager(facilityvo);
		facilitymanager.updateFacility(facilityvo1);
	}

	public void testDelete() throws Exception {
		FacilityVo facilityvo = new FacilityVo();
		facilityvo.setId(2);
		FacilityManager facilitymanager = new FacilityManager(facilityvo);
		facilitymanager.deleteFacility();
	}

	public void testDropDown() throws Exception {
		FacilityManager facilitymanager = new FacilityManager(new FacilityVo());
		DropDown dropdown = facilitymanager.getDropDown();
		assertTrue(dropdown != null);
		Collection collection = dropdown.getDropDownValues();
		assertTrue(collection.size() > 1);
		System.out.println((new StringBuilder()).append("size=")
				.append(collection.size()).toString());
	}

	public void testSelecForUser() throws Exception {
		FacilityManager facilitymanager = new FacilityManager(new FacilityVo());
		List list = facilitymanager.searchBy("asdf", "", -99, 7);
		assertTrue(list != null && list.size() > 0);
		System.out.println((new StringBuilder()).append("Search Result =")
				.append(list.size()).toString());
	}

	public static Test suite() {
		TestSuite testsuite = new TestSuite();
		testsuite.addTest(new FacilityManagerTest("testAddFacility"));
		testsuite.addTest(new FacilityManagerTest("testUpdate"));
		testsuite.addTest(new FacilityManagerTest("testDelete"));
		testsuite.addTest(new FacilityManagerTest("testDropDown"));
		testsuite.addTest(new FacilityManagerTest("testSelecForUser"));
		return testsuite;
	}

	public static void main(String args[]) {
		TestRunner.run(suite());
	}

	protected void setUp() throws Exception {
		PropertyConfigurator
				.configure("G:\\Project\\javaSrc\\websrc\\WEB-INF\\config\\log4j.properties");
	}
}
