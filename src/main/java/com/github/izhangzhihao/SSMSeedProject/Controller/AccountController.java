package com.github.izhangzhihao.SSMSeedProject.Controller;


import com.github.izhangzhihao.SSMSeedProject.Model.User;
import com.github.izhangzhihao.SSMSeedProject.Service.UserService;
import com.github.izhangzhihao.SSMSeedProject.Utils.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;


@SuppressWarnings("JavaDoc")
@Controller
@RequestMapping("/Account")
public class AccountController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 转向登录界面
     *
     * @return 登录界面
     */
    @GetMapping("/Login")
    public String loginPage() {
        return "Account/Login";
    }

    /**
     * 接收用户登录传参，判断是否登陆成功
     *
     * @param UserName
     * @param Password
     * @param validateCode
     * @param RememberMe
     * @param session
     * @return
     */
    @PostMapping("/Login")
    public String login(@RequestParam("UserName") String UserName,
                        @RequestParam("Password") String Password,
                        @RequestParam("validateCode") String validateCode,
                        @RequestParam(value = "RememberMe", required = false) String RememberMe,
                        @PathParam("error") boolean isError,
                        HttpSession session) {
        /*String code = (String) session.getAttribute("validateCode");
        if (code == null || StringUtils.isEmpty(validateCode) || !StringUtils.equals(code.toLowerCase(), validateCode.toLowerCase())) {
            //登陆失败清除session的验证码，以防暴力破解
            session.removeAttribute("validateCode");
            return "redirect:/Account/Login";
        }

        session.removeAttribute("validateCode");
        return "index";

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            return "redirect:/Account/Login";
        } else {
            //获取用户登录权限详细
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetail = ((UserDetails) principal);
                //model.addAttribute("username", userDetail.getUsername());
                User u = userService.selectByPrimaryKey(userDetail.getUsername()).orElseThrow(OptionalNotPresentException::new);
                //model.addAttribute("realName", u.getRealname());
            }

            //登录成功跳到主页
            return "/";
        }*/

        Optional<User> user = userService.selectByPrimaryKey(UserName);
        User loginUser = user.orElseThrow(() -> new UsernameNotFoundException("userName" + UserName + "not found"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("image/jpeg");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_ALL_MIXED, 4, null);
        request.getSession().setAttribute("validateCode", verifyCode);
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }

    /**
     * 退出登录
     *
     * @return
     */
    @GetMapping("/LogOut")
    public String logOut() {
        return "redirect:/Account/Login";
    }
}
