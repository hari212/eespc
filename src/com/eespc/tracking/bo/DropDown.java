// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DropDown.java

package com.eespc.tracking.bo;

import java.io.Serializable;
import java.util.Collection;

public class DropDown implements Serializable {

	public DropDown() {
		dropDownValues = null;
	}

	public DropDown(Collection collection) {
		dropDownValues = null;
		dropDownValues = collection;
	}

	public Collection getDropDownValues() {
		return dropDownValues;
	}

	public void setDropDownValues(Collection collection) {
		dropDownValues = collection;
	}

	private Collection dropDownValues;
}
