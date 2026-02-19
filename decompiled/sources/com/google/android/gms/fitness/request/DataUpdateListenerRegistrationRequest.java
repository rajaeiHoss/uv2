package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;
import java.util.Arrays;

public class DataUpdateListenerRegistrationRequest extends zzbgl {
    public static final Parcelable.Creator<DataUpdateListenerRegistrationRequest> CREATOR = new zzv();
    private final PendingIntent zzekk;
    private DataType zzhhj;
    private DataSource zzhhk;
    private final zzbzt zzhnu;

    public static class Builder {
        /* access modifiers changed from: private */
        public PendingIntent zzekk;
        /* access modifiers changed from: private */
        public DataType zzhhj;
        /* access modifiers changed from: private */
        public DataSource zzhhk;

        public DataUpdateListenerRegistrationRequest build() {
            zzbq.zza((this.zzhhk == null && this.zzhhj == null) ? false : true, (Object) "Set either dataSource or dataTYpe");
            zzbq.checkNotNull(this.zzekk, "pendingIntent must be set");
            return new DataUpdateListenerRegistrationRequest(this);
        }

        public Builder setDataSource(DataSource dataSource) throws NullPointerException {
            zzbq.checkNotNull(dataSource);
            this.zzhhk = dataSource;
            return this;
        }

        public Builder setDataType(DataType dataType) {
            zzbq.checkNotNull(dataType);
            this.zzhhj = dataType;
            return this;
        }

        public Builder setPendingIntent(PendingIntent pendingIntent) {
            zzbq.checkNotNull(pendingIntent);
            this.zzekk = pendingIntent;
            return this;
        }
    }

    public DataUpdateListenerRegistrationRequest(DataSource dataSource, DataType dataType, PendingIntent pendingIntent, IBinder iBinder) {
        this.zzhhk = dataSource;
        this.zzhhj = dataType;
        this.zzekk = pendingIntent;
        this.zzhnu = zzbzu.zzba(iBinder);
    }

    private DataUpdateListenerRegistrationRequest(Builder builder) {
        this(builder.zzhhk, builder.zzhhj, builder.zzekk, (IBinder) null);
    }

    public DataUpdateListenerRegistrationRequest(DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest, IBinder iBinder) {
        this(dataUpdateListenerRegistrationRequest.zzhhk, dataUpdateListenerRegistrationRequest.zzhhj, dataUpdateListenerRegistrationRequest.zzekk, iBinder);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof DataUpdateListenerRegistrationRequest) {
                DataUpdateListenerRegistrationRequest dataUpdateListenerRegistrationRequest = (DataUpdateListenerRegistrationRequest) obj;
                if (zzbg.equal(this.zzhhk, dataUpdateListenerRegistrationRequest.zzhhk) && zzbg.equal(this.zzhhj, dataUpdateListenerRegistrationRequest.zzhhj) && zzbg.equal(this.zzekk, dataUpdateListenerRegistrationRequest.zzekk)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public DataSource getDataSource() {
        return this.zzhhk;
    }

    public DataType getDataType() {
        return this.zzhhj;
    }

    public PendingIntent getIntent() {
        return this.zzekk;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhhk, this.zzhhj, this.zzekk});
    }

    public String toString() {
        return zzbg.zzx(this).zzg("dataSource", this.zzhhk).zzg("dataType", this.zzhhj).zzg("pendingIntent", this.zzekk).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getDataSource(), i, false);
        zzbgo.zza(parcel, 2, (Parcelable) getDataType(), i, false);
        zzbgo.zza(parcel, 3, (Parcelable) getIntent(), i, false);
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 4, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
