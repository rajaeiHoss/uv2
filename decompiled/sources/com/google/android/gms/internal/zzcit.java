package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

final class zzcit {
    final String name;
    final String zzcm;
    final long zzjhs;
    final long zzjht;
    final long zzjhu;
    final long zzjhv;
    final Long zzjhw;
    final Long zzjhx;
    final Boolean zzjhy;

    zzcit(String str, String str2, long j, long j2, long j3, long j4, Long l, Long l2, Boolean bool) {
        long j5 = j;
        long j6 = j2;
        long j7 = j4;
        zzbq.zzgv(str);
        zzbq.zzgv(str2);
        boolean z = true;
        zzbq.checkArgument(j5 >= 0);
        zzbq.checkArgument(j6 >= 0);
        zzbq.checkArgument(j7 < 0 ? false : z);
        this.zzcm = str;
        this.name = str2;
        this.zzjhs = j5;
        this.zzjht = j6;
        this.zzjhu = j3;
        this.zzjhv = j7;
        this.zzjhw = l;
        this.zzjhx = l2;
        this.zzjhy = bool;
    }

    /* access modifiers changed from: package-private */
    public final zzcit zza(Long l, Long l2, Boolean bool) {
        return new zzcit(this.zzcm, this.name, this.zzjhs, this.zzjht, this.zzjhu, this.zzjhv, l, l2, (bool == null || bool.booleanValue()) ? bool : null);
    }

    /* access modifiers changed from: package-private */
    public final zzcit zzban() {
        return new zzcit(this.zzcm, this.name, this.zzjhs + 1, 1 + this.zzjht, this.zzjhu, this.zzjhv, this.zzjhw, this.zzjhx, this.zzjhy);
    }

    /* access modifiers changed from: package-private */
    public final zzcit zzbb(long j) {
        return new zzcit(this.zzcm, this.name, this.zzjhs, this.zzjht, j, this.zzjhv, this.zzjhw, this.zzjhx, this.zzjhy);
    }

    /* access modifiers changed from: package-private */
    public final zzcit zzbc(long j) {
        return new zzcit(this.zzcm, this.name, this.zzjhs, this.zzjht, this.zzjhu, j, this.zzjhw, this.zzjhx, this.zzjhy);
    }
}
