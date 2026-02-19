package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wearable.MessageApi;

final class zzhe extends zzgm<MessageApi.SendMessageResult> {
    public zzhe(zzn<MessageApi.SendMessageResult> zzn) {
        super(zzn);
    }

    public final void zza(zzga zzga) {
        zzav(new zzey(zzgd.zzdg(zzga.statusCode), zzga.zzino));
    }
}
