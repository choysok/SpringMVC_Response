package com.page.controller;

import com.page.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionInterceptor {

    /*
       异常拦截器
   */
    @RequestMapping("/testExceptionInterceptor")
    public String  testExceptionInterceptor() throws Exception {
        System.out.println("执行了 testExceptionInterceptor请求！");
        //模拟异常
        try {
            int a =10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            //抛出自定义异常信息
            throw new SysException("查询所有用户出错了");
        }
        return "success";
    }



}
