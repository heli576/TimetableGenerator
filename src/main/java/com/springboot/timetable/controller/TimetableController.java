package com.springboot.timetable.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.timetable.model.Input;
import com.springboot.timetable.model.Result;
import com.springboot.timetable.service.InputData;
import com.springboot.timetable.service.Scheduler;

@RestController
public class TimetableController {
	@RequestMapping(value="/timetable",method = RequestMethod.POST)
	public List<Result> createTimetable(@RequestBody Input data) {
        new InputData();
        InputData.batch = data.batch;
        InputData.daysperweek = data.days;
        InputData.hoursperday = data.hours;
        InputData.teacher = data.teacher;
        InputData.nobatch = (data.batch).length;
        InputData.noteacher = (data.teacher).length;
        InputData.mutationrate = 0.1;
        InputData.crossoverrate = 1.0;		
        InputData.assignTeacher();
    
		Scheduler timetable = new Scheduler();
		return timetable.ans;
	}
	
}

