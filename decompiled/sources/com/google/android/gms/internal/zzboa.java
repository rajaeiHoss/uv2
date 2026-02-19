package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.zzc;
import com.google.android.gms.drive.zzr;
import com.google.android.gms.drive.zzt;
import java.io.InputStream;
import java.io.OutputStream;

public final class zzboa implements DriveContents {
    /* access modifiers changed from: private */
    public static final zzal zzgpv = new zzal("DriveContentsImpl", "");
    private boolean mClosed = false;
    /* access modifiers changed from: private */
    public final zzc zzgul;
    private boolean zzgum = false;
    private boolean zzgun = false;

    public zzboa(zzc zzc) {
        this.zzgul = (zzc) zzbq.checkNotNull(zzc);
    }

    private final PendingResult<Status> zza(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, zzr zzr) {
        if (zzr == null) {
            zzr = (zzr) new zzt().build();
        }
        if (this.zzgul.getMode() == 268435456) {
            throw new IllegalStateException("Cannot commit contents opened with MODE_READ_ONLY");
        } else if (!ExecutionOptions.zzcq(zzr.zzapq()) || this.zzgul.zzapd()) {
            zzr.zzf(googleApiClient);
            if (this.mClosed) {
                throw new IllegalStateException("DriveContents already closed.");
            } else if (getDriveId() != null) {
                if (metadataChangeSet == null) {
                    metadataChangeSet = MetadataChangeSet.zzgqo;
                }
                zzapm();
                return googleApiClient.zze(new zzboc(this, googleApiClient, metadataChangeSet, zzr));
            } else {
                throw new IllegalStateException("Only DriveContents obtained through DriveFile.open can be committed.");
            }
        } else {
            throw new IllegalStateException("DriveContents must be valid for conflict detection.");
        }
    }

    public final PendingResult<Status> commit(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet) {
        return zza(googleApiClient, metadataChangeSet, (zzr) null);
    }

    public final PendingResult<Status> commit(GoogleApiClient googleApiClient, MetadataChangeSet metadataChangeSet, ExecutionOptions executionOptions) {
        return zza(googleApiClient, metadataChangeSet, executionOptions == null ? null : zzr.zzb(executionOptions));
    }

    public final void discard(GoogleApiClient googleApiClient) {
        if (!this.mClosed) {
            zzapm();
            ((zzboe) googleApiClient.zze(new zzboe(this, googleApiClient))).setResultCallback(new zzbod(this));
            return;
        }
        throw new IllegalStateException("DriveContents already closed.");
    }

    public final DriveId getDriveId() {
        return this.zzgul.getDriveId();
    }

    public final InputStream getInputStream() {
        if (this.mClosed) {
            throw new IllegalStateException("Contents have been closed, cannot access the input stream.");
        } else if (this.zzgul.getMode() != 268435456) {
            throw new IllegalStateException("getInputStream() can only be used with contents opened with MODE_READ_ONLY.");
        } else if (!this.zzgum) {
            this.zzgum = true;
            return this.zzgul.getInputStream();
        } else {
            throw new IllegalStateException("getInputStream() can only be called once per Contents instance.");
        }
    }

    public final int getMode() {
        return this.zzgul.getMode();
    }

    public final OutputStream getOutputStream() {
        if (this.mClosed) {
            throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
        } else if (this.zzgul.getMode() != 536870912) {
            throw new IllegalStateException("getOutputStream() can only be used with contents opened with MODE_WRITE_ONLY.");
        } else if (!this.zzgun) {
            this.zzgun = true;
            return this.zzgul.getOutputStream();
        } else {
            throw new IllegalStateException("getOutputStream() can only be called once per Contents instance.");
        }
    }

    public final ParcelFileDescriptor getParcelFileDescriptor() {
        if (!this.mClosed) {
            return this.zzgul.getParcelFileDescriptor();
        }
        throw new IllegalStateException("Contents have been closed, cannot access the output stream.");
    }

    public final PendingResult<DriveApi.DriveContentsResult> reopenForWrite(GoogleApiClient googleApiClient) {
        if (this.mClosed) {
            throw new IllegalStateException("DriveContents already closed.");
        } else if (this.zzgul.getMode() == 268435456) {
            zzapm();
            return googleApiClient.zzd(new zzbob(this, googleApiClient));
        } else {
            throw new IllegalStateException("reopenForWrite can only be used with DriveContents opened with MODE_READ_ONLY.");
        }
    }

    public final zzc zzapl() {
        return this.zzgul;
    }

    public final void zzapm() {
        zzp.zza(this.zzgul.getParcelFileDescriptor());
        this.mClosed = true;
    }

    public final boolean zzapn() {
        return this.mClosed;
    }
}
