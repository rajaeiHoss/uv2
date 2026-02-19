package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LocationResult extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<LocationResult> CREATOR = new zzac();
    static final List<Location> zzisl = Collections.emptyList();
    private final List<Location> zzism;

    LocationResult(List<Location> list) {
        this.zzism = list;
    }

    public static LocationResult create(List<Location> list) {
        if (list == null) {
            list = zzisl;
        }
        return new LocationResult(list);
    }

    public static LocationResult extractResult(Intent intent) {
        if (!hasResult(intent)) {
            return null;
        }
        return (LocationResult) intent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
    }

    public static boolean hasResult(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof LocationResult)) {
            return false;
        }
        LocationResult locationResult = (LocationResult) obj;
        if (locationResult.zzism.size() != this.zzism.size()) {
            return false;
        }
        Iterator<Location> it = this.zzism.iterator();
        for (Location time : locationResult.zzism) {
            if (it.next().getTime() != time.getTime()) {
                return false;
            }
        }
        return true;
    }

    public final Location getLastLocation() {
        int size = this.zzism.size();
        if (size == 0) {
            return null;
        }
        return this.zzism.get(size - 1);
    }

    public final List<Location> getLocations() {
        return this.zzism;
    }

    public final int hashCode() {
        int i = 17;
        for (Location time : this.zzism) {
            long time2 = time.getTime();
            i = (i * 31) + ((int) (time2 ^ (time2 >>> 32)));
        }
        return i;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzism);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
        sb.append("LocationResult[locations: ");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getLocations(), false);
        zzbgo.zzai(parcel, zze);
    }
}
