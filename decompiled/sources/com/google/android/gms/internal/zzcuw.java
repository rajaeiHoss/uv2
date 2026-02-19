package com.google.android.gms.internal;

import android.os.ParcelUuid;
import android.util.SparseArray;
import com.google.android.gms.common.util.zzm;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.UByte;

public final class zzcuw {
    private static final ParcelUuid zzkcc = ParcelUuid.fromString("00000000-0000-1000-8000-00805F9B34FB");
    private final String zziia;
    private final int zzkcd;
    private final List<ParcelUuid> zzkce;
    private final SparseArray<byte[]> zzkcf;
    private final Map<ParcelUuid, byte[]> zzkcg;
    private final int zzkch;
    private final byte[] zzkci;

    private zzcuw(List<ParcelUuid> list, SparseArray<byte[]> sparseArray, Map<ParcelUuid, byte[]> map, int i, int i2, String str, byte[] bArr) {
        this.zzkce = list;
        this.zzkcf = sparseArray;
        this.zzkcg = map;
        this.zziia = str;
        this.zzkcd = i;
        this.zzkch = i2;
        this.zzkci = bArr;
    }

    private static int zza(byte[] bArr, int i, int i2, int i3, List<ParcelUuid> list) {
        while (i2 > 0) {
            list.add(zzv(zzb(bArr, i, i3)));
            i2 -= i3;
            i += i3;
        }
        return i;
    }

