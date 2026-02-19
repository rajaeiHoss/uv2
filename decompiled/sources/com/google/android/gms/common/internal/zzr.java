package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzcyk;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzr {
    private final String zzehh;
    private final Account zzeho;
    private final Set<Scope> zzfsw;
    private final int zzfsy;
    private final View zzfsz;
    private final String zzfta;
    private final Set<Scope> zzgfq;
    private final Map<Api<?>, zzt> zzgfr;
    private final zzcyk zzgfs;
    private Integer zzgft;

    public zzr(Account account, Set<Scope> set, Map<Api<?>, zzt> map, int i, View view, String str, String str2, zzcyk zzcyk) {
        this.zzeho = account;
        Set<Scope> unmodifiableSet = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        this.zzfsw = unmodifiableSet;
        map = map == null ? Collections.EMPTY_MAP : map;
        this.zzgfr = map;
        this.zzfsz = view;
        this.zzfsy = i;
        this.zzehh = str;
        this.zzfta = str2;
        this.zzgfs = zzcyk;
        HashSet hashSet = new HashSet(unmodifiableSet);
        for (zzt zzt : map.values()) {
            hashSet.addAll(zzt.zzenh);
        }
        this.zzgfq = Collections.unmodifiableSet(hashSet);
    }

    public static zzr zzcm(Context context) {
        return new GoogleApiClient.Builder(context).zzaic();
    }

    public final Account getAccount() {
        return this.zzeho;
    }

    @Deprecated
    public final String getAccountName() {
        Account account = this.zzeho;
        if (account != null) {
            return account.name;
        }
        return null;
    }

    public final Account zzamd() {
        Account account = this.zzeho;
        return account != null ? account : new Account("<<default account>>", "com.google");
    }

    public final int zzame() {
        return this.zzfsy;
    }

    public final Set<Scope> zzamf() {
        return this.zzfsw;
    }

    public final Set<Scope> zzamg() {
        return this.zzgfq;
    }

    public final Map<Api<?>, zzt> zzamh() {
        return this.zzgfr;
    }

    public final String zzami() {
        return this.zzehh;
    }

    public final String zzamj() {
        return this.zzfta;
    }

    public final View zzamk() {
        return this.zzfsz;
    }

    public final zzcyk zzaml() {
        return this.zzgfs;
    }

    public final Integer zzamm() {
        return this.zzgft;
    }

    public final Set<Scope> zzc(Api<?> api) {
        zzt zzt = this.zzgfr.get(api);
        if (zzt == null || zzt.zzenh.isEmpty()) {
            return this.zzfsw;
        }
        HashSet hashSet = new HashSet(this.zzfsw);
        hashSet.addAll(zzt.zzenh);
        return hashSet;
    }

    public final void zzc(Integer num) {
        this.zzgft = num;
    }
}
