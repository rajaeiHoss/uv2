package com.google.android.gms.games.internal.experience;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.Game;

public final class ExperienceEventRef extends zzc implements ExperienceEvent {
    public ExperienceEventRef(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    public final int describeContents() {
        throw new NoSuchMethodError();
    }

    public final boolean equals(Object obj) {
        throw new NoSuchMethodError();
    }

    public final ExperienceEvent freeze() {
        throw new NoSuchMethodError();
    }

    public final Game getGame() {
        throw new NoSuchMethodError();
    }

    public final Uri getIconImageUri() {
        throw new NoSuchMethodError();
    }

    public final String getIconImageUrl() {
        return getString("icon_url");
    }

    public final int getType() {
        throw new NoSuchMethodError();
    }

    public final int hashCode() {
        throw new NoSuchMethodError();
    }

    public final String toString() {
        throw new NoSuchMethodError();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        throw new NoSuchMethodError();
    }

    public final String zzava() {
        throw new NoSuchMethodError();
    }

    public final String zzavb() {
        throw new NoSuchMethodError();
    }

    public final String zzavc() {
        throw new NoSuchMethodError();
    }

    public final long zzavd() {
        throw new NoSuchMethodError();
    }

    public final long zzave() {
        throw new NoSuchMethodError();
    }

    public final long zzavf() {
        throw new NoSuchMethodError();
    }

    public final int zzavg() {
        throw new NoSuchMethodError();
    }
}
