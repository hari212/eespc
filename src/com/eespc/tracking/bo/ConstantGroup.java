
package com.eespc.tracking.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConstantGroup
    implements Serializable
{

    private ConstantGroup(int i, String s)
    {
        code = i;
        name = s;
        if(s == null)
        {
            throw new IllegalArgumentException("Name is required");
        } else
        {
            list.add(this);
            return;
        }
    }

    public int getCode()
    {
        return code;
    }

    public String getName()
    {
        return name;
    }

    public String getDescriptor()
    {
        return getName();
    }

    public static List getAll()
    {
        return list;
    }

    public static ConstantGroup get(int i)
    {
        int j = list.size();
        for(int k = 0; k < j; k++)
        {
            ConstantGroup constantgroup = (ConstantGroup)list.get(k);
            if(constantgroup.getCode() == i)
                return constantgroup;
        }

        return null;
    }

    public static ConstantGroup parse(String s)
    {
        if(s == null)
            return null;
        int i = list.size();
        for(int j = 0; j < i; j++)
        {
            ConstantGroup constantgroup = (ConstantGroup)list.get(j);
            if(constantgroup.getName().equals(s))
                return constantgroup;
        }

        return null;
    }

    public static boolean contains(String s)
    {
        ConstantGroup constantgroup = parse(s);
        return constantgroup != null;
    }

    public static int size()
    {
        return list.size();
    }

    public String toString()
    {
        return getName();
    }

    private static List list = new ArrayList();
    private final int code;
    private final String name;
    public static final ConstantGroup STATE_PROV_CODE = new ConstantGroup(1, "State Prov Code");
    public static final ConstantGroup FACILITY_TYPE = new ConstantGroup(2, "Facility Type");
    public static final ConstantGroup BOROUGHS = new ConstantGroup(3, "Boroughs");
    public static final ConstantGroup TANK_LOCATION = new ConstantGroup(4, "Tank Location");
    public static final ConstantGroup PRODUCT_STORED = new ConstantGroup(5, "Product Stored");
    public static final ConstantGroup TANK_TYPE = new ConstantGroup(6, "Tank Type");
    public static final ConstantGroup PIPING_TYPE = new ConstantGroup(7, "Piping Type");
    public static final ConstantGroup INTERNAL_PROTECTION = new ConstantGroup(8, "Internal Protection");
    public static final ConstantGroup EXTERNAL_PROTECTION = new ConstantGroup(9, "External Protection");
    public static final ConstantGroup PIPE_LOCATION = new ConstantGroup(10, "Pipe Location");
    public static final ConstantGroup TANK_OPERATING_STATUS = new ConstantGroup(11, "Tank Operating Status");
    public static final ConstantGroup SECONDARY_CONTAINMENT = new ConstantGroup(12, "Secondary Containment");
    public static final ConstantGroup OVERFILL_PREVENTION = new ConstantGroup(13, "Overfill Prevention");
    public static final ConstantGroup RESOURCES_TYPE = new ConstantGroup(14, "Resources Type");
    public static final ConstantGroup HYDRAULIC_STORAGE_USAGE = new ConstantGroup(15, "Hydraulic Storage Tank Usage.");
    public static final ConstantGroup SPRAY_BOOTH_CHEMICALS = new ConstantGroup(16, "Spray Booth Chemicals.");
    public static final ConstantGroup BOILER_USE = new ConstantGroup(17, "Boiler Primary Use.");
    public static final ConstantGroup FUNACE_OIL_TYPE = new ConstantGroup(18, "Furnace Oil Type.");
    public static final ConstantGroup INCINERATOR_WASTE_TYPE = new ConstantGroup(19, "Incinerator Waste Type.");
    public static final ConstantGroup CONTROL_SYSTEM_TYPE = new ConstantGroup(20, "Control System Type.");
    public static final ConstantGroup COGEN_USE = new ConstantGroup(21, "CoGen/Turbine Primary Use.");
    public static final ConstantGroup YESNO = new ConstantGroup(22, "Yes No.");
    public static final ConstantGroup FUME_HOOD_CHEMICALS = new ConstantGroup(23, "Fume Hood Chemicals.");
    public static final ConstantGroup UPLOAD_FOLDER = new ConstantGroup(24, "Upload Folder");
    public static final ConstantGroup TANK_CORROSIONPROTECTION = new ConstantGroup(25, "Corrosion Protecton Type");
    public static final ConstantGroup SPILL_PREVENT_METHOD = new ConstantGroup(26, "Spill Prevention Method");
    public static final ConstantGroup HAZARDOUS_WASTE_CATEGORY = new ConstantGroup(27, "Hazardous Waste Category");
    public static final ConstantGroup WASTE_TYPE = new ConstantGroup(28, "Hazardous Waste Type");
    public static final ConstantGroup WASTE_TYPE1 = new ConstantGroup(28, "Hazardous Waste Type1");
    public static final ConstantGroup WASTE_TYPE2 = new ConstantGroup(28, "Hazardous Waste Type2");
    public static final ConstantGroup WASTE_TYPE3 = new ConstantGroup(28, "Hazardous Waste Type3");
    public static final ConstantGroup WASTE_TYPE4 = new ConstantGroup(28, "Hazardous Waste Type4");
    public static final ConstantGroup TANK_EXTERNAL_PROTECT = new ConstantGroup(29, "Tank External Protection");
    public static final ConstantGroup TANK_DISPENSER = new ConstantGroup(30, "Tank Dispenser");
    public static final ConstantGroup TANK_LEAK_DETECTION = new ConstantGroup(31, "Tank Leak Detection");
    public static final ConstantGroup PIPE_SECONDARY_CONTAINMENT = new ConstantGroup(32, "Piping Secondary Containment");
    public static final ConstantGroup TANK_FUELSUPPLIEDTO = new ConstantGroup(33, "Fuel Supplied To");
    public static final ConstantGroup TANK_LOCATION_BASE = new ConstantGroup(34, "Tank Regional Location");
	public static final ConstantGroup OTHER_TANKS = new ConstantGroup(35, "Other Tanks");
	public static final ConstantGroup OTHER_TANKS_TYPE = new ConstantGroup(36, "Other Tanks Type");
	public static final ConstantGroup HYDRAULIC_TANK_MATERIAL = new ConstantGroup(37, "Tank Material");
	public static final ConstantGroup CHILLER_OPERATED_BY = new ConstantGroup(38, "Chiller Opertaion");
	public static final ConstantGroup PA_TYPE = new ConstantGroup(39, "PA Type");
	public static final ConstantGroup VIOLATION_TYPE = new ConstantGroup(40, "Violation Type");
	public static final ConstantGroup INSPECTION_TYPE = new ConstantGroup(41, "Inspection Type");
}