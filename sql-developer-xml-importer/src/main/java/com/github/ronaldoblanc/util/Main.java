package com.github.ronaldoblanc.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String... args) throws GeneralSecurityException,
			ParserConfigurationException, SAXException, IOException {

		if (args.length < 1) {
			System.err.println("Usage:");
			System.err
					.println("sql-developer-jdbc-helper <XML input data> <-p> [Caution: plain text password]");
			System.exit(-99);
		}

		boolean properties = false;
		if (args.length > 1) {
			properties = true;
		}
		String inputData = args[0];
		Map<String, ConnectionProperties> connections = new SqlDeveloperXmlParser()
				.getConnections(inputData);
		for (String key : connections.keySet()) {
			ConnectionProperties connection = connections.get(key);
			if (properties) {
				System.out.println(connection.asPropertiesFileEntries());
			} else {
				System.out.println(connection.toString());
			}
		}
	}
}
