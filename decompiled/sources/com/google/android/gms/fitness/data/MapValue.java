package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class MapValue extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<MapValue> CREATOR = new zzw();
    private final int format;
    private final float value;

    public MapValue(int i, float f) {
        this.format = i;
        this.value = f;
    }

    public final float asFloat() {
        zzbq.zza(this.format == 2, (Object) "Value is not in float format");
        return this.value;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MapValue)) {
            return false;
        }
        MapValue mapValue = (MapValue) obj;
        int i = this.format;
        if (i == mapValue.format) {
            if (i != 2) {
                return this.value == mapValue.value;
            }
            if (asFloat() == mapValue.asFloat()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (int) this.value;
    }

    public String toString() {
        return this.format != 2 ? "unknown" : Float.toString(asFloat());
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.format);
        zzbgo.zza(parcel, 2, this.value);
        zzbgo.zzai(parcel, zze);
    }
}
