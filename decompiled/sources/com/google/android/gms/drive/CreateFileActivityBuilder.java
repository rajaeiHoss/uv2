package com.google.android.gms.drive;

import android.content.IntentSender;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbmi;
import com.google.android.gms.internal.zzboa;

@Deprecated
public class CreateFileActivityBuilder {
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private final zzbmi zzgpg = new zzbmi(0);
    private DriveContents zzgph;
    private boolean zzgpi;

    public IntentSender build(GoogleApiClient googleApiClient) {
        zzbq.zza(googleApiClient.isConnected(), (Object) "Client must be connected");
        zzapi();
        return this.zzgpg.build(googleApiClient);
    }

    /* access modifiers changed from: package-private */
    public final int getRequestId() {
        return this.zzgpg.getRequestId();
    }

    public CreateFileActivityBuilder setActivityStartFolder(DriveId driveId) {
        this.zzgpg.zza(driveId);
        return this;
    }

    public CreateFileActivityBuilder setActivityTitle(String str) {
        this.zzgpg.setActivityTitle(str);
        return this;
    }

    public CreateFileActivityBuilder setInitialDriveContents(DriveContents driveContents) {
        if (driveContents == null) {
            this.zzgpg.zzct(1);
        } else if (!(driveContents instanceof zzboa)) {
            throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
        } else if (driveContents.getDriveId() != null) {
            throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
        } else if (!driveContents.zzapn()) {
            this.zzgpg.zzct(driveContents.zzapl().zzgpc);
            this.zzgph = driveContents;
        } else {
            throw new IllegalArgumentException("DriveContents are already closed.");
        }
        this.zzgpi = true;
        return this;
    }

    public CreateFileActivityBuilder setInitialMetadata(MetadataChangeSet metadataChangeSet) {
        this.zzgpg.zza(metadataChangeSet);
        return this;
    }

    /* access modifiers changed from: package-private */
    public final MetadataChangeSet zzape() {
        return this.zzgpg.zzape();
    }

    /* access modifiers changed from: package-private */
    public final DriveId zzapf() {
        return this.zzgpg.zzapf();
    }

    /* access modifiers changed from: package-private */
    public final String zzapg() {
        return this.zzgpg.zzapg();
    }

    /* access modifiers changed from: package-private */
    public final int zzaph() {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public final void zzapi() {
        zzbq.zza(this.zzgpi, (Object) "Must call setInitialDriveContents.");
        DriveContents driveContents = this.zzgph;
        if (driveContents != null) {
            driveContents.zzapm();
        }
        this.zzgpg.zzapi();
    }
}
