package com.google.android.gms.games.internal.api;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests;
import java.util.ArrayList;
import java.util.List;

public final class zzbv implements Requests {
    public final PendingResult<Requests.UpdateRequestsResult> acceptRequest(GoogleApiClient googleApiClient, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return acceptRequests(googleApiClient, arrayList);
    }

    public final PendingResult<Requests.UpdateRequestsResult> acceptRequests(GoogleApiClient googleApiClient, List<String> list) {
        return googleApiClient.zze(new zzbw(this, googleApiClient, list == null ? null : (String[]) list.toArray(new String[list.size()])));
    }

    public final PendingResult<Requests.UpdateRequestsResult> dismissRequest(GoogleApiClient googleApiClient, String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return dismissRequests(googleApiClient, arrayList);
    }

    public final PendingResult<Requests.UpdateRequestsResult> dismissRequests(GoogleApiClient googleApiClient, List<String> list) {
        return googleApiClient.zze(new zzbx(this, googleApiClient, list == null ? null : (String[]) list.toArray(new String[list.size()])));
    }

    public final ArrayList<GameRequest> getGameRequestsFromBundle(Bundle bundle) {
        if (bundle == null || !bundle.containsKey(Requests.EXTRA_REQUESTS)) {
            return new ArrayList<>();
        }
        ArrayList arrayList = (ArrayList) bundle.get(Requests.EXTRA_REQUESTS);
        ArrayList<GameRequest> arrayList2 = new ArrayList<>();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList2.add((GameRequest) arrayList.get(i));
        }
        return arrayList2;
    }

    public final ArrayList<GameRequest> getGameRequestsFromInboxResponse(Intent intent) {
        return intent == null ? new ArrayList<>() : getGameRequestsFromBundle(intent.getExtras());
    }

    public final Intent getInboxIntent(GoogleApiClient googleApiClient) {
        return Games.zzg(googleApiClient).zzaue();
    }

    public final int getMaxLifetimeDays(GoogleApiClient googleApiClient) {
        return Games.zzg(googleApiClient).zzaug();
    }

    public final int getMaxPayloadSize(GoogleApiClient googleApiClient) {
        return Games.zzg(googleApiClient).zzauf();
    }

    public final Intent getSendIntent(GoogleApiClient googleApiClient, int i, byte[] bArr, int i2, Bitmap bitmap, String str) {
        return Games.zzg(googleApiClient).zza(i, bArr, i2, bitmap, str);
    }

    public final PendingResult<Requests.LoadRequestsResult> loadRequests(GoogleApiClient googleApiClient, int i, int i2, int i3) {
        return googleApiClient.zzd(new zzby(this, googleApiClient, i, i2, i3));
    }

    public final void registerRequestListener(GoogleApiClient googleApiClient, OnRequestReceivedListener onRequestReceivedListener) {
        GamesClientImpl zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzj(googleApiClient.zzt(onRequestReceivedListener));
        }
    }

    public final void unregisterRequestListener(GoogleApiClient googleApiClient) {
        GamesClientImpl zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzatu();
        }
    }
}
