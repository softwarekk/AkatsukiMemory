package com.young.commomlib.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Des 以用户或者校区分发策略
 * Author
 * Date 2020-06-15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public  @interface UserSchool {
    Userpolicy value();
}
