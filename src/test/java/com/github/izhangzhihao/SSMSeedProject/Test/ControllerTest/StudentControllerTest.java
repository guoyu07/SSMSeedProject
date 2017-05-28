package com.github.izhangzhihao.SSMSeedProject.Test.ControllerTest;


import com.github.izhangzhihao.SSMSeedProject.Controller.StudentController;
import com.github.izhangzhihao.SSMSeedProject.Test.TestUtils.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Random;

import static com.github.izhangzhihao.SSMSeedProject.Utils.StringUtils.getRandomUUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class StudentControllerTest extends BaseTest {

    @Autowired
    private StudentController studentController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(studentController)
                .build();
    }

    @Test
    public void getAllStudents() throws Exception {
        mockMvc.perform(get("/Student/AllStudents"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findExistStudentByIdTest() throws Exception {
        mockMvc.perform(get("/Student/Student/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findNotExistStudentByIdTest() throws Exception {
        mockMvc.perform(get("/Student/Student/" + new Random().nextInt()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void createStudentTest() throws Exception {
        mockMvc.perform(post("/Student/Student/name/" + getRandomUUID() + "/email/" + getRandomUUID()))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void updateExistStudentTest() throws Exception {
        mockMvc.perform(put("/Student/Student/studId/1/name/" + getRandomUUID() + "/email/" + getRandomUUID()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateNotExistStudentTest() throws Exception {
        mockMvc.perform(put("/Student/Student/studId/" + new Random().nextInt() + "/name/" + getRandomUUID() + "/email/" + getRandomUUID()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteExistStudentTest() throws Exception {
        mockMvc.perform(delete("/Student/Student/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNotExistStudentTest() throws Exception {
        mockMvc.perform(delete("/Student/Student/"+new Random().nextInt()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
