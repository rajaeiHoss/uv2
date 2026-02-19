package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.plus.PlusShare;
import java.util.List;

@zzabh
public final class zzin extends zzbgl {
    public static final Parcelable.Creator<zzin> CREATOR = new zzio();
    public final String url;
    private long zzbba;
    private String zzbbb;
    private String zzbbc;
    private String zzbbd;
    private Bundle zzbbe;
    private boolean zzbbf;
    private long zzbbg;

    zzin(String str, long j, String str2, String str3, String str4, Bundle bundle, boolean z, long j2) {
        this.url = str;
        this.zzbba = j;
        this.zzbbb = str2 == null ? "" : str2;
        this.zzbbc = str3 == null ? "" : str3;
        this.zzbbd = str4 == null ? "" : str4;
        this.zzbbe = bundle == null ? new Bundle() : bundle;
        this.zzbbf = z;
        this.zzbbg = j2;
    }

    public static zzin zzab(String str) {
        return zzd(Uri.parse(str));
    }

    public static zzin zzd(Uri uri) {
        try {
            if (!"gcache".equals(uri.getScheme())) {
                return null;
            }
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.size() != 2) {
                int size = pathSegments.size();
                StringBuilder sb = new StringBuilder(62);
                sb.append("Expected 2 path parts for namespace and id, found :");
                sb.append(size);
                zzahw.zzcz(sb.toString());
                return null;
            }
            String str = pathSegments.get(0);
            String str2 = pathSegments.get(1);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter(PlusShare.KEY_CALL_TO_ACTION_URL);
            boolean equals = "1".equals(uri.getQueryParameter("read_only"));
            String queryParameter2 = uri.getQueryParameter("expiration");
            long parseLong = queryParameter2 == null ? 0 : Long.parseLong(queryParameter2);
            Bundle bundle = new Bundle();
            for (String next : zzbt.zzen().zzg(uri)) {
                if (next.startsWith("tag.")) {
                    bundle.putString(next.substring(4), uri.getQueryParameter(next));
                }
            }
            return new zzin(queryParameter, parseLong, host, str, str2, bundle, equals, 0);
        } catch (NullPointerException | NumberFormatException e) {
            zzahw.zzc("Unable to parse Uri into cache offering.", e);
            return null;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.url, false);
        zzbgo.zza(parcel, 3, this.zzbba);
        zzbgo.zza(parcel, 4, this.zzbbb, false);
        zzbgo.zza(parcel, 5, this.zzbbc, false);
        zzbgo.zza(parcel, 6, this.zzbbd, false);
        zzbgo.zza(parcel, 7, this.zzbbe, false);
        zzbgo.zza(parcel, 8, this.zzbbf);
        zzbgo.zza(parcel, 9, this.zzbbg);
        zzbgo.zzai(parcel, zze);
    }
}
