package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class LoyaltyPointsBalance extends zzbgl {
    public static final Parcelable.Creator<LoyaltyPointsBalance> CREATOR = new zzh();
    String zzlkt;
    int zzlqa;
    String zzlqb;
    double zzlqc;
    long zzlqd;
    int zzlqe;

    public final class Builder {
        private Builder() {
        }

        public final LoyaltyPointsBalance build() {
            return LoyaltyPointsBalance.this;
        }

        public final Builder setDouble(double d) {
            LoyaltyPointsBalance.this.zzlqc = d;
            LoyaltyPointsBalance.this.zzlqe = 2;
            return this;
        }

        public final Builder setInt(int i) {
            LoyaltyPointsBalance.this.zzlqa = i;
            LoyaltyPointsBalance.this.zzlqe = 0;
            return this;
        }

        public final Builder setMoney(String str, long j) {
            LoyaltyPointsBalance.this.zzlkt = str;
            LoyaltyPointsBalance.this.zzlqd = j;
            LoyaltyPointsBalance.this.zzlqe = 3;
            return this;
        }

        public final Builder setString(String str) {
            LoyaltyPointsBalance.this.zzlqb = str;
            LoyaltyPointsBalance.this.zzlqe = 1;
            return this;
        }
    }

    public interface Type {
        public static final int DOUBLE = 2;
        public static final int INT = 0;
        public static final int MONEY = 3;
        public static final int STRING = 1;
        public static final int UNDEFINED = -1;
    }

    LoyaltyPointsBalance() {
        this.zzlqe = -1;
        this.zzlqa = -1;
        this.zzlqc = -1.0d;
    }

    LoyaltyPointsBalance(int i, String str, double d, String str2, long j, int i2) {
        this.zzlqa = i;
        this.zzlqb = str;
        this.zzlqc = d;
        this.zzlkt = str2;
        this.zzlqd = j;
        this.zzlqe = i2;
    }

    public static Builder newBuilder() {
        return new LoyaltyPointsBalance().new Builder();
    }

    public final String getCurrencyCode() {
        return this.zzlkt;
    }

    public final long getCurrencyMicros() {
        return this.zzlqd;
    }

    public final double getDouble() {
        return this.zzlqc;
    }

    public final int getInt() {
        return this.zzlqa;
    }

    public final String getString() {
        return this.zzlqb;
    }

    public final int getType() {
        return this.zzlqe;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzlqa);
        zzbgo.zza(parcel, 3, this.zzlqb, false);
        zzbgo.zza(parcel, 4, this.zzlqc);
        zzbgo.zza(parcel, 5, this.zzlkt, false);
        zzbgo.zza(parcel, 6, this.zzlqd);
        zzbgo.zzc(parcel, 7, this.zzlqe);
        zzbgo.zzai(parcel, zze);
    }
}
