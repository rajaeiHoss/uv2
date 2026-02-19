package com.google.android.gms.location.places.ui;

import android.view.View;

final class zzf implements View.OnClickListener {
    private /* synthetic */ SupportPlaceAutocompleteFragment zzizo;

    zzf(SupportPlaceAutocompleteFragment supportPlaceAutocompleteFragment) {
        this.zzizo = supportPlaceAutocompleteFragment;
    }

    public final void onClick(View view) {
        this.zzizo.setText("");
    }
}
