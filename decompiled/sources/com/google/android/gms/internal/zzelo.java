package com.google.android.gms.internal;

import java.util.Comparator;

final class zzelo implements Comparator<zzelj> {
    private /* synthetic */ zzeln zznmf;

    zzelo(zzeln zzeln) {
        this.zznmf = zzeln;
    }

    public final int compare(zzelj zzelj, zzelj zzelj2) {
        return this.zznmf.zznme.compare(new zzenm(zzelj.zzcam(), zzelj.zzcak().zzbve()), new zzenm(zzelj2.zzcam(), zzelj2.zzcak().zzbve()));
    }
}
