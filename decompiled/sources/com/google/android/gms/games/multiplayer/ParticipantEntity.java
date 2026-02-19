package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class ParticipantEntity extends GamesDowngradeableSafeParcel implements Participant {
    public static final Parcelable.Creator<ParticipantEntity> CREATOR = new zza();
    private final int zzcfl;
    private final String zzemi;
    private final int zzety;
    private final Uri zzhra;
    private final Uri zzhrb;
    private final String zzhrl;
    private final String zzhrm;
    private final PlayerEntity zzhwk;
    private final String zzhyl;
    private final String zzifg;
    private final boolean zzifh;
    private final ParticipantResult zzifi;

    static final class zza extends zzc {
        zza() {
        }

        public final ParticipantEntity createFromParcel(Parcel parcel) {
            return zzr(parcel);
        }

        public final ParticipantEntity zzr(Parcel parcel) {
            if (ParticipantEntity.zze(ParticipantEntity.zzamq()) || ParticipantEntity.zzgq(ParticipantEntity.class.getCanonicalName())) {
                return super.createFromParcel(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            Uri parse = readString3 == null ? null : Uri.parse(readString3);
            String readString4 = parcel.readString();
            Uri parse2 = readString4 == null ? null : Uri.parse(readString4);
            int readInt = parcel.readInt();
            String readString5 = parcel.readString();
            boolean z = true;
            boolean z2 = parcel.readInt() > 0;
            if (parcel.readInt() <= 0) {
                z = false;
            }
            return new ParticipantEntity(readString, readString2, parse, parse2, readInt, readString5, z2, z ? PlayerEntity.CREATOR.createFromParcel(parcel) : null, 7, (ParticipantResult) null, (String) null, (String) null);
        }
    }

    public ParticipantEntity(Participant participant) {
        this.zzhyl = participant.getParticipantId();
        this.zzemi = participant.getDisplayName();
        this.zzhra = participant.getIconImageUri();
        this.zzhrb = participant.getHiResImageUri();
        this.zzcfl = participant.getStatus();
        this.zzifg = participant.zzavt();
        this.zzifh = participant.isConnectedToRoom();
        Player player = participant.getPlayer();
        this.zzhwk = player == null ? null : new PlayerEntity(player);
        this.zzety = participant.getCapabilities();
        this.zzifi = participant.getResult();
        this.zzhrl = participant.getIconImageUrl();
        this.zzhrm = participant.getHiResImageUrl();
    }

    ParticipantEntity(String str, String str2, Uri uri, Uri uri2, int i, String str3, boolean z, PlayerEntity playerEntity, int i2, ParticipantResult participantResult, String str4, String str5) {
        this.zzhyl = str;
        this.zzemi = str2;
        this.zzhra = uri;
        this.zzhrb = uri2;
        this.zzcfl = i;
        this.zzifg = str3;
        this.zzifh = z;
        this.zzhwk = playerEntity;
        this.zzety = i2;
        this.zzifi = participantResult;
        this.zzhrl = str4;
        this.zzhrm = str5;
    }

    static int zza(Participant participant) {
        return Arrays.hashCode(new Object[]{participant.getPlayer(), Integer.valueOf(participant.getStatus()), participant.zzavt(), Boolean.valueOf(participant.isConnectedToRoom()), participant.getDisplayName(), participant.getIconImageUri(), participant.getHiResImageUri(), Integer.valueOf(participant.getCapabilities()), participant.getResult(), participant.getParticipantId()});
    }

    static boolean zza(Participant participant, Object obj) {
        if (!(obj instanceof Participant)) {
            return false;
        }
        if (participant == obj) {
            return true;
        }
        Participant participant2 = (Participant) obj;
        return zzbg.equal(participant2.getPlayer(), participant.getPlayer()) && zzbg.equal(Integer.valueOf(participant2.getStatus()), Integer.valueOf(participant.getStatus())) && zzbg.equal(participant2.zzavt(), participant.zzavt()) && zzbg.equal(Boolean.valueOf(participant2.isConnectedToRoom()), Boolean.valueOf(participant.isConnectedToRoom())) && zzbg.equal(participant2.getDisplayName(), participant.getDisplayName()) && zzbg.equal(participant2.getIconImageUri(), participant.getIconImageUri()) && zzbg.equal(participant2.getHiResImageUri(), participant.getHiResImageUri()) && zzbg.equal(Integer.valueOf(participant2.getCapabilities()), Integer.valueOf(participant.getCapabilities())) && zzbg.equal(participant2.getResult(), participant.getResult()) && zzbg.equal(participant2.getParticipantId(), participant.getParticipantId());
    }

    static String zzb(Participant participant) {
        return zzbg.zzx(participant).zzg("ParticipantId", participant.getParticipantId()).zzg("Player", participant.getPlayer()).zzg("Status", Integer.valueOf(participant.getStatus())).zzg("ClientAddress", participant.zzavt()).zzg("ConnectedToRoom", Boolean.valueOf(participant.isConnectedToRoom())).zzg("DisplayName", participant.getDisplayName()).zzg("IconImage", participant.getIconImageUri()).zzg("IconImageUrl", participant.getIconImageUrl()).zzg("HiResImage", participant.getHiResImageUri()).zzg("HiResImageUrl", participant.getHiResImageUrl()).zzg("Capabilities", Integer.valueOf(participant.getCapabilities())).zzg("Result", participant.getResult()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Participant freeze() {
        return this;
    }

    public final int getCapabilities() {
        return this.zzety;
    }

    public final String getDisplayName() {
        PlayerEntity playerEntity = this.zzhwk;
        return playerEntity == null ? this.zzemi : playerEntity.getDisplayName();
    }

    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        PlayerEntity playerEntity = this.zzhwk;
        if (playerEntity == null) {
            zzh.zzb(this.zzemi, charArrayBuffer);
        } else {
            playerEntity.getDisplayName(charArrayBuffer);
        }
    }

    public final Uri getHiResImageUri() {
        PlayerEntity playerEntity = this.zzhwk;
        return playerEntity == null ? this.zzhrb : playerEntity.getHiResImageUri();
    }

    public final String getHiResImageUrl() {
        PlayerEntity playerEntity = this.zzhwk;
        return playerEntity == null ? this.zzhrm : playerEntity.getHiResImageUrl();
    }

    public final Uri getIconImageUri() {
        PlayerEntity playerEntity = this.zzhwk;
        return playerEntity == null ? this.zzhra : playerEntity.getIconImageUri();
    }

    public final String getIconImageUrl() {
        PlayerEntity playerEntity = this.zzhwk;
        return playerEntity == null ? this.zzhrl : playerEntity.getIconImageUrl();
    }

    public final String getParticipantId() {
        return this.zzhyl;
    }

    public final Player getPlayer() {
        return this.zzhwk;
    }

    public final ParticipantResult getResult() {
        return this.zzifi;
    }

    public final int getStatus() {
        return this.zzcfl;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isConnectedToRoom() {
        return this.zzifh;
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getParticipantId(), false);
        zzbgo.zza(parcel, 2, getDisplayName(), false);
        zzbgo.zza(parcel, 3, (Parcelable) getIconImageUri(), i, false);
        zzbgo.zza(parcel, 4, (Parcelable) getHiResImageUri(), i, false);
        zzbgo.zzc(parcel, 5, getStatus());
        zzbgo.zza(parcel, 6, this.zzifg, false);
        zzbgo.zza(parcel, 7, isConnectedToRoom());
        zzbgo.zza(parcel, 8, (Parcelable) getPlayer(), i, false);
        zzbgo.zzc(parcel, 9, this.zzety);
        zzbgo.zza(parcel, 10, (Parcelable) getResult(), i, false);
        zzbgo.zza(parcel, 11, getIconImageUrl(), false);
        zzbgo.zza(parcel, 12, getHiResImageUrl(), false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzavt() {
        return this.zzifg;
    }
}
