package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterRequest extends zzbgl {
    public static final Parcelable.Creator<RegisterRequest> CREATOR = new zzi();
    public static final int U2F_V1_CHALLENGE_BYTE_LENGTH = 65;
    private final String mAppId;
    private final int zzehz;
    private final ProtocolVersion zzhgj;
    private final byte[] zzhgl;

    RegisterRequest(int i, String str, byte[] bArr, String str2) {
        this.zzehz = i;
        try {
            this.zzhgj = ProtocolVersion.fromString(str);
            this.zzhgl = bArr;
            this.mAppId = str2;
        } catch (ProtocolVersion.UnsupportedProtocolException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RegisterRequest(ProtocolVersion protocolVersion, byte[] bArr, String str) {
        boolean z = true;
        this.zzehz = 1;
        this.zzhgj = (ProtocolVersion) zzbq.checkNotNull(protocolVersion);
        this.zzhgl = (byte[]) zzbq.checkNotNull(bArr);
        if (protocolVersion == ProtocolVersion.V1) {
            zzbq.checkArgument(bArr.length != 65 ? false : z, "invalid challengeValue length for V1");
        }
        this.mAppId = str;
    }

    public static RegisterRequest parseFromJson(JSONObject jSONObject) throws JSONException {
        String str = null;
        try {
            ProtocolVersion fromString = ProtocolVersion.fromString(jSONObject.has("version") ? jSONObject.getString("version") : null);
            try {
                byte[] decode = Base64.decode(jSONObject.getString(ClientData.KEY_CHALLENGE), 8);
                if (jSONObject.has("appId")) {
                    str = jSONObject.getString("appId");
                }
                try {
                    return new RegisterRequest(fromString, decode, str);
                } catch (IllegalArgumentException e) {
                    throw new JSONException(e.getMessage());
                }
            } catch (IllegalArgumentException e2) {
                throw new JSONException(e2.toString());
            }
        } catch (ProtocolVersion.UnsupportedProtocolException e3) {
            throw new JSONException(e3.toString());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RegisterRequest registerRequest = (RegisterRequest) obj;
        if (!Arrays.equals(this.zzhgl, registerRequest.zzhgl) || this.zzhgj != registerRequest.zzhgj) {
            return false;
        }
        String str = this.mAppId;
        String str2 = registerRequest.mAppId;
        if (str == null) {
            if (str2 != null) {
                return false;
            }
        } else if (!str.equals(str2)) {
            return false;
        }
        return true;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public byte[] getChallengeValue() {
        return this.zzhgl;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.zzhgj;
    }

    public int getVersionCode() {
        return this.zzehz;
    }

    public int hashCode() {
        int hashCode = (((Arrays.hashCode(this.zzhgl) + 31) * 31) + this.zzhgj.hashCode()) * 31;
        String str = this.mAppId;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", this.zzhgj.toString());
            jSONObject.put(ClientData.KEY_CHALLENGE, Base64.encodeToString(this.zzhgl, 11));
            String str = this.mAppId;
            if (str != null) {
                jSONObject.put("appId", str);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getVersionCode());
        zzbgo.zza(parcel, 2, this.zzhgj.toString(), false);
        zzbgo.zza(parcel, 3, getChallengeValue(), false);
        zzbgo.zza(parcel, 4, getAppId(), false);
        zzbgo.zzai(parcel, zze);
    }
}
