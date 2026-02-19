package com.google.android.gms.games.quest;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

@Deprecated
public final class MilestoneBuffer extends AbstractDataBuffer<Milestone> {
    public MilestoneBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    public final Milestone get(int i) {
        return new zzb(this.zzfxb, i);
    }
}
