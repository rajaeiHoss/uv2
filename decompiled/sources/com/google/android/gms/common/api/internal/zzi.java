package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbq;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zzi extends zzo {
    private final SparseArray<zza> zzfuf = new SparseArray<>();

    class zza implements GoogleApiClient.OnConnectionFailedListener {
        public final int zzfug;
        public final GoogleApiClient zzfuh;
        public final GoogleApiClient.OnConnectionFailedListener zzfui;

        public zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.zzfug = i;
            this.zzfuh = googleApiClient;
            this.zzfui = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public final void onConnectionFailed(ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("beginFailureResolution for ");
            sb.append(valueOf);
            Log.d("AutoManageHelper", sb.toString());
            zzi.this.zzb(connectionResult, this.zzfug);
        }
    }

    private zzi(zzcf zzcf) {
        super(zzcf);
        this.zzgam.zza("AutoManageHelper", (LifecycleCallback) this);
    }

    public static zzi zza(zzce zzce) {
        zzcf zzb = zzb(zzce);
        zzi zzi = (zzi) zzb.zza("AutoManageHelper", zzi.class);
        return zzi != null ? zzi : new zzi(zzb);
    }

    private final zza zzbr(int i) {
        if (this.zzfuf.size() <= i) {
            return null;
        }
        SparseArray<zza> sparseArray = this.zzfuf;
        return sparseArray.get(sparseArray.keyAt(i));
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.zzfuf.size(); i++) {
            zza zzbr = zzbr(i);
            if (zzbr != null) {
                printWriter.append(str).append("GoogleApiClient #").print(zzbr.zzfug);
                printWriter.println(":");
                zzbr.zzfuh.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
            }
        }
    }

    public final void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(this.zzfuf);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
        sb.append("onStart ");
        sb.append(z);
        sb.append(" ");
        sb.append(valueOf);
        Log.d("AutoManageHelper", sb.toString());
        if (this.zzfut.get() == null) {
            for (int i = 0; i < this.zzfuf.size(); i++) {
                zza zzbr = zzbr(i);
                if (zzbr != null) {
                    zzbr.zzfuh.connect();
                }
            }
        }
    }

    public final void onStop() {
        super.onStop();
        for (int i = 0; i < this.zzfuf.size(); i++) {
            zza zzbr = zzbr(i);
            if (zzbr != null) {
                zzbr.zzfuh.disconnect();
            }
        }
    }

    public final void zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzbq.checkNotNull(googleApiClient, "GoogleApiClient instance cannot be null");
        boolean z = this.zzfuf.indexOfKey(i) < 0;
        StringBuilder sb = new StringBuilder(54);
        sb.append("Already managing a GoogleApiClient with id ");
        sb.append(i);
        zzbq.zza(z, (Object) sb.toString());
        zzp zzp = (zzp) this.zzfut.get();
        boolean z2 = this.mStarted;
        String valueOf = String.valueOf(zzp);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 49);
        sb2.append("starting AutoManage for client ");
        sb2.append(i);
        sb2.append(" ");
        sb2.append(z2);
        sb2.append(" ");
        sb2.append(valueOf);
        Log.d("AutoManageHelper", sb2.toString());
        this.zzfuf.put(i, new zza(i, googleApiClient, onConnectionFailedListener));
        if (this.mStarted && zzp == null) {
            String valueOf2 = String.valueOf(googleApiClient);
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 11);
            sb3.append("connecting ");
            sb3.append(valueOf2);
            Log.d("AutoManageHelper", sb3.toString());
            googleApiClient.connect();
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zza zza2 = this.zzfuf.get(i);
        if (zza2 != null) {
            zzbq(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zza2.zzfui;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzaih() {
        for (int i = 0; i < this.zzfuf.size(); i++) {
            zza zzbr = zzbr(i);
            if (zzbr != null) {
                zzbr.zzfuh.connect();
            }
        }
    }

    public final void zzbq(int i) {
        zza zza2 = this.zzfuf.get(i);
        this.zzfuf.remove(i);
        if (zza2 != null) {
            zza2.zzfuh.unregisterConnectionFailedListener(zza2);
            zza2.zzfuh.disconnect();
        }
    }
}
