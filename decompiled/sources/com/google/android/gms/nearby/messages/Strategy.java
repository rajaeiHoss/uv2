package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;

public class Strategy extends zzbgl {
    public static final Strategy BLE_ONLY;
    public static final Parcelable.Creator<Strategy> CREATOR = new zzg();
    public static final Strategy DEFAULT = new Builder().build();
    public static final int DISCOVERY_MODE_BROADCAST = 1;
    public static final int DISCOVERY_MODE_DEFAULT = 3;
    public static final int DISCOVERY_MODE_SCAN = 2;
    public static final int DISTANCE_TYPE_DEFAULT = 0;
    public static final int DISTANCE_TYPE_EARSHOT = 1;
    public static final int TTL_SECONDS_DEFAULT = 300;
    public static final int TTL_SECONDS_INFINITE = Integer.MAX_VALUE;
    public static final int TTL_SECONDS_MAX = 86400;
    @Deprecated
    private static Strategy zzkbc;
    private int zzehz;
    @Deprecated
    private int zzkbd;
    private int zzkbe;
    private int zzkbf;
    @Deprecated
    private boolean zzkbg;
    private int zzkbh;
    private int zzkbi;
    private final int zzkbj;

    public static class Builder {
        private int zzkbk = 3;
        private int zzkbl = Strategy.TTL_SECONDS_DEFAULT;
        private int zzkbm = 0;
        private int zzkbn = -1;
        private int zzkbo = 0;

        public Strategy build() {
            if (this.zzkbn != 2 || this.zzkbm != 1) {
                return new Strategy(2, 0, this.zzkbl, this.zzkbm, false, this.zzkbn, this.zzkbk, 0);
            }
            throw new IllegalStateException("Cannot set EARSHOT with BLE only mode.");
        }

        public Builder setDiscoveryMode(int i) {
            this.zzkbk = i;
            return this;
        }

        public Builder setDistanceType(int i) {
            this.zzkbm = i;
            return this;
        }

        public Builder setTtlSeconds(int i) {
            zzbq.zzb(i == Integer.MAX_VALUE || (i > 0 && i <= 86400), "mTtlSeconds(%d) must either be TTL_SECONDS_INFINITE, or it must be between 1 and TTL_SECONDS_MAX(%d) inclusive", Integer.valueOf(i), Integer.valueOf(Strategy.TTL_SECONDS_MAX));
            this.zzkbl = i;
            return this;
        }

        public final Builder zzes(int i) {
            this.zzkbn = 2;
            return this;
        }
    }