    private static byte[] zzb(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x008d A[Catch:{ Exception -> 0x0098 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008f A[Catch:{ Exception -> 0x0098 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzcuw zzu(byte[] r13) {
        /*
            r0 = 0
            if (r13 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            r2 = -1
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            android.util.SparseArray r7 = new android.util.SparseArray
            r7.<init>()
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
            r11 = r0
            r9 = -1
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x001b:
            int r2 = r13.length     // Catch:{ Exception -> 0x0098 }
            if (r1 >= r2) goto L_0x0087
            int r2 = r1 + 1
            byte r1 = r13[r1]     // Catch:{ Exception -> 0x0098 }
            r4 = 255(0xff, float:3.57E-43)
            r1 = r1 & r4
            if (r1 == 0) goto L_0x0087
            int r1 = r1 + -1
            int r5 = r2 + 1
            byte r2 = r13[r2]     // Catch:{ Exception -> 0x0098 }
            r2 = r2 & r4
            r6 = 22
            r12 = 2
            if (r2 == r6) goto L_0x0072
            if (r2 == r4) goto L_0x005b
            switch(r2) {
                case 1: goto L_0x0056;
                case 2: goto L_0x0052;
                case 3: goto L_0x0052;
                case 4: goto L_0x004d;
                case 5: goto L_0x004d;
                case 6: goto L_0x0047;
                case 7: goto L_0x0047;
                case 8: goto L_0x003d;
                case 9: goto L_0x003d;
                case 10: goto L_0x0039;
                default: goto L_0x0038;
            }     // Catch:{ Exception -> 0x0098 }
        L_0x0038:
            goto L_0x0085
        L_0x0039:
            byte r2 = r13[r5]     // Catch:{ Exception -> 0x0098 }
            r10 = r2
            goto L_0x0085
        L_0x003d:
            java.lang.String r11 = new java.lang.String     // Catch:{ Exception -> 0x0098 }
            byte[] r2 = zzb(r13, r5, r1)     // Catch:{ Exception -> 0x0098 }
            r11.<init>(r2)     // Catch:{ Exception -> 0x0098 }
            goto L_0x0085
        L_0x0047:
            r2 = 16
            zza(r13, r5, r1, r2, r3)     // Catch:{ Exception -> 0x0098 }
            goto L_0x0085
        L_0x004d:
            r2 = 4
            zza(r13, r5, r1, r2, r3)     // Catch:{ Exception -> 0x0098 }
            goto L_0x0085
        L_0x0052:
            zza(r13, r5, r1, r12, r3)     // Catch:{ Exception -> 0x0098 }
            goto L_0x0085
        L_0x0056:
            byte r2 = r13[r5]     // Catch:{ Exception -> 0x0098 }
            r2 = r2 & r4
            r9 = r2
            goto L_0x0085
        L_0x005b:
            int r2 = r5 + 1
            byte r2 = r13[r2]     // Catch:{ Exception -> 0x0098 }
            r2 = r2 & r4
            int r2 = r2 << 8
            byte r6 = r13[r5]     // Catch:{ Exception -> 0x0098 }
            r4 = r4 & r6
            int r2 = r2 + r4
            int r4 = r5 + 2
            int r6 = r1 + -2
            byte[] r4 = zzb(r13, r4, r6)     // Catch:{ Exception -> 0x0098 }
            r7.put(r2, r4)     // Catch:{ Exception -> 0x0098 }
            goto L_0x0085
        L_0x0072:
            byte[] r2 = zzb(r13, r5, r12)     // Catch:{ Exception -> 0x0098 }
            android.os.ParcelUuid r2 = zzv(r2)     // Catch:{ Exception -> 0x0098 }
            int r4 = r5 + 2
            int r6 = r1 + -2
            byte[] r4 = zzb(r13, r4, r6)     // Catch:{ Exception -> 0x0098 }
            r8.put(r2, r4)     // Catch:{ Exception -> 0x0098 }
        L_0x0085:
            int r1 = r1 + r5
            goto L_0x001b
        L_0x0087:
            boolean r1 = r3.isEmpty()     // Catch:{ Exception -> 0x0098 }
            if (r1 == 0) goto L_0x008f
            r6 = r0
            goto L_0x0090
        L_0x008f:
            r6 = r3
        L_0x0090:
            com.google.android.gms.internal.zzcuw r1 = new com.google.android.gms.internal.zzcuw     // Catch:{ Exception -> 0x0098 }
            r5 = r1
            r12 = r13
            r5.<init>(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x0098 }
            return r1
        L_0x0098:
            r1 = move-exception
            java.lang.String r2 = "Unable to parse scan record: "
            java.lang.String r13 = java.util.Arrays.toString(r13)
            java.lang.String r13 = java.lang.String.valueOf(r13)
            int r3 = r13.length()
            if (r3 == 0) goto L_0x00ae
            java.lang.String r13 = r2.concat(r13)
            goto L_0x00b3
        L_0x00ae:
            java.lang.String r13 = new java.lang.String
            r13.<init>(r2)
        L_0x00b3:
            java.lang.String r2 = "BleRecord"
            android.util.Log.w(r2, r13, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcuw.zzu(byte[]):com.google.android.gms.internal.zzcuw");
    }

    private static ParcelUuid zzv(byte[] bArr) {
        long j;
        if (bArr != null) {
            int length = bArr.length;
            if (length != 2 && length != 4 && length != 16) {
                StringBuilder sb = new StringBuilder(38);
                sb.append("uuidBytes length invalid - ");
                sb.append(length);
                throw new IllegalArgumentException(sb.toString());
            } else if (length == 16) {
                ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
                return new ParcelUuid(new UUID(order.getLong(8), order.getLong(0)));
            } else {
                if (length == 2) {
                    j = ((long) (bArr[0] & UByte.MAX_VALUE)) + ((long) ((bArr[1] & UByte.MAX_VALUE) << 8));
                } else {
                    j = ((long) ((bArr[3] & UByte.MAX_VALUE) << 24)) + ((long) (bArr[0] & UByte.MAX_VALUE)) + ((long) ((bArr[1] & UByte.MAX_VALUE) << 8)) + ((long) ((bArr[2] & UByte.MAX_VALUE) << 16));
                }
                ParcelUuid parcelUuid = zzkcc;
                return new ParcelUuid(new UUID(parcelUuid.getUuid().getMostSignificantBits() + (j << 32), parcelUuid.getUuid().getLeastSignificantBits()));
            }
        } else {
            throw new IllegalArgumentException("uuidBytes cannot be null");
        }
    }

    private static <T> String zzw(Map<T, byte[]> map) {
        StringBuilder sb = new StringBuilder();
        if (map.keySet().size() <= 0) {
            return "{}";
        }
        sb.append('{');
        int i = 0;
        for (Map.Entry next : map.entrySet()) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(next.getKey());
            sb.append('=');
            byte[] bArr = (byte[]) next.getValue();
            sb.append(bArr == null ? null : zzm.zzm(bArr));
            i++;
        }
        sb.append('}');
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcuw)) {
            return false;
        }
        return Arrays.equals(this.zzkci, ((zzcuw) obj).zzkci);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzkci);
    }

    public final String toString() {
        String str;
        int i = this.zzkcd;
        String valueOf = String.valueOf(this.zzkce);
        SparseArray<byte[]> sparseArray = this.zzkcf;
        StringBuilder sb = new StringBuilder();
        if (sparseArray.size() <= 0) {
            str = "{}";
        } else {
            sb.append('{');
            for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                if (i2 > 0) {
                    sb.append(", ");
                }
                int keyAt = sparseArray.keyAt(i2);
                byte[] valueAt = sparseArray.valueAt(i2);
                sb.append(keyAt);
                sb.append('=');
                sb.append(valueAt == null ? null : zzm.zzm(valueAt));
            }
            sb.append('}');
            str = sb.toString();
        }
        String zzw = zzw(this.zzkcg);
        int i3 = this.zzkch;
        String str2 = this.zziia;
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf).length() + 139 + String.valueOf(str).length() + String.valueOf(zzw).length() + String.valueOf(str2).length());
        sb2.append("BleRecord [mAdvertiseFlags=");
        sb2.append(i);
        sb2.append(", mServiceUuids=");
        sb2.append(valueOf);
        sb2.append(", mManufacturerSpecificData=");
        sb2.append(str);
        sb2.append(", mServiceData=");
        sb2.append(zzw);
        sb2.append(", mTxPowerLevel=");
        sb2.append(i3);
        sb2.append(", mDeviceName=");
        sb2.append(str2);
        sb2.append("]");
        return sb2.toString();
    }
}
