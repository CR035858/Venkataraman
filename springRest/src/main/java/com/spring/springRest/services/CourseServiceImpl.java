package com.spring.springRest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.springRest.entities.Course;

@Service
public class CourseServiceImpl implements CourseService {
	
	List<Course> list;
	public CourseServiceImpl() {
		list =new ArrayList<>();
		list.add(new Course(145,"Java Core ","this course contains the java develpoment and core modules"));
		
		list.add(new Course(129,"python","this course i made for python learners and also"));
		
	}
	
	@Override
	public List<Course> getCourses(){
		return list;
	}
}
