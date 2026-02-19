package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

public final class zzbeh {
    public static final Api.zzf<zzbdp> zzfng = new Api.zzf<>();
    public static final Api.zzf<zzbfa> zzfnh = new Api.zzf<>();
    public static final Api.zzf<zzbez> zzfni = new Api.zzf<>();
    private static Api.zzf<Api.zze> zzfnj = new Api.zzf<>();
    private static Api.zzf<Api.zze> zzfnk = new Api.zzf<>();
    private static Charset zzfnl;
    private static String zzfnm = zzbdw.zzfw("com.google.cast.multizone");

    static {
        Charset charset;
        try {
            charset = Charset.forName("UTF-8");
        } catch (IllegalCharsetNameException | UnsupportedCharsetException unused) {
            charset = null;
        }
        zzfnl = charset;
    }
}
