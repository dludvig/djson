package br.com.dludvig.djson;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.TreeSet;

public class ObjectInfo {

	private SortedSet<Method> methods = new TreeSet<Method>(new MethodComparator());

	public ObjectInfo(Object obj) {
		methods.addAll(Arrays.asList(obj.getClass().getDeclaredMethods()));
	}
	
	public Queue<ObjectProperties> getInfo() {
		Queue<ObjectProperties> listObjProps = new LinkedList<>();
		
		for (Method method: methods) {
			String name = method.getName();
			int modifier = method.getModifiers();
			int numberOfArgs = method.getParameterTypes().length;
			Class<?> returnType =  method.getReturnType();
			
			String property;

			if (name.startsWith("get") && (numberOfArgs == 0) && (modifier == Modifier.PUBLIC) && (returnType.getName()!="void")) {
				property = Character.toLowerCase( name.charAt(3) ) + name.substring(4);

				ObjectProperties objProp = new ObjectProperties();
				objProp.setMet(method);
				objProp.setProperty(property);
				listObjProps.add(objProp);
				
			}
		}
		
		return listObjProps;

	}

	private static class MethodComparator implements Comparator<Method> {

		public int compare(Method m1, Method m2) {
			return (m1.getName().compareTo(m2.getName()));
		}
	}
	
}