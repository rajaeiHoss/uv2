package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.OnChangeListener;
import com.google.android.gms.drive.events.zzj;

final class zzbqe {
    private OnChangeListener zzgwa;
    private DriveId zzgwb;
    /* access modifiers changed from: private */
    public zzbra zzgwc;

    zzbqe(zzboz zzboz, OnChangeListener onChangeListener, DriveId driveId) {
        zzbq.checkState(zzj.zza(1, driveId));
        this.zzgwa = onChangeListener;
        this.zzgwb = driveId;
        Looper looper = zzboz.getLooper();
        Context applicationContext = zzboz.getApplicationContext();
        onChangeListener.getClass();
        zzbra zzbra = new zzbra(looper, applicationContext, 1, zzbqf.zza(onChangeListener));
        this.zzgwc = zzbra;
        zzbra.zzcv(1);
    }
}
