package com.github.izhangzhihao.SSMSeedProject.Mapper;

import com.github.izhangzhihao.SSMSeedProject.Model.Student;

import java.util.List;


public interface StudentMapper {

    List<Student> findAllStudents();

    Student findStudentById(int id);

    void insertStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(int id);

    Student findStudentByIdWithAddress(int id);

    Student selectStudentWithTeachers(int id);
}
