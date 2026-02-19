package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class EventEntity extends zzc implements Event {
    public static final Parcelable.Creator<EventEntity> CREATOR = new zza();
    private final String mName;
    private final boolean zzawi;
    private final long zzdxg;
    private final String zzdxh;
    private final Uri zzhra;
    private final String zzhrl;
    private final PlayerEntity zzhwk;
    private final String zzhwp;
    private final String zzhwq;

    public EventEntity(Event event) {
        this.zzhwp = event.getEventId();
        this.mName = event.getName();
        this.zzdxh = event.getDescription();
        this.zzhra = event.getIconImageUri();
        this.zzhrl = event.getIconImageUrl();
        this.zzhwk = (PlayerEntity) event.getPlayer().freeze();
        this.zzdxg = event.getValue();
        this.zzhwq = event.getFormattedValue();
        this.zzawi = event.isVisible();
    }

    EventEntity(String str, String str2, String str3, Uri uri, String str4, Player player, long j, String str5, boolean z) {
        this.zzhwp = str;
        this.mName = str2;
        this.zzdxh = str3;
        this.zzhra = uri;
        this.zzhrl = str4;
        this.zzhwk = new PlayerEntity(player);
        this.zzdxg = j;
        this.zzhwq = str5;
        this.zzawi = z;
    }

    static int zza(Event event) {
        return Arrays.hashCode(new Object[]{event.getEventId(), event.getName(), event.getDescription(), event.getIconImageUri(), event.getIconImageUrl(), event.getPlayer(), Long.valueOf(event.getValue()), event.getFormattedValue(), Boolean.valueOf(event.isVisible())});
    }

    static boolean zza(Event event, Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        if (event == obj) {
            return true;
        }
        Event event2 = (Event) obj;
        return zzbg.equal(event2.getEventId(), event.getEventId()) && zzbg.equal(event2.getName(), event.getName()) && zzbg.equal(event2.getDescription(), event.getDescription()) && zzbg.equal(event2.getIconImageUri(), event.getIconImageUri()) && zzbg.equal(event2.getIconImageUrl(), event.getIconImageUrl()) && zzbg.equal(event2.getPlayer(), event.getPlayer()) && zzbg.equal(Long.valueOf(event2.getValue()), Long.valueOf(event.getValue())) && zzbg.equal(event2.getFormattedValue(), event.getFormattedValue()) && zzbg.equal(Boolean.valueOf(event2.isVisible()), Boolean.valueOf(event.isVisible()));
    }

    static String zzb(Event event) {
        return zzbg.zzx(event).zzg("Id", event.getEventId()).zzg("Name", event.getName()).zzg("Description", event.getDescription()).zzg("IconImageUri", event.getIconImageUri()).zzg("IconImageUrl", event.getIconImageUrl()).zzg("Player", event.getPlayer()).zzg("Value", Long.valueOf(event.getValue())).zzg("FormattedValue", event.getFormattedValue()).zzg("isVisible", Boolean.valueOf(event.isVisible())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Event freeze() {
        return this;
    }

    public final String getDescription() {
        return this.zzdxh;
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzdxh, charArrayBuffer);
    }

    public final String getEventId() {
        return this.zzhwp;
    }

    public final String getFormattedValue() {
        return this.zzhwq;
    }

    public final void getFormattedValue(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.zzhwq, charArrayBuffer);
    }

    public final Uri getIconImageUri() {
        return this.zzhra;
    }

    public final String getIconImageUrl() {
        return this.zzhrl;
    }

    public final String getName() {
        return this.mName;
    }

    public final void getName(CharArrayBuffer charArrayBuffer) {
        zzh.zzb(this.mName, charArrayBuffer);
    }

    public final Player getPlayer() {
        return this.zzhwk;
    }

    public final long getValue() {
        return this.zzdxg;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final boolean isVisible() {
        return this.zzawi;
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getEventId(), false);
        zzbgo.zza(parcel, 2, getName(), false);
        zzbgo.zza(parcel, 3, getDescription(), false);
        zzbgo.zza(parcel, 4, (Parcelable) getIconImageUri(), i, false);
        zzbgo.zza(parcel, 5, getIconImageUrl(), false);
        zzbgo.zza(parcel, 6, (Parcelable) getPlayer(), i, false);
        zzbgo.zza(parcel, 7, getValue());
        zzbgo.zza(parcel, 8, getFormattedValue(), false);
        zzbgo.zza(parcel, 9, isVisible());
        zzbgo.zzai(parcel, zze);
    }
}
