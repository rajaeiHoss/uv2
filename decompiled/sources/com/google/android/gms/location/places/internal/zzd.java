package com.google.android.gms.location.places.internal;

import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Collections;
import java.util.List;

public final class zzd extends zzaw implements AutocompletePrediction {
    public zzd(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    private final String zzaxe() {
        return zzad("ap_description", "");
    }

    private final String zzaxf() {
        return zzad("ap_primary_text", "");
    }

    private final String zzaxg() {
        return zzad("ap_secondary_text", "");
    }

    private final List<zzb> zzaxh() {
        return zza("ap_matched_subscriptions", zzb.CREATOR, Collections.emptyList());
    }

    private final List<zzb> zzaxi() {
        return zza("ap_primary_text_matched", zzb.CREATOR, Collections.emptyList());
    }

    private final List<zzb> zzaxj() {
        return zza("ap_secondary_text_matched", zzb.CREATOR, Collections.emptyList());
    }

    public final /* synthetic */ AutocompletePrediction freeze() {
        String placeId = getPlaceId();
        List<Integer> placeTypes = getPlaceTypes();
        int zzy = zzy("ap_personalization_type", 6);
        String zzaxe = zzaxe();
        return new zza(placeId, placeTypes, zzy, (String) zzbq.checkNotNull(zzaxe), zzaxh(), zzaxf(), zzaxi(), zzaxg(), zzaxj());
    }

    public final CharSequence getFullText(CharacterStyle characterStyle) {
        return zzg.zza(zzaxe(), zzaxh(), characterStyle);
    }

    public final String getPlaceId() {
        return zzad("ap_place_id", (String) null);
    }

    public final List<Integer> getPlaceTypes() {
        return zzc("ap_place_types", Collections.emptyList());
    }

    public final CharSequence getPrimaryText(CharacterStyle characterStyle) {
        return zzg.zza(zzaxf(), zzaxi(), characterStyle);
    }

    public final CharSequence getSecondaryText(CharacterStyle characterStyle) {
        return zzg.zza(zzaxg(), zzaxj(), characterStyle);
    }
}
