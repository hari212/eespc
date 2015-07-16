// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Exhibit4DataSourceFactory.java

package com.eespc.tracking.reports.datasources.factories;

import net.sf.jasperreports.engine.JRDataSource;

import com.eespc.tracking.entity.BoilerEntity;
import com.eespc.tracking.reports.datasources.HashDataSource;

// Referenced classes of package com.eespc.tracking.reports.datasources.factories:
//            AbstractDataSourceFactory

public class Exhibit4DataSourceFactory extends AbstractDataSourceFactory {

	public Exhibit4DataSourceFactory() {
	}

	public JRDataSource getDataSource() {
		return getDataSource(87);
	}

	public JRDataSource getDataSource(int facilityId) {
		java.util.List boilerList = BoilerEntity
				.getNycdobStatusReport(facilityId);
		HashDataSource ds = new HashDataSource(boilerList);
		return ds;
	}
}
