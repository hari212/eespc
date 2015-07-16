package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.ui.filters.ResourceTypeValidatorIntf;
import com.eespc.tracking.util.SortingUtil;
import com.eespc.tracking.util.UtilityObject;

// Referenced classes of package com.eespc.tracking.bo.myenum:
//            CogenTurbineTypeEnum

public class ResourcesTypeEnum implements Serializable {

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.myenum.ResourcesTypeEnum.class);
	private final int code;
	private final String name;
	private final String descriptor;
	private final String validator;
	private final String viewActionInfo;
	private final String editActionInfo;
	private final String deleteActionInfo;
	private static final List list = new ArrayList();
	public static final ResourcesTypeEnum STACK = new ResourcesTypeEnum(1,
			"Stack", "/eespc/StackInfo.do?methodToCall=", null,
			"/eespc/StackInfo.do?methodToCall=view",
			"/eespc/StackInfo.do?methodToCall=edit",
			"/eespc/StackInfo.do?methodToCall=delete");
	public static final ResourcesTypeEnum STORAGE_TANK = new ResourcesTypeEnum(
			2, "Storage Tank", "/eespc/StorageTankInfo.do?methodToCall=", null,
			"/eespc/StorageTankInfo.do?methodToCall=view",
			"/eespc/StorageTankInfo.do?methodToCall=edit",
			"/eespc/StorageTankInfo.do?methodToCall=delete");
	public static final ResourcesTypeEnum HYDRAULIC_STORAGE_TANK = new ResourcesTypeEnum(
			3, "Hydraulic Storage Tank",
			"/eespc/HydraulicStorageTankInfo.do?methodToCall=", null,
			"/eespc/HydraulicStorageTankInfo.do?methodToCall=view",
			"/eespc/HydraulicStorageTankInfo.do?methodToCall=edit",
			"/eespc/HydraulicStorageTankInfo.do?methodToCall=delete");
	public static final ResourcesTypeEnum BULK_OXYGEN_STORAGE_TANK = new ResourcesTypeEnum(
			4, "Bulk Oxygen Storage Tank",
			"/eespc/BulkOxygenStorageTankInfo.do?methodToCall=", null,
			"/eespc/BulkOxygenStorageTankInfo.do?methodToCall=view",
			"/eespc/BulkOxygenStorageTankInfo.do?methodToCall=edit",
			"/eespc/BulkOxygenStorageTankInfo.do?methodToCall=delete");
	public static final ResourcesTypeEnum BRIDGE = new ResourcesTypeEnum(5,
			"Bridge", "/eespc/BridgeTunnelInfo.do?methodToCall=&type=1",
			"com.eespc.tracking.ui.filters.BridgeTunnelValidator",
			"/eespc/BridgeTunnelInfo.do?methodToCall=view&type=1",
			"/eespc/BridgeTunnelInfo.do?methodToCall=edit&type=1",
			"/eespc/BridgeTunnelInfo.do?methodToCall=delete&type=1");
	public static final ResourcesTypeEnum TUNNEL = new ResourcesTypeEnum(6,
			"Tunnel", "/eespc/BridgeTunnelInfo.do?methodToCall=&type=2",
			"com.eespc.tracking.ui.filters.BridgeTunnelValidator",
			"/eespc/BridgeTunnelInfo.do?methodToCall=view&type=2",
			"/eespc/BridgeTunnelInfo.do?methodToCall=edit&type=2",
			"/eespc/BridgeTunnelInfo.do?methodToCall=delete&type=2");
	public static final ResourcesTypeEnum RPZ = new ResourcesTypeEnum(7,
			"RPZ (Back Flow Preventor)",
			"/eespc/RpzAction.do?methodToCall=reset", null,
			"/eespc/RpzAction.do?methodToCall=view",
			"/eespc/RpzAction.do?methodToCall=edit",
			"/eespc/RpzAction.do?methodToCall=delete");
	public static final ResourcesTypeEnum LANDFILL = new ResourcesTypeEnum(8,
			"Landfill", "/eespc/LandFillsAction.do?methodToCall=reset", null,
			"/eespc/LandFillsAction.do?methodToCall=view",
			"/eespc/LandFillsAction.do?methodToCall=edit",
			"/eespc/LandFillsAction.do?methodToCall=delete");
	public static final ResourcesTypeEnum PAINT_SPRAY = new ResourcesTypeEnum(
			9, "Paint Spray Booth",
			"/eespc/PaintSprayAction.do?methodToCall=reset", null,
			"/eespc/PaintSprayAction.do?methodToCall=view",
			"/eespc/PaintSprayAction.do?methodToCall=edit",
			"/eespc/PaintSprayAction.do?methodToCall=delete");
	public static final ResourcesTypeEnum BOILER = new ResourcesTypeEnum(10,
			"Boiler", "/eespc/Boiler.do?methodToCall=", null,
			"/eespc/Boiler.do?methodToCall=view",
			"/eespc/Boiler.do?methodToCall=edit",
			"/eespc/Boiler.do?methodToCall=delete");
	public static final ResourcesTypeEnum GNERATOR = new ResourcesTypeEnum(11,
			"Generator", "/eespc/Generator.do?methodToCall=", null,
			"/eespc/Generator.do?methodToCall=view",
			"/eespc/Generator.do?methodToCall=edit",
			"/eespc/Generator.do?methodToCall=delete");
	public static final ResourcesTypeEnum COGEN;
	public static final ResourcesTypeEnum TURBINES;
	public static final ResourcesTypeEnum ETO = new ResourcesTypeEnum(14,
			"ETO", "/eespc/Eto.do?methodToCall=", null,
			"/eespc/Eto.do?methodToCall=view",
			"/eespc/Eto.do?methodToCall=edit",
			"/eespc/Eto.do?methodToCall=delete");
	public static final ResourcesTypeEnum ELEVATOR = new ResourcesTypeEnum(15,
			"Elevator", "/eespc/ElevatorAction.do?methodToCall=reset", null,
			"/eespc/ElevatorAction.do?methodToCall=view",
			"/eespc/ElevatorAction.do?methodToCall=edit",
			"/eespc/ElevatorAction.do?methodToCall=delete");
	public static final ResourcesTypeEnum INCINERATOR = new ResourcesTypeEnum(
			16, "Incinerator",
			"/eespc/IncineratorAction.do?methodToCall=reset", null,
			"/eespc/IncineratorAction.do?methodToCall=view",
			"/eespc/IncineratorAction.do?methodToCall=edit",
			"/eespc/IncineratorAction.do?methodToCall=delete");
	public static final ResourcesTypeEnum CREMATORIES = new ResourcesTypeEnum(
			17, "Crematories",
			"/eespc/IncineratorAction.do?methodToCall=reset", null,
			"/eespc/IncineratorAction.do?methodToCall=view",
			"/eespc/IncineratorAction.do?methodToCall=edit",
			"/eespc/IncineratorAction.do?methodToCall=delete");
	public static final ResourcesTypeEnum CHILLER = new ResourcesTypeEnum(18,
			"Chiller", "/eespc/ChillerAction.do?methodToCall=reset", null,
			"/eespc/ChillerAction.do?methodToCall=view",
			"/eespc/ChillerAction.do?methodToCall=edit",
			"/eespc/ChillerAction.do?methodToCall=delete");
	public static final ResourcesTypeEnum ABSORBER = new ResourcesTypeEnum(19,
			"Absorber", "/eespc/ChillerAction.do?methodToCall=reset", null,
			"/eespc/ChillerAction.do?methodToCall=view",
			"/eespc/ChillerAction.do?methodToCall=edit",
			"/eespc/ChillerAction.do?methodToCall=delete");
	public static final ResourcesTypeEnum FUMEHOOD = new ResourcesTypeEnum(20,
			"Fumehood", "/eespc/FumehoodAction.do?methodToCall=reset", null,
			"/eespc/FumehoodAction.do?methodToCall=view",
			"/eespc/FumehoodAction.do?methodToCall=edit",
			"/eespc/FumehoodAction.do?methodToCall=delete");
	public static final ResourcesTypeEnum PRESS = new ResourcesTypeEnum(21,
			"Printing Press", "/eespc/PressAction.do?methodToCall=reset", null,
			"/eespc/PressAction.do?methodToCall=view",
			"/eespc/PressAction.do?methodToCall=edit",
			"/eespc/PressAction.do?methodToCall=delete");
	public static final ResourcesTypeEnum OTHERS = new ResourcesTypeEnum(22,
			"Miscellaneous  ", "/eespc/OthersAction.do?methodToCall=reset",
			null, "/eespc/OthersAction.do?methodToCall=view",
			"/eespc/OthersAction.do?methodToCall=edit",
			"/eespc/OthersAction.do?methodToCall=delete");
	public static final ResourcesTypeEnum CHILLERWITHAIRCONDITIONINGREFRIGATION = new ResourcesTypeEnum(
			23, "AirConditioning Unit",
			"/eespc/ChillerRefrigationAction.do?methodToCall=reset", null,
			"/eespc/ChillerRefrigationAction.do?methodToCall=view",
			"/eespc/ChillerRefrigationAction.do?methodToCall=edit",
			"/eespc/ChillerRefrigationAction.do?methodToCall=delete");
	public static final ResourcesTypeEnum FIREALARM = new ResourcesTypeEnum(24,
			"Fire Alarm", "/eespc/FireAlarmAction.do?methodToCall=reset", null,
			"/eespc/FireAlarmAction.do?methodToCall=view",
			"/eespc/FireAlarmAction.do?methodToCall=edit",
			"/eespc/FireAlarmAction.do?methodToCall=delete");
	public static final ResourcesTypeEnum PLACEOFASSEMBLY = new ResourcesTypeEnum(
			25, "Place of Assembly",
			"/eespc/PlaceofAssemblyAction.do?methodToCall=reset", null,
			"/eespc/PlaceofAssemblyAction.do?methodToCall=view",
			"/eespc/PlaceofAssemblyAction.do?methodToCall=edit",
			"/eespc/PlaceofAssemblyAction.do?methodToCall=delete");

	private ResourcesTypeEnum(int i, String s, String s1, String s2, String s3) {
		code = i;
		name = s;
		descriptor = s1;
		validator = s2;
		viewActionInfo = s3;
		editActionInfo = "";
		deleteActionInfo = "";
		list.add(this);
	}

	private ResourcesTypeEnum(int i, String s, String s1, String s2, String s3,
			String s4, String s5) {
		code = i;
		name = s;
		descriptor = s1;
		validator = s2;
		viewActionInfo = s3;
		editActionInfo = s4;
		deleteActionInfo = s5;
		list.add(this);
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public String getValidator() {
		return validator;
	}

	public String getViewActionInfo() {
		return viewActionInfo;
	}

	public String getEditActionInfo() {
		return editActionInfo;
	}

	public String getDeleteActionInfo() {
		return deleteActionInfo;
	}

	public static ResourcesTypeEnum get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			ResourcesTypeEnum resourcestypeenum = (ResourcesTypeEnum) list
					.get(k);
			if (resourcestypeenum.getCode() == i) {
				return resourcestypeenum;
			}
		}

		return null;
	}

	public static ResourcesTypeEnum parse(String s) {
		if (s == null) {
			return null;
		}
		int i = size();
		for (int j = 0; j < i; j++) {
			ResourcesTypeEnum resourcestypeenum = (ResourcesTypeEnum) list
					.get(j);
			if (resourcestypeenum.getName().equals(s)) {
				return resourcestypeenum;
			}
		}

		return null;
	}

	public static int size() {
		return list.size();
	}

	public String toString() {
		return (new StringBuilder()).append(getCode()).append(" | ")
				.append(getName()).append(" | ").append(getDescriptor())
				.toString();
	}

	public static DropDown getDropDownObj(HttpServletRequest httpservletrequest)
			throws Exception {
		SortingUtil sortingutil = new SortingUtil();
		try {
			sortingutil.sort(list,
					com.eespc.tracking.bo.myenum.ResourcesTypeEnum.class,
					"getName", true);
		} catch (Exception exception) {
			log.error("**Sorting Error**", exception);
		}
		int i = list.size();
		DropDown dropdown = null;
		ArrayList arraylist = new ArrayList();
		arraylist.add(NameValueBean.getPleaseSelect());
		for (int j = 0; j < i; j++) {
			ResourcesTypeEnum resourcestypeenum = (ResourcesTypeEnum) list
					.get(j);
			String s = "";
			String s1 = "";
			String s2 = null;
			if (resourcestypeenum == null) {
				continue;
			}
			s = resourcestypeenum.getDescriptor();
			s1 = resourcestypeenum.getName();
			s2 = resourcestypeenum.getValidator();
			boolean flag = true;
			if (UtilityObject.isNotEmpty(s2)) {
				ResourceTypeValidatorIntf resourcetypevalidatorintf = UtilityObject
						.getValidator(s2);
				if (resourcetypevalidatorintf != null) {
					try {
						flag = resourcetypevalidatorintf
								.isValidToShow(httpservletrequest);
						log.debug((new StringBuilder())
								.append("after isValidToShow() got ")
								.append(flag).toString());
					} catch (TrackingException trackingexception) {
						log.error(trackingexception);
						flag = false;
					}
				} else {
					log.debug("Validator Interface is empty");
				}
			}
			if (flag) {
				arraylist.add(new NameValueBean(s1, s));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public static String getJavaScript() {
		return getJavaScript(false);
	}

	public static String getJavaScript(boolean flag) {
		String s = "document.forms[0].id.value=id;";
		String s1 = System.getProperty("line.separator");
		String s2 = "   ";
		String s3 = "      ";
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("<script>").append(s1);
		stringbuffer.append("function deleteSource(id, type)").append(s1);
		stringbuffer.append("{").append(s1);
		stringbuffer.append(s2)
				.append("var where_to= confirm(\"Do you want delete this??\")")
				.append(s1);
		stringbuffer.append(s2).append(s).append(s1);
		int i = size();
		for (int j = 0; j < i; j++) {
			ResourcesTypeEnum resourcestypeenum = (ResourcesTypeEnum) list
					.get(j);
			if (j == 0) {
				stringbuffer.append(s2)
						.append("if(where_to== true){if (type ==")
						.append(resourcestypeenum.getCode()).append("){")
						.append(s1);
			} else {
				stringbuffer.append(s2).append("else if (type ==")
						.append(resourcestypeenum.getCode()).append("){")
						.append(s1);
			}
			stringbuffer.append(s3).append("document.forms[0].action='");
			stringbuffer.append(resourcestypeenum.getDeleteActionInfo())
					.append("';").append(s1);
			stringbuffer.append(s2).append("}");
		}

		stringbuffer.append(s1).append(s2)
				.append("document.forms[0].submit();").append(s1);
		stringbuffer.append("}else{}}").append(s1);
		if (flag) {
			stringbuffer.append("function editSource(id, type)").append(s1);
		} else {
			stringbuffer.append("function viewSource(id, type)").append(s1);
		}
		stringbuffer.append("{").append(s1);
		stringbuffer.append(s2).append(s).append(s1);
		int k = size();
		for (int l = 0; l < k; l++) {
			ResourcesTypeEnum resourcestypeenum1 = (ResourcesTypeEnum) list
					.get(l);
			if (l == 0) {
				stringbuffer.append(s2).append("if (type ==")
						.append(resourcestypeenum1.getCode()).append("){")
						.append(s1);
			} else {
				stringbuffer.append(s2).append("else if (type ==")
						.append(resourcestypeenum1.getCode()).append("){")
						.append(s1);
			}
			stringbuffer.append(s3).append("document.forms[0].action='");
			if (flag) {
				stringbuffer.append(resourcestypeenum1.getEditActionInfo())
						.append("';").append(s1);
			} else {
				stringbuffer.append(resourcestypeenum1.getViewActionInfo())
						.append("';").append(s1);
			}
			stringbuffer.append(s2).append("}");
		}

		stringbuffer.append(s1).append(s2)
				.append("document.forms[0].submit();").append(s1);
		stringbuffer.append("}").append(s1);
		stringbuffer.append("</script>").append(s1);
		return stringbuffer.toString();
	}

	static {
		COGEN = new ResourcesTypeEnum(
				12,
				"Co Gen Engines",
				(new StringBuilder())
						.append("/eespc/CoGenTurbine.do?methodToCall=&type=")
						.append(CogenTurbineTypeEnum.COGEN.getCode())
						.toString(),
				null,
				(new StringBuilder())
						.append("/eespc/CoGenTurbine.do?methodToCall=view&type=")
						.append(CogenTurbineTypeEnum.COGEN.getCode())
						.toString(),
				(new StringBuilder())
						.append("/eespc/CoGenTurbine.do?methodToCall=edit&type=")
						.append(CogenTurbineTypeEnum.COGEN.getCode())
						.toString(),
				(new StringBuilder())
						.append("/eespc/CoGenTurbine.do?methodToCall=delete&type=")
						.append(CogenTurbineTypeEnum.COGEN.getCode())
						.toString());
		TURBINES = new ResourcesTypeEnum(
				13,
				"Turbines",
				(new StringBuilder())
						.append("/eespc/CoGenTurbine.do?methodToCall=&type=")
						.append(CogenTurbineTypeEnum.TURBINES.getCode())
						.toString(),
				null,
				(new StringBuilder())
						.append("/eespc/CoGenTurbine.do?methodToCall=view&type=")
						.append(CogenTurbineTypeEnum.TURBINES.getCode())
						.toString(),
				(new StringBuilder())
						.append("/eespc/CoGenTurbine.do?methodToCall=edit&type=")
						.append(CogenTurbineTypeEnum.TURBINES.getCode())
						.toString(),
				(new StringBuilder())
						.append("/eespc/CoGenTurbine.do?methodToCall=delete&type=")
						.append(CogenTurbineTypeEnum.TURBINES.getCode())
						.toString());
	}
}
