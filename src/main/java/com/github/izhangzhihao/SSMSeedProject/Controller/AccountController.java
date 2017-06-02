package com.github.izhangzhihao.SSMSeedProject.Controller;

import com.github.izhangzhihao.SSMSeedProject.Utils.ValidateCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/Account")
public class AccountController {
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
     * 判断验证码有没有输对
     *
     * @param code
     * @param session
     * @return
     */
    @PostMapping("/assertValidateCode/{code}")
    public ResponseEntity<Void> assertValidateCode(@PathVariable String code, HttpSession session) {
        String rightCode = (String) session.getAttribute("validateCode");
        final boolean result = code != null && !StringUtils.isEmpty(code) && code.toLowerCase().equals(rightCode.toLowerCase());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * 退出登录
     *
     * @return
     */
    @GetMapping("/LogOut")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
