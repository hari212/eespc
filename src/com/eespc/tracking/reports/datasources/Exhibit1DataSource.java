package com.eespc.tracking.reports.datasources;

import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.AddressVo;
import com.eespc.tracking.bo.BuildingVo;

// Referenced classes of package com.eespc.tracking.reports.datasources:
//            AbstractDataSource

public class Exhibit1DataSource extends AbstractDataSource {

	private static Log log;

	public Exhibit1DataSource(List list) {
		super(list);
	}

	public Object getFieldValue(JRField jrfield) throws JRException {
		String s = jrfield.getName();
		String s1 = "";
		if (s == null) {
			throw new JRException("Field Name is Null");
		}
		BuildingVo buildingvo = (BuildingVo) super.dataObject;
		if (s.equalsIgnoreCase("No")) {
			s1 = String.valueOf(super.index + 1);
		} else if (s.equalsIgnoreCase("BUILDINGNAME")) {
			s1 = buildingvo.getBuildingName();
		} else if (s.equalsIgnoreCase("bldgAddress")) {
			AddressVo addressvo = buildingvo.getBuildingAddress();
			if (addressvo != null) {
				s1 = addressvo.getFormattedAddress();
			}
		} else if (s.equalsIgnoreCase("DOBBINNUMBER")) {
			s1 = buildingvo.getDobBinNumber();
		} else if (s.equalsIgnoreCase("BLOCKNUMBER")) {
			s1 = buildingvo.getBlockNumber();
		} else if (s.equalsIgnoreCase("LOTNUMBER")) {
			s1 = buildingvo.getLotNumber();
		} else if (s.equalsIgnoreCase("BLDGSIXSTORIES")) {
			s1 = buildingvo.getbldtall();
		} else {
			log.debug("Exhibit1DataSource.getFieldValue(...) invalid fieldName:"
					+ s);
		}
		return s1;
	}

	static {
		log = LogFactory
				.getLog(com.eespc.tracking.reports.datasources.Exhibit1DataSource.class);
	}
}
