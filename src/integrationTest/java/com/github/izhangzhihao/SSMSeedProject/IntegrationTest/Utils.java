package com.github.izhangzhihao.SSMSeedProject.IntegrationTest;

import com.github.izhangzhihao.SSMSeedProject.Model.Address;
import com.github.izhangzhihao.SSMSeedProject.Model.Role;
import com.github.izhangzhihao.SSMSeedProject.Model.User;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import static java.util.Arrays.asList;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

public class Utils {
    public static final User mockUser = new User("admin", "9cf3e758a497c6274bd066d0b2168432f8a34aad95f63a65677a9a56acec94a7",
            asList(new Role("ROLE_ADMIN"), new Role("ROLE_USER")),
            false,
            false,
            false,
            new Address(1, "street", "city", "state", "zip", "country")
    );

    public static final RequestPostProcessor admin = user("admin").roles("ADMIN");
}
