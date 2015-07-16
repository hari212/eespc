// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Exhibit2DataSourceFactory.java

package com.eespc.tracking.reports.datasources.factories;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;

import com.eespc.tracking.bo.AddressVo;
import com.eespc.tracking.bo.BuildingResourcesVo;
import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.entity.BuildingEntity;
import com.eespc.tracking.reports.datasources.Exhibit2DataSource;

// Referenced classes of package com.eespc.tracking.reports.datasources.factories:
//            AbstractDataSourceFactory

public class Exhibit2DataSourceFactory extends AbstractDataSourceFactory {

	public Exhibit2DataSourceFactory() {
	}

	public JRDataSource getDataSource() {
		byte byte0 = 81;
		return getDataSource(byte0);
	}

	private void getResourceCounts(List list, List list1) {
		int i = list1 != null ? list1.size() : 0;
		for (int j = 0; j < i; j++) {
			BuildingVo buildingvo = (BuildingVo) list1.get(j);
			List list2 = BuildingEntity.getResources(buildingvo.getId());
			int k = list2 != null ? list2.size() : 0;
			int l = 0;
			int i1 = 0;
			int j1 = 0;
			int k1 = 0;
			int l1 = 0;
			int i2 = 0;
			int j2 = 0;
			int k2 = 0;
			int l2 = 0;
			int i3 = 0;
			int j3 = 0;
			int k3 = 0;
			int l3 = 0;
			int i4 = 0;
			int j4 = 0;
			int k4 = 0;
			int l4 = 0;
			int i5 = 0;
			int j5 = 0;
			int k5 = 0;
			for (int l5 = 0; l5 < k; l5++) {
				BuildingResourcesVo buildingresourcesvo = (BuildingResourcesVo) list2
						.get(l5);
				int i6 = buildingresourcesvo.getEntityType();
				if (i6 == ResourcesTypeEnum.ELEVATOR.getCode()) {
					l++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.BOILER.getCode()) {
					i1++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.CHILLER.getCode()
						|| i6 == ResourcesTypeEnum.ABSORBER.getCode()) {
					j1++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.GNERATOR.getCode()) {
					k1++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.RPZ.getCode()) {
					l1++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.FUMEHOOD.getCode()) {
					i2++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.STORAGE_TANK.getCode()) {
					j2++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.STACK.getCode()) {
					k2++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.PAINT_SPRAY.getCode()) {
					l2++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.BULK_OXYGEN_STORAGE_TANK.getCode()) {
					i3++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.HYDRAULIC_STORAGE_TANK.getCode()) {
					j3++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.BRIDGE.getCode()
						|| i6 == ResourcesTypeEnum.TUNNEL.getCode()) {
					k3++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.COGEN.getCode()
						|| i6 == ResourcesTypeEnum.TURBINES.getCode()) {
					l3++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.PRESS.getCode()) {
					i4++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.LANDFILL.getCode()) {
					j4++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.INCINERATOR.getCode()
						|| i6 == ResourcesTypeEnum.CREMATORIES.getCode()) {
					k4++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.OTHERS.getCode()) {
					l4++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.ETO.getCode())
					i5++;
				if (i6 == ResourcesTypeEnum.FIREALARM.getCode()) {
					j5++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.CHILLERWITHAIRCONDITIONINGREFRIGATION
						.getCode())
					k5++;
			}

			Hashtable hashtable = new Hashtable();
			AddressVo addressvo = buildingvo.getBuildingAddress();
			String s = "";
			if (addressvo != null)
				s = addressvo.getFormatted_Address();
			String s1 = buildingvo.getBuildingName();
			hashtable.put("NO",
					(new StringBuffer(String.valueOf(j + 1))).toString());
			hashtable.put("BUILDING_NAME", s1 == null ? "" : ((Object) (s1)));
			System.out.println();
			hashtable.put("BUILDING_ADDRESS", s);
			hashtable.put("ELEVATOR", new Integer(l));
			hashtable.put("BOILER", new Integer(i1));
			hashtable.put("CHILLER", new Integer(j1));
			hashtable.put("FUMEHOOD", new Integer(i2));
			hashtable.put("GENERATOR", new Integer(k1));
			hashtable.put("RPZ", new Integer(l1));
			hashtable.put("STORAGE_TANK", new Integer(j2));
			hashtable.put("STACK", new Integer(k2));
			hashtable.put("PAINT_SPRAY", new Integer(l2));
			hashtable.put("BULK_OXYGEN_STORAGE_TANK", new Integer(i3));
			hashtable.put("HYDRAULIC_STORAGE_TANK", new Integer(j3));
			hashtable.put("BRIDGE", new Integer(k3));
			hashtable.put("COGEN", new Integer(l3));
			hashtable.put("PRESS", new Integer(i4));
			hashtable.put("LANDFILL", new Integer(j4));
			hashtable.put("INCINERATOR", new Integer(k4));
			hashtable.put("OTHERS", new Integer(l4));
			hashtable.put("ETO", new Integer(i5));
			hashtable.put("FIREALARM", new Integer(j5));
			hashtable.put("REFRIGERATION", new Integer(k5));
			list.add(hashtable);
		}

	}

