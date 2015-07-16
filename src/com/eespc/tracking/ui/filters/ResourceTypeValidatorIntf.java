// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResourceTypeValidatorIntf.java

package com.eespc.tracking.ui.filters;

import javax.servlet.http.HttpServletRequest;

import com.eespc.tracking.exceptions.TrackingException;

public interface ResourceTypeValidatorIntf {

	public abstract boolean isValidToShow(HttpServletRequest httpservletrequest)
			throws TrackingException;
}
