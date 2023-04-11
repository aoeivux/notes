package com.aoeivux.八股;

public class People {
	private Integer age;
	private String name;
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "People [age=" + age + ", name=" + name + "]";
	}
	public People(Integer age, String name) {
		this.age = age;
		this.name = name;
	}
	
}
