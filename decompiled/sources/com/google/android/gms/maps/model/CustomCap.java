package com.google.android.gms.maps.model;

public final class CustomCap extends Cap {
    public final BitmapDescriptor bitmapDescriptor;
    public final float refWidth;

    public CustomCap(BitmapDescriptor bitmapDescriptor2) {
        this(bitmapDescriptor2, 10.0f);
    }

    public CustomCap(BitmapDescriptor bitmapDescriptor2, float f) {
        super((BitmapDescriptor) com.google.android.gms.common.internal.zzbq.checkNotNull(bitmapDescriptor2, "bitmapDescriptor must not be null"), f);
        this.bitmapDescriptor = bitmapDescriptor2;
        this.refWidth = f;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.bitmapDescriptor);
        float f = this.refWidth;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 55);
        sb.append("[CustomCap: bitmapDescriptor=");
        sb.append(valueOf);
        sb.append(" refWidth=");
        sb.append(f);
        sb.append("]");
        return sb.toString();
    }
}
