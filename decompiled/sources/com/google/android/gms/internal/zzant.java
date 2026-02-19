package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smackx.GroupChatInvitation;
import org.json.JSONObject;

@zzabh
public final class zzant implements zzt<zzann> {
    private boolean zzdok;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = map.get(str);
        if (str2 == null) {
            return i;
        }
        try {
            zzlc.zzij();
            return zzako.zza(context, Integer.parseInt(str2));
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34 + String.valueOf(str2).length());
            sb.append("Could not parse ");
            sb.append(str);
            sb.append(" in a video GMSG: ");
            sb.append(str2);
            zzahw.zzcz(sb.toString());
            return i;
        }
    }

    private static void zza(zzanb zzanb, Map<String, String> map) {
        String str = map.get("minBufferMs");
        String str2 = map.get("maxBufferMs");
        String str3 = map.get("bufferForPlaybackMs");
        String str4 = map.get("bufferForPlaybackAfterRebufferMs");
        if (str != null) {
            try {
                Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                zzahw.zzcz(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", new Object[]{str, str2}));
                return;
            }
        }
        if (str2 != null) {
            Integer.parseInt(str2);
        }
        if (str3 != null) {
            Integer.parseInt(str3);
        }
        if (str4 != null) {
            Integer.parseInt(str4);
        }
    }

    public final void zza(zzann zzann, Map<String, String> map) {
        int i;
        int i2;
        String str = (String) map.get("action");
        if (str == null) {
            zzahw.zzcz("Action missing from video GMSG.");
            return;
        }
        if (zzahw.zzae(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String jSONObject2 = jSONObject.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(jSONObject2).length());
            sb.append("Video GMSG: ");
            sb.append(str);
            sb.append(" ");
            sb.append(jSONObject2);
            zzahw.zzby(sb.toString());
        }
        if ("background".equals(str)) {
            String str2 = (String) map.get("color");
            if (TextUtils.isEmpty(str2)) {
                zzahw.zzcz("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                zzann.setBackgroundColor(Color.parseColor(str2));
            } catch (IllegalArgumentException unused) {
                zzahw.zzcz("Invalid color parameter in video GMSG.");
            }
        } else {
            if ("decoderProps".equals(str)) {
                String str3 = (String) map.get("mimeTypes");
                if (str3 == null) {
                    zzahw.zzcz("No MIME types specified for decoder properties inspection.");
                    zzanb.zza(zzann, "missingMimeTypes");
                } else if (Build.VERSION.SDK_INT < 16) {
                    zzahw.zzcz("Video decoder properties available on API versions >= 16.");
                    zzanb.zza(zzann, "deficientApiVersion");
                } else {
                    HashMap hashMap = new HashMap();
                    for (String str4 : str3.split(",")) {
                        hashMap.put(str4, zzakm.zzct(str4.trim()));
                    }
                    zzanb.zza(zzann, (Map<String, List<Map<String, Object>>>) hashMap);
                }
            } else {
                zzane zztg = zzann.zztg();
                if (zztg == null) {
                    zzahw.zzcz("Could not get underlay container for a video GMSG.");
                    return;
                }
                boolean equals = "new".equals(str);
                boolean equals2 = "position".equals(str);
                if (equals || equals2) {
                    Context context = zzann.getContext();
                    int zza = zza(context, map, GroupChatInvitation.ELEMENT_NAME, 0);
                    int zza2 = zza(context, map, "y", 0);
                    int zza3 = zza(context, map, "w", -1);
                    int zza4 = zza(context, map, "h", -1);
                    if (((Boolean) zzlc.zzio().zzd(zzoi.zzbss)).booleanValue()) {
                        zza3 = Math.min(zza3, zzann.zztn() - zza);
                        i = Math.min(zza4, zzann.zztm() - zza2);
                    } else {
                        i = zza4;
                    }
                    try {
                        i2 = Integer.parseInt((String) map.get("player"));
                    } catch (NumberFormatException unused2) {
                        i2 = 0;
                    }
                    boolean parseBoolean = Boolean.parseBoolean((String) map.get("spherical"));
                    if (!equals || zztg.zztb() != null) {
                        zztg.zze(zza, zza2, zza3, i);
                        return;
                    }
                    zztg.zza(zza, zza2, zza3, i, i2, parseBoolean, new zzanm((String) map.get("flags")));
                    zzanb zztb = zztg.zztb();
                    if (zztb != null) {
                        zza(zztb, (Map<String, String>) map);
                        return;
                    }
                    return;
                }
                zzanb zztb2 = zztg.zztb();
                if (zztb2 == null) {
                    zzanb.zza(zzann);
                } else if ("click".equals(str)) {
                    Context context2 = zzann.getContext();
                    int zza5 = zza(context2, map, GroupChatInvitation.ELEMENT_NAME, 0);
                    int zza6 = zza(context2, map, "y", 0);
                    long uptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) zza5, (float) zza6, 0);
                    zztb2.zzf(obtain);
                    obtain.recycle();
                } else if ("currentTime".equals(str)) {
                    String str5 = (String) map.get("time");
                    if (str5 == null) {
                        zzahw.zzcz("Time parameter missing from currentTime video GMSG.");
                        return;
                    }
                    try {
                        zztb2.seekTo((int) (Float.parseFloat(str5) * 1000.0f));
                    } catch (NumberFormatException unused3) {
                        String valueOf = String.valueOf(str5);
                        zzahw.zzcz(valueOf.length() != 0 ? "Could not parse time parameter from currentTime video GMSG: ".concat(valueOf) : new String("Could not parse time parameter from currentTime video GMSG: "));
                    }
                } else if ("hide".equals(str)) {
                    zztb2.setVisibility(4);
                } else if ("load".equals(str)) {
                    zztb2.zzsu();
                } else if ("loadControl".equals(str)) {
                    zza(zztb2, (Map<String, String>) map);
                } else if ("muted".equals(str)) {
                    if (Boolean.parseBoolean((String) map.get("muted"))) {
                        zztb2.zzsv();
                    } else {
                        zztb2.zzsw();
                    }
                } else if ("pause".equals(str)) {
                    zztb2.pause();
                } else if ("play".equals(str)) {
                    zztb2.play();
                } else if ("show".equals(str)) {
                    zztb2.setVisibility(0);
                } else if ("src".equals(str)) {
                    zztb2.zzda((String) map.get("src"));
                } else if ("touchMove".equals(str)) {
                    Context context3 = zzann.getContext();
                    zztb2.zza((float) zza(context3, map, "dx", 0), (float) zza(context3, map, "dy", 0));
                    if (!this.zzdok) {
                        zzann.zzno();
                        this.zzdok = true;
                    }
                } else if ("volume".equals(str)) {
                    String str6 = (String) map.get("volume");
                    if (str6 == null) {
                        zzahw.zzcz("Level parameter missing from volume video GMSG.");
                        return;
                    }
                    try {
                        zztb2.zzb(Float.parseFloat(str6));
                    } catch (NumberFormatException unused4) {
                        String valueOf2 = String.valueOf(str6);
                        zzahw.zzcz(valueOf2.length() != 0 ? "Could not parse volume parameter from volume video GMSG: ".concat(valueOf2) : new String("Could not parse volume parameter from volume video GMSG: "));
                    }
                } else if ("watermark".equals(str)) {
                    zztb2.zzsx();
                } else {
                    String valueOf3 = String.valueOf(str);
                    zzahw.zzcz(valueOf3.length() != 0 ? "Unknown video action: ".concat(valueOf3) : new String("Unknown video action: "));
                }
            }
        }
    }
}
