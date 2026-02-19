package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.util.zzf;
import java.util.HashMap;
import java.util.Map;

@zzabh
public final class zzaou extends zzmn {
    private final Object lock = new Object();
    private boolean zzbkn = true;
    private boolean zzbko;
    private boolean zzbkp;
    private zzmp zzcjx;
    private final zzann zzdly;
    private final boolean zzdrn;
    private final boolean zzdro;
    private final float zzdrp;
    private int zzdrq;
    private boolean zzdrr;
    private boolean zzdrs = true;
    private float zzdrt;
    private float zzdru;

    public zzaou(zzann zzann, float f, boolean z, boolean z2) {
        this.zzdly = zzann;
        this.zzdrp = f;
        this.zzdrn = z;
        this.zzdro = z2;
    }

    private final void zzc(String str, Map<String, String> map) {
        HashMap hashMap = map == null ? new HashMap() : new HashMap(map);
        hashMap.put("action", str);
        zzaly.zzdjt.execute(new zzaov(this, hashMap));
    }

    public final float getAspectRatio() {
        float f;
        synchronized (this.lock) {
            f = this.zzdru;
        }
        return f;
    }

    public final int getPlaybackState() {
        int i;
        synchronized (this.lock) {
            i = this.zzdrq;
        }
        return i;
    }

    public final boolean isClickToExpandEnabled() {
        boolean z;
        boolean isCustomControlsEnabled = isCustomControlsEnabled();
        synchronized (this.lock) {
            if (!isCustomControlsEnabled) {
                try {
                    if (this.zzbkp && this.zzdro) {
                        z = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            z = false;
        }
        return z;
    }

    public final boolean isCustomControlsEnabled() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzdrn && this.zzbko;
        }
        return z;
    }

    public final boolean isMuted() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzdrs;
        }
        return z;
    }

    public final void mute(boolean z) {
        zzc(z ? "mute" : "unmute", (Map<String, String>) null);
    }

    public final void pause() {
        zzc("pause", (Map<String, String>) null);
    }

    public final void play() {
        zzc("play", (Map<String, String>) null);
    }

    public final void zza(float f, int i, boolean z, float f2) {
        boolean z2;
        int i2;
        synchronized (this.lock) {
            this.zzdrt = f;
            z2 = this.zzdrs;
            this.zzdrs = z;
            i2 = this.zzdrq;
            this.zzdrq = i;
            float f3 = this.zzdru;
            this.zzdru = f2;
            if (Math.abs(f2 - f3) > 1.0E-4f) {
                this.zzdly.getView().invalidate();
            }
        }
        zzaly.zzdjt.execute(new zzaow(this, i2, i, z2, z));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, int i2, boolean z, boolean z2) {
        synchronized (this.lock) {
            boolean z3 = false;
            boolean z4 = i != i2;
            boolean z5 = this.zzdrr;
            boolean z6 = !z5 && i2 == 1;
            boolean z7 = z4 && i2 == 1;
            boolean z8 = z4 && i2 == 2;
            boolean z9 = z4 && i2 == 3;
            boolean z10 = z != z2;
            if (z5 || z6) {
                z3 = true;
            }
            this.zzdrr = z3;
            zzmp zzmp = this.zzcjx;
            if (zzmp != null) {
                if (z6) {
                    try {
                        zzmp.onVideoStart();
                    } catch (RemoteException e) {
                        zzahw.zzc("Unable to call onVideoStart()", e);
                    }
                }
                if (z7) {
                    try {
                        this.zzcjx.onVideoPlay();
                    } catch (RemoteException e2) {
                        zzahw.zzc("Unable to call onVideoPlay()", e2);
                    }
                }
                if (z8) {
                    try {
                        this.zzcjx.onVideoPause();
                    } catch (RemoteException e3) {
                        zzahw.zzc("Unable to call onVideoPause()", e3);
                    }
                }
                if (z9) {
                    try {
                        this.zzcjx.onVideoEnd();
                    } catch (RemoteException e4) {
                        zzahw.zzc("Unable to call onVideoEnd()", e4);
                    }
                }
                if (z10) {
                    try {
                        this.zzcjx.onVideoMute(z2);
                    } catch (RemoteException e5) {
                        zzahw.zzc("Unable to call onVideoMute()", e5);
                    }
                }
            } else {
                return;
            }
        }
        return;
    }

    public final void zza(zzmp zzmp) {
        synchronized (this.lock) {
            this.zzcjx = zzmp;
        }
    }

    public final void zzb(zzns zzns) {
        synchronized (this.lock) {
            this.zzbkn = zzns.zzbkn;
            this.zzbko = zzns.zzbko;
            this.zzbkp = zzns.zzbkp;
        }
        zzc("initialState", zzf.zza("muteStart", zzns.zzbkn ? "1" : "0", "customControlsRequested", zzns.zzbko ? "1" : "0", "clickToExpandRequested", zzns.zzbkp ? "1" : "0"));
    }

    public final float zziq() {
        return this.zzdrp;
    }

    public final float zzir() {
        float f;
        synchronized (this.lock) {
            f = this.zzdrt;
        }
        return f;
    }

    public final zzmp zzis() throws RemoteException {
        zzmp zzmp;
        synchronized (this.lock) {
            zzmp = this.zzcjx;
        }
        return zzmp;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzr(Map map) {
        this.zzdly.zza("pubVideoCmd", map);
    }
}
