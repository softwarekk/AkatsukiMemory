package com.young.commomlib.inject;

import android.view.View;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.young.baselib.utils.TLog;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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
        userMethods.clear();
        context=null;
    }

    public  void destoryInject(){
        userMethods.clear();
        methodsHash.clear();
        context=null;
    }

    public  void  init(){
        if(injectpolicy==InjectPolicy.P_CLICK){
            initClick();
            return;
        }
        if(injectpolicy==null){
            initClick();
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
