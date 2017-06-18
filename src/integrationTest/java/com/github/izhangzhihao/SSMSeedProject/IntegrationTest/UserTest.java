package com.github.izhangzhihao.SSMSeedProject.IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.izhangzhihao.SSMSeedProject.Application;
import com.github.izhangzhihao.SSMSeedProject.Model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static com.github.izhangzhihao.SSMSeedProject.IntegrationTest.Utils.admin;
import static com.github.izhangzhihao.SSMSeedProject.IntegrationTest.Utils.mockUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class UserTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private JacksonTester<User> json;

    @Before
    public void setUpMockMVC() {
        mockMvc = webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
        JacksonTester.initFields(this,new ObjectMapper());
    }

    @Test
    public void getUserTest() throws Exception {
        String contentAsString = mockMvc.perform(
                get("/User/userName/admin")
                        .with(admin)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        User parseResult = this.json.parse(contentAsString).getObject();

        assertThat(parseResult.getPassword()).isNull();

        parseResult.setPassWord(mockUser.getPassword());

        assertThat(parseResult).isEqualToComparingFieldByFieldRecursively(mockUser);
    }

    @Test
    public void getUserNoAccessTest() throws Exception {
        mockMvc.perform(
                get("/User/userName/admin")
        ).andExpect(status().isForbidden());
    }

    @Test
    public void NotFoundUserTest() throws Exception {
        mockMvc.perform(
                get("/User/userName/notExist")
                        .with(admin)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isNotFound());
    }
}
