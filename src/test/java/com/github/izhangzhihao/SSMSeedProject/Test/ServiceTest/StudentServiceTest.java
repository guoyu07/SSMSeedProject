//package com.github.izhangzhihao.SSMSeedProject.Test.ServiceTest;
//
//import com.github.izhangzhihao.SSMSeedProject.Model.Student;
//import com.github.izhangzhihao.SSMSeedProject.Service.StudentService;
//import com.github.izhangzhihao.SSMSeedProject.Test.TestUtils.BaseTest;
//import org.junit.Assert;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Date;
//import java.util.List;
//
//
//public class StudentServiceTest extends BaseTest {
//    @Autowired
//    private static StudentService studentService;
//
//    @Test
//    public void testFindAllStudents() {
//        List<Student> students = studentService.findAllStudents();
//        Assert.assertNotNull(students);
//        students.forEach(System.out::println);
//    }
//
//    @Test
//    public void testFindStudentById() {
//        Student student = studentService.findStudentById(1);
//        Assert.assertNotNull(student);
//        System.out.println(student);
//    }
//
//    @Test
//    public void testCreateStudent() {
//        Student student = new Student();
//        int id = 3;
//        student.setStudId(id);
//        student.setName("student_" + id);
//        student.setEmail("student_" + id + "gmail.com");
//        student.setDob(new Date());
//        studentService.createStudent(student);
//        Student newStudent = studentService.findStudentById(id);
//        Assert.assertNotNull(newStudent);
//    }
//}
