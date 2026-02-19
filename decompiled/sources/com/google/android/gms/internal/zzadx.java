package com.google.android.gms.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzadx {
    private int mOrientation = -1;
    private zzacl zzaoq;
    private String zzbxx;
    private boolean zzciv = false;
    private final zzacf zzcjk;
    private List<String> zzcpa;
    private String zzcwq;
    private String zzcwr;
    private List<String> zzcws;
    private String zzcwt;
    private String zzcwu;
    private String zzcwv;
    private List<String> zzcww;
    private long zzcwx = -1;
    private boolean zzcwy = false;
    private final long zzcwz = -1;
    private long zzcxa = -1;
    private boolean zzcxb = false;
    private boolean zzcxc = false;
    private boolean zzcxd = false;
    private boolean zzcxe = true;
    private boolean zzcxf = true;
    private String zzcxg = "";
    private boolean zzcxh = false;
    private zzagd zzcxi;
    private List<String> zzcxj;
    private List<String> zzcxk;
    private boolean zzcxl = false;
    private boolean zzcxm = false;
    private String zzcxn;
    private List<String> zzcxo;
    private boolean zzcxp;
    private String zzcxq;
    private zzagn zzcxr;
    private boolean zzcxs;
    private boolean zzcxt;

    public zzadx(zzacf zzacf, String str) {
        this.zzcwr = str;
        this.zzcjk = zzacf;
    }

    private static String zza(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }

    private static long zzb(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list == null || list.isEmpty()) {
            return -1;
        }
        String str2 = (String) list.get(0);
        try {
            return (long) (Float.parseFloat(str2) * 1000.0f);
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 36 + String.valueOf(str2).length());
            sb.append("Could not parse float from ");
            sb.append(str);
            sb.append(" header: ");
            sb.append(str2);
            zzahw.zzcz(sb.toString());
            return -1;
        }
    }

    private static List<String> zzc(Map<String, List<String>> map, String str) {
        String str2;
        List list = map.get(str);
        if (list == null || list.isEmpty() || (str2 = (String) list.get(0)) == null) {
            return null;
        }
        return Arrays.asList(str2.trim().split("\\s+"));
    }

    private static boolean zzd(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list == null || list.isEmpty()) {
            return false;
        }
        return Boolean.valueOf((String) list.get(0)).booleanValue();
    }

    public final zzacj zza(long j, boolean z) {
        zzacf zzacf = this.zzcjk;
        String str = this.zzcwr;
        String str2 = this.zzbxx;
        List<String> list = this.zzcws;
        List<String> list2 = this.zzcww;
        long j2 = this.zzcwx;
        boolean z2 = this.zzcwy;
        List<String> list3 = this.zzcpa;
        long j3 = this.zzcxa;
        int i = this.mOrientation;
        String str3 = this.zzcwq;
        String str4 = this.zzcwu;
        String str5 = this.zzcwv;
        String str6 = str4;
        boolean z3 = this.zzcxb;
        boolean z4 = this.zzcxc;
        boolean z5 = this.zzcxd;
        boolean z6 = this.zzcxe;
        String str7 = this.zzcxg;
        boolean z7 = this.zzcxh;
        boolean z8 = this.zzciv;
        zzagd zzagd = this.zzcxi;
        List<String> list4 = this.zzcxj;
        List<String> list5 = this.zzcxk;
        boolean z9 = this.zzcxl;
        zzacl zzacl = this.zzaoq;
        boolean z10 = this.zzcxm;
        String str8 = this.zzcxn;
        List<String> list6 = this.zzcxo;
        boolean z11 = this.zzcxp;
        String str9 = this.zzcxq;
        zzagn zzagn = this.zzcxr;
        String str10 = this.zzcwt;
        boolean z12 = this.zzcxf;
        boolean z13 = z12;
        List<String> list7 = list6;
        boolean z14 = z11;
        List<String> list8 = list5;
        boolean z15 = z9;
        boolean z16 = z5;
        String str11 = str7;
        return new zzacj(zzacf, str, str2, list, list2, j2, z2, -1, list3, j3, i, str3, j, str6, str5, z3, z4, z16, z6, false, str11, z7, z8, zzagd, list4, list8, z15, zzacl, z10, str8, list7, z14, str9, zzagn, str10, z13, this.zzcxs, this.zzcxt, z ? 2 : 1);
    }

    public final void zza(String str, Map<String, List<String>> map, String str2) {
        this.zzbxx = str2;
        zzo(map);
    }

    public final void zzo(Map<String, List<String>> map) {
        int zzrg = -1;
        this.zzcwq = zza(map, "X-Afma-Ad-Size");
        this.zzcxq = zza(map, "X-Afma-Ad-Slot-Size");
        List<String> zzc = zzc(map, "X-Afma-Click-Tracking-Urls");
        if (zzc != null) {
            this.zzcws = zzc;
        }
        this.zzcwt = zza(map, "X-Afma-Debug-Signals");
        List list = map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty()) {
            this.zzcwu = (String) list.get(0);
        }
        List<String> zzc2 = zzc(map, "X-Afma-Tracking-Urls");
        if (zzc2 != null) {
            this.zzcww = zzc2;
        }
        long zzb = zzb(map, "X-Afma-Interstitial-Timeout");
        if (zzb != -1) {
            this.zzcwx = zzb;
        }
        this.zzcwy |= zzd(map, "X-Afma-Mediation");
        List<String> zzc3 = zzc(map, "X-Afma-Manual-Tracking-Urls");
        if (zzc3 != null) {
            this.zzcpa = zzc3;
        }
        long zzb2 = zzb(map, "X-Afma-Refresh-Rate");
        if (zzb2 != -1) {
            this.zzcxa = zzb2;
        }
        List list2 = map.get("X-Afma-Orientation");
        if (list2 != null && !list2.isEmpty()) {
            String str = (String) list2.get(0);
            if ("portrait".equalsIgnoreCase(str)) {
                zzrg = zzbt.zzen().zzrh();
            } else if ("landscape".equalsIgnoreCase(str)) {
                zzrg = zzbt.zzen().zzrg();
            }
            if (zzrg != -1) {
                this.mOrientation = zzrg;
            }
        }
        this.zzcwv = zza(map, "X-Afma-ActiveView");
        List list3 = map.get("X-Afma-Use-HTTPS");
        if (list3 != null && !list3.isEmpty()) {
            this.zzcxd = Boolean.valueOf((String) list3.get(0)).booleanValue();
        }
        this.zzcxb |= zzd(map, "X-Afma-Custom-Rendering-Allowed");
        this.zzcxc = "native".equals(zza(map, "X-Afma-Ad-Format"));
        List list4 = map.get("X-Afma-Content-Url-Opted-Out");
        if (list4 != null && !list4.isEmpty()) {
            this.zzcxe = Boolean.valueOf((String) list4.get(0)).booleanValue();
        }
        List list5 = map.get("X-Afma-Content-Vertical-Opted-Out");
        if (list5 != null && !list5.isEmpty()) {
            this.zzcxf = Boolean.valueOf((String) list5.get(0)).booleanValue();
        }
        List list6 = map.get("X-Afma-Gws-Query-Id");
        if (list6 != null && !list6.isEmpty()) {
            this.zzcxg = (String) list6.get(0);
        }
        String zza = zza(map, "X-Afma-Fluid");
        if (zza != null && zza.equals("height")) {
            this.zzcxh = true;
        }
        this.zzciv = "native_express".equals(zza(map, "X-Afma-Ad-Format"));
        this.zzcxi = zzagd.zzbu(zza(map, "X-Afma-Rewards"));
        if (this.zzcxj == null) {
            this.zzcxj = zzc(map, "X-Afma-Reward-Video-Start-Urls");
        }
        if (this.zzcxk == null) {
            this.zzcxk = zzc(map, "X-Afma-Reward-Video-Complete-Urls");
        }
        this.zzcxl |= zzd(map, "X-Afma-Use-Displayed-Impression");
        this.zzcxm |= zzd(map, "X-Afma-Auto-Collect-Location");
        this.zzcxn = zza(map, "Set-Cookie");
        String zza2 = zza(map, "X-Afma-Auto-Protection-Configuration");
        if (zza2 == null || TextUtils.isEmpty(zza2)) {
            Uri.Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon();
            buildUpon.appendQueryParameter("id", "gmob-apps-blocked-navigation");
            if (!TextUtils.isEmpty(this.zzcwu)) {
                buildUpon.appendQueryParameter("debugDialog", this.zzcwu);
            }
            boolean booleanValue = ((Boolean) zzlc.zzio().zzd(zzoi.zzbll)).booleanValue();
            String builder = buildUpon.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(builder).length() + 31);
            sb.append(builder);
            sb.append("&navigationURL={NAVIGATION_URL}");
            this.zzaoq = new zzacl(booleanValue, Arrays.asList(new String[]{sb.toString()}));
        } else {
            try {
                this.zzaoq = zzacl.zzk(new JSONObject(zza2));
            } catch (JSONException e) {
                zzahw.zzc("Error parsing configuration JSON", e);
                this.zzaoq = new zzacl();
            }
        }
        List<String> zzc4 = zzc(map, "X-Afma-Remote-Ping-Urls");
        if (zzc4 != null) {
            this.zzcxo = zzc4;
        }
        String zza3 = zza(map, "X-Afma-Safe-Browsing");
        if (!TextUtils.isEmpty(zza3)) {
            try {
                this.zzcxr = zzagn.zzn(new JSONObject(zza3));
            } catch (JSONException e2) {
                zzahw.zzc("Error parsing safe browsing header", e2);
            }
        }
        this.zzcxp |= zzd(map, "X-Afma-Render-In-Browser");
        String zza4 = zza(map, "X-Afma-Pool");
        if (!TextUtils.isEmpty(zza4)) {
            try {
                this.zzcxs = new JSONObject(zza4).getBoolean("never_pool");
            } catch (JSONException e3) {
                zzahw.zzc("Error parsing interstitial pool header", e3);
            }
        }
        this.zzcxt = zzd(map, "X-Afma-Custom-Close-Blocked");
    }
}
