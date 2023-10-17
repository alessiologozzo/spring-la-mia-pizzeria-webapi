package org.lessons.java.spring.utils;

public class StringDTO {
	private String value;
	
	public StringDTO() {
		value = "";
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
