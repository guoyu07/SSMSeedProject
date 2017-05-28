package com.github.izhangzhihao.SSMSeedProject.Security;

import com.github.izhangzhihao.SSMSeedProject.Model.User;
import com.github.izhangzhihao.SSMSeedProject.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;


@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Locates the user based on the UserName. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a UserName that is of a different case than what
     * was actually requested..
     *
     * @param UserName the UserName identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(final @NotNull String UserName) throws UsernameNotFoundException {
        Assert.hasText(UserName,"UserName can not be empty!");
        Optional<User> user = userService.selectByPrimaryKey(UserName);

        User loginUser = user.orElseThrow(() -> new UsernameNotFoundException("userName:" + UserName + "not found"));

        return new org.springframework.security.core.userdetails.User(UserName.toLowerCase(), loginUser.getPassword(), loginUser.getAuthorities());
    }
}
