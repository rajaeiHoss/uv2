package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.view.View;
import androidx.collection.ArraySet;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzcyk;
import java.util.Collection;
import java.util.Map;

public final class zzs {
    private String zzehh;
    private Account zzeho;
    private int zzfsy = 0;
    private String zzfta;
    private zzcyk zzgfs = zzcyk.zzklp;
    private ArraySet<Scope> zzgfu;

    public final zzr zzamn() {
        return new zzr(this.zzeho, this.zzgfu, (Map<Api<?>, zzt>) null, 0, (View) null, this.zzehh, this.zzfta, this.zzgfs);
    }

    public final zzs zze(Account account) {
        this.zzeho = account;
        return this;
    }

    public final zzs zze(Collection<Scope> collection) {
        if (this.zzgfu == null) {
            this.zzgfu = new ArraySet<>();
        }
        this.zzgfu.addAll(collection);
        return this;
    }

    public final zzs zzgo(String str) {
        this.zzehh = str;
        return this;
    }

    public final zzs zzgp(String str) {
        this.zzfta = str;
        return this;
    }
}
