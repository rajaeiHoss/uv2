package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzae;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zzf;
import com.google.android.gms.internal.zzbgs;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zzba extends GoogleApiClient implements zzcd {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Looper zzalj;
    private final int zzfte;
    private final GoogleApiAvailability zzftg;
    private Api.zza<? extends zzcyj, zzcyk> zzfth;
    private boolean zzftk;
    private final Lock zzfwa;
    private zzr zzfwf;
    private Map<Api<?>, Boolean> zzfwi;
    final Queue<zzm<?, ?>> zzfwo = new LinkedList();
    private final zzae zzfyc;
    private zzcc zzfyd = null;
    private volatile boolean zzfye;
    private long zzfyf = 120000;
    private long zzfyg = 5000;
    private final zzbf zzfyh;
    private zzbx zzfyi;
    final Map<Api.zzc<?>, Api.zze> zzfyj;
    Set<Scope> zzfyk = new HashSet();
    private final zzcm zzfyl = new zzcm();
    private final ArrayList<zzt> zzfym;
    private Integer zzfyn = null;
    Set<zzdh> zzfyo = null;
    final zzdk zzfyp;
    private final zzaf zzfyq;

    public zzba(Context context, Lock lock, Looper looper, zzr zzr, GoogleApiAvailability googleApiAvailability, Api.zza<? extends zzcyj, zzcyk> zza, Map<Api<?>, Boolean> map, List<GoogleApiClient.ConnectionCallbacks> list, List<GoogleApiClient.OnConnectionFailedListener> list2, Map<Api.zzc<?>, Api.zze> map2, int i, int i2, ArrayList<zzt> arrayList, boolean z) {
        Map<Api.zzc<?>, Api.zze> map3 = map2;
        int i3 = i;
        zzbb zzbb = new zzbb(this);
        this.zzfyq = zzbb;
        this.mContext = context;
        this.zzfwa = lock;
        this.zzftk = false;
        this.zzfyc = new zzae(looper, zzbb);
        this.zzalj = looper;
        this.zzfyh = new zzbf(this, looper);
        this.zzftg = googleApiAvailability;
        this.zzfte = i3;
        if (i3 >= 0) {
            this.zzfyn = Integer.valueOf(i2);
        }
        this.zzfwi = map;
        this.zzfyj = map3;
        this.zzfym = arrayList;
        this.zzfyp = new zzdk(map3);
        for (GoogleApiClient.ConnectionCallbacks registerConnectionCallbacks : list) {
            this.zzfyc.registerConnectionCallbacks(registerConnectionCallbacks);
        }
        for (GoogleApiClient.OnConnectionFailedListener registerConnectionFailedListener : list2) {
            this.zzfyc.registerConnectionFailedListener(registerConnectionFailedListener);
        }
        this.zzfwf = zzr;
        this.zzfth = zza;
    }

    /* access modifiers changed from: private */
    public final void resume() {
        this.zzfwa.lock();
        try {
            if (this.zzfye) {
                zzajq();
            }
        } finally {
            this.zzfwa.unlock();
        }
    }

    public static int zza(Iterable<Api.zze> iterable, boolean z) {
        boolean z2 = false;
        boolean z3 = false;
        for (Api.zze next : iterable) {
            if (next.zzacc()) {
                z2 = true;
            }
            if (next.zzacn()) {
                z3 = true;
            }
        }
        if (z2) {
            return (!z3 || !z) ? 1 : 2;
        }
        return 3;
    }

    /* access modifiers changed from: private */
    public final void zza(GoogleApiClient googleApiClient, zzdb zzdb, boolean z) {
        zzbgs.zzgif.zzd(googleApiClient).setResultCallback(new zzbe(this, zzdb, z, googleApiClient));
    }

    private final void zzajq() {
        this.zzfyc.zzamt();
        this.zzfyd.connect();
    }

    /* access modifiers changed from: private */
    public final void zzajr() {
        this.zzfwa.lock();
        try {
            if (zzajs()) {
                zzajq();
            }
        } finally {
            this.zzfwa.unlock();
        }
    }

    private final void zzbu(int i) {
        Integer num = this.zzfyn;
        if (num == null) {
            this.zzfyn = Integer.valueOf(i);
        } else if (num.intValue() != i) {
            String zzbv = zzbv(i);
            String zzbv2 = zzbv(this.zzfyn.intValue());
            StringBuilder sb = new StringBuilder(String.valueOf(zzbv).length() + 51 + String.valueOf(zzbv2).length());
            sb.append("Cannot use sign-in mode: ");
            sb.append(zzbv);
            sb.append(". Mode was already set to ");
            sb.append(zzbv2);
            throw new IllegalStateException(sb.toString());
        }
        if (this.zzfyd == null) {
            boolean z = false;
            boolean z2 = false;
            for (Api.zze next : this.zzfyj.values()) {
                if (next.zzacc()) {
                    z = true;
                }
                if (next.zzacn()) {
                    z2 = true;
                }
            }
            int intValue = this.zzfyn.intValue();
            if (intValue != 1) {
                if (intValue == 2 && z) {
                    if (this.zzftk) {
                        this.zzfyd = new zzaa(this.mContext, this.zzfwa, this.zzalj, this.zzftg, this.zzfyj, this.zzfwf, this.zzfwi, this.zzfth, this.zzfym, this, true);
                        return;
                    }
                    this.zzfyd = zzv.zza(this.mContext, this, this.zzfwa, this.zzalj, this.zzftg, this.zzfyj, this.zzfwf, this.zzfwi, this.zzfth, this.zzfym);
                    return;
                }
            } else if (!z) {
                throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
            } else if (z2) {
                throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            if (!this.zzftk || z2) {
                this.zzfyd = new zzbi(this.mContext, this, this.zzfwa, this.zzalj, this.zzftg, this.zzfyj, this.zzfwf, this.zzfwi, this.zzfth, this.zzfym, this);
                return;
            }
            this.zzfyd = new zzaa(this.mContext, this.zzfwa, this.zzalj, this.zzftg, this.zzfyj, this.zzfwf, this.zzfwi, this.zzfth, this.zzfym, this, false);
        }
    }

    private static String zzbv(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "UNKNOWN" : "SIGN_IN_MODE_NONE" : "SIGN_IN_MODE_OPTIONAL" : "SIGN_IN_MODE_REQUIRED";
    }

    public final ConnectionResult blockingConnect() {
        boolean z = true;
        zzbq.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "blockingConnect must not be called on the UI thread");
        this.zzfwa.lock();
        try {
            if (this.zzfte >= 0) {
                if (this.zzfyn == null) {
                    z = false;
                }
                zzbq.zza(z, (Object) "Sign-in mode should have been set explicitly by auto-manage.");
            } else {
                Integer num = this.zzfyn;
                if (num == null) {
                    this.zzfyn = Integer.valueOf(zza(this.zzfyj.values(), false));
                } else if (num.intValue() == 2) {
                    throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
            }
            zzbu(this.zzfyn.intValue());
            this.zzfyc.zzamt();
            return this.zzfyd.blockingConnect();
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        zzbq.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "blockingConnect must not be called on the UI thread");
        zzbq.checkNotNull(timeUnit, "TimeUnit must not be null");
        this.zzfwa.lock();
        try {
            Integer num = this.zzfyn;
            if (num == null) {
                this.zzfyn = Integer.valueOf(zza(this.zzfyj.values(), false));
            } else if (num.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzbu(this.zzfyn.intValue());
            this.zzfyc.zzamt();
            return this.zzfyd.blockingConnect(j, timeUnit);
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final PendingResult<Status> clearDefaultAccountAndReconnect() {
        zzbq.zza(isConnected(), (Object) "GoogleApiClient is not connected yet.");
        zzbq.zza(this.zzfyn.intValue() != 2, (Object) "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        zzdb zzdb = new zzdb((GoogleApiClient) this);
        if (this.zzfyj.containsKey(zzbgs.zzegu)) {
            zza(this, zzdb, false);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            GoogleApiClient build = new GoogleApiClient.Builder(this.mContext).addApi(zzbgs.API).addConnectionCallbacks(new zzbc(this, atomicReference, zzdb)).addOnConnectionFailedListener(new zzbd(this, zzdb)).setHandler(this.zzfyh).build();
            atomicReference.set(build);
            build.connect();
        }
        return zzdb;
    }

    public final void connect() {
        this.zzfwa.lock();
        try {
            boolean z = false;
            if (this.zzfte >= 0) {
                if (this.zzfyn != null) {
                    z = true;
                }
                zzbq.zza(z, (Object) "Sign-in mode should have been set explicitly by auto-manage.");
            } else {
                Integer num = this.zzfyn;
                if (num == null) {
                    this.zzfyn = Integer.valueOf(zza(this.zzfyj.values(), false));
                } else if (num.intValue() == 2) {
                    throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
            }
            connect(this.zzfyn.intValue());
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final void connect(int i) {
        this.zzfwa.lock();
        boolean z = true;
        if (!(i == 3 || i == 1 || i == 2)) {
            z = false;
        }
        try {
            StringBuilder sb = new StringBuilder(33);
            sb.append("Illegal sign-in mode: ");
            sb.append(i);
            zzbq.checkArgument(z, sb.toString());
            zzbu(i);
            zzajq();
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final void disconnect() {
        this.zzfwa.lock();
        try {
            this.zzfyp.release();
            zzcc zzcc = this.zzfyd;
            if (zzcc != null) {
                zzcc.disconnect();
            }
            this.zzfyl.release();
            for (zzm zzm : this.zzfwo) {
                zzm.zza((zzdn) null);
                zzm.cancel();
            }
            this.zzfwo.clear();
            if (this.zzfyd != null) {
                zzajs();
                this.zzfyc.zzams();
            }
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("mContext=").println(this.mContext);
        printWriter.append(str).append("mResuming=").print(this.zzfye);
        printWriter.append(" mWorkQueue.size()=").print(this.zzfwo.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zzfyp.zzgbs.size());
        zzcc zzcc = this.zzfyd;
        if (zzcc != null) {
            zzcc.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public final ConnectionResult getConnectionResult(Api<?> api) {
        ConnectionResult connectionResult;
        this.zzfwa.lock();
        try {
            if (!isConnected()) {
                if (!this.zzfye) {
                    throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
                }
            }
            if (this.zzfyj.containsKey(api.zzahm())) {
                ConnectionResult connectionResult2 = this.zzfyd.getConnectionResult(api);
                if (connectionResult2 == null) {
                    if (this.zzfye) {
                        connectionResult = ConnectionResult.zzfqt;
                    } else {
                        Log.w("GoogleApiClientImpl", zzaju());
                        Log.wtf("GoogleApiClientImpl", String.valueOf(api.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
                        connectionResult = new ConnectionResult(8, (PendingIntent) null);
                    }
                    return connectionResult;
                }
                this.zzfwa.unlock();
                return connectionResult2;
            }
            throw new IllegalArgumentException(String.valueOf(api.getName()).concat(" was never registered with GoogleApiClient"));
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzalj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r3 = r2.zzfyj.get(r3.zzahm());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean hasConnectedApi(com.google.android.gms.common.api.Api<?> r3) {
        /*
            r2 = this;
            boolean r0 = r2.isConnected()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.util.Map<com.google.android.gms.common.api.Api$zzc<?>, com.google.android.gms.common.api.Api$zze> r0 = r2.zzfyj
            com.google.android.gms.common.api.Api$zzc r3 = r3.zzahm()
            java.lang.Object r3 = r0.get(r3)
            com.google.android.gms.common.api.Api$zze r3 = (com.google.android.gms.common.api.Api.zze) r3
            if (r3 == 0) goto L_0x001e
            boolean r3 = r3.isConnected()
            if (r3 == 0) goto L_0x001e
            r3 = 1
            return r3
        L_0x001e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzba.hasConnectedApi(com.google.android.gms.common.api.Api):boolean");
    }

    public final boolean isConnected() {
        zzcc zzcc = this.zzfyd;
        return zzcc != null && zzcc.isConnected();
    }

    public final boolean isConnecting() {
        zzcc zzcc = this.zzfyd;
        return zzcc != null && zzcc.isConnecting();
    }

    public final boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.zzfyc.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    public final boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.zzfyc.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    public final void reconnect() {
        disconnect();
        connect();
    }

    public final void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zzfyc.registerConnectionCallbacks(connectionCallbacks);
    }

    public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zzfyc.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public final void stopAutoManage(FragmentActivity fragmentActivity) {
        zzce zzce = new zzce(fragmentActivity);
        if (this.zzfte >= 0) {
            zzi.zza(zzce).zzbq(this.zzfte);
            return;
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }

    public final void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zzfyc.unregisterConnectionCallbacks(connectionCallbacks);
    }

    public final void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zzfyc.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    public final <C extends Api.zze> C zza(Api.zzc<C> zzc) {
        @SuppressWarnings("unchecked")
        C c = (C) this.zzfyj.get(zzc);
        zzbq.checkNotNull(c, "Appropriate Api was not requested.");
        return c;
    }

    public final void zza(zzdh zzdh) {
        this.zzfwa.lock();
        try {
            if (this.zzfyo == null) {
                this.zzfyo = new HashSet();
            }
            this.zzfyo.add(zzdh);
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final boolean zza(Api<?> api) {
        return this.zzfyj.containsKey(api.zzahm());
    }

    public final boolean zza(zzcu zzcu) {
        zzcc zzcc = this.zzfyd;
        return zzcc != null && zzcc.zza(zzcu);
    }

    public final void zzaia() {
        zzcc zzcc = this.zzfyd;
        if (zzcc != null) {
            zzcc.zzaia();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzajs() {
        if (!this.zzfye) {
            return false;
        }
        this.zzfye = false;
        this.zzfyh.removeMessages(2);
        this.zzfyh.removeMessages(1);
        zzbx zzbx = this.zzfyi;
        if (zzbx != null) {
            zzbx.unregister();
            this.zzfyi = null;
        }
        return true;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public final boolean zzajt() {
        this.zzfwa.lock();
        try {
            Set<zzdh> set = this.zzfyo;
            if (set == null) {
                this.zzfwa.unlock();
                return false;
            }
            boolean z = !set.isEmpty();
            this.zzfwa.unlock();
            return z;
        } catch (Throwable th) {
            this.zzfwa.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzaju() {
        StringWriter stringWriter = new StringWriter();
        dump("", (FileDescriptor) null, new PrintWriter(stringWriter), (String[]) null);
        return stringWriter.toString();
    }

    public final void zzb(zzdh zzdh) {
        String str = null;
        Exception exc = null;
        this.zzfwa.lock();
        try {
            Set<zzdh> set = this.zzfyo;
            if (set == null) {
                str = "Attempted to remove pending transform when no transforms are registered.";
                exc = new Exception();
            } else if (!set.remove(zzdh)) {
                str = "Failed to remove pending transform - this may lead to memory leaks!";
                exc = new Exception();
            } else {
                if (!zzajt()) {
                    this.zzfyd.zzais();
                }
            }
            if (str != null) {
                Log.wtf("GoogleApiClientImpl", str, exc);
            }
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final void zzc(ConnectionResult connectionResult) {
        if (!zzf.zzd(this.mContext, connectionResult.getErrorCode())) {
            zzajs();
        }
        if (!this.zzfye) {
            this.zzfyc.zzk(connectionResult);
            this.zzfyc.zzams();
        }
    }

    public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        zzbq.checkArgument(t.zzahm() != null, "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean containsKey = this.zzfyj.containsKey(t.zzahm());
        String name = t.zzaht() != null ? t.zzaht().getName() : "the API";
        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 65);
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(name);
        sb.append(" required for this call.");
        zzbq.checkArgument(containsKey, sb.toString());
        this.zzfwa.lock();
        try {
            zzcc zzcc = this.zzfyd;
            if (zzcc == null) {
                this.zzfwo.add(t);
            } else {
                t = zzcc.zzd(t);
            }
            return t;
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        zzbq.checkArgument(t.zzahm() != null, "This task can not be executed (it's probably a Batch or malformed)");
        boolean containsKey = this.zzfyj.containsKey(t.zzahm());
        String name = t.zzaht() != null ? t.zzaht().getName() : "the API";
        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 65);
        sb.append("GoogleApiClient is not configured to use ");
        sb.append(name);
        sb.append(" required for this call.");
        zzbq.checkArgument(containsKey, sb.toString());
        this.zzfwa.lock();
        try {
            if (this.zzfyd != null) {
                if (this.zzfye) {
                    this.zzfwo.add(t);
                    while (!this.zzfwo.isEmpty()) {
                        zzm remove = this.zzfwo.remove();
                        this.zzfyp.zzb(remove);
                        remove.zzu(Status.zzfts);
                    }
                } else {
                    t = this.zzfyd.zze(t);
                }
                return t;
            }
            throw new IllegalStateException("GoogleApiClient is not connected yet.");
        } finally {
            this.zzfwa.unlock();
        }
    }

    public final void zzf(int i, boolean z) {
        if (i == 1 && !z && !this.zzfye) {
            this.zzfye = true;
            if (this.zzfyi == null) {
                this.zzfyi = GoogleApiAvailability.zza(this.mContext.getApplicationContext(), (zzby) new zzbg(this));
            }
            zzbf zzbf = this.zzfyh;
            zzbf.sendMessageDelayed(zzbf.obtainMessage(1), this.zzfyf);
            zzbf zzbf2 = this.zzfyh;
            zzbf2.sendMessageDelayed(zzbf2.obtainMessage(2), this.zzfyg);
        }
        this.zzfyp.zzald();
        this.zzfyc.zzcf(i);
        this.zzfyc.zzams();
        if (i == 2) {
            zzajq();
        }
    }

    public final void zzk(Bundle bundle) {
        while (!this.zzfwo.isEmpty()) {
            zze(this.zzfwo.remove());
        }
        this.zzfyc.zzl(bundle);
    }

    public final <L> zzci<L> zzt(L l) {
        this.zzfwa.lock();
        try {
            return this.zzfyl.zza(l, this.zzalj, "NO_TYPE");
        } finally {
            this.zzfwa.unlock();
        }
    }
}
