// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Configurator.java

package com.eespc.tracking.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.InetAddress;

public class Configurator {

	public Configurator() {
	}

	public static InputStream getResourceAsStream(String resourceName) {
		if (resourceName == null || resourceName.length() == 0)
			throw new IllegalArgumentException("Blank resource name");
		File file = new File("/opt/www/data", resourceName);
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Configuration file not found");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ClassLoader.getSystemResourceAsStream(resourceName);
	}

	public static InputStream getResourceAsStream(String configDirectory,
			String resourceName) {
		if (resourceName == null || resourceName.length() == 0)
			throw new IllegalArgumentException("Blank resource name");
		if (configDirectory == null || configDirectory.length() == 0)
			throw new IllegalArgumentException("Blank Config Directory");
		File file = new File(configDirectory, resourceName);
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Configuration file not found");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ClassLoader.getSystemResourceAsStream(resourceName);
	}

	public static String getHostname() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			return getHostAddress();
		}
	}

	public static String getHostAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception exception) {
			return null;
		}
	}

	public static final String CONFIG_DIRECTORY = "/opt/www/data";
}
