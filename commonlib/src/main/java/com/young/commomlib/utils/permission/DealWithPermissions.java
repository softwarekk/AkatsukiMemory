package com.young.commomlib.utils.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;


/**
 * Created by Young on 2018/10/30.
 */

public class DealWithPermissions {
    //8.0 组权限
    public static final String[] PHONE = new String[] {//电话相关
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE
    };
    public static final  String[] SMS = new String[] {//短信相关
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.RECEIVE_MMS
    };
    public static String[] STORAGE = new String[] {//存储
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static final String[] SENSORS = new String[] {//传感器
            Manifest.permission.BODY_SENSORS
    };
    public static final String[] MICROPHONE = new String[] {//麦克分
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static String[] LOCATION = new String[] {//定位
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    public static final String[] CONTACTS = new String[] {//联系人
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS
    };
    public static final String[] CAMERAS = new String[] {//相机
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public static final String[] CALENDARS = new String[] {//联系人
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR
    };

    // dangero
    // us permission  空行为一组全限 组权限只申请一条就可以

    public static  final  String READ_EXTERNAL_STORAGE= Manifest.permission.READ_EXTERNAL_STORAGE;//读写权限
    public static  final  String WRITE_EXTERNAL_STORAGE= Manifest.permission.WRITE_EXTERNAL_STORAGE;

    public static  final  String CALL_PHONE= Manifest.permission.CALL_PHONE;//电话相关

    public static  final  String CAMERA= Manifest.permission.CAMERA;//相机

    public static  final  String ACCESS_FINE_LOCATION= Manifest.permission.ACCESS_FINE_LOCATION;//定位

    public static  final  String RECORD_AUDIO= Manifest.permission.RECORD_AUDIO;//麦克风

    public static  final  String READ_CALENDAR= Manifest.permission.READ_CALENDAR;//日历相关

    public static  final  String BODY_SENSORS= Manifest.permission.BODY_SENSORS;//传感器

    public static final int STORAGE_REQUEST_CODE=1;//存储请求值
    public static final int MICROPHONE_REQUEST_CODE=3;//麦克分请求值
    public static final int CAMERAS_REQUEST_CODE=2;//录音请求值
    public static final int REQUEST_CODE_DEFAULT=100;//录音请求值

    // dangerous permission  空行 为一组全限 组权限只申请一条就可以
    private static  DealWithPermissions instance;
    private static Activity mActivity;
    private int targetSdkVersion;
    private PackageInfo info;
    public static DealWithPermissions getInstance(Activity aInstance){
        mActivity =aInstance;
        if (instance == null) {
            instance = new DealWithPermissions();
        }
        return instance;
    }
    public DealWithPermissions() {
        try {
            info = mActivity.getPackageManager().getPackageInfo(
                    mActivity.getPackageName(), 0);
            targetSdkVersion = info.applicationInfo.targetSdkVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void requestSpecificPermissions(String [] PERMISSIONS){//请求部分需求得权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M||mActivity==null) {
            return ;
        }
        int requestCode=REQUEST_CODE_DEFAULT;
        if(PERMISSIONS==STORAGE){
            requestCode=STORAGE_REQUEST_CODE;
        }else if(PERMISSIONS==CAMERAS){
            requestCode=CAMERAS_REQUEST_CODE;
        }else if(PERMISSIONS==MICROPHONE){
            requestCode=MICROPHONE_REQUEST_CODE;
        }
        startDealWith(PERMISSIONS,requestCode);
    }
    /**
     * @param PERMISSIONS
     *  @param requestCode 请求值用作回掉处理
     */
    private void startDealWith(String [] PERMISSIONS,int requestCode) {
        for(String rPermission:PERMISSIONS){
            Boolean isGranted=true;//允许
            if (targetSdkVersion >= Build.VERSION_CODES.M) {//解决targetsdk<23 权限判断一直是true
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    isGranted = mActivity.checkSelfPermission(rPermission)
                            == PackageManager.PERMISSION_GRANTED;
                }
            } else {
                isGranted = PermissionChecker.checkSelfPermission(mActivity, rPermission)
                        == PermissionChecker.PERMISSION_GRANTED;
            }
            if(!isGranted){
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        mActivity,
                        PERMISSIONS,
                        requestCode
                );
                mActivity=null;
                return;
            }
        }
        mActivity=null;
        return;
    }
    public Boolean checkPermision(String manifestName){
        Boolean isGranted=false;
        if (targetSdkVersion >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                isGranted = mActivity.checkSelfPermission(manifestName)
                        == PackageManager.PERMISSION_GRANTED;
            }
        } else {//解决targetsdk<23 权限判断一直是true
            isGranted = PermissionChecker.checkSelfPermission(mActivity, manifestName)
                    == PermissionChecker.PERMISSION_GRANTED;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        mActivity=null;
        return isGranted;
    }
    public Boolean checkPermision(String[] manifestNames){
        Boolean isGranted=false;
        if (targetSdkVersion >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for(String manifestName:manifestNames ) {
                    isGranted = mActivity.checkSelfPermission(manifestName)== PackageManager.PERMISSION_GRANTED;
                    if(!isGranted){
                        break;
                    }
                }
            }
        } else {//解决targetsdk<23 权限判断一直是true
            for(String manifestName:manifestNames ) {
                isGranted = PermissionChecker.checkSelfPermission(mActivity, manifestName)
                        == PermissionChecker.PERMISSION_GRANTED;
                if(!isGranted){
                    break;
                }
            }
        }
        mActivity=null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        return isGranted;
    }

}
