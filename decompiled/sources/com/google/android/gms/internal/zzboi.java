package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.drive.DriveFile;

final class zzboi implements zzcl<DriveFile.DownloadProgressListener> {
    private /* synthetic */ long zzguu;
    private /* synthetic */ long zzguv;

    zzboi(zzboh zzboh, long j, long j2) {
        this.zzguu = j;
        this.zzguv = j2;
    }

    public final void zzajh() {
    }

    public final void zzu(DriveFile.DownloadProgressListener downloadProgressListener) {
        downloadProgressListener.onProgress(this.zzguu, this.zzguv);
    }
}
