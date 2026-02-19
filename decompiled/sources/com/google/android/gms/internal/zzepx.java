package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import java.util.List;

public final class zzepx extends zzbgl implements ShortDynamicLink {
    public static final Parcelable.Creator<zzepx> CREATOR = new zzepz();
    private final Uri zznsi;
    private final Uri zznsj;
    private final List<zzepy> zznsk;

    public zzepx(Uri uri, Uri uri2, List<zzepy> list) {
        this.zznsi = uri;
        this.zznsj = uri2;
        this.zznsk = list;
    }

    public final Uri getPreviewLink() {
        return this.zznsj;
    }

    public final Uri getShortLink() {
        return this.zznsi;
    }

    public final List<zzepy> getWarnings() {
        return this.zznsk;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getShortLink(), i, false);
        zzbgo.zza(parcel, 2, (Parcelable) getPreviewLink(), i, false);
        zzbgo.zzc(parcel, 3, getWarnings(), false);
        zzbgo.zzai(parcel, zze);
    }
}
