//package com.github.izhangzhihao.SSMSeedProject.security;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class EntryPoint implements AuthenticationEntryPoint {
//    // The class from Step 1
//    private MessageProcessor processor;
//
//    public CustomEntryPoint() {
//        // It is up to you to decide when to instantiate
//        processor = new MessageProcessor();
//    }
//
//    @Override
//    public void commence(HttpServletRequest request,
//                         HttpServletResponse response, AuthenticationException authException)
//            throws IOException, ServletException {
//
//        // This object is just like the model class,
//        // the processor will convert it to appropriate format in response body
//        CustomExceptionObject returnValue = new CustomExceptionObject();
//        try {
//            processor.handle(returnValue, request, response);
//        } catch (Exception e) {
//            throw new ServletException();
//        }
//    }
//}