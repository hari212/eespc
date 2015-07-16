// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Const2Name.java

package com.eespc.tracking.bo;

// Referenced classes of package com.eespc.tracking.bo:
//            ConstantGroup

public class Const2Name implements Comparable {

	public Const2Name(ConstantGroup constantgroup, int i, String s, String s1,
			int j) {
		group = constantgroup;
		value = i;
		name = s;
		description = s1;
		sequenceNo = j;
	}

	public ConstantGroup getGroup() {
		return group;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public String toString() {
		return (new StringBuilder()).append("<<Const2Name:")
				.append(group.getName()).append("|").append(value).append("|")
				.append(name).append("|").append(sequenceNo).append("|")
				.append(description).append(">>").toString();
	}

	public int compareTo(Object obj) {
		if (obj instanceof Const2Name) {
			Const2Name const2name = (Const2Name) obj;
			return getName().compareToIgnoreCase(const2name.getName());
		} else {
			return -1;
		}
	}

	protected final ConstantGroup group;
	protected final int value;
	protected final String name;
	protected final String description;
	protected final int sequenceNo;
}
