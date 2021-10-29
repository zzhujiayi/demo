package com.example.demo.spring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author Zhu Jiayi
 * @Date 2021/9/14 16:31
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MyImportSelector.class)
public @interface EnableMyImport {
}
