package com.github.izhangzhihao.SSMSeedProject.Controller;

import com.github.izhangzhihao.SSMSeedProject.Annotation.RequireAdmin;
import com.github.izhangzhihao.SSMSeedProject.Exception.OptionalNotPresentException;
import com.github.izhangzhihao.SSMSeedProject.Model.User;
import com.github.izhangzhihao.SSMSeedProject.Service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequireAdmin
    @GetMapping("/UserList")
    public List<User> getAllUsers() {
        return userService.selectAllUser().orElseThrow(OptionalNotPresentException::new);
    }

    @RequireAdmin
    @GetMapping("/userName/{userName}")
    public User getUser(@PathVariable String userName) {
        return userService.selectByPrimaryKey(userName).orElseThrow(OptionalNotPresentException::new);
    }

    @RequireAdmin
    @GetMapping("/UserPageInfo/pageNumber/{pageNumber}/pageSize/{pageSize}")
    public PageInfo<User> UserPageInfo(@PathVariable int pageNumber,
                                       @PathVariable int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<User> userList = userService.selectAllUser().orElseThrow(OptionalNotPresentException::new);
        return new PageInfo<>(userList);
    }
}
