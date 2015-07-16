// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FacilityManager.java

package com.eespc.tracking.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.eespc.tracking.entity.AddressEntity;
import com.eespc.tracking.entity.ContactEntity;
import com.eespc.tracking.entity.FacilityEntity;
import com.eespc.tracking.exceptions.AddressException;
import com.eespc.tracking.exceptions.ContactException;
import com.eespc.tracking.exceptions.FacilityException;

// Referenced classes of package com.eespc.tracking.bo:
//            ContactVo, FacilityVo, DropDown

public class FacilityManager {

	public FacilityManager(FacilityVo facilityvo) {
		facilityVo = null;
		if (facilityvo == null) {
			throw new IllegalArgumentException("Facility Vo Obj cannot be null");
		} else {
			facilityVo = facilityvo;
			init();
			return;
		}
	}

	public FacilityManager() {
		facilityVo = null;
	}

	private void init() {
	}

	public void addNewFacility() throws FacilityException {
		List list = searchBy(null, facilityVo.getDecId(), -99);
		if (list != null && list.size() > 0)
			throw new FacilityException("DECID already exists.");
		int i = -99;
		int j = -99;
		try {
			i = AddressEntity.addAddress(facilityVo.getFacilityAddress());
		} catch (AddressException addressexception) {
			throw new FacilityException("While inserting Address:",
					addressexception);
		}
		if (i < 0)
			throw new FacilityException("Unable to insert Address Information");
		j = FacilityEntity.addNewFacility(i, facilityVo);
		List list1 = facilityVo.getContactListForInsert();
		int k = list1.size();
		for (int l = 0; l < k; l++) {
			ContactVo contactvo = (ContactVo) list1.get(l);
			int i1 = -99;
			try {
				i1 = ContactEntity.addContact(contactvo);
			} catch (ContactException contactexception) {
				throw new FacilityException(
						"Unable to insert Contact Information",
						contactexception);
			}
			try {
				ContactEntity.insertToContactToFacility(i1, j);
			} catch (ContactException contactexception1) {
				throw new FacilityException(
						"Unable to insert Contact2Facility", contactexception1);
			}
		}

	}

	public void updateFacility(FacilityVo facilityvo) throws FacilityException {
		if (facilityvo == null)
			throw new IllegalArgumentException("FacilityVo to update is NULL");
		String s = facilityvo.getDecId();
		String s1 = facilityVo.getDecId();
		if (null != s && null != s1 && !s.equalsIgnoreCase(s1)) {
			List list = searchBy(null, s, -99);
			if (list != null && list.size() > 0)
				throw new FacilityException("DEC ID already exist.");
		}
		facilityvo.setId(facilityVo.getId());
		FacilityEntity.updateFacility(facilityvo);
		try {
			AddressEntity.update(facilityvo.getFacilityAddress());
		} catch (AddressException addressexception) {
			throw new FacilityException("Unable to update the Address.",
					addressexception);
		}
		List list1 = facilityVo.getContactListForInsert();
		int i = list1 != null ? list1.size() : 0;
		for (int j = 0; j < i; j++) {
			ContactVo contactvo = (ContactVo) list1.get(j);
			if (contactvo.getContactId() > 0) {
				try {
					ContactEntity.updateContact(contactvo);
				} catch (ContactException contactexception) {
					throw new FacilityException(
							"Unable to update Contact Information",
							contactexception);
				}
				continue;
			}
			int k = -1;
			try {
				k = ContactEntity.addContact(contactvo);
			} catch (ContactException contactexception1) {
				throw new FacilityException(
						"Unable to insert Contact Information",
						contactexception1);
			}
			try {
				ContactEntity.insertToContactToFacility(k, facilityVo.getId());
			} catch (ContactException contactexception2) {
				throw new FacilityException(
						"Unable to insert Contact2Facility", contactexception2);
			}
		}

	}

	public void deleteFacility() throws FacilityException {
		FacilityEntity.deleteFacility(facilityVo.getId());
	}

	public FacilityVo findById(int i) throws Exception {
		return FacilityEntity.findById(i);
	}

	public List searchBy(String s, String s1, int i) {
		return searchBy(s, s1, i, -99);
	}

	public List searchBy(String s, String s1, int i, int j) {
		Object obj = new ArrayList();
		obj = FacilityEntity.searchBy(s, s1, i, j);
		return (List) (List) obj;
	}

	public List findAll() {
		Object obj = new ArrayList();
		try {
			obj = FacilityEntity.findAll();
		} catch (FacilityException facilityexception) {
			if (logger.isEnabledFor(Priority.ERROR))
				logger.error(facilityexception);
		}
		return (List) (List) obj;
	}

	public DropDown getDropDown() {
		return FacilityEntity.getDropDown();
	}

	static Class _mthclass$(String s) throws Exception {
		return Class.forName(s);
	}

	protected FacilityVo facilityVo;
	static Logger logger = Logger
			.getLogger(com.eespc.tracking.bo.FacilityManager.class);

}
