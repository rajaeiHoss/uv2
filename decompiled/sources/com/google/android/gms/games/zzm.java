package com.google.android.gms.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;

final class zzm implements Games.GetServerAuthCodeResult {
    private /* synthetic */ Status zzetp;

    zzm(Games.zzc zzc, Status status) {
        this.zzetp = status;
    }

    public final String getCode() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
