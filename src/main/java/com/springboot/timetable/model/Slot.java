package com.springboot.timetable.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Slot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	@OneToOne
	@JoinColumn(name="id")
	public Batch batch;
	public int teacherId;
	public String subject;
	
   public Slot(Batch batch,int teacherId,String subject){
		
		this.batch=batch;
		this.subject=subject;
		this.teacherId=teacherId;
	
	}
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
