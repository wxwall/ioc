package com.spring.ioc;

import com.spring.ioc.annotation.AutoWried;
import com.spring.ioc.annotation.Componet;
import com.spring.ioc.service.IOCServer;
import com.spring.ioc.service.Service;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuxiaowei on 2018/6/4
 */
public class Bootstrap {

    @AutoWried
    Service service;

    @AutoWried
    IOCServer iocServer;



    public Map<String,Object> ioc = new HashMap<String,Object>();

    public List<String> beanName = new ArrayList<String>();

    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();
        String directory = "com.spring.ioc.service";
        bootstrap.init(directory);
        bootstrap.inject();

    }


    private void inject() {
        if(beanName == null){
            return;
        }
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if(field.getType().isInterface()){
                if(field.isAnnotationPresent(AutoWried.class)){
                    field.setAccessible(true);
                    try {
                        // 第一个参数是实例：是取field的所在实例
                        // 第二个参数也是实例：取field需要注入的实例
                        field.set(this,ioc.get(field.getName()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

            }

        }

        this.service.aa();
        this.iocServer.bb();
    }

    private void init(String directory) {
        URL url = this.getClass().getClassLoader().getResource(directory.replaceAll("\\.", "\\/"));
        File file = new File(url.getFile());
        File[] files = file.listFiles();
        for (File fileList: files) {
            if(fileList.isDirectory()){
                //init(fileList.toString());
            }
            if(fileList.getName().endsWith(".class")){
                String clazzName = directory + "." + fileList.getName().replaceAll(".class","");
                Object instance = null;

                //todo 判断是否是接口，接口不能实现化
                try {
                    if(Class.forName(clazzName).isInterface()){
                        continue;
                    }
                    instance = Class.forName(clazzName).newInstance();
                    Componet componet = instance.getClass().getAnnotation(Componet.class);
                    String name = componet.value();
                    ioc.put(name,instance);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }
    }



}
