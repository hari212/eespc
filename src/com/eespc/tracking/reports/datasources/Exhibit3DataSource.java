// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Exhibit3DataSource.java

package com.eespc.tracking.reports.datasources;

import java.util.Hashtable;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package com.eespc.tracking.reports.datasources:
//            AbstractDataSource

public class Exhibit3DataSource extends AbstractDataSource {

	public Exhibit3DataSource(List _dataList) {
		super(_dataList);
	}

	public Object getFieldValue(JRField jrField) throws JRException {
		String fieldName = jrField.getName();
		if (fieldName == null) {
			throw new JRException("Field Name is Null");
		} else {
			Hashtable _valueObject = (Hashtable) super.dataObject;
			return _valueObject.get(fieldName);
		}
	}

	private static Log log;

	static {
		log = LogFactory
				.getLog(com.eespc.tracking.reports.datasources.Exhibit3DataSource.class);
	}
}
