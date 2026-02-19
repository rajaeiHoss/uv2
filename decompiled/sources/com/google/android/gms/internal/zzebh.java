package com.google.android.gms.internal;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.zzcf;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.internal.zzu;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

abstract class zzebh<SuccessT, CallbackT> {
    /* access modifiers changed from: private */
    public boolean zzleo;
    protected FirebaseApp zzmpb;
    protected String zzmpu;
    protected final int zzmrg;
    protected final zzebk zzmrh = new zzebk(this);
    protected FirebaseUser zzmri;
    protected zzeaz zzmrj;
    protected CallbackT zzmrk;
    protected zzu zzmrl;
    protected zzebg<SuccessT> zzmrm;
    protected final List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> zzmrn = new ArrayList();
    private Activity zzmro;
    protected Executor zzmrp;
    protected zzebj zzmrq;
    protected zzebw zzmrr;
    protected zzebu zzmrs;
    protected zzebs zzmrt;
    protected zzecc zzmru;
    protected String zzmrv;
    protected PhoneAuthCredential zzmrw;
    boolean zzmrx;
    private SuccessT zzmry;
    private Status zzmrz;

    static class zza extends LifecycleCallback {
        private List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> zzmsa;

        private zza(zzcf zzcf, List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> list) {
            super(zzcf);
            this.zzgam.zza("PhoneAuthActivityStopCallback", (LifecycleCallback) this);
            this.zzmsa = list;
        }

        public static void zza(Activity activity, List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> list) {
            zzcf zzo = zzo(activity);
            if (((zza) zzo.zza("PhoneAuthActivityStopCallback", zza.class)) == null) {
                new zza(zzo, list);
            }
        }

        public final void onStop() {
            synchronized (this.zzmsa) {
                this.zzmsa.clear();
            }
        }
    }

    public zzebh(int i) {
        this.zzmrg = i;
    }

    /* access modifiers changed from: private */
    public final void zzay(Status status) {
        zzu zzu = this.zzmrl;
        if (zzu != null) {
            zzu.onError(status);
        }
    }

    /* access modifiers changed from: private */
    public final void zzbub() {
        zzbtu();
        zzbq.zza(this.zzleo, (Object) "no success or failure set on method implementation");
    }

    /* access modifiers changed from: protected */
    public abstract void dispatch() throws RemoteException;

    public final zzebh<SuccessT, CallbackT> zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        synchronized (this.zzmrn) {
            this.zzmrn.add((PhoneAuthProvider.OnVerificationStateChangedCallbacks) zzbq.checkNotNull(onVerificationStateChangedCallbacks));
        }
        this.zzmro = activity;
        if (activity != null) {
            zza.zza(activity, this.zzmrn);
        }
        this.zzmrp = (Executor) zzbq.checkNotNull(executor);
        return this;
    }

    public final zzebh<SuccessT, CallbackT> zza(zzu zzu) {
        this.zzmrl = (zzu) zzbq.checkNotNull(zzu, "external failure callback cannot be null");
        return this;
    }

    public final void zzax(Status status) {
        this.zzleo = true;
        this.zzmrx = false;
        this.zzmrz = status;
        this.zzmrm.zza(null, status);
    }

    public final zzebh<SuccessT, CallbackT> zzbg(CallbackT callbackt) {
        this.zzmrk = zzbq.checkNotNull(callbackt, "external callback cannot be null");
        return this;
    }

    public final void zzbh(SuccessT successt) {
        this.zzleo = true;
        this.zzmrx = true;
        this.zzmry = successt;
        this.zzmrm.zza(successt, (Status) null);
    }

    public abstract void zzbtu();

    public final zzebh<SuccessT, CallbackT> zzc(FirebaseApp firebaseApp) {
        this.zzmpb = (FirebaseApp) zzbq.checkNotNull(firebaseApp, "firebaseApp cannot be null");
        return this;
    }

    public final zzebh<SuccessT, CallbackT> zzf(FirebaseUser firebaseUser) {
        this.zzmri = (FirebaseUser) zzbq.checkNotNull(firebaseUser, "firebaseUser cannot be null");
        return this;
    }
}
