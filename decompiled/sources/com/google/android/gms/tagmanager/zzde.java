package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbt;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public final class zzde extends zzbr {
    private static final String ID = zzbh.LANGUAGE.toString();

    public zzde() {
        super(ID, new String[0]);
    }

    public final boolean zzbfh() {
        return false;
    }

    public final /* bridge */ /* synthetic */ String zzbgp() {
        return super.zzbgp();
    }

    public final /* bridge */ /* synthetic */ Set zzbgq() {
        return super.zzbgq();
    }

    public final zzbt zzx(Map<String, zzbt> map) {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return zzgk.zzbil();
        }
        String language = locale.getLanguage();
        return language == null ? zzgk.zzbil() : zzgk.zzam(language.toLowerCase());
    }
}
