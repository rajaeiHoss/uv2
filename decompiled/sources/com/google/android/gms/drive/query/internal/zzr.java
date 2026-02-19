package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.List;

public final class zzr extends zza {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    private List<Filter> zzhbe;
    private zzx zzhbk;
    private List<FilterHolder> zzhbz;

    public zzr(zzx zzx, Filter filter, Filter... filterArr) {
        this.zzhbk = zzx;
        ArrayList arrayList = new ArrayList(filterArr.length + 1);
        this.zzhbz = arrayList;
        arrayList.add(new FilterHolder(filter));
        ArrayList arrayList2 = new ArrayList(filterArr.length + 1);
        this.zzhbe = arrayList2;
        arrayList2.add(filter);
        for (Filter filter2 : filterArr) {
            this.zzhbz.add(new FilterHolder(filter2));
            this.zzhbe.add(filter2);
        }
    }

    public zzr(zzx zzx, Iterable<Filter> iterable) {
        this.zzhbk = zzx;
        this.zzhbe = new ArrayList();
        this.zzhbz = new ArrayList();
        for (Filter next : iterable) {
            this.zzhbe.add(next);
            this.zzhbz.add(new FilterHolder(next));
        }
    }

    zzr(zzx zzx, List<FilterHolder> list) {
        this.zzhbk = zzx;
        this.zzhbz = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhbk, i, false);
        zzbgo.zzc(parcel, 2, this.zzhbz, false);
        zzbgo.zzai(parcel, zze);
    }

    public final <T> T zza(zzj<T> zzj) {
        ArrayList arrayList = new ArrayList();
        for (FilterHolder filter : this.zzhbz) {
            arrayList.add(filter.getFilter().zza(zzj));
        }
        return zzj.zza(this.zzhbk, (List<T>) arrayList);
    }
}
