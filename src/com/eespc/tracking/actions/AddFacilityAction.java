package com.eespc.tracking.actions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.eespc.tracking.bo.AddressVo;
import com.eespc.tracking.bo.ContactVo;
import com.eespc.tracking.bo.FacilityManager;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.FuelConsumptionVo;
import com.eespc.tracking.bo.StatepermitVo;
import com.eespc.tracking.bo.UserVo;
import com.eespc.tracking.bo.myenum.BoroughsEnum;
import com.eespc.tracking.bo.myenum.ContactTypeEnum;
import com.eespc.tracking.bo.myenum.FacilityTypeEnum;
import com.eespc.tracking.bo.myenum.FolderTypeEnum;
import com.eespc.tracking.bo.myenum.StateEnum;
import com.eespc.tracking.bo.myenum.YearEnum;
import com.eespc.tracking.entity.FacilityEntity;
import com.eespc.tracking.entity.HwasteEntity;
import com.eespc.tracking.entity.LsfEntity;
import com.eespc.tracking.exceptions.FacilityException;
import com.eespc.tracking.ui.NavMenu;
import com.eespc.tracking.util.UtilityObject;
// Referenced classes of package com.eespc.tracking.actions:
//            ControllerAction

public class AddFacilityAction extends DispatchAction {

	public AddFacilityAction() {
	}

	public ActionForward initial(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("AddFacilityAction - initial()");
		setDropDown(httpservletrequest);
		HttpSession httpsession = httpservletrequest.getSession();
		httpsession.removeAttribute("FACILITY_OBJECT");
		httpsession.removeAttribute("INSPECTION_LIST");
		httpsession.removeAttribute("RESOURCE_LIST");
		httpsession.removeAttribute("BUILDING_OBJECT");
		httpsession.removeAttribute("STACK_OBJECT");
		httpsession.removeAttribute("BUILDING_LIST");
		httpservletrequest.removeAttribute("LSF_LIST");
		httpservletrequest.removeAttribute("HWASTE_LIST");
		setMenu(httpservletrequest, "3");
		return actionmapping.findForward("success");
	}

