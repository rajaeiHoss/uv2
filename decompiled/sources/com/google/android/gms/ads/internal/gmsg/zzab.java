package com.google.android.gms.ads.internal.gmsg;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzagx;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzala;
import com.google.android.gms.internal.zzaof;
import com.google.android.gms.internal.zzapa;
import com.google.android.gms.internal.zzapb;
import com.google.android.gms.internal.zzapo;
import com.google.android.gms.internal.zzapr;
import com.google.android.gms.internal.zzapt;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zzcw;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzyd;
import java.net.URISyntaxException;
import java.util.Map;
import org.jivesoftware.smackx.packet.CapsExtension;

@zzabh
public final class zzab<T extends zzapa & zzapb & zzapo & zzapr & zzapt> implements zzt<T> {
    private final Context mContext;
    private zzala zzarg;
    private zzkf zzbgt;
    private zzb zzcbc;
    private zzw zzccm;
    private zzyd zzccn;
    private final zzcv zzccq;
    private com.google.android.gms.ads.internal.overlay.zzt zzccr;
    private zzn zzccs;
    private zzaof zzcct = null;

    public zzab(Context context, zzala zzala, zzcv zzcv, com.google.android.gms.ads.internal.overlay.zzt zzt, zzkf zzkf, zzb zzb, zzn zzn, zzw zzw, zzyd zzyd) {
        this.mContext = context;
        this.zzarg = zzala;
        this.zzccq = zzcv;
        this.zzccr = zzt;
        this.zzbgt = zzkf;
        this.zzcbc = zzb;
        this.zzccm = zzw;
        this.zzccn = zzyd;
        this.zzccs = zzn;
    }

    static String zza(Context context, zzcv zzcv, String str, View view, Activity activity) {
        if (zzcv == null) {
            return str;
        }
        try {
            Uri parse = Uri.parse(str);
            if (zzcv.zzc(parse)) {
                parse = zzcv.zza(parse, context, view, activity);
            }
            return parse.toString();
        } catch (zzcw unused) {
            return str;
        } catch (Exception e) {
            zzbt.zzep().zza(e, "OpenGmsgHandler.maybeAddClickSignalsToUrl");
            return str;
        }
    }

    private static boolean zzk(Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }

    private static int zzl(Map<String, String> map) {
        String str = map.get("o");
        if (str == null) {
            return -1;
        }
        if ("p".equalsIgnoreCase(str)) {
            return zzbt.zzen().zzrh();
        }
        if ("l".equalsIgnoreCase(str)) {
            return zzbt.zzen().zzrg();
        }
        if (CapsExtension.NODE_NAME.equalsIgnoreCase(str)) {
            return zzbt.zzen().zzri();
        }
        return -1;
    }

    private final void zzl(boolean z) {
        zzyd zzyd = this.zzccn;
        if (zzyd != null) {
            zzyd.zzm(z);
        }
    }

    public final void zza(T t, Map<String, String> map) {
        zzapa zzapa = t;
        String zzb = zzagx.zzb((String) map.get("u"), zzapa.getContext());
        String str = (String) map.get("a");
        if (str == null) {
            zzahw.zzcz("Action missing from an open GMSG.");
            return;
        }
        zzw zzw = this.zzccm;
        if (zzw != null && !zzw.zzcz()) {
            this.zzccm.zzt(zzb);
        } else if ("expand".equalsIgnoreCase(str)) {
            if (((zzapb) zzapa).zzud()) {
                zzahw.zzcz("Cannot expand WebView that is already expanded.");
                return;
            }
            zzl(false);
            ((zzapo) zzapa).zza(zzk(map), zzl((Map<String, String>) map));
        } else if ("webapp".equalsIgnoreCase(str)) {
            zzl(false);
            zzapo zzapo = (zzapo) zzapa;
            boolean zzk = zzk(map);
            if (zzb != null) {
                zzapo.zza(zzk, zzl((Map<String, String>) map), zzb);
            } else {
                zzapo.zza(zzk, zzl((Map<String, String>) map), (String) map.get("html"), (String) map.get("baseurl"));
            }
        } else if (!"app".equalsIgnoreCase(str) || !"true".equalsIgnoreCase((String) map.get("system_browser"))) {
            zzl(true);
            String str2 = (String) map.get("intent_url");
            Intent intent = null;
            if (!TextUtils.isEmpty(str2)) {
                try {
                    intent = Intent.parseUri(str2, 0);
                } catch (URISyntaxException e) {
                    String valueOf = String.valueOf(str2);
                    zzahw.zzb(valueOf.length() != 0 ? "Error parsing the url: ".concat(valueOf) : new String("Error parsing the url: "), e);
                }
            }
            if (!(intent == null || intent.getData() == null)) {
                Uri data = intent.getData();
                String uri = data.toString();
                if (!TextUtils.isEmpty(uri)) {
                    try {
                        uri = zza(zzapa.getContext(), ((zzapr) zzapa).zzuc(), uri, ((zzapt) zzapa).getView(), zzapa.zztj());
                    } catch (Exception e2) {
                        zzahw.zzb("Error occurred while adding signals.", e2);
                        zzbt.zzep().zza(e2, "OpenGmsgHandler.onGmsg");
                    }
                    try {
                        data = Uri.parse(uri);
                    } catch (Exception e3) {
                        String valueOf2 = String.valueOf(uri);
                        zzahw.zzb(valueOf2.length() != 0 ? "Error parsing the uri: ".concat(valueOf2) : new String("Error parsing the uri: "), e3);
                        zzbt.zzep().zza(e3, "OpenGmsgHandler.onGmsg");
                    }
                }
                intent.setData(data);
            }
            if (intent != null) {
                ((zzapo) zzapa).zza(new zzc(intent));
                return;
            }
            if (!TextUtils.isEmpty(zzb)) {
                zzb = zza(zzapa.getContext(), ((zzapr) zzapa).zzuc(), zzb, ((zzapt) zzapa).getView(), zzapa.zztj());
            }
            ((zzapo) zzapa).zza(new zzc((String) map.get("i"), zzb, (String) map.get("m"), (String) map.get("p"), (String) map.get(CapsExtension.NODE_NAME), (String) map.get("f"), (String) map.get("e")));
        } else {
            zzl(true);
            zzapa.getContext();
            if (TextUtils.isEmpty(zzb)) {
                zzahw.zzcz("Destination url cannot be empty.");
                return;
            }
            try {
                ((zzapo) zzapa).zza(new zzc(new zzac(zzapa.getContext(), ((zzapr) zzapa).zzuc(), ((zzapt) zzapa).getView()).zzm(map)));
            } catch (ActivityNotFoundException e4) {
                zzahw.zzcz(e4.getMessage());
            }
        }
    }
}
