package com.google.android.gms.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzcxf;

final class zzcxg extends zzcxf.zzb {
    private /* synthetic */ byte[] zzkkj;
    private /* synthetic */ String zzkkk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcxg(GoogleApiClient googleApiClient, byte[] bArr, String str) {
        super(googleApiClient);
        this.zzkkj = bArr;
        this.zzkkk = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcxs zzcxs) throws RemoteException {
        zzcxb zzcxb = this.zzkkr;
        byte[] bArr = this.zzkkj;
        String str = this.zzkkk;
        if (TextUtils.isEmpty(str)) {
            str = zzcxs.zzle("com.google.android.safetynet.ATTEST_API_KEY");
        }
        ((zzcxd) zzcxs.zzalw()).zza(zzcxb, bArr, str);
    }
}