    static {
        Strategy build = new Builder().zzes(2).setTtlSeconds(Integer.MAX_VALUE).build();
        BLE_ONLY = build;
        zzkbc = build;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    Strategy(int r2, int r3, int r4, int r5, boolean r6, int r7, int r8, int r9) {
        /*
            r1 = this;
            r1.<init>()
            r1.zzehz = r2
            r1.zzkbd = r3
            r2 = 1
            r0 = 2
            if (r3 != 0) goto L_0x000e
        L_0x000b:
            r1.zzkbi = r8
            goto L_0x0019
        L_0x000e:
            if (r3 == r0) goto L_0x0017
            r8 = 3
            if (r3 == r8) goto L_0x0014
            goto L_0x000b
        L_0x0014:
            r1.zzkbi = r0
            goto L_0x0019
        L_0x0017:
            r1.zzkbi = r2
        L_0x0019:
            r1.zzkbf = r5
            r1.zzkbg = r6
            if (r6 == 0) goto L_0x0027
            r1.zzkbh = r0
            r2 = 2147483647(0x7fffffff, float:NaN)
            r1.zzkbe = r2
            goto L_0x0038
        L_0x0027:
            r1.zzkbe = r4
            r3 = -1
            if (r7 == r3) goto L_0x0036
            if (r7 == 0) goto L_0x0036
            if (r7 == r2) goto L_0x0036
            r2 = 6
            if (r7 == r2) goto L_0x0036
            r1.zzkbh = r7
            goto L_0x0038
        L_0x0036:
            r1.zzkbh = r3
        L_0x0038:
            r1.zzkbj = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.nearby.messages.Strategy.<init>(int, int, int, int, boolean, int, int, int):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Strategy)) {
            return false;
        }
        Strategy strategy = (Strategy) obj;
        return this.zzehz == strategy.zzehz && this.zzkbi == strategy.zzkbi && this.zzkbe == strategy.zzkbe && this.zzkbf == strategy.zzkbf && this.zzkbh == strategy.zzkbh && this.zzkbj == strategy.zzkbj;
    }

    public int hashCode() {
        return (((((((((this.zzehz * 31) + this.zzkbi) * 31) + this.zzkbe) * 31) + this.zzkbf) * 31) + this.zzkbh) * 31) + this.zzkbj;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        int i = this.zzkbe;
        int i2 = this.zzkbf;
        String str4 = "DEFAULT";
        if (i2 == 0) {
            str = str4;
        } else if (i2 != 1) {
            StringBuilder sb = new StringBuilder(19);
            sb.append("UNKNOWN:");
            sb.append(i2);
            str = sb.toString();
        } else {
            str = "EARSHOT";
        }
        int i3 = this.zzkbh;
        if (i3 == -1) {
            str2 = str4;
        } else {
            ArrayList arrayList = new ArrayList();
            if ((i3 & 4) > 0) {
                arrayList.add("ULTRASOUND");
            }
            if ((i3 & 2) > 0) {
                arrayList.add("BLE");
            }
            if (arrayList.isEmpty()) {
                StringBuilder sb2 = new StringBuilder(19);
                sb2.append("UNKNOWN:");
                sb2.append(i3);
                str2 = sb2.toString();
            } else {
                str2 = arrayList.toString();
            }
        }
        int i4 = this.zzkbi;
        if (i4 == 3) {
            str3 = str4;
        } else {
            ArrayList arrayList2 = new ArrayList();
            if ((i4 & 1) > 0) {
                arrayList2.add("BROADCAST");
            }
            if ((i4 & 2) > 0) {
                arrayList2.add("SCAN");
            }
            if (arrayList2.isEmpty()) {
                StringBuilder sb3 = new StringBuilder(19);
                sb3.append("UNKNOWN:");
                sb3.append(i4);
                str3 = sb3.toString();
            } else {
                str3 = arrayList2.toString();
            }
        }
        int i5 = this.zzkbj;
        if (i5 != 0) {
            if (i5 != 1) {
                StringBuilder sb4 = new StringBuilder(20);
                sb4.append("UNKNOWN: ");
                sb4.append(i5);
                str4 = sb4.toString();
            } else {
                str4 = "ALWAYS_ON";
            }
        }
        StringBuilder sb5 = new StringBuilder(String.valueOf(str).length() + 102 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length());
        sb5.append("Strategy{ttlSeconds=");
        sb5.append(i);
        sb5.append(", distanceType=");
        sb5.append(str);
        sb5.append(", discoveryMedium=");
        sb5.append(str2);
        sb5.append(", discoveryMode=");
        sb5.append(str3);
        sb5.append(", backgroundScanMode=");
        sb5.append(str4);
        sb5.append('}');
        return sb5.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzkbd);
        zzbgo.zzc(parcel, 2, this.zzkbe);
        zzbgo.zzc(parcel, 3, this.zzkbf);
        zzbgo.zza(parcel, 4, this.zzkbg);
        zzbgo.zzc(parcel, 5, this.zzkbh);
        zzbgo.zzc(parcel, 6, this.zzkbi);
        zzbgo.zzc(parcel, 7, this.zzkbj);
        zzbgo.zzc(parcel, 1000, this.zzehz);
        zzbgo.zzai(parcel, zze);
    }

    public final int zzbdu() {
        return this.zzkbj;
    }
}
