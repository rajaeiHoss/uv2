package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbhf;
import com.google.android.gms.internal.zzdyz;
import com.google.android.gms.internal.zzebw;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzx {
    private Context mContext;
    private SharedPreferences zzbkx = this.mContext.getSharedPreferences(String.format("com.google.firebase.auth.api.Store.%s", new Object[]{this.zzmuf}), 0);
    private zzbhf zzenl = new zzbhf("StorageHelpers", new String[0]);
    private String zzmuf;

    public zzx(Context context, String str) {
        zzbq.checkNotNull(context);
        this.zzmuf = zzbq.zzgv(str);
        this.mContext = context.getApplicationContext();
    }

    private final String zzi(FirebaseUser firebaseUser) {
        JSONObject jSONObject = new JSONObject();
        if (!zzk.class.isAssignableFrom(firebaseUser.getClass())) {
            return null;
        }
        zzk zzk = (zzk) firebaseUser;
        try {
            jSONObject.put("cachedTokenState", zzk.zzbtn());
            jSONObject.put("applicationName", zzk.zzbtl().getName());
            jSONObject.put(AppMeasurement.Param.TYPE, "com.google.firebase.auth.internal.DefaultFirebaseUser");
            if (zzk.zzbum() != null) {
                JSONArray jSONArray = new JSONArray();
                List<zzh> zzbum = zzk.zzbum();
                for (int i = 0; i < zzbum.size(); i++) {
                    jSONArray.put(zzbum.get(i).zzack());
                }
                jSONObject.put("userInfos", jSONArray);
            }
            jSONObject.put("anonymous", zzk.isAnonymous());
            jSONObject.put("version", "2");
            return jSONObject.toString();
        } catch (Exception e) {
            this.zzenl.zza("Failed to turn object into JSON", e, new Object[0]);
            throw new zzdyz(e);
        }
    }

    private final zzk zzz(JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("cachedTokenState");
            String string2 = jSONObject.getString("applicationName");
            boolean z = jSONObject.getBoolean("anonymous");
            String str = "2";
            String string3 = jSONObject.getString("version");
            if (string3 != null) {
                str = string3;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("userInfos");
            int length = jSONArray.length();
            ArrayList arrayList = new ArrayList(length);
            for (int i = 0; i < length; i++) {
                arrayList.add(zzh.zzph(jSONArray.getString(i)));
            }
            zzk zzk = new zzk(FirebaseApp.getInstance(string2), arrayList);
            if (!TextUtils.isEmpty(string)) {
                zzk.zza(zzebw.zzpg(string));
            }
            ((zzk) zzk.zzck(z)).zzpi(str);
            return zzk;
        } catch (zzdyz | ArrayIndexOutOfBoundsException | IllegalArgumentException | JSONException e) {
            this.zzenl.zzf(e);
            return null;
        }
    }

    public final void clear(String str) {
        this.zzbkx.edit().remove(str).apply();
    }

    public final void zza(FirebaseUser firebaseUser, zzebw zzebw) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(zzebw);
        this.zzbkx.edit().putString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}), zzebw.zzack()).apply();
    }

    public final FirebaseUser zzbuq() {
        String string = this.zzbkx.getString("com.google.firebase.auth.FIREBASE_USER", (String) null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has(AppMeasurement.Param.TYPE) && "com.google.firebase.auth.internal.DefaultFirebaseUser".equalsIgnoreCase(jSONObject.optString(AppMeasurement.Param.TYPE))) {
                return zzz(jSONObject);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public final void zzg(FirebaseUser firebaseUser) {
        zzbq.checkNotNull(firebaseUser);
        String zzi = zzi(firebaseUser);
        if (!TextUtils.isEmpty(zzi)) {
            this.zzbkx.edit().putString("com.google.firebase.auth.FIREBASE_USER", zzi).apply();
        }
    }

    public final zzebw zzh(FirebaseUser firebaseUser) {
        zzbq.checkNotNull(firebaseUser);
        String string = this.zzbkx.getString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}), (String) null);
        if (string != null) {
            return zzebw.zzpg(string);
        }
        return null;
    }
}
