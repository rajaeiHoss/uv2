package com.google.firebase.auth;

import com.google.android.gms.internal.zzbgp;
import java.util.Map;

public interface AdditionalUserInfo extends zzbgp {
    Map<String, Object> getProfile();

    String getProviderId();

    String getUsername();

    boolean isNewUser();
}
