package com.github.izhangzhihao.SSMSeedProject.Test.Service;

import com.github.izhangzhihao.SSMSeedProject.Mapper.UserMapper;
import com.github.izhangzhihao.SSMSeedProject.Model.User;
import com.github.izhangzhihao.SSMSeedProject.Service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.github.izhangzhihao.SSMSeedProject.Test.Utils.mockUser;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void selectByPrimaryKey() {
        given(this.userMapper.selectByPrimaryKey("admin"))
                .willReturn(mockUser);
        Optional<User> optionalResult = userService.selectByPrimaryKey("admin");
        assertThat(mockUser, is(optionalResult.get()));
    }
}
