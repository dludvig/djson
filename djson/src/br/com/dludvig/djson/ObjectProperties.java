package br.com.dludvig.djson;

import java.lang.reflect.Method;

public class ObjectProperties {

	public ObjectProperties() {
		// TODO Auto-generated constructor stub
	}
	
	private Method met;
	private String property;
	
	public Method getMet() {
		return met;
	}
	public void setMet(Method met) {
		this.met = met;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}

}