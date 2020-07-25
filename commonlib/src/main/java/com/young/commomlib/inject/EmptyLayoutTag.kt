package com.young.commomlib.inject

import androidx.annotation.IntDef
import com.young.commomlib.Constants
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/*
 * Des
 * Author Young
 * Date 2020-06-11
 */
@IntDef(value = [Constants.PAGE_HIDE, Constants.PAGE_LOADING, Constants.PAGE_NET_ERROR,Constants.PAGE_NO_DATA])
@Target(
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.TYPE_PARAMETER
)
@Retention(RetentionPolicy.SOURCE)
annotation class EmptyLayoutTag