package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisteredKey extends zzbgl {
    public static final Parcelable.Creator<RegisteredKey> CREATOR = new zzl();
    private final String mAppId;
    private final KeyHandle zzhgu;
    private String zzhgv;

    public RegisteredKey(KeyHandle keyHandle) {
        this(keyHandle, (String) null, (String) null);
    }

    public RegisteredKey(KeyHandle keyHandle, String str, String str2) {
        this.zzhgu = (KeyHandle) zzbq.checkNotNull(keyHandle);
        this.zzhgv = str;
        this.mAppId = str2;
    }

    public static RegisteredKey parseFromJson(JSONObject jSONObject) throws JSONException {
        String str = null;
        String string = jSONObject.has(ClientData.KEY_CHALLENGE) ? jSONObject.getString(ClientData.KEY_CHALLENGE) : null;
        KeyHandle parseFromJson = KeyHandle.parseFromJson(jSONObject);
        if (jSONObject.has("appId")) {
            str = jSONObject.getString("appId");
        }
        return new RegisteredKey(parseFromJson, string, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RegisteredKey registeredKey = (RegisteredKey) obj;
        String str = this.zzhgv;
        if (str == null) {
            if (registeredKey.zzhgv != null) {
                return false;
            }
        } else if (!str.equals(registeredKey.zzhgv)) {
            return false;
        }
        if (!this.zzhgu.equals(registeredKey.zzhgu)) {
            return false;
        }
        String str2 = this.mAppId;
        String str3 = registeredKey.mAppId;
        if (str2 == null) {
            if (str3 != null) {
                return false;
            }
        } else if (!str2.equals(str3)) {
            return false;
        }
        return true;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public String getChallengeValue() {
        return this.zzhgv;
    }

    public KeyHandle getKeyHandle() {
        return this.zzhgu;
    }

    public int hashCode() {
        String str = this.zzhgv;
        int i = 0;
        int hashCode = ((((str == null ? 0 : str.hashCode()) + 31) * 31) + this.zzhgu.hashCode()) * 31;
        String str2 = this.mAppId;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            String str = this.zzhgv;
            if (str != null) {
                jSONObject.put(ClientData.KEY_CHALLENGE, str);
            }
            JSONObject json = this.zzhgu.toJson();
            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject.put(next, json.get(next));
            }
            String str2 = this.mAppId;
            if (str2 != null) {
                jSONObject.put("appId", str2);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE, Base64.encodeToString(this.zzhgu.getBytes(), 11));
            if (this.zzhgu.getProtocolVersion() != ProtocolVersion.UNKNOWN) {
                jSONObject.put("version", this.zzhgu.getProtocolVersion().toString());
            }
            if (this.zzhgu.getTransports() != null) {
                jSONObject.put("transports", this.zzhgu.getTransports().toString());
            }
            String str = this.zzhgv;
            if (str != null) {
                jSONObject.put(ClientData.KEY_CHALLENGE, str);
            }
            String str2 = this.mAppId;
            if (str2 != null) {
                jSONObject.put("appId", str2);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) getKeyHandle(), i, false);
        zzbgo.zza(parcel, 3, getChallengeValue(), false);
        zzbgo.zza(parcel, 4, getAppId(), false);
        zzbgo.zzai(parcel, zze);
    }
}
