package com.jadyer.seed.comm.exception;

import com.jadyer.seed.comm.constant.CodeEnum;
import com.jadyer.seed.comm.constant.CommonResult;
import com.jadyer.seed.comm.util.LogUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常控制器
 * ----------------------------------------------------------------------------------------------------------------------
 * ControllerAdvice是Spring3.2提供的新注解，该注解使用了@Component注解，所以使用<context:component-scan>就能扫描到
 * ControllerAdvice可以把使用了@ExceptionHandler/@InitBinder/@ModelAttribute注解的方法应用到所有@RequestMapping注解的方法
 * 最常用的就是通过@ExceptionHandler进行全局异常的统一捕获和控制
 * ----------------------------------------------------------------------------------------------------------------------
 * ControllerAdvice注解的作用域是全局Controller
 * ----------------------------------------------------------------------------------------------------------------------
 * Created by 玄玉<http://jadyer.cn/> on 2015/6/6 12:31.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 1、这里会捕获Throwable及其所有子异常
     * 2、欲返回JSON则需使用@ResponseBody，否则会去找JSP页面（即它不会受到被捕获的方法是否使用了@ResponseBody的影响）
     */
    @ResponseBody
    @ExceptionHandler({SeedException.class, Throwable.class})
    public CommonResult process(Throwable cause, HttpServletRequest request){
        LogUtil.getLogger().info("Exception Occured URL="+request.getRequestURL()+",堆栈轨迹如下", cause);
        CommonResult result = new CommonResult();
        if(cause instanceof SeedException){
            result.setCode(((SeedException)cause).getCode());
        }else{
            result.setCode(CodeEnum.SYSTEM_ERROR.getCode());
        }
        //result.setMsg(cause.getMessage().contains("%s") ? cause.getMessage().replaceAll("%s", "") : cause.getMessage());
        result.setMsg(cause.getMessage());
        return result;
    }
}