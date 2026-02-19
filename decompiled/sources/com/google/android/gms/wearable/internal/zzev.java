package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.MessageApi;

final class zzev extends zzn<MessageApi.SendMessageResult> {
    private /* synthetic */ String val$action;
    private /* synthetic */ byte[] zzlbv;
    private /* synthetic */ String zzlsy;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzev(zzeu zzeu, GoogleApiClient googleApiClient, String str, String str2, byte[] bArr) {
        super(googleApiClient);
        this.zzlsy = str;
        this.val$action = str2;
        this.zzlbv = bArr;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        ((zzep) zzhg.zzalw()).zza(new zzhe(this), this.zzlsy, this.val$action, this.zzlbv);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ MessageApi.SendMessageResult zzb(Status status) {
        return new zzey(status, -1);
    }
}
