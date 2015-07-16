// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XmlHelper.java

package com.eespc.tracking.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

// Referenced classes of package com.eespc.tracking.util:
//            XmlHelperException

public class XmlHelper {

	public XmlHelper() {
	}

	public static synchronized Document newDocument() {
		return db.newDocument();
	}

	public static DocumentBuilderFactory getFactory() {
		return dfactory;
	}

	public static Document parse(File file) throws XmlHelperException {
		try {
			InputSource in = new InputSource(new FileInputStream(file));
			return parse(in);
		} catch (FileNotFoundException fnfe) {
			throw new XmlHelperException(fnfe, "FileNotFoundException raised");
		}
	}

	public static Document parse(String xml) throws XmlHelperException {
		if (xml == null || xml.trim().length() == 0) {
			throw new IllegalArgumentException("Blank or null XML String");
		} else {
			InputSource in = new InputSource(new StringReader(xml));
			return parse(in);
		}
	}

	public static synchronized Document parse(InputSource is)
			throws XmlHelperException {
		Document doc = null;
		try {
			doc = db.parse(is);
		} catch (SAXException se) {
			throw new XmlHelperException(se, "SAXException raised");
		} catch (IOException ioe) {
			throw new XmlHelperException(ioe, "IOException raised");
		}
		return doc;
	}

	public static String getAttributeValue(Node node, String attributeName) {
		NamedNodeMap attrs = node.getAttributes();
		if (attrs == null)
			return null;
		Node n = attrs.getNamedItem(attributeName);
		if (n == null)
			return null;
		else
			return n.getNodeValue();
	}

	public static NodeList getNodeList(Node node, String xpath)
			throws XmlHelperException {
		try {
			return XPathAPI.selectNodeList(node, xpath);
		} catch (TransformerException te) {
			throw new XmlHelperException(te, "TransformerException raised");
		}
	}

	public static Node getNode(Node node, String xpath)
			throws XmlHelperException {
		NodeList nodeList = getNodeList(node, xpath);
		if (nodeList != null && nodeList.getLength() > 0)
			return nodeList.item(0);
		else
			return null;
	}

	public static Node getBusinessNode(Node node, String xpath)
			throws XmlHelperException {
		NodeIterator nodeiterator = null;
		try {
			nodeiterator = XPathAPI.selectNodeIterator(node, xpath);
		} catch (TransformerException te) {
			throw new XmlHelperException(te, "TransformerException raised");
		}
		return nodeiterator.getRoot();
	}

	public static String getNodeValue(Node node, String defValue)
			throws XmlHelperException {
		if (node == null || node.getChildNodes().getLength() == 0)
			return defValue;
		if (node.getChildNodes().getLength() == 1)
			return node.getFirstChild().getNodeValue();
		else
			return print(node);
	}

	public static String getStringNodeValue(Node node, String xpath,
			String defValue) throws XmlHelperException {
		if (node == null || node.getChildNodes().getLength() == 0)
			return defValue;
		else
			return getNodeValue(getNode(node, xpath), defValue);
	}

	public static int getIntNodeValue(Node node, String xpath, int defValue)
			throws XmlHelperException {
		String result = getStringNodeValue(node, xpath,
				String.valueOf(defValue)).trim();
		try {
			return Integer.parseInt(result);
		} catch (NumberFormatException nfe) {
			return defValue;
		}
	}

	public static long getLongNodeValue(Node node, String xpath, long defValue)
			throws XmlHelperException {
		String result = getStringNodeValue(node, xpath,
				String.valueOf(defValue)).trim();
		try {
			return Long.parseLong(result);
		} catch (NumberFormatException nfe) {
			return defValue;
		}
	}

	public static boolean getBooleanNodeValue(Node node, String xpath,
			boolean defValue) throws XmlHelperException {
		String val = getStringNodeValue(node, xpath, String.valueOf(defValue))
				.trim().toLowerCase();
		if (val.equals("true") || val.equals("1"))
			return true;
		if (val.equals("false") || val.equals("0"))
			return false;
		else
			return defValue;
	}

	public static boolean getBooleanAttributeValue(Node node,
			String attributeName) throws XmlHelperException {
		return getBooleanAttributeValue(node, attributeName, false);
	}

