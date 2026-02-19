package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;

final class zzafn implements Runnable {
    private /* synthetic */ zzkk zzaps;
    private /* synthetic */ zzwi zzdai;
    private /* synthetic */ zzafl zzdaj;
    private /* synthetic */ zzaft zzdak;

    zzafn(zzafl zzafl, zzwi zzwi, zzkk zzkk, zzaft zzaft) {
        this.zzdaj = zzafl;
        this.zzdai = zzwi;
        this.zzaps = zzkk;
        this.zzdak = zzaft;
    }

    public final void run() {
        try {
            this.zzdai.zza(zzn.zzz(this.zzdaj.mContext), this.zzaps, (String) null, (zzafz) this.zzdak, this.zzdaj.zzdac);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(this.zzdaj.zzcip);
            zzahw.zzc(valueOf.length() != 0 ? "Fail to initialize adapter ".concat(valueOf) : new String("Fail to initialize adapter "), e);
            zzafl zzafl = this.zzdaj;
            zzafl.zza(zzafl.zzcip, 0);
        }
    }
}
