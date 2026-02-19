package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

final class zzeny implements zzenz {
    private List<byte[]> zznpp = new ArrayList();
    private int zznpq = 0;

    zzeny() {
    }

    public final boolean zzao(byte[] bArr) {
        this.zznpp.add(bArr);
        this.zznpq += bArr.length;
        return true;
    }

    public final zzeom zzcda() {
        byte[] bArr = new byte[this.zznpq];
        int i = 0;
        for (int i2 = 0; i2 < this.zznpp.size(); i2++) {
            byte[] bArr2 = this.zznpp.get(i2);
            System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
            i += bArr2.length;
        }
        return new zzeom(bArr);
    }
}
