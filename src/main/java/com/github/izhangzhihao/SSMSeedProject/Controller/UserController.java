package com.github.izhangzhihao.SSMSeedProject.Controller;

import com.github.izhangzhihao.SSMSeedProject.Exception.OptionalNotPresentException;
import com.github.izhangzhihao.SSMSeedProject.Model.User;
import com.github.izhangzhihao.SSMSeedProject.Service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/UserList")
    public List<User> getAllUsers() {
        return userService.selectAllUser().orElseThrow(OptionalNotPresentException::new);
    }

    @GetMapping("/userName/{userName}")
    public User getUser(@PathVariable String userName) {
        return userService.selectByPrimaryKey(userName).orElseThrow(OptionalNotPresentException::new);
    }

    @GetMapping("/UserPageInfo/pageNumber/{pageNumber}/pageSize/{pageSize}")
    public PageInfo<User> UserPageInfo(@PathVariable int pageNumber,
                                       @PathVariable int pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<User> userList = userService.selectAllUser().orElseThrow(OptionalNotPresentException::new);
        return new PageInfo<>(userList);
    }
}
