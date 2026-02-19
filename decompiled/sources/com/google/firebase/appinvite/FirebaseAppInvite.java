package com.google.firebase.appinvite;

import android.os.Bundle;
import com.google.android.gms.internal.zzdyy;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

public abstract class FirebaseAppInvite {
    public static FirebaseAppInvite getInvitation(PendingDynamicLinkData pendingDynamicLinkData) {
        Bundle zzcds = pendingDynamicLinkData.zzcds();
        if (zzcds == null || zzcds.getString("com.google.firebase.appinvite.fdl.extension.InvitationId", (String) null) == null) {
            return null;
        }
        return new zzdyy(zzcds);
    }

    public abstract String getInvitationId();
}
