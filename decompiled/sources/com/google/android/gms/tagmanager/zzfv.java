package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

final class zzfv implements zzbe {
    private final Context mContext;
    private final String zzddt;
    private final zzfy zzktx;
    private final zzfx zzkty;

    zzfv(Context context, zzfx zzfx) {
        this(new zzfw(), context, zzfx);
    }

    private zzfv(zzfy zzfy, Context context, zzfx zzfx) {
        this.zzktx = zzfy;
        this.mContext = context.getApplicationContext();
        this.zzkty = zzfx;
        String str = Build.VERSION.RELEASE;
        Locale locale = Locale.getDefault();
        String str2 = null;
        if (!(locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append(locale.getLanguage().toLowerCase());
            if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
                sb.append("-");
                sb.append(locale.getCountry().toLowerCase());
            }
            str2 = sb.toString();
        }
        this.zzddt = String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{"GoogleTagManager", "4.00", str, str2, Build.MODEL, Build.ID});
    }

    private static URL zzd(zzbx zzbx) {
        try {
            return new URL(zzbx.zzbgt());
        } catch (MalformedURLException unused) {
            zzdj.e("Error trying to parse the GTM url.");
            return null;
        }
    }

    public final void zzam(List<zzbx> list) {
        int min = Math.min(list.size(), 40);
        boolean z = true;
        for (int i = 0; i < min; i++) {
            zzbx zzbx = list.get(i);
            URL zzd = zzd(zzbx);
            if (zzd == null) {
                zzdj.zzcz("No destination: discarding hit.");
                this.zzkty.zzb(zzbx);
            } else {
                HttpURLConnection zzc = null;
                InputStream inputStream = null;
                try {
                    zzc = this.zzktx.zzc(zzd);
                    if (z) {
                        zzdo.zzeo(this.mContext);
                        z = false;
                    }
                    zzc.setRequestProperty("User-Agent", this.zzddt);
                    int responseCode = zzc.getResponseCode();
                    inputStream = zzc.getInputStream();
                    if (responseCode != 200) {
                        StringBuilder sb = new StringBuilder(25);
                        sb.append("Bad response: ");
                        sb.append(responseCode);
                        zzdj.zzcz(sb.toString());
                        this.zzkty.zzc(zzbx);
                    } else {
                        this.zzkty.zza(zzbx);
                    }
                } catch (IOException e) {
                    String valueOf = String.valueOf(e.getClass().getSimpleName());
                    zzdj.zzcz(valueOf.length() != 0 ? "Exception sending hit: ".concat(valueOf) : new String("Exception sending hit: "));
                    zzdj.zzcz(e.getMessage());
                    this.zzkty.zzc(zzbx);
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e2) {
                            zzdj.zzcz(e2.getMessage());
                        }
                    }
                    if (zzc != null) {
                        zzc.disconnect();
                    }
                }
            }
        }
    }

    public final boolean zzbgj() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzdj.v("...no network connectivity");
        return false;
    }
}
