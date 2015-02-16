package com.github.ronaldoblanc.util;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SqlDeveloperXmlParser {

	public Map<String, ConnectionProperties> getConnections(String filename)
			throws ParserConfigurationException, SAXException, IOException {
		File file = new File(filename);
		Map<String, ConnectionProperties> connections = parseInputXml(file);
		return connections;
	}

	private byte[] stringToByte(String input) {
		int inputSize = input.length();
		char[] inputData = input.toCharArray();
		byte[] result = new byte[inputSize / 2];
		for (int i = 0; i < inputSize; i = (i + 2)) {
			String byteStr = new String((new char[] { (char) inputData[i],
					(char) inputData[i + 1] }));
			result[i / 2] = (byte) Integer.parseInt(byteStr, 16);
		}
		return result;
	}

	private byte[] decryptPassword(String input)
			throws GeneralSecurityException {
		byte[] result = stringToByte(input);
		int constant = result[0];
		if (constant != 5) {
			throw new IllegalArgumentException();
		}

		byte[] secretKey = new byte[8];
		System.arraycopy(result, 1, secretKey, 0, 8);

		byte[] encryptedPassword = new byte[result.length - 9];
		System.arraycopy(result, 9, encryptedPassword, 0,
				encryptedPassword.length);

		byte[] iv = new byte[8];
		for (int i = 0; i < iv.length; i++) {
			iv[i] = 0;
		}

		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secretKey, "DES"),
				new IvParameterSpec(iv));
		return cipher.doFinal(encryptedPassword);
	}

	private Map<String, ConnectionProperties> parseInputXml(File xml)
			throws ParserConfigurationException, SAXException, IOException {
		final Map<String, ConnectionProperties> result = new HashMap<String, ConnectionProperties>();
		SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
		parser.parse(xml, new DefaultHandler() {
			final String STRING_REF_ADDR = "StringRefAddr";
			final String CONTENTS = "Contents";
			final String ADDR_TYPE = "addrType";
			final String CONNECTION_NAME = "ConnName";
			final String USER = "user";
			final String PASSWORD = "password";
			final String HOSTNAME = "hostname";
			final String PORT = "port";
			final String SID = "sid";
			final String CONNECTION_TYPE = "OracleConnectionType";
			final String DRIVER_CLASS = "driver";
			final String DRIVER_TYPE = "oraDriverType";
			final String URL = "customUrl";
			final String REFERENCE = "Reference";

			String actualConnection;
			boolean contents = false;
			boolean connectionName = false;
			boolean user = false;
			boolean password = false;
			boolean hostname = false;
			boolean port = false;
			boolean sid = false;
			boolean connectionType = false;
			boolean driverClass = false;
			boolean driverType = false;
			boolean url = false;

			@Override
			public void startElement(String arg0, String arg1, String arg2,
					Attributes arg3) throws SAXException {
				if (arg2.equalsIgnoreCase(REFERENCE)) {
					actualConnection = arg3.getValue("name");
					result.put(actualConnection, new ConnectionProperties());
				}

				if (arg2.equalsIgnoreCase(CONTENTS)) {
					contents = true;
				}

				if (arg2.equalsIgnoreCase(STRING_REF_ADDR)) {
					if (arg3.getValue(ADDR_TYPE).equalsIgnoreCase(
							CONNECTION_NAME)) {
						connectionName = true;
					}

					if (arg3.getValue(ADDR_TYPE).equalsIgnoreCase(USER)) {
						user = true;
					}
					if (arg3.getValue(ADDR_TYPE).equalsIgnoreCase(PASSWORD)) {
						password = true;
					}
					if (arg3.getValue(ADDR_TYPE).equalsIgnoreCase(HOSTNAME)) {
						hostname = true;
					}
					if (arg3.getValue(ADDR_TYPE).equalsIgnoreCase(PORT)) {
						port = true;
					}
					if (arg3.getValue(ADDR_TYPE).equalsIgnoreCase(SID)) {
						sid = true;
					}
					if (arg3.getValue(ADDR_TYPE).equalsIgnoreCase(
							CONNECTION_TYPE)) {
						connectionType = true;
					}
					if (arg3.getValue(ADDR_TYPE).equalsIgnoreCase(DRIVER_CLASS)) {
						driverClass = true;
					}
					if (arg3.getValue(ADDR_TYPE).equalsIgnoreCase(DRIVER_TYPE)) {
						driverType = true;
					}
					if (arg3.getValue(ADDR_TYPE).equalsIgnoreCase(URL)) {
						url = true;
					}
				}
			}

			@Override
			public void endElement(String arg0, String arg1, String arg2)
					throws SAXException {
			}

			@Override
			public void characters(char[] arg0, int arg1, int arg2)
					throws SAXException {
				if (contents) {
					if (connectionName) {
						result.get(actualConnection).setConnectionName(
								new String(arg0, arg1, arg2));
						connectionName = false;
					}
					if (user) {
						result.get(actualConnection).setUser(
								new String(arg0, arg1, arg2));
						user = false;
					}
					if (password) {
						try {
							result.get(actualConnection).setPassword(
									new String(decryptPassword(new String(arg0,
											arg1, arg2))));
						} catch (GeneralSecurityException e) {
							result.get(actualConnection).setPassword(
									new String(arg0, arg1, arg2));
						}
						password = false;
					}
					if (hostname) {
						result.get(actualConnection).setHostname(
								new String(arg0, arg1, arg2));
						hostname = false;
					}
					if (port) {
						result.get(actualConnection).setPort(
								new String(arg0, arg1, arg2));
						port = false;
					}
					if (sid) {
						result.get(actualConnection).setSid(
								new String(arg0, arg1, arg2));
						sid = false;
					}
					if (connectionType) {
						result.get(actualConnection).setConnectionType(
								new String(arg0, arg1, arg2));
						connectionType = false;
					}
					if (driverClass) {
						result.get(actualConnection).setDriverClass(
								new String(arg0, arg1, arg2));
						driverClass = false;
					}
					if (driverType) {
						result.get(actualConnection).setDriverType(
								new String(arg0, arg1, arg2));
						driverType = false;
					}
					if (url) {
						result.get(actualConnection).setUrl(
								new String(arg0, arg1, arg2));
						url = false;
					}
					contents = false;
				}
			}
		});
		return result;
	}
}
