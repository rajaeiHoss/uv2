package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.wearable.Channel;

final class zzgs extends zzgm<Channel.GetInputStreamResult> {
    private final zzbr zzlvj;

    public zzgs(zzn<Channel.GetInputStreamResult> zzn, zzbr zzbr) {
        super(zzn);
        this.zzlvj = (zzbr) zzbq.checkNotNull(zzbr);
    }

    public final void zza(zzdm zzdm) {
        zzbj zzbj;
        if (zzdm.zzluj != null) {
            zzbj = new zzbj(new ParcelFileDescriptor.AutoCloseInputStream(zzdm.zzluj));
            this.zzlvj.zza(new zzbk(zzbj));
        } else {
            zzbj = null;
        }
        zzav(new zzbg(new Status(zzdm.statusCode), zzbj));
    }
}
