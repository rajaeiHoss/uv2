package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.query.internal.zzf;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SortOrder extends zzbgl {
    public static final Parcelable.Creator<SortOrder> CREATOR = new zzc();
    private List<zzf> zzhbh;
    private boolean zzhbi;

    public static class Builder {
        private final List<zzf> zzhbh = new ArrayList();
        private boolean zzhbi = false;

        public Builder addSortAscending(SortableMetadataField sortableMetadataField) {
            this.zzhbh.add(new zzf(sortableMetadataField.getName(), true));
            return this;
        }

        public Builder addSortDescending(SortableMetadataField sortableMetadataField) {
            this.zzhbh.add(new zzf(sortableMetadataField.getName(), false));
            return this;
        }

        public SortOrder build() {
            return new SortOrder(this.zzhbh, false);
        }
    }

    SortOrder(List<zzf> list, boolean z) {
        this.zzhbh = list;
        this.zzhbi = z;
    }

    public String toString() {
        return String.format(Locale.US, "SortOrder[%s, %s]", new Object[]{TextUtils.join(",", this.zzhbh), Boolean.valueOf(this.zzhbi)});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzhbh, false);
        zzbgo.zza(parcel, 2, this.zzhbi);
        zzbgo.zzai(parcel, zze);
    }
}
