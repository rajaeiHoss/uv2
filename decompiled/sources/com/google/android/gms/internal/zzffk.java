package com.google.android.gms.internal;

public final class zzffk extends zzflm<zzffk> {
    public zzffk() {
        this.zzpvl = null;
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffk)) {
            return false;
        }
        zzffk zzffk = (zzffk) obj;
        return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffk.zzpvl == null || zzffk.zzpvl.isEmpty() : this.zzpvl.equals(zzffk.zzpvl);
    }

    public final int hashCode() {
        return ((getClass().getName().hashCode() + 527) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final /* synthetic */ com.google.android.gms.internal.zzfls zza(com.google.android.gms.internal.zzflj r2) throws java.io.IOException {
        /*
            r1 = this;
        L_0x0000:
            int r0 = r2.zzcxx()
            if (r0 == 0) goto L_0x000c
            boolean r0 = super.zza(r2, r0)
            if (r0 != 0) goto L_0x0000
        L_0x000c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzffk.zza(com.google.android.gms.internal.zzflj):com.google.android.gms.internal.zzfls");
    }
}
