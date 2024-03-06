package com.verificer.exchange.web.security.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@NeedLogin
public @interface AllowUnActivation {
}
