package com.eespc.tracking.reports.datasources;

import java.util.Hashtable;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package com.eespc.tracking.reports.datasources:
//            AbstractDataSource

public class Exhibit2DataSource extends AbstractDataSource {

	private static Log log;

	public Exhibit2DataSource(List _dataList) {
		super(_dataList);
	}

	public Object getFieldValue(JRField jrField) throws JRException {
		String fieldName = jrField.getName();
		System.out.println("fieldName=" + fieldName);
		if (fieldName == null) {
			throw new JRException("Field Name is Null");
		} else {
			Hashtable _valueObject = (Hashtable) super.dataObject;
			return _valueObject.get(fieldName);
		}
	}

	static {
		log = LogFactory
				.getLog(com.eespc.tracking.reports.datasources.Exhibit2DataSource.class);
	}
}
