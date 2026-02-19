package com.google.android.gms.internal;

import android.accounts.Account;
import java.util.ArrayList;
import java.util.List;

public final class zzaty {
    private Account account;
    private String zzefg;
    private boolean zzefh;
    private List<zzauc> zzefi;

    public final zzaty zza(zzauc zzauc) {
        if (this.zzefi == null && zzauc != null) {
            this.zzefi = new ArrayList();
        }
        if (zzauc != null) {
            this.zzefi.add(zzauc);
        }
        return this;
    }

    public final zzatx zzabp() {
        String str = this.zzefg;
        boolean z = this.zzefh;
        Account account2 = this.account;
        List<zzauc> list = this.zzefi;
        return new zzatx(str, z, account2, list != null ? (zzauc[]) list.toArray(new zzauc[list.size()]) : null);
    }

    public final zzaty zzap(boolean z) {
        this.zzefh = true;
        return this;
    }

    public final zzaty zzb(Account account2) {
        this.account = account2;
        return this;
    }

    public final zzaty zzes(String str) {
        this.zzefg = str;
        return this;
    }
}