	public static boolean getBooleanAttributeValue(Node node,
			String attributeName, boolean defValue) throws XmlHelperException {
		String val = getAttributeValue(node, attributeName);
		if (val != null
				&& (val.toLowerCase().equals("true") || val.equals("1")))
			return true;
		if (val != null
				&& (val.toLowerCase().equals("false") || val.equals("0")))
			return false;
		else
			return defValue;
	}

	public static String[] getStringNodeValues(Node node, String xpath,
			String defValue) throws XmlHelperException {
		if (node == null || node.getChildNodes().getLength() == 0)
			return new String[0];
		NodeList nodeList = getNodeList(node, xpath);
		if (nodeList == null || nodeList.getLength() == 0)
			return new String[0];
		Vector list = new Vector();
		int len = nodeList.getLength();
		String result[] = new String[len];
		for (int i = 0; i < len; i++)
			result[i] = getNodeValue(nodeList.item(i), defValue);

		return result;
	}

	public static int[] getIntNodeValues(Node node, String xpath, int defValue)
			throws XmlHelperException {
		String result[] = getStringNodeValues(node, xpath,
				String.valueOf(defValue));
		if (result.length == 0)
			return new int[0];
		int intResult[] = new int[result.length];
		for (int i = 0; i < result.length; i++)
			try {
				intResult[i] = Integer.parseInt(result[i].trim());
			} catch (NumberFormatException nfe) {
				intResult[i] = defValue;
			}

		return intResult;
	}

	public static long[] getLongNodeValues(Node node, String xpath,
			long defValue) throws XmlHelperException {
		String result[] = getStringNodeValues(node, xpath,
				String.valueOf(defValue));
		if (result.length == 0)
			return new long[0];
		long longResult[] = new long[result.length];
		for (int i = 0; i < result.length; i++)
			try {
				longResult[i] = Long.parseLong(result[i].trim());
			} catch (NumberFormatException nfe) {
				longResult[i] = defValue;
			}

		return longResult;
	}

	public static boolean[] getBooleanNodeValues(Node node, String xpath,
			boolean defValue) throws XmlHelperException {
		String result[] = getStringNodeValues(node, xpath,
				String.valueOf(defValue));
		if (result.length == 0)
			return new boolean[0];
		boolean booleanResult[] = new boolean[result.length];
		String val = "";
		for (int i = 0; i < result.length; i++) {
			val = result[i].trim().toLowerCase();
			if (val.equals("true") || val.equals("1"))
				booleanResult[i] = true;
			else if (val.equals("false") || val.equals("0"))
				booleanResult[i] = false;
			else
				booleanResult[i] = defValue;
		}

		return booleanResult;
	}

