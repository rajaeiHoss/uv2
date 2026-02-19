package com.google.android.gms.games.multiplayer.realtime;

import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantEntity;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;

public final class RoomEntity extends GamesDowngradeableSafeParcel implements Room {
    public static final Parcelable.Creator<RoomEntity> CREATOR = new zza();
    private final long mCreationTimestamp;
    private final String zzdxh;
    private final String zzhxr;
    private final ArrayList<ParticipantEntity> zzifc;
    private final int zzifd;
    private final Bundle zzifw;
    private final String zzifz;
    private final int zziga;
    private final int zzigb;

    static final class zza extends zze {
        zza() {
        }

        public final RoomEntity createFromParcel(Parcel parcel) {
            return zzs(parcel);
        }

        public final RoomEntity zzs(Parcel parcel) {
            if (RoomEntity.zze(RoomEntity.zzamq()) || RoomEntity.zzgq(RoomEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            long readLong = parcel.readLong();
            int readInt = parcel.readInt();
            String readString3 = parcel.readString();
            int readInt2 = parcel.readInt();
            Bundle readBundle = parcel.readBundle();
            int readInt3 = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt3);
            for (int i = 0; i < readInt3; i++) {
                arrayList.add(ParticipantEntity.CREATOR.createFromParcel(parcel));
            }
            return new RoomEntity(readString, readString2, readLong, readInt, readString3, readInt2, readBundle, arrayList, -1);
        }
    }

    public RoomEntity(Room room) {
        this.zzhxr = room.getRoomId();
        this.zzifz = room.getCreatorId();
        this.mCreationTimestamp = room.getCreationTimestamp();
        this.zziga = room.getStatus();
        this.zzdxh = room.getDescription();
        this.zzifd = room.getVariant();
        this.zzifw = room.getAutoMatchCriteria();
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        this.zzifc = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.zzifc.add((ParticipantEntity) participants.get(i).freeze());
        }
        this.zzigb = room.getAutoMatchWaitEstimateSeconds();
    }

    RoomEntity(String str, String str2, long j, int i, String str3, int i2, Bundle bundle, ArrayList<ParticipantEntity> arrayList, int i3) {
        this.zzhxr = str;
        this.zzifz = str2;
        this.mCreationTimestamp = j;
        this.zziga = i;
        this.zzdxh = str3;
        this.zzifd = i2;
        this.zzifw = bundle;
        this.zzifc = arrayList;
        this.zzigb = i3;
    }

    static int zza(Room room) {
        return Arrays.hashCode(new Object[]{room.getRoomId(), room.getCreatorId(), Long.valueOf(room.getCreationTimestamp()), Integer.valueOf(room.getStatus()), room.getDescription(), Integer.valueOf(room.getVariant()), room.getAutoMatchCriteria(), room.getParticipants(), Integer.valueOf(room.getAutoMatchWaitEstimateSeconds())});
    }

    static int zza(Room room, String str) {
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        for (int i = 0; i < size; i++) {
            Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(str)) {
                return participant.getStatus();
            }
        }
        String roomId = room.getRoomId();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 28 + String.valueOf(roomId).length());
        sb.append("Participant ");
        sb.append(str);
        sb.append(" is not in room ");
        sb.append(roomId);
        throw new IllegalStateException(sb.toString());
    }

    static boolean zza(Room room, Object obj) {
        if (!(obj instanceof Room)) {
            return false;
        }
        if (room == obj) {
            return true;
        }
        Room room2 = (Room) obj;
        return zzbg.equal(room2.getRoomId(), room.getRoomId()) && zzbg.equal(room2.getCreatorId(), room.getCreatorId()) && zzbg.equal(Long.valueOf(room2.getCreationTimestamp()), Long.valueOf(room.getCreationTimestamp())) && zzbg.equal(Integer.valueOf(room2.getStatus()), Integer.valueOf(room.getStatus())) && zzbg.equal(room2.getDescription(), room.getDescription()) && zzbg.equal(Integer.valueOf(room2.getVariant()), Integer.valueOf(room.getVariant())) && zzbg.equal(room2.getAutoMatchCriteria(), room.getAutoMatchCriteria()) && zzbg.equal(room2.getParticipants(), room.getParticipants()) && zzbg.equal(Integer.valueOf(room2.getAutoMatchWaitEstimateSeconds()), Integer.valueOf(room.getAutoMatchWaitEstimateSeconds()));
    }

    static String zzb(Room room) {
        return zzbg.zzx(room).zzg("RoomId", room.getRoomId()).zzg("CreatorId", room.getCreatorId()).zzg("CreationTimestamp", Long.valueOf(room.getCreationTimestamp())).zzg("RoomStatus", Integer.valueOf(room.getStatus())).zzg("Description", room.getDescription()).zzg("Variant", Integer.valueOf(room.getVariant())).zzg("AutoMatchCriteria", room.getAutoMatchCriteria()).zzg("Participants", room.getParticipants()).zzg("AutoMatchWaitEstimateSeconds", Integer.valueOf(room.getAutoMatchWaitEstimateSeconds())).toString();
    }

    static String zzb(Room room, String str) {
        ArrayList<Participant> participants = room.getParticipants();
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

    static Participant zzc(Room room, String str) {
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        for (int i = 0; i < size; i++) {
            Participant participant = participants.get(i);
            if (participant.getParticipantId().equals(str)) {
                return participant;
            }
        }
        String roomId = room.getRoomId();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 29 + String.valueOf(roomId).length());
        sb.append("Participant ");
        sb.append(str);
        sb.append(" is not in match ");
        sb.append(roomId);
        throw new IllegalStateException(sb.toString());
    }

    static ArrayList<String> zzc(Room room) {
        ArrayList<Participant> participants = room.getParticipants();
        int size = participants.size();
        ArrayList<String> arrayList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(participants.get(i).getParticipantId());
        }
        return arrayList;
    }

    public final boolean equals(Object obj) {
        return zza((Room) this, obj);
    }

    public final Room freeze() {
        return this;
    }

    public final Bundle getAutoMatchCriteria() {
        return this.zzifw;
    }

    public final int getAutoMatchWaitEstimateSeconds() {
        return this.zzigb;
    }

    public final long getCreationTimestamp() {
        return this.mCreationTimestamp;
    }

    public final String getCreatorId() {
        return this.zzifz;
    }

    public final String getDescription() {
        return this.zzdxh;
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzdxh, charArrayBuffer);
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
        return zza((Room) this, str);
    }

    public final ArrayList<Participant> getParticipants() {
        return new ArrayList<>(this.zzifc);
    }

    public final String getRoomId() {
        return this.zzhxr;
    }

    public final int getStatus() {
        return this.zziga;
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
        zzbgo.zza(parcel, 1, getRoomId(), false);
        zzbgo.zza(parcel, 2, getCreatorId(), false);
        zzbgo.zza(parcel, 3, getCreationTimestamp());
        zzbgo.zzc(parcel, 4, getStatus());
        zzbgo.zza(parcel, 5, getDescription(), false);
        zzbgo.zzc(parcel, 6, getVariant());
        zzbgo.zza(parcel, 7, getAutoMatchCriteria(), false);
        zzbgo.zzc(parcel, 8, getParticipants(), false);
        zzbgo.zzc(parcel, 9, getAutoMatchWaitEstimateSeconds());
        zzbgo.zzai(parcel, zze);
    }
}
