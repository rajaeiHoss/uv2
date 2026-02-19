package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.maps.model.internal.zzaa;
import com.google.android.gms.maps.model.internal.zzz;

public final class TileOverlayOptions extends zzbgl {
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzu();
    private float zzjda;
    private boolean zzjdb = true;
    private float zzjdi = 0.0f;
    /* access modifiers changed from: private */
    public zzz zzjep;
    private TileProvider zzjeq;
    private boolean zzjer = true;

    public TileOverlayOptions() {
    }

    TileOverlayOptions(IBinder iBinder, boolean z, float f, boolean z2, float f2) {
        zzz zzbq = zzaa.zzbq(iBinder);
        this.zzjep = zzbq;
        this.zzjeq = zzbq == null ? null : new zzs(this);
        this.zzjdb = z;
        this.zzjda = f;
        this.zzjer = z2;
        this.zzjdi = f2;
    }

    public final TileOverlayOptions fadeIn(boolean z) {
        this.zzjer = z;
        return this;
    }

    public final boolean getFadeIn() {
        return this.zzjer;
    }

    public final TileProvider getTileProvider() {
        return this.zzjeq;
    }

    public final float getTransparency() {
        return this.zzjdi;
    }

    public final float getZIndex() {
        return this.zzjda;
    }

    public final boolean isVisible() {
        return this.zzjdb;
    }

    public final TileOverlayOptions tileProvider(TileProvider tileProvider) {
        this.zzjeq = tileProvider;
        this.zzjep = tileProvider == null ? null : new zzt(this, tileProvider);
        return this;
    }

    public final TileOverlayOptions transparency(float f) {
        zzbq.checkArgument(f >= 0.0f && f <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzjdi = f;
        return this;
    }

    public final TileOverlayOptions visible(boolean z) {
        this.zzjdb = z;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzjep.asBinder(), false);
        zzbgo.zza(parcel, 3, isVisible());
        zzbgo.zza(parcel, 4, getZIndex());
        zzbgo.zza(parcel, 5, getFadeIn());
        zzbgo.zza(parcel, 6, getTransparency());
        zzbgo.zzai(parcel, zze);
    }

    public final TileOverlayOptions zIndex(float f) {
        this.zzjda = f;
        return this;
    }
}
