package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

abstract class zzdyn implements zzdsy {
    private final byte[] key;
    private final zzdym zzmmb;
    private final zzdym zzmmc;

    zzdyn(byte[] bArr) throws InvalidKeyException {
        this.key = (byte[]) bArr.clone();
        this.zzmmb = zzf(bArr, 1);
        this.zzmmc = zzf(bArr, 0);
    }

    public byte[] zzd(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        this.zzmmb.zzbsp();
        if (length <= 2147483619) {
            ByteBuffer allocate = ByteBuffer.allocate(bArr.length + this.zzmmb.zzbsp() + 16);
            if (allocate.remaining() >= bArr.length + this.zzmmb.zzbsp() + 16) {
                int position = allocate.position();
                this.zzmmb.zza(allocate, bArr);
                allocate.position(position);
                byte[] bArr3 = new byte[this.zzmmb.zzbsp()];
                allocate.get(bArr3);
                allocate.limit(allocate.limit() - 16);
                byte[] bArr4 = new byte[32];
                this.zzmmc.zze(bArr3, 0).get(bArr4);
                int length2 = bArr2.length % 16 == 0 ? bArr2.length : (bArr2.length + 16) - (bArr2.length % 16);
                int remaining = allocate.remaining();
                int i = remaining % 16;
                int i2 = (i == 0 ? remaining : (remaining + 16) - i) + length2;
                ByteBuffer order = ByteBuffer.allocate(i2 + 16).order(ByteOrder.LITTLE_ENDIAN);
                order.put(bArr2);
                order.position(length2);
                order.put(allocate);
                order.position(i2);
                order.putLong((long) bArr2.length);
                order.putLong((long) remaining);
                byte[] zzf = zzdyk.zzf(bArr4, order.array());
                allocate.limit(allocate.limit() + 16);
                allocate.put(zzf);
                return allocate.array();
            }
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        throw new GeneralSecurityException("plaintext too long");
    }

    /* access modifiers changed from: package-private */
    public abstract zzdym zzf(byte[] bArr, int i) throws InvalidKeyException;
}
