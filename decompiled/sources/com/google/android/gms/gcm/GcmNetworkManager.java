package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.zzs;
import com.google.android.gms.iid.zzaa;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GcmNetworkManager {
    public static final int RESULT_FAILURE = 2;
    public static final int RESULT_RESCHEDULE = 1;
    public static final int RESULT_SUCCESS = 0;
    private static GcmNetworkManager zziji;
    private Context mContext;
    private final PendingIntent zzekd;
    private final Map<String, Map<String, Boolean>> zzijj = new ArrayMap();

    private GcmNetworkManager(Context context) {
        this.mContext = context;
        this.zzekd = PendingIntent.getBroadcast(context, 0, new Intent().setPackage("com.google.example.invalidpackage"), 0);
    }

    public static GcmNetworkManager getInstance(Context context) {
        GcmNetworkManager gcmNetworkManager;
        synchronized (GcmNetworkManager.class) {
            if (zziji == null) {
                zziji = new GcmNetworkManager(context.getApplicationContext());
            }
            gcmNetworkManager = zziji;
        }
        return gcmNetworkManager;
    }

    private final Intent zzawe() {
        String zzdr = zzaa.zzdr(this.mContext);
        int zzdn = zzdr != null ? GoogleCloudMessaging.zzdn(this.mContext) : -1;
        if (zzdr == null || zzdn < 5000000) {
            StringBuilder sb = new StringBuilder(91);
            sb.append("Google Play Services is not available, dropping GcmNetworkManager request. code=");
            sb.append(zzdn);
            Log.e("GcmNetworkManager", sb.toString());
            return null;
        }
        Intent intent = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE");
        intent.setPackage(zzdr);
        intent.putExtra("app", this.zzekd);
        intent.putExtra(FirebaseAnalytics.Param.SOURCE, 4);
        intent.putExtra("source_version", zzs.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        return intent;
    }

    static void zzid(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Must provide a valid tag.");
        } else if (100 < str.length()) {
            throw new IllegalArgumentException("Tag is larger than max permissible tag length (100)");
        }
    }

    private final boolean zzie(String str) {
        List<ResolveInfo> list;
        Intent intent;
        Intent intent2;
        zzbq.checkNotNull(str, "GcmTaskService must not be null.");
        PackageManager packageManager = this.mContext.getPackageManager();
        if (packageManager == null) {
            list = Collections.emptyList();
        } else {
            if (str != null) {
                intent = new Intent(GcmTaskService.SERVICE_ACTION_EXECUTE_TASK);
                intent2 = intent.setClassName(this.mContext, str);
            } else {
                intent = new Intent(GcmTaskService.SERVICE_ACTION_EXECUTE_TASK);
                intent2 = intent.setPackage(this.mContext.getPackageName());
            }
            list = packageManager.queryIntentServices(intent2, 0);
        }
        if (list == null ? true : list.isEmpty()) {
            Log.e("GcmNetworkManager", String.valueOf(str).concat(" is not available. This may cause the task to be lost."));
            return true;
        }
        for (ResolveInfo next : list) {
            if (next.serviceInfo != null && next.serviceInfo.enabled) {
                return true;
            }
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 118);
        sb.append("The GcmTaskService class you provided ");
        sb.append(str);
        sb.append(" does not seem to support receiving com.google.android.gms.gcm.ACTION_TASK_READY");
        throw new IllegalArgumentException(sb.toString());
    }

    public void cancelAllTasks(Class<? extends GcmTaskService> cls) {
        ComponentName componentName = new ComponentName(this.mContext, cls);
        zzie(componentName.getClassName());
        Intent zzawe = zzawe();
        if (zzawe != null) {
            zzawe.putExtra("scheduler_action", "CANCEL_ALL");
            zzawe.putExtra("component", componentName);
            this.mContext.sendBroadcast(zzawe);
        }
    }

    public void cancelTask(String str, Class<? extends GcmTaskService> cls) {
        ComponentName componentName = new ComponentName(this.mContext, cls);
        zzid(str);
        zzie(componentName.getClassName());
        Intent zzawe = zzawe();
        if (zzawe != null) {
            zzawe.putExtra("scheduler_action", "CANCEL_TASK");
            zzawe.putExtra("tag", str);
            zzawe.putExtra("component", componentName);
            this.mContext.sendBroadcast(zzawe);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void schedule(com.google.android.gms.gcm.Task r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.lang.String r0 = r5.getServiceName()     // Catch:{ all -> 0x004c }
            r4.zzie(r0)     // Catch:{ all -> 0x004c }
            android.content.Intent r0 = r4.zzawe()     // Catch:{ all -> 0x004c }
            if (r0 != 0) goto L_0x0010
            monitor-exit(r4)
            return
        L_0x0010:
            android.os.Bundle r1 = r0.getExtras()     // Catch:{ all -> 0x004c }
            java.lang.String r2 = "scheduler_action"
            java.lang.String r3 = "SCHEDULE_TASK"
            r1.putString(r2, r3)     // Catch:{ all -> 0x004c }
            r5.toBundle(r1)     // Catch:{ all -> 0x004c }
            r0.putExtras(r1)     // Catch:{ all -> 0x004c }
            android.content.Context r1 = r4.mContext     // Catch:{ all -> 0x004c }
            r1.sendBroadcast(r0)     // Catch:{ all -> 0x004c }
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Boolean>> r0 = r4.zzijj     // Catch:{ all -> 0x004c }
            java.lang.String r1 = r5.getServiceName()     // Catch:{ all -> 0x004c }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x004c }
            java.util.Map r0 = (java.util.Map) r0     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x004a
            java.lang.String r1 = r5.getTag()     // Catch:{ all -> 0x004c }
            boolean r1 = r0.containsKey(r1)     // Catch:{ all -> 0x004c }
            if (r1 == 0) goto L_0x004a
            java.lang.String r5 = r5.getTag()     // Catch:{ all -> 0x004c }
            r1 = 1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x004c }
            r0.put(r5, r1)     // Catch:{ all -> 0x004c }
        L_0x004a:
            monitor-exit(r4)
            return
        L_0x004c:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.gcm.GcmNetworkManager.schedule(com.google.android.gms.gcm.Task):void");
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzaa(String str, String str2) {
        Map map = this.zzijj.get(str2);
        if (map == null) {
            map = new ArrayMap();
            this.zzijj.put(str2, map);
        }
        return map.put(str, false) == null;
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzab(String str, String str2) {
        Map map = this.zzijj.get(str2);
        if (map != null) {
            if ((map.remove(str) != null) && map.isEmpty()) {
                this.zzijj.remove(str2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzac(String str, String str2) {
        Map map = this.zzijj.get(str2);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzif(String str) {
        return this.zzijj.containsKey(str);
    }
}
