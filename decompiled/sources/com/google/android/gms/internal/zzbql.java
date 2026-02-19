package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.zzj;
import java.util.ArrayList;
import java.util.Set;

public class zzbql implements DriveResource {
    protected final DriveId zzgpe;

    public zzbql(DriveId driveId) {
        this.zzgpe = driveId;
    }

    public PendingResult<Status> addChangeListener(GoogleApiClient googleApiClient, ChangeListener changeListener) {
        return ((zzbnq) googleApiClient.zza(Drive.zzegu)).zza(googleApiClient, this.zzgpe, changeListener);
    }

    public PendingResult<Status> addChangeSubscription(GoogleApiClient googleApiClient) {
        zzbnq zzbnq = (zzbnq) googleApiClient.zza(Drive.zzegu);
        zzbly zzbly = new zzbly(1, this.zzgpe);
        zzbq.checkArgument(zzj.zza(zzbly.zzgjw, zzbly.zzgpe));
        zzbq.zza(zzbnq.isConnected(), (Object) "Client must be connected");
        if (zzbnq.zzgtt) {
            return googleApiClient.zze(new zzbnt(zzbnq, googleApiClient, zzbly));
        }
        throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to add event subscriptions");
    }

    public PendingResult<Status> delete(GoogleApiClient googleApiClient) {
        return googleApiClient.zze(new zzbqq(this, googleApiClient));
    }

    public DriveId getDriveId() {
        return this.zzgpe;
    }

    public PendingResult<DriveResource.MetadataResult> getMetadata(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzbqm(this, googleApiClient, false));
    }

    public PendingResult<DriveApi.MetadataBufferResult> listParents(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzbqn(this, googleApiClient));
    }

    public PendingResult<Status> removeChangeListener(GoogleApiClient googleApiClient, ChangeListener changeListener) {
        return ((zzbnq) googleApiClient.zza(Drive.zzegu)).zzb(googleApiClient, this.zzgpe, changeListener);
    }

    public PendingResult<Status> removeChangeSubscription(GoogleApiClient googleApiClient) {
        zzbnq zzbnq = (zzbnq) googleApiClient.zza(Drive.zzegu);
        DriveId driveId = this.zzgpe;
        zzbq.checkArgument(zzj.zza(1, driveId));
        zzbq.zza(zzbnq.isConnected(), (Object) "Client must be connected");
        return googleApiClient.zze(new zzbnu(zzbnq, googleApiClient, driveId, 1));
    }

    public PendingResult<Status> setParents(GoogleApiClient googleApiClient, Set<DriveId> set) {
        if (set != null) {
            return googleApiClient.zze(new zzbqo(this, googleApiClient, new ArrayList(set)));
        }
        throw new IllegalArgumentException("ParentIds must be provided.");
    }

    public PendingResult<Status> trash(GoogleApiClient googleApiClient) {
        return googleApiClient.zze(new zzbqr(this, googleApiClient));
    }

    public PendingResult<Status> untrash(GoogleApiClient googleApiClient) {
        return googleApiClient.zze(new zzbqs(this, googleApiClient));
    }

    public PendingResult<DriveResource.MetadataResult> updateMetadata(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet) {
        if (metadataChangeSet != null) {
            return googleApiClient.zze(new zzbqp(this, googleApiClient, metadataChangeSet));
        }
        throw new IllegalArgumentException("ChangeSet must be provided.");
    }
}
