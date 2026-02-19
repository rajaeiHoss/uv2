package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class zzb extends zzc implements zza {
    public static final Parcelable.Creator<zzb> CREATOR = new com.google.android.gms.games.internal.player.zzc();
    private final String zzice;
    private final String zzicf;
    private final long zzicg;
    private final Uri zzich;
    private final Uri zzici;
    private final Uri zzicj;

    public zzb(zza zza) {
        this.zzice = zza.zzavh();
        this.zzicf = zza.zzavi();
        this.zzicg = zza.zzavj();
        this.zzich = zza.zzavk();
        this.zzici = zza.zzavl();
        this.zzicj = zza.zzavm();
    }

    zzb(String str, String str2, long j, Uri uri, Uri uri2, Uri uri3) {
        this.zzice = str;
        this.zzicf = str2;
        this.zzicg = j;
        this.zzich = uri;
        this.zzici = uri2;
        this.zzicj = uri3;
    }

    static int zza(zza zza) {
        return Arrays.hashCode(new Object[]{zza.zzavh(), zza.zzavi(), Long.valueOf(zza.zzavj()), zza.zzavk(), zza.zzavl(), zza.zzavm()});
    }

    static boolean zza(zza zza, Object obj) {
        if (!(obj instanceof zza)) {
            return false;
        }
        if (zza == obj) {
            return true;
        }
        zza zza2 = (zza) obj;
        return zzbg.equal(zza2.zzavh(), zza.zzavh()) && zzbg.equal(zza2.zzavi(), zza.zzavi()) && zzbg.equal(Long.valueOf(zza2.zzavj()), Long.valueOf(zza.zzavj())) && zzbg.equal(zza2.zzavk(), zza.zzavk()) && zzbg.equal(zza2.zzavl(), zza.zzavl()) && zzbg.equal(zza2.zzavm(), zza.zzavm());
    }

    static String zzb(zza zza) {
        return zzbg.zzx(zza).zzg("GameId", zza.zzavh()).zzg("GameName", zza.zzavi()).zzg("ActivityTimestampMillis", Long.valueOf(zza.zzavj())).zzg("GameIconUri", zza.zzavk()).zzg("GameHiResUri", zza.zzavl()).zzg("GameFeaturedUri", zza.zzavm()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final zza freeze() {
        return this;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzice, false);
        zzbgo.zza(parcel, 2, this.zzicf, false);
        zzbgo.zza(parcel, 3, this.zzicg);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzich, i, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzici, i, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzicj, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzavh() {
        return this.zzice;
    }

    public final String zzavi() {
        return this.zzicf;
    }

    public final long zzavj() {
        return this.zzicg;
    }

    public final Uri zzavk() {
        return this.zzich;
    }

    public final Uri zzavl() {
        return this.zzici;
    }

    public final Uri zzavm() {
        return this.zzicj;
    }
}
