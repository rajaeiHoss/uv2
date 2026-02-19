package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi;

final class zzak extends zzn<ChannelApi.OpenChannelResult> {
    private /* synthetic */ String zzcfe;
    private /* synthetic */ String zzlsy;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzak(zzaj zzaj, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient);
        this.zzlsy = str;
        this.zzcfe = str2;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        ((zzep) zzhg.zzalw()).zza((zzek) new zzha(this), this.zzlsy, this.zzcfe);
    }

    public final /* synthetic */ ChannelApi.OpenChannelResult zzb(Status status) {
        return new zzam(status, (Channel) null);
    }
}
