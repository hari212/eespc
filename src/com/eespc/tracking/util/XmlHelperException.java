// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlHelperException.java

package com.eespc.tracking.util;

public class XmlHelperException extends Exception {

	public XmlHelperException(String message) {
		this(null, message);
	}

	public XmlHelperException(Exception e) {
		this(e, null);
	}

	public XmlHelperException(Exception e, String message) {
		this.message = null;
		parentException = null;
		this.message = message;
		parentException = e;
	}

	public Exception getParentException() {
		return parentException;
	}

	public String getMessage() {
		return message;
	}

	public String toString() {
		String msg = "";
		if (message != null)
			msg = msg + message;
		if (parentException != null)
			msg = msg + " " + parentException.getMessage();
		return msg;
	}

	public static final String ERR_SAXException = "SAXException raised";
	public static final String ERR_IOException = "IOException raised";
	public static final String ERR_FileNotFoundException = "FileNotFoundException raised";
	public static final String ERR_TransformerException = "TransformerException raised";
	public static final String ERR_TransformerConfigurationException = "TransformerConfigurationException raised";
	private String message;
	private Exception parentException;
}
