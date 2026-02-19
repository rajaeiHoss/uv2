package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public final class zzo extends zzl<DriveId> implements SearchableCollectionMetadataField<DriveId> {
    public static final zzg zzgyw = new zzp();

    public zzo(int i) {
        super("parents", Collections.emptySet(), Arrays.asList(new String[]{"parentsExtra", "dbInstanceId", "parentsExtraHolder"}), 4100000);
    }

    /* access modifiers changed from: private */
    public static void zzd(DataHolder dataHolder) {
        Bundle zzahs = dataHolder.zzahs();
        if (zzahs != null) {
            synchronized (dataHolder) {
                DataHolder dataHolder2 = (DataHolder) zzahs.getParcelable("parentsExtraHolder");
                if (dataHolder2 != null) {
                    dataHolder2.close();
                    zzahs.remove("parentsExtraHolder");
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Collection<DriveId> zzc(DataHolder dataHolder, int i, int i2) {
        return zzd(dataHolder, i, i2);
    }

    /* access modifiers changed from: protected */
    public final Collection<DriveId> zzd(DataHolder dataHolder, int i, int i2) {
        String str;
        DataHolder dataHolder2 = dataHolder;
        Bundle zzahs = dataHolder.zzahs();
        ArrayList parcelableArrayList = zzahs.getParcelableArrayList("parentsExtra");
        if (parcelableArrayList == null) {
            if (zzahs.getParcelable("parentsExtraHolder") != null) {
                synchronized (dataHolder) {
                    DataHolder dataHolder3 = (DataHolder) dataHolder.zzahs().getParcelable("parentsExtraHolder");
                    if (dataHolder3 != null) {
                        try {
                            int count = dataHolder.getCount();
                            ArrayList arrayList = new ArrayList(count);
                            HashMap hashMap = new HashMap(count);
                            for (int i3 = 0; i3 < count; i3++) {
                                int zzby = dataHolder2.zzby(i3);
                                ParentDriveIdSet parentDriveIdSet = new ParentDriveIdSet();
                                arrayList.add(parentDriveIdSet);
                                hashMap.put(Long.valueOf(dataHolder2.zzb("sqlId", i3, zzby)), parentDriveIdSet);
                            }
                            Bundle zzahs2 = dataHolder3.zzahs();
                            String string = zzahs2.getString("childSqlIdColumn");
                            String string2 = zzahs2.getString("parentSqlIdColumn");
                            String string3 = zzahs2.getString("parentResIdColumn");
                            int count2 = dataHolder3.getCount();
                            for (int i4 = 0; i4 < count2; i4++) {
                                int zzby2 = dataHolder3.zzby(i4);
                                ((ParentDriveIdSet) hashMap.get(Long.valueOf(dataHolder3.zzb(string, i4, zzby2)))).zzgyv.add(new zzq(dataHolder3.zzd(string3, i4, zzby2), dataHolder3.zzb(string2, i4, zzby2), 1));
                            }
                            dataHolder.zzahs().putParcelableArrayList("parentsExtra", arrayList);
                        } finally {
                            dataHolder3.close();
                            str = "parentsExtraHolder";
                            dataHolder.zzahs().remove(str);
                        }
                    }
                }
                parcelableArrayList = zzahs.getParcelableArrayList("parentsExtra");
            }
            if (parcelableArrayList == null) {
                return null;
            }
        }
        return ((ParentDriveIdSet) parcelableArrayList.get(i)).zzab(zzahs.getLong("dbInstanceId"));
    }

    /* access modifiers changed from: protected */
    public final Collection<DriveId> zzo(Bundle bundle) {
        return zzp(bundle);
    }

    /* access modifiers changed from: protected */
    public final Collection<DriveId> zzp(Bundle bundle) {
        Collection zzp = super.zzo(bundle);
        if (zzp == null) {
            return null;
        }
        return new HashSet(zzp);
    }
}
