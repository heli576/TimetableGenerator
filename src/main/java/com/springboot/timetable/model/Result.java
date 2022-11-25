package com.springboot.timetable.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
public int day;
public int hours;
public String batchName;
@OneToMany
@JoinColumn(name="id")
public List<ResObj> res;
public int getDay() {
	return day;
}
public void setDay(int day) {
	this.day = day;
}
public int getHours() {
	return hours;
}
public void setHours(int hours) {
	this.hours = hours;
}
public String getBatchName() {
	return batchName;
}
public void setBatchName(String batchName) {
	this.batchName = batchName;
}
public List<ResObj> getRes() {
	return res;
}
public void setRes(List<ResObj> res) {
	this.res = res;
}

}
