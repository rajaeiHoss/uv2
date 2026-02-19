package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;

public abstract class zzbdg extends zzbdo {
    private Handler mHandler;
    protected final zze zzata;
    private long zzflv;
    private Runnable zzflw;
    protected boolean zzflx;

    private zzbdg(String str, zze zze, String str2, String str3) {
        this(str, zze, str2, (String) null, 1000);
    }

    public zzbdg(String str, zze zze, String str2, String str3, long j) {
        super(str, str2, str3);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.zzata = zze;
        this.zzflw = new zzbdi(this);
        this.zzflv = 1000;
        zzbg(false);
    }

    public zzbdg(String str, String str2, String str3) {
        this(str, zzi.zzanq(), str2, (String) null);
    }

    public void zzagm() {
        zzbg(false);
    }

    /* access modifiers changed from: protected */
    public final void zzbg(boolean z) {
        if (this.zzflx != z) {
            this.zzflx = z;
            if (z) {
                this.mHandler.postDelayed(this.zzflw, this.zzflv);
            } else {
                this.mHandler.removeCallbacks(this.zzflw);
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzz(long j);
}
