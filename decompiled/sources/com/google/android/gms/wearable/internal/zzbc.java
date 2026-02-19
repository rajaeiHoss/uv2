package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Channel;
import java.io.OutputStream;

final class zzbc extends zzn<Channel.GetOutputStreamResult> {
    private /* synthetic */ zzay zzlti;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbc(zzay zzay, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzlti = zzay;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        String zza = this.zzlti.zzeia;
        zzbr zzbr = new zzbr();
        ((zzep) zzhg.zzalw()).zzb((zzek) new zzgt(this, zzbr), (zzei) zzbr, zza);
    }

    public final /* synthetic */ Channel.GetOutputStreamResult zzb(Status status) {
        return new zzbh(status, (OutputStream) null);
    }
}
