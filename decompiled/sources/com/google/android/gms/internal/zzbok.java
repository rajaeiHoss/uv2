package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.internal.zzk;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;
import com.google.android.gms.drive.zzo;
import com.google.android.gms.drive.zzq;

public final class zzbok extends zzbql implements DriveFolder {
    public zzbok(DriveId driveId) {
        super(driveId);
    }

    static int zza(DriveContents driveContents, zzk zzk) {
        if (driveContents == null) {
            return (zzk == null || !zzk.zzarc()) ? 1 : 0;
        }
        int requestId = driveContents.zzapl().getRequestId();
        driveContents.zzapm();
        return requestId;
    }

    private final PendingResult<DriveFolder.DriveFileResult> zza(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, DriveContents driveContents, zzo zzo) {
        if (zzo == null) {
            zzo = (zzo) new zzq().build();
        }
        zzo zzo2 = zzo;
        if (metadataChangeSet != null) {
            zzk zzhh = zzk.zzhh(metadataChangeSet.getMimeType());
            if (zzhh == null || !zzhh.isFolder()) {
                zzo2.zzf(googleApiClient);
                if (driveContents != null) {
                    if (!(driveContents instanceof zzboa)) {
                        throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
                    } else if (driveContents.getDriveId() != null) {
                        throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
                    } else if (driveContents.zzapn()) {
                        throw new IllegalArgumentException("DriveContents are already closed.");
                    }
                }
                int zza = zza(driveContents, zzk.zzhh(metadataChangeSet.getMimeType()));
                String zzapt = zzo2.zzapt();
                if (zzapt != null) {
                    metadataChangeSet = zza(metadataChangeSet, zzapt);
                }
                MetadataChangeSet metadataChangeSet2 = metadataChangeSet;
                zzk zzhh2 = zzk.zzhh(metadataChangeSet2.getMimeType());
                return googleApiClient.zze(new zzbol(this, googleApiClient, metadataChangeSet2, zza, (zzhh2 == null || !zzhh2.zzarc()) ? 0 : 1, zzo2));
            }
            throw new IllegalArgumentException("May not create folders using this method. Use DriveFolder.createFolder() instead of mime type application/vnd.google-apps.folder");
        }
        throw new IllegalArgumentException("MetadataChangeSet must be provided.");
    }

    static MetadataChangeSet zza(MetadataChangeSet metadataChangeSet, String str) {
        return metadataChangeSet.zza(zzbuj.zzhah, str);
    }

    static Query zza(Query query, DriveId driveId) {
        Query.Builder addFilter = new Query.Builder().addFilter(Filters.in(SearchableField.PARENTS, driveId));
        if (query != null) {
            if (query.getFilter() != null) {
                addFilter.addFilter(query.getFilter());
            }
            addFilter.setPageToken(query.getPageToken());
            addFilter.setSortOrder(query.getSortOrder());
        }
        return addFilter.build();
    }

    static void zzb(MetadataChangeSet metadataChangeSet) {
        if (metadataChangeSet != null) {
            zzk zzhh = zzk.zzhh(metadataChangeSet.getMimeType());
            if (zzhh != null) {
                if (!(!zzhh.zzarc() && !zzhh.isFolder())) {
                    throw new IllegalArgumentException("May not create shortcut files using this method. Use DriveFolder.createShortcutFile() instead.");
                }
                return;
            }
            return;
        }
        throw new IllegalArgumentException("MetadataChangeSet must be provided.");
    }

    public final PendingResult<DriveFolder.DriveFileResult> createFile(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, DriveContents driveContents) {
        zzb(metadataChangeSet);
        return zza(googleApiClient, metadataChangeSet, driveContents, (zzo) null);
    }

    public final PendingResult<DriveFolder.DriveFileResult> createFile(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, DriveContents driveContents, ExecutionOptions executionOptions) {
        zzb(metadataChangeSet);
        return zza(googleApiClient, metadataChangeSet, driveContents, zzo.zza(executionOptions));
    }

    public final PendingResult<DriveFolder.DriveFolderResult> createFolder(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet) {
        if (metadataChangeSet == null) {
            throw new IllegalArgumentException("MetadataChangeSet must be provided.");
        } else if (metadataChangeSet.getMimeType() == null || metadataChangeSet.getMimeType().equals(DriveFolder.MIME_TYPE)) {
            return googleApiClient.zze(new zzbom(this, googleApiClient, metadataChangeSet));
        } else {
            throw new IllegalArgumentException("The mimetype must be of type application/vnd.google-apps.folder");
        }
    }

    public final PendingResult<DriveApi.MetadataBufferResult> listChildren(GoogleApiClient googleApiClient) {
        return queryChildren(googleApiClient, (Query) null);
    }

    public final PendingResult<DriveApi.MetadataBufferResult> queryChildren(GoogleApiClient googleApiClient, Query query) {
        return new zzbmu().query(googleApiClient, zza(query, getDriveId()));
    }
}
