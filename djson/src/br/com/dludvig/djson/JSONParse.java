package br.com.dludvig.djson;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

public class JSONParse {

	private static final Logger log = Logger.getLogger(JSONParse.class.getName() );
	
	private TypeContext typeContext;

	public JSONParse(TypeContext typeContext) {
		this.typeContext = typeContext;
	}

	public String parse(Object obj) {
		return parse(obj, null);
	}
	
	public String parseList(List<?> list) {
		StringBuffer strParse = new StringBuffer();
		
		strParse.append( typeContext.getOpenObject() );
		
		strParse.append( getList(list, null) );
		
		strParse.append( typeContext.decrement(true));
		strParse.append( typeContext.getCloseObject());
		
		return strParse.toString();
	}
	
	public String parse(Object obj, String property) {
		log.info("Object conversion has been initiated.");
		
		ObjectInfo objInfo = new ObjectInfo(obj);
		Queue<ObjectProperties> objects = objInfo.getInfo();
		
		StringBuffer strParse = new StringBuffer();   
		
		if (property != null) {
			strParse.append( "\""+property+"\": " );
		}
		
		strParse.append( typeContext.getOpenObject() );
		boolean first = true;
		
		for (ObjectProperties objProp : objects) {
			if (!first)
				strParse.append(",");
			
			strParse.append( typeContext.increment(first));
			first=false;
			
			Object value = getValue(obj, objProp.getMet());
			Class<?>  returnType = objProp.getMet().getReturnType();

			if (value == null) {
				strParse.append( typeContext.getValue(objProp.getProperty(), value));
			} else if (returnType.isArray()) {
				strParse.append( getArray(value, objProp.getProperty()));
			} else if (value instanceof java.util.AbstractList<?>) {
				strParse.append( getList(value, objProp.getProperty()));
			}else if (returnType.isAnnotationPresent(JSONT.class)) {
				strParse.append( parse(value, objProp.getProperty()));
			} else 
				strParse.append( typeContext.getValue(objProp.getProperty(), value));

		}
		
		strParse.append( typeContext.decrement(true));
		strParse.append( typeContext.getCloseObject());
		
		return strParse.toString();
	}
	
	private String getList(Object list, String property) {
		log.info("List conversion has been initiated.");
		
		StringBuffer result = new StringBuffer();   
		
		if (property != null)
			result.append( typeContext.getOpenArray(property));
		
		boolean first = true;
		boolean jsonObject = false;
		for (Object obj : (Iterable<?>)list) {
			if (!first) 
				result.append( ", ");

			if (obj == null) {
				result.append( typeContext.getValue("", obj));
			} else if (obj instanceof java.util.AbstractList<?>) {
				result.append( getList(obj, ""));
			}else if (obj.getClass().isAnnotationPresent(JSONT.class)) {
				jsonObject = true;
				if (first)
					result.append(typeContext.increment(true));
				result.append( parse(obj));
			} else 
				result.append( typeContext.getValue("", obj));
			
			first = false;
		}
		
		if (jsonObject)
			result.append( typeContext.decrement(true));
		
		if (property != null)
			result.append( typeContext.getCloseArray());
		
		return result.toString();
	}

	private String getArray(Object value, String property) {
		log.info("Array conversion has been initiated.");
		
		StringBuffer result = new StringBuffer();   
		result.append( typeContext.getOpenArray(property));
		
		int length = Array.getLength(value);
		for (int i = 0; i < length; ++i) {
			if (i!=0)
				result.append( ", ");
			Object objArray = Array.get(value,i);
			if (objArray.getClass().isArray()) {
				result.append( getArray(objArray, ""));
			} else {
				result.append(typeContext.getValue("", objArray));
			}
		}
		result.append( typeContext.getCloseArray());
		
		return result.toString();
	}
	
	
	private Object getValue(Object instance, Method method) {
		try {
			if (method != null ) {
				return method.invoke(instance , (Object[]) null);
			} else {
				return null;
			}
		} catch (InvocationTargetException e) {
			throw new JSONException("An error occurred reading the property " + instance.getClass().getName() + "." + method.getName(), e);
		} catch (IllegalAccessException e) {
			throw new JSONException("An error occurred reading the property " + instance.getClass().getName() + "." + method.getName(), e);
		}
	}

}