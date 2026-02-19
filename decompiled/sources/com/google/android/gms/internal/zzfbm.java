package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.firebase.FirebaseApp;
import org.json.JSONObject;

public final class zzfbm {
    private static DynamiteModule.zzd zzoxr = DynamiteModule.zzhdm;
    private static final Object zzoxs = new Object();
    private static volatile zzfbm zzoxt;
    private Context mContext;
    private FirebaseApp zzotv;
    private zzfbk zzoxu;

    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzfbm(com.google.firebase.FirebaseApp r4) throws android.os.RemoteException {
        /*
            r3 = this;
            java.lang.String r0 = "NetworkRqFactoryProxy"
            r3.<init>()
            android.content.Context r1 = r4.getApplicationContext()
            r3.mContext = r1
            r3.zzotv = r4
            com.google.android.gms.dynamite.DynamiteModule$zzd r4 = zzoxr     // Catch:{ zzc -> 0x0043 }
            java.lang.String r2 = "com.google.android.gms.firebasestorage"
            com.google.android.gms.dynamite.DynamiteModule r4 = com.google.android.gms.dynamite.DynamiteModule.zza((android.content.Context) r1, (com.google.android.gms.dynamite.DynamiteModule.zzd) r4, (java.lang.String) r2)     // Catch:{ zzc -> 0x0043 }
            java.lang.String r1 = "com.google.firebase.storage.network.NetworkRequestFactoryImpl"
            android.os.IBinder r4 = r4.zzhk(r1)     // Catch:{ zzc -> 0x0043 }
            if (r4 != 0) goto L_0x001f
            r4 = 0
            goto L_0x0033
        L_0x001f:
            java.lang.String r1 = "com.google.firebase.storage.network.INetworkRequestFactory"
            android.os.IInterface r1 = r4.queryLocalInterface(r1)     // Catch:{ zzc -> 0x0043 }
            boolean r2 = r1 instanceof com.google.android.gms.internal.zzfbk     // Catch:{ zzc -> 0x0043 }
            if (r2 == 0) goto L_0x002d
            r4 = r1
            com.google.android.gms.internal.zzfbk r4 = (com.google.android.gms.internal.zzfbk) r4     // Catch:{ zzc -> 0x0043 }
            goto L_0x0033
        L_0x002d:
            com.google.android.gms.internal.zzfbl r1 = new com.google.android.gms.internal.zzfbl     // Catch:{ zzc -> 0x0043 }
            r1.<init>(r4)     // Catch:{ zzc -> 0x0043 }
            r4 = r1
        L_0x0033:
            r3.zzoxu = r4     // Catch:{ zzc -> 0x0043 }
            if (r4 == 0) goto L_0x0038
            return
        L_0x0038:
            java.lang.String r4 = "Unable to load Firebase Storage Network layer."
            android.util.Log.e(r0, r4)
            android.os.RemoteException r4 = new android.os.RemoteException
            r4.<init>()
            throw r4
        L_0x0043:
            r4 = move-exception
            java.lang.String r1 = "NetworkRequestFactoryProxy failed with a RemoteException:"
            android.util.Log.e(r0, r1, r4)
            android.os.RemoteException r4 = new android.os.RemoteException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfbm.<init>(com.google.firebase.FirebaseApp):void");
    }

    private final zzfbn zze(zzfbn zzfbn) {
        zzfbn.zzbo("x-firebase-gmpid", this.zzotv.getOptions().getApplicationId());
        return zzfbn;
    }

    public static zzfbm zzi(FirebaseApp firebaseApp) throws RemoteException {
        if (zzoxt == null) {
            synchronized (zzoxs) {
                if (zzoxt == null) {
                    zzoxt = new zzfbm(firebaseApp);
                }
            }
        }
        return zzoxt;
    }

    public final zzfbn zza(Uri uri, long j) throws RemoteException {
        return zze(new zzfbn(this.zzoxu.zza(uri, zzn.zzz(this.mContext), j)));
    }

    public final zzfbn zza(Uri uri, String str) throws RemoteException {
        return zze(new zzfbn(this.zzoxu.zza(uri, zzn.zzz(this.mContext), str)));
    }

    public final zzfbn zza(Uri uri, String str, byte[] bArr, long j, int i, boolean z) throws RemoteException {
        return zze(new zzfbn(this.zzoxu.zza(uri, zzn.zzz(this.mContext), str, zzn.zzz(bArr), j, i, z)));
    }

    public final zzfbn zza(Uri uri, JSONObject jSONObject) throws RemoteException {
        return zze(new zzfbn(this.zzoxu.zza(uri, zzn.zzz(this.mContext), zzn.zzz(jSONObject))));
    }

    public final zzfbn zza(Uri uri, JSONObject jSONObject, String str) throws RemoteException {
        return zze(new zzfbn(this.zzoxu.zza(uri, zzn.zzz(this.mContext), zzn.zzz(jSONObject), str)));
    }

    public final zzfbn zzb(Uri uri, String str) throws RemoteException {
        return zze(new zzfbn(this.zzoxu.zzb(uri, zzn.zzz(this.mContext), str)));
    }

    public final String zzcou() {
        try {
            return this.zzoxu.zzcou();
        } catch (RemoteException e) {
            Log.e("NetworkRqFactoryProxy", "getBackendAuthority failed with a RemoteException:", e);
            return null;
        }
    }

    public final String zzu(Uri uri) {
        try {
            return this.zzoxu.zzu(uri);
        } catch (RemoteException e) {
            Log.e("NetworkRqFactoryProxy", "getDefaultURL failed with a RemoteException:", e);
            return null;
        }
    }

    public final zzfbn zzv(Uri uri) throws RemoteException {
        return zze(new zzfbn(this.zzoxu.zza(uri, zzn.zzz(this.mContext))));
    }

    public final zzfbn zzw(Uri uri) throws RemoteException {
        return zze(new zzfbn(this.zzoxu.zzb(uri, zzn.zzz(this.mContext))));
    }
}
