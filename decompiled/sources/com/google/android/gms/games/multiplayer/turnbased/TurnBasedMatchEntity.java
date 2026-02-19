package com.google.android.gms.games.multiplayer.turnbased;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;

public final class TurnBasedMatchEntity extends zzc implements TurnBasedMatch {
    public static final Parcelable.Creator<TurnBasedMatchEntity> CREATOR = new com.google.android.gms.games.multiplayer.turnbased.zzc();
    private final long mCreationTimestamp;
    private final int mVersion;
    private final String zzdxh;
    private final long zzhwn;
    private final String zzhyd;
    private final GameEntity zzibx;
    private final ArrayList<ParticipantEntity> zzifc;
    private final int zzifd;
    private final Bundle zzifw;
    private final String zzifz;
    private final String zzigi;
    private final String zzigj;
    private final int zzigk;
    private final byte[] zzigl;
    private final String zzigm;
    private final byte[] zzign;
    private final int zzigo;
    private final int zzigp;
    private final boolean zzigq;
    private final String zzigr;

    TurnBasedMatchEntity(GameEntity gameEntity, String str, String str2, long j, String str3, long j2, String str4, int i, int i2, int i3, byte[] bArr, ArrayList<ParticipantEntity> arrayList, String str5, byte[] bArr2, int i4, Bundle bundle, int i5, boolean z, String str6, String str7) {
        this.zzibx = gameEntity;
        this.zzhyd = str;
        this.zzifz = str2;
        this.mCreationTimestamp = j;
        this.zzigi = str3;
        this.zzhwn = j2;
        this.zzigj = str4;
        this.zzigk = i;
        this.zzigp = i5;
        this.zzifd = i2;
        this.mVersion = i3;
        this.zzigl = bArr;
        this.zzifc = arrayList;
        this.zzigm = str5;
        this.zzign = bArr2;
        this.zzigo = i4;
        this.zzifw = bundle;
        this.zzigq = z;
        this.zzdxh = str6;
        this.zzigr = str7;
    }

