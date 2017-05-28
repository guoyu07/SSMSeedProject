package com.github.izhangzhihao.SSMSeedProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/404")
    public String notFound() {
        return "404";
    }
}
