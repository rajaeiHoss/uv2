package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class PlaceEntity extends zzbgl implements ReflectedParcelable, Place {
    public static final Parcelable.Creator<PlaceEntity> CREATOR = new zzaf();
    private final String mName;
    private final String zzbzd;
    private final LatLng zziux;
    private final String zziuy;
    private final List<Integer> zziuz;
    private final String zziva;
    private final Uri zzivb;
    private Locale zziwm;
    private final Bundle zzixn;
    @Deprecated
    private final zzal zzixo;
    private final float zzixp;
    private final LatLngBounds zzixq;
    private final String zzixr;
    private final boolean zzixs;
    private final float zzixt;
    private final int zzixu;
    private final List<Integer> zzixv;
    private final String zzixw;
    private final List<String> zzixx;
    private final zzan zzixy;
    private final zzag zzixz;
    private final String zziya;
    private final Map<Integer, String> zziyb;
    private final TimeZone zziyc;

    public static class zza {
        private String mName;
        private String zzbzd;
        private LatLng zziux;
        private String zziuy;
        private String zziva;
        private Uri zzivb;
        private float zzixp;
        private LatLngBounds zzixq;
        private boolean zzixs;
        private float zzixt = -1.0f;
        private int zzixu = -1;
        private List<String> zzixx;
        private zzan zzixy;
        private String zziya;
        private List<Integer> zziyd;
        private zzag zziye;

        public final zza zza(zzag zzag) {
            this.zziye = zzag;
            return this;
        }

        public final zza zza(zzan zzan) {
            this.zzixy = zzan;
            return this;
        }

        public final zza zza(LatLng latLng) {
            this.zziux = latLng;
            return this;
        }

        public final zza zza(LatLngBounds latLngBounds) {
            this.zzixq = latLngBounds;
            return this;
        }

        public final zza zzaf(List<Integer> list) {
            this.zziyd = list;
            return this;
        }

        public final zza zzag(List<String> list) {
            this.zzixx = list;
            return this;
        }

        public final PlaceEntity zzaxk() {
            String str = this.zzbzd;
            List<Integer> list = this.zziyd;
            List emptyList = Collections.emptyList();
            String str2 = this.mName;
            String str3 = this.zziuy;
            String str4 = this.zziva;
            List<String> list2 = this.zzixx;
            zzal zzal2 = new zzal(str2, str3, str4, (String) null, list2);
            return new PlaceEntity(str, list, emptyList, (Bundle) null, str2, str3, str4, (String) null, list2, this.zziux, this.zzixp, this.zzixq, (String) null, this.zzivb, this.zzixs, this.zzixt, this.zzixu, zzal2, this.zzixy, this.zziye, this.zziya);
        }

        public final zza zzbp(boolean z) {
            this.zzixs = z;
            return this;
        }

        public final zza zzc(float f) {
            this.zzixp = f;
            return this;
        }

        public final zza zzd(float f) {
            this.zzixt = f;
            return this;
        }

        public final zza zzem(int i) {
            this.zzixu = i;
            return this;
        }

        public final zza zzio(String str) {
            this.zzbzd = str;
            return this;
        }

        public final zza zzip(String str) {
            this.mName = str;
            return this;
        }

        public final zza zziq(String str) {
            this.zziuy = str;
            return this;
        }

        public final zza zzir(String str) {
            this.zziva = str;
            return this;
        }

        public final zza zzis(String str) {
            this.zziya = str;
            return this;
        }

        public final zza zzo(Uri uri) {
            this.zzivb = uri;
            return this;
        }
    }

    PlaceEntity(String str, List<Integer> list, List<Integer> list2, Bundle bundle, String str2, String str3, String str4, String str5, List<String> list3, LatLng latLng, float f, LatLngBounds latLngBounds, String str6, Uri uri, boolean z, float f2, int i, zzal zzal, zzan zzan, zzag zzag, String str7) {
        this.zzbzd = str;
        this.zziuz = Collections.unmodifiableList(list);
        this.zzixv = list2;
        this.zzixn = bundle != null ? bundle : new Bundle();
        this.mName = str2;
        this.zziuy = str3;
        this.zziva = str4;
        this.zzixw = str5;
        this.zzixx = list3 != null ? list3 : Collections.emptyList();
        this.zziux = latLng;
        this.zzixp = f;
        this.zzixq = latLngBounds;
        this.zzixr = str6 != null ? str6 : "UTC";
        this.zzivb = uri;
        this.zzixs = z;
        this.zzixt = f2;
        this.zzixu = i;
        this.zziyb = Collections.unmodifiableMap(new HashMap());
        this.zziyc = null;
        this.zziwm = null;
        this.zzixo = zzal;
        this.zzixy = zzan;
        this.zzixz = zzag;
        this.zziya = str7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceEntity)) {
            return false;
        }
        PlaceEntity placeEntity = (PlaceEntity) obj;
        return this.zzbzd.equals(placeEntity.zzbzd) && zzbg.equal(this.zziwm, placeEntity.zziwm);
    }

    public final /* bridge */ /* synthetic */ Place freeze() {
        return this;
    }

    public final /* synthetic */ CharSequence getAddress() {
        return this.zziuy;
    }

    public final CharSequence getAttributions() {
        return zzg.zzk(this.zzixx);
    }

    public final String getId() {
        return this.zzbzd;
    }

    public final LatLng getLatLng() {
        return this.zziux;
    }

    public final Locale getLocale() {
        return this.zziwm;
    }

    public final /* synthetic */ CharSequence getName() {
        return this.mName;
    }

    public final /* synthetic */ CharSequence getPhoneNumber() {
        return this.zziva;
    }

    public final List<Integer> getPlaceTypes() {
        return this.zziuz;
    }

    public final int getPriceLevel() {
        return this.zzixu;
    }

    public final float getRating() {
        return this.zzixt;
    }

    public final LatLngBounds getViewport() {
        return this.zzixq;
    }

    public final Uri getWebsiteUri() {
        return this.zzivb;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzbzd, this.zziwm});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final void setLocale(Locale locale) {
        this.zziwm = locale;
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("id", this.zzbzd).zzg("placeTypes", this.zziuz).zzg("locale", this.zziwm).zzg("name", this.mName).zzg("address", this.zziuy).zzg("phoneNumber", this.zziva).zzg("latlng", this.zziux).zzg("viewport", this.zzixq).zzg("websiteUri", this.zzivb).zzg("isPermanentlyClosed", Boolean.valueOf(this.zzixs)).zzg("priceLevel", Integer.valueOf(this.zzixu)).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getId(), false);
        zzbgo.zza(parcel, 2, this.zzixn, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzixo, i, false);
        zzbgo.zza(parcel, 4, (Parcelable) getLatLng(), i, false);
        zzbgo.zza(parcel, 5, this.zzixp);
        zzbgo.zza(parcel, 6, (Parcelable) getViewport(), i, false);
        zzbgo.zza(parcel, 7, this.zzixr, false);
        zzbgo.zza(parcel, 8, (Parcelable) getWebsiteUri(), i, false);
        zzbgo.zza(parcel, 9, this.zzixs);
        zzbgo.zza(parcel, 10, getRating());
        zzbgo.zzc(parcel, 11, getPriceLevel());
        zzbgo.zza(parcel, 13, this.zzixv, false);
        zzbgo.zza(parcel, 14, (String) getAddress(), false);
        zzbgo.zza(parcel, 15, (String) getPhoneNumber(), false);
        zzbgo.zza(parcel, 16, this.zzixw, false);
        zzbgo.zzb(parcel, 17, this.zzixx, false);
        zzbgo.zza(parcel, 19, (String) getName(), false);
        zzbgo.zza(parcel, 20, getPlaceTypes(), false);
        zzbgo.zza(parcel, 21, (Parcelable) this.zzixy, i, false);
        zzbgo.zza(parcel, 22, (Parcelable) this.zzixz, i, false);
        zzbgo.zza(parcel, 23, this.zziya, false);
        zzbgo.zzai(parcel, zze);
    }
}
