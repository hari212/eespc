// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SqlUtilTest.java

package com.eespc.tracking.util;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import com.eespc.tracking.bo.Constants;

// Referenced classes of package com.eespc.tracking.util:
//            SqlUtil

public class SqlUtilTest extends TestCase {

	public SqlUtilTest() {
	}

	public SqlUtilTest(String arg0) {
		super(arg0);
	}

	public void testFacility() throws Exception {
		int id = 1;
		SqlUtil util = new SqlUtil();
		util.addInParam(new Integer(id));
		System.out.println("id=" + id);
		List temp = util.execQueryUsingConstructor(Constants.GETTING_FACILITY,
				com.eespc.tracking.bo.FacilityVo.class);
		System.out.println("temp is null ?" + (temp != null ? "NOPE" : "NULL"));
		if (temp != null && temp.size() > 0)
			System.out.println(temp.get(0));
	}

	public void testForInsert() throws Exception {
		SqlUtil util = new SqlUtil();
		java.sql.Connection conn = util.getConnection();
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new SqlUtilTest("testFacility"));
		return suite;
	}

	public static void main(String args[]) {
		TestRunner.run(suite());
	}
}
