package com.young.commomlib.inject;

import android.view.View;

import com.young.baselib.utils.TLog;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/*
 * Des
 * Author
 * Date 2020-06-22
 */
public class EventInvocationHandler implements InvocationHandler {
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
            if(policy==InjectPolicy.P_CLICK){
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
        }else {
        }
    }
}
