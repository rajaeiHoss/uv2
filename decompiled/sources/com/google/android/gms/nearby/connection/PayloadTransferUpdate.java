package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

public final class PayloadTransferUpdate extends zzbgl {
    public static final Parcelable.Creator<PayloadTransferUpdate> CREATOR = new zzi();
    /* access modifiers changed from: private */
    public int status;
    /* access modifiers changed from: private */
    public long zzjwr;
    /* access modifiers changed from: private */
    public long zzjws;
    /* access modifiers changed from: private */
    public long zzjwt;

    public static final class Builder {
        private final PayloadTransferUpdate zzjwu;

        public Builder() {
            this.zzjwu = new PayloadTransferUpdate();
        }

        public Builder(PayloadTransferUpdate payloadTransferUpdate) {
            PayloadTransferUpdate payloadTransferUpdate2 = new PayloadTransferUpdate();
            this.zzjwu = payloadTransferUpdate2;
            long unused = payloadTransferUpdate2.zzjwr = payloadTransferUpdate.zzjwr;
            int unused2 = payloadTransferUpdate2.status = payloadTransferUpdate.status;
            long unused3 = payloadTransferUpdate2.zzjws = payloadTransferUpdate.zzjws;
            long unused4 = payloadTransferUpdate2.zzjwt = payloadTransferUpdate.zzjwt;
        }

        public final PayloadTransferUpdate build() {
            return this.zzjwu;
        }

        public final Builder setBytesTransferred(long j) {
            long unused = this.zzjwu.zzjwt = j;
            return this;
        }

        public final Builder setPayloadId(long j) {
            long unused = this.zzjwu.zzjwr = j;
            return this;
        }

        public final Builder setStatus(int i) {
            int unused = this.zzjwu.status = i;
            return this;
        }

        public final Builder setTotalBytes(long j) {
            long unused = this.zzjwu.zzjws = j;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
        public static final int CANCELED = 4;
        public static final int FAILURE = 2;
        public static final int IN_PROGRESS = 3;
        public static final int SUCCESS = 1;
    }

    private PayloadTransferUpdate() {
    }

    public PayloadTransferUpdate(long j, int i, long j2, long j3) {
        this.zzjwr = j;
        this.status = i;
        this.zzjws = j2;
        this.zzjwt = j3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PayloadTransferUpdate) {
            PayloadTransferUpdate payloadTransferUpdate = (PayloadTransferUpdate) obj;
            return zzbg.equal(Long.valueOf(this.zzjwr), Long.valueOf(payloadTransferUpdate.zzjwr)) && zzbg.equal(Integer.valueOf(this.status), Integer.valueOf(payloadTransferUpdate.status)) && zzbg.equal(Long.valueOf(this.zzjws), Long.valueOf(payloadTransferUpdate.zzjws)) && zzbg.equal(Long.valueOf(this.zzjwt), Long.valueOf(payloadTransferUpdate.zzjwt));
        }
        return false;
    }

    public final long getBytesTransferred() {
        return this.zzjwt;
    }

    public final long getPayloadId() {
        return this.zzjwr;
    }

    public final int getStatus() {
        return this.status;
    }

    public final long getTotalBytes() {
        return this.zzjws;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzjwr), Integer.valueOf(this.status), Long.valueOf(this.zzjws), Long.valueOf(this.zzjwt)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getPayloadId());
        zzbgo.zzc(parcel, 2, getStatus());
        zzbgo.zza(parcel, 3, getTotalBytes());
        zzbgo.zza(parcel, 4, getBytesTransferred());
        zzbgo.zzai(parcel, zze);
    }
}
