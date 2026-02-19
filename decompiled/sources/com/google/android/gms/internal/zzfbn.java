package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StorageException;
import java.io.InputStream;
import org.json.JSONObject;

public final class zzfbn {
    private Exception zzleq;
    private zzfbi zzoxv;
    private int zzoxw;
    private Exception zzoxx;

    public zzfbn(zzfbi zzfbi) {
        this.zzoxv = zzfbi;
    }

    private final void zza(Exception exc, String str) {
        Log.e("NetworkRequestProxy", str, exc);
        this.zzleq = exc;
    }

    public final Exception getException() {
        try {
            Exception exc = this.zzoxx;
            if (exc != null) {
                return exc;
            }
            Exception exc2 = this.zzleq;
            return exc2 != null ? exc2 : (Exception) zzn.zzy(this.zzoxv.zzcor());
        } catch (RemoteException e) {
            zza((Exception) e, "getException failed with a RemoteException");
            return null;
        }
    }

    public final int getResultCode() {
        try {
            int i = this.zzoxw;
            return i != 0 ? i : this.zzoxv.getResultCode();
        } catch (RemoteException e) {
            zza((Exception) e, "getResultCode failed with a RemoteException");
            return 0;
        }
    }

    public final InputStream getStream() {
        try {
            return (InputStream) zzn.zzy(this.zzoxv.zzcoo());
        } catch (RemoteException e) {
            zza((Exception) e, "getStream failed with a RemoteException");
            return null;
        }
    }

    public final void reset() {
        try {
            this.zzoxw = 0;
            this.zzoxx = null;
            this.zzoxv.reset();
        } catch (RemoteException e) {
            zza((Exception) e, "reset failed with a RemoteException");
        }
    }

    public final <TResult> void zza(TaskCompletionSource<TResult> taskCompletionSource, TResult tresult) {
        Exception exception = getException();
        if (!zzcos() || exception != null) {
            taskCompletionSource.setException(StorageException.fromExceptionAndHttpCode(exception, getResultCode()));
        } else {
            taskCompletionSource.setResult(tresult);
        }
    }

    public final void zzbo(String str, String str2) {
        try {
            this.zzoxv.zzbo(str, str2);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(str);
            Log.e("NetworkRequestProxy", valueOf.length() != 0 ? "Caught remote exception setting custom header:".concat(valueOf) : new String("Caught remote exception setting custom header:"), e);
        }
    }

    public final void zzcon() {
        try {
            zzfbi zzfbi = this.zzoxv;
            if (zzfbi != null) {
                zzfbi.zzcon();
            }
        } catch (RemoteException e) {
            zza((Exception) e, "performRequestEnd failed with a RemoteException");
        }
    }

    public final String zzcoq() {
        try {
            this.zzoxv.zzcoq();
            return null;
        } catch (RemoteException e) {
            zza((Exception) e, "getRawResult failed with a RemoteException");
            return null;
        }
    }

    public final boolean zzcos() {
        try {
            if (this.zzoxw == -2) {
                return false;
            }
            if (this.zzoxx != null) {
                return false;
            }
            return this.zzoxv.zzcos();
        } catch (RemoteException e) {
            zza((Exception) e, "isResultSuccess failed with a RemoteException");
            return false;
        }
    }

    public final int zzcot() {
        try {
            return this.zzoxv.zzcot();
        } catch (RemoteException e) {
            zza((Exception) e, "getResultingContentLength failed with a RemoteException");
            return 0;
        }
    }

    public final JSONObject zzcov() throws RemoteException {
        return (JSONObject) zzn.zzy(this.zzoxv.zzcop());
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0026 A[Catch:{ RemoteException -> 0x002d }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0027 A[Catch:{ RemoteException -> 0x002d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zze(java.lang.String r2, android.content.Context r3) {
        /*
            r1 = this;
            java.lang.String r0 = "connectivity"
            java.lang.Object r3 = r3.getSystemService(r0)     // Catch:{ RemoteException -> 0x002d }
            android.net.ConnectivityManager r3 = (android.net.ConnectivityManager) r3     // Catch:{ RemoteException -> 0x002d }
            android.net.NetworkInfo r3 = r3.getActiveNetworkInfo()     // Catch:{ RemoteException -> 0x002d }
            if (r3 == 0) goto L_0x0017
            boolean r3 = r3.isConnected()     // Catch:{ RemoteException -> 0x002d }
            if (r3 != 0) goto L_0x0015
            goto L_0x0017
        L_0x0015:
            r3 = 1
            goto L_0x0024
        L_0x0017:
            r3 = -2
            r1.zzoxw = r3     // Catch:{ RemoteException -> 0x002d }
            java.net.SocketException r3 = new java.net.SocketException     // Catch:{ RemoteException -> 0x002d }
            java.lang.String r0 = "Network subsystem is unavailable"
            r3.<init>(r0)     // Catch:{ RemoteException -> 0x002d }
            r1.zzoxx = r3     // Catch:{ RemoteException -> 0x002d }
            r3 = 0
        L_0x0024:
            if (r3 != 0) goto L_0x0027
            return
        L_0x0027:
            com.google.android.gms.internal.zzfbi r3 = r1.zzoxv     // Catch:{ RemoteException -> 0x002d }
            r3.zzsr(r2)     // Catch:{ RemoteException -> 0x002d }
            return
        L_0x002d:
            java.lang.Exception r2 = r1.zzleq
            java.lang.String r3 = "performRequest failed with a RemoteException"
            r1.zza((java.lang.Exception) r2, (java.lang.String) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfbn.zze(java.lang.String, android.content.Context):void");
    }

    public final void zzss(String str) {
        try {
            this.zzoxv.zzss(str);
        } catch (RemoteException e) {
            zza((Exception) e, "performRequestStart failed with a RemoteException");
        }
    }

    public final String zzst(String str) {
        try {
            return this.zzoxv.zzst(str);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(str);
            Log.e("NetworkRequestProxy", valueOf.length() != 0 ? "getResultString failed with a RemoteException:".concat(valueOf) : new String("getResultString failed with a RemoteException:"), e);
            return null;
        }
    }
}
