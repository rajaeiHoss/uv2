package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;

public final class zzb extends zzc implements Invitation {
    private final Game zzidx;
    private final ArrayList<Participant> zzifc;
    private final ParticipantRef zziff;

    zzb(DataHolder dataHolder, int i, int i2) {
        super(dataHolder, i);
        this.zzidx = new GameRef(dataHolder, i);
        this.zzifc = new ArrayList<>(i2);
        String string = getString("external_inviter_id");
        ParticipantRef participantRef = null;
        for (int i3 = 0; i3 < i2; i3++) {
            ParticipantRef participantRef2 = new ParticipantRef(this.zzfxb, this.zzgch + i3);
            if (participantRef2.getParticipantId().equals(string)) {
                participantRef = participantRef2;
            }
            this.zzifc.add(participantRef2);
        }
        this.zziff = (ParticipantRef) zzbq.checkNotNull(participantRef, "Must have a valid inviter!");
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return InvitationEntity.zza(this, obj);
    }

    public final Invitation freeze() {
        return new InvitationEntity(this);
    }

    public final int getAvailableAutoMatchSlots() {
        if (!getBoolean("has_automatch_criteria")) {
            return 0;
        }
        return getInteger("automatch_max_players");
    }

    public final long getCreationTimestamp() {
        return Math.max(getLong("creation_timestamp"), getLong("last_modified_timestamp"));
    }

    public final Game getGame() {
        return this.zzidx;
    }

    public final String getInvitationId() {
        return getString("external_invitation_id");
    }

    public final int getInvitationType() {
        return getInteger(AppMeasurement.Param.TYPE);
    }

    public final Participant getInviter() {
        return this.zziff;
    }

    public final ArrayList<Participant> getParticipants() {
        return this.zzifc;
    }

    public final int getVariant() {
        return getInteger("variant");
    }

    public final int hashCode() {
        return InvitationEntity.zza(this);
    }

    public final String toString() {
        return InvitationEntity.zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((InvitationEntity) ((Invitation) freeze())).writeToParcel(parcel, i);
    }
}
