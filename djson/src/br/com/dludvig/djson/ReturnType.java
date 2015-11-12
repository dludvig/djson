package br.com.dludvig.djson;

public class ReturnType {

	public static final ReturnType JSON = new ReturnType("json");
	//public static final ReturnType XML = new ReturnType("xml");
	
	private String type = "";
	
	private ReturnType() {}

	private ReturnType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
}
