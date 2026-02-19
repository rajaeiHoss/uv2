package com.google.android.gms.games.request;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

@Deprecated
public final class zzb extends zzc implements GameRequest {
    private final int zzidy;

    public zzb(DataHolder dataHolder, int i, int i2) {
        super(dataHolder, i);
        this.zzidy = i2;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return GameRequestEntity.zza(this, obj);
    }

    public final GameRequest freeze() {
        return new GameRequestEntity(this);
    }

    public final long getCreationTimestamp() {
        return getLong("creation_timestamp");
    }

    public final byte[] getData() {
        return getByteArray(DataPacketExtension.ELEMENT_NAME);
    }

    public final long getExpirationTimestamp() {
        return getLong("expiration_timestamp");
    }

    public final Game getGame() {
        return new GameRef(this.zzfxb, this.zzgch);
    }

    public final int getRecipientStatus(String str) {
        for (int i = this.zzgch; i < this.zzgch + this.zzidy; i++) {
            int zzby = this.zzfxb.zzby(i);
            if (this.zzfxb.zzd("recipient_external_player_id", i, zzby).equals(str)) {
                return this.zzfxb.zzc("recipient_status", i, zzby);
            }
        }
        return -1;
    }

    public final List<Player> getRecipients() {
        ArrayList arrayList = new ArrayList(this.zzidy);
        for (int i = 0; i < this.zzidy; i++) {
            arrayList.add(new PlayerRef(this.zzfxb, this.zzgch + i, "recipient_"));
        }
        return arrayList;
    }

    public final String getRequestId() {
        return getString("external_request_id");
    }

    public final Player getSender() {
        return new PlayerRef(this.zzfxb, this.zzgch, "sender_");
    }

    public final int getStatus() {
        return getInteger("status");
    }

    public final int getType() {
        return getInteger(AppMeasurement.Param.TYPE);
    }

    public final int hashCode() {
        return GameRequestEntity.zza(this);
    }

    public final boolean isConsumed(String str) {
        return getRecipientStatus(str) == 1;
    }

    public final String toString() {
        return GameRequestEntity.zzc(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        ((GameRequestEntity) ((GameRequest) freeze())).writeToParcel(parcel, i);
    }
}
