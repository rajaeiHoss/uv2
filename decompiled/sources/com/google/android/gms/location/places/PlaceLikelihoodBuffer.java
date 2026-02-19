package com.google.android.gms.location.places;

import android.os.Bundle;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.location.places.internal.zzai;
import com.google.android.gms.location.places.internal.zzak;
import java.util.Comparator;

public class PlaceLikelihoodBuffer extends AbstractDataBuffer<PlaceLikelihood> implements Result {
    private static final Comparator<zzai> zzivu = new zzi();
    private final Status mStatus;
    private final int zzbkq;
    private final String zzivi;
    private final boolean zzivv;

    public PlaceLikelihoodBuffer(DataHolder dataHolder, int i) {
        this(dataHolder, false, i);
    }

    private PlaceLikelihoodBuffer(DataHolder dataHolder, boolean z, int i) {
        super(dataHolder);
        this.mStatus = PlacesStatusCodes.zzcm(dataHolder.getStatusCode());
        switch (i) {
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
                this.zzbkq = i;
                this.zzivv = false;
                this.zzivi = (dataHolder == null || dataHolder.zzahs() == null) ? null : dataHolder.zzahs().getString("com.google.android.gms.location.places.PlaceLikelihoodBuffer.ATTRIBUTIONS_EXTRA_KEY");
                return;
            default:
                StringBuilder sb = new StringBuilder(27);
                sb.append("invalid source: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    public static int zzab(Bundle bundle) {
        return bundle.getInt("com.google.android.gms.location.places.PlaceLikelihoodBuffer.SOURCE_EXTRA_KEY");
    }

    public PlaceLikelihood get(int i) {
        return new zzak(this.zzfxb, i);
    }

    public CharSequence getAttributions() {
        return this.zzivi;
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public String toString() {
        return zzbg.zzx(this).zzg("status", getStatus()).zzg("attributions", this.zzivi).toString();
    }
}