	private List getResource_Counts(List list, List list1) {
		int i = list1 != null ? list1.size() : 0;
		for (int j = 0; j < i; j++) {
			BuildingVo buildingvo = (BuildingVo) list1.get(j);
			List list2 = BuildingEntity.getResources(buildingvo.getId());
			int k = list2 != null ? list2.size() : 0;
			int l = 0;
			int i1 = 0;
			int j1 = 0;
			int k1 = 0;
			int l1 = 0;
			int i2 = 0;
			int j2 = 0;
			int k2 = 0;
			int l2 = 0;
			int i3 = 0;
			int j3 = 0;
			int k3 = 0;
			int l3 = 0;
			int i4 = 0;
			int j4 = 0;
			int k4 = 0;
			int l4 = 0;
			int i5 = 0;
			int j5 = 0;
			int k5 = 0;
			for (int l5 = 0; l5 < k; l5++) {
				BuildingResourcesVo buildingresourcesvo = (BuildingResourcesVo) list2
						.get(l5);
				int i6 = buildingresourcesvo.getEntityType();
				if (i6 == ResourcesTypeEnum.ELEVATOR.getCode()) {
					l++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.BOILER.getCode()) {
					i1++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.CHILLER.getCode()
						|| i6 == ResourcesTypeEnum.ABSORBER.getCode()) {
					j1++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.GNERATOR.getCode()) {
					k1++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.RPZ.getCode()) {
					l1++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.FUMEHOOD.getCode()) {
					i2++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.STORAGE_TANK.getCode()) {
					j2++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.STACK.getCode()) {
					k2++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.PAINT_SPRAY.getCode()) {
					l2++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.BULK_OXYGEN_STORAGE_TANK.getCode()) {
					i3++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.HYDRAULIC_STORAGE_TANK.getCode()) {
					j3++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.BRIDGE.getCode()
						|| i6 == ResourcesTypeEnum.TUNNEL.getCode()) {
					k3++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.COGEN.getCode()
						|| i6 == ResourcesTypeEnum.TURBINES.getCode()) {
					l3++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.PRESS.getCode()) {
					i4++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.LANDFILL.getCode()) {
					j4++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.INCINERATOR.getCode()
						|| i6 == ResourcesTypeEnum.CREMATORIES.getCode()) {
					k4++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.OTHERS.getCode()) {
					l4++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.ETO.getCode())
					i5++;
				if (i6 == ResourcesTypeEnum.FIREALARM.getCode()) {
					j5++;
					continue;
				}
				if (i6 == ResourcesTypeEnum.CHILLERWITHAIRCONDITIONINGREFRIGATION
						.getCode())
					k5++;
			}

			Hashtable hashtable = new Hashtable();
			AddressVo addressvo = buildingvo.getBuildingAddress();
			String s = "";
			if (addressvo != null)
				s = addressvo.getFormatted_Address();
			String s1 = buildingvo.getBuildingName();
			hashtable.put("NO",
					(new StringBuffer(String.valueOf(j + 1))).toString());
			hashtable.put("BUILDING_NAME", s1 == null ? "" : ((Object) (s1)));
			System.out.println();
			hashtable.put("BUILDING_ADDRESS", s);
			hashtable.put("ELEVATOR", new Integer(l));
			hashtable.put("BOILER", new Integer(i1));
			hashtable.put("CHILLER", new Integer(j1));
			hashtable.put("FUMEHOOD", new Integer(i2));
			hashtable.put("GENERATOR", new Integer(k1));
			hashtable.put("RPZ", new Integer(l1));
			hashtable.put("STORAGE_TANK", new Integer(j2));
			hashtable.put("STACK", new Integer(k2));
			hashtable.put("PAINT_SPRAY", new Integer(l2));
			hashtable.put("BULK_OXYGEN_STORAGE_TANK", new Integer(i3));
			hashtable.put("HYDRAULIC_STORAGE_TANK", new Integer(j3));
			hashtable.put("BRIDGE", new Integer(k3));
			hashtable.put("COGEN", new Integer(l3));
			hashtable.put("PRESS", new Integer(i4));
			hashtable.put("LANDFILL", new Integer(j4));
			hashtable.put("INCINERATOR", new Integer(k4));
			hashtable.put("OTHERS", new Integer(l4));
			hashtable.put("ETO", new Integer(i5));
			hashtable.put("FIREALARM", new Integer(j5));
			hashtable.put("REFRIGERATION", new Integer(k5));
			list.add(hashtable);
		}

		return list;
	}

	public List getData(int i) {
		ArrayList arraylist = new ArrayList();
		List list = BuildingEntity.getBuildingsInFacility(i);
		return getResource_Counts(arraylist, list);
	}

	public JRDataSource getDataSource(int i) {
		ArrayList arraylist = new ArrayList();
		List list = BuildingEntity.getBuildingsInFacility(i);
		getResourceCounts(arraylist, list);
		Exhibit2DataSource exhibit2datasource = new Exhibit2DataSource(
				arraylist);
		return exhibit2datasource;
	}
}
