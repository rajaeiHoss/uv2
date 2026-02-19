package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.plus.PlusShare;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzdha extends zzdcr {
    private static void zza(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    private static String zzb(String str, int i, Set<Character> set) {
        if (i == 1) {
            try {
                return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
            } catch (UnsupportedEncodingException unused) {
                return str;
            }
        } else if (i != 2) {
            return str;
        } else {
            String replace = str.replace("\\", "\\\\");
            for (Character ch : set) {
                String ch2 = ch.toString();
                String valueOf = String.valueOf(ch2);
                replace = replace.replace(ch2, valueOf.length() != 0 ? "\\".concat(valueOf) : new String("\\"));
            }
            return replace;
        }
    }

    private static void zzb(StringBuilder sb, String str, int i, Set<Character> set) {
        sb.append(zzb(str, i, set));
    }

    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length > 0);
        zzdjq<?> zzdjq = zzdjqArr[0];
        zzdjq<?> zzdjq2 = zzdjqArr.length > 1 ? zzdjqArr[1] : zzdjw.zzlcz;
        int i = 2;
        String zzd = (zzdjqArr.length <= 2 || zzdjqArr[2] == zzdjw.zzlcz) ? "" : zzdcq.zzd(zzdjqArr[2]);
        String str = "=";
        if (zzdjqArr.length > 3 && zzdjqArr[3] != zzdjw.zzlcz) {
            str = zzdcq.zzd(zzdjqArr[3]);
        }
        HashSet hashSet = null;
        if (zzdjq2 != zzdjw.zzlcz) {
            zzbq.checkArgument(zzdjq2 instanceof zzdkc);
            if (PlusShare.KEY_CALL_TO_ACTION_URL.equals(zzdjq2.value())) {
                i = 1;
            } else if (!"backslash".equals(zzdjq2.value())) {
                return new zzdkc("");
            } else {
                hashSet = new HashSet();
                zza((Set<Character>) hashSet, zzd);
                zza((Set<Character>) hashSet, str);
                hashSet.remove('\\');
            }
        } else {
            i = 0;
        }
        StringBuilder sb = new StringBuilder();
        if (zzdjq instanceof zzdjx) {
            for (Object value : (List) ((zzdjx) zzdjq).value()) {
                zzdjq zzdjq3 = (zzdjq) value;
                if (!z) {
                    sb.append(zzd);
                }
                zzb(sb, zzdcq.zzd(zzdjq3), i, hashSet);
                z = false;
            }
        } else if (zzdjq instanceof zzdka) {
            Map map = (Map) ((zzdka) zzdjq).value();
            for (Object keyObj : map.keySet()) {
                String str2 = (String) keyObj;
                if (!z) {
                    sb.append(zzd);
                }
                String zzd2 = zzdcq.zzd((zzdjq) map.get(str2));
                zzb(sb, str2, i, hashSet);
                sb.append(str);
                zzb(sb, zzd2, i, hashSet);
                z = false;
            }
        } else {
            zzb(sb, zzdcq.zzd(zzdjq), i, hashSet);
        }
        return new zzdkc(sb.toString());
    }
}
