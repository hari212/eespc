// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Exhibit7DataSourceFactory.java

package com.eespc.tracking.reports.datasources.factories;

import net.sf.jasperreports.engine.JRDataSource;

import com.eespc.tracking.entity.StorageTankEntity;
import com.eespc.tracking.reports.datasources.HashDataSource;

// Referenced classes of package com.eespc.tracking.reports.datasources.factories:
//            AbstractDataSourceFactory

public class Exhibit7DataSourceFactory extends AbstractDataSourceFactory {

	public Exhibit7DataSourceFactory() {
	}

	public JRDataSource getDataSource() {
		return getDataSource(87);
	}

	public JRDataSource getDataSource(int facilityId) {
		java.util.List genList = StorageTankEntity.getNysdecReport(facilityId);
		HashDataSource ds = new HashDataSource(genList);
		return ds;
	}
}
