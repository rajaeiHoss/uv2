package com.google.android.gms.gcm;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.google.android.gms.drive.DriveFile;
import java.util.Iterator;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

final class zza {
    static zza zzijk;
    private final Context mContext;
    private String zzijl;
    private final AtomicInteger zzijm = new AtomicInteger((int) SystemClock.elapsedRealtime());

    private zza(Context context) {
        this.mContext = context.getApplicationContext();
    }

    private final Bundle zzawf() {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        return (applicationInfo == null || applicationInfo.metaData == null) ? Bundle.EMPTY : applicationInfo.metaData;
    }

    static String zzd(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace("gcm.n.", "gcm.notification.")) : string;
    }

    static synchronized zza zzdl(Context context) {
        zza zza;
        synchronized (zza.class) {
            if (zzijk == null) {
                zzijk = new zza(context);
            }
            zza = zzijk;
        }
        return zza;
    }

    static boolean zzdm(Context context) {
        if (((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            return false;
        }
        int myPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (next.pid == myPid) {
                    if (next.importance == 100) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final String zze(Bundle bundle, String str) {
        Bundle bundle2 = bundle;
        String zzd = zzd(bundle, str);
        if (!TextUtils.isEmpty(zzd)) {
            return zzd;
        }
        String valueOf = String.valueOf(str);
        String zzd2 = zzd(bundle2, "_loc_key".length() != 0 ? valueOf.concat("_loc_key") : new String(valueOf));
        if (TextUtils.isEmpty(zzd2)) {
            return null;
        }
        Resources resources = this.mContext.getResources();
        int identifier = resources.getIdentifier(zzd2, "string", this.mContext.getPackageName());
        if (identifier == 0) {
            String valueOf2 = String.valueOf(str);
            String substring = ("_loc_key".length() != 0 ? valueOf2.concat("_loc_key") : new String(valueOf2)).substring(6);
            StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 49 + String.valueOf(zzd2).length());
            sb.append(substring);
            sb.append(" resource not found: ");
            sb.append(zzd2);
            sb.append(" Default value will be used.");
            Log.w("GcmNotification", sb.toString());
            return null;
        }
        String valueOf3 = String.valueOf(str);
        String zzd3 = zzd(bundle2, "_loc_args".length() != 0 ? valueOf3.concat("_loc_args") : new String(valueOf3));
        if (TextUtils.isEmpty(zzd3)) {
            return resources.getString(identifier);
        }
        try {
            JSONArray jSONArray = new JSONArray(zzd3);
            int length = jSONArray.length();
            Object[] objArr = new String[length];
            for (int i = 0; i < length; i++) {
                objArr[i] = jSONArray.opt(i);
            }
            return resources.getString(identifier, objArr);
        } catch (JSONException unused) {
            String valueOf4 = String.valueOf(str);
            String substring2 = ("_loc_args".length() != 0 ? valueOf4.concat("_loc_args") : new String(valueOf4)).substring(6);
            StringBuilder sb2 = new StringBuilder(String.valueOf(substring2).length() + 41 + String.valueOf(zzd3).length());
            sb2.append("Malformed ");
            sb2.append(substring2);
            sb2.append(": ");
            sb2.append(zzd3);
            sb2.append("  Default value will be used.");
            Log.w("GcmNotification", sb2.toString());
            return null;
        } catch (MissingFormatArgumentException e) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(zzd2).length() + 58 + String.valueOf(zzd3).length());
            sb3.append("Missing format argument for ");
            sb3.append(zzd2);
            sb3.append(": ");
            sb3.append(zzd3);
            sb3.append(" Default value will be used.");
            Log.w("GcmNotification", sb3.toString(), e);
            return null;
        }
    }

    static void zzs(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Iterator it = bundle.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            String string = bundle.getString(str);
            if (str.startsWith("gcm.notification.")) {
                str = str.replace("gcm.notification.", "gcm.n.");
            }
            if (str.startsWith("gcm.n.")) {
                if (!"gcm.n.e".equals(str)) {
                    bundle2.putString(str.substring(6), string);
                }
                it.remove();
            }
        }
        String string2 = bundle2.getString("sound2");
        if (string2 != null) {
            bundle2.remove("sound2");
            bundle2.putString("sound", string2);
        }
        if (!bundle2.isEmpty()) {
            bundle.putBundle("notification", bundle2);
        }
    }

    private final PendingIntent zzu(Bundle bundle) {
        Intent intent;
        String zzd = zzd(bundle, "gcm.n.click_action");
        if (!TextUtils.isEmpty(zzd)) {
            intent = new Intent(zzd);
            intent.setPackage(this.mContext.getPackageName());
            intent.setFlags(DriveFile.MODE_READ_ONLY);
        } else {
            intent = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName());
            if (intent == null) {
                Log.w("GcmNotification", "No activity found to launch app");
                return null;
            }
        }
        Bundle bundle2 = new Bundle(bundle);
        GcmListenerService.zzr(bundle2);
        intent.putExtras(bundle2);
        for (String str : bundle2.keySet()) {
            if (str.startsWith("gcm.n.") || str.startsWith("gcm.notification.")) {
                intent.removeExtra(str);
            }
        }
        return PendingIntent.getActivity(this.mContext, this.zzijm.getAndIncrement(), intent, BasicMeasure.EXACTLY);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01ea  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0247  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzt(android.os.Bundle r15) {
        /*
            r14 = this;
            java.lang.String r0 = "gcm.n.title"
            java.lang.String r0 = r14.zze(r15, r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x001c
            android.content.Context r0 = r14.mContext
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo()
            android.content.Context r1 = r14.mContext
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            java.lang.CharSequence r0 = r0.loadLabel(r1)
        L_0x001c:
            java.lang.String r1 = "gcm.n.body"
            java.lang.String r1 = r14.zze(r15, r1)
            java.lang.String r2 = "gcm.n.icon"
            java.lang.String r2 = zzd(r15, r2)
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            java.lang.String r4 = "GcmNotification"
            if (r3 != 0) goto L_0x0077
            android.content.Context r3 = r14.mContext
            android.content.res.Resources r3 = r3.getResources()
            android.content.Context r5 = r14.mContext
            java.lang.String r5 = r5.getPackageName()
            java.lang.String r6 = "drawable"
            int r5 = r3.getIdentifier(r2, r6, r5)
            if (r5 == 0) goto L_0x0045
            goto L_0x0089
        L_0x0045:
            android.content.Context r5 = r14.mContext
            java.lang.String r5 = r5.getPackageName()
            java.lang.String r6 = "mipmap"
            int r5 = r3.getIdentifier(r2, r6, r5)
            if (r5 == 0) goto L_0x0054
            goto L_0x0089
        L_0x0054:
            java.lang.String r3 = java.lang.String.valueOf(r2)
            int r3 = r3.length()
            int r3 = r3 + 57
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r3)
            java.lang.String r3 = "Icon resource "
            r5.append(r3)
            r5.append(r2)
            java.lang.String r2 = " not found. Notification will use app icon."
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            android.util.Log.w(r4, r2)
        L_0x0077:
            android.content.Context r2 = r14.mContext
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo()
            int r2 = r2.icon
            if (r2 != 0) goto L_0x0088
            r2 = 17301651(0x1080093, float:2.4979667E-38)
            r5 = 17301651(0x1080093, float:2.4979667E-38)
            goto L_0x0089
        L_0x0088:
            r5 = r2
        L_0x0089:
            java.lang.String r2 = "gcm.n.color"
            java.lang.String r2 = zzd(r15, r2)
            java.lang.String r3 = "gcm.n.sound2"
            java.lang.String r3 = zzd(r15, r3)
            boolean r6 = android.text.TextUtils.isEmpty(r3)
            r7 = 0
            if (r6 == 0) goto L_0x009e
            r3 = r7
            goto L_0x00f6
        L_0x009e:
            java.lang.String r6 = "default"
            boolean r6 = r6.equals(r3)
            if (r6 != 0) goto L_0x00f1
            android.content.Context r6 = r14.mContext
            android.content.res.Resources r6 = r6.getResources()
            android.content.Context r8 = r14.mContext
            java.lang.String r8 = r8.getPackageName()
            java.lang.String r9 = "raw"
            int r6 = r6.getIdentifier(r3, r9, r8)
            if (r6 == 0) goto L_0x00f1
            android.content.Context r6 = r14.mContext
            java.lang.String r6 = r6.getPackageName()
            java.lang.String r8 = java.lang.String.valueOf(r6)
            int r8 = r8.length()
            int r8 = r8 + 24
            java.lang.String r9 = java.lang.String.valueOf(r3)
            int r9 = r9.length()
            int r8 = r8 + r9
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r8)
            java.lang.String r8 = "android.resource://"
            r9.append(r8)
            r9.append(r6)
            java.lang.String r6 = "/raw/"
            r9.append(r6)
            r9.append(r3)
            java.lang.String r3 = r9.toString()
            android.net.Uri r3 = android.net.Uri.parse(r3)
            goto L_0x00f6
        L_0x00f1:
            r3 = 2
            android.net.Uri r3 = android.media.RingtoneManager.getDefaultUri(r3)
        L_0x00f6:
            android.app.PendingIntent r6 = r14.zzu(r15)
            boolean r8 = com.google.android.gms.common.util.zzs.isAtLeastO()
            r9 = 3
            r10 = 1
            if (r8 == 0) goto L_0x01ea
            android.content.Context r8 = r14.mContext
            android.content.pm.ApplicationInfo r8 = r8.getApplicationInfo()
            int r8 = r8.targetSdkVersion
            r11 = 25
            if (r8 <= r11) goto L_0x01ea
            java.lang.String r8 = "gcm.n.android_channel_id"
            java.lang.String r8 = zzd(r15, r8)
            boolean r11 = com.google.android.gms.common.util.zzs.isAtLeastO()
            java.lang.String r12 = "fcm_fallback_notification_channel"
            if (r11 != 0) goto L_0x011e
            goto L_0x019c
        L_0x011e:
            android.content.Context r7 = r14.mContext
            java.lang.Class<android.app.NotificationManager> r11 = android.app.NotificationManager.class
            java.lang.Object r7 = r7.getSystemService(r11)
            android.app.NotificationManager r7 = (android.app.NotificationManager) r7
            boolean r11 = android.text.TextUtils.isEmpty(r8)
            if (r11 != 0) goto L_0x0158
            android.app.NotificationChannel r11 = r7.getNotificationChannel(r8)
            if (r11 == 0) goto L_0x0135
            goto L_0x015c
        L_0x0135:
            java.lang.String r11 = java.lang.String.valueOf(r8)
            int r11 = r11.length()
            int r11 = r11 + 122
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r11)
            java.lang.String r11 = "Notification Channel requested ("
            r13.append(r11)
            r13.append(r8)
            java.lang.String r8 = ") has not been created by the app. Manifest configuration, or default, value will be used."
            r13.append(r8)
            java.lang.String r8 = r13.toString()
            android.util.Log.w(r4, r8)
        L_0x0158:
            java.lang.String r8 = r14.zzijl
            if (r8 == 0) goto L_0x015e
        L_0x015c:
            r7 = r8
            goto L_0x019c
        L_0x015e:
            android.os.Bundle r8 = r14.zzawf()
            java.lang.String r11 = "com.google.android.gms.gcm.default_notification_channel_id"
            java.lang.String r8 = r8.getString(r11)
            r14.zzijl = r8
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 != 0) goto L_0x017e
            java.lang.String r8 = r14.zzijl
            android.app.NotificationChannel r8 = r7.getNotificationChannel(r8)
            if (r8 == 0) goto L_0x017b
            java.lang.String r7 = r14.zzijl
            goto L_0x019c
        L_0x017b:
            java.lang.String r8 = "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used."
            goto L_0x0180
        L_0x017e:
            java.lang.String r8 = "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used."
        L_0x0180:
            android.util.Log.w(r4, r8)
            android.app.NotificationChannel r8 = r7.getNotificationChannel(r12)
            if (r8 != 0) goto L_0x0199
            android.app.NotificationChannel r8 = new android.app.NotificationChannel
            android.content.Context r11 = r14.mContext
            int r13 = com.google.android.gms.R.string.gcm_fallback_notification_channel_label
            java.lang.String r11 = r11.getString(r13)
            r8.<init>(r12, r11, r9)
            r7.createNotificationChannel(r8)
        L_0x0199:
            r14.zzijl = r12
            r7 = r12
        L_0x019c:
            android.app.Notification$Builder r8 = new android.app.Notification$Builder
            android.content.Context r11 = r14.mContext
            r8.<init>(r11)
            android.app.Notification$Builder r8 = r8.setAutoCancel(r10)
            android.app.Notification$Builder r5 = r8.setSmallIcon(r5)
            boolean r8 = android.text.TextUtils.isEmpty(r0)
            if (r8 != 0) goto L_0x01b4
            r5.setContentTitle(r0)
        L_0x01b4:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L_0x01c9
            r5.setContentText(r1)
            android.app.Notification$BigTextStyle r0 = new android.app.Notification$BigTextStyle
            r0.<init>()
            android.app.Notification$BigTextStyle r0 = r0.bigText(r1)
            r5.setStyle(r0)
        L_0x01c9:
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x01d6
            int r0 = android.graphics.Color.parseColor(r2)
            r5.setColor(r0)
        L_0x01d6:
            if (r3 == 0) goto L_0x01db
            r5.setSound(r3)
        L_0x01db:
            if (r6 == 0) goto L_0x01e0
            r5.setContentIntent(r6)
        L_0x01e0:
            if (r7 == 0) goto L_0x01e5
            r5.setChannelId(r7)
        L_0x01e5:
            android.app.Notification r0 = r5.build()
            goto L_0x0226
        L_0x01ea:
            androidx.core.app.NotificationCompat$Builder r7 = new androidx.core.app.NotificationCompat$Builder
            android.content.Context r8 = r14.mContext
            r7.<init>(r8)
            androidx.core.app.NotificationCompat$Builder r7 = r7.setAutoCancel(r10)
            androidx.core.app.NotificationCompat$Builder r5 = r7.setSmallIcon((int) r5)
            boolean r7 = android.text.TextUtils.isEmpty(r0)
            if (r7 != 0) goto L_0x0202
            r5.setContentTitle(r0)
        L_0x0202:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 != 0) goto L_0x020b
            r5.setContentText(r1)
        L_0x020b:
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x0218
            int r0 = android.graphics.Color.parseColor(r2)
            r5.setColor(r0)
        L_0x0218:
            if (r3 == 0) goto L_0x021d
            r5.setSound(r3)
        L_0x021d:
            if (r6 == 0) goto L_0x0222
            r5.setContentIntent(r6)
        L_0x0222:
            android.app.Notification r0 = r5.build()
        L_0x0226:
            java.lang.String r1 = "gcm.n.tag"
            java.lang.String r15 = zzd(r15, r1)
            boolean r1 = android.util.Log.isLoggable(r4, r9)
            if (r1 == 0) goto L_0x0237
            java.lang.String r1 = "Showing notification"
            android.util.Log.d(r4, r1)
        L_0x0237:
            android.content.Context r1 = r14.mContext
            java.lang.String r2 = "notification"
            java.lang.Object r1 = r1.getSystemService(r2)
            android.app.NotificationManager r1 = (android.app.NotificationManager) r1
            boolean r2 = android.text.TextUtils.isEmpty(r15)
            if (r2 == 0) goto L_0x025e
            long r2 = android.os.SystemClock.uptimeMillis()
            r15 = 37
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r15)
            java.lang.String r15 = "GCM-Notification:"
            r4.append(r15)
            r4.append(r2)
            java.lang.String r15 = r4.toString()
        L_0x025e:
            r2 = 0
            r1.notify(r15, r2, r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.gcm.zza.zzt(android.os.Bundle):boolean");
    }
}
