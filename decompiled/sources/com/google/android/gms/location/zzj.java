package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import kotlin.jvm.internal.LongCompanionObject;

public final class zzj extends zzbgl {
    public static final Parcelable.Creator<zzj> CREATOR = new zzk();
    private boolean zzirh;
    private long zziri;
    private float zzirj;
    private long zzirk;
    private int zzirl;

    public zzj() {
        this(true, 50, 0.0f, LongCompanionObject.MAX_VALUE, Integer.MAX_VALUE);
    }

    zzj(boolean z, long j, float f, long j2, int i) {
        this.zzirh = z;
        this.zziri = j;
        this.zzirj = f;
        this.zzirk = j2;
        this.zzirl = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzj)) {
            return false;
        }
        zzj zzj = (zzj) obj;
        return this.zzirh == zzj.zzirh && this.zziri == zzj.zziri && Float.compare(this.zzirj, zzj.zzirj) == 0 && this.zzirk == zzj.zzirk && this.zzirl == zzj.zzirl;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.zzirh), Long.valueOf(this.zziri), Float.valueOf(this.zzirj), Long.valueOf(this.zzirk), Integer.valueOf(this.zzirl)});
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DeviceOrientationRequest[mShouldUseMag=");
        sb.append(this.zzirh);
        sb.append(" mMinimumSamplingPeriodMs=");
        sb.append(this.zziri);
        sb.append(" mSmallestAngleChangeRadians=");
        sb.append(this.zzirj);
        long j = this.zzirk;
        if (j != LongCompanionObject.MAX_VALUE) {
            sb.append(" expireIn=");
            sb.append(j - SystemClock.elapsedRealtime());
            sb.append("ms");
        }
        if (this.zzirl != Integer.MAX_VALUE) {
            sb.append(" num=");
            sb.append(this.zzirl);
        }
        sb.append(']');
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzirh);
        zzbgo.zza(parcel, 2, this.zziri);
        zzbgo.zza(parcel, 3, this.zzirj);
        zzbgo.zza(parcel, 4, this.zzirk);
        zzbgo.zzc(parcel, 5, this.zzirl);
        zzbgo.zzai(parcel, zze);
    }
}
