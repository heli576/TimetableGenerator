package com.springboot.timetable.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private Teacher[] teacher;
	private int noOfTeachers;
	
	private Subject() {
		teacher = new Teacher[20];
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Teacher[] getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher[] teacher) {
		this.teacher = teacher;
	}

	public int getNoOfTeachers() {
		return noOfTeachers;
	}

	public void setNoOfTeachers(int noOfTeachers) {
		this.noOfTeachers = noOfTeachers;
	}
	
}
