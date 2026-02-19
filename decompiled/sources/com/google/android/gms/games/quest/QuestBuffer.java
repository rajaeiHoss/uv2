package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzg;

@Deprecated
public final class QuestBuffer extends zzg<Quest> {
    public QuestBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    /* access modifiers changed from: protected */
    public final String zzalj() {
        return "external_quest_id";
    }

    /* access modifiers changed from: protected */
    public final Quest zzl(int i, int i2) {
        return new QuestRef(this.zzfxb, i, i2);
    }
}
