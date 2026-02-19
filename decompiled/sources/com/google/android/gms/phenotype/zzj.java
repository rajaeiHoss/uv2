package com.google.android.gms.phenotype;

import java.util.Comparator;

final class zzj implements Comparator<zzi> {
    zzj() {
    }

    public final int compare(zzi zzi, zzi zzi2) {
        return zzi.zzkgp == zzi2.zzkgp ? zzi.name.compareTo(zzi2.name) : zzi.zzkgp - zzi2.zzkgp;
    }
}
