package com.android.zolarrobot.dlib.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by DuanYuntian on 2019/7/30.
 */

public class AppUtils
{

    public static String getLauncherActivityNameByPackageName(Context context, String packageName) {
        String className = null;
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);//android.intent.action.MAIN
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);//android.intent.category.LAUNCHER
        resolveIntent.setPackage(packageName);
        List<ResolveInfo> resolveinfoList = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            className = resolveinfo.activityInfo.name;
        }
        return className;
    }
    /***获得ip前三位加标点
     *@MehthodName:getTop3OfIp
     *@Param: []
     *@Author: DuanYuntian
     *@Date  :  2018/5/25
     * EG.          192.168.66.
     */
    public static String getTop3OfIp(){
        String ip = AppUtils.getHostIP();
        String[] thisIp= ip.split("\\.");
        String top3 =ip.substring(0,ip.length()-thisIp[3].length());
        return top3;
    }

    /***获取ip地址
     *@MehthodName:getHostIP
     *@Param: []
     *@Author: DuanYuntian
     *@Date  :  2018/5/25
     */
    public static String getHostIP() {
        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.i("yao", "SocketException");
            e.printStackTrace();
        }
        return hostIp;
    }
    // get all service name of list
    public static String getServiceClassName(Context sContext){
        ActivityManager mActivityManager = (ActivityManager) sContext.getSystemService(Context.ACTIVITY_SERVICE) ;
        String str = "";
        //获得当前正在运行的service
        List<ActivityManager.RunningServiceInfo> appList2 = mActivityManager
                .getRunningServices(100);
        for (ActivityManager.RunningServiceInfo running : appList2) {
            str+=running.service.getClassName();
        }
        return str;
    }

    /***检测服务是否存在
     *@MehthodName:checkServiceExcist
     *@Param: []
     *@Author: DuanYuntian
     *@Date  :  2018/5/24
     */
    @SuppressLint("LongLogTag")
    public static boolean checkServiceExcist(String name,Context context) {
        String serviceClassName = AppUtils.getServiceClassName(context);
        Log.e("StartHandler-checkVoicerExist", serviceClassName);
        if (serviceClassName.toLowerCase().contains(name.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    /***获得当前activity的名字
     *@MehthodName:getRunningActivityName
     *@Param: [context]
     *@Author: DuanYuntian
     *@Date  :  2018/8/15
     */
    public static String getRunningActivityName(Context context) {
        ActivityManager activityManager=(ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //完整类名
        String runningActivity=activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        String contextActivity = runningActivity.substring(runningActivity.lastIndexOf(".")+1);
        return contextActivity;
    }

    /***获得当前应用包名
     *@MehthodName:getAppPackageName
     *@Param: [context]
     *@Author: DuanYuntian
     *@Date  :  2018/8/15
     */
    public static String getAppPackageName(Context context){
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = activityManager.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        Log.d("lixx", "当前应用:" + componentInfo.getPackageName());
        return componentInfo.getPackageName();
    }
}
