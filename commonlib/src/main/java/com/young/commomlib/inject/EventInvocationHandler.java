package com.young.commomlib.inject;

import android.app.Activity;
import android.view.View;

import com.beile.basemoudle.DealWithPermissions;
import com.beile.basemoudle.utils.TLog;
import com.beile.commonlib.StatisticsConstants;
import com.beile.commonlib.base.CommonBaseApplication;
import com.beile.commonlib.util.BusinessUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/*
 * Des
 * Author
 * Date 2020-06-22
 */   public class EventInvocationHandler implements InvocationHandler {
    //需要在onClick中执行activity.click();
    private Object obj;
    private Method activityMethod;
    private InjectPolicy policy;//代理策略
    private Object params;//代理参数
    private HashMap<Integer, String> statisticsMap; //view id  和 对应的描述
    private List<String> statisticsLists;//统计的id 和type

    /**
     * @param context
     * @param activityMethod
     * @param pol
     * @param map //view id  和 对应的描述
     * @param statisticsConstants  //统计的id 和type
     */
    public EventInvocationHandler(Object context, Method activityMethod, InjectPolicy pol, HashMap<Integer, String> map, List<String> statisticsConstants) {
        this.policy=pol;
        this.statisticsMap=map;
        this.obj = context;
        this.activityMethod = activityMethod;
        this.statisticsLists=statisticsConstants;
    }

    public EventInvocationHandler(Object context, Method targetMet, InjectPolicy pol, Object param) {
        this.obj = context;
        this.activityMethod = targetMet;
        this.policy=pol;
        this.params=param;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)  {
        try {
            if(policy==InjectPolicy.P_PERMISSION){
                permissionDeal();
                return null;
            }else if(policy==InjectPolicy.P_USER_SCHOOL){
                userEvent();
                return null;
            }else if(policy==InjectPolicy.P_CLICK){
                statisticsLogic(args);
                return activityMethod.invoke(obj,args);
            }
            return activityMethod.invoke(obj);
        } catch (IllegalAccessException e) {
            TLog.log("inject_error",e.toString());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            TLog.log("inject_error",e.toString());
            e.printStackTrace();
        }
        return null;
    }
    //统计的综合具体实现
    private void statisticsLogic(Object[] args) {
        View view= (View) args[0];
        String content=statisticsMap.get(view.getId());
        if(statisticsLists.size()>0){
            BusinessUtil.Companion.get().statisticsDatum(statisticsLists.get(1),
                    statisticsLists.get(2),
                    content);
        }else {
            BusinessUtil.Companion.get().statisticsDatum(StatisticsConstants.STATISTICS_TYPE_DEFAULT,
                    StatisticsConstants.STATISTICS_ID_DEFAULT,
                    content);
        }
        TLog.log("test_inject1122233344455",obj+""+args[0].toString()+"__"+content);
    }

    /**
     * 权限代理判断 在业务层做了触发发作后 执行权限判断
     * 拥有权限则执行代理方法 没有权限则请求权限不触发代理方法
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void  permissionDeal() throws InvocationTargetException, IllegalAccessException {
        Activity activity= (Activity) obj;
        String[] permissionName= (String[]) params;
        if(DealWithPermissions.getInstance(activity).checkPermision(permissionName)) {
            activityMethod.invoke(activity);
        }else{
            DealWithPermissions.getInstance(activity).requestSpecificPermissions(permissionName);
        }
    }
    private void  userEvent() throws InvocationTargetException, IllegalAccessException {
        Userpolicy permissionName= (Userpolicy) params;
        if(CommonBaseApplication.getInstance().getLoginUser().getSchool_type().equals("2")) {
            if (permissionName==Userpolicy.P_SCHOOL_TEST) {
                activityMethod.invoke(obj);
            }
        }else if(CommonBaseApplication.getInstance().getLoginUser().getSchool_type().equals("1")) {
            if (permissionName==Userpolicy.P_SCHOOL_SIMPLE) {
                activityMethod.invoke(obj);
            }
        }
    }
}
