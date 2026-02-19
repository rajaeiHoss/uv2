package com.google.android.gms.internal;

import java.io.IOException;

enum zzfle {
    LOOSE {
        /* access modifiers changed from: package-private */
        public Object zza(zzfhb zzfhb) throws IOException {
            return zzfhb.readString();
        }
    },
    STRICT {
        /* access modifiers changed from: package-private */
        public Object zza(zzfhb zzfhb) throws IOException {
            return zzfhb.zzcye();
        }
    },
    LAZY {
        /* access modifiers changed from: package-private */
        public Object zza(zzfhb zzfhb) throws IOException {
            return zzfhb.zzcyf();
        }
    };

    /* access modifiers changed from: package-private */
    public abstract Object zza(zzfhb zzfhb) throws IOException;
}
