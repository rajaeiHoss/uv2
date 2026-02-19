package com.google.android.gms.location.places.ui;

import android.view.View;

final class zzc implements View.OnClickListener {
    private /* synthetic */ PlaceAutocompleteFragment zzizn;

    zzc(PlaceAutocompleteFragment placeAutocompleteFragment) {
        this.zzizn = placeAutocompleteFragment;
    }

    public final void onClick(View view) {
        if (!this.zzizn.zzizj) {
            this.zzizn.zzaxo();
        }
    }
}
