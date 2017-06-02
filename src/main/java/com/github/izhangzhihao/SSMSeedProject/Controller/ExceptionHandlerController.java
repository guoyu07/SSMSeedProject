package com.github.izhangzhihao.SSMSeedProject.Controller;

import com.github.izhangzhihao.SSMSeedProject.Exception.OptionalNotPresentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 张志豪 on 2016/11/28 0028.
 * 处理异常
 */
@ControllerAdvice
public class ExceptionHandlerController {

    private final Environment env;

    @Autowired
    public ExceptionHandlerController(Environment env) {
        this.env = env;
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String accessDenied() {
        return "403";
    }

    @ExceptionHandler({OptionalNotPresentException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handlerUnauthenticatedException(Exception ex, NativeWebRequest request) {
        if ((request.getHeader("accept").contains("application/json"))) {
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            Map<String, Serializable> attributes = new HashMap<>();
            attributes.put("error", HttpStatus.NOT_FOUND);
            view.setAttributesMap(attributes);
            return new ModelAndView(view);
        } else {
            return new ModelAndView("/404");
        }
    }


    /**
     * 全局Controller异常处理
     *
     * @param ex 异常
     * @return 跳转出错页面
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handlerExceptionMethod(Exception ex, NativeWebRequest request) {
        if ((request.getHeader("accept").contains("application/json"))) {//如果不是异步请求
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            Map<String, java.io.Serializable> attributes = new HashMap<>();
            attributes.put("error", HttpStatus.INTERNAL_SERVER_ERROR);
            if (env.getDefaultProfiles()[0].equals("development")) {
                attributes.put("MSG", ex.toString());
                attributes.put("Line", ex.getStackTrace()[0].getLineNumber());
                attributes.put("Method", ex.getStackTrace()[0].getMethodName());
            }
            view.setAttributesMap(attributes);
            return new ModelAndView(view);
        } else {
            if (env.getDefaultProfiles()[0].equals("development")) {
                ModelAndView modelAndView = new ModelAndView("500");
                modelAndView.addObject("MSG", ex.toString());
                modelAndView.addObject("Line", ex.getStackTrace()[0].getLineNumber());
                modelAndView.addObject("Method", ex.getStackTrace()[0].getMethodName());
                Writer writer = new StringWriter();
                //异常输出到浏览器console
                ex.printStackTrace(new PrintWriter(writer));
                modelAndView.addObject("detailed", writer.toString());
                return modelAndView;
            } else {
                return new ModelAndView("/500");
            }
        }
    }
}
