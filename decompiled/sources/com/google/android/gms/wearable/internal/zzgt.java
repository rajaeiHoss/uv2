package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.wearable.Channel;

final class zzgt extends zzgm<Channel.GetOutputStreamResult> {
    private final zzbr zzlvj;

    public zzgt(zzn<Channel.GetOutputStreamResult> zzn, zzbr zzbr) {
        super(zzn);
        this.zzlvj = (zzbr) zzbq.checkNotNull(zzbr);
    }

    public final void zza(zzdo zzdo) {
        zzbl zzbl;
        if (zzdo.zzluj != null) {
            zzbl = new zzbl(new ParcelFileDescriptor.AutoCloseOutputStream(zzdo.zzluj));
            this.zzlvj.zza(new zzbm(zzbl));
        } else {
            zzbl = null;
        }
        zzav(new zzbh(new Status(zzdo.statusCode), zzbl));
    }
}
