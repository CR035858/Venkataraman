package com.spring.springRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springRest.entities.Course;
import com.spring.springRest.services.CourseService;


@RestController

public class controller {
	
	@Autowired
	public CourseService courseS;
	
	@GetMapping("/home")
	public String home() {
		
		return "welcome to this course";
	}
	
	
	@GetMapping("/course")
	public List<Course> getCourses() {
		return this.courseS.getCourses();		
	}
}
