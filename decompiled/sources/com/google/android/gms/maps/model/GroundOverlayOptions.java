package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class GroundOverlayOptions extends zzbgl {
    public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new zzd();
    public static final float NO_DIMENSION = -1.0f;
    private float bearing;
    private float height;
    private float width;
    private float zzjda;
    private boolean zzjdb = true;
    private boolean zzjdc = false;
    private BitmapDescriptor zzjdf;
    private LatLng zzjdg;
    private LatLngBounds zzjdh;
    private float zzjdi = 0.0f;
    private float zzjdj = 0.5f;
    private float zzjdk = 0.5f;

    public GroundOverlayOptions() {
    }

    GroundOverlayOptions(IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7, boolean z2) {
        this.zzjdf = new BitmapDescriptor(IObjectWrapper.zza.zzaq(iBinder));
        this.zzjdg = latLng;
        this.width = f;
        this.height = f2;
        this.zzjdh = latLngBounds;
        this.bearing = f3;
        this.zzjda = f4;
        this.zzjdb = z;
        this.zzjdi = f5;
        this.zzjdj = f6;
        this.zzjdk = f7;
        this.zzjdc = z2;
    }

    private final GroundOverlayOptions zza(LatLng latLng, float f, float f2) {
        this.zzjdg = latLng;
        this.width = f;
        this.height = f2;
        return this;
    }

    public final GroundOverlayOptions anchor(float f, float f2) {
        this.zzjdj = f;
        this.zzjdk = f2;
        return this;
    }

    public final GroundOverlayOptions bearing(float f) {
        this.bearing = ((f % 360.0f) + 360.0f) % 360.0f;
        return this;
    }

    public final GroundOverlayOptions clickable(boolean z) {
        this.zzjdc = z;
        return this;
    }

    public final float getAnchorU() {
        return this.zzjdj;
    }

    public final float getAnchorV() {
        return this.zzjdk;
    }

    public final float getBearing() {
        return this.bearing;
    }

    public final LatLngBounds getBounds() {
        return this.zzjdh;
    }

    public final float getHeight() {
        return this.height;
    }

    public final BitmapDescriptor getImage() {
        return this.zzjdf;
    }

    public final LatLng getLocation() {
        return this.zzjdg;
    }

    public final float getTransparency() {
        return this.zzjdi;
    }

    public final float getWidth() {
        return this.width;
    }

    public final float getZIndex() {
        return this.zzjda;
    }

    public final GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        zzbq.checkNotNull(bitmapDescriptor, "imageDescriptor must not be null");
        this.zzjdf = bitmapDescriptor;
        return this;
    }

    public final boolean isClickable() {
        return this.zzjdc;
    }

    public final boolean isVisible() {
        return this.zzjdb;
    }

    public final GroundOverlayOptions position(LatLng latLng, float f) {
        boolean z = true;
        zzbq.zza(this.zzjdh == null, (Object) "Position has already been set using positionFromBounds");
        zzbq.checkArgument(latLng != null, "Location must be specified");
        if (f < 0.0f) {
            z = false;
        }
        zzbq.checkArgument(z, "Width must be non-negative");
        return zza(latLng, f, -1.0f);
    }

    public final GroundOverlayOptions position(LatLng latLng, float f, float f2) {
        boolean z = true;
        zzbq.zza(this.zzjdh == null, (Object) "Position has already been set using positionFromBounds");
        zzbq.checkArgument(latLng != null, "Location must be specified");
        zzbq.checkArgument(f >= 0.0f, "Width must be non-negative");
        if (f2 < 0.0f) {
            z = false;
        }
        zzbq.checkArgument(z, "Height must be non-negative");
        return zza(latLng, f, f2);
    }

    public final GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        LatLng latLng = this.zzjdg;
        boolean z = latLng == null;
        String valueOf = String.valueOf(latLng);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 46);
        sb.append("Position has already been set using position: ");
        sb.append(valueOf);
        zzbq.zza(z, (Object) sb.toString());
        this.zzjdh = latLngBounds;
        return this;
    }

    public final GroundOverlayOptions transparency(float f) {
        zzbq.checkArgument(f >= 0.0f && f <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzjdi = f;
        return this;
    }

    public final GroundOverlayOptions visible(boolean z) {
        this.zzjdb = z;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzjdf.zzaxq().asBinder(), false);
        zzbgo.zza(parcel, 3, (Parcelable) getLocation(), i, false);
        zzbgo.zza(parcel, 4, getWidth());
        zzbgo.zza(parcel, 5, getHeight());
        zzbgo.zza(parcel, 6, (Parcelable) getBounds(), i, false);
        zzbgo.zza(parcel, 7, getBearing());
        zzbgo.zza(parcel, 8, getZIndex());
        zzbgo.zza(parcel, 9, isVisible());
        zzbgo.zza(parcel, 10, getTransparency());
        zzbgo.zza(parcel, 11, getAnchorU());
        zzbgo.zza(parcel, 12, getAnchorV());
        zzbgo.zza(parcel, 13, isClickable());
        zzbgo.zzai(parcel, zze);
    }

    public final GroundOverlayOptions zIndex(float f) {
        this.zzjda = f;
        return this;
    }
}
