package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于标识某个方法需要进行功能字段自动填充
 */
@Target(ElementType.METHOD)//指定这个注解只能放在方法上面
@Retention(RetentionPolicy.RUNTIME)//保留至运行时
public @interface AutoFill {
    //指定数据库操作类型：UPDATE INSERT
    OperationType value();
}
