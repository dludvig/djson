package br.com.dludvig.djson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class TypeContext {

	private static final Logger log = Logger.getLogger(TypeContext.class.getName() );

	private boolean json = false;
	private int level = 0;

	public TypeContext() {
		this("json");
	}

	public TypeContext(String type) {
		if (type=="json") {
			this.json = true;
			log.info("Conversion type has been set: JSON.");
		}
	}

	public String increment(boolean tab){
		String result = "\n";
		if (tab)
			level++;
		result += repeat("\t", level);

		return result;
	}
	public String decrement(boolean tab){
		String result = "\n";
		if (tab)
			level = Math.max(level-1, 0);
		result += repeat("\t", level);

		return result;
	}

	public String getValue(String property, Object value){
		String result = "";
		if (json) {
			if (property.length()>0)
				result += "\""+property+"\": ";
			
			if (value == null)
				result += "null";
			else if (value instanceof Date)
				result += "\""+(new SimpleDateFormat("dd/MM/yyyy").format(value))+"\"";
			else if (value instanceof String)
				result += "\""+value+"\"";
			else
				result += value.toString();
		}
		
		return result;
	}

	public String getOpenObject(){
		String result = "";
		if (json)
			result = "{";

		return result;
	}

	public String getCloseObject(){
		String result = "";
		if (json)
			result = "}";

		return result;
	}

	public String getOpenArray(String property){
		String result = "";
		if (json) {
			if (property.length()>0)
				result += "\""+property+"\": ";
			result += "[";
		}

		return result;
	}

	public String getCloseArray(){
		String result = "";
		if (json)
			result = "]";

		return result;
	}

	private String repeat(String string, int qty) {   
		StringBuffer retorno = new StringBuffer();   
		for (int i=0; i<qty; i++) {
			retorno.append(string);   
		}   
		return retorno.toString();   
	}  
}