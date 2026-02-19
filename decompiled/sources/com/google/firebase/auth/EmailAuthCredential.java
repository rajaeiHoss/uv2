package com.google.firebase.auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbhf;

public class EmailAuthCredential extends AuthCredential {
    public static final Parcelable.Creator<EmailAuthCredential> CREATOR = new zzf();
    private static final zzbhf zzehr = new zzbhf("EmailAuthCredential", new String[0]);
    private String zzekn;
    private String zzemh;
    private final String zzmoy;
    private String zzmoz;
    private boolean zzmpa;

    EmailAuthCredential(String str, String str2) {
        this(str, str2, (String) null, (String) null, false);
    }

    EmailAuthCredential(String str, String str2, String str3, String str4, boolean z) {
        this.zzemh = zzbq.zzgv(str);
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            this.zzekn = str2;
            this.zzmoy = str3;
            this.zzmoz = str4;
            this.zzmpa = z;
            return;
        }
        throw new IllegalArgumentException("Cannot create an EmailAuthCredential without a password or emailLink.");
    }

    public static boolean isSignInWithEmailLink(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (zzpb(str)) {
                return true;
            }
            return zzpb(Uri.parse(str).getQueryParameter("link"));
        } catch (UnsupportedOperationException e) {
            zzbhf zzbhf = zzehr;
            Object[] objArr = new Object[1];
            String valueOf = String.valueOf(e.getMessage());
            objArr[0] = valueOf.length() != 0 ? "isSignInWithEmailLink returned an UnsupportedOperationException: ".concat(valueOf) : new String("isSignInWithEmailLink returned an UnsupportedOperationException: ");
            zzbhf.zza("EmailAuthCredential", objArr);
            return false;
        }
    }

    private static boolean zzpb(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri parse = Uri.parse(str);
        String queryParameter = parse.getQueryParameter("oobCode");
        String queryParameter2 = parse.getQueryParameter("mode");
        return !TextUtils.isEmpty(queryParameter) && !TextUtils.isEmpty(queryParameter2) && queryParameter2.equals("signIn");
    }

    public final String getEmail() {
        return this.zzemh;
    }

    public final String getPassword() {
        return this.zzekn;
    }

    public String getProvider() {
        return "password";
    }

    public String getSignInMethod() {
        return !TextUtils.isEmpty(this.zzekn) ? "password" : EmailAuthProvider.EMAIL_LINK_SIGN_IN_METHOD;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzemh, false);
        zzbgo.zza(parcel, 2, this.zzekn, false);
        zzbgo.zza(parcel, 3, this.zzmoy, false);
        zzbgo.zza(parcel, 4, this.zzmoz, false);
        zzbgo.zza(parcel, 5, this.zzmpa);
        zzbgo.zzai(parcel, zze);
    }

    public final EmailAuthCredential zza(FirebaseUser firebaseUser) {
        this.zzmoz = firebaseUser.zzbtn();
        this.zzmpa = true;
        return this;
    }

    public final boolean zzbth() {
        return !TextUtils.isEmpty(this.zzmoy);
    }
}
