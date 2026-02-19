package com.google.firebase.database.connection.idl;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzefn;
import java.util.List;

final class zzak extends zzbgl {
    public static final Parcelable.Creator<zzak> CREATOR = new zzal();
    private List<String> zznbx;
    private List<String> zznby;

    public zzak(List<String> list, List<String> list2) {
        this.zznbx = list;
        this.zznby = list2;
    }

    public static zzefn zza(zzak zzak, Object obj) {
        return new zzefn(zzak.zznbx, zzak.zznby, obj);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzb(parcel, 2, this.zznbx, false);
        zzbgo.zzb(parcel, 3, this.zznby, false);
        zzbgo.zzai(parcel, zze);
    }
}
