package com.google.android.gms.games.internal.api;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;

public final class zzbj implements Quests {
    public final PendingResult<Quests.AcceptQuestResult> accept(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.zze(new zzbk(this, googleApiClient, str));
    }

    public final PendingResult<Quests.ClaimMilestoneResult> claim(GoogleApiClient googleApiClient, String str, String str2) {
        return googleApiClient.zze(new zzbl(this, googleApiClient, str, str2));
    }

    public final Intent getQuestIntent(GoogleApiClient googleApiClient, String str) {
        return Games.zzg(googleApiClient).zzia(str);
    }

    public final Intent getQuestsIntent(GoogleApiClient googleApiClient, int[] iArr) {
        return Games.zzg(googleApiClient).zzc(iArr);
    }

    public final PendingResult<Quests.LoadQuestsResult> load(GoogleApiClient googleApiClient, int[] iArr, int i, boolean z) {
        return googleApiClient.zzd(new zzbm(this, googleApiClient, iArr, i, z));
    }

    public final PendingResult<Quests.LoadQuestsResult> loadByIds(GoogleApiClient googleApiClient, boolean z, String... strArr) {
        return googleApiClient.zzd(new zzbn(this, googleApiClient, z, strArr));
    }

    public final void registerQuestUpdateListener(GoogleApiClient googleApiClient, QuestUpdateListener questUpdateListener) {
        GamesClientImpl zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzi((zzci<QuestUpdateListener>) googleApiClient.zzt(questUpdateListener));
        }
    }

    public final void showStateChangedPopup(GoogleApiClient googleApiClient, String str) {
        GamesClientImpl zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzib(str);
        }
    }

    public final void unregisterQuestUpdateListener(GoogleApiClient googleApiClient) {
        GamesClientImpl zza = Games.zza(googleApiClient, false);
        if (zza != null) {
            zza.zzatt();
        }
    }
}
