package com.google.android.gms.awareness;

import android.accounts.Account;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import java.util.Arrays;

public class AwarenessOptions implements Api.ApiOptions.Optional {
    public static final int NO_UID = -1;
    private final Account zzeho;
    private final String zzeqs;
    private final int zzeqt;
    private final String zzequ;
    private final String zzeqv;
    private final int zzeqw;

    protected AwarenessOptions(String str, int i, String str2, String str3, int i2, Account account) {
        zzbq.checkNotNull(str, "moduleId must not be null");
        this.zzeqs = str;
        this.zzeqt = i;
        this.zzequ = str2;
        this.zzeqv = str3;
        this.zzeqw = i2;
        this.zzeho = account;
    }

    @Deprecated
    public static AwarenessOptions create1p(String str) {
        zzbq.zzgv(str);
        return new AwarenessOptions(str, 1, (String) null, (String) null, -1, (Account) null);
    }

    public static AwarenessOptions create1p(String str, Account account) {
        zzbq.zzgv(str);
        return new AwarenessOptions(str, 1, (String) null, (String) null, -1, account);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            AwarenessOptions awarenessOptions = (AwarenessOptions) obj;
            return this.zzeqt == awarenessOptions.zzeqt && this.zzeqw == awarenessOptions.zzeqw && zzbg.equal(this.zzeqs, awarenessOptions.zzeqs) && zzbg.equal(this.zzequ, awarenessOptions.zzequ) && zzbg.equal(this.zzeqv, awarenessOptions.zzeqv) && zzbg.equal(this.zzeho, awarenessOptions.zzeho);
        }
        return false;
    }

    public final Account getAccount() {
        return this.zzeho;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzeqs, Integer.valueOf(this.zzeqt), this.zzequ, this.zzeqv, Integer.valueOf(this.zzeqw), this.zzeho});
    }

    public final String zzada() {
        return this.zzeqs;
    }

    public final int zzadb() {
        return this.zzeqt;
    }

    public final String zzadc() {
        return this.zzequ;
    }

    public final String zzadd() {
        return this.zzeqv;
    }

    public final int zzade() {
        return this.zzeqw;
    }
}
