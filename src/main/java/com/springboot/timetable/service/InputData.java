package com.springboot.timetable.service;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.springboot.timetable.model.Batch;
import com.springboot.timetable.model.Teacher;

public class InputData {
	public static Batch[] batch;
	public static Teacher[] teacher;
	public static double crossoverrate = 1.0;
	public static double mutationrate = 0.1;
	public static int nobatch;
	public static int noteacher;
	public static int hoursperday;
	public static int daysperweek;
	
//	public Batch[] batch;
//	public Teacher[] teacher;
//	public double crossoverrate = 1.0;
//	public double mutationrate = 0.1;
//	public int nobatch;
//	public int noteacher;
//	public int hoursperday;
//	public int daysperweek;

	public InputData() {
		batch = new Batch[100];
		teacher =   new Teacher[100];
	}

	boolean classformat(String l) {
		StringTokenizer st = new StringTokenizer(l, " ");
		if (st.countTokens() == 3)
			return (true);
		else
			return (false);
	}

	public void takeinput()// takes input from file input.txt
	{
		hoursperday = 6;
		daysperweek = 5;
		try {
			File file = new File("/home/heli/SpringBoot/timetable/src/main/java/com/springboot/timetable/service/input.txt");
			
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				// input batchs
				if (line.equalsIgnoreCase("batchs")) {
					int i = 0, j;
					while (!(line = scanner.nextLine()).equalsIgnoreCase("teachers")) {
						batch[i] = new Batch();
						StringTokenizer st = new StringTokenizer(line, " ");
						batch[i].id = i;
						batch[i].name = st.nextToken();
						batch[i].noOfSubject = 0;
						j = 0;
						while (st.hasMoreTokens()) {
							batch[i].subject[j] = st.nextToken();
							batch[i].hours[j++] = Integer.parseInt(st.nextToken());
							batch[i].noOfSubject++;
						}
						i++;
					}
					nobatch = i;
				}

				//input teachers
				if (line.equalsIgnoreCase("teachers")) {
					int i = 0;
					while (!(line = scanner.nextLine()).equalsIgnoreCase("end")) {
						teacher[i] = new Teacher();
						StringTokenizer st = new StringTokenizer(line, " ");
						teacher[i].id = i;
						teacher[i].name = st.nextToken();
						teacher[i].subject = st.nextToken();

						i++;
					}
					noteacher = i;
				}

			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		assignTeacher();

	}

	// assigning a teacher for each subject for every batch
	public static void assignTeacher() {
		for (int i = 0; i < nobatch; i++) {

			// looping through every subject of a student group
			for (int j = 0; j < batch[i].noOfSubject; j++) {

				int teacherId = -1;
				int assignedmin = -1;

				String subject = batch[i].subject[j];
				for (int k = 0; k < noteacher; k++) {
					if (teacher[k].subject.equalsIgnoreCase(subject)) {

						// if first teacher found for this subject
						if (assignedmin == -1) {
							assignedmin = teacher[k].assigned;
							teacherId = k;
						}

						// if teacher found has less no of pre assignments than the teacher assigned for this subject
						else if (assignedmin > teacher[k].assigned) {
							assignedmin = teacher[k].assigned;
							teacherId = k;
						}
					}
				}
				teacher[teacherId].assigned++;

				batch[i].teacherId[j]= teacherId;
			}
		}
	}

	public Batch[] getBatch() {
		return batch;
	}

	public void setBatch(Batch[] batch) {
		this.batch = batch;
	}

	public Teacher[] getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher[] teacher) {
		this.teacher = teacher;
	}

	public double getCrossoverrate() {
		return crossoverrate;
	}

	public void setCrossoverrate(double crossoverrate) {
		this.crossoverrate = crossoverrate;
	}

	public double getMutationrate() {
		return mutationrate;
	}

	public void setMutationrate(double mutationrate) {
		this.mutationrate = mutationrate;
	}

	public int getNobatch() {
		return nobatch;
	}

	public void setNobatch(int nobatch) {
		this.nobatch = nobatch;
	}

	public int getNoteacher() {
		return noteacher;
	}

	public void setNoteacher(int noteacher) {
		this.noteacher = noteacher;
	}

	public int getHoursperday() {
		return hoursperday;
	}

	public void setHoursperday(int hoursperday) {
		this.hoursperday = hoursperday;
	}

	public int getDaysperweek() {
		return daysperweek;
	}

	public void setDaysperweek(int daysperweek) {
		this.daysperweek = daysperweek;
	}


}
