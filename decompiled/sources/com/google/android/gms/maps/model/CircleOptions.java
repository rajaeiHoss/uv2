package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.view.ViewCompat;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.List;

public final class CircleOptions extends zzbgl {
    public static final Parcelable.Creator<CircleOptions> CREATOR = new zzc();
    private int fillColor = 0;
    private int strokeColor = ViewCompat.MEASURED_STATE_MASK;
    private LatLng zzjcx = null;
    private double zzjcy = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    private float zzjcz = 10.0f;
    private float zzjda = 0.0f;
    private boolean zzjdb = true;
    private boolean zzjdc = false;
    private List<PatternItem> zzjdd = null;

    public CircleOptions() {
    }

    CircleOptions(LatLng latLng, double d, float f, int i, int i2, float f2, boolean z, boolean z2, List<PatternItem> list) {
        this.zzjcx = latLng;
        this.zzjcy = d;
        this.zzjcz = f;
        this.strokeColor = i;
        this.fillColor = i2;
        this.zzjda = f2;
        this.zzjdb = z;
        this.zzjdc = z2;
        this.zzjdd = list;
    }

    public final CircleOptions center(LatLng latLng) {
        this.zzjcx = latLng;
        return this;
    }

    public final CircleOptions clickable(boolean z) {
        this.zzjdc = z;
        return this;
    }

    public final CircleOptions fillColor(int i) {
        this.fillColor = i;
        return this;
    }

    public final LatLng getCenter() {
        return this.zzjcx;
    }

    public final int getFillColor() {
        return this.fillColor;
    }

    public final double getRadius() {
        return this.zzjcy;
    }

    public final int getStrokeColor() {
        return this.strokeColor;
    }

    public final List<PatternItem> getStrokePattern() {
        return this.zzjdd;
    }

    public final float getStrokeWidth() {
        return this.zzjcz;
    }

    public final float getZIndex() {
        return this.zzjda;
    }

    public final boolean isClickable() {
        return this.zzjdc;
    }

    public final boolean isVisible() {
        return this.zzjdb;
    }

    public final CircleOptions radius(double d) {
        this.zzjcy = d;
        return this;
    }

    public final CircleOptions strokeColor(int i) {
        this.strokeColor = i;
        return this;
    }

    public final CircleOptions strokePattern(List<PatternItem> list) {
        this.zzjdd = list;
        return this;
    }

    public final CircleOptions strokeWidth(float f) {
        this.zzjcz = f;
        return this;
    }

    public final CircleOptions visible(boolean z) {
        this.zzjdb = z;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) getCenter(), i, false);
        zzbgo.zza(parcel, 3, getRadius());
        zzbgo.zza(parcel, 4, getStrokeWidth());
        zzbgo.zzc(parcel, 5, getStrokeColor());
        zzbgo.zzc(parcel, 6, getFillColor());
        zzbgo.zza(parcel, 7, getZIndex());
        zzbgo.zza(parcel, 8, isVisible());
        zzbgo.zza(parcel, 9, isClickable());
        zzbgo.zzc(parcel, 10, getStrokePattern(), false);
        zzbgo.zzai(parcel, zze);
    }

    public final CircleOptions zIndex(float f) {
        this.zzjda = f;
        return this;
    }
}
