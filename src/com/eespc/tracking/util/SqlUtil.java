// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 4/15/2007 12:10:16 PM Sigma
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SqlUtil.java

package com.eespc.tracking.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class SqlUtil {

	public SqlUtil() {
		params = null;
		isStoredProcOutParam = null;
		printBuffer = new StringBuffer();
		conToGiveOut = null;
		con = null;
		startTime = System.currentTimeMillis();
		endTime = System.currentTimeMillis();
		params = new ArrayList();
		isStoredProcOutParam = new ArrayList();
	}

	public void clearParams() {
		params.clear();
		isStoredProcOutParam.clear();
		if (log.isInfoEnabled())
			log.info(printBuffer.toString());
		printBuffer = new StringBuffer();
	}

	public void addInParam(Object obj) {
		params.add(obj);
		isStoredProcOutParam.add(Boolean.FALSE);
	}

	public void addOutParam(Object obj) {
		params.add(obj);
		isStoredProcOutParam.add(Boolean.TRUE);
	}

	public int getCountParams() {
		return params.size();
	}

	public static String getClassToName(Class class1) {
		return (String) class2Name.get(class1);
	}

	public void connectionCleanUp() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
				con = null;
			}
		} catch (SQLException sqlexception) {
			sqlexception.printStackTrace();
		}
	}

	public List execQueryUsingMethods(String s, Class class1, List list)
			throws Exception {
		return execQuery(s, class1, list, false);
	}

	public List execQueryUsingConstructor(String s, Class class1)
			throws Exception {
		return execQuery(s, class1, null, true);
	}

	public boolean execForDmlUsingQuery(String s) throws Exception {
		boolean flag = false;
		checkQuery(s);
		printBuffer.append(s);
		try {
			con = getConnection();
			PreparedStatement preparedstatement = con.prepareStatement(s);
			registerInParam(preparedstatement);
			startTime = System.currentTimeMillis();
			int i = preparedstatement.executeUpdate();
			endTime = System.currentTimeMillis();
			if (i > 0)
				flag = true;
		} catch (Exception exception) {
			System.out
					.println("Exception In execForDmlUsingQuery:" + exception);
			printBuffer.append(", ERROR: " + exception.getMessage());
			Exception exception1 = new Exception(
					"Error from execForDmlUsingQuery");
			exception1.initCause(exception);
			throw exception1;
		} finally {
			printBuffer.append(", ExecTime(ms) " + (endTime - startTime));
			clearParams();
			connectionCleanUp();
		}
		return flag;
	}

	public List execSp(String s) throws Exception {
		Object obj = execSpHelper(s, null, null, false);
		return ((List) (obj != null ? (List) obj : new ArrayList()));
	}

	public Object execSp(String s, Class class1, List list) throws Exception {
		return execSpHelper(s, class1, list, true);
	}

	private Object execSpHelper(String s, Class class1, List list, boolean flag)
			throws Exception {
		checkQuery(s);
		if (flag && class1 == null)
			throw new Exception("Object to output cannot be NULL");
		Object obj = null;
		ArrayList arraylist = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer("{call " + s + "(");
		for (int i = 0; i < params.size(); i++) {
			stringbuffer.append("?");
			if (i < params.size() - 1)
				stringbuffer.append(",");
		}

		stringbuffer.append(")}");
		printBuffer.append(stringbuffer.toString());
		try {
			con = getConnection();
			CallableStatement callablestatement = con.prepareCall(stringbuffer
					.toString());
			registerInOutParam(callablestatement);
			startTime = System.currentTimeMillis();
			callablestatement.execute();
			endTime = System.currentTimeMillis();
			for (int j = 0; j < params.size(); j++) {
				Object obj2 = params.get(j);
				boolean flag1 = ((Boolean) isStoredProcOutParam.get(j))
						.booleanValue();
				if (flag1)
					arraylist.add(callablestatement.getObject(j + 1));
			}

			if (flag && arraylist != null && arraylist.size() > 0) {
				Object obj1 = null;
				Class aclass[] = { java.lang.Object.class };
				Object obj3 = null;
				obj1 = class1.newInstance();
				for (int k = 0; k < list.size(); k++) {
					String s1 = (String) list.get(k);
					Object aobj[] = { arraylist.get(k) };
					Method method = class1.getDeclaredMethod(s1.trim(), aclass);
					Object obj4 = method.invoke(obj1, aobj);
				}

				obj = obj1;
			} else {
				obj = arraylist;
			}
		} catch (Exception exception) {
			printBuffer.append(", ERROR: " + exception.getMessage());
			Exception exception1 = new Exception("Error in execSpHelper ");
			exception1.initCause(exception);
			throw exception1;
		} finally {
			printBuffer.append(", ExecTime(ms) " + (endTime - startTime));
			clearParams();
			connectionCleanUp();
		}
		return obj;
	}

	private List execQuery(String s, Class class1, List list, boolean flag)
			throws Exception {

		ArrayList arraylist = new ArrayList();
		checkQuery(s);
		if (flag && class1 == null)
			throw new Exception("Object to output is mandatory.");
		if (!flag
				&& (class1 == null || list == null || list != null
						&& list.size() == 0))
			throw new Exception(
					"Object to output and list of set methods are mandatory.");
		printBuffer.append(s);

		try {
			con = getConnection();

			PreparedStatement preparedstatement = con.prepareStatement(s);

			registerInParam(preparedstatement);
			startTime = System.currentTimeMillis();
			ResultSet resultset = preparedstatement.executeQuery();
			endTime = System.currentTimeMillis();
			if (resultset != null) {
				Object obj = null;
				if (flag) {
					Class aclass[] = { java.sql.ResultSet.class };
					Object obj3 = null;
					Object obj1;
					for (; resultset.next(); arraylist.add(obj1)) {
						Object aobj[] = { resultset };
						Constructor constructor = class1.getConstructor(aclass);
						obj1 = constructor.newInstance(aobj);
					}

				} else {
					Class aclass1[] = { java.lang.Object.class };
					Object obj4 = null;
					Object obj2;
					for (; resultset.next(); arraylist.add(obj2)) {
						obj2 = class1.newInstance();
						for (int i = 0; i < list.size(); i++) {
							String s1 = (String) list.get(i);
							Object aobj1[] = { resultset.getObject(i + 1) };
							Method method = class1.getDeclaredMethod(s1.trim(),
									aclass1);
							Object obj5 = method.invoke(obj2, aobj1);
						}

					}

				}
			} else {

			}
		} catch (Exception exception) {
			exception.printStackTrace();
			printBuffer.append(", ERROR: " + exception.getMessage());
			Exception exception1 = new Exception("Error in execQuery ");
			exception1.initCause(exception);
			throw exception1;
		} finally {
			printBuffer.append(", ExecTime(ms) " + (endTime - startTime));
			clearParams();
			connectionCleanUp();
		}
		return arraylist;
	}

	private void registerInOutParam(CallableStatement callablestatement)
			throws Exception {
		StringBuffer stringbuffer = new StringBuffer();
		if (params.size() > 0)
			stringbuffer.append("(");
		for (int i = 0; i < params.size(); i++) {
			Object obj = params.get(i);
			boolean flag = ((Boolean) isStoredProcOutParam.get(i))
					.booleanValue();
			if (obj == null) {
				if (!flag) {
					callablestatement.setNull(i + 1, 12);
					stringbuffer.append("null" + COMMA);
				}
				continue;
			}
			if (obj instanceof Integer) {
				int j = ((Integer) obj).intValue();
				if (flag) {
					callablestatement.registerOutParameter(i + 1, 4);
					stringbuffer.append("?" + COMMA);
				} else {
					callablestatement.setInt(i + 1, j);
					stringbuffer.append(j + COMMA);
				}
				continue;
			}
			if (obj instanceof String) {
				String s1 = (String) obj;
				if (flag) {
					callablestatement.registerOutParameter(i + 1, 12);
					stringbuffer.append("?" + COMMA);
				} else {
					callablestatement.setString(i + 1, s1);
					stringbuffer.append("'" + s1 + "'" + COMMA);
				}
				continue;
			}
			if (obj instanceof Double) {
				double d = ((Double) obj).doubleValue();
				if (flag) {
					callablestatement.registerOutParameter(i + 1, 8);
					stringbuffer.append("?" + COMMA);
				} else {
					callablestatement.setDouble(i + 1, d);
					stringbuffer.append(d + COMMA);
				}
				continue;
			}
			if (obj instanceof Float) {
				float f = ((Float) obj).floatValue();
				if (flag) {
					callablestatement.registerOutParameter(i + 1, 6);
					stringbuffer.append("?" + COMMA);
				} else {
					callablestatement.setFloat(i + 1, f);
					stringbuffer.append(f + COMMA);
				}
				continue;
			}
			if (obj instanceof Long) {
				long l = ((Long) obj).longValue();
				if (flag) {
					callablestatement.registerOutParameter(i + 1, -5);
					stringbuffer.append("?" + COMMA);
				} else {
					callablestatement.setFloat(i + 1, l);
					stringbuffer.append(l + COMMA);
				}
				continue;
			}
			if (obj instanceof Boolean) {
				boolean flag1 = ((Boolean) obj).booleanValue();
				if (flag) {
					callablestatement.registerOutParameter(i + 1, -7);
					stringbuffer.append("?" + COMMA);
				} else {
					callablestatement.setBoolean(i + 1, flag1);
					stringbuffer.append(flag1 + COMMA);
				}
				continue;
			}
			if (obj instanceof java.sql.Date) {
				java.sql.Date date = (java.sql.Date) obj;
				if (flag) {
					callablestatement.registerOutParameter(i + 1, 91);
					stringbuffer.append("?" + COMMA);
				} else {
					callablestatement.setDate(i + 1, (java.sql.Date) obj);
					stringbuffer.append(obj + COMMA);
				}
			} else {
				throw new Exception("Unknown parameter type: "
						+ obj.getClass().getName() + ", at index " + i);
			}
		}

		if (params.size() > 0) {
			String s = stringbuffer.toString();
			if (s.charAt(stringbuffer.length() - 1) == ',')
				s = s.substring(0, s.length() - 1);
			printBuffer.append(s + ")");
		}
	}

	private void registerInParam(PreparedStatement preparedstatement)
			throws Exception {
		StringBuffer stringbuffer = new StringBuffer();
		if (params.size() > 0)
			stringbuffer.append("(");
		for (int i = 0; i < params.size(); i++) {
			Object obj = params.get(i);
			if (obj == null) {
				preparedstatement.setNull(i + 1, 12);
				stringbuffer.append("null" + COMMA);

				continue;
			}
			if (obj instanceof Integer) {
				int j = ((Integer) obj).intValue();
				preparedstatement.setInt(i + 1, j);
				stringbuffer.append(j + COMMA);
				continue;
			}
			if (obj instanceof String) {
				String s1 = (String) obj;
				preparedstatement.setString(i + 1, s1);
				stringbuffer.append("'" + s1 + "'" + COMMA);
				continue;
			}
			if (obj instanceof Double) {
				double d = ((Double) obj).doubleValue();
				preparedstatement.setDouble(i + 1, d);
				stringbuffer.append(d + COMMA);
				continue;
			}
			if (obj instanceof Float) {
				float f = ((Float) obj).floatValue();
				preparedstatement.setFloat(i + 1, f);
				stringbuffer.append(f + COMMA);
				continue;
			}
			if (obj instanceof Long) {
				long l = ((Long) obj).longValue();
				preparedstatement.setFloat(i + 1, l);
				stringbuffer.append(l + COMMA);
				continue;
			}
			if (obj instanceof Boolean) {
				boolean flag = ((Boolean) obj).booleanValue();
				preparedstatement.setBoolean(i + 1, flag);
				stringbuffer.append(flag + COMMA);
				continue;
			}
			if (obj instanceof java.sql.Date) {
				java.sql.Date date = (java.sql.Date) obj;
				preparedstatement.setDate(i + 1, (java.sql.Date) obj);
				stringbuffer.append(date + COMMA);
			} else {
				throw new Exception("Unknown parameter type: "
						+ obj.getClass().getName() + ", at index " + i);
			}
		}

		if (params.size() > 0) {
			String s = stringbuffer.toString();
			if (s.charAt(stringbuffer.length() - 1) == ',')
				s = s.substring(0, s.length() - 1);
			printBuffer.append(s + ")");
		}
		// System.out.println("haiiii"+printbuffer);
	}

	private void checkQuery(String s) throws Exception {
		if (s == null || s.trim().length() == 0)
			throw new Exception("SP/Query is mandatory.");
		else
			return;
	}

	public Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conToGiveOut = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/eespc?user=root&password=root");
		// conToGiveOut =
		// DriverManager.getConnection("jdbc:mysql://75.127.148.171:3306/eespc","root","1408");
		return conToGiveOut;
	}

	public void closeConnection() {
		try {
			if (conToGiveOut != null && !conToGiveOut.isClosed()) {
				conToGiveOut.close();
				conToGiveOut = null;
			}
		} catch (SQLException sqlexception) {
			if (log.isEnabledFor(Priority.ERROR))
				log.error(sqlexception);
		}
	}

	public static void close(Connection fCon) {
		try {
			if (fCon != null && !fCon.isClosed()) {
				fCon.close();
				fCon = null;
			}
		} catch (SQLException sqlexception) {
			if (log.isEnabledFor(Priority.ERROR))
				log.error(sqlexception);
		}
	}

	public static void close(Statement statement) {
		if (statement != null)
			try {
				statement.close();
			} catch (SQLException sqlexception) {
				if (log.isEnabledFor(Priority.ERROR))
					log.error("Error while closing the Prepared Statement",
							sqlexception);
			}
	}

	public static void close(ResultSet resultset) {
		if (resultset != null)
			try {
				resultset.close();
			} catch (SQLException sqlexception) {
				if (log.isEnabledFor(Priority.ERROR))
					log.error("Error while closing the ResultSet", sqlexception);
			}
	}

	public int insert(String s) throws Exception {
		if (s == null || s.trim().length() == 0)
			throw new IllegalArgumentException("Query String cannot be null.");
		int i = -99;
		Object obj = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			Connection connection = getConnection();
			statement = connection.createStatement();
			startTime = System.currentTimeMillis();
			statement.executeUpdate(s.toString());
			resultset = statement.getGeneratedKeys();
			if (resultset.next()) {
				String s1 = resultset.getString(1);
				if (s1 != null && s1.trim().length() > 0)
					i = Integer.parseInt(s1);
			}
		} catch (Exception exception) {
			throw exception;
		} finally {
			closeConnection();
			close(statement);
			close(resultset);
			endTime = System.currentTimeMillis();
			if (log.isInfoEnabled())
				log.info(s + ", ExecTime(ms) " + (endTime - startTime));
		}
		return i;
	}

	private ArrayList params;
	private ArrayList isStoredProcOutParam;
	private static Logger log;
	private StringBuffer printBuffer;
	private static final HashMap class2Name;
	Connection conToGiveOut;
	Connection con;
	public static String CR = System.getProperty("line.separator");
	public static String COMMA = ",";
	private long startTime;
	private long endTime;

	static {
		log = Logger.getLogger((com.eespc.tracking.util.SqlUtil.class)
				.getName());
		class2Name = new HashMap();
		class2Name.put(Boolean.TYPE, "boolean");
		class2Name.put(java.lang.Boolean.class, "java.lang.Boolean");
		class2Name.put(Byte.TYPE, "byte");
		class2Name.put(java.lang.Byte.class, "java.lang.Byte");
		class2Name.put(Character.TYPE, "char");
		class2Name.put(java.lang.Character.class, "java.lang.Character");
		class2Name.put(Double.TYPE, "double");
		class2Name.put(java.lang.Double.class, "java.lang.Double");
		class2Name.put(Float.TYPE, "float");
		class2Name.put(java.lang.Float.class, "java.lang.Float");
		class2Name.put(Integer.TYPE, "int");
		class2Name.put(java.lang.Integer.class, "java.lang.Integer");
		class2Name.put(Long.TYPE, "long");
		class2Name.put(java.lang.Long.class, "java.lang.Long");
		class2Name.put(Short.TYPE, "short");
		class2Name.put(java.lang.Short.class, "java.lang.Short");
		class2Name.put(Void.TYPE, "void");
		class2Name.put(null, "void");
		class2Name.put(java.lang.String.class, "java.lang.String");
		class2Name.put(java.sql.Date.class, "java.sql.Date");
		class2Name.put(java.sql.Time.class, "java.sql.Time");
		class2Name.put(java.sql.Timestamp.class, "java.sql.Timestamp");
		class2Name.put(java.lang.Object.class, "java.lang.Object");
	}
}