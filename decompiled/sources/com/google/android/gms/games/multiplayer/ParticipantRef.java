package com.google.android.gms.games.multiplayer;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;

public final class ParticipantRef extends zzc implements Participant {
    private final PlayerRef zzifj;

    public ParticipantRef(DataHolder dataHolder, int i) {
        super(dataHolder, i);
        this.zzifj = new PlayerRef(dataHolder, i);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return ParticipantEntity.zza(this, obj);
    }

    public final Participant freeze() {
        return new ParticipantEntity(this);
    }

    public final int getCapabilities() {
        return getInteger("capabilities");
    }

    public final String getDisplayName() {
        return zzgl("external_player_id") ? getString("default_display_name") : this.zzifj.getDisplayName();
    }

    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        if (zzgl("external_player_id")) {
            zza("default_display_name", charArrayBuffer);
        } else {
            this.zzifj.getDisplayName(charArrayBuffer);
        }
    }

    public final Uri getHiResImageUri() {
        return zzgl("external_player_id") ? zzgk("default_display_hi_res_image_uri") : this.zzifj.getHiResImageUri();
    }

    public final String getHiResImageUrl() {
        return zzgl("external_player_id") ? getString("default_display_hi_res_image_url") : this.zzifj.getHiResImageUrl();
    }

    public final Uri getIconImageUri() {
        return zzgl("external_player_id") ? zzgk("default_display_image_uri") : this.zzifj.getIconImageUri();
    }

    public final String getIconImageUrl() {
        return zzgl("external_player_id") ? getString("default_display_image_url") : this.zzifj.getIconImageUrl();
    }

    public final String getParticipantId() {
        return getString("external_participant_id");
    }

    public final Player getPlayer() {
        if (zzgl("external_player_id")) {
            return null;
        }
        return this.zzifj;
    }

    public final ParticipantResult getResult() {
        if (zzgl("result_type")) {
            return null;
        }
        return new ParticipantResult(getParticipantId(), getInteger("result_type"), getInteger("placing"));
    }

    public final int getStatus() {
        return getInteger("player_status");
    }

    public final int hashCode() {
        return ParticipantEntity.zza(this);
    }

    public final boolean isConnectedToRoom() {
        return getInteger("connected") > 0;
    }

    public final String toString() {
        return ParticipantEntity.zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((ParticipantEntity) ((Participant) freeze())).writeToParcel(parcel, i);
    }

    public final String zzavt() {
        return getString("client_address");
    }
}
