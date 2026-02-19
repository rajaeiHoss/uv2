package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.zzo;

final class zzbol extends zzboq {
    private /* synthetic */ MetadataChangeSet zzguw;
    private /* synthetic */ int zzgux;
    private /* synthetic */ int zzguy;
    private /* synthetic */ zzo zzguz;
    private /* synthetic */ zzbok zzgva;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbol(zzbok zzbok, GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, int i, int i2, zzo zzo) {
        super(googleApiClient);
        this.zzgva = zzbok;
        this.zzguw = metadataChangeSet;
        this.zzgux = i;
        this.zzguy = i2;
        this.zzguz = zzo;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        this.zzguw.zzapv().setContext(zzbnq.getContext());
        ((zzbrk) zzbnq.zzalw()).zza(new zzbml(this.zzgva.getDriveId(), this.zzguw.zzapv(), this.zzgux, this.zzguy, this.zzguz), (zzbrm) new zzbon(this));
    }
}
