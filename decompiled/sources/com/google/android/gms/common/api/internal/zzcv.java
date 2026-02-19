package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzbt;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcyg;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import com.google.android.gms.internal.zzcyo;
import com.google.android.gms.internal.zzcyw;
import java.util.Set;

public final class zzcv extends zzcyo implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static Api.zza<? extends zzcyj, zzcyk> zzgbc = zzcyg.zzegv;
    private final Context mContext;
    private final Handler mHandler;
    private Set<Scope> zzenh;
    private final Api.zza<? extends zzcyj, zzcyk> zzfsa;
    private zzr zzfwf;
    private zzcyj zzfxl;
    /* access modifiers changed from: private */
    public zzcy zzgbd;

    public zzcv(Context context, Handler handler, zzr zzr) {
        this(context, handler, zzr, zzgbc);
    }

    public zzcv(Context context, Handler handler, zzr zzr, Api.zza<? extends zzcyj, zzcyk> zza) {
        this.mContext = context;
        this.mHandler = handler;
        this.zzfwf = (zzr) zzbq.checkNotNull(zzr, "ClientSettings must not be null");
        this.zzenh = zzr.zzamf();
        this.zzfsa = zza;
    }

    /* access modifiers changed from: private */
    public final void zzc(zzcyw zzcyw) {
        ConnectionResult zzain = zzcyw.zzain();
        if (zzain.isSuccess()) {
            zzbt zzbfa = zzcyw.zzbfa();
            zzain = zzbfa.zzain();
            if (!zzain.isSuccess()) {
                String valueOf = String.valueOf(zzain);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 48);
                sb.append("Sign-in succeeded with resolve account failure: ");
                sb.append(valueOf);
                Log.wtf("SignInCoordinator", sb.toString(), new Exception());
            } else {
                this.zzgbd.zzb(zzbfa.zzamy(), this.zzenh);
                this.zzfxl.disconnect();
            }
        }
        this.zzgbd.zzh(zzain);
        this.zzfxl.disconnect();
    }

    public final void onConnected(Bundle bundle) {
        this.zzfxl.zza(this);
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzgbd.zzh(connectionResult);
    }

    public final void onConnectionSuspended(int i) {
        this.zzfxl.disconnect();
    }

    public final void zza(zzcy zzcy) {
        zzcyj zzcyj = this.zzfxl;
        if (zzcyj != null) {
            zzcyj.disconnect();
        }
        this.zzfwf.zzc(Integer.valueOf(System.identityHashCode(this)));
        Api.zza<? extends zzcyj, zzcyk> zza = this.zzfsa;
        Context context = this.mContext;
        Looper looper = this.mHandler.getLooper();
        zzr zzr = this.zzfwf;
        this.zzfxl = (zzcyj) zza.zza(context, looper, zzr, zzr.zzaml(), this, this);
        this.zzgbd = zzcy;
        Set<Scope> set = this.zzenh;
        if (set == null || set.isEmpty()) {
            this.mHandler.post(new zzcw(this));
        } else {
            this.zzfxl.connect();
        }
    }

    public final zzcyj zzakn() {
        return this.zzfxl;
    }

    public final void zzakz() {
        zzcyj zzcyj = this.zzfxl;
        if (zzcyj != null) {
            zzcyj.disconnect();
        }
    }

    public final void zzb(zzcyw zzcyw) {
        this.mHandler.post(new zzcx(this, zzcyw));
    }
}