	public static Date getDateNodeValue(Node node, String xpath,
			String dateFormatt, String defValue) throws XmlHelperException {
		String result = getStringNodeValue(node, xpath, defValue);
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormatt);
		try {
			return formatter.parse(result);
		} catch (ParseException parseexception) {
			return null;
		}
	}

	public static Date[] getDateNodeValues(Node node, String xpath,
			String dateFormatt, String defValue) throws XmlHelperException {
		String result[] = getStringNodeValues(node, xpath, defValue);
		if (result.length == 0)
			return new Date[0];
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormatt);
		Date dateResult[] = new Date[result.length];
		for (int i = 0; i < result.length; i++)
			try {
				dateResult[i] = formatter.parse(result[i]);
			} catch (ParseException pe) {
				dateResult[i] = null;
			}

		return dateResult;
	}

	public static synchronized String print(Node node)
			throws XmlHelperException {
		StringWriter sw = null;
		try {
			serializer.setOutputProperty("omit-xml-declaration", "yes");
			sw = new StringWriter();
			serializer.transform(new DOMSource(node), new StreamResult(sw));
		} catch (TransformerException te) {
			throw new XmlHelperException(te, "TransformerException raised");
		}
		return sw.toString();
	}

	public static Node getDocumentElement(Document doc) {
		if (doc == null)
			return null;
		else
			return doc.getDocumentElement();
	}

	public static synchronized String transform(StreamSource ssXml,
			StreamSource ssXsl) throws XmlHelperException {
		try {
			Transformer transformer = tFactory.newTransformer(ssXsl);
			StreamResult sr = new StreamResult();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			transformer.transform(ssXml, new StreamResult(os));
			return os.toString();
		} catch (TransformerConfigurationException tce) {
			throw new XmlHelperException(tce,
					"TransformerConfigurationException raised");
		} catch (TransformerException te) {
			throw new XmlHelperException(te, "TransformerException raised");
		}
	}

	public static String transform(String xmlStr, String xslStr)
			throws XmlHelperException {
		return transform(new StreamSource(new StringReader(xmlStr)),
				new StreamSource(new StringReader(xslStr)));
	}

	public static String transform(File xmlFile, File xslFile)
			throws XmlHelperException {
		return transform(new StreamSource(xmlFile), new StreamSource(xslFile));
	}

	public static String transform(URL xmlUrl, URL xslUrl)
			throws XmlHelperException {
		try {
			return transform(new StreamSource(xmlUrl.openStream()),
					new StreamSource(xslUrl.openStream()));
		} catch (IOException ioe) {
			throw new XmlHelperException(ioe.getMessage());
		}
	}

	public static String xmlEncoding(String xmlString) {
		if (xmlString == null) {
			return null;
		} else {
			xmlString = replaceSubstring(xmlString, "&", "&amp;");
			xmlString = replaceSubstring(xmlString, "'", "&apos;");
			xmlString = replaceSubstring(xmlString, "\"", "&quot;");
			xmlString = replaceSubstring(xmlString, "<", "&lt;");
			xmlString = replaceSubstring(xmlString, ">", "&gt;");
			return xmlString;
		}
	}

	public static String xmlUnencoding(String xmlString) {
		if (xmlString == null) {
			return null;
		} else {
			xmlString = replaceSubstring(xmlString, "&amp;", "&");
			xmlString = replaceSubstring(xmlString, "&apos;", "'");
			xmlString = replaceSubstring(xmlString, "&quot;", "\"");
			xmlString = replaceSubstring(xmlString, "&lt;", "<");
			xmlString = replaceSubstring(xmlString, "&gt;", ">");
			return xmlString;
		}
	}

	private static String replaceSubstring(String str, String oldString,
			String newString) {
		if (str == null || oldString == null || newString == null)
			return str;
		int startIndex = 0;
		int endIndex = 0;
		int patternLength = oldString.length();
		StringBuffer buf = new StringBuffer();
		while (endIndex != -1)
			if ((endIndex = str.indexOf(oldString, startIndex)) == -1) {
				buf.append(str.substring(startIndex));
			} else {
				buf.append(str.substring(startIndex, endIndex));
				buf.append(newString);
				startIndex = endIndex + patternLength;
			}
		return buf.toString();
	}

	public static String readTextFile(String fileName)
			throws FileNotFoundException, IOException {
		File file = new File(fileName);
		FileInputStream in = new FileInputStream(file);
		byte buf[] = new byte[(int) file.length()];
		in.read(buf);
		return new String(buf);
	}

	public static String readTextFile(URL url) throws IOException {
		if (url == null)
			return "";
		BufferedInputStream bis = null;
		StringBuffer sb = new StringBuffer();
		try {
			bis = new BufferedInputStream(url.openStream());
			byte buff[] = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length)))
				sb.append(new String(buff, 0, bytesRead));
		} catch (IOException e) {
			throw e;
		}
		return sb.toString();
	}

	public static void writeToFile(String fileName, String xml)
			throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				fileName, true), 1024));
		out.println(xml);
		out.flush();
		out.close();
	}

	public static String getPrefix(Node nameNode) {
		if (nameNode == null)
			return null;
		String nodeName = nameNode.getNodeName();
		int index = nodeName.indexOf(":");
		if (index == -1)
			return null;
		else
			return nodeName.substring(0, index);
	}

	public static void main(String args1[]) {
	}

	private static DocumentBuilderFactory dfactory;
	private static DocumentBuilder db = null;
	private static TransformerFactory tFactory;
	private static Transformer serializer = null;

	static {
		dfactory = null;
		tFactory = null;
		try {
			dfactory = DocumentBuilderFactory.newInstance();
			dfactory.setCoalescing(true);
			dfactory.setNamespaceAware(false);
			db = dfactory.newDocumentBuilder();
			tFactory = TransformerFactory.newInstance();
			serializer = tFactory.newTransformer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
