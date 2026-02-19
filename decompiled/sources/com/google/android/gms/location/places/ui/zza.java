package com.google.android.gms.location.places.ui;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgq;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.internal.PlaceEntity;

class zza {
    public static final int RESULT_ERROR = 2;

    zza() {
    }

    public static Place getPlace(Context context, Intent intent) {
        zzbq.checkNotNull(intent, "intent must not be null");
        zzbq.checkNotNull(context, "context must not be null");
        return (Place) zzbgq.zza(intent, "selected_place", PlaceEntity.CREATOR);
    }

    public static Status getStatus(Context context, Intent intent) {
        zzbq.checkNotNull(intent, "intent must not be null");
        zzbq.checkNotNull(context, "context must not be null");
        return (Status) zzbgq.zza(intent, "status", Status.CREATOR);
    }
}
