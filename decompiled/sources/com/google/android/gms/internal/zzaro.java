package com.google.android.gms.internal;

import android.content.ComponentName;
import android.os.RemoteException;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.stats.zza;
import java.util.Collections;

public final class zzaro extends zzari {
    /* access modifiers changed from: private */
    public final zzarq zzdzm = new zzarq(this);
    private zzasz zzdzn;
    private final zzasn zzdzo;
    private final zzatp zzdzp;

    protected zzaro(zzark zzark) {
        super(zzark);
        this.zzdzp = new zzatp(zzark.zzxx());
        this.zzdzo = new zzarp(this, zzark);
    }

    /* access modifiers changed from: private */
    public final void onServiceDisconnected(ComponentName componentName) {
        zzk.zzwj();
        if (this.zzdzn != null) {
            this.zzdzn = null;
            zza("Disconnected from device AnalyticsService", componentName);
            zzyc().zzxu();
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzasz zzasz) {
        zzk.zzwj();
        this.zzdzn = zzasz;
        zzyw();
        zzyc().onServiceConnected();
    }

    private final void zzyw() {
        this.zzdzp.start();
        this.zzdzo.zzs(zzast.zzecv.get().longValue());
    }

    /* access modifiers changed from: private */
    public final void zzyx() {
        zzk.zzwj();
        if (isConnected()) {
            zzea("Inactivity, disconnecting from device AnalyticsService");
            disconnect();
        }
    }

    public final boolean connect() {
        zzk.zzwj();
        zzyk();
        if (this.zzdzn != null) {
            return true;
        }
        zzasz zzyy = this.zzdzm.zzyy();
        if (zzyy == null) {
            return false;
        }
        this.zzdzn = zzyy;
        zzyw();
        return true;
    }

    public final void disconnect() {
        zzk.zzwj();
        zzyk();
        try {
            zza.zzanm();
            getContext().unbindService(this.zzdzm);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        if (this.zzdzn != null) {
            this.zzdzn = null;
            zzyc().zzxu();
        }
    }

    public final boolean isConnected() {
        zzk.zzwj();
        zzyk();
        return this.zzdzn != null;
    }

    public final boolean zzb(zzasy zzasy) {
        zzbq.checkNotNull(zzasy);
        zzk.zzwj();
        zzyk();
        zzasz zzasz = this.zzdzn;
        if (zzasz == null) {
            return false;
        }
        try {
            zzasz.zza(zzasy.zzjq(), zzasy.zzaan(), zzasy.zzaap() ? zzasl.zzaab() : zzasl.zzaac(), Collections.emptyList());
            zzyw();
            return true;
        } catch (RemoteException unused) {
            zzea("Failed to send hits to AnalyticsService");
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzwk() {
    }

    public final boolean zzyv() {
        zzk.zzwj();
        zzyk();
        zzasz zzasz = this.zzdzn;
        if (zzasz == null) {
            return false;
        }
        try {
            zzasz.zzxr();
            zzyw();
            return true;
        } catch (RemoteException unused) {
            zzea("Failed to clear hits from AnalyticsService");
            return false;
        }
    }
}
