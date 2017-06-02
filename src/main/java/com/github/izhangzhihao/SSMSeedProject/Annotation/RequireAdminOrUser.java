package com.github.izhangzhihao.SSMSeedProject.Annotation;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(
        {
                ElementType.TYPE, ElementType.METHOD
        })
@Component
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@PreAuthorize("hasAnyRole({'USER','ADMIN'})")
public @interface RequireAdminOrUser {
}
