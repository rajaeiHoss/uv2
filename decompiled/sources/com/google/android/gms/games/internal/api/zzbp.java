package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.Quests;

final class zzbp implements Quests.AcceptQuestResult {
    private /* synthetic */ Status zzetp;

    zzbp(zzbo zzbo, Status status) {
        this.zzetp = status;
    }

    public final Quest getQuest() {
        return null;
    }

    public final Status getStatus() {
        return this.zzetp;
    }
}
