package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class InstrumentInfo extends zzbgl {
    public static final int CARD_CLASS_CREDIT = 1;
    public static final int CARD_CLASS_DEBIT = 2;
    public static final int CARD_CLASS_PREPAID = 3;
    public static final int CARD_CLASS_UNKNOWN = 0;
    public static final Parcelable.Creator<InstrumentInfo> CREATOR = new zzp();
    private String zzlky;
    private String zzlkz;
    private int zzlla;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CardClass {
    }

    private InstrumentInfo() {
    }

    public InstrumentInfo(String str, String str2, int i) {
        this.zzlky = str;
        this.zzlkz = str2;
        this.zzlla = i;
    }

    public final int getCardClass() {
        int i = this.zzlla;
        if (i == 1 || i == 2 || i == 3) {
            return i;
        }
        return 0;
    }

    public final String getInstrumentDetails() {
        return this.zzlkz;
    }

    public final String getInstrumentType() {
        return this.zzlky;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getInstrumentType(), false);
        zzbgo.zza(parcel, 3, getInstrumentDetails(), false);
        zzbgo.zzc(parcel, 4, getCardClass());
        zzbgo.zzai(parcel, zze);
    }
}
