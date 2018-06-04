package com.spring.ioc.service;

import com.spring.ioc.annotation.Componet;

/**
 * Created by wuxiaowei on 2018/6/4
 */
@Componet( value = "service")
public class ServiceImpl implements Service {

    public void aa() {
        System.out.println("你好");
    }
}
