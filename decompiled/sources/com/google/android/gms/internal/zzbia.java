package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzr;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class zzbia extends zzbhs {
    public static final Parcelable.Creator<zzbia> CREATOR = new zzbib();
    private final String mClassName;
    private final int zzehz;
    private final zzbhv zzgiw;
    private final Parcel zzgjd;
    private final int zzgje = 2;
    private int zzgjf;
    private int zzgjg;

    zzbia(int i, Parcel parcel, zzbhv zzbhv) {
        this.zzehz = i;
        this.zzgjd = (Parcel) zzbq.checkNotNull(parcel);
        this.zzgiw = zzbhv;
        this.mClassName = zzbhv == null ? null : zzbhv.zzanj();
        this.zzgjf = 2;
    }

    private static void zza(StringBuilder sb, int i, Object obj) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(obj);
                return;
            case 7:
                sb.append("\"");
                sb.append(zzq.zzha(obj.toString()));
                sb.append("\"");
                return;
            case 8:
                sb.append("\"");
                sb.append(zzc.zzj((byte[]) obj));
                sb.append("\"");
                return;
            case 9:
                sb.append("\"");
                sb.append(zzc.zzk((byte[]) obj));
                sb.append("\"");
                return;
            case 10:
                zzr.zza(sb, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                StringBuilder sb2 = new StringBuilder(26);
                sb2.append("Unknown type = ");
                sb2.append(i);
                throw new IllegalArgumentException(sb2.toString());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: double[]} */
    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(java.lang.StringBuilder r6, com.google.android.gms.internal.zzbhq<?, ?> r7, android.os.Parcel r8, int r9) {
        /*
            r5 = this;
            boolean r0 = r7.zzgir
            java.lang.String r1 = ","
            r2 = 0
            if (r0 == 0) goto L_0x00c9
            java.lang.String r0 = "["
            r6.append(r0)
            int r0 = r7.zzgiq
            r3 = 0
            switch(r0) {
                case 0: goto L_0x00ab;
                case 1: goto L_0x0082;
                case 2: goto L_0x007a;
                case 3: goto L_0x0072;
                case 4: goto L_0x005b;
                case 5: goto L_0x0052;
                case 6: goto L_0x0049;
                case 7: goto L_0x0040;
                case 8: goto L_0x0038;
                case 9: goto L_0x0038;
                case 10: goto L_0x0038;
                case 11: goto L_0x001a;
                default: goto L_0x0012;
            }
        L_0x0012:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Unknown field type out."
            r6.<init>(r7)
            throw r6
        L_0x001a:
            android.os.Parcel[] r8 = com.google.android.gms.internal.zzbgm.zzae(r8, r9)
            int r9 = r8.length
            r0 = 0
        L_0x0020:
            if (r0 >= r9) goto L_0x00c3
            if (r0 <= 0) goto L_0x0027
            r6.append(r1)
        L_0x0027:
            r3 = r8[r0]
            r3.setDataPosition(r2)
            java.util.Map r3 = r7.zzanh()
            r4 = r8[r0]
            r5.zza((java.lang.StringBuilder) r6, (java.util.Map<java.lang.String, com.google.android.gms.internal.zzbhq<?, ?>>) r3, (android.os.Parcel) r4)
            int r0 = r0 + 1
            goto L_0x0020
        L_0x0038:
            java.lang.UnsupportedOperationException r6 = new java.lang.UnsupportedOperationException
            java.lang.String r7 = "List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported"
            r6.<init>(r7)
            throw r6
        L_0x0040:
            java.lang.String[] r7 = com.google.android.gms.internal.zzbgm.zzaa(r8, r9)
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r6, (java.lang.String[]) r7)
            goto L_0x00c3
        L_0x0049:
            boolean[] r7 = com.google.android.gms.internal.zzbgm.zzv(r8, r9)
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r6, (boolean[]) r7)
            goto L_0x00c3
        L_0x0052:
            java.math.BigDecimal[] r7 = com.google.android.gms.internal.zzbgm.zzz(r8, r9)
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r6, (java.lang.Object[]) r7)
            goto L_0x00c3
        L_0x005b:
            int r7 = com.google.android.gms.internal.zzbgm.zza(r8, r9)
            int r9 = r8.dataPosition()
            if (r7 != 0) goto L_0x0066
            goto L_0x006e
        L_0x0066:
            double[] r3 = r8.createDoubleArray()
            int r9 = r9 + r7
            r8.setDataPosition(r9)
        L_0x006e:
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r6, (double[]) r3)
            goto L_0x00c3
        L_0x0072:
            float[] r7 = com.google.android.gms.internal.zzbgm.zzy(r8, r9)
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r6, (float[]) r7)
            goto L_0x00c3
        L_0x007a:
            long[] r7 = com.google.android.gms.internal.zzbgm.zzx(r8, r9)
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r6, (long[]) r7)
            goto L_0x00c3
        L_0x0082:
            int r7 = com.google.android.gms.internal.zzbgm.zza(r8, r9)
            int r9 = r8.dataPosition()
            if (r7 != 0) goto L_0x008d
            goto L_0x00a7
        L_0x008d:
            int r0 = r8.readInt()
            java.math.BigInteger[] r3 = new java.math.BigInteger[r0]
        L_0x0093:
            if (r2 >= r0) goto L_0x00a3
            java.math.BigInteger r1 = new java.math.BigInteger
            byte[] r4 = r8.createByteArray()
            r1.<init>(r4)
            r3[r2] = r1
            int r2 = r2 + 1
            goto L_0x0093
        L_0x00a3:
            int r9 = r9 + r7
            r8.setDataPosition(r9)
        L_0x00a7:
            com.google.android.gms.common.util.zzb.zza((java.lang.StringBuilder) r6, (java.lang.Object[]) r3)
            goto L_0x00c3
        L_0x00ab:
            int[] r7 = com.google.android.gms.internal.zzbgm.zzw(r8, r9)
            int r8 = r7.length
        L_0x00b0:
            if (r2 >= r8) goto L_0x00c3
            if (r2 == 0) goto L_0x00b7
            r6.append(r1)
        L_0x00b7:
            r9 = r7[r2]
            java.lang.String r9 = java.lang.Integer.toString(r9)
            r6.append(r9)
            int r2 = r2 + 1
            goto L_0x00b0
        L_0x00c3:
            java.lang.String r7 = "]"
            r6.append(r7)
            return
        L_0x00c9:
            int r0 = r7.zzgiq
            java.lang.String r3 = "\""
            switch(r0) {
                case 0: goto L_0x019c;
                case 1: goto L_0x0194;
                case 2: goto L_0x018c;
                case 3: goto L_0x0184;
                case 4: goto L_0x017c;
                case 5: goto L_0x0174;
                case 6: goto L_0x016c;
                case 7: goto L_0x015a;
                case 8: goto L_0x0148;
                case 9: goto L_0x0136;
                case 10: goto L_0x00e7;
                case 11: goto L_0x00d8;
                default: goto L_0x00d0;
            }
        L_0x00d0:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Unknown field type out"
            r6.<init>(r7)
            throw r6
        L_0x00d8:
            android.os.Parcel r8 = com.google.android.gms.internal.zzbgm.zzad(r8, r9)
            r8.setDataPosition(r2)
            java.util.Map r7 = r7.zzanh()
            r5.zza((java.lang.StringBuilder) r6, (java.util.Map<java.lang.String, com.google.android.gms.internal.zzbhq<?, ?>>) r7, (android.os.Parcel) r8)
            return
        L_0x00e7:
            android.os.Bundle r7 = com.google.android.gms.internal.zzbgm.zzs(r8, r9)
            java.util.Set r8 = r7.keySet()
            r8.size()
            java.lang.String r9 = "{"
            r6.append(r9)
            java.util.Iterator r8 = r8.iterator()
            r9 = 1
        L_0x00fd:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x012f
            java.lang.Object r0 = r8.next()
            java.lang.String r0 = (java.lang.String) r0
            if (r9 != 0) goto L_0x010e
            r6.append(r1)
        L_0x010e:
            r6.append(r3)
            r6.append(r0)
            r6.append(r3)
            java.lang.String r9 = ":"
            r6.append(r9)
            r6.append(r3)
            java.lang.String r9 = r7.getString(r0)
            java.lang.String r9 = com.google.android.gms.common.util.zzq.zzha(r9)
            r6.append(r9)
            r6.append(r3)
            r9 = 0
            goto L_0x00fd
        L_0x012f:
            java.lang.String r7 = "}"
            r6.append(r7)
            return
        L_0x0136:
            byte[] r7 = com.google.android.gms.internal.zzbgm.zzt(r8, r9)
            r6.append(r3)
            java.lang.String r7 = com.google.android.gms.common.util.zzc.zzk(r7)
            r6.append(r7)
            r6.append(r3)
            return
        L_0x0148:
            byte[] r7 = com.google.android.gms.internal.zzbgm.zzt(r8, r9)
            r6.append(r3)
            java.lang.String r7 = com.google.android.gms.common.util.zzc.zzj(r7)
            r6.append(r7)
            r6.append(r3)
            return
        L_0x015a:
            java.lang.String r7 = com.google.android.gms.internal.zzbgm.zzq(r8, r9)
            r6.append(r3)
            java.lang.String r7 = com.google.android.gms.common.util.zzq.zzha(r7)
            r6.append(r7)
            r6.append(r3)
            return
        L_0x016c:
            boolean r7 = com.google.android.gms.internal.zzbgm.zzc(r8, r9)
            r6.append(r7)
            return
        L_0x0174:
            java.math.BigDecimal r7 = com.google.android.gms.internal.zzbgm.zzp(r8, r9)
            r6.append(r7)
            return
        L_0x017c:
            double r7 = com.google.android.gms.internal.zzbgm.zzn(r8, r9)
            r6.append(r7)
            return
        L_0x0184:
            float r7 = com.google.android.gms.internal.zzbgm.zzl(r8, r9)
            r6.append(r7)
            return
        L_0x018c:
            long r7 = com.google.android.gms.internal.zzbgm.zzi(r8, r9)
            r6.append(r7)
            return
        L_0x0194:
            java.math.BigInteger r7 = com.google.android.gms.internal.zzbgm.zzk(r8, r9)
            r6.append(r7)
            return
        L_0x019c:
            int r7 = com.google.android.gms.internal.zzbgm.zzg(r8, r9)
            r6.append(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbia.zza(java.lang.StringBuilder, com.google.android.gms.internal.zzbhq, android.os.Parcel, int):void");
    }

    private final void zza(StringBuilder sb, Map<String, zzbhq<?, ?>> map, Parcel parcel) {
        Object obj;
        SparseArray sparseArray = new SparseArray();
        for (Map.Entry next : map.entrySet()) {
            sparseArray.put(((zzbhq) next.getValue()).zzgit, next);
        }
        sb.append('{');
        int zzd = zzbgm.zzd(parcel);
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            Map.Entry entry = (Map.Entry) sparseArray.get(65535 & readInt);
            if (entry != null) {
                if (z) {
                    sb.append(",");
                }
                zzbhq zzbhq = (zzbhq) entry.getValue();
                sb.append("\"");
                sb.append((String) entry.getKey());
                sb.append("\":");
                if (zzbhq.zzang()) {
                    switch (zzbhq.zzgiq) {
                        case 0:
                            obj = Integer.valueOf(zzbgm.zzg(parcel, readInt));
                            break;
                        case 1:
                            obj = zzbgm.zzk(parcel, readInt);
                            break;
                        case 2:
                            obj = Long.valueOf(zzbgm.zzi(parcel, readInt));
                            break;
                        case 3:
                            obj = Float.valueOf(zzbgm.zzl(parcel, readInt));
                            break;
                        case 4:
                            obj = Double.valueOf(zzbgm.zzn(parcel, readInt));
                            break;
                        case 5:
                            obj = zzbgm.zzp(parcel, readInt);
                            break;
                        case 6:
                            obj = Boolean.valueOf(zzbgm.zzc(parcel, readInt));
                            break;
                        case 7:
                            obj = zzbgm.zzq(parcel, readInt);
                            break;
                        case 8:
                        case 9:
                            obj = zzbgm.zzt(parcel, readInt);
                            break;
                        case 10:
                            obj = zzm(zzbgm.zzs(parcel, readInt));
                            break;
                        case 11:
                            throw new IllegalArgumentException("Method does not accept concrete type.");
                        default:
                            int i = zzbhq.zzgiq;
                            StringBuilder sb2 = new StringBuilder(36);
                            sb2.append("Unknown field out type = ");
                            sb2.append(i);
                            throw new IllegalArgumentException(sb2.toString());
                    }
                    zzb(sb, zzbhq, zza(zzbhq, obj));
                } else {
                    zza(sb, zzbhq, parcel, readInt);
                }
                z = true;
            }
        }
        if (parcel.dataPosition() == zzd) {
            sb.append('}');
            return;
        }
        StringBuilder sb3 = new StringBuilder(37);
        sb3.append("Overread allowed size end=");
        sb3.append(zzd);
        throw new zzbgn(sb3.toString(), parcel);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0005, code lost:
        if (r0 != 1) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.os.Parcel zzanl() {
        /*
            r2 = this;
            int r0 = r2.zzgjf
            if (r0 == 0) goto L_0x0008
            r1 = 1
            if (r0 == r1) goto L_0x0010
            goto L_0x001a
        L_0x0008:
            android.os.Parcel r0 = r2.zzgjd
            int r0 = com.google.android.gms.internal.zzbgo.zze(r0)
            r2.zzgjg = r0
        L_0x0010:
            android.os.Parcel r0 = r2.zzgjd
            int r1 = r2.zzgjg
            com.google.android.gms.internal.zzbgo.zzai(r0, r1)
            r0 = 2
            r2.zzgjf = r0
        L_0x001a:
            android.os.Parcel r0 = r2.zzgjd
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbia.zzanl():android.os.Parcel");
    }

    private final void zzb(StringBuilder sb, zzbhq<?, ?> zzbhq, Object obj) {
        if (zzbhq.zzgip) {
            ArrayList arrayList = (ArrayList) obj;
            sb.append("[");
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    sb.append(",");
                }
                zza(sb, zzbhq.zzgio, arrayList.get(i));
            }
            sb.append("]");
            return;
        }
        zza(sb, zzbhq.zzgio, obj);
    }

    private static HashMap<String, String> zzm(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    public String toString() {
        zzbq.checkNotNull(this.zzgiw, "Cannot convert to JSON on client side.");
        Parcel zzanl = zzanl();
        zzanl.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        zza(sb, this.zzgiw.zzgz(this.mClassName), zzanl);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzbhv zzbhv;
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zza(parcel, 2, zzanl(), false);
        int i2 = this.zzgje;
        if (i2 == 0) {
            zzbhv = null;
        } else if (i2 == 1 || i2 == 2) {
            zzbhv = this.zzgiw;
        } else {
            int i3 = this.zzgje;
            StringBuilder sb = new StringBuilder(34);
            sb.append("Invalid creation type: ");
            sb.append(i3);
            throw new IllegalStateException(sb.toString());
        }
        zzbgo.zza(parcel, 3, (Parcelable) zzbhv, i, false);
        zzbgo.zzai(parcel, zze);
    }

    public final Map<String, zzbhq<?, ?>> zzabz() {
        zzbhv zzbhv = this.zzgiw;
        if (zzbhv == null) {
            return null;
        }
        return zzbhv.zzgz(this.mClassName);
    }

    public final Object zzgx(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public final boolean zzgy(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
}
