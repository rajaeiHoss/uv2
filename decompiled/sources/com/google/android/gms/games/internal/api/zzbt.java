package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.Quests;

final class zzbt implements Quests.LoadQuestsResult {
    private /* synthetic */ Status zzetp;

    zzbt(zzbs zzbs, Status status) {
        this.zzetp = status;
    }

    public final QuestBuffer getQuests() {
        return new QuestBuffer(DataHolder.zzbz(this.zzetp.getStatusCode()));
    }

    public final Status getStatus() {
        return this.zzetp;
    }

    public final void release() {
    }
}
