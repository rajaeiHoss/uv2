package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.List;

public class AutocompleteFilter extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<AutocompleteFilter> CREATOR = new zzc();
    public static final int TYPE_FILTER_ADDRESS = 2;
    public static final int TYPE_FILTER_CITIES = 5;
    public static final int TYPE_FILTER_ESTABLISHMENT = 34;
    public static final int TYPE_FILTER_GEOCODE = 1007;
    public static final int TYPE_FILTER_NONE = 0;
    public static final int TYPE_FILTER_REGIONS = 4;
    private int zzehz;
    private boolean zzivc;
    private List<Integer> zzivd;
    private String zzive;
    private int zzivf;

    public static final class Builder {
        private boolean zzivc = false;
        private String zzive = "";
        private int zzivf = 0;

        public final AutocompleteFilter build() {
            return new AutocompleteFilter(1, false, Arrays.asList(new Integer[]{Integer.valueOf(this.zzivf)}), this.zzive);
        }

        public final Builder setCountry(String str) {
            this.zzive = str;
            return this;
        }

        public final Builder setTypeFilter(int i) {
            this.zzivf = i;
            return this;
        }
    }

    AutocompleteFilter(int i, boolean z, List<Integer> list, String str) {
        this.zzehz = i;
        this.zzivd = list;
        this.zzivf = (list == null || list.isEmpty()) ? 0 : list.iterator().next().intValue();
        this.zzive = str;
        if (this.zzehz <= 0) {
            this.zzivc = !z;
        } else {
            this.zzivc = z;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AutocompleteFilter)) {
            return false;
        }
        AutocompleteFilter autocompleteFilter = (AutocompleteFilter) obj;
        return this.zzivf == autocompleteFilter.zzivf && this.zzivc == autocompleteFilter.zzivc && this.zzive == autocompleteFilter.zzive;
    }

    public int getTypeFilter() {
        return this.zzivf;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.zzivc), Integer.valueOf(this.zzivf), this.zzive});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("includeQueryPredictions", Boolean.valueOf(this.zzivc)).zzg("typeFilter", Integer.valueOf(this.zzivf)).zzg("country", this.zzive).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzivc);
        zzbgo.zza(parcel, 2, this.zzivd, false);
        zzbgo.zza(parcel, 3, this.zzive, false);
        zzbgo.zzc(parcel, 1000, this.zzehz);
        zzbgo.zzai(parcel, zze);
    }
}
