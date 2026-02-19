package com.google.android.gms.internal;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzbmp extends Metadata {
    private final MetadataBundle zzgtl;

    public zzbmp(MetadataBundle metadataBundle) {
        this.zzgtl = metadataBundle;
    }

    public final Metadata freeze() {
        return new zzbmp(this.zzgtl.zzara());
    }

    public final boolean isDataValid() {
        return this.zzgtl != null;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzgtl);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 17);
        sb.append("Metadata [mImpl=");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    public final <T> T zza(MetadataField<T> metadataField) {
        return this.zzgtl.zza(metadataField);
    }
}
