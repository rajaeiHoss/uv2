package com.google.android.gms.drive;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.metadata.internal.zzf;
import com.google.android.gms.internal.zzbmp;
import com.google.android.gms.internal.zzbuj;

public final class MetadataBuffer extends AbstractDataBuffer<Metadata> {
    private zza zzgqm;

    static class zza extends Metadata {
        private final DataHolder zzfxb;
        private final int zzgci;
        /* access modifiers changed from: private */
        public final int zzgqn;

        public zza(DataHolder dataHolder, int i) {
            this.zzfxb = dataHolder;
            this.zzgqn = i;
            this.zzgci = dataHolder.zzby(i);
        }

        public final Metadata freeze() {
            MetadataBundle zzaqz = MetadataBundle.zzaqz();
            for (Object fieldObj : zzf.zzaqx()) {
                MetadataField next = (MetadataField) fieldObj;
                if (next != zzbuj.zzhac) {
                    next.zza(this.zzfxb, zzaqz, this.zzgqn, this.zzgci);
                }
            }
            return new zzbmp(zzaqz);
        }

        public final boolean isDataValid() {
            return !this.zzfxb.isClosed();
        }

        public final <T> T zza(MetadataField<T> metadataField) {
            return metadataField.zza(this.zzfxb, this.zzgqn, this.zzgci);
        }
    }

    public MetadataBuffer(DataHolder dataHolder) {
        super(dataHolder);
        dataHolder.zzahs().setClassLoader(MetadataBuffer.class.getClassLoader());
    }

    public final Metadata get(int i) {
        zza zza2 = this.zzgqm;
        if (zza2 != null && zza2.zzgqn == i) {
            return zza2;
        }
        zza zza3 = new zza(this.zzfxb, i);
        this.zzgqm = zza3;
        return zza3;
    }

    @Deprecated
    public final String getNextPageToken() {
        return null;
    }

    public final void release() {
        if (this.zzfxb != null) {
            zzf.zzb(this.zzfxb);
        }
        super.release();
    }
}
