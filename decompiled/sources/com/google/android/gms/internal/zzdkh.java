package com.google.android.gms.internal;

import com.google.android.gms.internal.zzbj;
import com.google.android.gms.tagmanager.zzdj;
import com.google.android.gms.tagmanager.zzgk;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class zzdkh {
    private static zzbt zza(int i, zzbp zzbp, zzbt[] zzbtArr, Set<Integer> set) throws zzdkp {
        if (set.contains(Integer.valueOf(i))) {
            String valueOf = String.valueOf(set);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 90);
            sb.append("Value cycle detected.  Current value reference: ");
            sb.append(i);
            sb.append(".  Previous value references: ");
            sb.append(valueOf);
            sb.append(".");
            zzna(sb.toString());
        }
        zzbt zzbt = (zzbt) zza(zzbp.zzww, i, "values");
        if (zzbtArr[i] != null) {
            return zzbtArr[i];
        }
        zzbt zzbt2 = null;
        set.add(Integer.valueOf(i));
        int i2 = 0;
        switch (zzbt.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                zzbt2 = zzbt;
                break;
            case 2:
                zzbj.zza zzm = zzm(zzbt);
                zzbt zzl = zzl(zzbt);
                zzl.zzyl = new zzbt[zzm.zzxx.length];
                int[] iArr = zzm.zzxx;
                int length = iArr.length;
                int i3 = 0;
                while (i2 < length) {
                    int i4 = i3 + 1;
                    zzl.zzyl[i3] = zza(iArr[i2], zzbp, zzbtArr, set);
                    i2++;
                    i3 = i4;
                }
                zzbt2 = zzl;
                break;
            case 3:
                zzbt2 = zzl(zzbt);
                zzbj.zza zzm2 = zzm(zzbt);
                if (zzm2.zzxy.length != zzm2.zzxz.length) {
                    int length2 = zzm2.zzxy.length;
                    int length3 = zzm2.zzxz.length;
                    StringBuilder sb2 = new StringBuilder(58);
                    sb2.append("Uneven map keys (");
                    sb2.append(length2);
                    sb2.append(") and map values (");
                    sb2.append(length3);
                    sb2.append(")");
                    zzna(sb2.toString());
                }
                zzbt2.zzym = new zzbt[zzm2.zzxy.length];
                zzbt2.zzyn = new zzbt[zzm2.zzxy.length];
                int[] iArr2 = zzm2.zzxy;
                int length4 = iArr2.length;
                int i5 = 0;
                int i6 = 0;
                while (i5 < length4) {
                    zzbt2.zzym[i6] = zza(iArr2[i5], zzbp, zzbtArr, set);
                    i5++;
                    i6++;
                }
                int[] iArr3 = zzm2.zzxz;
                int length5 = iArr3.length;
                int i7 = 0;
                while (i2 < length5) {
                    zzbt2.zzyn[i7] = zza(iArr3[i2], zzbp, zzbtArr, set);
                    i2++;
                    i7++;
                }
                break;
            case 4:
                zzbt2 = zzl(zzbt);
                zzbt2.zzyo = zzgk.zzd(zza(zzm(zzbt).zzyc, zzbp, zzbtArr, set));
                break;
            case 7:
                zzbt2 = zzl(zzbt);
                zzbj.zza zzm3 = zzm(zzbt);
                zzbt2.zzys = new zzbt[zzm3.zzyb.length];
                int[] iArr4 = zzm3.zzyb;
                int length6 = iArr4.length;
                int i8 = 0;
                while (i2 < length6) {
                    zzbt2.zzys[i8] = zza(iArr4[i2], zzbp, zzbtArr, set);
                    i2++;
                    i8++;
                }
                break;
        }
        if (zzbt2 == null) {
            String valueOf2 = String.valueOf(zzbt);
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf2).length() + 15);
            sb3.append("Invalid value: ");
            sb3.append(valueOf2);
            zzna(sb3.toString());
        }
        zzbtArr[i] = zzbt2;
        set.remove(Integer.valueOf(i));
        return zzbt2;
    }

    private static zzdkj zza(zzbl zzbl, zzbp zzbp, zzbt[] zzbtArr, int i) throws zzdkp {
        zzdkk zzbku = zzdkj.zzbku();
        for (int valueOf : zzbl.zzwh) {
            zzbo zzbo = (zzbo) zza(zzbp.zzwx, Integer.valueOf(valueOf).intValue(), "properties");
            String str = (String) zza(zzbp.zzwv, zzbo.key, "keys");
            zzbt zzbt = (zzbt) zza(zzbtArr, zzbo.value, "values");
            if (zzbi.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzbku.zzn(zzbt);
            } else {
                zzbku.zzb(str, zzbt);
            }
        }
        return zzbku.zzbkv();
    }

    public static zzdkl zza(zzbp zzbp) throws zzdkp {
        zzbt[] zzbtArr = new zzbt[zzbp.zzww.length];
        for (int i = 0; i < zzbp.zzww.length; i++) {
            zza(i, zzbp, zzbtArr, (Set<Integer>) new HashSet(0));
        }
        zzdkm zzbkw = zzdkl.zzbkw();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < zzbp.zzwz.length; i2++) {
            arrayList.add(zza(zzbp.zzwz[i2], zzbp, zzbtArr, i2));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < zzbp.zzxa.length; i3++) {
            arrayList2.add(zza(zzbp.zzxa[i3], zzbp, zzbtArr, i3));
        }
        ArrayList arrayList3 = new ArrayList();
        for (int i4 = 0; i4 < zzbp.zzwy.length; i4++) {
            zzdkj zza = zza(zzbp.zzwy[i4], zzbp, zzbtArr, i4);
            zzbkw.zzc(zza);
            arrayList3.add(zza);
        }
        for (zzbq zza2 : zzbp.zzxb) {
            zzbkw.zzb(zza(zza2, arrayList, arrayList3, arrayList2, zzbp));
        }
        zzbkw.zznn(zzbp.version);
        zzbkw.zzfk(zzbp.zzxj);
        return zzbkw.zzbky();
    }

    private static zzdkn zza(zzbq zzbq, List<zzdkj> list, List<zzdkj> list2, List<zzdkj> list3, zzbp zzbp) {
        zzdko zzdko = new zzdko();
        for (int valueOf : zzbq.zzxl) {
            zzdko.zzd(list3.get(Integer.valueOf(valueOf).intValue()));
        }
        for (int valueOf2 : zzbq.zzxm) {
            zzdko.zze(list3.get(Integer.valueOf(valueOf2).intValue()));
        }
        for (int valueOf3 : zzbq.zzxn) {
            zzdko.zzf(list.get(Integer.valueOf(valueOf3).intValue()));
        }
        for (int valueOf4 : zzbq.zzxp) {
            zzdko.zzno(zzbp.zzww[Integer.valueOf(valueOf4).intValue()].string);
        }
        for (int valueOf5 : zzbq.zzxo) {
            zzdko.zzg(list.get(Integer.valueOf(valueOf5).intValue()));
        }
        for (int valueOf6 : zzbq.zzxq) {
            zzdko.zznp(zzbp.zzww[Integer.valueOf(valueOf6).intValue()].string);
        }
        for (int valueOf7 : zzbq.zzxr) {
            zzdko.zzh(list2.get(Integer.valueOf(valueOf7).intValue()));
        }
        for (int valueOf8 : zzbq.zzxt) {
            zzdko.zznq(zzbp.zzww[Integer.valueOf(valueOf8).intValue()].string);
        }
        for (int valueOf9 : zzbq.zzxs) {
            zzdko.zzi(list2.get(Integer.valueOf(valueOf9).intValue()));
        }
        for (int valueOf10 : zzbq.zzxu) {
            zzdko.zznr(zzbp.zzww[Integer.valueOf(valueOf10).intValue()].string);
        }
        return zzdko.zzblb();
    }

    private static <T> T zza(T[] tArr, int i, String str) throws zzdkp {
        if (i < 0 || i >= tArr.length) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 45);
            sb.append("Index out of bounds detected: ");
            sb.append(i);
            sb.append(" in ");
            sb.append(str);
            zzna(sb.toString());
        }
        return tArr[i];
    }

    public static void zzb(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static zzbt zzl(zzbt zzbt) {
        zzbt zzbt2 = new zzbt();
        zzbt2.type = zzbt.type;
        zzbt2.zzyt = (int[]) zzbt.zzyt.clone();
        if (zzbt.zzyu) {
            zzbt2.zzyu = zzbt.zzyu;
        }
        return zzbt2;
    }

    private static zzbj.zza zzm(zzbt zzbt) throws zzdkp {
        if (((zzbj.zza) zzbt.zza(zzbj.zza.zzxv)) == null) {
            String valueOf = String.valueOf(zzbt);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 54);
            sb.append("Expected a ServingValue and didn't get one. Value is: ");
            sb.append(valueOf);
            zzna(sb.toString());
        }
        return (zzbj.zza) zzbt.zza(zzbj.zza.zzxv);
    }

    private static void zzna(String str) throws zzdkp {
        zzdj.e(str);
        throw new zzdkp(str);
    }
}
