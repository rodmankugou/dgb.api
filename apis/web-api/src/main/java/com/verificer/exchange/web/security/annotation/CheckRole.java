package com.verificer.exchange.web.security.annotation;

import com.verificer.biz.beans.enums.CompanyRoleEnum;

import java.lang.annotation.*;
import java.util.List;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@NeedLogin
public @interface CheckRole {
    CompanyRoleEnum[] allowRoles() default {};
}
