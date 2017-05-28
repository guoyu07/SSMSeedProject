package com.github.izhangzhihao.SSMSeedProject.Service;

import com.github.izhangzhihao.SSMSeedProject.Mapper.StudentMapper;
import com.github.izhangzhihao.SSMSeedProject.Model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> findAllStudents() {
        return studentMapper.findAllStudents();
    }

    public Student findStudentById(int studId) {
        return studentMapper.findStudentById(studId);
    }

    @Transactional
    public void createStudent(Student student) {
        studentMapper.insertStudent(student);
    }

    @Transactional
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Transactional
    public void deleteStudent(int studId) {
        studentMapper.deleteStudent(studId);
    }

    public Student findStudentByIdWithAddress(int id) {
        return studentMapper.findStudentByIdWithAddress(id);
    }

    public Student selectStudentWithTeachers(int id) {
        return studentMapper.selectStudentWithTeachers(id);
    }
}