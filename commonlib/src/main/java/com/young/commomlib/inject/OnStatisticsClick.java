package com.young.commomlib.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Des
 * Author
 * Date 2020-06-29
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)//java-->class-->runtime
public  @interface OnStatisticsClick {
    int[] viewId() default -1;
    String[] statics();//统计内容
    int [] type() default 0;//统计类型
    int [] sId() default 0;//统计数据
}
