package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import java.util.Objects;

public final class zzcv {
    private static final String[] zzahz = {"/aclk", "/pcs/click"};
    private String zzahv = "googleads.g.doubleclick.net";
    private String zzahw = "/pagead/ads";
    private String zzahx = "ad.doubleclick.net";
    private String[] zzahy = {".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private zzcr zzaia;

    public zzcv(zzcr zzcr) {
        this.zzaia = zzcr;
    }

    private final Uri zza(Uri uri, Context context, String str, boolean z, View view, Activity activity) throws zzcw {
        try {
            boolean zza = zza(uri);
            if (zza) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new zzcw("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new zzcw("Query parameter already exists: ms");
            }
            String zza2 = z ? this.zzaia.zza(context, str, view, activity) : this.zzaia.zza(context);
            if (zza) {
                String uri2 = uri.toString();
                int indexOf = uri2.indexOf(";adurl");
                if (indexOf != -1) {
                    int i = indexOf + 1;
                    return Uri.parse(uri2.substring(0, i) + "dc_ms" + "=" + zza2 + ";" + uri2.substring(i));
                }
                String encodedPath = uri.getEncodedPath();
                int indexOf2 = uri2.indexOf(encodedPath);
                return Uri.parse(uri2.substring(0, encodedPath.length() + indexOf2) + ";" + "dc_ms" + "=" + zza2 + ";" + uri2.substring(indexOf2 + encodedPath.length()));
            }
            String uri3 = uri.toString();
            int indexOf3 = uri3.indexOf("&adurl");
            if (indexOf3 == -1) {
                indexOf3 = uri3.indexOf("?adurl");
            }
            if (indexOf3 == -1) {
                return uri.buildUpon().appendQueryParameter("ms", zza2).build();
            }
            int i2 = indexOf3 + 1;
            return Uri.parse(uri3.substring(0, i2) + "ms" + "=" + zza2 + "&" + uri3.substring(i2));
        } catch (UnsupportedOperationException unused) {
            throw new zzcw("Provided Uri is not in a valid state");
        }
    }

    private final boolean zza(Uri uri) {
        Objects.requireNonNull(uri);
        try {
            return uri.getHost().equals(this.zzahx);
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public final Uri zza(Uri uri, Context context) throws zzcw {
        return zza(uri, context, (String) null, false, (View) null, (Activity) null);
    }

    public final Uri zza(Uri uri, Context context, View view, Activity activity) throws zzcw {
        try {
            return zza(uri, context, uri.getQueryParameter("ai"), true, view, activity);
        } catch (UnsupportedOperationException unused) {
            throw new zzcw("Provided Uri is not in a valid state");
        }
    }

    public final void zza(MotionEvent motionEvent) {
        this.zzaia.zza(motionEvent);
    }

    public final zzcr zzae() {
        return this.zzaia;
    }

    public final boolean zzb(Uri uri) {
        Objects.requireNonNull(uri);
        try {
            String host = uri.getHost();
            for (String endsWith : this.zzahy) {
                if (host.endsWith(endsWith)) {
                    return true;
                }
            }
        } catch (NullPointerException unused) {
        }
        return false;
    }

    public final boolean zzc(Uri uri) {
        if (zzb(uri)) {
            for (String endsWith : zzahz) {
                if (uri.getPath().endsWith(endsWith)) {
                    return true;
                }
            }
        }
        return false;
    }
}
