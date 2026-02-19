package com.google.android.gms.games.internal;

import android.os.Binder;
import android.view.View;

public class zzaa {
    protected GamesClientImpl zziac;
    protected zzac zziad;

    protected zzaa(GamesClientImpl gamesClientImpl, int i) {
        this.zziac = gamesClientImpl;
        zzdv(i);
    }

    public void zzaa(View view) {
    }

    public void zzaux() {
        this.zziac.zza(this.zziad.zziae, this.zziad.zzauy());
    }

    /* access modifiers changed from: protected */
    public void zzdv(int i) {
        this.zziad = new zzac(i, new Binder());
    }
}
