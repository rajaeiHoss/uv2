package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbyn;
import com.google.android.gms.internal.zzbyo;
import java.util.Arrays;
import java.util.List;

public class DataSourcesRequest extends zzbgl {
    public static final Parcelable.Creator<DataSourcesRequest> CREATOR = new zzp();
    private final List<DataType> zzhhz;
    private final List<Integer> zzhol;
    private final boolean zzhom;
    private final zzbyn zzhon;

    public static class Builder {
        private boolean zzhom = false;
        /* access modifiers changed from: private */
        public DataType[] zzhoo = new DataType[0];
        /* access modifiers changed from: private */
        public int[] zzhop = {0, 1};

        public DataSourcesRequest build() {
            boolean z = true;
            zzbq.zza(this.zzhoo.length > 0, (Object) "Must add at least one data type");
            if (this.zzhop.length <= 0) {
                z = false;
            }
            zzbq.zza(z, (Object) "Must add at least one data source type");
            return new DataSourcesRequest(this);
        }

        public Builder setDataSourceTypes(int... iArr) {
            this.zzhop = iArr;
            return this;
        }

        public Builder setDataTypes(DataType... dataTypeArr) {
            this.zzhoo = dataTypeArr;
            return this;
        }
    }

    private DataSourcesRequest(Builder builder) {
        this((List<DataType>) zzb.zza(builder.zzhoo), (List<Integer>) Arrays.asList(zzb.zzb(builder.zzhop)), false, (zzbyn) null);
    }

    public DataSourcesRequest(DataSourcesRequest dataSourcesRequest, zzbyn zzbyn) {
        this(dataSourcesRequest.zzhhz, dataSourcesRequest.zzhol, dataSourcesRequest.zzhom, zzbyn);
    }

    DataSourcesRequest(List<DataType> list, List<Integer> list2, boolean z, IBinder iBinder) {
        this.zzhhz = list;
        this.zzhol = list2;
        this.zzhom = z;
        this.zzhon = zzbyo.zzau(iBinder);
    }

    private DataSourcesRequest(List<DataType> list, List<Integer> list2, boolean z, zzbyn zzbyn) {
        this.zzhhz = list;
        this.zzhol = list2;
        this.zzhom = z;
        this.zzhon = zzbyn;
    }

    public List<DataType> getDataTypes() {
        return this.zzhhz;
    }

    public String toString() {
        zzbi zzg = zzbg.zzx(this).zzg("dataTypes", this.zzhhz).zzg("sourceTypes", this.zzhol);
        if (this.zzhom) {
            zzg.zzg("includeDbOnlySources", "true");
        }
        return zzg.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getDataTypes(), false);
        zzbgo.zza(parcel, 2, this.zzhol, false);
        zzbgo.zza(parcel, 3, this.zzhom);
        zzbyn zzbyn = this.zzhon;
        zzbgo.zza(parcel, 4, zzbyn == null ? null : zzbyn.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
