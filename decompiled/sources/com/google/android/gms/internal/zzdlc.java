package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule;

public abstract class zzdlc<T> {
    private final Context mContext;
    private final Object mLock = new Object();
    private final String mTag;
    private boolean zzlhu = false;
    private T zzlhv;

    public zzdlc(Context context, String str) {
        this.mContext = context;
        this.mTag = str;
    }

    public final boolean isOperational() {
        return zzblo() != null;
    }

    /* access modifiers changed from: protected */
    public abstract T zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, DynamiteModule.zzc;

    /* access modifiers changed from: protected */
    public abstract void zzbll() throws RemoteException;

    public final void zzbln() {
        synchronized (this.mLock) {
            if (this.zzlhv != null) {
                try {
                    zzbll();
                } catch (RemoteException e) {
                    Log.e(this.mTag, "Could not finalize native handle", e);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final T zzblo() {
        synchronized (this.mLock) {
            T t = this.zzlhv;
            if (t != null) {
                return t;
            }
            try {
                this.zzlhv = zza(DynamiteModule.zza(this.mContext, DynamiteModule.zzhdm, "com.google.android.gms.vision.dynamite"), this.mContext);
            } catch (RemoteException | DynamiteModule.zzc e) {
                Log.e(this.mTag, "Error creating remote native handle", e);
            }
            boolean z = this.zzlhu;
            if (!z && this.zzlhv == null) {
                Log.w(this.mTag, "Native handle not yet available. Reverting to no-op handle.");
                this.zzlhu = true;
            } else if (z && this.zzlhv != null) {
                Log.w(this.mTag, "Native handle is now available.");
            }
            T t2 = this.zzlhv;
            return t2;
        }
    }
}
