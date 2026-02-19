package com.google.android.gms.internal;

import android.content.Context;
import java.util.List;

public final class zzfan implements Runnable {
    private final Context mContext;
    private final List<byte[]> zzgmi;
    private final long zzosl;

    public zzfan(Context context, List<byte[]> list, long j) {
        this.mContext = context;
        this.zzgmi = list;
        this.zzosl = j;
    }

    public final void run() {
        zzdyx.zza(this.mContext, "frc", this.zzgmi, 1, new zzdyw(), this.zzosl);
    }
}
