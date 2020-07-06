package com.young.commomlib.inject;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.view.View;

import com.beile.basemoudle.utils.StringUtils;
import com.beile.basemoudle.utils.TLog;
import com.beile.commonlib.base.CommonBaseApplication;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.beile.basemoudle.DealWithPermissions.CAMERAS;
import static com.beile.basemoudle.DealWithPermissions.MICROPHONE;
import static com.beile.basemoudle.DealWithPermissions.STORAGE;

/*
 * Des 注解处理器
 * Author Young
 * Date 2020-06-11
 */
public class InjectUtil implements LifecycleObserver {

    private static InjectUtil INSTACHE;
    public static InjectUtil getInstance(InjectPolicy policy){
        if(INSTACHE==null){
            INSTACHE=new InjectUtil();
        }
        injectpolicy=policy;
        return INSTACHE;
    }


    private static HashMap<Integer, Method> userMethods=new HashMap();
    private static HashMap<Integer, Method> methodsHash=new HashMap<>();//初始的method
    private static HashMap<Integer, Annotation> annotionHash=new HashMap<>();//拿到的annotion
    private static Object context;
    private static InjectPolicy injectpolicy;

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(LifecycleOwner owner){
        context=owner;
        init();
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void  onDestory(){
        TLog.log("test__inject","ondestory");
        userMethods.clear();
        context=null;
    }

    public  void destoryInject(){
        userMethods.clear();
        methodsHash.clear();
        context=null;
    }

    public  void  init(){
        if(injectpolicy.equals(InjectPolicy.P_PERMISSION)){
            initPermission();
            return;
        }else if(injectpolicy.equals(InjectPolicy.P_USER_SCHOOL)){
            initUserEvent();
            return;
        }else if(injectpolicy==InjectPolicy.P_CLICK){
            initClick();
            return;
        }
        if(injectpolicy==null){
            initPermission();
            initUserEvent();
            initClick();
        }
    }
    /*
     * 处理权限
     * */
    private void initPermission(){
        Class<?> clazz=context.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Permission pInject = field.getAnnotation(Permission.class);
            if(pInject!=null) {
                String targetName = pInject.tms();
                Method targetMet= null;
                try {
                    targetMet = clazz.getMethod(targetName);
                    TLog.log("test_inject_event11", targetMet + "");
                    String[] permissionName=null;
                    PermissionPolicy policy=pInject.value();
                    if(policy.equals(PermissionPolicy.P_STO)){
                        permissionName=STORAGE;
                    }else if(policy.equals(PermissionPolicy.P_MIC)){
                        permissionName=MICROPHONE;
                    }else if(policy.equals(PermissionPolicy.P_CAM)){
                        permissionName=CAMERAS;
                    }
                    EventInvocationHandler listenerInvocationHandler=
                            new EventInvocationHandler(context,targetMet,InjectPolicy.P_PERMISSION,permissionName);

                    //做代理   new View.OnClickListener()对象

                    Object proxy= Proxy.newProxyInstance(InjectUtil.class.getClassLoader()
                            ,new Class[]{IInjectEvent.class},listenerInvocationHandler);
                    IInjectEvent event= (IInjectEvent) proxy;
                    field.setAccessible(true);
                    field.set(context,event);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private  void initUserEvent(){
        Class<?> clazz=context.getClass();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        TLog.log("test_inject_event11",declaredMethods.length+"");
        for(Method mt:declaredMethods) {
            UserSchool userSchool = mt.getAnnotation(UserSchool.class);
            if(userSchool!=null){
                TLog.log("test_inject_event678",""+userSchool+"__"+userMethods.size()+"__"+mt);
                userMethods.put(userMethods.size(),mt);
            }
        }
    }
    /**
     * 目标class
     * @param onlyname 有效方法名
     */
    public void injectUserEvent(String onlyname){
        for(int i=0;i<userMethods.size();i++){
            Method mt=userMethods.get(i);
            if(!StringUtils.isEmpty(onlyname)){
                //但同一个class 中多个注解函数时 限定 作用函数
                if(!mt.getName().equals(onlyname)){
                    continue;
                }
            }
            if(mt==null){
                continue;
            }
            UserSchool userSchool = mt.getAnnotation(UserSchool.class);
            if(CommonBaseApplication.getInstance().getLoginUser().getSchool_type().equals("2")) {
                if (userSchool.value().equals(Userpolicy.P_SCHOOL_TEST)) {
                    invokeM(context,mt);
                }
            }else if(CommonBaseApplication.getInstance().getLoginUser().getSchool_type().equals("1")) {
                TLog.log("test_inject_event33",userSchool.value()+""+userSchool.value().equals(Userpolicy.P_SCHOOL_SIMPLE));
                if (userSchool.value().equals(Userpolicy.P_SCHOOL_SIMPLE)) {
                    invokeM(context,mt);
                }
            }
        }
    }
    private  void invokeM(Object con, Method mt){
        try {
            mt.setAccessible(true);
            mt.invoke(con);
        } catch (IllegalAccessException e) {
            TLog.log("test_inject_event4544",e.toString());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            TLog.log("test_inject_event5555",e.toString());
            e.printStackTrace();
        }
    }

    /**
     * 专注于统计点击事件
     */
    private void initClick(){
        Class<?> clazz=context.getClass();
        Method[] methods=clazz.getDeclaredMethods();
        HashMap<Integer, String> statisticsMap=new HashMap<>();
        for (Method method : methods) {
            //annotation是事件比如onClick 就去取对应的注解
            OnStatisticsClick event = method.getAnnotation(OnStatisticsClick.class);
            TLog.log("test_inject11",event+"___");
            if (event == null) {
                continue;
            }
            //1.setOnClickListener 订阅关系
//                String listenerSetter();
            String listenerSetter = "setOnClickListener";
            //2.new View.OnClickListener()  事件本身
//                Class<?> listenerType();
            Class<?> listenerType = View.OnClickListener.class;
            //3.事件处理程序 method

            //得到3要素之后，就可以执行代码了
            try {
                //反射得到id,再根据ID号得到对应的VIEW（Button）
                int[] viewId = event.viewId();
                int[] staticaticasId=event.sId();
                int[] staticaticasType=event.type();
                TLog.log("test_inject11222",method+"___"+viewId.length);
                for (int i=0;i<viewId.length ; i++) {
                    int vId=viewId[i];
                    int sId=staticaticasId[i];
                    int typeId=staticaticasType[i];
                    List<Integer> sList=new ArrayList<>();
                    sList.add(sId);
                    sList.add(typeId);
                    //为了得到Button对象,使用findViewById
                    Method findViewById = clazz.getMethod("findViewById", int.class);
                    View view = (View) findViewById.invoke(context, vId);
                    statisticsMap.put(view.getId(),event.statics()[i]);
                    //运行到这里，view就相到于我们写的Button
                    if (view == null) {
                        continue;
                    }
                    //activity==context    click===method
                    EventInvocationHandler listenerInvocationHandler =
                            new EventInvocationHandler(context, method, InjectPolicy.P_CLICK,statisticsMap);

                    //做代理   new View.OnClickListener()对象
                    Object proxy = Proxy.newProxyInstance(listenerType.getClassLoader()
                            , new Class[]{listenerType}, listenerInvocationHandler);
                    Method onClickMethod = view.getClass().getMethod(listenerSetter, listenerType);
                    //这就相当于 view.setonclicklisetne(OnClick) onclick->动态指向代理方法
                    onClickMethod.invoke(view, proxy);
                }
            } catch (Exception e) {
                e.printStackTrace();
                TLog.log("inject_error",e.toString());
            }
        }
    }
}
