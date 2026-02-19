package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.CreateFileActivityBuilder;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.OpenFileActivityBuilder;
import com.google.android.gms.drive.TransferPreferences;
import com.google.android.gms.drive.query.Query;

@Deprecated
public final class zzbmu implements DriveApi {
    public final PendingResult<DriveApi.DriveIdResult> fetchDriveId(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.zzd(new zzbmz(this, googleApiClient, str));
    }

    public final DriveFolder getAppFolder(GoogleApiClient googleApiClient) {
        zzbnq zzbnq = (zzbnq) googleApiClient.zza(Drive.zzegu);
        if (zzbnq.zzaql()) {
            DriveId zzaqk = zzbnq.zzaqk();
            if (zzaqk != null) {
                return new zzbok(zzaqk);
            }
            return null;
        }
        throw new IllegalStateException("Client is not yet connected");
    }

    public final DriveFolder getRootFolder(GoogleApiClient googleApiClient) {
        zzbnq zzbnq = (zzbnq) googleApiClient.zza(Drive.zzegu);
        if (zzbnq.zzaql()) {
            DriveId zzaqj = zzbnq.zzaqj();
            if (zzaqj != null) {
                return new zzbok(zzaqj);
            }
            return null;
        }
        throw new IllegalStateException("Client is not yet connected");
    }

    public final CreateFileActivityBuilder newCreateFileActivityBuilder() {
        return new CreateFileActivityBuilder();
    }

    public final PendingResult<DriveApi.DriveContentsResult> newDriveContents(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzbmy(this, googleApiClient, DriveFile.MODE_WRITE_ONLY));
    }

    public final OpenFileActivityBuilder newOpenFileActivityBuilder() {
        return new OpenFileActivityBuilder();
    }

    public final PendingResult<DriveApi.MetadataBufferResult> query(GoogleApiClient googleApiClient, Query query) {
        if (query != null) {
            return googleApiClient.zzd(new zzbmv(this, googleApiClient, query));
        }
        throw new IllegalArgumentException("Query must be provided.");
    }

    public final PendingResult<Status> requestSync(GoogleApiClient googleApiClient) {
        return googleApiClient.zze(new zzbna(this, googleApiClient));
    }

    public final PendingResult<Status> zza(GoogleApiClient googleApiClient, TransferPreferences transferPreferences) {
        zzbq.checkNotNull(transferPreferences, "Transfer preferences should not be null.");
        return googleApiClient.zze(new zzbmx(this, googleApiClient, new zzbre(transferPreferences)));
    }

    public final PendingResult<DriveApi.zza> zze(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzbmw(this, googleApiClient));
    }
}
