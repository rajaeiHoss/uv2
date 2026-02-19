package com.streamax.client;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

public class NotificationClickReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.e("clickNotication:", "click");
    }

    public boolean isRunning(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || (runningTasks = activityManager.getRunningTasks(100)) == null) {
            return false;
        }
        for (ActivityManager.RunningTaskInfo next : runningTasks) {
            if (!TextUtils.isEmpty(context.getPackageName()) && next.baseActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBackground(Context context) {
        Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            if (next.processName.equals(context.getPackageName())) {
                String packageName = context.getPackageName();
                Log.i(packageName, "此appimportace =" + next.importance + ",context.getClass().getName()=" + context.getClass().getName());
                if (next.importance != 100) {
                    String packageName2 = context.getPackageName();
                    Log.i(packageName2, "处于后台" + next.processName);
                    return true;
                }
                String packageName3 = context.getPackageName();
                Log.i(packageName3, "处于前台" + next.processName);
            }
        }
        return false;
    }
}
