package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.ads.AdActivity;

@zzabh
public final class zzahm {
    private final Object mLock = new Object();
    private String mSessionId;
    private long zzdec = -1;
    private long zzded = -1;
    private int zzdee = -1;
    int zzdef = -1;
    private long zzdeg = 0;
    private int zzdeh = 0;
    private int zzdei = 0;

    public zzahm(String str) {
        this.mSessionId = str;
    }

    private static boolean zzae(Context context) {
        int identifier = context.getResources().getIdentifier("Theme.Translucent", "style", "android");
        if (identifier == 0) {
            zzahw.zzcy("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
        try {
            if (identifier == context.getPackageManager().getActivityInfo(new ComponentName(context.getPackageName(), AdActivity.CLASS_NAME), 0).theme) {
                return true;
            }
            zzahw.zzcy("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            zzahw.zzcz("Fail to fetch AdActivity theme");
            zzahw.zzcy("Please set theme of AdActivity to @android:style/Theme.Translucent to enable transparent background interstitial ad.");
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.internal.zzkk r11, long r12) {
        /*
            r10 = this;
            java.lang.Object r0 = r10.mLock
            monitor-enter(r0)
            com.google.android.gms.internal.zzahi r1 = com.google.android.gms.ads.internal.zzbt.zzep()     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.zzahy r1 = r1.zzqe()     // Catch:{ all -> 0x008c }
            long r1 = r1.zzqw()     // Catch:{ all -> 0x008c }
            com.google.android.gms.common.util.zze r3 = com.google.android.gms.ads.internal.zzbt.zzes()     // Catch:{ all -> 0x008c }
            long r3 = r3.currentTimeMillis()     // Catch:{ all -> 0x008c }
            long r5 = r10.zzded     // Catch:{ all -> 0x008c }
            r7 = -1
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0049
            long r1 = r3 - r1
            com.google.android.gms.internal.zzny<java.lang.Long> r5 = com.google.android.gms.internal.zzoi.zzbpc     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.zzog r6 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x008c }
            java.lang.Object r5 = r6.zzd(r5)     // Catch:{ all -> 0x008c }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x008c }
            long r5 = r5.longValue()     // Catch:{ all -> 0x008c }
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0039
            r1 = -1
            r10.zzdef = r1     // Catch:{ all -> 0x008c }
            goto L_0x0047
        L_0x0039:
            com.google.android.gms.internal.zzahi r1 = com.google.android.gms.ads.internal.zzbt.zzep()     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.zzahy r1 = r1.zzqe()     // Catch:{ all -> 0x008c }
            int r1 = r1.zzqx()     // Catch:{ all -> 0x008c }
            r10.zzdef = r1     // Catch:{ all -> 0x008c }
        L_0x0047:
            r10.zzded = r12     // Catch:{ all -> 0x008c }
        L_0x0049:
            r10.zzdec = r12     // Catch:{ all -> 0x008c }
            r12 = 1
            if (r11 == 0) goto L_0x005f
            android.os.Bundle r13 = r11.extras     // Catch:{ all -> 0x008c }
            if (r13 == 0) goto L_0x005f
            android.os.Bundle r11 = r11.extras     // Catch:{ all -> 0x008c }
            java.lang.String r13 = "gw"
            r1 = 2
            int r11 = r11.getInt(r13, r1)     // Catch:{ all -> 0x008c }
            if (r11 != r12) goto L_0x005f
            monitor-exit(r0)     // Catch:{ all -> 0x008c }
            return
        L_0x005f:
            int r11 = r10.zzdee     // Catch:{ all -> 0x008c }
            int r11 = r11 + r12
            r10.zzdee = r11     // Catch:{ all -> 0x008c }
            int r11 = r10.zzdef     // Catch:{ all -> 0x008c }
            int r11 = r11 + r12
            r10.zzdef = r11     // Catch:{ all -> 0x008c }
            if (r11 != 0) goto L_0x007b
            r11 = 0
            r10.zzdeg = r11     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.zzahi r11 = com.google.android.gms.ads.internal.zzbt.zzep()     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.zzahy r11 = r11.zzqe()     // Catch:{ all -> 0x008c }
            r11.zzk((long) r3)     // Catch:{ all -> 0x008c }
            goto L_0x008a
        L_0x007b:
            com.google.android.gms.internal.zzahi r11 = com.google.android.gms.ads.internal.zzbt.zzep()     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.zzahy r11 = r11.zzqe()     // Catch:{ all -> 0x008c }
            long r11 = r11.zzqy()     // Catch:{ all -> 0x008c }
            long r3 = r3 - r11
            r10.zzdeg = r3     // Catch:{ all -> 0x008c }
        L_0x008a:
            monitor-exit(r0)     // Catch:{ all -> 0x008c }
            return
        L_0x008c:
            r11 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008c }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzahm.zzb(com.google.android.gms.internal.zzkk, long):void");
    }

    public final Bundle zzk(Context context, String str) {
        Bundle bundle;
        synchronized (this.mLock) {
            bundle = new Bundle();
            bundle.putString("session_id", this.mSessionId);
            bundle.putLong("basets", this.zzded);
            bundle.putLong("currts", this.zzdec);
            bundle.putString("seq_num", str);
            bundle.putInt("preqs", this.zzdee);
            bundle.putInt("preqs_in_session", this.zzdef);
            bundle.putLong("time_in_session", this.zzdeg);
            bundle.putInt("pclick", this.zzdeh);
            bundle.putInt("pimp", this.zzdei);
            bundle.putBoolean("support_transparent_background", zzae(context));
        }
        return bundle;
    }

    public final void zzpk() {
        synchronized (this.mLock) {
            this.zzdei++;
        }
    }

    public final void zzpl() {
        synchronized (this.mLock) {
            this.zzdeh++;
        }
    }
}
