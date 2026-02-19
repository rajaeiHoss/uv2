package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.DriveFolder;

public final class zzk {
    private String zzgyu;

    private zzk(String str) {
        this.zzgyu = str.toLowerCase();
    }

    public static zzk zzhh(String str) {
        zzbq.checkArgument(str == null || !str.isEmpty());
        if (str == null) {
            return null;
        }
        return new zzk(str);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this.zzgyu.equals(((zzk) obj).zzgyu);
    }

    public final int hashCode() {
        return this.zzgyu.hashCode();
    }

    public final boolean isFolder() {
        return this.zzgyu.equals(DriveFolder.MIME_TYPE);
    }

    public final String toString() {
        return this.zzgyu;
    }

    public final boolean zzarc() {
        return this.zzgyu.startsWith("application/vnd.google-apps");
    }
}
