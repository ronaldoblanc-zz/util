package com.github.ronaldoblanc.util;

import java.util.Properties;

public final class ConnectionProperties {
	private static final String NEW_LINE = "\n";
	private String connectionName;
	private String user;
	private String password;
	private String hostname;
	private String port;
	private String sid;
	private String connectionType;
	private String driverClass;
	private String driverType;
	private String url;

	public String getConnectionName() {
		return connectionName;
	}

	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Properties asProperties() {
		Properties result = new Properties();
		result.put("jdbc.driverClassName", getDriverClass());
		result.put("jdbc.url", getUrl());
		result.put("jdbc.username", getUser());
		result.put("jdbc.password", getPassword());
		return result;
	}

	public String asPropertiesFileEntries() {
		StringBuilder sb = new StringBuilder("### Connection name: ["
				+ connectionName + "]").append(NEW_LINE);
		sb.append("jdbc.driverClassName=" + getDriverClass()).append(NEW_LINE);
		sb.append("jdbc.url=" + getUrl()).append(NEW_LINE);
		sb.append("jdbc.username=" + getUser()).append(NEW_LINE);
		sb.append("jdbc.schema=" + getUser()).append(NEW_LINE);
		sb.append("jdbc.password=" + getPassword()).append(NEW_LINE);
		return sb.toString();
	}

	public String toString() {
		return "ConnectionProperties [connectionName=" + getConnectionName()
				+ ", user=" + getUser() + ", password=XXXXXX" + ", hostname="
				+ getHostname() + ", port=" + getPort() + ", sid=" + getSid()
				+ ", connectionType=" + getConnectionType() + ", driverClass="
				+ getDriverClass() + ", driverType=" + getDriverType()
				+ ", url=" + getUrl() + "]";
	}
}
