package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zzb();
    private static zze zzemg = zzi.zzanq();
    private int versionCode;
    private String zzbzd;
    private List<Scope> zzeie;
    private String zzekq;
    private String zzekr;
    private String zzelh;
    private String zzemh;
    private String zzemi;
    private Uri zzemj;
    private String zzemk;
    private long zzeml;
    private String zzemm;
    private Set<Scope> zzemn = new HashSet();

    GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List<Scope> list, String str7, String str8) {
        this.versionCode = i;
        this.zzbzd = str;
        this.zzelh = str2;
        this.zzemh = str3;
        this.zzemi = str4;
        this.zzemj = uri;
        this.zzemk = str5;
        this.zzeml = j;
        this.zzemm = str6;
        this.zzeie = list;
        this.zzekq = str7;
        this.zzekr = str8;
    }

    private final JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (getId() != null) {
                jSONObject.put("id", getId());
            }
            if (getIdToken() != null) {
                jSONObject.put("tokenId", getIdToken());
            }
            if (getEmail() != null) {
                jSONObject.put("email", getEmail());
            }
            if (getDisplayName() != null) {
                jSONObject.put("displayName", getDisplayName());
            }
            if (getGivenName() != null) {
                jSONObject.put("givenName", getGivenName());
            }
            if (getFamilyName() != null) {
                jSONObject.put("familyName", getFamilyName());
            }
            if (getPhotoUrl() != null) {
                jSONObject.put("photoUrl", getPhotoUrl().toString());
            }
            if (getServerAuthCode() != null) {
                jSONObject.put("serverAuthCode", getServerAuthCode());
            }
            jSONObject.put("expirationTime", this.zzeml);
            jSONObject.put("obfuscatedIdentifier", this.zzemm);
            JSONArray jSONArray = new JSONArray();
            List<Scope> list = this.zzeie;
            Scope[] scopeArr = (Scope[]) list.toArray(new Scope[list.size()]);
            Arrays.sort(scopeArr, zza.zzemo);
            for (Scope zzaie : scopeArr) {
                jSONArray.put(zzaie.zzaie());
            }
            jSONObject.put("grantedScopes", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static GoogleSignInAccount zza(String str, String str2, String str3, String str4, String str5, String str6, Uri uri, Long l, String str7, Set<Scope> set) {
        return new GoogleSignInAccount(3, str, str2, str3, str4, uri, (String) null, (l == null ? Long.valueOf(zzemg.currentTimeMillis() / 1000) : l).longValue(), zzbq.zzgv(str7), new ArrayList((Collection) zzbq.checkNotNull(set)), str5, str6);
    }

    public static GoogleSignInAccount zzacd() {
        Account account = new Account("<<default account>>", "com.google");
        return zza((String) null, (String) null, account.name, (String) null, (String) null, (String) null, (Uri) null, 0L, account.name, new HashSet());
    }

    public static GoogleSignInAccount zzfa(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("photoUrl", (String) null);
        Uri parse = !TextUtils.isEmpty(optString) ? Uri.parse(optString) : null;
        long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        GoogleSignInAccount zza = zza(jSONObject.optString("id"), jSONObject.optString("tokenId", (String) null), jSONObject.optString("email", (String) null), jSONObject.optString("displayName", (String) null), jSONObject.optString("givenName", (String) null), jSONObject.optString("familyName", (String) null), parse, Long.valueOf(parseLong), jSONObject.getString("obfuscatedIdentifier"), hashSet);
        zza.zzemk = jSONObject.optString("serverAuthCode", (String) null);
        return zza;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        return googleSignInAccount.zzemm.equals(this.zzemm) && googleSignInAccount.zzacf().equals(zzacf());
    }

    public Account getAccount() {
        if (this.zzemh == null) {
            return null;
        }
        return new Account(this.zzemh, "com.google");
    }

    public String getDisplayName() {
        return this.zzemi;
    }

    public String getEmail() {
        return this.zzemh;
    }

    public String getFamilyName() {
        return this.zzekr;
    }

    public String getGivenName() {
        return this.zzekq;
    }

    public Set<Scope> getGrantedScopes() {
        return new HashSet(this.zzeie);
    }

    public String getId() {
        return this.zzbzd;
    }

    public String getIdToken() {
        return this.zzelh;
    }

    public Uri getPhotoUrl() {
        return this.zzemj;
    }

    public String getServerAuthCode() {
        return this.zzemk;
    }

    public int hashCode() {
        return ((this.zzemm.hashCode() + 527) * 31) + zzacf().hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.versionCode);
        zzbgo.zza(parcel, 2, getId(), false);
        zzbgo.zza(parcel, 3, getIdToken(), false);
        zzbgo.zza(parcel, 4, getEmail(), false);
        zzbgo.zza(parcel, 5, getDisplayName(), false);
        zzbgo.zza(parcel, 6, (Parcelable) getPhotoUrl(), i, false);
        zzbgo.zza(parcel, 7, getServerAuthCode(), false);
        zzbgo.zza(parcel, 8, this.zzeml);
        zzbgo.zza(parcel, 9, this.zzemm, false);
        zzbgo.zzc(parcel, 10, this.zzeie, false);
        zzbgo.zza(parcel, 11, getGivenName(), false);
        zzbgo.zza(parcel, 12, getFamilyName(), false);
        zzbgo.zzai(parcel, zze);
    }

    public final GoogleSignInAccount zza(Scope... scopeArr) {
        if (scopeArr != null) {
            Collections.addAll(this.zzemn, scopeArr);
        }
        return this;
    }

    public final boolean zza() {
        return zzemg.currentTimeMillis() / 1000 >= this.zzeml - 300;
    }

    public final String zzace() {
        return this.zzemm;
    }

    public final Set<Scope> zzacf() {
        HashSet hashSet = new HashSet(this.zzeie);
        hashSet.addAll(this.zzemn);
        return hashSet;
    }

    public final String zzacg() {
        JSONObject jsonObject = toJsonObject();
        jsonObject.remove("serverAuthCode");
        return jsonObject.toString();
    }
}
