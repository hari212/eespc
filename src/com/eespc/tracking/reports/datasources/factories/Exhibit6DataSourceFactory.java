// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Exhibit6DataSourceFactory.java

package com.eespc.tracking.reports.datasources.factories;

import net.sf.jasperreports.engine.JRDataSource;

import com.eespc.tracking.entity.GeneratorEntity;
import com.eespc.tracking.reports.datasources.HashDataSource;

// Referenced classes of package com.eespc.tracking.reports.datasources.factories:
//            AbstractDataSourceFactory

public class Exhibit6DataSourceFactory extends AbstractDataSourceFactory {

	public Exhibit6DataSourceFactory() {
	}

	public JRDataSource getDataSource() {
		return getDataSource(87);
	}

	public JRDataSource getDataSource(int i) {
		java.util.List list = GeneratorEntity.getNycdepReport(i);
		HashDataSource hashdatasource = new HashDataSource(list);
		return hashdatasource;
	}
}
