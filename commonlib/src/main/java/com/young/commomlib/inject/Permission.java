package com.young.commomlib.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Des permision
 * Author Young
 * Date 2020-06-11
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
        PermissionPolicy value();//权限名
        String tms();//命中的代理方法名
}

