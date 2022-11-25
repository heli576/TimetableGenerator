package com.springboot.timetable.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

public class Gene implements Serializable {
	private static final long serialVersionUID = 1L;
	public int slotno[];
	int days=InputData.daysperweek;
	int hours=InputData.hoursperday;
	
	Random r=new Random();
	
	Gene(int i){
		
		boolean[] flag=new boolean[days*hours];
		
		/*  generating an array of slot no corresponding to index of gene eg suppose index
		 *	is 2 then slotno will vary from 2*hours*days to 3*hours*days
		 */
		
		slotno=new int[days*hours];
		
		for(int j=0;j<days*hours;j++){
			
			int rnd;
			while(flag[rnd=r.nextInt(days*hours)]==true){}
			flag[rnd]=true;
			slotno[j]=i*days*hours + rnd;
			
			/*	Slot[] slot=TimeTable.returnSlots();
			 *	if(slot[slotno[j]]!=null)System.out.print(slot[slotno[j]].subject+" ");
			 *	else System.out.print("break ");
			 */
		
		}
		
	}
	
	public Gene deepClone() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this);

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Gene) ois.readObject();
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
}
