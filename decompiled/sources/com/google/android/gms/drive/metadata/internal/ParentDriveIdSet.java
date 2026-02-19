package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParentDriveIdSet extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<ParentDriveIdSet> CREATOR = new zzn();
    final List<zzq> zzgyv;

    public ParentDriveIdSet() {
        this(new ArrayList());
    }

    ParentDriveIdSet(List<zzq> list) {
        this.zzgyv = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzgyv, false);
        zzbgo.zzai(parcel, zze);
    }

    public final Set<DriveId> zzab(long j) {
        HashSet hashSet = new HashSet();
        for (zzq next : this.zzgyv) {
            hashSet.add(new DriveId(next.zzgpw, next.zzgpx, j, next.zzgpy));
        }
        return hashSet;
    }
}
