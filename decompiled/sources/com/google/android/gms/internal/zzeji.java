package com.google.android.gms.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import java.util.List;

final class zzeji implements zzeev, zzejg {
    private /* synthetic */ zzeir zznie;
    private final zzelv zznir;
    /* access modifiers changed from: private */
    public final zzejk zznis;

    public zzeji(zzeir zzeir, zzelv zzelv) {
        this.zznie = zzeir;
        this.zznir = zzelv;
        this.zznis = zzeir.zze(zzelv.zzcbi());
    }

    public final List<? extends zzell> zzb(DatabaseError databaseError) {
        if (databaseError == null) {
            zzelu zzcbi = this.zznir.zzcbi();
            zzejk zzejk = this.zznis;
            return zzejk != null ? this.zznie.zza(zzejk) : this.zznie.zzs(zzcbi.zzbvh());
        }
        zzemm zza = this.zznie.zzmxz;
        String valueOf = String.valueOf(this.zznir.zzcbi().zzbvh());
        String databaseError2 = databaseError.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 19 + String.valueOf(databaseError2).length());
        sb.append("Listen at ");
        sb.append(valueOf);
        sb.append(" failed: ");
        sb.append(databaseError2);
        zza.zzf(sb.toString(), (Throwable) null);
        return this.zznie.zza(this.zznir.zzcbi(), databaseError);
    }

    public final String zzbwq() {
        return this.zznir.zzcbj().zzccc();
    }

    public final boolean zzbwr() {
        return zzeoy.zzn(this.zznir.zzcbj()) > PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
    }

    public final zzeel zzbws() {
        zzemx zzh = zzemx.zzh(this.zznir.zzcbj());
        List<zzegu> zzbwi = zzh.zzbwi();
        ArrayList arrayList = new ArrayList(zzbwi.size());
        for (zzegu zzbyp : zzbwi) {
            arrayList.add(zzbyp.zzbyp());
        }
        return new zzeel(arrayList, zzh.zzbwj());
    }
}
