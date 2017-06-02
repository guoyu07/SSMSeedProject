package com.github.izhangzhihao.SSMSeedProject.Test.Controller;

import com.github.izhangzhihao.SSMSeedProject.Controller.UserController;
import com.github.izhangzhihao.SSMSeedProject.Model.User;
import com.github.izhangzhihao.SSMSeedProject.Service.UserService;
import com.github.izhangzhihao.SSMSeedProject.Test.Service.UserServiceTest;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.json.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.json.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.*;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.github.izhangzhihao.SSMSeedProject.Test.Utils.mockUser;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@JsonTest
public class UserControllerTest {

    @Autowired
    private JacksonTester<User> jacksonTester;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setUpMockMVC(){
        mockMvc = standaloneSetup(userController).build();
    }


    @Test
    public void getUser() throws Exception {
        when(userService.selectByPrimaryKey("admin"))
                .thenReturn(Optional.of(mockUser));

        String contentAsString = mockMvc.perform(
                get("/User/userName/admin")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        User parseResult = this.jacksonTester.parse(contentAsString).getObject();

        assertThat(parseResult.getPassword()).isNull();

        parseResult.setPassWord(mockUser.getPassword());

        assertThat(parseResult).isEqualToComparingFieldByFieldRecursively(mockUser);
    }

}
