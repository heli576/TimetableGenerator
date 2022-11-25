package com.springboot.timetable.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String name;
	public String[] subject;
	public int noOfSubject;
	public int teacherId[];
	public int[] hours;
	
	public Batch() {
		subject = new String[10];
		hours = new int[10];
		teacherId = new int[10];
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

	public String[] getSubject() {
		return subject;
	}

	public void setSubject(String[] subject) {
		this.subject = subject;
	}

	public int getNoOfSubject() {
		return noOfSubject;
	}

	public void setNoOfSubject(int noOfSubject) {
		this.noOfSubject = noOfSubject;
	}

	public int[] getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int[] teacherId) {
		this.teacherId = teacherId;
	}

	public int[] getHours() {
		return hours;
	}

	public void setHours(int[] hours) {
		this.hours = hours;
	}
}
