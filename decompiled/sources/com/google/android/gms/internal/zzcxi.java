package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzcxf;
import java.util.ArrayList;

final class zzcxi extends zzcxf.zzf {
    private /* synthetic */ String zzkkk;
    private /* synthetic */ String zzkkm;
    private /* synthetic */ int[] zzkkn;
    private /* synthetic */ int zzkko;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcxi(GoogleApiClient googleApiClient, int[] iArr, int i, String str, String str2) {
        super(googleApiClient);
        this.zzkkn = iArr;
        this.zzkko = i;
        this.zzkkm = str;
        this.zzkkk = str2;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcxs zzcxs) throws RemoteException {
        ArrayList arrayList = new ArrayList();
        for (int valueOf : this.zzkkn) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        zzcxs.zza(this.zzkkr, arrayList, this.zzkko, this.zzkkm, this.zzkkk);
    }
}
