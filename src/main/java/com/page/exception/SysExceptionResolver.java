package com.page.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
       处理器异常解决器
 */
public class SysExceptionResolver  implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
       SysException sysException = null;
       if(e instanceof SysException){
           sysException = (SysException) e;
       }else{
           e = new SysException("系统正在维护.........");
       }
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.addObject("errorMsg",sysException.getMessage());
       modelAndView.setViewName("error");
    return modelAndView;
    }
}
