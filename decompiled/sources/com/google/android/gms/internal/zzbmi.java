package com.google.android.gms.internal;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;

@Deprecated
public final class zzbmi {
    private String zzesj;
    private DriveId zzgqt;
    private MetadataChangeSet zzgtc;
    private Integer zzgtd;
    private final int zzgte = 0;

    public zzbmi(int i) {
    }

    public final IntentSender build(GoogleApiClient googleApiClient) {
        zzbq.zza(googleApiClient.isConnected(), (Object) "Client must be connected");
        zzapi();
        zzbnq zzbnq = (zzbnq) googleApiClient.zza(Drive.zzegu);
        this.zzgtc.zzapv().setContext(zzbnq.getContext());
        try {
            return ((zzbrk) zzbnq.zzalw()).zza(new zzbmj(this.zzgtc.zzapv(), this.zzgtd.intValue(), this.zzesj, this.zzgqt, 0));
        } catch (RemoteException e) {
            throw new RuntimeException("Unable to connect Drive Play Service", e);
        }
    }

    public final int getRequestId() {
        return this.zzgtd.intValue();
    }

    public final void setActivityTitle(String str) {
        this.zzesj = (String) zzbq.checkNotNull(str);
    }

    public final void zza(DriveId driveId) {
        this.zzgqt = (DriveId) zzbq.checkNotNull(driveId);
    }

    public final void zza(MetadataChangeSet metadataChangeSet) {
        this.zzgtc = (MetadataChangeSet) zzbq.checkNotNull(metadataChangeSet);
    }

    public final MetadataChangeSet zzape() {
        return this.zzgtc;
    }

    public final DriveId zzapf() {
        return this.zzgqt;
    }

    public final String zzapg() {
        return this.zzesj;
    }

    public final void zzapi() {
        zzbq.checkNotNull(this.zzgtc, "Must provide initial metadata via setInitialMetadata.");
        Integer num = this.zzgtd;
        this.zzgtd = Integer.valueOf(num == null ? 0 : num.intValue());
    }

    public final void zzct(int i) {
        this.zzgtd = Integer.valueOf(i);
    }
}
