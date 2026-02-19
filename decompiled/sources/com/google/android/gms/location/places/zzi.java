package com.google.android.gms.location.places;

import com.google.android.gms.location.places.internal.zzai;
import java.util.Comparator;

final class zzi implements Comparator<zzai> {
    zzi() {
    }

    public final int compare(zzai zzai, zzai zzai2) {
        return -Float.compare(zzai.getLikelihood(), zzai2.getLikelihood());
    }
}
