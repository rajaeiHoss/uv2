package com.google.android.gms.drive.metadata.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbuj;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class MetadataBundle extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<MetadataBundle> CREATOR = new zzj();
    private static final zzal zzgpv = new zzal("MetadataBundle", "");
    private Bundle zzgyt;

    MetadataBundle(Bundle bundle) {
        int i;
        Bundle bundle2 = (Bundle) zzbq.checkNotNull(bundle);
        this.zzgyt = bundle2;
        bundle2.setClassLoader(getClass().getClassLoader());
        ArrayList arrayList = new ArrayList();
        Iterator it = this.zzgyt.keySet().iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            String str = (String) it.next();
            if (zzf.zzhg(str) == null) {
                arrayList.add(str);
                zzgpv.zzc("MetadataBundle", "Ignored unknown metadata field in bundle: %s", str);
            }
        }
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            this.zzgyt.remove((String) obj);
        }
    }

    public static MetadataBundle zzaqz() {
        return new MetadataBundle(new Bundle());
    }

    public static <T> MetadataBundle zzb(MetadataField<T> metadataField, T t) {
        MetadataBundle zzaqz = zzaqz();
        zzaqz.zzc(metadataField, t);
        return zzaqz;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        MetadataBundle metadataBundle = (MetadataBundle) obj;
        Set<String> keySet = this.zzgyt.keySet();
        if (!keySet.equals(metadataBundle.zzgyt.keySet())) {
            return false;
        }
        for (String str : keySet) {
            if (!zzbg.equal(this.zzgyt.get(str), metadataBundle.zzgyt.get(str))) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (String str : this.zzgyt.keySet()) {
            i = (i * 31) + this.zzgyt.get(str).hashCode();
        }
        return i;
    }

    public final void setContext(Context context) {
        BitmapTeleporter bitmapTeleporter = (BitmapTeleporter) zza(zzbuj.zzhac);
        if (bitmapTeleporter != null) {
            bitmapTeleporter.setTempDir(context.getCacheDir());
        }
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzgyt);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
        sb.append("MetadataBundle [values=");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgyt, false);
        zzbgo.zzai(parcel, zze);
    }

    public final <T> T zza(MetadataField<T> metadataField) {
        return metadataField.zzn(this.zzgyt);
    }

    public final MetadataBundle zzara() {
        return new MetadataBundle(new Bundle(this.zzgyt));
    }

    public final Set<MetadataField<?>> zzarb() {
        HashSet hashSet = new HashSet();
        for (String zzhg : this.zzgyt.keySet()) {
            hashSet.add(zzf.zzhg(zzhg));
        }
        return hashSet;
    }

    public final <T> T zzc(MetadataField<T> metadataField) {
        T zza = zza(metadataField);
        this.zzgyt.remove(metadataField.getName());
        return zza;
    }

    public final <T> void zzc(MetadataField<T> metadataField, T t) {
        if (zzf.zzhg(metadataField.getName()) == null) {
            String valueOf = String.valueOf(metadataField.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Unregistered field: ".concat(valueOf) : new String("Unregistered field: "));
        } else {
            metadataField.zza(t, this.zzgyt);
        }
    }

    public final boolean zzd(MetadataField<?> metadataField) {
        return this.zzgyt.containsKey(metadataField.getName());
    }
}
