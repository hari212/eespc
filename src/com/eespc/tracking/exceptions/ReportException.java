// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReportException.java

package com.eespc.tracking.exceptions;

public class ReportException extends Exception {

	public ReportException() {
	}

	public ReportException(String s) {
		super(s);
	}

	public ReportException(Throwable throwable) {
		super(throwable);
	}

	public ReportException(String s, Throwable throwable) {
		super(s, throwable);
	}
}
