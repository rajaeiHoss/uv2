package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zzo;
import com.google.android.gms.auth.api.signin.internal.zzq;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions extends zzbgl implements Api.ApiOptions.Optional, ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = new zze();
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    public static final Scope SCOPE_GAMES = new Scope(Scopes.GAMES);
    public static final Scope SCOPE_GAMES_LITE;
    public static final Scope zzemx = new Scope(Scopes.PROFILE);
    public static final Scope zzemy = new Scope("email");
    public static final Scope zzemz = new Scope("openid");
    private static Comparator<Scope> zzeng = new zzd();
    private int versionCode;
    /* access modifiers changed from: private */
    public Account zzeho;
    /* access modifiers changed from: private */
    public boolean zzela;
    /* access modifiers changed from: private */
    public String zzelb;
    /* access modifiers changed from: private */
    public final ArrayList<Scope> zzena;
    /* access modifiers changed from: private */
    public final boolean zzenb;
    /* access modifiers changed from: private */
    public final boolean zzenc;
    /* access modifiers changed from: private */
    public String zzend;
    /* access modifiers changed from: private */
    public ArrayList<zzo> zzene;
    private Map<Integer, zzo> zzenf;

    public static final class Builder {
        private Account zzeho;
        private boolean zzela;
        private String zzelb;
        private boolean zzenb;
        private boolean zzenc;
        private String zzend;
        private Set<Scope> zzenh = new HashSet();
        private Map<Integer, zzo> zzeni = new HashMap();

        public Builder() {
        }

        public Builder(GoogleSignInOptions googleSignInOptions) {
            zzbq.checkNotNull(googleSignInOptions);
            this.zzenh = new HashSet(googleSignInOptions.zzena);
            this.zzenb = googleSignInOptions.zzenb;
            this.zzenc = googleSignInOptions.zzenc;
            this.zzela = googleSignInOptions.zzela;
            this.zzelb = googleSignInOptions.zzelb;
            this.zzeho = googleSignInOptions.zzeho;
            this.zzend = googleSignInOptions.zzend;
            this.zzeni = GoogleSignInOptions.zzx(googleSignInOptions.zzene);
        }

        private final String zzfc(String str) {
            zzbq.zzgv(str);
            String str2 = this.zzelb;
            zzbq.checkArgument(str2 == null || str2.equals(str), "two different server client ids provided");
            return str;
        }

        public final Builder addExtension(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            if (!this.zzeni.containsKey(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()))) {
                if (googleSignInOptionsExtension.getImpliedScopes() != null) {
                    this.zzenh.addAll(googleSignInOptionsExtension.getImpliedScopes());
                }
                this.zzeni.put(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()), new zzo(googleSignInOptionsExtension));
                return this;
            }
            throw new IllegalStateException("Only one extension per type may be added");
        }

        public final GoogleSignInOptions build() {
            if (this.zzenh.contains(GoogleSignInOptions.SCOPE_GAMES) && this.zzenh.contains(GoogleSignInOptions.SCOPE_GAMES_LITE)) {
                this.zzenh.remove(GoogleSignInOptions.SCOPE_GAMES_LITE);
            }
            if (this.zzela && (this.zzeho == null || !this.zzenh.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList(this.zzenh), this.zzeho, this.zzela, this.zzenb, this.zzenc, this.zzelb, this.zzend, this.zzeni, (zzd) null);
        }

        public final Builder requestEmail() {
            this.zzenh.add(GoogleSignInOptions.zzemy);
            return this;
        }

        public final Builder requestId() {
            this.zzenh.add(GoogleSignInOptions.zzemz);
            return this;
        }

        public final Builder requestIdToken(String str) {
            this.zzela = true;
            this.zzelb = zzfc(str);
            return this;
        }

        public final Builder requestProfile() {
            this.zzenh.add(GoogleSignInOptions.zzemx);
            return this;
        }

        public final Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.zzenh.add(scope);
            this.zzenh.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public final Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public final Builder requestServerAuthCode(String str, boolean z) {
            this.zzenb = true;
            this.zzelb = zzfc(str);
            this.zzenc = z;
            return this;
        }

        public final Builder setAccountName(String str) {
            this.zzeho = new Account(zzbq.zzgv(str), "com.google");
            return this;
        }

        public final Builder setHostedDomain(String str) {
            this.zzend = zzbq.zzgv(str);
            return this;
        }
    }

    static {
        Scope scope = new Scope("https://www.googleapis.com/auth/games_lite");
        SCOPE_GAMES_LITE = scope;
        DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(scope, new Scope[0]).build();
    }

    GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, ArrayList<zzo> arrayList2) {
        this(i, arrayList, account, z, z2, z3, str, str2, zzx(arrayList2));
    }

    private GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map<Integer, zzo> map) {
        this.versionCode = i;
        this.zzena = arrayList;
        this.zzeho = account;
        this.zzela = z;
        this.zzenb = z2;
        this.zzenc = z3;
        this.zzelb = str;
        this.zzend = str2;
        this.zzene = new ArrayList<>(map.values());
        this.zzenf = map;
    }

    /* synthetic */ GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map, zzd zzd) {
        this(3, (ArrayList<Scope>) arrayList, account, z, z2, z3, str, str2, (Map<Integer, zzo>) map);
    }

    private final JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zzena, zzeng);
            ArrayList arrayList = this.zzena;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                jSONArray.put(((Scope) obj).zzaie());
            }
            jSONObject.put("scopes", jSONArray);
            Account account = this.zzeho;
            if (account != null) {
                jSONObject.put("accountName", account.name);
            }
            jSONObject.put("idTokenRequested", this.zzela);
            jSONObject.put("forceCodeForRefreshToken", this.zzenc);
            jSONObject.put("serverAuthRequested", this.zzenb);
            if (!TextUtils.isEmpty(this.zzelb)) {
                jSONObject.put("serverClientId", this.zzelb);
            }
            if (!TextUtils.isEmpty(this.zzend)) {
                jSONObject.put("hostedDomain", this.zzend);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static GoogleSignInOptions zzfb(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString = jSONObject.optString("accountName", (String) null);
        return new GoogleSignInOptions(3, (ArrayList<Scope>) new ArrayList(hashSet), !TextUtils.isEmpty(optString) ? new Account(optString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", (String) null), jSONObject.optString("hostedDomain", (String) null), (Map<Integer, zzo>) new HashMap());
    }

    /* access modifiers changed from: private */
    public static Map<Integer, zzo> zzx(List<zzo> list) {
        HashMap hashMap = new HashMap();
        if (list == null) {
            return hashMap;
        }
        for (zzo next : list) {
            hashMap.put(Integer.valueOf(next.getType()), next);
        }
        return hashMap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
        if (r1.equals(r4.zzeho) != false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005e, code lost:
        if (r3.zzelb.equals(r4.zzelb) != false) goto L_0x0060;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r4 = (com.google.android.gms.auth.api.signin.GoogleSignInOptions) r4     // Catch:{ ClassCastException -> 0x0074 }
            java.util.ArrayList<com.google.android.gms.auth.api.signin.internal.zzo> r1 = r3.zzene     // Catch:{ ClassCastException -> 0x0074 }
            int r1 = r1.size()     // Catch:{ ClassCastException -> 0x0074 }
            if (r1 > 0) goto L_0x0074
            java.util.ArrayList<com.google.android.gms.auth.api.signin.internal.zzo> r1 = r4.zzene     // Catch:{ ClassCastException -> 0x0074 }
            int r1 = r1.size()     // Catch:{ ClassCastException -> 0x0074 }
            if (r1 <= 0) goto L_0x0017
            goto L_0x0074
        L_0x0017:
            java.util.ArrayList<com.google.android.gms.common.api.Scope> r1 = r3.zzena     // Catch:{ ClassCastException -> 0x0074 }
            int r1 = r1.size()     // Catch:{ ClassCastException -> 0x0074 }
            java.util.ArrayList r2 = r4.zzaci()     // Catch:{ ClassCastException -> 0x0074 }
            int r2 = r2.size()     // Catch:{ ClassCastException -> 0x0074 }
            if (r1 != r2) goto L_0x0074
            java.util.ArrayList<com.google.android.gms.common.api.Scope> r1 = r3.zzena     // Catch:{ ClassCastException -> 0x0074 }
            java.util.ArrayList r2 = r4.zzaci()     // Catch:{ ClassCastException -> 0x0074 }
            boolean r1 = r1.containsAll(r2)     // Catch:{ ClassCastException -> 0x0074 }
            if (r1 != 0) goto L_0x0034
            goto L_0x0074
        L_0x0034:
            android.accounts.Account r1 = r3.zzeho     // Catch:{ ClassCastException -> 0x0074 }
            if (r1 != 0) goto L_0x003d
            android.accounts.Account r1 = r4.zzeho     // Catch:{ ClassCastException -> 0x0074 }
            if (r1 != 0) goto L_0x0074
            goto L_0x0045
        L_0x003d:
            android.accounts.Account r2 = r4.zzeho     // Catch:{ ClassCastException -> 0x0074 }
            boolean r1 = r1.equals(r2)     // Catch:{ ClassCastException -> 0x0074 }
            if (r1 == 0) goto L_0x0074
        L_0x0045:
            java.lang.String r1 = r3.zzelb     // Catch:{ ClassCastException -> 0x0074 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ ClassCastException -> 0x0074 }
            if (r1 == 0) goto L_0x0056
            java.lang.String r1 = r4.zzelb     // Catch:{ ClassCastException -> 0x0074 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ ClassCastException -> 0x0074 }
            if (r1 == 0) goto L_0x0074
            goto L_0x0060
        L_0x0056:
            java.lang.String r1 = r3.zzelb     // Catch:{ ClassCastException -> 0x0074 }
            java.lang.String r2 = r4.zzelb     // Catch:{ ClassCastException -> 0x0074 }
            boolean r1 = r1.equals(r2)     // Catch:{ ClassCastException -> 0x0074 }
            if (r1 == 0) goto L_0x0074
        L_0x0060:
            boolean r1 = r3.zzenc     // Catch:{ ClassCastException -> 0x0074 }
            boolean r2 = r4.zzenc     // Catch:{ ClassCastException -> 0x0074 }
            if (r1 != r2) goto L_0x0074
            boolean r1 = r3.zzela     // Catch:{ ClassCastException -> 0x0074 }
            boolean r2 = r4.zzela     // Catch:{ ClassCastException -> 0x0074 }
            if (r1 != r2) goto L_0x0074
            boolean r1 = r3.zzenb     // Catch:{ ClassCastException -> 0x0074 }
            boolean r4 = r4.zzenb     // Catch:{ ClassCastException -> 0x0074 }
            if (r1 != r4) goto L_0x0074
            r4 = 1
            return r4
        L_0x0074:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.GoogleSignInOptions.equals(java.lang.Object):boolean");
    }

    public final Account getAccount() {
        return this.zzeho;
    }

    public Scope[] getScopeArray() {
        ArrayList<Scope> arrayList = this.zzena;
        return (Scope[]) arrayList.toArray(new Scope[arrayList.size()]);
    }

    public final String getServerClientId() {
        return this.zzelb;
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = this.zzena;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            arrayList.add(((Scope) obj).zzaie());
        }
        Collections.sort(arrayList);
        return new zzq().zzs(arrayList).zzs(this.zzeho).zzs(this.zzelb).zzav(this.zzenc).zzav(this.zzela).zzav(this.zzenb).zzacr();
    }

    public final boolean isIdTokenRequested() {
        return this.zzela;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.versionCode);
        zzbgo.zzc(parcel, 2, zzaci(), false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzeho, i, false);
        zzbgo.zza(parcel, 4, this.zzela);
        zzbgo.zza(parcel, 5, this.zzenb);
        zzbgo.zza(parcel, 6, this.zzenc);
        zzbgo.zza(parcel, 7, this.zzelb, false);
        zzbgo.zza(parcel, 8, this.zzend, false);
        zzbgo.zzc(parcel, 9, this.zzene, false);
        zzbgo.zzai(parcel, zze);
    }

    public final ArrayList<Scope> zzaci() {
        return new ArrayList<>(this.zzena);
    }

    public final boolean zzacj() {
        return this.zzenb;
    }

    public final String zzack() {
        return toJsonObject().toString();
    }
}
