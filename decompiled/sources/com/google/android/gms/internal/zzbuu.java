package com.google.android.gms.internal;

import android.os.Bundle;
import android.util.SparseArray;
import androidx.collection.LongSparseArray;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.zzc;
import com.google.android.gms.drive.metadata.internal.zzg;
import com.google.android.gms.drive.metadata.internal.zzm;
import java.util.Arrays;

public class zzbuu extends zzm<AppVisibleCustomProperties> {
    public static final zzg zzhao = new zzbuv();

    public zzbuu(int i) {
        super("customProperties", Arrays.asList(new String[]{"hasCustomProperties", "sqlId"}), Arrays.asList(new String[]{"customPropertiesExtra", "customPropertiesExtraHolder"}), 5000000);
    }

    /* access modifiers changed from: private */
    public static void zzd(DataHolder dataHolder) {
        Bundle zzahs = dataHolder.zzahs();
        if (zzahs != null) {
            synchronized (dataHolder) {
                DataHolder dataHolder2 = (DataHolder) zzahs.getParcelable("customPropertiesExtraHolder");
                if (dataHolder2 != null) {
                    dataHolder2.close();
                    zzahs.remove("customPropertiesExtraHolder");
                }
            }
        }
    }

    private static AppVisibleCustomProperties zzf(DataHolder dataHolder, int i, int i2) {
        String str;
        DataHolder dataHolder2 = dataHolder;
        Bundle zzahs = dataHolder.zzahs();
        SparseArray sparseParcelableArray = zzahs.getSparseParcelableArray("customPropertiesExtra");
        if (sparseParcelableArray == null) {
            if (zzahs.getParcelable("customPropertiesExtraHolder") != null) {
                synchronized (dataHolder) {
                    DataHolder dataHolder3 = (DataHolder) dataHolder.zzahs().getParcelable("customPropertiesExtraHolder");
                    if (dataHolder3 != null) {
                        try {
                            Bundle zzahs2 = dataHolder3.zzahs();
                            String string = zzahs2.getString("entryIdColumn");
                            String string2 = zzahs2.getString("keyColumn");
                            String string3 = zzahs2.getString("visibilityColumn");
                            String string4 = zzahs2.getString("valueColumn");
                            LongSparseArray longSparseArray = new LongSparseArray();
                            for (int i3 = 0; i3 < dataHolder3.getCount(); i3++) {
                                int zzby = dataHolder3.zzby(i3);
                                long zzb = dataHolder3.zzb(string, i3, zzby);
                                String zzd = dataHolder3.zzd(string2, i3, zzby);
                                int zzc = dataHolder3.zzc(string3, i3, zzby);
                                zzc zzc2 = new zzc(new CustomPropertyKey(zzd, zzc), dataHolder3.zzd(string4, i3, zzby));
                                AppVisibleCustomProperties.zza zza = (AppVisibleCustomProperties.zza) longSparseArray.get(zzb);
                                if (zza == null) {
                                    zza = new AppVisibleCustomProperties.zza();
                                    longSparseArray.put(zzb, zza);
                                }
                                zza.zza(zzc2);
                            }
                            SparseArray sparseArray = new SparseArray();
                            for (int i4 = 0; i4 < dataHolder.getCount(); i4++) {
                                AppVisibleCustomProperties.zza zza2 = (AppVisibleCustomProperties.zza) longSparseArray.get(dataHolder2.zzb("sqlId", i4, dataHolder2.zzby(i4)));
                                if (zza2 != null) {
                                    sparseArray.append(i4, zza2.zzaqw());
                                }
                            }
                            dataHolder.zzahs().putSparseParcelableArray("customPropertiesExtra", sparseArray);
                        } finally {
                            dataHolder3.close();
                            str = "customPropertiesExtraHolder";
                            dataHolder.zzahs().remove(str);
                        }
                    }
                }
                sparseParcelableArray = zzahs.getSparseParcelableArray("customPropertiesExtra");
            }
            if (sparseParcelableArray == null) {
                return AppVisibleCustomProperties.zzgyn;
            }
        }
        return (AppVisibleCustomProperties) sparseParcelableArray.get(i, AppVisibleCustomProperties.zzgyn);
    }

    /* access modifiers changed from: protected */
    public final AppVisibleCustomProperties zzc(DataHolder dataHolder, int i, int i2) {
        return zzf(dataHolder, i, i2);
    }
}
