package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.List;

@Deprecated
public final class AppMetadata extends zzbgl {
    public static final Parcelable.Creator<AppMetadata> CREATOR = new zzd();
    private final List<AppIdentifier> zzjwd;

    public AppMetadata(List<AppIdentifier> list) {
        this.zzjwd = (List) zzbq.checkNotNull(list, "Must specify application identifiers");
        zzbq.zza(list.size(), (Object) "Application identifiers cannot be empty");
    }

    public final List<AppIdentifier> getAppIdentifiers() {
        return this.zzjwd;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getAppIdentifiers(), false);
        zzbgo.zzai(parcel, zze);
    }
}
