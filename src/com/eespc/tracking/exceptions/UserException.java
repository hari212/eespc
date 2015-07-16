// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   UserException.java

package com.eespc.tracking.exceptions;

public class UserException extends Exception {

	public UserException() {
	}

	public UserException(String arg0) {
		super(arg0);
	}

	public UserException(Throwable arg0) {
		super(arg0);
	}

	public UserException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
