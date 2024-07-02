package com.komal.students.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class Courses {
    public List<String> getCourses(){
        return Arrays.asList("BA", "B Com", "B Tech", "Bsc", "Mca", "Mba", "M Tech", "Ms");
    }
 }
