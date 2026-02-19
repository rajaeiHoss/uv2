package com.google.android.gms.location.places.internal;

import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.internal.zzbgp;
import com.google.android.gms.internal.zzbgq;
import com.google.android.gms.internal.zzdoh;
import com.google.android.gms.internal.zzflr;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class zzaw extends zzc {
    public zzaw(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    private final byte[] zzb(String str, byte[] bArr) {
        if (!zzgj(str) || zzgl(str)) {
            return null;
        }
        return getByteArray(str);
    }

    /* access modifiers changed from: protected */
    public final float zza(String str, float f) {
        return (!zzgj(str) || zzgl(str)) ? f : getFloat(str);
    }

    /* access modifiers changed from: protected */
    public final <E extends zzbgp> E zza(String str, Parcelable.Creator<E> creator) {
        byte[] zzb = zzb(str, (byte[]) null);
        if (zzb == null) {
            return null;
        }
        return zzbgq.zza(zzb, creator);
    }

    /* access modifiers changed from: protected */
    public final <E extends zzbgp> List<E> zza(String str, Parcelable.Creator<E> creator, List<E> list) {
        byte[] zzb = zzb(str, (byte[]) null);
        if (zzb == null) {
            return list;
        }
        try {
            zzdoh zzae = zzdoh.zzae(zzb);
            if (zzae.zzlyk == null) {
                return list;
            }
            ArrayList arrayList = new ArrayList(zzae.zzlyk.length);
            for (byte[] zza : zzae.zzlyk) {
                arrayList.add(zzbgq.zza(zza, creator));
            }
            return arrayList;
        } catch (zzflr e) {
            if (Log.isLoggable("SafeDataBufferRef", 6)) {
                Log.e("SafeDataBufferRef", "Cannot parse byte[]", e);
            }
            return list;
        }
    }

    /* access modifiers changed from: protected */
    public final String zzad(String str, String str2) {
        return (!zzgj(str) || zzgl(str)) ? str2 : getString(str);
    }

    /* access modifiers changed from: protected */
    public final List<Integer> zzc(String str, List<Integer> list) {
        byte[] zzb = zzb(str, (byte[]) null);
        if (zzb == null) {
            return list;
        }
        try {
            zzdoh zzae = zzdoh.zzae(zzb);
            if (zzae.zzlyj == null) {
                return list;
            }
            ArrayList arrayList = new ArrayList(zzae.zzlyj.length);
            for (int valueOf : zzae.zzlyj) {
                arrayList.add(Integer.valueOf(valueOf));
            }
            return arrayList;
        } catch (zzflr e) {
            if (Log.isLoggable("SafeDataBufferRef", 6)) {
                Log.e("SafeDataBufferRef", "Cannot parse byte[]", e);
            }
            return list;
        }
    }

    /* access modifiers changed from: protected */
    public final List<String> zzd(String str, List<String> list) {
        byte[] zzb = zzb(str, (byte[]) null);
        if (zzb == null) {
            return list;
        }
        try {
            zzdoh zzae = zzdoh.zzae(zzb);
            return zzae.zzlyi == null ? list : Arrays.asList(zzae.zzlyi);
        } catch (zzflr e) {
            if (Log.isLoggable("SafeDataBufferRef", 6)) {
                Log.e("SafeDataBufferRef", "Cannot parse byte[]", e);
            }
            return list;
        }
    }

    /* access modifiers changed from: protected */
    public final int zzy(String str, int i) {
        return (!zzgj(str) || zzgl(str)) ? i : getInteger(str);
    }
}
