package com.komal.students.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String course;
    private String country;

    public Student(String firstName, String lastName, String course, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.country = country;
    }
}
