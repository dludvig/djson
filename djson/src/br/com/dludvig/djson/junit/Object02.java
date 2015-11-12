package br.com.dludvig.djson.junit;

import java.util.List;

import br.com.dludvig.djson.JSONT;

@JSONT
public class Object02 {

	private Long ID;
	private String ident;
	private List<String> months;
	
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public List<String> getMonths() {
		return months;
	}
	public void setMonths(List<String> months) {
		this.months = months;
	}
}