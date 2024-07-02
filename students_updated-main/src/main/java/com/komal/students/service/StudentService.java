package com.komal.students.service;

import com.komal.students.dao.Student;
import com.komal.students.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    public List<Student> getAllData() {
        return studentRepo.findAll();
    }

    public Optional<Student> getById(Long id) {
        return studentRepo.findById(id);
    }

    public Student saveData(Student student) {
        return studentRepo.saveAndFlush(student);
    }

    public String updateData(Student student, Long id) {
        String result = "";
        if(studentRepo.existsById(id)) {
            studentRepo.saveAndFlush(student);
            result = "Student saved successfully";
        } else {
            result = "Student id not found";
        }
        return result;
    }

    public String deleteById(Long id){
        studentRepo.deleteById(id);
        return "Student deleted Success";
    }
}
