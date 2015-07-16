package com.eespc.tracking.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.eespc.tracking.util.SqlUtil;

public class StackStorageChecker {

	private static Logger log = Logger
			.getLogger(com.eespc.tracking.entity.StackStorageChecker.class);
	private String message;

	public StackStorageChecker() {
		message = null;
	}

	public String getMessage() {
		return message;
	}

	public boolean isAvailable(int i) {
		message = null;
		boolean flag = false;
		try {
			boolean flag1 = isStackAvailable(i);
			boolean flag2 = false;
			if (flag1) {
				flag2 = isStorageTankAvailable(i);
			}
			if (flag1 && flag2) {
				flag = true;
			} else {
				if (!flag1 && flag2) {
					message = "No Stack Available for this Facility.";
				}
				if (flag1 && !flag2) {
					message = "No Storage Tank Available for this Facility.";
				}
				if (!flag1 && !flag2) {
					message = "No Stack & Storage Tank Available for this Facility.";
				}
			}
		} catch (Exception exception) {
			log.error(exception);
			message = exception.getMessage();
		}
		return flag;
	}

	private boolean isStackAvailable(int i) throws Exception {
		boolean flag;
		SqlUtil sqlutil;
		Connection connection;
		Statement statement;
		Exception exception1;
		flag = false;
		sqlutil = new SqlUtil();
		connection = sqlutil.getConnection();
		statement = null;
		ResultSet resultset = null;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("select stk.stackid from stack stk, building bldg ");
		stringbuffer.append("where   bldg.buildingid = stk.buildingid and ");
		stringbuffer.append("bldg.buildingid in ");
		stringbuffer
				.append("(select buildingid from building where facilityid=");
		stringbuffer.append(i).append(") ");
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(stringbuffer.toString());
			flag = resultset.next();
		} catch (Exception exception) {
			log.debug(stringbuffer.toString());
			throw exception;
		} finally {
			SqlUtil.close(resultset);
		}
		SqlUtil.close(resultset);
		SqlUtil.close(statement);
		if (connection != null) {
			sqlutil.closeConnection();
		}
		SqlUtil.close(statement);
		if (connection != null) {
			sqlutil.closeConnection();
		}
		return flag;
	}

	private boolean isStorageTankAvailable(int i) throws Exception {
		boolean flag;
		SqlUtil sqlutil;
		Connection connection;
		Statement statement;
		Exception exception1;
		flag = false;
		sqlutil = new SqlUtil();
		connection = sqlutil.getConnection();
		statement = null;
		ResultSet resultset = null;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("select stk.storagetankid from storagetank stk, building bldg ");
		stringbuffer.append("where   bldg.buildingid = stk.buildingid and ");
		stringbuffer.append("bldg.buildingid in ");
		stringbuffer
				.append("(select buildingid from building where facilityid=");
		stringbuffer.append(i).append(") ");
		try {
			statement = connection.createStatement();
			resultset = statement.executeQuery(stringbuffer.toString());
			flag = resultset.next();
		} catch (Exception exception) {
			log.debug(stringbuffer.toString());
			throw exception;
		} finally {
			SqlUtil.close(resultset);
		}
		SqlUtil.close(resultset);
		SqlUtil.close(statement);
		if (connection != null) {
			sqlutil.closeConnection();
		}
		SqlUtil.close(statement);
		if (connection != null) {
			sqlutil.closeConnection();
		}
		return flag;
	}

}
