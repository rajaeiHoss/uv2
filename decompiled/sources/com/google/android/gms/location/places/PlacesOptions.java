package com.google.android.gms.location.places;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;
import java.util.Locale;

public final class PlacesOptions implements Api.ApiOptions.Optional {
    public final String zzehk;
    public final String zziwj;
    public final String zziwk;
    public final int zziwl;
    public final Locale zziwm;

    public static class Builder {
        private int zziwl = 0;

        public PlacesOptions build() {
            return new PlacesOptions(this);
        }
    }

    private PlacesOptions(Builder builder) {
        this.zziwj = null;
        this.zziwk = null;
        this.zziwl = 0;
        this.zzehk = null;
        this.zziwm = null;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof PlacesOptions) && zzbg.equal((Object) null, (Object) null) && zzbg.equal((Object) null, (Object) null) && zzbg.equal(0, 0) && zzbg.equal((Object) null, (Object) null) && zzbg.equal((Object) null, (Object) null);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{null, null, 0, null, null});
    }
}
