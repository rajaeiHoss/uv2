package com.google.android.gms.people.protomodel;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FetchBackUpDeviceContactInfoResponseEntity extends zzbgl implements zzd {
    public static final Parcelable.Creator<FetchBackUpDeviceContactInfoResponseEntity> CREATOR = new zze();
    private final List<zzc> zzkfn;
    private List<zza> zzkfo;

    public FetchBackUpDeviceContactInfoResponseEntity(List<zzc> list) {
        this.zzkfn = list;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzd)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return zzbg.equal(zzbec(), ((zzd) obj).zzbec());
    }

    public zzd freeze() {
        return this;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{zzbec()});
    }

    public boolean isDataValid() {
        return true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, zzbec(), false);
        zzbgo.zzai(parcel, zze);
    }

    public final List<zza> zzbec() {
        if (this.zzkfo == null && this.zzkfn != null) {
            this.zzkfo = new ArrayList(this.zzkfn.size());
            for (zzc add : this.zzkfn) {
                this.zzkfo.add(add);
            }
        }
        return this.zzkfo;
    }
}
