package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.DriveSpace;
import com.google.android.gms.drive.query.internal.zzr;
import com.google.android.gms.drive.query.internal.zzt;
import com.google.android.gms.drive.query.internal.zzx;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Query extends zzbgl {
    public static final Parcelable.Creator<Query> CREATOR = new zzb();
    private List<DriveSpace> zzgrq;
    private final Set<DriveSpace> zzgrr;
    private zzr zzhay;
    private String zzhaz;
    private SortOrder zzhba;
    final List<String> zzhbb;
    final boolean zzhbc;
    final boolean zzhbd;

    public static class Builder {
        private Set<DriveSpace> zzgrr;
        private String zzhaz;
        private SortOrder zzhba;
        private List<String> zzhbb;
        private boolean zzhbc;
        private boolean zzhbd;
        private final List<Filter> zzhbe;

        public Builder() {
            this.zzhbe = new ArrayList();
            this.zzgrr = Collections.emptySet();
        }

        public Builder(Query query) {
            ArrayList arrayList = new ArrayList();
            this.zzhbe = arrayList;
            this.zzgrr = Collections.emptySet();
            arrayList.add(query.getFilter());
            this.zzhaz = query.getPageToken();
            this.zzhba = query.getSortOrder();
            this.zzhbb = query.zzhbb != null ? query.zzhbb : Collections.emptyList();
            this.zzhbc = query.zzhbc;
            this.zzgrr = query.zzard() != null ? query.zzard() : Collections.emptySet();
            this.zzhbd = query.zzhbd;
        }

        public Builder addFilter(Filter filter) {
            zzbq.checkNotNull(filter, "Filter may not be null.");
            if (!(filter instanceof zzt)) {
                this.zzhbe.add(filter);
            }
            return this;
        }

        public Query build() {
            return new Query(new zzr(zzx.zzhcg, (Iterable<Filter>) this.zzhbe), this.zzhaz, this.zzhba, (List) this.zzhbb, this.zzhbc, (Set) this.zzgrr, this.zzhbd);
        }

        @Deprecated
        public Builder setPageToken(String str) {
            this.zzhaz = str;
            return this;
        }

        public Builder setSortOrder(SortOrder sortOrder) {
            this.zzhba = sortOrder;
            return this;
        }
    }

    private Query(zzr zzr, String str, SortOrder sortOrder, List<String> list, boolean z, List<DriveSpace> list2, Set<DriveSpace> set, boolean z2) {
        this.zzhay = zzr;
        this.zzhaz = str;
        this.zzhba = sortOrder;
        this.zzhbb = list;
        this.zzhbc = z;
        this.zzgrq = list2;
        this.zzgrr = set;
        this.zzhbd = z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    Query(zzr zzr, String str, SortOrder sortOrder, List<String> list, boolean z, List<DriveSpace> list2, boolean z2) {
        this(zzr, str, sortOrder, list, z, list2, (Set<DriveSpace>) list2 == null ? Collections.emptySet() : new HashSet(list2), z2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    private Query(zzr zzr, String str, SortOrder sortOrder, List<String> list, boolean z, Set<DriveSpace> set, boolean z2) {
        this(zzr, str, sortOrder, list, z, (List<DriveSpace>) set == null ? Collections.emptyList() : new ArrayList(set), set, z2);
    }

    public Filter getFilter() {
        return this.zzhay;
    }

    @Deprecated
    public String getPageToken() {
        return this.zzhaz;
    }

    public SortOrder getSortOrder() {
        return this.zzhba;
    }

    public String toString() {
        return String.format(Locale.US, "Query[%s,%s,PageToken=%s,Spaces=%s]", new Object[]{this.zzhay, this.zzhba, this.zzhaz, this.zzgrq});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhay, i, false);
        zzbgo.zza(parcel, 3, this.zzhaz, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzhba, i, false);
        zzbgo.zzb(parcel, 5, this.zzhbb, false);
        zzbgo.zza(parcel, 6, this.zzhbc);
        zzbgo.zzc(parcel, 7, this.zzgrq, false);
        zzbgo.zza(parcel, 8, this.zzhbd);
        zzbgo.zzai(parcel, zze);
    }

    public final Set<DriveSpace> zzard() {
        return this.zzgrr;
    }
}
