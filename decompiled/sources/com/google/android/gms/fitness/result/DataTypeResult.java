package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class DataTypeResult extends zzbgl implements Result {
    public static final Parcelable.Creator<DataTypeResult> CREATOR = new zze();
    private final Status zzefs;
    private final DataType zzhhj;

    public DataTypeResult(Status status, DataType dataType) {
        this.zzefs = status;
        this.zzhhj = dataType;
    }

    public static DataTypeResult zzaf(Status status) {
        return new DataTypeResult(status, (DataType) null);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DataTypeResult) {
                DataTypeResult dataTypeResult = (DataTypeResult) obj;
                if (this.zzefs.equals(dataTypeResult.zzefs) && zzbg.equal(this.zzhhj, dataTypeResult.zzhhj)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public DataType getDataType() {
        return this.zzhhj;
    }

    public Status getStatus() {
        return this.zzefs;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzefs, this.zzhhj});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("status", this.zzefs).zzg("dataType", this.zzhhj).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getStatus(), i, false);
        zzbgo.zza(parcel, 3, (Parcelable) getDataType(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
