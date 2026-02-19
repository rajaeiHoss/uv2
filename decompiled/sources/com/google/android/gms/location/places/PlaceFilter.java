package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class PlaceFilter extends zza {
    public static final Parcelable.Creator<PlaceFilter> CREATOR = new zzh();
    private static final PlaceFilter zzivj = new PlaceFilter();
    private List<Integer> zzivk;
    private boolean zzivl;
    private List<zzo> zzivm;
    private List<String> zzivn;
    private final Set<Integer> zzivo;
    private final Set<zzo> zzivp;
    private final Set<String> zzivq;

    @Deprecated
    public static final class zza {
        private boolean zzivl;
        private Collection<Integer> zzivr;
        private Collection<zzo> zzivs;
        private String[] zzivt;

        private zza() {
            this.zzivr = null;
            this.zzivl = false;
            this.zzivs = null;
            this.zzivt = null;
        }
    }

    public PlaceFilter() {
        this(false, (Collection<String>) null);
    }

    private PlaceFilter(Collection<Integer> collection, boolean z, Collection<String> collection2, Collection<zzo> collection3) {
        this((List<Integer>) zzj((Collection) null), z, zzj(collection2), (List<zzo>) zzj((Collection) null));
    }

    PlaceFilter(List<Integer> list, boolean z, List<String> list2, List<zzo> list3) {
        this.zzivk = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
        this.zzivl = z;
        this.zzivm = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.zzivn = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.zzivo = zzae(this.zzivk);
        this.zzivp = zzae(this.zzivm);
        this.zzivq = zzae(this.zzivn);
    }

    public PlaceFilter(boolean z, Collection<String> collection) {
        this((Collection<Integer>) null, z, collection, (Collection<zzo>) null);
    }

    @Deprecated
    public static PlaceFilter zzaxd() {
        new zza();
        return new PlaceFilter((List<Integer>) null, false, (List<String>) null, (List<zzo>) null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceFilter)) {
            return false;
        }
        PlaceFilter placeFilter = (PlaceFilter) obj;
        return this.zzivo.equals(placeFilter.zzivo) && this.zzivl == placeFilter.zzivl && this.zzivp.equals(placeFilter.zzivp) && this.zzivq.equals(placeFilter.zzivq);
    }

    public final Set<String> getPlaceIds() {
        return this.zzivq;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzivo, Boolean.valueOf(this.zzivl), this.zzivp, this.zzivq});
    }

    public final boolean isRestrictedToPlacesOpenNow() {
        return this.zzivl;
    }

    public final String toString() {
        zzbi zzx = zzbg.zzx(this);
        if (!this.zzivo.isEmpty()) {
            zzx.zzg("types", this.zzivo);
        }
        zzx.zzg("requireOpenNow", Boolean.valueOf(this.zzivl));
        if (!this.zzivq.isEmpty()) {
            zzx.zzg("placeIds", this.zzivq);
        }
        if (!this.zzivp.isEmpty()) {
            zzx.zzg("requestedUserDataTypes", this.zzivp);
        }
        return zzx.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzivk, false);
        zzbgo.zza(parcel, 3, this.zzivl);
        zzbgo.zzc(parcel, 4, this.zzivm, false);
        zzbgo.zzb(parcel, 6, this.zzivn, false);
        zzbgo.zzai(parcel, zze);
    }
}
