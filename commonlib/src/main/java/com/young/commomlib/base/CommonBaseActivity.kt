package com.young.commomlib.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.young.baselib.CustomProjectLiveData
import com.young.baselib.activity.BaseActivity
import com.young.baselib.viewmodel.BaseUIViewMode
import com.young.commomlib.utils.BarUtils
import com.young.commomlib.utils.permission.DealWithPermissions.*
import com.young.commomlib.utils.permission.PermissionPolicy
import com.young.commomlib.viewmodel.CommonShareVM

/*
 * Des 业务级别
 * Author Young
 * Date 2020-05-18
 */
abstract class CommonBaseActivity<V : ViewDataBinding, VM : BaseUIViewMode> : BaseActivity<V,VM> (){
    protected  var mCommonModel: CommonShareVM? = null //app 分享数据模块
    val LOG_TAG=this.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 给工具类初始化
        BarUtils.setStatusBarVisibility(this,false)
        mCommonModel= getAppViewModelProvider()!!.get(CommonShareVM::class.java)
    }
    override fun onDestroy() {
        super.onDestroy()
    }


    var permissionTag= CustomProjectLiveData<Int>()//根据permissionresult 触发permisssionRun
    /*
    * 权限运行 拥有权限复归逻辑 否则请求权限
    * 权限请求成功后响应试触发 mt 后面逻辑
    * */
    inline fun  <T : AppCompatActivity> T.permissionRun(policy: PermissionPolicy, crossinline mt: () -> Unit) {
        var a = arrayOf<String>()//具体权限
        var code=STORAGE_REQUEST_CODE
        when(policy){
            PermissionPolicy.P_STO->{
                a= STORAGE
            }
            PermissionPolicy.P_MIC->{
                a= MICROPHONE
                code= MICROPHONE_REQUEST_CODE
            }
            PermissionPolicy.P_CAM->{
                a= CAMERAS
                code= CAMERAS_REQUEST_CODE
            }
        }
        if (getInstance(this).checkPermision(a)) mt()
        else {
            getInstance(this).requestSpecificPermissions(a)
            permissionTag.observe(this, Observer<Int> {
                if(code==it){
                    mt()
                }
            })
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if ((grantResults.size > 0) && (grantResults.get(0) == android.content.pm.PackageManager.PERMISSION_GRANTED))
        {
            permissionTag.value=requestCode
        }
    }
}