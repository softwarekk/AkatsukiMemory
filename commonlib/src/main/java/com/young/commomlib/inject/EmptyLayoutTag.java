package com.young.commomlib.inject;

import android.support.annotation.IntDef;

import com.beile.commonlib.widget.EmptyLayout;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Des
 * Author Young
 * Date 2020-06-11
 */
@IntDef(value ={EmptyLayout.HIDE_LAYOUT,
        EmptyLayout.NETWORK_ERROR,
        EmptyLayout.NETWORK_LOADING,
        EmptyLayout.NO_LOGIN,
        EmptyLayout.NODATA_ENABLE_CLICK,
        EmptyLayout.NODATA})
@Target({ElementType.PARAMETER, ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.SOURCE)
public @interface EmptyLayoutTag {
}
