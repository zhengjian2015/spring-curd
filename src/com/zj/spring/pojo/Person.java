package com.zj.spring.pojo;

public class Person {
	private String name;
	private Integer age;
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("���ù��췽��");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	
	
}
