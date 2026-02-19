package com.google.android.gms.games.multiplayer;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;

public final class ParticipantBuffer extends AbstractDataBuffer<Participant> {
    public ParticipantBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    public final Participant get(int i) {
        return new ParticipantRef(this.zzfxb, i);
    }
}
