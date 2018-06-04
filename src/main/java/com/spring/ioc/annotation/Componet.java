package com.spring.ioc.annotation;

import java.lang.annotation.*;

/**
 * Created by wuxiaowei on 2018/6/4
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Componet {

    String value() default "";
}
