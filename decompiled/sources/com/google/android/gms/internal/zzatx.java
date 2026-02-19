package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;
import java.util.BitSet;

public final class zzatx extends zzbgl {
    public static final Parcelable.Creator<zzatx> CREATOR = new zzatz();
    private Account account;
    private zzauc[] zzeff;
    private String zzefg;
    private boolean zzefh;

    zzatx(String str, boolean z, Account account2, zzauc... zzaucArr) {
        this(zzaucArr, str, z, account2);
        if (zzaucArr != null) {
            BitSet bitSet = new BitSet(zzaui.zzefv.length);
            for (zzauc zzauc : zzaucArr) {
                int i = zzauc.zzefp;
                if (i != -1) {
                    if (bitSet.get(i)) {
                        String valueOf = String.valueOf(zzaui.zzaw(i));
                        throw new IllegalArgumentException(valueOf.length() != 0 ? "Duplicate global search section type ".concat(valueOf) : new String("Duplicate global search section type "));
                    }
                    bitSet.set(i);
                }
            }
        }
    }

    zzatx(zzauc[] zzaucArr, String str, boolean z, Account account2) {
        this.zzeff = zzaucArr;
        this.zzefg = str;
        this.zzefh = z;
        this.account = account2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzatx) {
            zzatx zzatx = (zzatx) obj;
            return zzbg.equal(this.zzefg, zzatx.zzefg) && zzbg.equal(Boolean.valueOf(this.zzefh), Boolean.valueOf(zzatx.zzefh)) && zzbg.equal(this.account, zzatx.account) && Arrays.equals(this.zzeff, zzatx.zzeff);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzefg, Boolean.valueOf(this.zzefh), this.account, Integer.valueOf(Arrays.hashCode(this.zzeff))});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzeff, i, false);
        zzbgo.zza(parcel, 2, this.zzefg, false);
        zzbgo.zza(parcel, 3, this.zzefh);
        zzbgo.zza(parcel, 4, (Parcelable) this.account, i, false);
        zzbgo.zzai(parcel, zze);
    }
}
