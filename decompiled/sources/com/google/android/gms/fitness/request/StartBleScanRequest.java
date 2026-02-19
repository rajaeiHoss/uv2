package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;
import java.util.Collections;
import java.util.List;

public class StartBleScanRequest extends zzbgl {
    public static final Parcelable.Creator<StartBleScanRequest> CREATOR = new zzbg();
    private final List<DataType> zzhhz;
    private final zzbzt zzhnu;
    private final zzae zzhpq;
    private final int zzhpr;

    public static class Builder {
        /* access modifiers changed from: private */
        public DataType[] zzhoo = new DataType[0];
        /* access modifiers changed from: private */
        public zzae zzhpq;
        /* access modifiers changed from: private */
        public int zzhpr = 10;

        public StartBleScanRequest build() {
            zzbq.zza(this.zzhpq != null, (Object) "Must set BleScanCallback");
            return new StartBleScanRequest(this);
        }

        public Builder setBleScanCallback(BleScanCallback bleScanCallback) {
            this.zzhpq = zzd.zzasf().zza(bleScanCallback);
            return this;
        }

        public Builder setDataTypes(DataType... dataTypeArr) {
            this.zzhoo = dataTypeArr;
            return this;
        }

        public Builder setTimeoutSecs(int i) {
            boolean z = true;
            zzbq.checkArgument(i > 0, "Stop time must be greater than zero");
            if (i > 60) {
                z = false;
            }
            zzbq.checkArgument(z, "Stop time must be less than 1 minute");
            this.zzhpr = i;
            return this;
        }
    }

    private StartBleScanRequest(Builder builder) {
        this((List<DataType>) zzb.zza(builder.zzhoo), builder.zzhpq, builder.zzhpr, (zzbzt) null);
    }

    public StartBleScanRequest(StartBleScanRequest startBleScanRequest, zzbzt zzbzt) {
        this(startBleScanRequest.zzhhz, startBleScanRequest.zzhpq, startBleScanRequest.zzhpr, zzbzt);
    }

    StartBleScanRequest(List<DataType> list, IBinder iBinder, int i, IBinder iBinder2) {
        zzae zzae;
        this.zzhhz = list;
        if (iBinder == null) {
            zzae = null;
        } else {
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.request.IBleScanCallback");
            zzae = queryLocalInterface instanceof zzae ? (zzae) queryLocalInterface : new zzag(iBinder);
        }
        this.zzhpq = zzae;
        this.zzhpr = i;
        this.zzhnu = zzbzu.zzba(iBinder2);
    }

    public StartBleScanRequest(List<DataType> list, zzae zzae, int i, zzbzt zzbzt) {
        this.zzhhz = list;
        this.zzhpq = zzae;
        this.zzhpr = i;
        this.zzhnu = zzbzt;
    }

    public List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.zzhhz);
    }

    public int getTimeoutSecs() {
        return this.zzhpr;
    }

    public String toString() {
        return com.google.android.gms.common.internal.zzbg.zzx(this).zzg("dataTypes", this.zzhhz).zzg("timeoutSecs", Integer.valueOf(this.zzhpr)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getDataTypes(), false);
        zzbgo.zza(parcel, 2, this.zzhpq.asBinder(), false);
        zzbgo.zzc(parcel, 3, getTimeoutSecs());
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 4, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
