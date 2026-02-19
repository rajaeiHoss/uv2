package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Collection;

public final class LabelValueRow extends zzbgl {
    public static final Parcelable.Creator<LabelValueRow> CREATOR = new zze();
    String zzlpu;
    String zzlpv;
    ArrayList<LabelValue> zzlpw;

    public final class Builder {
        private Builder() {
        }

        public final Builder addColumn(LabelValue labelValue) {
            LabelValueRow.this.zzlpw.add(labelValue);
            return this;
        }

        public final Builder addColumns(Collection<LabelValue> collection) {
            LabelValueRow.this.zzlpw.addAll(collection);
            return this;
        }

        public final LabelValueRow build() {
            return LabelValueRow.this;
        }

        public final Builder setHexBackgroundColor(String str) {
            LabelValueRow.this.zzlpv = str;
            return this;
        }

        public final Builder setHexFontColor(String str) {
            LabelValueRow.this.zzlpu = str;
            return this;
        }
    }

    LabelValueRow() {
        this.zzlpw = new ArrayList<>();
    }

    LabelValueRow(String str, String str2, ArrayList<LabelValue> arrayList) {
        this.zzlpu = str;
        this.zzlpv = str2;
        this.zzlpw = arrayList;
    }

    public static Builder newBuilder() {
        return new LabelValueRow().new Builder();
    }

    public final ArrayList<LabelValue> getColumns() {
        return this.zzlpw;
    }

    public final String getHexBackgroundColor() {
        return this.zzlpv;
    }

    public final String getHexFontColor() {
        return this.zzlpu;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzlpu, false);
        zzbgo.zza(parcel, 3, this.zzlpv, false);
        zzbgo.zzc(parcel, 4, this.zzlpw, false);
        zzbgo.zzai(parcel, zze);
    }
}
