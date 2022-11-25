package com.springboot.timetable.service;

import com.springboot.timetable.model.Batch;
import com.springboot.timetable.model.Slot;

public class Timetable {
	public static Slot[] slot;

	Timetable() {

		int k = 0;
		int subjectno = 0;
		int hourcount = 1;
		int days = InputData.daysperweek;
		int hours = InputData.hoursperday;
		int nostgrp = InputData.nobatch;

		//create slots
		slot = new Slot[hours * days * nostgrp];

		// looping for every batch
		for (int i = 0; i < nostgrp; i++) {

			subjectno = 0;
			// for every slot in a week for a student group
			for (int j = 0; j < hours * days; j++) {

				Batch sg = InputData.batch[i];
				if (subjectno >= sg.noOfSubject) {
					slot[k++] = null;
				}

				// create new slot
				else {

					slot[k++] = new Slot(sg, sg.teacherId[subjectno], sg.subject[subjectno]);

					if (hourcount < sg.hours[subjectno]) {
						hourcount++;
					} else {
						hourcount = 1;
						subjectno++;
					}

				}

			}

		}

	}

	public static Slot[] returnSlots() {
		return slot;
	}
}
