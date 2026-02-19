package com.google.android.gms.internal;

import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.GetTokenResult;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class zzfbh {
    public static Uri zzg(FirebaseApp firebaseApp, String str) throws UnsupportedEncodingException {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.toLowerCase().startsWith("gs://")) {
            String valueOf = String.valueOf(zzfbd.zzsn(zzfbd.zzsp(str.substring(5))));
            return Uri.parse(valueOf.length() != 0 ? "gs://".concat(valueOf) : new String("gs://"));
        }
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        if (scheme == null || (!zzbg.equal(scheme.toLowerCase(), "http") && !zzbg.equal(scheme.toLowerCase(), "https"))) {
            String valueOf2 = String.valueOf(scheme);
            Log.w("StorageUtil", valueOf2.length() != 0 ? "FirebaseStorage is unable to support the scheme:".concat(valueOf2) : new String("FirebaseStorage is unable to support the scheme:"));
            throw new IllegalArgumentException("Uri scheme");
        }
        try {
            int indexOf = parse.getAuthority().toLowerCase().indexOf(zzfbm.zzi(firebaseApp).zzcou());
            String zzso = zzfbd.zzso(parse.getEncodedPath());
            if (indexOf == 0 && zzso.startsWith("/")) {
                int indexOf2 = zzso.indexOf("/b/", 0);
                int i = indexOf2 + 3;
                int indexOf3 = zzso.indexOf("/", i);
                int indexOf4 = zzso.indexOf("/o/", 0);
                if (indexOf2 == -1 || indexOf3 == -1) {
                    Log.w("StorageUtil", "Firebase Storage URLs must point to an object in your Storage Bucket. Please obtain a URL using the Firebase Console or getDownloadUrl().");
                    throw new IllegalArgumentException("Firebase Storage URLs must point to an object in your Storage Bucket. Please obtain a URL using the Firebase Console or getDownloadUrl().");
                }
                str2 = zzso.substring(i, indexOf3);
                zzso = indexOf4 != -1 ? zzso.substring(indexOf4 + 3) : "";
            } else if (indexOf > 1) {
                str2 = parse.getAuthority().substring(0, indexOf - 1);
            } else {
                Log.w("StorageUtil", "Firebase Storage URLs must point to an object in your Storage Bucket. Please obtain a URL using the Firebase Console or getDownloadUrl().");
                throw new IllegalArgumentException("Firebase Storage URLs must point to an object in your Storage Bucket. Please obtain a URL using the Firebase Console or getDownloadUrl().");
            }
            zzbq.zzh(str2, "No bucket specified");
            return new Uri.Builder().scheme("gs").authority(str2).encodedPath(zzso).build();
        } catch (RemoteException unused) {
            throw new UnsupportedEncodingException("Could not parse Url because the Storage network layer did not load");
        }
    }

    public static String zzh(FirebaseApp firebaseApp) {
        try {
            String token = ((GetTokenResult) Tasks.await(firebaseApp.getToken(false), NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, TimeUnit.MILLISECONDS)).getToken();
            if (!TextUtils.isEmpty(token)) {
                return token;
            }
            Log.w("StorageUtil", "no auth token for request");
            return null;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
            sb.append("error getting token ");
            sb.append(valueOf);
            Log.e("StorageUtil", sb.toString());
            return null;
        }
    }

    public static long zzsq(String str) {
        if (str == null) {
            return 0;
        }
        String replaceAll = str.replaceAll("Z$", "-0000");
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault()).parse(replaceAll).getTime();
        } catch (ParseException e) {
            String valueOf = String.valueOf(replaceAll);
            Log.w("StorageUtil", valueOf.length() != 0 ? "unable to parse datetime:".concat(valueOf) : new String("unable to parse datetime:"), e);
            return 0;
        }
    }
}
