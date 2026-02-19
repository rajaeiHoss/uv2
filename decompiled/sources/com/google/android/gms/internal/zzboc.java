package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.zzr;

final class zzboc extends zzbnp {
    private /* synthetic */ zzboa zzguo;
    private /* synthetic */ MetadataChangeSet zzgup;
    private /* synthetic */ zzr zzguq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzboc(zzboa zzboa, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, zzr zzr) {
        super(googleApiClient);
        this.zzguo = zzboa;
        this.zzgup = metadataChangeSet;
        this.zzguq = zzr;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        this.zzgup.zzapv().setContext(zzbnq.getContext());
        ((zzbrk) zzbnq.zzalw()).zza(new zzbmb(this.zzguo.zzgul.getDriveId(), this.zzgup.zzapv(), this.zzguo.zzgul.getRequestId(), this.zzguo.zzgul.zzapd(), this.zzguq), (zzbrm) new zzbto(this));
    }
}
