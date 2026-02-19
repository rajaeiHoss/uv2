package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Deprecated
public class AddPlaceRequest extends zzbgl {
    public static final Parcelable.Creator<AddPlaceRequest> CREATOR = new zzb();
    private final String mName;
    private final LatLng zziux;
    private final String zziuy;
    private final List<Integer> zziuz;
    private final String zziva;
    private final Uri zzivb;

    public AddPlaceRequest(String str, LatLng latLng, String str2, List<Integer> list, Uri uri) {
        this(str, latLng, str2, list, (String) null, (Uri) zzbq.checkNotNull(uri));
    }

    public AddPlaceRequest(String str, LatLng latLng, String str2, List<Integer> list, String str3) {
        this(str, latLng, str2, list, zzbq.zzgv(str3), (Uri) null);
    }

    public AddPlaceRequest(String str, LatLng latLng, String str2, List<Integer> list, String str3, Uri uri) {
        this.mName = zzbq.zzgv(str);
        this.zziux = (LatLng) zzbq.checkNotNull(latLng);
        this.zziuy = zzbq.zzgv(str2);
        ArrayList arrayList = new ArrayList((Collection) zzbq.checkNotNull(list));
        this.zziuz = arrayList;
        boolean z = true;
        zzbq.checkArgument(!arrayList.isEmpty(), "At least one place type should be provided.");
        if (TextUtils.isEmpty(str3) && uri == null) {
            z = false;
        }
        zzbq.checkArgument(z, "One of phone number or URI should be provided.");
        this.zziva = str3;
        this.zzivb = uri;
    }

    public String getAddress() {
        return this.zziuy;
    }

    public LatLng getLatLng() {
        return this.zziux;
    }

    public String getName() {
        return this.mName;
    }

    public String getPhoneNumber() {
        return this.zziva;
    }

    public List<Integer> getPlaceTypes() {
        return this.zziuz;
    }

    public Uri getWebsiteUri() {
        return this.zzivb;
    }

    public String toString() {
        return zzbg.zzx(this).zzg("name", this.mName).zzg("latLng", this.zziux).zzg("address", this.zziuy).zzg("placeTypes", this.zziuz).zzg("phoneNumer", this.zziva).zzg("websiteUri", this.zzivb).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getName(), false);
        zzbgo.zza(parcel, 2, (Parcelable) getLatLng(), i, false);
        zzbgo.zza(parcel, 3, getAddress(), false);
        zzbgo.zza(parcel, 4, getPlaceTypes(), false);
        zzbgo.zza(parcel, 5, getPhoneNumber(), false);
        zzbgo.zza(parcel, 6, (Parcelable) getWebsiteUri(), i, false);
        zzbgo.zzai(parcel, zze);
    }
}
