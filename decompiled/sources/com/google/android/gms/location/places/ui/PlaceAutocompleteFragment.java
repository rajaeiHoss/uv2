package com.google.android.gms.location.places.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.google.android.gms.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

public class PlaceAutocompleteFragment extends Fragment {
    private View zzizg;
    private View zzizh;
    private EditText zzizi;
    /* access modifiers changed from: private */
    public boolean zzizj;
    private LatLngBounds zzizk;
    private AutocompleteFilter zzizl;
    private PlaceSelectionListener zzizm;

    private final void zzaxn() {
        this.zzizh.setVisibility(this.zzizi.getText().toString().isEmpty() ^ true ? 0 : 8);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: com.google.android.gms.common.GooglePlayServicesRepairableException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: com.google.android.gms.common.GooglePlayServicesNotAvailableException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: com.google.android.gms.common.GooglePlayServicesRepairableException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: com.google.android.gms.common.GooglePlayServicesRepairableException} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzaxo() {
        /*
            r6 = this;
            java.lang.String r0 = "Could not open autocomplete activity"
            java.lang.String r1 = "Places"
            r2 = -1
            com.google.android.gms.location.places.ui.PlaceAutocomplete$IntentBuilder r3 = new com.google.android.gms.location.places.ui.PlaceAutocomplete$IntentBuilder     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            r4 = 2
            r3.<init>(r4)     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            com.google.android.gms.maps.model.LatLngBounds r4 = r6.zzizk     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            com.google.android.gms.location.places.ui.PlaceAutocomplete$IntentBuilder r3 = r3.setBoundsBias(r4)     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            com.google.android.gms.location.places.AutocompleteFilter r4 = r6.zzizl     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            com.google.android.gms.location.places.ui.PlaceAutocomplete$IntentBuilder r3 = r3.setFilter(r4)     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            android.widget.EditText r4 = r6.zzizi     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            android.text.Editable r4 = r4.getText()     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            java.lang.String r4 = r4.toString()     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            com.google.android.gms.location.places.ui.PlaceAutocomplete$IntentBuilder r3 = r3.zzit(r4)     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            r4 = 1
            com.google.android.gms.location.places.ui.PlaceAutocomplete$IntentBuilder r3 = r3.zzen(r4)     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            android.app.Activity r5 = r6.getActivity()     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            android.content.Intent r3 = r3.build(r5)     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            r6.zzizj = r4     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            r4 = 30421(0x76d5, float:4.2629E-41)
            r6.startActivityForResult(r3, r4)     // Catch:{ GooglePlayServicesRepairableException -> 0x003f, GooglePlayServicesNotAvailableException -> 0x003b }
            r4 = -1
            goto L_0x0047
        L_0x003b:
            r3 = move-exception
            int r4 = r3.errorCode
            goto L_0x0044
        L_0x003f:
            r3 = move-exception
            int r4 = r3.getConnectionStatusCode()
        L_0x0044:
            android.util.Log.e(r1, r0, r3)
        L_0x0047:
            if (r4 == r2) goto L_0x0056
            com.google.android.gms.common.GoogleApiAvailability r0 = com.google.android.gms.common.GoogleApiAvailability.getInstance()
            android.app.Activity r1 = r6.getActivity()
            r2 = 30422(0x76d6, float:4.263E-41)
            r0.showErrorDialogFragment(r1, r4, r2)
        L_0x0056:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.places.ui.PlaceAutocompleteFragment.zzaxo():void");
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.zzizj = false;
        if (i == 30421) {
            if (i2 == -1) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), intent);
                PlaceSelectionListener placeSelectionListener = this.zzizm;
                if (placeSelectionListener != null) {
                    placeSelectionListener.onPlaceSelected(place);
                }
                setText(place.getName().toString());
            } else if (i2 == 2) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), intent);
                PlaceSelectionListener placeSelectionListener2 = this.zzizm;
                if (placeSelectionListener2 != null) {
                    placeSelectionListener2.onError(status);
                }
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.place_autocomplete_fragment, viewGroup, false);
        this.zzizg = inflate.findViewById(R.id.place_autocomplete_search_button);
        this.zzizh = inflate.findViewById(R.id.place_autocomplete_clear_button);
        this.zzizi = (EditText) inflate.findViewById(R.id.place_autocomplete_search_input);
        zzc zzc = new zzc(this);
        this.zzizg.setOnClickListener(zzc);
        this.zzizi.setOnClickListener(zzc);
        this.zzizh.setOnClickListener(new zzd(this));
        zzaxn();
        return inflate;
    }

    public void onDestroyView() {
        this.zzizg = null;
        this.zzizh = null;
        this.zzizi = null;
        super.onDestroyView();
    }

    public void setBoundsBias(LatLngBounds latLngBounds) {
        this.zzizk = latLngBounds;
    }

    public void setFilter(AutocompleteFilter autocompleteFilter) {
        this.zzizl = autocompleteFilter;
    }

    public void setHint(CharSequence charSequence) {
        this.zzizi.setHint(charSequence);
        this.zzizg.setContentDescription(charSequence);
    }

    public void setOnPlaceSelectedListener(PlaceSelectionListener placeSelectionListener) {
        this.zzizm = placeSelectionListener;
    }

    public void setText(CharSequence charSequence) {
        this.zzizi.setText(charSequence);
        zzaxn();
    }
}