	public ActionForward add(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String s = "success";
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		AddressVo addressvo = new AddressVo();
		addressvo.setAddress1((String) dynaactionform.get("addr1"));
		addressvo.setAddress2((String) dynaactionform.get("addr2"));
		addressvo.setAddress3((String) dynaactionform.get("addr3"));
		addressvo.setCity((String) dynaactionform.get("city"));
		addressvo.setState((String) dynaactionform.get("state"));
		addressvo.setZipCode((String) dynaactionform.get("zip"));
		FacilityVo facilityvo = new FacilityVo();
		facilityvo.setClientName((String) dynaactionform.get("clName"));
		facilityvo.setVicePresident((String) dynaactionform.get("vPresident"));
		facilityvo.setDecId((String) dynaactionform.get("decId"));
		String s1 = (String) dynaactionform.get("facilityType");
		if (s1 != null && s1.trim().length() > 0)
			facilityvo.setFacilityType(Integer.parseInt(s1));
		facilityvo.setFacilityAddress(addressvo);
		String s2 = (String) dynaactionform.get("borough");
		if (s2 != null && s2.trim().length() > 0)
			facilityvo.setBorough(Integer.parseInt(s2));

		facilityvo.setCounty((String) dynaactionform.get("county"));
		facilityvo.setTown((String) dynaactionform.get("town"));

		facilityvo.setPhone((String) dynaactionform.get("phone"));
		facilityvo.setFax((String) dynaactionform.get("fax"));
		facilityvo.setFuelcaping((String) dynaactionform.get("fuelcaping"));
		facilityvo.setOilcap((String) dynaactionform.get("oilcap"));
		facilityvo.setGascap((String) dynaactionform.get("gascap"));

		facilityvo.setNumofbldg((String) dynaactionform.get("numofbldg"));

		ArrayList arraylist = new ArrayList();
		String s3 = (String) dynaactionform.get("pContact");
		String s4 = (String) dynaactionform.get("sContact");
		String s5 = (String) dynaactionform.get("tContact");
		if (s3 != null && !s3.equalsIgnoreCase("-1")) {
			AddressVo addressvo1 = new AddressVo();
			addressvo1.setAddress1((String) dynaactionform.get("pAddr1"));
			addressvo1.setAddress2((String) dynaactionform.get("pAddr2"));
			addressvo1.setAddress3((String) dynaactionform.get("pAddr3"));
			addressvo1.setCity((String) dynaactionform.get("pCity"));
			addressvo1.setState((String) dynaactionform.get("pState"));
			addressvo1.setZipCode((String) dynaactionform.get("pZip"));
			ContactVo contactvo = new ContactVo();
			contactvo.setFirstName((String) dynaactionform.get("pFirstName"));
			contactvo.setLastName((String) dynaactionform.get("pLastName"));
			contactvo.setContactType(1);
			contactvo.setTitle((String) dynaactionform.get("pTitle"));
			contactvo.setPhone((String) dynaactionform.get("pPhone"));
			contactvo.setAlternatePhone((String) dynaactionform
					.get("pMobilePhone"));
			contactvo.setFax((String) dynaactionform.get("pFax"));
			contactvo.setEmail((String) dynaactionform.get("pEmail"));
			contactvo.setAddress(addressvo1);
			arraylist.add(contactvo);
		}

		if (s4 != null && !s4.equalsIgnoreCase("-1")) {
			AddressVo addressvo2 = new AddressVo();
			addressvo2.setAddress1((String) dynaactionform.get("sAddr1"));
			addressvo2.setAddress2((String) dynaactionform.get("sAddr2"));
			addressvo2.setAddress3((String) dynaactionform.get("sAddr3"));
			addressvo2.setCity((String) dynaactionform.get("sCity"));
			addressvo2.setState((String) dynaactionform.get("sState"));
			addressvo2.setZipCode((String) dynaactionform.get("sZip"));
			ContactVo contactvo1 = new ContactVo();
			contactvo1.setFirstName((String) dynaactionform.get("sFirstName"));
			contactvo1.setLastName((String) dynaactionform.get("sLastName"));
			contactvo1.setContactType(2);
			contactvo1.setTitle((String) dynaactionform.get("sTitle"));
			contactvo1.setPhone((String) dynaactionform.get("sPhone"));
			contactvo1.setAlternatePhone((String) dynaactionform
					.get("sMobilePhone"));
			contactvo1.setFax((String) dynaactionform.get("sFax"));
			contactvo1.setEmail((String) dynaactionform.get("sEmail"));
			contactvo1.setAddress(addressvo2);
			arraylist.add(contactvo1);
		}
		if (s5 != null && !s5.equalsIgnoreCase("-1")) {
			AddressVo addressvo3 = new AddressVo();
			addressvo3.setAddress1((String) dynaactionform.get("tAddr1"));
			addressvo3.setAddress2((String) dynaactionform.get("tAddr2"));
			addressvo3.setAddress3((String) dynaactionform.get("tAddr3"));
			addressvo3.setCity((String) dynaactionform.get("tCity"));
			addressvo3.setState((String) dynaactionform.get("tState"));
			addressvo3.setZipCode((String) dynaactionform.get("tZip"));
			ContactVo contactvo2 = new ContactVo();
			contactvo2.setFirstName((String) dynaactionform.get("tFirstName"));
			contactvo2.setLastName((String) dynaactionform.get("tLastName"));
			contactvo2.setContactType(3);
			contactvo2.setTitle((String) dynaactionform.get("tTitle"));
			contactvo2.setPhone((String) dynaactionform.get("tPhone"));
			contactvo2.setAlternatePhone((String) dynaactionform
					.get("tMobilePhone"));
			contactvo2.setFax((String) dynaactionform.get("tFax"));
			contactvo2.setEmail((String) dynaactionform.get("tEmail"));
			contactvo2.setAddress(addressvo3);
			arraylist.add(contactvo2);
		}
		/*
		 * if(s4.equalsIgnoreCase("-1")) { AddressVo addressvo2 = new
		 * AddressVo(); addressvo2.setAddress1(null);
		 * addressvo2.setAddress2(null); addressvo2.setAddress3(null);
		 * addressvo2.setCity(null); addressvo2.setState(null);
		 * addressvo2.setZipCode(null); ContactVo contactvo1 = new ContactVo();
		 * contactvo1.setFirstName(null); contactvo1.setLastName(null);
		 * contactvo1.setContactType(2); contactvo1.setTitle(null);
		 * contactvo1.setPhone(null); contactvo1.setAlternatePhone(null);
		 * contactvo1.setFax(null); contactvo1.setEmail(null);
		 * contactvo1.setAddress(addressvo2); arraylist.add(contactvo1); }
		 * if(s5.equalsIgnoreCase("-1")) { AddressVo addressvo3 = new
		 * AddressVo(); addressvo3.setAddress1(null);
		 * addressvo3.setAddress2(null); addressvo3.setAddress3(null);
		 * addressvo3.setCity(null); addressvo3.setState(null);
		 * addressvo3.setZipCode(null); ContactVo contactvo2 = new ContactVo();
		 * contactvo2.setFirstName(null); contactvo2.setLastName(null);
		 * contactvo2.setContactType(3); contactvo2.setTitle(null);
		 * contactvo2.setPhone(null); contactvo2.setAlternatePhone(null);
		 * contactvo2.setFax(null); contactvo2.setEmail(null);
		 * contactvo2.setAddress(addressvo3); arraylist.add(contactvo2); }
		 */
		facilityvo.setContactList(arraylist);
		FacilityManager facilitymanager = new FacilityManager(facilityvo);
		String s6 = "";
		String s7 = "";
		try {
			facilitymanager.addNewFacility();
			List list = facilitymanager.searchBy(null, facilityvo.getDecId(),
					facilityvo.getFacilityType());
			if (list != null && list.size() > 0) {
				FacilityVo facilityvo1 = (FacilityVo) list.get(0);
				httpsession.setAttribute("FACILITY_OBJECT", facilityvo1);
			}
			httpservletrequest.setAttribute("isComponentEditable", "N");
			s6 = "Added Facility.";
			s7 = "CONFIRMATION";
			s = "goToAddBuilding";
		} catch (FacilityException facilityexception) {
			facilityexception.printStackTrace();
			s6 = facilityexception.getMessage();
			s7 = "ERROR";
			if (log.isErrorEnabled())
				log.error(facilityexception);
			s = "success";
		}
		if (s6 != null && s6.trim().length() > 0 && s7 != null
				&& s7.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s6);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s7);
		}
		log.debug("AddFacilityAction - add()");
		if (s != null && s.equalsIgnoreCase("goToAddBuilding")) {
			setMenu(httpservletrequest, "4");
			return new ActionForward("/BuildingInfo.do", true);
		} else {
			setDropDown(httpservletrequest);
			setMenu(httpservletrequest, "3");
			return actionmapping.findForward(s);
		}
	}

	public ActionForward addstatepermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("addstatepermit..............");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo1 = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		StatepermitVo permitvo = new StatepermitVo();
		permitvo.setFacilityId(facilityvo1.getId());
		permitvo.setFstatus((String) dynaactionform.get("fstatus"));
		String s1 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("fexpirationdate")),
				"yyyy-MM-dd");
		permitvo.setFexpirationdate(s1);
		permitvo.setFjan((String) dynaactionform.get("fjan"));
		permitvo.setFfeb((String) dynaactionform.get("ffeb"));
		permitvo.setFmar((String) dynaactionform.get("fmar"));
		permitvo.setFapril((String) dynaactionform.get("fapril"));
		permitvo.setFmay((String) dynaactionform.get("fmay"));
		permitvo.setFjune((String) dynaactionform.get("fjune"));
		permitvo.setFjuly((String) dynaactionform.get("fjuly"));
		permitvo.setFaug((String) dynaactionform.get("faug"));
		permitvo.setFsep((String) dynaactionform.get("fsep"));
		permitvo.setFoct((String) dynaactionform.get("foct"));
		permitvo.setFnov((String) dynaactionform.get("fnov"));
		permitvo.setFdec((String) dynaactionform.get("fdec"));
		String s2 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("flastproposal")),
				"yyyy-MM-dd");
		permitvo.setFlastproposal(s2);
		String s3 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("fnextproposal")),
				"yyyy-MM-dd");
		permitvo.setFnextproposal(s3);
		permitvo.setFcomments((String) dynaactionform.get("fcomments"));

		int permitid = FacilityEntity.addStatepermit(permitvo);

		httpservletrequest.setAttribute("isComponentEditable", "N");
		setDropDown(httpservletrequest);
		setFormForView(true, httpservletrequest, facilityvo1, dynaactionform);
		setMenu(httpservletrequest, "4");
		return actionmapping.findForward("success");
	}

	public ActionForward editstatepermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("editstatepermit..............");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo1 = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		int editid = Integer.parseInt((String) dynaactionform.get("fpermitid"));
		System.out.println("fpermitid::::::::::" + editid);
		httpservletrequest.setAttribute("stateeditid", editid);
		httpservletrequest.setAttribute("editid", editid);
		httpservletrequest.setAttribute("EDIT_PERMIT", "true");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setDropDown(httpservletrequest);
		setFormForView(true, httpservletrequest, facilityvo1, dynaactionform);
		setMenu(httpservletrequest, "4");
		return actionmapping.findForward("success");
	}

	public ActionForward updatestatepermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("updatestatepermit..............");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo1 = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");

		StatepermitVo permitvo = new StatepermitVo();
		permitvo.setId(Integer.parseInt((String) dynaactionform
				.get("fpermitid")));
		permitvo.setFstatus((String) dynaactionform.get("fstatus"));
		String s1 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("fexpirationdate")),
				"yyyy-MM-dd");
		permitvo.setFexpirationdate(s1);
		permitvo.setFjan((String) dynaactionform.get("fjan"));
		permitvo.setFfeb((String) dynaactionform.get("ffeb"));
		permitvo.setFmar((String) dynaactionform.get("fmar"));
		permitvo.setFapril((String) dynaactionform.get("fapril"));
		permitvo.setFmay((String) dynaactionform.get("fmay"));
		permitvo.setFjune((String) dynaactionform.get("fjune"));
		permitvo.setFjuly((String) dynaactionform.get("fjuly"));
		permitvo.setFaug((String) dynaactionform.get("faug"));
		permitvo.setFsep((String) dynaactionform.get("fsep"));
		permitvo.setFoct((String) dynaactionform.get("foct"));
		permitvo.setFnov((String) dynaactionform.get("fnov"));
		permitvo.setFdec((String) dynaactionform.get("fdec"));
		String s2 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("flastproposal")),
				"yyyy-MM-dd");
		permitvo.setFlastproposal(s2);
		String s3 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("fnextproposal")),
				"yyyy-MM-dd");
		permitvo.setFnextproposal(s3);
		permitvo.setFcomments((String) dynaactionform.get("fcomments"));
		FacilityEntity.updatestateprmit(permitvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setDropDown(httpservletrequest);
		setFormForView(true, httpservletrequest, facilityvo1, dynaactionform);
		setMenu(httpservletrequest, "4");
		return actionmapping.findForward("success");
	}

	public ActionForward deletestatepermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("deletestatepermit..............");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo1 = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		int delid = Integer.parseInt((String) dynaactionform.get("fpermitid"));
		FacilityEntity.deletesatatepermit(delid);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setDropDown(httpservletrequest);
		setFormForView(true, httpservletrequest, facilityvo1, dynaactionform);
		setMenu(httpservletrequest, "4");
		return actionmapping.findForward("success");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("AddFacilityAction - udpate()");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		AddressVo addressvo = facilityvo.getFacilityAddress();
		addressvo.setAddress1((String) dynaactionform.get("addr1"));
		addressvo.setAddress2((String) dynaactionform.get("addr2"));
		addressvo.setAddress3((String) dynaactionform.get("addr3"));
		addressvo.setCity((String) dynaactionform.get("city"));
		addressvo.setState((String) dynaactionform.get("state"));
		addressvo.setZipCode((String) dynaactionform.get("zip"));
		facilityvo.setClientName((String) dynaactionform.get("clName"));
		facilityvo.setVicePresident((String) dynaactionform.get("vPresident"));
		facilityvo.setDecId((String) dynaactionform.get("decId"));
		String s = (String) dynaactionform.get("facilityType");
		if (s != null && s.trim().length() > 0)
			facilityvo.setFacilityType(Integer.parseInt(s));
		facilityvo.setFacilityAddress(addressvo);
		String s1 = (String) dynaactionform.get("borough");
		if (s1 != null && s1.trim().length() > 0)
			facilityvo.setBorough(Integer.parseInt(s1));

		facilityvo.setCounty((String) dynaactionform.get("county"));
		facilityvo.setTown((String) dynaactionform.get("town"));
		facilityvo.setFuelcaping((String) dynaactionform.get("fuelcaping"));
		facilityvo.setOilcap((String) dynaactionform.get("oilcap"));
		facilityvo.setGascap((String) dynaactionform.get("gascap"));

		facilityvo.setNumofbldg((String) dynaactionform.get("numofbldg"));

		facilityvo.setPhone((String) dynaactionform.get("phone"));
		facilityvo.setFax((String) dynaactionform.get("fax"));
		ContactVo contactvo = null;
		AddressVo addressvo1 = null;
		ContactVo contactvo1 = null;
		AddressVo addressvo2 = null;
		ContactVo contactvo2 = null;
		AddressVo addressvo3 = null;
		List list = facilityvo.getContactList();
		int i = list != null ? list.size() : 0;
		Object obj = null;
		for (int j = 0; j < i; j++) {
			ContactVo contactvo3 = (ContactVo) list.get(j);
			int k = contactvo3.getContactType();
			if (k == ContactTypeEnum.PRIMARY.getCode()) {
				contactvo = contactvo3;
				AddressVo addressvo4 = contactvo3.getAddress();
				if (addressvo4 != null)
					addressvo1 = addressvo4;
				continue;
			}
			if (k == ContactTypeEnum.SECONDARY.getCode()) {
				contactvo1 = contactvo3;
				AddressVo addressvo5 = contactvo3.getAddress();
				if (addressvo5 != null)
					addressvo2 = addressvo5;
				continue;
			}
			if (k == ContactTypeEnum.TERTIARY.getCode()) {
				contactvo2 = contactvo3;
				AddressVo addressvo6 = contactvo3.getAddress();
				if (addressvo6 != null)
					addressvo3 = addressvo6;
			} else {
				log.error((new StringBuilder())
						.append("Unknown contact type (").append(k)
						.append(") for ").append(facilityvo.getClientName())
						.toString());
			}
		}

		ArrayList arraylist = new ArrayList();
		String s2 = (String) dynaactionform.get("pContact");
		String s3 = (String) dynaactionform.get("sContact");
		String s4 = (String) dynaactionform.get("tContact");
		if (s2 != null && !s2.equalsIgnoreCase("-1")) {
			if (addressvo1 == null)
				addressvo1 = new AddressVo();
			addressvo1.setAddress1((String) dynaactionform.get("pAddr1"));
			addressvo1.setAddress2((String) dynaactionform.get("pAddr2"));
			addressvo1.setAddress3((String) dynaactionform.get("pAddr3"));
			addressvo1.setCity((String) dynaactionform.get("pCity"));
			addressvo1.setState((String) dynaactionform.get("pState"));
			addressvo1.setZipCode((String) dynaactionform.get("pZip"));
			if (contactvo == null)
				contactvo = new ContactVo();
			contactvo.setFirstName((String) dynaactionform.get("pFirstName"));
			contactvo.setLastName((String) dynaactionform.get("pLastName"));
			contactvo.setContactType(1);
			contactvo.setTitle((String) dynaactionform.get("pTitle"));
			contactvo.setPhone((String) dynaactionform.get("pPhone"));
			contactvo.setAlternatePhone((String) dynaactionform
					.get("pMobilePhone"));
			contactvo.setFax((String) dynaactionform.get("pFax"));
			contactvo.setEmail((String) dynaactionform.get("pEmail"));
			contactvo.setAddress(addressvo1);
			arraylist.add(contactvo);
		}
		if (s3 != null && !s3.equalsIgnoreCase("-1")) {
			if (addressvo2 == null)
				addressvo2 = new AddressVo();
			addressvo2.setAddress1((String) dynaactionform.get("sAddr1"));
			addressvo2.setAddress2((String) dynaactionform.get("sAddr2"));
			addressvo2.setAddress3((String) dynaactionform.get("sAddr3"));
			addressvo2.setCity((String) dynaactionform.get("sCity"));
			addressvo2.setState((String) dynaactionform.get("sState"));
			addressvo2.setZipCode((String) dynaactionform.get("sZip"));
			if (contactvo1 == null)
				contactvo1 = new ContactVo();
			contactvo1.setFirstName((String) dynaactionform.get("sFirstName"));
			contactvo1.setLastName((String) dynaactionform.get("sLastName"));
			contactvo1.setContactType(2);
			contactvo1.setTitle((String) dynaactionform.get("sTitle"));
			contactvo1.setPhone((String) dynaactionform.get("sPhone"));
			contactvo1.setAlternatePhone((String) dynaactionform
					.get("sMobilePhone"));
			contactvo1.setFax((String) dynaactionform.get("sFax"));
			contactvo1.setEmail((String) dynaactionform.get("sEmail"));
			contactvo1.setAddress(addressvo2);
			arraylist.add(contactvo1);
		}
		/*
		 * if(s3 != null && s3.equalsIgnoreCase("-1")) { if(addressvo2 == null)
		 * addressvo2 = new AddressVo(); addressvo2.setAddress1(null);
		 * addressvo2.setAddress2(null); addressvo2.setAddress3(null);
		 * addressvo2.setCity(null); addressvo2.setState(null);
		 * addressvo2.setZipCode(null); if(contactvo1 == null) contactvo1 = new
		 * ContactVo(); contactvo1.setFirstName(null);
		 * contactvo1.setLastName(null); contactvo1.setContactType(2);
		 * contactvo1.setTitle(null); contactvo1.setPhone(null);
		 * contactvo1.setAlternatePhone(null); contactvo1.setFax(null);
		 * contactvo1.setEmail(null); contactvo1.setAddress(addressvo2);
		 * arraylist.add(contactvo1); }
		 */
		if (s4 != null && !s4.equalsIgnoreCase("-1")) {
			if (addressvo3 == null)
				addressvo3 = new AddressVo();
			addressvo3.setAddress1((String) dynaactionform.get("tAddr1"));
			addressvo3.setAddress2((String) dynaactionform.get("tAddr2"));
			addressvo3.setAddress3((String) dynaactionform.get("tAddr3"));
			addressvo3.setCity((String) dynaactionform.get("tCity"));
			addressvo3.setState((String) dynaactionform.get("tState"));
			addressvo3.setZipCode((String) dynaactionform.get("tZip"));
			if (contactvo2 == null)
				contactvo2 = new ContactVo();
			contactvo2.setFirstName((String) dynaactionform.get("tFirstName"));
			contactvo2.setLastName((String) dynaactionform.get("tLastName"));
			contactvo2.setContactType(3);
			contactvo2.setTitle((String) dynaactionform.get("tTitle"));
			contactvo2.setPhone((String) dynaactionform.get("tPhone"));
			contactvo2.setAlternatePhone((String) dynaactionform
					.get("tMobilePhone"));
			contactvo2.setFax((String) dynaactionform.get("tFax"));
			contactvo2.setEmail((String) dynaactionform.get("tEmail"));
			contactvo2.setAddress(addressvo3);
			arraylist.add(contactvo2);
		}
		/*
		 * if(s4 != null && s4.equalsIgnoreCase("-1")) { if(addressvo3 == null)
		 * addressvo3 = new AddressVo(); addressvo3.setAddress1(null);
		 * addressvo3.setAddress2(null); addressvo3.setAddress3(null);
		 * addressvo3.setCity(null); addressvo3.setState(null);
		 * addressvo3.setZipCode(null); if(contactvo2 == null) contactvo2 = new
		 * ContactVo(); contactvo2.setFirstName(null);
		 * contactvo2.setLastName(null); contactvo2.setContactType(3);
		 * contactvo2.setTitle(null); contactvo2.setPhone(null);
		 * contactvo2.setAlternatePhone(null); contactvo2.setFax(null);
		 * contactvo2.setEmail(null); contactvo2.setAddress(addressvo3);
		 * arraylist.add(contactvo2); }
		 */

		facilityvo.setContactList(arraylist);
		FacilityManager facilitymanager = new FacilityManager(facilityvo);
		String s5 = "";
		String s6 = "";
		FacilityVo facilityvo1 = null;
		try {
			facilitymanager.updateFacility(facilityvo);
			List list1 = facilitymanager.searchBy(null, facilityvo.getDecId(),
					facilityvo.getFacilityType());
			if (list1 != null && list1.size() > 0)
				facilityvo1 = (FacilityVo) list1.get(0);
			httpservletrequest.setAttribute("isComponentEditable", "N");
			s5 = "Updated Facility.";
			s6 = "CONFIRMATION";
		} catch (FacilityException facilityexception) {
			facilityexception.printStackTrace();
			s5 = facilityexception.getMessage();
			s6 = "ERROR";
			if (log.isErrorEnabled())
				log.error(facilityexception);
		}
		if (s5 != null && s5.trim().length() > 0 && s6 != null
				&& s6.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s5);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s6);
		}
		log.debug("AddFacilityAction - update()");
		setDropDown(httpservletrequest);
		setFormForView(true, httpservletrequest, facilityvo1, dynaactionform);
		setMenu(httpservletrequest, "4");
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String s = httpservletrequest.getParameter("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		FacilityManager facilitymanager = new FacilityManager(new FacilityVo());
		FacilityVo facilityvo = facilitymanager.findById(i);
		httpservletrequest.removeAttribute("isComponentEditable");
		setDropDown(httpservletrequest);
		setFormForView(false, httpservletrequest, facilityvo, dynaactionform);
		setMenu(httpservletrequest, "4");
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String s = httpservletrequest.getParameter("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		FacilityManager facilitymanager = new FacilityManager(new FacilityVo());
		FacilityVo facilityvo = facilitymanager.findById(i);
		FacilityEntity.delete(facilityvo);
		setDropDown(httpservletrequest);
		return actionmapping.findForward("deletesuccess");
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String s = httpservletrequest.getParameter("id");
		log.debug((new StringBuilder()).append("Facility Id=").append(s)
				.toString());
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		FacilityManager facilitymanager = new FacilityManager(new FacilityVo());
		FacilityVo facilityvo = facilitymanager.findById(i);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setDropDown(httpservletrequest);
		setFormForView(true, httpservletrequest, facilityvo, dynaactionform);
		setMenu(httpservletrequest, "4");
		return actionmapping.findForward("view");
	}

	public ActionForward viewExist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setDropDown(httpservletrequest);
		setFormForView(true, httpservletrequest, facilityvo, dynaactionform);
		setMenu(httpservletrequest, "4");
		return actionmapping.findForward("view");
	}

	public ActionForward viewFromNav(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String s = null;
		FacilityVo facilityvo = (FacilityVo) httpservletrequest.getSession()
				.getAttribute("FACILITY_OBJECT");
		if (facilityvo != null)
			s = String.valueOf(facilityvo.getId());
		else
			log.debug("Unable to get the facility vo from session()");
		log.debug((new StringBuilder()).append("Facility inner Id=").append(s)
				.toString());
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setDropDown(httpservletrequest);
		setFormForView(true, httpservletrequest, facilityvo, dynaactionform);
		setMenu(httpservletrequest, "4");
		return actionmapping.findForward("view");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = FacilityTypeEnum
				.getDropDownObj();
		com.eespc.tracking.bo.DropDown dropdown1 = BoroughsEnum
				.getDropDownObj();
		com.eespc.tracking.bo.DropDown dropdown2 = StateEnum.getDropDownObj();
		com.eespc.tracking.bo.DropDown dropdown3 = ContactTypeEnum
				.getDropDownObj();
		com.eespc.tracking.bo.DropDown dropdown4 = FolderTypeEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("FACILITY_TYPE", dropdown);
		httpservletrequest.setAttribute("BOROUGHS", dropdown1);
		httpservletrequest.setAttribute("STATES", dropdown2);
		httpservletrequest.setAttribute("CONTACT_TYPE", dropdown3);
		httpservletrequest.setAttribute("FOLDER_TYPE", dropdown4);
		com.eespc.tracking.bo.DropDown dropdown5 = YearEnum.getDropDownObj();
		httpservletrequest.setAttribute("YEARS", dropdown5);
	}

	public ActionForward fuelConsumptionInfoyearconsumtion(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("fcyear");
		System.out.println("Current Year" + s);
		String year = String.valueOf((Integer.parseInt(s) - 1));
		System.out.println("Prev Year" + year);
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = new FacilityVo();
		facilityvo = (FacilityVo) httpsession.getAttribute("FACILITY_OBJECT");
		FacilityEntity facilityentity = new FacilityEntity();

		List list = facilityentity.getFuelConsumptionyearList(
				facilityvo.getId(), year);
		System.out.println("hai senthil" + list.size());
		httpservletresponse.setContentType("text/html");
		PrintWriter printwriter = httpservletresponse.getWriter();

		if (list != null && list.size() > 0) {
			Properties properties = UtilityObject.getRollingAverageInfo(list);
			printwriter.println(properties.get("PREVIOUS_CONSUMPTION"));
		} else {
			printwriter.println("0|0|0|0|0|0|0|0|0|0|0|0");
		}
		printwriter.flush();
		return null;

	}

	public ActionForward o_fuelConsumptionInfoyearconsumtion(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("fcyear");
		System.out.println("Current Year" + s);
		String year = String.valueOf((Integer.parseInt(s) - 1));
		System.out.println("Prev Year" + year);
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = new FacilityVo();
		facilityvo = (FacilityVo) httpsession.getAttribute("FACILITY_OBJECT");
		FacilityEntity facilityentity = new FacilityEntity();
		List list = facilityentity.geto_FuelConsumptionyearList(
				facilityvo.getId(), year);
		System.out.println("Size :" + list.size());
		httpservletresponse.setContentType("text/html");
		PrintWriter printwriter = httpservletresponse.getWriter();

		if (list != null && list.size() > 0) {
			Properties properties = UtilityObject.getRollingAverageInfo(list);
			printwriter.println(properties.get("PREVIOUS_CONSUMPTION"));
		} else {
			printwriter.println("0|0|0|0|0|0|0|0|0|0|0|0");
		}

		printwriter.flush();
		return null;

	}

	public ActionForward fuelConsumptionInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BoilerAction - In fuelConsumptionInfo");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();

		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		FuelConsumptionVo fuelconsumptionvo = new FuelConsumptionVo();
		fuelconsumptionvo.setEntityId(facilityvo.getId());
		String ttype = (String) dynaactionform.get("bctype");
		if (ttype.equals("1")) {
			fuelconsumptionvo.setBctype(ttype);
			fuelconsumptionvo.setYear((String) dynaactionform.get("fcyear"));
			fuelconsumptionvo.setJan(getValue("jan", dynaactionform));
			fuelconsumptionvo.setFeb(getValue("feb", dynaactionform));
			fuelconsumptionvo.setMar(getValue("mar", dynaactionform));
			fuelconsumptionvo.setApr(getValue("apr", dynaactionform));
			fuelconsumptionvo.setMay(getValue("may", dynaactionform));
			fuelconsumptionvo.setJun(getValue("jun", dynaactionform));
			fuelconsumptionvo.setJul(getValue("jul", dynaactionform));
			fuelconsumptionvo.setAug(getValue("aug", dynaactionform));
			fuelconsumptionvo.setSep(getValue("sep", dynaactionform));
			fuelconsumptionvo.setOct(getValue("oct", dynaactionform));
			fuelconsumptionvo.setNov(getValue("nov", dynaactionform));
			fuelconsumptionvo.setDec(getValue("dec", dynaactionform));
			fuelconsumptionvo.setRollingConsumption(getValue("consumption",
					dynaactionform));
			fuelconsumptionvo.setCompliant(UtilityObject
					.convertBoolean((String) dynaactionform.get("compliance")));
			fuelconsumptionvo.setLocked(UtilityObject
					.convertBoolean((String) dynaactionform.get("lock")));

			if (UtilityObject.isNotEmpty((String) dynaactionform
					.get("fuelConsumptionId"))) {
				fuelconsumptionvo.setId(UtilityObject
						.getIntFromString((String) dynaactionform
								.get("fuelConsumptionId")));
				FacilityEntity.updateFuelConsumption(fuelconsumptionvo);
				log.debug("Updated fuel object");
			} else {
				int i = FacilityEntity.addFuelConsumption(fuelconsumptionvo);
				log.debug((new StringBuilder()).append("Added fuel id=")
						.append(i).toString());
			}

		} else {
			fuelconsumptionvo.setBctype(ttype);
			fuelconsumptionvo.setYear((String) dynaactionform.get("o_fcyear"));
			fuelconsumptionvo.setJan(getValue("o_jan", dynaactionform));
			fuelconsumptionvo.setFeb(getValue("o_feb", dynaactionform));
			fuelconsumptionvo.setMar(getValue("o_mar", dynaactionform));
			fuelconsumptionvo.setApr(getValue("o_apr", dynaactionform));
			fuelconsumptionvo.setMay(getValue("o_may", dynaactionform));
			fuelconsumptionvo.setJun(getValue("o_jun", dynaactionform));
			fuelconsumptionvo.setJul(getValue("o_jul", dynaactionform));
			fuelconsumptionvo.setAug(getValue("o_aug", dynaactionform));
			fuelconsumptionvo.setSep(getValue("o_sep", dynaactionform));
			fuelconsumptionvo.setOct(getValue("o_oct", dynaactionform));
			fuelconsumptionvo.setNov(getValue("o_nov", dynaactionform));
			fuelconsumptionvo.setDec(getValue("o_dec", dynaactionform));
			fuelconsumptionvo.setRollingConsumption(getValue("o_consumption",
					dynaactionform));
			fuelconsumptionvo
					.setCompliant(UtilityObject
							.convertBoolean((String) dynaactionform
									.get("o_compliance")));
			fuelconsumptionvo.setLocked(UtilityObject
					.convertBoolean((String) dynaactionform.get("o_lock")));
			if (UtilityObject.isNotEmpty((String) dynaactionform
					.get("o_fuelConsumptionId"))) {
				fuelconsumptionvo.setId(UtilityObject
						.getIntFromString((String) dynaactionform
								.get("o_fuelConsumptionId")));
				FacilityEntity.updateFuelConsumption(fuelconsumptionvo);
				log.debug("Updated fuel object");
			} else {
				int i = FacilityEntity.addFuelConsumption(fuelconsumptionvo);
				log.debug((new StringBuilder()).append("Added fuel id=")
						.append(i).toString());
			}

		}

		facilityvo.setFuelConsumptionList(null);
		facilityvo.seto_FuelConsumptionList(null);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setDropDown(httpservletrequest);
		setFormForView(true, httpservletrequest, facilityvo, dynaactionform);
		setMenu(httpservletrequest, "4");
		return actionmapping.findForward("success");
	}

	public ActionForward deletefuelConsumptionInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BoilerAction - In fuelConsumptionInfo");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		FuelConsumptionVo fuelconsumptionvo = new FuelConsumptionVo();
		fuelconsumptionvo.setEntityId(fuelconsumptionvo.getId());
		int tid = Integer.parseInt((String) dynaactionform
				.get("fueldeleteConsumptionId"));
		FacilityEntity.deletefuelconsumption(tid);
		facilityvo.seto_FuelConsumptionList(null);
		facilityvo.setFuelConsumptionList(null);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setDropDown(httpservletrequest);
		setFormForView(true, httpservletrequest, facilityvo, dynaactionform);
		setMenu(httpservletrequest, "4");
		return actionmapping.findForward("success");
	}

	private void setFormForView(boolean flag,
			HttpServletRequest httpservletrequest, FacilityVo facilityvo,
			DynaActionForm dynaactionform) throws Exception {
		if (facilityvo == null)
			throw new IllegalArgumentException(
					"FacilityVo is null in setFormForView()");
		HttpSession httpsession = httpservletrequest.getSession();
		httpsession.setAttribute("FACILITY_OBJECT", facilityvo);
		log.debug((new StringBuilder()).append("setFormForView(...) ")
				.append(facilityvo.getId()).toString());
		AddressVo addressvo = facilityvo.getFacilityAddress();
		if (addressvo != null) {
			dynaactionform.set("addr1",
					UtilityObject.checkNull(addressvo.getAddress1()));
			dynaactionform.set("addr2",
					UtilityObject.checkNull(addressvo.getAddress2()));
			dynaactionform.set("addr3",
					UtilityObject.checkNull(addressvo.getAddress3()));
			dynaactionform.set("city",
					UtilityObject.checkNull(addressvo.getCity()));
			dynaactionform.set("state",
					UtilityObject.checkNull(addressvo.getState()));
			dynaactionform.set("zip",
					UtilityObject.checkNull(addressvo.getZipCode()));
		}
		dynaactionform.set("id",
				(new StringBuilder()).append(facilityvo.getId()).append("")
						.toString());
		dynaactionform.set("clName", facilityvo.getClientName());
		dynaactionform.set("vPresident", facilityvo.getVicePresident());
		dynaactionform.set("decId", facilityvo.getDecId());
		dynaactionform.set("fuelcaping", facilityvo.getFuelcaping());
		dynaactionform.set("oilcap", facilityvo.getOilcap());
		dynaactionform.set("gascap", facilityvo.getGascap());
		dynaactionform.set("numofbldg", facilityvo.getNumofbldg());
		int i = facilityvo.getFacilityType();
		if (flag) {
			FacilityTypeEnum facilitytypeenum = FacilityTypeEnum.get(i);
			dynaactionform.set("facilityType", facilitytypeenum == null ? ""
					: ((Object) (facilitytypeenum.getName())));
		} else {
			dynaactionform.set("facilityType", (new StringBuilder()).append(i)
					.append("").toString());
		}
		int j = facilityvo.getBorough();
		if (flag) {
			if (j == 1) {
				httpservletrequest.setAttribute("other", "yes");
			} else {
				httpservletrequest.setAttribute("other", "no");
			}

			BoroughsEnum boroughsenum = BoroughsEnum.get(j);

			httpservletrequest.setAttribute("PERMIT_LIST",
					FacilityEntity.getstatepermitList(facilityvo.getId()));

			dynaactionform.set("borough", boroughsenum == null ? ""
					: ((Object) (boroughsenum.getName())));

		} else {

			dynaactionform.set("borough", (new StringBuilder()).append(j)
					.append("").toString());
		}
		dynaactionform.set("county", facilityvo.getCounty());
		dynaactionform.set("town", facilityvo.getTown());

		dynaactionform.set("phone", facilityvo.getPhone());
		dynaactionform.set("fax", facilityvo.getFax());
		List list = facilityvo.getContactList();
		int k = list != null ? list.size() : -99;
		dynaactionform.set("pContact", "");
		dynaactionform.set("sContact", "");
		dynaactionform.set("tContact", "");
		dynaactionform.set("pState", "");
		dynaactionform.set("sState", "");
		dynaactionform.set("tState", "");
		for (int l = 0; l < k; l++) {
			ContactVo contactvo = (ContactVo) list.get(l);
			String s = "p";
			int i1 = contactvo.getContactType();
			if (i1 == ContactTypeEnum.PRIMARY.getCode())
				s = "p";
			else if (i1 == ContactTypeEnum.SECONDARY.getCode())
				s = "s";
			else if (i1 == ContactTypeEnum.TERTIARY.getCode())
				s = "t";
			AddressVo addressvo1 = contactvo.getAddress();
			if (flag) {
				ContactTypeEnum contacttypeenum = ContactTypeEnum.get(i1);
				dynaactionform.set(
						(new StringBuilder()).append(s).append("Contact")
								.toString(), contacttypeenum == null ? ""
								: ((Object) (contacttypeenum.getName())));
			} else {
				dynaactionform.set(
						(new StringBuilder()).append(s).append("Contact")
								.toString(), (new StringBuilder()).append(i1)
								.append("").toString());
			}
			dynaactionform.set(
					(new StringBuilder()).append(s).append("FirstName")
							.toString(), contactvo.getFirstName());
			dynaactionform.set(
					(new StringBuilder()).append(s).append("LastName")
							.toString(), contactvo.getLastName());
			dynaactionform.set((new StringBuilder()).append(s).append("Title")
					.toString(), contactvo.getTitle());
			dynaactionform.set((new StringBuilder()).append(s).append("Addr1")
					.toString(), addressvo1.getAddress1());
			dynaactionform.set((new StringBuilder()).append(s).append("Addr2")
					.toString(), addressvo1.getAddress2());
			dynaactionform.set((new StringBuilder()).append(s).append("Addr3")
					.toString(), addressvo1.getAddress3());
			dynaactionform.set((new StringBuilder()).append(s).append("City")
					.toString(), addressvo1.getCity());
			dynaactionform.set((new StringBuilder()).append(s).append("State")
					.toString(), addressvo1.getState());
			dynaactionform.set((new StringBuilder()).append(s).append("Zip")
					.toString(), addressvo1.getZipCode());
			dynaactionform.set((new StringBuilder()).append(s).append("Phone")
					.toString(), contactvo.getPhone());
			dynaactionform.set(
					(new StringBuilder()).append(s).append("MobilePhone")
							.toString(), contactvo.getAlternatePhone());
			dynaactionform.set((new StringBuilder()).append(s).append("Fax")
					.toString(), contactvo.getFax());
			dynaactionform.set((new StringBuilder()).append(s).append("Email")
					.toString(), contactvo.getEmail());
		}
		//
		List g_list1 = facilityvo.getFuelConsumptionList();
		List o_list1 = facilityvo.geto_FuelConsumptionList();
		System.out.println("In Facilityaction G:" + g_list1.size());
		System.out.println("In Facilityaction O:" + o_list1.size());
		if (g_list1 != null && g_list1.size() > 0) {
			Properties properties = UtilityObject
					.getRollingAverageInfo(g_list1);
			httpservletrequest.setAttribute("hdnConsumption",
					properties.get("TOTAL"));
			httpservletrequest.setAttribute("hdnPreviousConsumption",
					properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("hdnCurrentMonthIndex",
					properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("FUEL_CONSUMPTION", g_list1);
		} else {
			httpservletrequest
					.setAttribute("FUEL_CONSUMPTION", new ArrayList());
			httpservletrequest.setAttribute("hdnConsumption", "");
			httpservletrequest.setAttribute("hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}

		if (o_list1 != null && o_list1.size() > 0) {
			Properties o_properties = UtilityObject
					.getRollingAverageInfo(o_list1);
			httpservletrequest.setAttribute("o_hdnConsumption",
					o_properties.get("TOTAL"));
			httpservletrequest.setAttribute("o_hdnPreviousConsumption",
					o_properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("o_hdnCurrentMonthIndex",
					o_properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("O_FUEL_CONSUMPTION", o_list1);
		} else {
			httpservletrequest.setAttribute("O_FUEL_CONSUMPTION",
					new ArrayList());
			httpservletrequest.setAttribute("o_hdnConsumption", "");
			httpservletrequest.setAttribute("o_hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("o_hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}
		//

		List list1 = facilityvo.getBuildingList();
		if (list1 != null)
			httpsession.setAttribute("BUILDING_LIST", list1);

		List list2 = LsfEntity.getLfsInFacility(facilityvo.getId());
		if (list2 != null)
			httpservletrequest.setAttribute("LSF_LIST", list2);

		List list3 = HwasteEntity.getHwasteInFacility(facilityvo.getId());
		if (list3 != null)
			httpservletrequest.setAttribute("HWASTE_LIST", list3);

	}

	private void setMenu(HttpServletRequest httpservletrequest, String s) {
		HttpSession httpsession = httpservletrequest.getSession();
		UserVo uservo = (UserVo) httpsession.getAttribute("SESSION_USER");
		NavMenu navmenu = new NavMenu(uservo, httpservletrequest);
		ControllerAction.setMenus(httpsession, s, navmenu);
	}

	static Class _mthclass$(String s) throws Exception {
		return Class.forName(s);
	}

	private float getValue(String s, DynaActionForm dynaactionform)
			throws NumberFormatException {
		String s1 = "0.0";
		s1 = (String) dynaactionform.get(s);
		if (s1.equals(""))
			s1 = "0.0";
		if (UtilityObject.isNotEmpty(s1))
			return Float.parseFloat(s1);
		else
			return 0f;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.AddFacilityAction.class);

}