package com.spring.ioc.service;

import com.spring.ioc.annotation.Componet;

/**
 * Created by wuxiaowei on 2018/6/4
 */
@Componet(value = "iocServer")
public class IOCServerImpl implements IOCServer {
    public void bb() {
        System.out.println("中国");
    }
}
