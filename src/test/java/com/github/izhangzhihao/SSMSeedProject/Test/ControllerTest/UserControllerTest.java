package com.github.izhangzhihao.SSMSeedProject.Test.ControllerTest;

import com.github.izhangzhihao.SSMSeedProject.Controller.UserController;
import com.github.izhangzhihao.SSMSeedProject.Model.Address;
import com.github.izhangzhihao.SSMSeedProject.Model.Role;
import com.github.izhangzhihao.SSMSeedProject.Model.User;
import com.github.izhangzhihao.SSMSeedProject.Service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    @Test
    public void getUser() {
        User mockUser = new User("admin", "9cf3e758a497c6274bd066d0b2168432f8a34aad95f63a65677a9a56acec94a7",
                asList(new Role("ROLE_ADMIN"), new Role("ROLE_ADMIN")),
                true,
                true,
                true,
                new Address(1, "street", "city", "state", "zip", "country")
        );
        given(this.userService.selectByPrimaryKey("admin")).willReturn(Optional.of(mockUser));
        User result = userController.getUser("admin");
        assertThat(mockUser,is(result));
    }
}
