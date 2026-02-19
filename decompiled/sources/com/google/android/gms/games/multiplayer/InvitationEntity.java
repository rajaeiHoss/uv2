package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;

public final class InvitationEntity extends GamesDowngradeableSafeParcel implements Invitation {
    public static final Parcelable.Creator<InvitationEntity> CREATOR = new zza();
    private final long mCreationTimestamp;
    private final String zzeha;
    private final GameEntity zzibx;
    private final int zzifa;
    private final ParticipantEntity zzifb;
    private final ArrayList<ParticipantEntity> zzifc;
    private final int zzifd;
    private final int zzife;

    static final class zza extends com.google.android.gms.games.multiplayer.zza {
        zza() {
        }

        public final InvitationEntity createFromParcel(Parcel parcel) {
            return zzq(parcel);
        }

        public final InvitationEntity zzq(Parcel parcel) {
            if (InvitationEntity.zze(InvitationEntity.zzamq()) || InvitationEntity.zzgq(InvitationEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            GameEntity createFromParcel = GameEntity.CREATOR.createFromParcel(parcel);
            String readString = parcel.readString();
            long readLong = parcel.readLong();
            int readInt = parcel.readInt();
            ParticipantEntity createFromParcel2 = ParticipantEntity.CREATOR.createFromParcel(parcel);
            int readInt2 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt2);
            for (int i = 0; i < readInt2; i++) {
                arrayList.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new InvitationEntity(createFromParcel, readString, readLong, readInt, createFromParcel2, arrayList, -1, 0);
        }
    }

    InvitationEntity(GameEntity gameEntity, String str, long j, int i, ParticipantEntity participantEntity, ArrayList<ParticipantEntity> arrayList, int i2, int i3) {
        this.zzibx = gameEntity;
        this.zzeha = str;
        this.mCreationTimestamp = j;
        this.zzifa = i;
        this.zzifb = participantEntity;
        this.zzifc = arrayList;
        this.zzifd = i2;
        this.zzife = i3;
    }

    InvitationEntity(Invitation invitation) {
        this.zzibx = new GameEntity(invitation.getGame());
        this.zzeha = invitation.getInvitationId();
        this.mCreationTimestamp = invitation.getCreationTimestamp();
        this.zzifa = invitation.getInvitationType();
        this.zzifd = invitation.getVariant();
        this.zzife = invitation.getAvailableAutoMatchSlots();
        String participantId = invitation.getInviter().getParticipantId();
        ArrayList<Participant> participants = invitation.getParticipants();
        int size = participants.size();
        this.zzifc = new ArrayList<>(size);
        Participant participant = null;
        for (int i = 0; i < size; i++) {
            Participant participant2 = participants.get(i);
            if (participant2.getParticipantId().equals(participantId)) {
                participant = participant2;
            }
            this.zzifc.add((ParticipantEntity) participant2.freeze());
        }
        zzbq.checkNotNull(participant, "Must have a valid inviter!");
        this.zzifb = (ParticipantEntity) participant.freeze();
    }

    static int zza(Invitation invitation) {
        return Arrays.hashCode(new Object[]{invitation.getGame(), invitation.getInvitationId(), Long.valueOf(invitation.getCreationTimestamp()), Integer.valueOf(invitation.getInvitationType()), invitation.getInviter(), invitation.getParticipants(), Integer.valueOf(invitation.getVariant()), Integer.valueOf(invitation.getAvailableAutoMatchSlots())});
    }

    static boolean zza(Invitation invitation, Object obj) {
        if (!(obj instanceof Invitation)) {
            return false;
        }
        if (invitation == obj) {
            return true;
        }
        Invitation invitation2 = (Invitation) obj;
        return zzbg.equal(invitation2.getGame(), invitation.getGame()) && zzbg.equal(invitation2.getInvitationId(), invitation.getInvitationId()) && zzbg.equal(Long.valueOf(invitation2.getCreationTimestamp()), Long.valueOf(invitation.getCreationTimestamp())) && zzbg.equal(Integer.valueOf(invitation2.getInvitationType()), Integer.valueOf(invitation.getInvitationType())) && zzbg.equal(invitation2.getInviter(), invitation.getInviter()) && zzbg.equal(invitation2.getParticipants(), invitation.getParticipants()) && zzbg.equal(Integer.valueOf(invitation2.getVariant()), Integer.valueOf(invitation.getVariant())) && zzbg.equal(Integer.valueOf(invitation2.getAvailableAutoMatchSlots()), Integer.valueOf(invitation.getAvailableAutoMatchSlots()));
    }

    static String zzb(Invitation invitation) {
        return zzbg.zzx(invitation).zzg("Game", invitation.getGame()).zzg("InvitationId", invitation.getInvitationId()).zzg("CreationTimestamp", Long.valueOf(invitation.getCreationTimestamp())).zzg("InvitationType", Integer.valueOf(invitation.getInvitationType())).zzg("Inviter", invitation.getInviter()).zzg("Participants", invitation.getParticipants()).zzg("Variant", Integer.valueOf(invitation.getVariant())).zzg("AvailableAutoMatchSlots", Integer.valueOf(invitation.getAvailableAutoMatchSlots())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Invitation freeze() {
        return this;
    }

    public final int getAvailableAutoMatchSlots() {
        return this.zzife;
    }

    public final long getCreationTimestamp() {
        return this.mCreationTimestamp;
    }

    public final Game getGame() {
        return this.zzibx;
    }

    public final String getInvitationId() {
        return this.zzeha;
    }

    public final int getInvitationType() {
        return this.zzifa;
    }

    public final Participant getInviter() {
        return this.zzifb;
    }

    public final ArrayList<Participant> getParticipants() {
        return new ArrayList<>(this.zzifc);
    }

    public final int getVariant() {
        return this.zzifd;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getGame(), i, false);
        zzbgo.zza(parcel, 2, getInvitationId(), false);
        zzbgo.zza(parcel, 3, getCreationTimestamp());
        zzbgo.zzc(parcel, 4, getInvitationType());
        zzbgo.zza(parcel, 5, (Parcelable) getInviter(), i, false);
        zzbgo.zzc(parcel, 6, getParticipants(), false);
        zzbgo.zzc(parcel, 7, getVariant());
        zzbgo.zzc(parcel, 8, getAvailableAutoMatchSlots());
        zzbgo.zzai(parcel, zze);
    }
}
