package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.drive.DriveFile;

final class zzboh implements DriveFile.DownloadProgressListener {
    private final zzci<DriveFile.DownloadProgressListener> zzgut;

    public zzboh(zzci<DriveFile.DownloadProgressListener> zzci) {
        this.zzgut = zzci;
    }

    public final void onProgress(long j, long j2) {
        this.zzgut.zza(new zzboi(this, j, j2));
    }
}
