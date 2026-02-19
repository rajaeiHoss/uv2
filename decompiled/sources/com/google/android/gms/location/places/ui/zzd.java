package com.google.android.gms.location.places.ui;

import android.view.View;

final class zzd implements View.OnClickListener {
    private /* synthetic */ PlaceAutocompleteFragment zzizn;

    zzd(PlaceAutocompleteFragment placeAutocompleteFragment) {
        this.zzizn = placeAutocompleteFragment;
    }

    public final void onClick(View view) {
        this.zzizn.setText("");
    }
}
