package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;

final class zzbom extends zzbos {
    private /* synthetic */ MetadataChangeSet zzguw;
    private /* synthetic */ zzbok zzgva;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbom(zzbok zzbok, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet) {
        super(googleApiClient);
        this.zzgva = zzbok;
        this.zzguw = metadataChangeSet;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        this.zzguw.zzapv().setContext(zzbnq.getContext());
        ((zzbrk) zzbnq.zzalw()).zza(new zzbmn(this.zzgva.getDriveId(), this.zzguw.zzapv()), (zzbrm) new zzboo(this));
    }
}
