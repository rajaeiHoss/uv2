package com.google.android.gms.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.Logger;

public final class zzegp extends zzegm {
    public final synchronized void setLogLevel(Logger.Level level) {
        zzbyg();
        int i = zzegq.zzneq[level.ordinal()];
        if (i == 1) {
            this.zznem = zzemo.DEBUG;
        } else if (i == 2) {
            this.zznem = zzemo.INFO;
        } else if (i == 3) {
            this.zznem = zzemo.WARN;
        } else if (i == 4) {
            this.zznem = zzemo.ERROR;
        } else if (i == 5) {
            this.zznem = zzemo.NONE;
        } else {
            String valueOf = String.valueOf(level);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 19);
            sb.append("Unknown log level: ");
            sb.append(valueOf);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public final synchronized void setPersistenceCacheSizeBytes(long j) {
        zzbyg();
        if (j < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            throw new DatabaseException("The minimum cache size must be at least 1MB");
        } else if (j <= 104857600) {
            this.cacheSize = j;
        } else {
            throw new DatabaseException("Firebase Database currently doesn't support a cache size larger than 100MB");
        }
    }

    public final synchronized void setPersistenceEnabled(boolean z) {
        zzbyg();
        this.zzmzx = z;
    }

    public final synchronized void zzd(FirebaseApp firebaseApp) {
        this.zzmxo = firebaseApp;
    }

    public final synchronized void zzqd(String str) {
        zzbyg();
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Session identifier is not allowed to be empty or null!");
        }
        this.zznel = str;
    }
}
