package com.example.springstudentservice.controller;

import com.example.springstudentservice.Dto.StudentDto;
import com.example.springstudentservice.entity.Student;
import com.example.springstudentservice.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Slf4j
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;
    @PostMapping("/save-student")
    private Student registerStudent(@Valid @RequestBody StudentDto studentDto){
        log.info("into the controller"+studentDto);
        Student student= studentService.registerStudent(studentDto);
        return student;
    }

    @GetMapping("/get")
    private Student getStudentById(@RequestParam(value = "stuId", required = true) int stuId){
        return studentService.getStudentById(stuId);
    }

    //ad-hoc search
    @GetMapping("/get-by-name")
    private Student getStudentByName(@RequestParam("stuName") String stuName){
        return studentService.getStudentByName(stuName);
    }

}
