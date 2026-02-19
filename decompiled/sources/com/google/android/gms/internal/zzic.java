package com.google.android.gms.internal;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

@zzabh
public final class zzic {
    private final int zzbae;
    private final zzhs zzbag;
    private String zzbao;
    private String zzbap;
    private final boolean zzbaq = false;
    private final int zzbar;
    private final int zzbas;

    public zzic(int i, int i2, int i3) {
        this.zzbae = i;
        if (i2 > 64 || i2 < 0) {
            this.zzbar = 64;
        } else {
            this.zzbar = i2;
        }
        if (i3 <= 0) {
            this.zzbas = 1;
        } else {
            this.zzbas = i3;
        }
        this.zzbag = new zzib(this.zzbar);
    }

    private final boolean zza(String str, HashSet<String> hashSet) {
        boolean z;
        String[] split = str.split("\n");
        if (split.length == 0) {
            return true;
        }
        for (String str2 : split) {
            if (str2.indexOf("'") != -1) {
                StringBuilder sb = new StringBuilder(str2);
                int i = 1;
                boolean z2 = false;
                while (true) {
                    int i2 = i + 2;
                    if (i2 > sb.length()) {
                        break;
                    }
                    if (sb.charAt(i) == '\'') {
                        if (sb.charAt(i - 1) != ' ') {
                            int i3 = i + 1;
                            if ((sb.charAt(i3) == 's' || sb.charAt(i3) == 'S') && (i2 == sb.length() || sb.charAt(i2) == ' ')) {
                                sb.insert(i, ' ');
                                i = i2;
                                z2 = true;
                            }
                        }
                        sb.setCharAt(i, ' ');
                        z2 = true;
                    }
                    i++;
                }
                String sb2 = z2 ? sb.toString() : null;
                if (sb2 != null) {
                    this.zzbap = sb2;
                    str2 = sb2;
                }
            }
            String[] zzb = zzhw.zzb(str2, true);
            if (zzb.length >= this.zzbas) {
                for (int i4 = 0; i4 < zzb.length; i4++) {
                    String str3 = "";
                    int i5 = 0;
                    while (true) {
                        if (i5 >= this.zzbas) {
                            z = true;
                            break;
                        }
                        int i6 = i4 + i5;
                        if (i6 >= zzb.length) {
                            z = false;
                            break;
                        }
                        if (i5 > 0) {
                            str3 = String.valueOf(str3).concat(" ");
                        }
                        String valueOf = String.valueOf(str3);
                        String valueOf2 = String.valueOf(zzb[i6]);
                        str3 = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                        i5++;
                    }
                    if (!z) {
                        break;
                    }
                    hashSet.add(str3);
                    if (hashSet.size() >= this.zzbae) {
                        return false;
                    }
                }
                if (hashSet.size() >= this.zzbae) {
                    return false;
                }
            }
        }
        return true;
    }

    public final String zza(ArrayList<String> arrayList, ArrayList<zzhr> arrayList2) {
        Collections.sort(arrayList2, new zzid(this));
        HashSet hashSet = new HashSet();
        int i = 0;
        while (i < arrayList2.size() && zza(Normalizer.normalize(arrayList.get(arrayList2.get(i).zzhg()), Normalizer.Form.NFKC).toLowerCase(Locale.US), (HashSet<String>) hashSet)) {
            i++;
        }
        zzhv zzhv = new zzhv();
        this.zzbao = "";
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            try {
                zzhv.write(this.zzbag.zzy((String) it.next()));
            } catch (IOException e) {
                zzahw.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zzhv.toString();
    }
}
