package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbyq;
import com.google.android.gms.internal.zzbyr;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataTypeCreateRequest extends zzbgl {
    public static final Parcelable.Creator<DataTypeCreateRequest> CREATOR = new zzr();
    private final String name;
    private final List<Field> zzhjk;
    private final zzbyq zzhoq;

    public static class Builder {
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public List<Field> zzhjk = new ArrayList();

        public Builder addField(Field field) {
            if (!this.zzhjk.contains(field)) {
                this.zzhjk.add(field);
            }
            return this;
        }

        public Builder addField(String str, int i) {
            zzbq.checkArgument(str != null && !str.isEmpty(), "Invalid name specified");
            return addField(Field.zzo(str, i));
        }

        public DataTypeCreateRequest build() {
            zzbq.zza(this.name != null, (Object) "Must set the name");
            zzbq.zza(!this.zzhjk.isEmpty(), (Object) "Must specify the data fields");
            return new DataTypeCreateRequest(this);
        }

        public Builder setName(String str) {
            this.name = str;
            return this;
        }
    }

    private DataTypeCreateRequest(Builder builder) {
        this(builder.name, (List<Field>) builder.zzhjk, (zzbyq) null);
    }

    public DataTypeCreateRequest(DataTypeCreateRequest dataTypeCreateRequest, zzbyq zzbyq) {
        this(dataTypeCreateRequest.name, dataTypeCreateRequest.zzhjk, zzbyq);
    }

    DataTypeCreateRequest(String str, List<Field> list, IBinder iBinder) {
        this.name = str;
        this.zzhjk = Collections.unmodifiableList(list);
        this.zzhoq = zzbyr.zzav(iBinder);
    }

    private DataTypeCreateRequest(String str, List<Field> list, zzbyq zzbyq) {
        this.name = str;
        this.zzhjk = Collections.unmodifiableList(list);
        this.zzhoq = zzbyq;
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof DataTypeCreateRequest) {
                DataTypeCreateRequest dataTypeCreateRequest = (DataTypeCreateRequest) obj;
                if (zzbg.equal(this.name, dataTypeCreateRequest.name) && zzbg.equal(this.zzhjk, dataTypeCreateRequest.zzhjk)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public List<Field> getFields() {
        return this.zzhjk;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.name, this.zzhjk});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("name", this.name).zzg("fields", this.zzhjk).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getName(), false);
        zzbgo.zzc(parcel, 2, getFields(), false);
        zzbyq zzbyq = this.zzhoq;
        zzbgo.zza(parcel, 3, zzbyq == null ? null : zzbyq.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
