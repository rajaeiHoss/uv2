package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzejo {
    private final List<String> zzniy = new ArrayList();
    private int zzniz;

    private zzejo(zzegu zzegu) throws DatabaseException {
        this.zzniz = 0;
        Iterator<zzemq> it = zzegu.iterator();
        while (it.hasNext()) {
            this.zzniy.add(it.next().asString());
        }
        this.zzniz = Math.max(1, this.zzniy.size());
        for (int i = 0; i < this.zzniy.size(); i++) {
            this.zzniz += zzc(this.zzniy.get(i));
        }
        zzbzm();
    }

    public static void zza(zzegu zzegu, Object obj) throws DatabaseException {
        new zzejo(zzegu).zzbv(obj);
    }

    private final void zzbv(Object obj) throws DatabaseException {
        if (obj instanceof Map) {
            Map map = (Map) obj;
            for (Object key : map.keySet()) {
                String str = (String) key;
                if (!str.startsWith(".")) {
                    zzqe(str);
                    zzbv(map.get(str));
                    zzbzl();
                }
            }
        } else if (obj instanceof List) {
            List list = (List) obj;
            for (int i = 0; i < list.size(); i++) {
                zzqe(Integer.toString(i));
                zzbv(list.get(i));
                zzbzl();
            }
        }
    }

    private final String zzbzl() {
        List<String> list = this.zzniy;
        String remove = list.remove(list.size() - 1);
        this.zzniz -= zzc(remove);
        if (this.zzniy.size() > 0) {
            this.zzniz--;
        }
        return remove;
    }

    private final void zzbzm() throws DatabaseException {
        String str;
        if (this.zzniz > 768) {
            int i = this.zzniz;
            StringBuilder sb = new StringBuilder(56);
            sb.append("Data has a key path longer than 768 bytes (");
            sb.append(i);
            sb.append(").");
            throw new DatabaseException(sb.toString());
        } else if (this.zzniy.size() > 32) {
            if (this.zzniy.size() == 0) {
                str = "";
            } else {
                String zzg = zzg("/", this.zzniy);
                StringBuilder sb2 = new StringBuilder(String.valueOf(zzg).length() + 10);
                sb2.append("in path '");
                sb2.append(zzg);
                sb2.append("'");
                str = sb2.toString();
            }
            String valueOf = String.valueOf(str);
            throw new DatabaseException(valueOf.length() != 0 ? "Path specified exceeds the maximum depth that can be written (32) or object contains a cycle ".concat(valueOf) : new String("Path specified exceeds the maximum depth that can be written (32) or object contains a cycle "));
        }
    }

    private static int zzc(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt <= 127) {
                i2++;
            } else if (charAt <= 2047) {
                i2 += 2;
            } else if (Character.isHighSurrogate(charAt)) {
                i2 += 4;
                i++;
            } else {
                i2 += 3;
            }
            i++;
        }
        return i2;
    }

    private static String zzg(String str, List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append(str);
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    private final void zzqe(String str) throws DatabaseException {
        if (this.zzniy.size() > 0) {
            this.zzniz++;
        }
        this.zzniy.add(str);
        this.zzniz += zzc(str);
        zzbzm();
    }
}
