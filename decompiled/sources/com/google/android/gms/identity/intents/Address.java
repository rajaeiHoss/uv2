package com.google.android.gms.identity.intents;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.internal.zzcdo;

public final class Address {
    public static final Api<AddressOptions> API;
    private static Api.zzf<zzcdo> zzegu = new Api.zzf<>();
    private static final Api.zza<zzcdo, AddressOptions> zzegv;

    public static final class AddressOptions implements Api.ApiOptions.HasOptions {
        public final int theme;

        public AddressOptions() {
            this.theme = 0;
        }

        public AddressOptions(int i) {
            this.theme = i;
        }
    }

    static abstract class zza extends zzm<Status, zzcdo> {
        public zza(GoogleApiClient googleApiClient) {
            super((Api<?>) Address.API, googleApiClient);
        }

        public final Status zzb(Status status) {
            return status;
        }
    }

    static {
        Api.zza<zzcdo, AddressOptions> zza2 = new com.google.android.gms.identity.intents.zza();
        zzegv = zza2;
        API = new Api<>("Address.API", zza2, zzegu);
    }

    public static void requestUserAddress(GoogleApiClient googleApiClient, UserAddressRequest userAddressRequest, int i) {
        googleApiClient.zzd(new zzb(googleApiClient, userAddressRequest, i));
    }
}
