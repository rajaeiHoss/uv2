package com.google.android.gms.internal;

import java.util.Comparator;

public final class zzid implements Comparator<zzhr> {
    public zzid(zzic zzic) {
    }

    public final int compare(zzhr zzhr, zzhr zzhr2) {
        if (zzhr.zzhd() < zzhr2.zzhd()) {
            return -1;
        }
        if (zzhr.zzhd() > zzhr2.zzhd()) {
            return 1;
        }
        if (zzhr.zzhc() < zzhr2.zzhc()) {
            return -1;
        }
        if (zzhr.zzhc() > zzhr2.zzhc()) {
            return 1;
        }
        float zzhf = (zzhr.zzhf() - zzhr.zzhd()) * (zzhr.zzhe() - zzhr.zzhc());
        float zzhf2 = (zzhr2.zzhf() - zzhr2.zzhd()) * (zzhr2.zzhe() - zzhr2.zzhc());
        if (zzhf > zzhf2) {
            return -1;
        }
        return zzhf < zzhf2 ? 1 : 0;
    }
}
