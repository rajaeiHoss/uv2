package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Channel;
import java.io.InputStream;

final class zzbb extends zzn<Channel.GetInputStreamResult> {
    private /* synthetic */ zzay zzlti;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbb(zzay zzay, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzlti = zzay;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        String zza = this.zzlti.zzeia;
        zzbr zzbr = new zzbr();
        ((zzep) zzhg.zzalw()).zza((zzek) new zzgs(this, zzbr), (zzei) zzbr, zza);
    }

    public final /* synthetic */ Channel.GetInputStreamResult zzb(Status status) {
        return new zzbg(status, (InputStream) null);
    }
}
