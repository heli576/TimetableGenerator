package com.springboot.timetable.service;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.springboot.timetable.model.ResObj;
import com.springboot.timetable.model.Result;
import com.springboot.timetable.model.Slot;


public class Chromosome implements Serializable, Comparable<Chromosome> {
	private static final long serialVersionUID = 1L;
	static double crossoverrate=InputData.crossoverrate;
	static double mutationrate=InputData.mutationrate;
	static int hours=InputData.hoursperday,days=InputData.daysperweek;
	static int nostgrp=InputData.nobatch;
	double fitness;
	int point;
	
	public Gene[] gene;
	
	Chromosome(){
		
		gene=new Gene[nostgrp];
		
		for(int i=0;i<nostgrp;i++){
			
			gene[i]=new Gene(i);
			
			//System.out.println("");
		}
		fitness=getFitness();		
		
	}
	
	public Chromosome deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Chromosome) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	public double getFitness(){
		point=0;
		for(int i=0;i<hours*days;i++){
			
			List<Integer> teacherlist=new ArrayList<Integer>();

			for(int j=0;j<nostgrp;j++){
			
				Slot slot;
				//System.out.println("i="+i+" j="+j);
				if(Timetable.slot[gene[j].slotno[i]]!=null)
					slot=Timetable.slot[gene[j].slotno[i]];
				else slot=null;
				if(slot!=null){
				
					if(teacherlist.contains(slot.teacherId)){
						point++;
					}
					else teacherlist.add(slot.teacherId);
				}
			}	
			
			
		}
		fitness=1-(point/((nostgrp-1.0)*hours*days));
		point=0;
		return fitness;
	}
	
	public String getTeacherName(int id) {
		for(int i = 0;i<InputData.noteacher;i++) {
			if(i==id) return InputData.teacher[i].getName();
		}
		return "";
	}
	
	List<Result> timetable = new ArrayList<>();
	//printing final Timetable
	public List<Result> printTimetable(){
		
		//for each student group separate time table
		for(int i=0;i<nostgrp;i++){
			Result r = new Result();
			//status used to get name of student grp because in case first class is free it will throw error
			boolean status=false;
			int l=0;
			while(!status){
				
				//printing name of batch
				if(Timetable.slot[gene[i].slotno[l]]!=null){
					System.out.println("Batch "+Timetable.slot[gene[i].slotno[l]].batch.name+" Timetable-");
					r.setBatchName(Timetable.slot[gene[i].slotno[l]].batch.name);
					status=true;
				}
				l++;
			
			}
			r.setDay(days);
			r.setHours(hours);
			List<ResObj> res = new ArrayList<>();
			//looping for each day
			for(int j=0;j<days;j++){
				
				//looping for each hour of the day
				for(int k=0;k<hours;k++){
				   ResObj obj = new ResObj();
					//checking if this slot is free otherwise printing it
					if(Timetable.slot[gene[i].slotno[k+j*hours]]!=null) {
						System.out.print(Timetable.slot[gene[i].slotno[k+j*hours]].subject+"(" +getTeacherName(Timetable.slot[gene[i].slotno[k+j*hours]].teacherId)+") ");
					    obj.setSubject(Timetable.slot[gene[i].slotno[k+j*hours]].subject);
					    obj.setTeacher(getTeacherName(Timetable.slot[gene[i].slotno[k+j*hours]].teacherId));
				
					}	
					else {
						System.out.print("*FREE* ");
						obj.setSubject("FREE");
						obj.setTeacher("");
					}
					res.add(obj);
				
				}
				r.setRes(res);
				System.out.println("");
			}
			timetable.add(r);
			System.out.println("\n\n\n");
		
		}
		return timetable;
	}
	
	
	
	public void printChromosome(){
		
		for(int i=0;i<nostgrp;i++){
			for(int j=0;j<hours*days;j++){
				System.out.print(gene[i].slotno[j]+" ");
			}
			System.out.println("");
		}
		
	}



	public int compareTo(Chromosome c) {
		
		if(fitness==c.fitness) return 0;
		else if(fitness>c.fitness) return -1;
		else return 1;
	
	}
}
