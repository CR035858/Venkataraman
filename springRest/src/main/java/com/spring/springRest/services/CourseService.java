package com.spring.springRest.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.springRest.entities.Course;

@Service
public interface CourseService {
	public List<Course> getCourses();	 
}
