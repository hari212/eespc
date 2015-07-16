// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableHeaderCellDef.java

package com.eespc.tracking.ui.taglib;

public class TableHeaderCellDef {

	public TableHeaderCellDef() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBodyCellId() {
		return bodyCellId;
	}

	public void setBodyCellId(String bodyCellId) {
		this.bodyCellId = bodyCellId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	private String id;
	private String bodyCellId;
	private String label;
}
