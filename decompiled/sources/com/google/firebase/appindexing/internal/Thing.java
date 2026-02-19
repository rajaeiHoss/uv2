package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.firebase.appindexing.Indexable;
import com.streamax.config.constant.Constants;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;

public final class Thing extends zzbgl implements ReflectedParcelable, Indexable {
    public static final Parcelable.Creator<Thing> CREATOR = new zzaa();
    private final String type;
    private final Bundle zzegs;
    private int zzehz;
    private final zza zzmnz;
    private final String zzwc;

    public static class zza extends zzbgl {
        public static final Parcelable.Creator<zza> CREATOR = new zzx();
        private final int score;
        private final Bundle zzegs;
        private final boolean zzmnx;
        private final String zzmny;

        public zza(boolean z, int i, String str, Bundle bundle) {
            this.zzmnx = z;
            this.score = i;
            this.zzmny = str;
            this.zzegs = bundle == null ? new Bundle() : bundle;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("worksOffline: ");
            sb.append(this.zzmnx);
            sb.append(", score: ");
            sb.append(this.score);
            if (!this.zzmny.isEmpty()) {
                sb.append(", accountEmail: ");
                sb.append(this.zzmny);
            }
            Bundle bundle = this.zzegs;
            if (bundle != null && !bundle.isEmpty()) {
                sb.append(", Properties { ");
                Thing.zza(this.zzegs, sb);
                sb.append(Constants.JsonSstringSuffix);
            }
            return sb.toString();
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int zze = zzbgo.zze(parcel);
            zzbgo.zza(parcel, 1, this.zzmnx);
            zzbgo.zzc(parcel, 2, this.score);
            zzbgo.zza(parcel, 3, this.zzmny, false);
            zzbgo.zza(parcel, 4, this.zzegs, false);
            zzbgo.zzai(parcel, zze);
        }
    }

    public Thing(int i, Bundle bundle, zza zza2, String str, String str2) {
        this.zzehz = i;
        this.zzegs = bundle;
        this.zzmnz = zza2;
        this.zzwc = str;
        this.type = str2;
        bundle.setClassLoader(getClass().getClassLoader());
    }

    public Thing(Bundle bundle, zza zza2, String str, String str2) {
        this.zzehz = 10;
        this.zzegs = bundle;
        this.zzmnz = zza2;
        this.zzwc = str;
        this.type = str2;
    }

    /* access modifiers changed from: private */
    public static void zza(Bundle bundle, StringBuilder sb) {
        String obj;
        try {
            Set keySet = bundle.keySet();
            String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
            Arrays.sort(strArr, zzz.zzemo);
            for (String str : strArr) {
                sb.append("{ key: '");
                sb.append(str);
                sb.append("' value: ");
                Object obj2 = bundle.get(str);
                if (obj2 == null) {
                    obj = "<null>";
                } else if (obj2.getClass().isArray()) {
                    sb.append("[ ");
                    for (int i = 0; i < Array.getLength(obj2); i++) {
                        sb.append("'");
                        sb.append(Array.get(obj2, i));
                        sb.append("' ");
                    }
                    obj = "]";
                } else {
                    obj = obj2.toString();
                }
                sb.append(obj);
                sb.append(" } ");
            }
        } catch (RuntimeException unused) {
            sb.append("<error>");
        }
    }

    static final /* synthetic */ int zzaz(String str, String str2) {
        if (str == null) {
            return str2 == null ? 0 : -1;
        }
        if (str2 == null) {
            return 1;
        }
        return str.compareTo(str2);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.type.equals("Thing") ? "Indexable" : this.type);
        sb.append(" { { id: ");
        if (this.zzwc == null) {
            str = "<null>";
        } else {
            str = "'";
            sb.append(str);
            sb.append(this.zzwc);
        }
        sb.append(str);
        sb.append(" } Properties { ");
        zza(this.zzegs, sb);
        sb.append("} ");
        sb.append("Metadata { ");
        sb.append(this.zzmnz.toString());
        sb.append(" } ");
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzegs, false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzmnz, i, false);
        zzbgo.zza(parcel, 3, this.zzwc, false);
        zzbgo.zza(parcel, 4, this.type, false);
        zzbgo.zzc(parcel, 1000, this.zzehz);
        zzbgo.zzai(parcel, zze);
    }
}
