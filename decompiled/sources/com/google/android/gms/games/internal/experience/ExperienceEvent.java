package com.google.android.gms.games.internal.experience;

import android.net.Uri;
import android.os.Parcelable;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.games.Game;

public interface ExperienceEvent extends Parcelable, Freezable<ExperienceEvent> {
    Game getGame();

    Uri getIconImageUri();

    @Deprecated
    String getIconImageUrl();

    int getType();

    String zzava();

    String zzavb();

    String zzavc();

    long zzavd();

    long zzave();

    long zzavf();

    int zzavg();
}
