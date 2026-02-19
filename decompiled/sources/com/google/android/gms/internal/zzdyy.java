package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.firebase.appinvite.FirebaseAppInvite;

public final class zzdyy extends FirebaseAppInvite {
    private final Bundle zzmon;

    public zzdyy(Bundle bundle) {
        this.zzmon = bundle;
    }

    public final String getInvitationId() {
        return this.zzmon.getString("com.google.firebase.appinvite.fdl.extension.InvitationId", (String) null);
    }
}
