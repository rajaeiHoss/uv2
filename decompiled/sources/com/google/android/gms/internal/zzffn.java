package com.google.android.gms.internal;

import java.io.IOException;

public final class zzffn extends zzflm<zzffn> {
    private int day = 0;
    private int hour = 0;
    private int minutes = 0;
    private int month = 0;
    private int seconds = 0;
    private int year = 0;

    public zzffn() {
        this.zzpnr = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzffn)) {
            return false;
        }
        zzffn zzffn = (zzffn) obj;
        if (this.year == zzffn.year && this.month == zzffn.month && this.day == zzffn.day && this.hour == zzffn.hour && this.minutes == zzffn.minutes && this.seconds == zzffn.seconds) {
            return (this.zzpvl == null || this.zzpvl.isEmpty()) ? zzffn.zzpvl == null || zzffn.zzpvl.isEmpty() : this.zzpvl.equals(zzffn.zzpvl);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((((((getClass().getName().hashCode() + 527) * 31) + this.year) * 31) + this.month) * 31) + this.day) * 31) + this.hour) * 31) + this.minutes) * 31) + this.seconds) * 31) + ((this.zzpvl == null || this.zzpvl.isEmpty()) ? 0 : this.zzpvl.hashCode());
    }

    public final /* synthetic */ zzfls zza(zzflj zzflj) throws IOException {
        while (true) {
            int zzcxx = zzflj.zzcxx();
            if (zzcxx == 0) {
                return this;
            }
            if (zzcxx == 8) {
                this.year = zzflj.zzcym();
            } else if (zzcxx == 16) {
                this.month = zzflj.zzcym();
            } else if (zzcxx == 24) {
                this.day = zzflj.zzcym();
            } else if (zzcxx == 32) {
                this.hour = zzflj.zzcym();
            } else if (zzcxx == 40) {
                this.minutes = zzflj.zzcym();
            } else if (zzcxx == 48) {
                this.seconds = zzflj.zzcym();
            } else if (!super.zza(zzflj, zzcxx)) {
                return this;
            }
        }
    }

    public final void zza(zzflk zzflk) throws IOException {
        int i = this.year;
        if (i != 0) {
            zzflk.zzad(1, i);
        }
        int i2 = this.month;
        if (i2 != 0) {
            zzflk.zzad(2, i2);
        }
        int i3 = this.day;
        if (i3 != 0) {
            zzflk.zzad(3, i3);
        }
        int i4 = this.hour;
        if (i4 != 0) {
            zzflk.zzad(4, i4);
        }
        int i5 = this.minutes;
        if (i5 != 0) {
            zzflk.zzad(5, i5);
        }
        int i6 = this.seconds;
        if (i6 != 0) {
            zzflk.zzad(6, i6);
        }
        super.zza(zzflk);
    }

    /* access modifiers changed from: protected */
    public final int zzq() {
        int zzq = super.zzq();
        int i = this.year;
        if (i != 0) {
            zzq += zzflk.zzag(1, i);
        }
        int i2 = this.month;
        if (i2 != 0) {
            zzq += zzflk.zzag(2, i2);
        }
        int i3 = this.day;
        if (i3 != 0) {
            zzq += zzflk.zzag(3, i3);
        }
        int i4 = this.hour;
        if (i4 != 0) {
            zzq += zzflk.zzag(4, i4);
        }
        int i5 = this.minutes;
        if (i5 != 0) {
            zzq += zzflk.zzag(5, i5);
        }
        int i6 = this.seconds;
        return i6 != 0 ? zzq + zzflk.zzag(6, i6) : zzq;
    }
}
