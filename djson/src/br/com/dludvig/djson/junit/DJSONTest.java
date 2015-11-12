package br.com.dludvig.djson.junit;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.dludvig.djson.JSONTransformer;
import junit.framework.TestCase;

public class DJSONTest extends TestCase {

	public void testSimpleObject(){
		ObjectSimple obj = new ObjectSimple();
		obj.setName("Involves");
		
		JSONTransformer jsonTransformer = new JSONTransformer();
		OutputStream stream = new ByteArrayOutputStream();
		jsonTransformer.transform(stream, obj);
		
		assertEquals("{\"name\": \"Involves\"}", stream.toString().replaceAll("\t", "").replaceAll("\n", "") );
	}
	
	public void testArrayObject(){
		ObjectArray obj = new ObjectArray();
		obj.setAges(new int[]{6, 31});
		obj.setNames(new String[]{"Involves", "Diogo"});
		
		JSONTransformer jsonTransformer = new JSONTransformer();
		OutputStream stream = new ByteArrayOutputStream();
		jsonTransformer.transform(stream, obj);
		
		assertEquals("{\"ages\": [6, 31],\"names\": [\"Involves\", \"Diogo\"]}", stream.toString().replaceAll("\t", "").replaceAll("\n", "") );
	}
	
	public void testListObject(){
		ObjectList obj = new ObjectList();
		List<String> list = new ArrayList<>();
		list.add("First");
		list.add("Second");
		list.add("Third");
		obj.setList(list);
		
		JSONTransformer jsonTransformer = new JSONTransformer();
		OutputStream stream = new ByteArrayOutputStream();
		jsonTransformer.transform(stream, obj);
		
		assertEquals("{\"list\": [\"First\", \"Second\", \"Third\"]}", stream.toString().replaceAll("\t", "").replaceAll("\n", "") );
	}
	
	public void testObjectObject(){
		ObjectObject obj = new ObjectObject();
		
		obj.setName("Involves");
		
		ObjectSimple objSimple = new ObjectSimple();
		objSimple.setName("Test Object");
		
		obj.setObj(objSimple);
		
		JSONTransformer jsonTransformer = new JSONTransformer();
		OutputStream stream = new ByteArrayOutputStream();
		jsonTransformer.transform(stream, obj);
		
		assertEquals("{\"name\": \"Involves\",\"obj\": {\"name\": \"Test Object\"}}", stream.toString().replaceAll("\t", "").replaceAll("\n", "") );
	}
	
	public void testList(){
		ObjectSimple obj1 = new ObjectSimple();
		obj1.setName("Involves");
		
		ObjectSimple obj2 = new ObjectSimple();
		obj2.setName("Diogo");
		
		ObjectSimple obj3 = new ObjectSimple();
		obj3.setName("XXX");
		
		List<ObjectSimple> list = new ArrayList<>();
		list.add(obj1);
		list.add(obj2);
		list.add(obj3);
		
		JSONTransformer jsonTransformer = new JSONTransformer();
		OutputStream stream = new ByteArrayOutputStream();
		jsonTransformer.transform(stream, list);
		
		assertEquals("{{\"name\": \"Involves\"}, {\"name\": \"Diogo\"}, {\"name\": \"XXX\"}}", stream.toString().replaceAll("\t", "").replaceAll("\n", "") );
	}
	
	public void testComplex(){
		Object01 obj = new Object01();
		obj.setName("Diogo");
		obj.setAge(31);
		obj.setBirth(new Date());
		obj.setNumbers(new double[]{9.8, 10, 6.5});
		obj.setAnnualBalance(new double[][]{{2014, 90.5},{2015, 100.1},{2016, 120.56}});
		obj.setSalary(new BigDecimal("2200.58"));
		obj.setSkills(new String[]{"Java", "Delphi", "HTML", "Isto e aquilo"});
		List<String> children = new ArrayList<String>();
		children.add("Liana");
		children.add("Fabio");
		children.add("Diogo");
		obj.setChildren(children);
		
		Object02 obj2 = new Object02();
		obj2.setID(183981293892L);
		obj2.setIdent("DH238Hdsja924893dakh38");
		List<String> months = new ArrayList<String>();
		months.add("january");
		months.add("october");
		months.add("december");
		obj2.setMonths(months);
		
		obj.setTest02(obj2);
		
		Object02 objList1 = new Object02();
		objList1.setID(7767657576767575L);
		objList1.setIdent("111111aababab");
		List<String> monthsList1 = new ArrayList<String>();
		monthsList1.add("february");
		monthsList1.add("august");
		monthsList1.add("november");
		objList1.setMonths(monthsList1);
		
		Object02 objList2 = new Object02();
		objList2.setID(112121212121212L);
		objList2.setIdent("aaaaa888898989bbbbbb");
		List<String> monthsList2 = new ArrayList<String>();
		monthsList2.add("july");
		monthsList2.add("june");
		monthsList2.add("march");
		objList2.setMonths(monthsList2);
		
		List<Object02> listObj2 = new ArrayList<>();
		listObj2.add(objList1);
		listObj2.add(objList2);
		
		obj.setListObj2(listObj2);
		
		JSONTransformer jsonTransformer = new JSONTransformer();
		OutputStream stream = new ByteArrayOutputStream();
		jsonTransformer.transform(stream, obj);
	}

}