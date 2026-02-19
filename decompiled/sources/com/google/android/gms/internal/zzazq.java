package com.google.android.gms.internal;

import com.google.android.gms.awareness.snapshot.PlacesResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.PlaceLikelihood;
import java.util.List;

final class zzazq implements PlacesResult {
    private /* synthetic */ zzazy zzert;
    private boolean zzerw = false;
    private List<PlaceLikelihood> zzerx = null;

    zzazq(zzazp zzazp, zzazy zzazy) {
        this.zzert = zzazy;
    }

    public final List<PlaceLikelihood> getPlaceLikelihoods() {
        DataHolder zzadi;
        if (this.zzerw) {
            return this.zzerx;
        }
        this.zzerw = true;
        if (this.zzert.zzadl() == null || (zzadi = this.zzert.zzadl().zzadi()) == null) {
            return null;
        }
        zzayx zzayx = new zzayx(zzadi);
        try {
            if (zzayx.getCount() <= 0) {
                return null;
            }
            List<PlaceLikelihood> placeLikelihoods = ((zzayw) ((zzbgp) zzayx.get(0))).getPlaceLikelihoods();
            this.zzerx = placeLikelihoods;
            zzayx.release();
            return placeLikelihoods;
        } finally {
            zzayx.release();
        }
    }

    public final Status getStatus() {
        return this.zzert.getStatus();
    }
}
