package com.github.izhangzhihao.SSMSeedProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("Teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private int id;
    private String name;
    private String passWord;

    public Teacher(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }

}
