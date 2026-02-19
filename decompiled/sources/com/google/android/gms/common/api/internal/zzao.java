package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzan;
import com.google.android.gms.common.internal.zzbt;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import com.google.android.gms.internal.zzcyw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public final class zzao implements zzbh {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Api.zza<? extends zzcyj, zzcyk> zzfth;
    /* access modifiers changed from: private */
    public final Lock zzfwa;
    private final zzr zzfwf;
    private final Map<Api<?>, Boolean> zzfwi;
    private final zzf zzfwk;
    private ConnectionResult zzfwt;
    /* access modifiers changed from: private */
    public final zzbi zzfxd;
    private int zzfxg;
    private int zzfxh = 0;
    private int zzfxi;
    private final Bundle zzfxj = new Bundle();
    private final Set<Api.zzc> zzfxk = new HashSet();
    /* access modifiers changed from: private */
    public zzcyj zzfxl;
    private boolean zzfxm;
    /* access modifiers changed from: private */
    public boolean zzfxn;
    private boolean zzfxo;
    /* access modifiers changed from: private */
    public zzan zzfxp;
    private boolean zzfxq;
    private boolean zzfxr;
    private ArrayList<Future<?>> zzfxs = new ArrayList<>();

    public zzao(zzbi zzbi, zzr zzr, Map<Api<?>, Boolean> map, zzf zzf, Api.zza<? extends zzcyj, zzcyk> zza, Lock lock, Context context) {
        this.zzfxd = zzbi;
        this.zzfwf = zzr;
        this.zzfwi = map;
        this.zzfwk = zzf;
        this.zzfth = zza;
        this.zzfwa = lock;
        this.mContext = context;
    }

    /* access modifiers changed from: private */
    public final void zza(zzcyw zzcyw) {
        if (zzbs(0)) {
            ConnectionResult zzain = zzcyw.zzain();
            if (zzain.isSuccess()) {
                zzbt zzbfa = zzcyw.zzbfa();
                ConnectionResult zzain2 = zzbfa.zzain();
                if (!zzain2.isSuccess()) {
                    String valueOf = String.valueOf(zzain2);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 48);
                    sb.append("Sign-in succeeded with resolve account failure: ");
                    sb.append(valueOf);
                    Log.wtf("GoogleApiClientConnecting", sb.toString(), new Exception());
                    zze(zzain2);
                    return;
                }
                this.zzfxo = true;
                this.zzfxp = zzbfa.zzamy();
                this.zzfxq = zzbfa.zzamz();
                this.zzfxr = zzbfa.zzana();
                zzajl();
            } else if (zzd(zzain)) {
                zzajn();
                zzajl();
            } else {
                zze(zzain);
            }
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzajk() {
        ConnectionResult connectionResult;
        int i = this.zzfxi - 1;
        this.zzfxi = i;
        if (i > 0) {
            return false;
        }
        if (i < 0) {
            Log.w("GoogleApiClientConnecting", this.zzfxd.zzfvq.zzaju());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            connectionResult = new ConnectionResult(8, (PendingIntent) null);
        } else if (this.zzfwt == null) {
            return true;
        } else {
            this.zzfxd.zzfzb = this.zzfxg;
            connectionResult = this.zzfwt;
        }
        zze(connectionResult);
        return false;
    }

    /* access modifiers changed from: private */
    public final void zzajl() {
        if (this.zzfxi == 0) {
            if (!this.zzfxn || this.zzfxo) {
                ArrayList arrayList = new ArrayList();
                this.zzfxh = 1;
                this.zzfxi = this.zzfxd.zzfyj.size();
                for (Api.zzc next : this.zzfxd.zzfyj.keySet()) {
                    if (!this.zzfxd.zzfyy.containsKey(next)) {
                        arrayList.add(this.zzfxd.zzfyj.get(next));
                    } else if (zzajk()) {
                        zzajm();
                    }
                }
                if (!arrayList.isEmpty()) {
                    this.zzfxs.add(zzbl.zzajx().submit(new zzau(this, arrayList)));
                }
            }
        }
    }

    private final void zzajm() {
        this.zzfxd.zzajw();
        zzbl.zzajx().execute(new zzap(this));
        zzcyj zzcyj = this.zzfxl;
        if (zzcyj != null) {
            if (this.zzfxq) {
                zzcyj.zza(this.zzfxp, this.zzfxr);
            }
            zzbk(false);
        }
        for (Api.zzc<?> zzc : this.zzfxd.zzfyy.keySet()) {
            this.zzfxd.zzfyj.get(zzc).disconnect();
        }
        this.zzfxd.zzfzc.zzk(this.zzfxj.isEmpty() ? null : this.zzfxj);
    }

    /* access modifiers changed from: private */
    public final void zzajn() {
        this.zzfxn = false;
        this.zzfxd.zzfvq.zzfyk = Collections.emptySet();
        for (Api.zzc next : this.zzfxk) {
            if (!this.zzfxd.zzfyy.containsKey(next)) {
                this.zzfxd.zzfyy.put(next, new ConnectionResult(17, (PendingIntent) null));
            }
        }
    }

    private final void zzajo() {
        ArrayList arrayList = this.zzfxs;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((Future) obj).cancel(true);
        }
        this.zzfxs.clear();
    }

    /* access modifiers changed from: private */
    public final Set<Scope> zzajp() {
        if (this.zzfwf == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.zzfwf.zzamf());
        Map<Api<?>, zzt> zzamh = this.zzfwf.zzamh();
        for (Api next : zzamh.keySet()) {
            if (!this.zzfxd.zzfyy.containsKey(next.zzahm())) {
                hashSet.addAll(zzamh.get(next).zzenh);
            }
        }
        return hashSet;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
        if ((r5.hasResolution() || r4.zzfwk.zzbo(r5.getErrorCode()) != null) != false) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.common.ConnectionResult r5, com.google.android.gms.common.api.Api<?> r6, boolean r7) {
        /*
            r4 = this;
            com.google.android.gms.common.api.Api$zzd r0 = r6.zzahk()
            int r0 = r0.getPriority()
            r1 = 0
            r2 = 1
            if (r7 == 0) goto L_0x0024
            boolean r7 = r5.hasResolution()
            if (r7 == 0) goto L_0x0014
        L_0x0012:
            r7 = 1
            goto L_0x0022
        L_0x0014:
            com.google.android.gms.common.zzf r7 = r4.zzfwk
            int r3 = r5.getErrorCode()
            android.content.Intent r7 = r7.zzbo(r3)
            if (r7 == 0) goto L_0x0021
            goto L_0x0012
        L_0x0021:
            r7 = 0
        L_0x0022:
            if (r7 == 0) goto L_0x002d
        L_0x0024:
            com.google.android.gms.common.ConnectionResult r7 = r4.zzfwt
            if (r7 == 0) goto L_0x002c
            int r7 = r4.zzfxg
            if (r0 >= r7) goto L_0x002d
        L_0x002c:
            r1 = 1
        L_0x002d:
            if (r1 == 0) goto L_0x0033
            r4.zzfwt = r5
            r4.zzfxg = r0
        L_0x0033:
            com.google.android.gms.common.api.internal.zzbi r7 = r4.zzfxd
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.ConnectionResult> r7 = r7.zzfyy
            com.google.android.gms.common.api.Api$zzc r6 = r6.zzahm()
            r7.put(r6, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzao.zzb(com.google.android.gms.common.ConnectionResult, com.google.android.gms.common.api.Api, boolean):void");
    }

    private final void zzbk(boolean z) {
        zzcyj zzcyj = this.zzfxl;
        if (zzcyj != null) {
            if (zzcyj.isConnected() && z) {
                this.zzfxl.zzbet();
            }
            this.zzfxl.disconnect();
            this.zzfxp = null;
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzbs(int i) {
        if (this.zzfxh == i) {
            return true;
        }
        Log.w("GoogleApiClientConnecting", this.zzfxd.zzfvq.zzaju());
        String valueOf = String.valueOf(this);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
        sb.append("Unexpected callback in ");
        sb.append(valueOf);
        Log.w("GoogleApiClientConnecting", sb.toString());
        int i2 = this.zzfxi;
        StringBuilder sb2 = new StringBuilder(33);
        sb2.append("mRemainingConnections=");
        sb2.append(i2);
        Log.w("GoogleApiClientConnecting", sb2.toString());
        String zzbt = zzbt(this.zzfxh);
        String zzbt2 = zzbt(i);
        StringBuilder sb3 = new StringBuilder(String.valueOf(zzbt).length() + 70 + String.valueOf(zzbt2).length());
        sb3.append("GoogleApiClient connecting is in step ");
        sb3.append(zzbt);
        sb3.append(" but received callback for step ");
        sb3.append(zzbt2);
        Log.wtf("GoogleApiClientConnecting", sb3.toString(), new Exception());
        zze(new ConnectionResult(8, (PendingIntent) null));
        return false;
    }

    private static String zzbt(int i) {
        return i != 0 ? i != 1 ? "UNKNOWN" : "STEP_GETTING_REMOTE_SERVICE" : "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
    }

    /* access modifiers changed from: private */
    public final boolean zzd(ConnectionResult connectionResult) {
        return this.zzfxm && !connectionResult.hasResolution();
    }

    /* access modifiers changed from: private */
    public final void zze(ConnectionResult connectionResult) {
        zzajo();
        zzbk(!connectionResult.hasResolution());
        this.zzfxd.zzg(connectionResult);
        this.zzfxd.zzfzc.zzc(connectionResult);
    }

    public final void begin() {
        this.zzfxd.zzfyy.clear();
        this.zzfxn = false;
        this.zzfwt = null;
        this.zzfxh = 0;
        this.zzfxm = true;
        this.zzfxo = false;
        this.zzfxq = false;
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (Api next : this.zzfwi.keySet()) {
            Api.zze zze = this.zzfxd.zzfyj.get(next.zzahm());
            z |= next.zzahk().getPriority() == 1;
            boolean booleanValue = this.zzfwi.get(next).booleanValue();
            if (zze.zzacc()) {
                this.zzfxn = true;
                if (booleanValue) {
                    this.zzfxk.add(next.zzahm());
                } else {
                    this.zzfxm = false;
                }
            }
            hashMap.put(zze, new zzaq(this, next, booleanValue));
        }
        if (z) {
            this.zzfxn = false;
        }
        if (this.zzfxn) {
            this.zzfwf.zzc(Integer.valueOf(System.identityHashCode(this.zzfxd.zzfvq)));
            zzax zzax = new zzax(this, (zzap) null);
            Api.zza<? extends zzcyj, zzcyk> zza = this.zzfth;
            Context context = this.mContext;
            Looper looper = this.zzfxd.zzfvq.getLooper();
            zzr zzr = this.zzfwf;
            this.zzfxl = (zzcyj) zza.zza(context, looper, zzr, zzr.zzaml(), zzax, zzax);
        }
        this.zzfxi = this.zzfxd.zzfyj.size();
        this.zzfxs.add(zzbl.zzajx().submit(new zzar(this, hashMap)));
    }

    public final void connect() {
    }

    public final boolean disconnect() {
        zzajo();
        zzbk(true);
        this.zzfxd.zzg((ConnectionResult) null);
        return true;
    }

    public final void onConnected(Bundle bundle) {
        if (zzbs(1)) {
            if (bundle != null) {
                this.zzfxj.putAll(bundle);
            }
            if (zzajk()) {
                zzajm();
            }
        }
    }

    public final void onConnectionSuspended(int i) {
        zze(new ConnectionResult(8, (PendingIntent) null));
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (zzbs(1)) {
            zzb(connectionResult, api, z);
            if (zzajk()) {
                zzajm();
            }
        }
    }

    public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        this.zzfxd.zzfvq.zzfwo.add(t);
        return t;
    }

    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
