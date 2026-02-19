package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzt;

@zzabh
public final class zzadt {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    /* access modifiers changed from: private */
    public String zzcwj;
    /* access modifiers changed from: private */
    public String zzcwk;
    /* access modifiers changed from: private */
    public zzamd<zzadz> zzcwl = new zzamd<>();
    private zzt<Object> zzcwm = new zzadu(this);
    private zzt<Object> zzcwn = new zzadv(this);
    private zzt<Object> zzcwo = new zzadw(this);

    public zzadt(Context context, String str, String str2) {
        this.mContext = context;
        this.zzcwk = str2;
        this.zzcwj = str;
    }
}
