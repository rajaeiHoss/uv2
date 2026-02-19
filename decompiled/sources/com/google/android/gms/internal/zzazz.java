package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.awareness.state.TimeIntervals;

public final class zzazz extends zzbgl implements TimeIntervals {
    public static final Parcelable.Creator<zzazz> CREATOR = new zzbaa();
    private final int[] zzesd;

    public zzazz(int[] iArr) {
        this.zzesd = iArr;
    }

    public final int[] getTimeIntervals() {
        return this.zzesd;
    }

    public final boolean hasTimeInterval(int i) {
        int[] iArr = this.zzesd;
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("TimeIntervals=");
        if (this.zzesd == null) {
            str = "unknown";
        } else {
            sb.append("[");
            int[] iArr = this.zzesd;
            int length = iArr.length;
            boolean z = true;
            int i = 0;
            while (i < length) {
                int i2 = iArr[i];
                if (!z) {
                    sb.append(", ");
                }
                sb.append(i2);
                i++;
                z = false;
            }
            str = "]";
        }
        sb.append(str);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getTimeIntervals(), false);
        zzbgo.zzai(parcel, zze);
    }
}
