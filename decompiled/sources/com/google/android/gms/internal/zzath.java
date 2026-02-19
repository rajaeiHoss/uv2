package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.analytics.zzk;

public final class zzath extends zzari {
    /* access modifiers changed from: private */
    public SharedPreferences zzeec;
    private long zzeed;
    private long zzeee = -1;
    private final zzatj zzeef = new zzatj(this, "monitoring", zzast.zzeda.get().longValue());

    protected zzath(zzark zzark) {
        super(zzark);
    }

    public final long zzaba() {
        zzk.zzwj();
        zzyk();
        if (this.zzeed == 0) {
            long j = this.zzeec.getLong("first_run", 0);
            if (j != 0) {
                this.zzeed = j;
            } else {
                long currentTimeMillis = zzxx().currentTimeMillis();
                SharedPreferences.Editor edit = this.zzeec.edit();
                edit.putLong("first_run", currentTimeMillis);
                if (!edit.commit()) {
                    zzed("Failed to commit first run time");
                }
                this.zzeed = currentTimeMillis;
            }
        }
        return this.zzeed;
    }

    public final zzatp zzabb() {
        return new zzatp(zzxx(), zzaba());
    }

    public final long zzabc() {
        zzk.zzwj();
        zzyk();
        if (this.zzeee == -1) {
            this.zzeee = this.zzeec.getLong("last_dispatch", 0);
        }
        return this.zzeee;
    }

    public final void zzabd() {
        zzk.zzwj();
        zzyk();
        long currentTimeMillis = zzxx().currentTimeMillis();
        SharedPreferences.Editor edit = this.zzeec.edit();
        edit.putLong("last_dispatch", currentTimeMillis);
        edit.apply();
        this.zzeee = currentTimeMillis;
    }

    public final String zzabe() {
        zzk.zzwj();
        zzyk();
        String string = this.zzeec.getString("installation_campaign", (String) null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }

    public final zzatj zzabf() {
        return this.zzeef;
    }

    public final void zzel(String str) {
        zzk.zzwj();
        zzyk();
        SharedPreferences.Editor edit = this.zzeec.edit();
        if (TextUtils.isEmpty(str)) {
            edit.remove("installation_campaign");
        } else {
            edit.putString("installation_campaign", str);
        }
        if (!edit.commit()) {
            zzed("Failed to commit campaign data");
        }
    }

    /* access modifiers changed from: protected */
    public final void zzwk() {
        this.zzeec = getContext().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
    }
}
