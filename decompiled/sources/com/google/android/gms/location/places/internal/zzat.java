package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.internal.PlaceEntity;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public final class zzat extends zzaw implements Place {
    private final String zzivz = zzad("place_id", "");
    private final zzag zzixz;

    public zzat(com.google.android.gms.common.data.DataHolder dataHolder, int i) {
        super(dataHolder, i);
        boolean z = false;
        if (!getPlaceTypes().isEmpty()) {
            z = true;
        } else {
            CharSequence phoneNumber = getPhoneNumber();
            if (phoneNumber != null && phoneNumber.length() > 0) {
                z = true;
            } else {
                Uri websiteUri = getWebsiteUri();
                if (websiteUri != null && !Uri.EMPTY.equals(websiteUri)) {
                    z = true;
                } else if (getRating() >= 0.0f || getPriceLevel() >= 0) {
                    z = true;
                }
            }
        }
        zzag zzag = null;
        if (z) {
            List<Integer> placeTypes = getPlaceTypes();
            CharSequence phoneNumber2 = getPhoneNumber();
            String phoneString = phoneNumber2 != null ? phoneNumber2.toString() : null;
            zzag = new zzag(placeTypes, phoneString, getWebsiteUri(), getRating(), getPriceLevel());
        }
        this.zzixz = zzag;
    }

    private final List<String> zzaxm() {
        return zzd("place_attributions", Collections.emptyList());
    }

    public final /* synthetic */ Place freeze() {
        PlaceEntity zzaxk = new PlaceEntity.zza().zziq(getAddress().toString()).zzag(zzaxm()).zzio(getId()).zzbp((!zzgj("place_is_permanently_closed") || zzgl("place_is_permanently_closed")) ? false : getBoolean("place_is_permanently_closed")).zza(getLatLng()).zzc(zza("place_level_number", 0.0f)).zzip(getName().toString()).zzir(getPhoneNumber().toString()).zzem(getPriceLevel()).zzd(getRating()).zzaf(getPlaceTypes()).zza(getViewport()).zzo(getWebsiteUri()).zza((zzan) zza("place_opening_hours", zzan.CREATOR)).zza(this.zzixz).zzis(zzad("place_adr_address", "")).zzaxk();
        zzaxk.setLocale(getLocale());
        return zzaxk;
    }

    public final CharSequence getAddress() {
        return zzad("place_address", "");
    }

    public final CharSequence getAttributions() {
        return zzg.zzk(zzaxm());
    }

    public final String getId() {
        return this.zzivz;
    }

    public final LatLng getLatLng() {
        return (LatLng) zza("place_lat_lng", LatLng.CREATOR);
    }

    public final Locale getLocale() {
        String zzad = zzad("place_locale_language", "");
        if (!TextUtils.isEmpty(zzad)) {
            return new Locale(zzad, zzad("place_locale_country", ""));
        }
        String zzad2 = zzad("place_locale", "");
        return !TextUtils.isEmpty(zzad2) ? new Locale(zzad2) : Locale.getDefault();
    }

    public final CharSequence getName() {
        return zzad("place_name", "");
    }

    public final CharSequence getPhoneNumber() {
        return zzad("place_phone_number", "");
    }

    public final List<Integer> getPlaceTypes() {
        return zzc("place_types", Collections.emptyList());
    }

    public final int getPriceLevel() {
        return zzy("place_price_level", -1);
    }

    public final float getRating() {
        return zza("place_rating", -1.0f);
    }

    public final LatLngBounds getViewport() {
        return (LatLngBounds) zza("place_viewport", LatLngBounds.CREATOR);
    }

    public final Uri getWebsiteUri() {
        String zzad = zzad("place_website_uri", (String) null);
        if (zzad == null) {
            return null;
        }
        return Uri.parse(zzad);
    }
}
