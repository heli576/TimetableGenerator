package com.springboot.timetable.service;

public class Utility {
	public static void printInputData(){
		System.out.println("Number of batches="+InputData.nobatch+" Number of teachers="+InputData.noteacher+" daysperweek="+InputData.daysperweek+" hoursperday="+InputData.hoursperday);
		for(int i=0;i<InputData.nobatch;i++){
			
			System.out.println(InputData.batch[i].id+" "+InputData.batch[i].name);
			
			for(int j=0;j<InputData.batch[i].noOfSubject;j++){
				System.out.println(InputData.batch[i].subject[j]+" "+InputData.batch[i].hours[j]+" hrs "+InputData.batch[i].teacherId[j]);
			}
			System.out.println("");
		}
		
		for(int i=0;i<InputData.noteacher;i++){			
			System.out.println(InputData.teacher[i].id+" "+InputData.teacher[i].name+" "+InputData.teacher[i].subject+" "+InputData.teacher[i].assigned);
		}
	}
	
	
	public static void printSlots(){
		
		int days=InputData.daysperweek;
		int hours=InputData.hoursperday;
		int nostgrp=InputData.nobatch;
		System.out.println("----Slots----");
		for(int i=0;i<days*hours*nostgrp;i++){
			if(Timetable.slot[i]!=null)
				System.out.println(i+"- "+Timetable.slot[i].batch.name+" "+Timetable.slot[i].subject+" "+Timetable.slot[i].teacherId);
			else
				System.out.println("Free Period");
			if((i+1)%(hours*days)==0) System.out.println("******************************");
		}
		
	}
}
