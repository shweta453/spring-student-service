package com.example.springstudentservice.service;

import com.example.springstudentservice.Dto.StudentDto;
import com.example.springstudentservice.entity.Student;
import com.example.springstudentservice.model.NotificationParameter;
import com.example.springstudentservice.model.SendNotificationRequest;
import com.example.springstudentservice.model.SendNotificationResponse;
import com.example.springstudentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RestTemplate restTemplate;

    public Student registerStudent(StudentDto studentDto){
        //SendNotificationResponse sendNotificationResponse = null;
        Student student= Student.builder()
                .stuName(studentDto.getStuName())
                .stuGender(studentDto.getStuGender())
                .emailId(studentDto.getEmailId())
                .build();
        student=studentRepository.save(student);
        System.out.println(student);
        //sendNotificationResponse = sendMailNotification(student, MailConstant.REGISTER_STUDENT_TEMPLATE_CODE);
        return student;
    }

    private SendNotificationResponse sendMailNotification(Student student, String templateCode) {
        List<NotificationParameter> notificationParameters = getNotificationParameters(student); //to convert userProfile into key value pair // to set values to keys
        SendNotificationRequest sendNotificationRequest = SendNotificationRequest.builder()
                .templateCode(templateCode)
                .parameters(notificationParameters)
                .build();
        //will go taking list of Notification Parameter containing key-value pair to common mail service through rest template
        String url = "http://localhost:8080/api/v1/send-notification";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SendNotificationRequest> entity = new HttpEntity<>(sendNotificationRequest, headers);

        SendNotificationResponse sendNotificationResponse = restTemplate.postForObject(url, entity, SendNotificationResponse.class);

        return sendNotificationResponse;
    }

    private List<NotificationParameter> getNotificationParameters(Student student) {
        List<NotificationParameter> notificationParameters = new ArrayList<NotificationParameter>();

        NotificationParameter notificationParameter = NotificationParameter.builder()
                .key("STUDENT_NAME")
                .value(student.getStuName()).build();
        notificationParameters.add(notificationParameter);
        notificationParameter = notificationParameter.toBuilder()
                .key("EMAIL")
                .value(student.getEmailId()).build();
        notificationParameters.add(notificationParameter);
        return notificationParameters;
    }

    public Student getStudentById(int stuId) {
        Student student=null;
       Optional<Student> studentOptional=studentRepository.findById(stuId);
       if(studentOptional.isPresent()) {
          student = studentOptional.get();
       }
       return student;
    }

    public Student getStudentByName(String stuName){
        Student student=studentRepository.findByStuNameContaining(stuName);
        return student;
    }
}
