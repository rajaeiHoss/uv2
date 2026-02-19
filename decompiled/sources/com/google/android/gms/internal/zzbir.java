package com.google.android.gms.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzbir {
    private static AppMeasurement zzde(Context context) {
        try {
            return AppMeasurement.getInstance(context);
        } catch (NoClassDefFoundError unused) {
            return null;
        }
    }

    public static List<zzbip> zzdf(Context context) {
        Map<String, Object> map;
        AppMeasurement zzde = zzde(context);
        if (zzde == null) {
            if (Log.isLoggable("FRCAnalytics", 3)) {
                Log.d("FRCAnalytics", "Unable to get user properties: analytics library is missing.");
            }
            return null;
        }
        try {
            map = zzde.getUserProperties(false);
        } catch (NullPointerException e) {
            if (Log.isLoggable("FRCAnalytics", 3)) {
                Log.d("FRCAnalytics", "Unable to get user properties.", e);
            }
            map = null;
        }
        if (map == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : map.entrySet()) {
            if (next.getValue() != null) {
                arrayList.add(new zzbip((String) next.getKey(), next.getValue().toString()));
            }
        }
        return arrayList;
    }
}
