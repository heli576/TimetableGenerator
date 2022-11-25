package com.springboot.timetable.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.springboot.timetable.model.Result;

public class Scheduler {
	List<Chromosome> firstlist;
	List<Chromosome> newlist;
	double firstlistfitness;
	double newlistfitness;
	int populationsize=1000;
	int maxgenerations=100;
	public List<Result> ans = new ArrayList<>();
	public static Chromosome finalson;
	public Scheduler() {
		System.out.println("scheduler run");
		Utility.printInputData();
		
		//generating slots
		new Timetable();
		Utility.printSlots();
		
		//initialising first generation of chromosomes
		initialisePopulation();
		
		//generating newer generation of chromosomes using crossovers and mutation
		ans = createNewGenerations();
		
	}
	List<Result> timetable = new ArrayList<>();
	public List<Result> createNewGenerations(){
		
		Chromosome father=null;
		Chromosome mother=null;
		Chromosome son=null;
		
		int nogenerations=0;

		while(nogenerations<maxgenerations){	
			
			newlist=new ArrayList<Chromosome>();
			newlistfitness=0;
			int i=0;
			
			for(i=0;i<populationsize/10;i++){	
				newlist.add(firstlist.get(i).deepClone());		
				newlistfitness+=firstlist.get(i).getFitness();
			}
			
			while(i<populationsize){
				
				father=selectParentRoulette();
				mother=selectParentRoulette();
		
				//crossover
				if(new Random().nextDouble()<InputData.crossoverrate){
					son=crossover(father,mother);	
				}else
					son=father;
				customMutation(son);
				
				
				if(son.fitness==1){
					System.out.println("Selected Chromosome is:-");
					son.printChromosome();
					break;
				}
				
				newlist.add(son);
				newlistfitness+=son.getFitness();
				i++;
				
			}
			
			//if chromosome with fitness 1 found
			if(i<populationsize){
				
				System.out.println("****************************************************************************************");
				System.out.println("\n\nSuitable Timetable has been generated in the "+i+"th Chromosome of "+(nogenerations+2)+" generation with fitness 1.");
				System.out.println("\nGenerated Timetable is:");
				timetable = son.printTimetable();
				finalson=son;
				break;
				
			}
			
			//if chromosome with required fitness not found in this generation
			firstlist=newlist;
			Collections.sort(newlist);Collections.sort(firstlist);
			System.out.println("**************************     Generation"+(nogenerations+2)+"     ********************************************\n");
			printGeneration(newlist);
			nogenerations++;

		}
		return timetable;
	}
	
	//select from the best 10% chromosomes
	public Chromosome selectParentRoulette(){
			
		firstlistfitness/=10;
		double randomdouble=new Random().nextDouble()*firstlistfitness;
		double currentsum=0;
		int i=0;

		while(currentsum<=randomdouble){
			currentsum+=firstlist.get(i++).getFitness();
		}
		return firstlist.get(--i).deepClone();

	}
		
		
	//custom mutation
	public void customMutation(Chromosome c){
				
		double newfitness=0,oldfitness=c.getFitness();
		int geneno=new Random().nextInt(InputData.nobatch);
				
		int i=0;
		while(newfitness<oldfitness){
			c.gene[geneno]=new Gene(geneno);
			newfitness=c.getFitness();		
			i++;
			if(i>=500000) break;
		}
				
	}	
		
	
	//Two point crossover
	public Chromosome crossover(Chromosome father,Chromosome mother){
			
		int randomint=new Random().nextInt(InputData.nobatch);
		Gene temp=father.gene[randomint].deepClone();
		father.gene[randomint]=mother.gene[randomint].deepClone();
		mother.gene[randomint]=temp;
		if(father.getFitness()>mother.getFitness())return father;
		else return mother;
		
	}
	
	//initialising first generation of population
	public void initialisePopulation(){
		
		//generating first generation of chromosomes and keeping them in an arraylist
		firstlist=new ArrayList<Chromosome>();
		firstlistfitness=0;
		
		for(int i=0;i<populationsize;i++){
		
			Chromosome c;
			firstlist.add(c=new Chromosome());
			firstlistfitness+=c.fitness;
			
		}
		Collections.sort(firstlist);
		System.out.println("----------Initial Generation-----------\n");
		printGeneration(firstlist);
		
	}
	
	
	//printing important details of a generation
	public void printGeneration(List<Chromosome> list){
		
		System.out.println("Fetching details from this generation...\n");	
		
		//to print only initial 4 chromosomes of sorted list
		for(int i=0;i<4;i++){
			System.out.println("Chromosome no."+i+": "+list.get(i).getFitness());
			list.get(i).printChromosome();
			System.out.println("");
		}
		
		System.out.println("Chromosome no. "+(populationsize/10+1)+" :"+list.get(populationsize/10+1).getFitness()+"\n");
		System.out.println("Chromosome no. "+(populationsize/5+1)+" :"+list.get(populationsize/5+1).getFitness()+"\n");
		System.out.println("Most fit chromosome from this generation has fitness = "+list.get(0).getFitness()+"\n");
		
	}
	
	
	//selecting from best chromosomes only(alternate to roulette wheel selection)
	public Chromosome selectParentBest(List<Chromosome> list){
		
		Random r=new Random();
		int randomint=r.nextInt(100);
		return list.get(randomint).deepClone();

	}
	
	
	//simple Mutation operation
	public void mutation(Chromosome c){
		int geneno=new Random().nextInt(InputData.nobatch);
		int temp=c.gene[geneno].slotno[0];
		for(int i=0;i<InputData.daysperweek*InputData.hoursperday-1;i++){
			c.gene[geneno].slotno[i]=c.gene[geneno].slotno[i+1];
		}
		c.gene[geneno].slotno[InputData.daysperweek*InputData.hoursperday-1]=temp;
	}
	
	
	//swap mutation
	public void swapMutation(Chromosome c){
		
		int geneno=new Random().nextInt(InputData.nobatch);
		int slotno1=new Random().nextInt(InputData.hoursperday*InputData.daysperweek);
		int slotno2=new Random().nextInt(InputData.hoursperday*InputData.daysperweek);
		
		int temp=c.gene[geneno].slotno[slotno1];
		c.gene[geneno].slotno[slotno1]=c.gene[geneno].slotno[slotno2];
		c.gene[geneno].slotno[slotno2]=temp;
	}
	
	
	
	
//	public static void main(String[] args) {
//		new Scheduler();
//	}
}
