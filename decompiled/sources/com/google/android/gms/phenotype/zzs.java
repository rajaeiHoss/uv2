package com.google.android.gms.phenotype;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.phenotype.PhenotypeFlag;

final class zzs extends PhenotypeFlag<String> {
    zzs(PhenotypeFlag.Factory factory, String str, String str2) {
        super(factory, str, str2, (zzr) null);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzc */
    public final String zzb(SharedPreferences sharedPreferences) {
        try {
            return sharedPreferences.getString(this.zzkgv, (String) null);
        } catch (ClassCastException e) {
            String valueOf = String.valueOf(this.zzkgv);
            Log.e("PhenotypeFlag", valueOf.length() != 0 ? "Invalid string value in SharedPreferences for ".concat(valueOf) : new String("Invalid string value in SharedPreferences for "), e);
            return null;
        }
    }

    public final String zzkz(String str) {
        return str;
    }
}
