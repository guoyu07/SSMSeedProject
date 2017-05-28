package com.github.izhangzhihao.SSMSeedProject.Controller;

import com.github.izhangzhihao.SSMSeedProject.Model.Student;
import com.github.izhangzhihao.SSMSeedProject.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/AllStudents")
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/Student/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable int id) {
        Student studentById = studentService.findStudentById(id);
        if (studentById != null) {
            return new ResponseEntity<>(studentById, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Student/name/{name}/email/{email}")
    public ResponseEntity<Void> createStudent(@PathVariable String name,
                                              @PathVariable String email)
            throws Exception {
        studentService.createStudent(new Student(name, email));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/Student/studId/{studId}/name/{name}/email/{email}")
    public ResponseEntity<Void> updateStudent(@PathVariable int studId,
                                              @PathVariable String name,
                                              @PathVariable String email)
            throws Exception {
        Student studentById = studentService.findStudentById(studId);
        if (studentById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            studentById.setName(name);
            studentById.setEmail(email);
            studentService.updateStudent(studentById);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/Student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id)
            throws Exception {
        Student studentById = studentService.findStudentById(id);
        if (studentById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            studentService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/StudentWithAddress/id/{id}")
    public ResponseEntity<Student> findStudentByIdWithAddress(@PathVariable int id) {
        Student studentByIdWithAddress = studentService.findStudentByIdWithAddress(id);
        if (studentByIdWithAddress == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(studentByIdWithAddress, HttpStatus.OK);
        }
    }

    @GetMapping("/StudentWithTeachers/id/{id}")
    public ResponseEntity<Student> selectStudentWithTeachers(@PathVariable int id) {
        Student selectStudentWithTeachers = studentService.selectStudentWithTeachers(id);
        if (selectStudentWithTeachers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(selectStudentWithTeachers, HttpStatus.OK);
        }
    }
}
