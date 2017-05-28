package com.github.izhangzhihao.SSMSeedProject.Mapper;

import com.github.izhangzhihao.SSMSeedProject.Model.Teacher;

import java.util.List;

public interface TeacherMapper {
    List<Teacher> selectTeacherByStudentId(int id);
}
