package com.google.android.gms.ads.internal.gmsg;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzaij;
import com.google.android.gms.internal.zzaof;
import java.util.HashMap;
import java.util.Map;

final class zzk implements zzt<zzaof> {
    zzk() {
    }

    public final void zza(zzaof zzaof, Map<String, String> map) {
        zzbt.zzel();
        DisplayMetrics zza = zzaij.zza((WindowManager) zzaof.getContext().getSystemService("window"));
        int i = zza.widthPixels;
        int i2 = zza.heightPixels;
        int[] iArr = new int[2];
        HashMap hashMap = new HashMap();
        ((View) zzaof).getLocationInWindow(iArr);
        hashMap.put("xInPixels", Integer.valueOf(iArr[0]));
        hashMap.put("yInPixels", Integer.valueOf(iArr[1]));
        hashMap.put("windowWidthInPixels", Integer.valueOf(i));
        hashMap.put("windowHeightInPixels", Integer.valueOf(i2));
        zzaof.zza("locationReady", (Map<String, ?>) hashMap);
        zzahw.zzcz("GET LOCATION COMPILED");
    }
}
