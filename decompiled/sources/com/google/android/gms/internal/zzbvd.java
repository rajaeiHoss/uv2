package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.zzm;
import java.util.Arrays;

public final class zzbvd extends zzm<DriveId> {
    public static final zzbvd zzhav = new zzbvd();

    private zzbvd() {
        super("driveId", Arrays.asList(new String[]{"sqlId", "resourceId", "mimeType"}), Arrays.asList(new String[]{"dbInstanceId"}), 4100000);
    }

    /* access modifiers changed from: protected */
    public final boolean zzb(DataHolder dataHolder, int i, int i2) {
        for (String zzgj : zzaqu()) {
            if (!dataHolder.zzgj(zzgj)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final DriveId zzc(DataHolder dataHolder, int i, int i2) {
        long j = dataHolder.zzahs().getLong("dbInstanceId");
        boolean equals = DriveFolder.MIME_TYPE.equals(dataHolder.zzd(zzbuj.zzgzu.getName(), i, i2));
        String zzd = dataHolder.zzd("resourceId", i, i2);
        return new DriveId("generated-android-null".equals(zzd) ? null : zzd, Long.valueOf(dataHolder.zzb("sqlId", i, i2)).longValue(), j, equals ? 1 : 0);
    }
}
