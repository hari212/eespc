// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractDataSourceFactory.java

package com.eespc.tracking.reports.datasources.factories;

import net.sf.jasperreports.engine.JRDataSource;

public abstract class AbstractDataSourceFactory {

	public AbstractDataSourceFactory() {
	}

	public abstract JRDataSource getDataSource();

	public abstract JRDataSource getDataSource(int i);
}
