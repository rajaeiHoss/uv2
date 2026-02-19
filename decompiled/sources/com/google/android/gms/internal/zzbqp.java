package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;

final class zzbqp extends zzbqw {
    private /* synthetic */ MetadataChangeSet zzguw;
    private /* synthetic */ zzbql zzgwm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbqp(zzbql zzbql, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet) {
        super(zzbql, googleApiClient, (zzbqm) null);
        this.zzgwm = zzbql;
        this.zzguw = metadataChangeSet;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        this.zzguw.zzapv().setContext(zzbnq.getContext());
        ((zzbrk) zzbnq.zzalw()).zza(new zzbtv(this.zzgwm.zzgpe, this.zzguw.zzapv()), (zzbrm) new zzbqu(this));
    }
}
