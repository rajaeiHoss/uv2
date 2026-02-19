package com.google.android.gms.internal;

import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

final class zzhv {
    private ByteArrayOutputStream zzbah = new ByteArrayOutputStream(4096);
    private Base64OutputStream zzbai = new Base64OutputStream(this.zzbah, 10);

    public final String toString() {
        String str;
        try {
            this.zzbai.close();
        } catch (IOException e) {
            zzahw.zzb("HashManager: Unable to convert to Base64.", e);
        }
        try {
            this.zzbah.close();
            str = this.zzbah.toString();
        } catch (IOException e2) {
            zzahw.zzb("HashManager: Unable to convert to Base64.", e2);
            str = "";
        } catch (Throwable th) {
            this.zzbah = null;
            this.zzbai = null;
            throw th;
        }
        this.zzbah = null;
        this.zzbai = null;
        return str;
    }

    public final void write(byte[] bArr) throws IOException {
        this.zzbai.write(bArr);
    }
}
