package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class Subscription extends zzbgl {
    public static final Parcelable.Creator<Subscription> CREATOR = new zzah();
    private final DataType zzhhj;
    private final DataSource zzhhk;
    private final long zzhlr;
    private final int zzhls;

    public static class zza {
        /* access modifiers changed from: private */
        public DataType zzhhj;
        /* access modifiers changed from: private */
        public DataSource zzhhk;
        /* access modifiers changed from: private */
        public long zzhlr = -1;
        /* access modifiers changed from: private */
        public int zzhls = 2;

        public final zza zza(DataSource dataSource) {
            this.zzhhk = dataSource;
            return this;
        }

        public final zza zza(DataType dataType) {
            this.zzhhj = dataType;
            return this;
        }

        public final Subscription zzasd() {
            DataSource dataSource;
            boolean z = false;
            zzbq.zza((this.zzhhk == null && this.zzhhj == null) ? false : true, (Object) "Must call setDataSource() or setDataType()");
            DataType dataType = this.zzhhj;
            if (dataType == null || (dataSource = this.zzhhk) == null || dataType.equals(dataSource.getDataType())) {
                z = true;
            }
            zzbq.zza(z, (Object) "Specified data type is incompatible with specified data source");
            return new Subscription(this);
        }
    }

    Subscription(DataSource dataSource, DataType dataType, long j, int i) {
        this.zzhhk = dataSource;
        this.zzhhj = dataType;
        this.zzhlr = j;
        this.zzhls = i;
    }

    private Subscription(zza zza2) {
        this.zzhhj = zza2.zzhhj;
        this.zzhhk = zza2.zzhhk;
        this.zzhlr = zza2.zzhlr;
        this.zzhls = zza2.zzhls;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Subscription)) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        return zzbg.equal(this.zzhhk, subscription.zzhhk) && zzbg.equal(this.zzhhj, subscription.zzhhj) && this.zzhlr == subscription.zzhlr && this.zzhls == subscription.zzhls;
    }

    public DataSource getDataSource() {
        return this.zzhhk;
    }

    public DataType getDataType() {
        return this.zzhhj;
    }

    public int hashCode() {
        DataSource dataSource = this.zzhhk;
        return Arrays.hashCode(new Object[]{dataSource, dataSource, Long.valueOf(this.zzhlr), Integer.valueOf(this.zzhls)});
    }

    public String toDebugString() {
        Object[] objArr = new Object[1];
        DataSource dataSource = this.zzhhk;
        objArr[0] = dataSource == null ? this.zzhhj.getName() : dataSource.toDebugString();
        return String.format("Subscription{%s}", objArr);
    }

    public String toString() {
        return zzbg.zzx(this).zzg("dataSource", this.zzhhk).zzg("dataType", this.zzhhj).zzg("samplingIntervalMicros", Long.valueOf(this.zzhlr)).zzg("accuracyMode", Integer.valueOf(this.zzhls)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getDataSource(), i, false);
        zzbgo.zza(parcel, 2, (Parcelable) getDataType(), i, false);
        zzbgo.zza(parcel, 3, this.zzhlr);
        zzbgo.zzc(parcel, 4, this.zzhls);
        zzbgo.zzai(parcel, zze);
    }

    public final DataType zzasc() {
        DataType dataType = this.zzhhj;
        return dataType == null ? this.zzhhk.getDataType() : dataType;
    }
}
