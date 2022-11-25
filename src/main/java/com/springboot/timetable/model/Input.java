package com.springboot.timetable.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Input {
@Id
public int id;
public int days;
public int hours;
public Teacher[] teacher;
public Batch[] batch;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getDays() {
	return days;
}
public void setDays(int days) {
	this.days = days;
}
public int getHours() {
	return hours;
}
public void setHours(int hours) {
	this.hours = hours;
}
public Teacher[] getTeacher() {
	return teacher;
}
public void setTeacher(Teacher[] teacher) {
	this.teacher = teacher;
}
public Batch[] getBatch() {
	return batch;
}
public void setBatch(Batch[] batch) {
	this.batch = batch;
}

}
