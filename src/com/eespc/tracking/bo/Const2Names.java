// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Const2Names.java

package com.eespc.tracking.bo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.eespc.tracking.util.SqlUtil;

// Referenced classes of package com.eespc.tracking.bo:
//            Const2Name, ConstantGroup

public class Const2Names {

	private Const2Names(HashMap hashmap, List list1) {
		map = new HashMap();
		list = new ArrayList();
		map = hashmap;
		list = list1;
	}

	public static Const2Names getInstance(ConstantGroup constantgroup) {
		return loadConstants(constantgroup);
	}

	public Const2Name get(int i) {
		return (Const2Name) map.get(String.valueOf(i));
	}

	public Const2Name parse(String s) {
		if (s == null)
			return null;
		int i = list.size();
		for (int j = 0; j < i; j++) {
			Const2Name const2name = (Const2Name) list.get(j);
			if (const2name.getName().equals(s))
				return const2name;
		}

		return null;
	}

	public Const2Name parseByDescriptor(String s) {
		if (s == null)
			return null;
		int i = list.size();
		for (int j = 0; j < i; j++) {
			Const2Name const2name = (Const2Name) list.get(j);
			if (const2name.getDescription().equals(s))
				return const2name;
		}

		return null;
	}

	public boolean contains(String s) {
		Const2Name const2name = parse(s);
		return const2name != null;
	}

	public int size() {
		return list.size();
	}

	public List getConstants() {
		return list;
	}

	private static Const2Names loadConstants(ConstantGroup constantgroup) {
		HashMap hashmap;
		Object obj;
		PreparedStatement preparedstatement;
		ResultSet resultset;
		SqlUtil sqlutil;
		if (constantgroup == null)
			return null;
		hashmap = new HashMap();
		obj = new ArrayList();
		try {

			if (groupMap.containsKey(constantgroup)) {
				hashmap = (HashMap) groupMap.get(constantgroup);
				obj = (List) orderedGroupMap.get(constantgroup);
				return new Const2Names(hashmap, ((List) (obj)));
			}
			Object obj1 = null;
			preparedstatement = null;
			resultset = null;
			sqlutil = new SqlUtil();
			Connection connection = sqlutil.getConnection();
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1, constantgroup.getCode());
			Const2Name const2name;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); ((List) (obj))
					.add(const2name)) {
				int i = 1;
				const2name = new Const2Name(constantgroup,
						resultset.getInt(i++), resultset.getString(i++),
						resultset.getString(i++), resultset.getInt(i++));
				hashmap.put(String.valueOf(const2name.value), const2name);
			}

			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			sqlutil.closeConnection();
			sqlutil = null;
		} catch (Exception e) {
			System.out.println(e);
		}
		// log.error((new
		// StringBuilder()).append("Const2Names.init(").append(constantgroup.getName()).append("): ").append(exception.getMessage()).toString());

		if (hashmap != null && obj != null) {
			groupMap.put(constantgroup, hashmap);
			orderedGroupMap.put(constantgroup, obj);
			return new Const2Names(hashmap, ((List) (obj)));
		} else {
			return null;
		}

	}

	public static void main(String args[]) throws Exception {
		Const2Names const2names = getInstance(ConstantGroup.FACILITY_TYPE);
		Const2Name const2name = const2names.get(2);
		System.out.println(const2name);
	}

	static Logger log = Logger.getLogger("com.eespc.tracking.bo.Const2Names");
	protected static HashMap groupMap = new HashMap();
	protected static HashMap orderedGroupMap = new HashMap();
	protected HashMap map;
	protected List list;
	private static String query = "select value, name, description, seq_num from const2name where valid_ind in('Y', 'y') and group_num = ? and name is not null order by seq_num ";

}
