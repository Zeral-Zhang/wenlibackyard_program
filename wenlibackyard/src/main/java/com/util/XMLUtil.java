package com.util;

import java.io.File;
import java.io.InputStream;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class XMLUtil {
	public static Document getDocument(InputStream xmlIs) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(xmlIs);
		return document;
	}
	
	public static Document getDocument(String xml) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(ResourceUtil.getResourceAsStream(xml));
		return document;
	}
	
	public static Document getDocument(File file) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		return document;
	}
	
	public static String getAttrValue(String enumerationName, Element element) {
		Attribute attr = element.attribute(enumerationName);
		return attr.getValue();
	}
	
	public static <ROOT> ROOT getXMLRoot(Class<ROOT> rootClass, String xmlclasspath) {
		Serializer serializer = new Persister();
		ROOT root = null;
		try {
			root = serializer.read(rootClass, ResourceUtil.getResourceAsStream(xmlclasspath));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return root;
	}
}
