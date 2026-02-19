package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterResponseData extends ResponseData {
    public static final Parcelable.Creator<RegisterResponseData> CREATOR = new zzk();
    private final int zzehz;
    private final ProtocolVersion zzhgj;
    private final byte[] zzhgs;
    private final String zzhgt;

    RegisterResponseData(int i, byte[] bArr, String str, String str2) {
        this.zzehz = i;
        this.zzhgs = bArr;
        try {
            this.zzhgj = ProtocolVersion.fromString(str);
            this.zzhgt = str2;
        } catch (ProtocolVersion.UnsupportedProtocolException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public RegisterResponseData(byte[] bArr) {
        this.zzehz = 1;
        this.zzhgs = (byte[]) zzbq.checkNotNull(bArr);
        this.zzhgj = ProtocolVersion.V1;
        this.zzhgt = null;
    }

    public RegisterResponseData(byte[] bArr, ProtocolVersion protocolVersion, String str) {
        boolean z = true;
        this.zzehz = 1;
        this.zzhgs = (byte[]) zzbq.checkNotNull(bArr);
        this.zzhgj = (ProtocolVersion) zzbq.checkNotNull(protocolVersion);
        zzbq.checkState(protocolVersion != ProtocolVersion.UNKNOWN);
        zzbq.checkState(protocolVersion == ProtocolVersion.V1 ? false : z);
        this.zzhgt = (String) zzbq.checkNotNull(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            RegisterResponseData registerResponseData = (RegisterResponseData) obj;
            return zzbg.equal(this.zzhgt, registerResponseData.zzhgt) && zzbg.equal(this.zzhgj, registerResponseData.zzhgj) && Arrays.equals(this.zzhgs, registerResponseData.zzhgs);
        }
        return false;
    }

    public String getClientDataString() {
        return this.zzhgt;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.zzhgj;
    }

    public byte[] getRegisterData() {
        return this.zzhgs;
    }

    public int getVersionCode() {
        return this.zzehz;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhgt, this.zzhgj, Integer.valueOf(Arrays.hashCode(this.zzhgs))});
    }

    public JSONObject toJsonObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("registrationData", Base64.encodeToString(this.zzhgs, 11));
            jSONObject.put("version", this.zzhgj.toString());
            String str = this.zzhgt;
            if (str != null) {
                jSONObject.put(SignResponseData.JSON_RESPONSE_DATA_CLIENT_DATA, Base64.encodeToString(str.getBytes(), 11));
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getVersionCode());
        zzbgo.zza(parcel, 2, getRegisterData(), false);
        zzbgo.zza(parcel, 3, this.zzhgj.toString(), false);
        zzbgo.zza(parcel, 4, getClientDataString(), false);
        zzbgo.zzai(parcel, zze);
    }
}
