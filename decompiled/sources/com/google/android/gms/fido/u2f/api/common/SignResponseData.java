package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public class SignResponseData extends ResponseData {
    public static final Parcelable.Creator<SignResponseData> CREATOR = new zzn();
    public static final String JSON_RESPONSE_DATA_CLIENT_DATA = "clientData";
    public static final String JSON_RESPONSE_DATA_KEY_HANDLE = "keyHandle";
    public static final String JSON_RESPONSE_DATA_SIGNATURE_DATA = "signatureData";
    private final byte[] zzhek;
    private final String zzhgt;
    private final byte[] zzhgx;

    public SignResponseData(byte[] bArr, String str, byte[] bArr2) {
        this.zzhek = (byte[]) zzbq.checkNotNull(bArr);
        this.zzhgt = (String) zzbq.checkNotNull(str);
        this.zzhgx = (byte[]) zzbq.checkNotNull(bArr2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            SignResponseData signResponseData = (SignResponseData) obj;
            return zzbg.equal(this.zzhgt, signResponseData.zzhgt) && Arrays.equals(this.zzhek, signResponseData.zzhek) && Arrays.equals(this.zzhgx, signResponseData.zzhgx);
        }
        return false;
    }

    public String getClientDataString() {
        return this.zzhgt;
    }

    public byte[] getKeyHandle() {
        return this.zzhek;
    }

    public byte[] getSignatureData() {
        return this.zzhgx;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhgt, Integer.valueOf(Arrays.hashCode(this.zzhek)), Integer.valueOf(Arrays.hashCode(this.zzhgx))});
    }

    public JSONObject toJsonObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_RESPONSE_DATA_KEY_HANDLE, Base64.encodeToString(this.zzhek, 11));
            jSONObject.put(JSON_RESPONSE_DATA_CLIENT_DATA, Base64.encodeToString(this.zzhgt.getBytes(), 11));
            jSONObject.put(JSON_RESPONSE_DATA_SIGNATURE_DATA, Base64.encodeToString(this.zzhgx, 11));
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getKeyHandle(), false);
        zzbgo.zza(parcel, 3, getClientDataString(), false);
        zzbgo.zza(parcel, 4, getSignatureData(), false);
        zzbgo.zzai(parcel, zze);
    }
}