    public TurnBasedMatchEntity(TurnBasedMatch turnBasedMatch) {
        this.zzibx = new GameEntity(turnBasedMatch.getGame());
        this.zzhyd = turnBasedMatch.getMatchId();
        this.zzifz = turnBasedMatch.getCreatorId();
        this.mCreationTimestamp = turnBasedMatch.getCreationTimestamp();
        this.zzigi = turnBasedMatch.getLastUpdaterId();
        this.zzhwn = turnBasedMatch.getLastUpdatedTimestamp();
        this.zzigj = turnBasedMatch.getPendingParticipantId();
        this.zzigk = turnBasedMatch.getStatus();
        this.zzigp = turnBasedMatch.getTurnStatus();
        this.zzifd = turnBasedMatch.getVariant();
        this.mVersion = turnBasedMatch.getVersion();
        this.zzigm = turnBasedMatch.getRematchId();
        this.zzigo = turnBasedMatch.getMatchNumber();
        this.zzifw = turnBasedMatch.getAutoMatchCriteria();
        this.zzigq = turnBasedMatch.isLocallyModified();
        this.zzdxh = turnBasedMatch.getDescription();
        this.zzigr = turnBasedMatch.getDescriptionParticipantId();
        byte[] data = turnBasedMatch.getData();
        if (data == null) {
            this.zzigl = null;
        } else {
            byte[] bArr = new byte[data.length];
            this.zzigl = bArr;
            System.arraycopy(data, 0, bArr, 0, data.length);
        }
        byte[] previousMatchData = turnBasedMatch.getPreviousMatchData();
        if (previousMatchData == null) {
            this.zzign = null;
        } else {
            byte[] bArr2 = new byte[previousMatchData.length];
            this.zzign = bArr2;
            System.arraycopy(previousMatchData, 0, bArr2, 0, previousMatchData.length);
        }
        ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        int size = participants.size();
        this.zzifc = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.zzifc.add((ParticipantEntity) participants.get(i).freeze());
        }
    }

    static int zza(TurnBasedMatch turnBasedMatch) {
        return Arrays.hashCode(new Object[]{turnBasedMatch.getGame(), turnBasedMatch.getMatchId(), turnBasedMatch.getCreatorId(), Long.valueOf(turnBasedMatch.getCreationTimestamp()), turnBasedMatch.getLastUpdaterId(), Long.valueOf(turnBasedMatch.getLastUpdatedTimestamp()), turnBasedMatch.getPendingParticipantId(), Integer.valueOf(turnBasedMatch.getStatus()), Integer.valueOf(turnBasedMatch.getTurnStatus()), turnBasedMatch.getDescription(), Integer.valueOf(turnBasedMatch.getVariant()), Integer.valueOf(turnBasedMatch.getVersion()), turnBasedMatch.getParticipants(), turnBasedMatch.getRematchId(), Integer.valueOf(turnBasedMatch.getMatchNumber()), turnBasedMatch.getAutoMatchCriteria(), Integer.valueOf(turnBasedMatch.getAvailableAutoMatchSlots()), Boolean.valueOf(turnBasedMatch.isLocallyModified())});
    }

    static int zza(TurnBasedMatch turnBasedMatch, String str) {
        ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        int size = participants.size();
        for (int i = 0; i < size; i++) {
            Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(str)) {
                return participant.getStatus();
            }
        }
        String matchId = turnBasedMatch.getMatchId();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 29 + String.valueOf(matchId).length());
        sb.append("Participant ");
        sb.append(str);
        sb.append(" is not in match ");
        sb.append(matchId);
        throw new IllegalStateException(sb.toString());
    }

    static boolean zza(TurnBasedMatch turnBasedMatch, Object obj) {
        if (!(obj instanceof TurnBasedMatch)) {
            return false;
        }
        if (turnBasedMatch == obj) {
            return true;
        }
        TurnBasedMatch turnBasedMatch2 = (TurnBasedMatch) obj;
        return zzbg.equal(turnBasedMatch2.getGame(), turnBasedMatch.getGame()) && zzbg.equal(turnBasedMatch2.getMatchId(), turnBasedMatch.getMatchId()) && zzbg.equal(turnBasedMatch2.getCreatorId(), turnBasedMatch.getCreatorId()) && zzbg.equal(Long.valueOf(turnBasedMatch2.getCreationTimestamp()), Long.valueOf(turnBasedMatch.getCreationTimestamp())) && zzbg.equal(turnBasedMatch2.getLastUpdaterId(), turnBasedMatch.getLastUpdaterId()) && zzbg.equal(Long.valueOf(turnBasedMatch2.getLastUpdatedTimestamp()), Long.valueOf(turnBasedMatch.getLastUpdatedTimestamp())) && zzbg.equal(turnBasedMatch2.getPendingParticipantId(), turnBasedMatch.getPendingParticipantId()) && zzbg.equal(Integer.valueOf(turnBasedMatch2.getStatus()), Integer.valueOf(turnBasedMatch.getStatus())) && zzbg.equal(Integer.valueOf(turnBasedMatch2.getTurnStatus()), Integer.valueOf(turnBasedMatch.getTurnStatus())) && zzbg.equal(turnBasedMatch2.getDescription(), turnBasedMatch.getDescription()) && zzbg.equal(Integer.valueOf(turnBasedMatch2.getVariant()), Integer.valueOf(turnBasedMatch.getVariant())) && zzbg.equal(Integer.valueOf(turnBasedMatch2.getVersion()), Integer.valueOf(turnBasedMatch.getVersion())) && zzbg.equal(turnBasedMatch2.getParticipants(), turnBasedMatch.getParticipants()) && zzbg.equal(turnBasedMatch2.getRematchId(), turnBasedMatch.getRematchId()) && zzbg.equal(Integer.valueOf(turnBasedMatch2.getMatchNumber()), Integer.valueOf(turnBasedMatch.getMatchNumber())) && zzbg.equal(turnBasedMatch2.getAutoMatchCriteria(), turnBasedMatch.getAutoMatchCriteria()) && zzbg.equal(Integer.valueOf(turnBasedMatch2.getAvailableAutoMatchSlots()), Integer.valueOf(turnBasedMatch.getAvailableAutoMatchSlots())) && zzbg.equal(Boolean.valueOf(turnBasedMatch2.isLocallyModified()), Boolean.valueOf(turnBasedMatch.isLocallyModified()));
    }

    static String zzb(TurnBasedMatch turnBasedMatch) {
        return zzbg.zzx(turnBasedMatch).zzg("Game", turnBasedMatch.getGame()).zzg("MatchId", turnBasedMatch.getMatchId()).zzg("CreatorId", turnBasedMatch.getCreatorId()).zzg("CreationTimestamp", Long.valueOf(turnBasedMatch.getCreationTimestamp())).zzg("LastUpdaterId", turnBasedMatch.getLastUpdaterId()).zzg("LastUpdatedTimestamp", Long.valueOf(turnBasedMatch.getLastUpdatedTimestamp())).zzg("PendingParticipantId", turnBasedMatch.getPendingParticipantId()).zzg("MatchStatus", Integer.valueOf(turnBasedMatch.getStatus())).zzg("TurnStatus", Integer.valueOf(turnBasedMatch.getTurnStatus())).zzg("Description", turnBasedMatch.getDescription()).zzg("Variant", Integer.valueOf(turnBasedMatch.getVariant())).zzg("Data", turnBasedMatch.getData()).zzg("Version", Integer.valueOf(turnBasedMatch.getVersion())).zzg("Participants", turnBasedMatch.getParticipants()).zzg("RematchId", turnBasedMatch.getRematchId()).zzg("PreviousData", turnBasedMatch.getPreviousMatchData()).zzg("MatchNumber", Integer.valueOf(turnBasedMatch.getMatchNumber())).zzg("AutoMatchCriteria", turnBasedMatch.getAutoMatchCriteria()).zzg("AvailableAutoMatchSlots", Integer.valueOf(turnBasedMatch.getAvailableAutoMatchSlots())).zzg("LocallyModified", Boolean.valueOf(turnBasedMatch.isLocallyModified())).zzg("DescriptionParticipantId", turnBasedMatch.getDescriptionParticipantId()).toString();
    }

    static String zzb(TurnBasedMatch turnBasedMatch, String str) {
        ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        int size = participants.size();
        for (int i = 0; i < size; i++) {
            Participant participant = participants.get(i);
            Player player = participant.getPlayer();
            if (player != null && player.getPlayerId().equals(str)) {
                return participant.getParticipantId();
            }
        }
        return null;
    }

    static Participant zzc(TurnBasedMatch turnBasedMatch, String str) {
        ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        int size = participants.size();
        for (int i = 0; i < size; i++) {
            Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(str)) {
                return participant;
            }
        }
        String matchId = turnBasedMatch.getMatchId();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 29 + String.valueOf(matchId).length());
        sb.append("Participant ");
        sb.append(str);
        sb.append(" is not in match ");
        sb.append(matchId);
        throw new IllegalStateException(sb.toString());
    }

    static ArrayList<String> zzc(TurnBasedMatch turnBasedMatch) {
        ArrayList<Participant> participants = turnBasedMatch.getParticipants();
        int size = participants.size();
        ArrayList<String> arrayList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(participants.get(i).getParticipantId());
        }
        return arrayList;
    }

    public final boolean canRematch() {
        return this.zzigk == 2 && this.zzigm == null;
    }

    public final boolean equals(Object obj) {
        return zza((TurnBasedMatch) this, obj);
    }

    public final TurnBasedMatch freeze() {
        return this;
    }

    public final Bundle getAutoMatchCriteria() {
        return this.zzifw;
    }

    public final int getAvailableAutoMatchSlots() {
        Bundle bundle = this.zzifw;
        if (bundle == null) {
            return 0;
        }
        return bundle.getInt(Multiplayer.EXTRA_MAX_AUTOMATCH_PLAYERS);
    }

    public final long getCreationTimestamp() {
        return this.mCreationTimestamp;
    }

    public final String getCreatorId() {
        return this.zzifz;
    }

    public final byte[] getData() {
        return this.zzigl;
    }

    public final String getDescription() {
        return this.zzdxh;
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzdxh, charArrayBuffer);
    }

    public final Participant getDescriptionParticipant() {
        String descriptionParticipantId = getDescriptionParticipantId();
        if (descriptionParticipantId == null) {
            return null;
        }
        return getParticipant(descriptionParticipantId);
    }

    public final String getDescriptionParticipantId() {
        return this.zzigr;
    }

    public final Game getGame() {
        return this.zzibx;
    }

    public final long getLastUpdatedTimestamp() {
        return this.zzhwn;
    }

    public final String getLastUpdaterId() {
        return this.zzigi;
    }

    public final String getMatchId() {
        return this.zzhyd;
    }

    public final int getMatchNumber() {
        return this.zzigo;
    }

    public final Participant getParticipant(String str) {
        return zzc(this, str);
    }

    public final String getParticipantId(String str) {
        return zzb(this, str);
    }

    public final ArrayList<String> getParticipantIds() {
        return zzc(this);
    }

    public final int getParticipantStatus(String str) {
        return zza((TurnBasedMatch) this, str);
    }

    public final ArrayList<Participant> getParticipants() {
        return new ArrayList<>(this.zzifc);
    }

    public final String getPendingParticipantId() {
        return this.zzigj;
    }

    public final byte[] getPreviousMatchData() {
        return this.zzign;
    }

    public final String getRematchId() {
        return this.zzigm;
    }

    public final int getStatus() {
        return this.zzigk;
    }

    public final int getTurnStatus() {
        return this.zzigp;
    }

    public final int getVariant() {
        return this.zzifd;
    }

    public final int getVersion() {
        return this.mVersion;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final boolean isLocallyModified() {
        return this.zzigq;
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) getGame(), i, false);
        zzbgo.zza(parcel, 2, getMatchId(), false);
        zzbgo.zza(parcel, 3, getCreatorId(), false);
        zzbgo.zza(parcel, 4, getCreationTimestamp());
        zzbgo.zza(parcel, 5, getLastUpdaterId(), false);
        zzbgo.zza(parcel, 6, getLastUpdatedTimestamp());
        zzbgo.zza(parcel, 7, getPendingParticipantId(), false);
        zzbgo.zzc(parcel, 8, getStatus());
        zzbgo.zzc(parcel, 10, getVariant());
        zzbgo.zzc(parcel, 11, getVersion());
        zzbgo.zza(parcel, 12, getData(), false);
        zzbgo.zzc(parcel, 13, getParticipants(), false);
        zzbgo.zza(parcel, 14, getRematchId(), false);
        zzbgo.zza(parcel, 15, getPreviousMatchData(), false);
        zzbgo.zzc(parcel, 16, getMatchNumber());
        zzbgo.zza(parcel, 17, getAutoMatchCriteria(), false);
        zzbgo.zzc(parcel, 18, getTurnStatus());
        zzbgo.zza(parcel, 19, isLocallyModified());
        zzbgo.zza(parcel, 20, getDescription(), false);
        zzbgo.zza(parcel, 21, getDescriptionParticipantId(), false);
        zzbgo.zzai(parcel, zze);
    }
}
