// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractDataSource.java

package com.eespc.tracking.reports.datasources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public abstract class AbstractDataSource implements JRDataSource, Serializable {

	public AbstractDataSource(List _dataList) {
		dataList = new ArrayList();
		index = 0;
		dataObject = null;
		if (_dataList == null) {
			throw new IllegalArgumentException("Data List cannot be NULL");
		} else {
			dataList = _dataList;
			return;
		}
	}

	public boolean next() throws JRException {
		if (index < dataList.size()) {
			dataObject = dataList.get(index);
			index++;
			return true;
		} else {
			return false;
		}
	}

	public abstract Object getFieldValue(JRField jrfield) throws JRException;

	protected List dataList;
	protected int index;
	protected Object dataObject;
}
