package br.com.dludvig.djson.junit;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Object01 {

	private String name;
	private int age;
	private Date birth;
	private double[] numbers;
	private double[][] annualBalance;
	private BigDecimal salary;
	private List<String> children;
	private String[] skills;
	private Object02 test02;
	private List<Object02> listObj2;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public double[] getNumbers() {
		return numbers;
	}
	public void setNumbers(double[] numbers) {
		this.numbers = numbers;
	}
	public double[][] getAnnualBalance() {
		return annualBalance;
	}
	public void setAnnualBalance(double[][] annualBalance) {
		this.annualBalance = annualBalance;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public List<String> getChildren() {
		return children;
	}
	public void setChildren(List<String> children) {
		this.children = children;
	}
	public String[] getSkills() {
		return skills;
	}
	public void setSkills(String[] skills) {
		this.skills = skills;
	}
	
	public Object02 getTest02() {
		return test02;
	}
	public void setTest02(Object02 test02) {
		this.test02 = test02;
	}
	public List<Object02> getListObj2() {
		return listObj2;
	}
	public void setListObj2(List<Object02> listObj2) {
		this.listObj2 = listObj2;
	}
	
}