package com.github.izhangzhihao.SSMSeedProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

@Alias("Student")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private Integer studId;
    private String name;
    private String email;
    private Date dob;
    private Address address;
    private List<Teacher> teacherList;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
}