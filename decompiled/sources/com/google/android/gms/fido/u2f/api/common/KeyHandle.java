package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class KeyHandle extends zzbgl {
    public static final Parcelable.Creator<KeyHandle> CREATOR = new zzg();
    private final List<Transport> zzdvg;
    private final int zzehz;
    private final byte[] zzhgi;
    private final ProtocolVersion zzhgj;

    KeyHandle(int i, byte[] bArr, String str, List<Transport> list) {
        this.zzehz = i;
        this.zzhgi = bArr;
        try {
            this.zzhgj = ProtocolVersion.fromString(str);
            this.zzdvg = list;
        } catch (ProtocolVersion.UnsupportedProtocolException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public KeyHandle(byte[] bArr, ProtocolVersion protocolVersion, List<Transport> list) {
        this.zzehz = 1;
        this.zzhgi = bArr;
        this.zzhgj = protocolVersion;
        this.zzdvg = list;
    }

    public static KeyHandle parseFromJson(JSONObject jSONObject) throws JSONException {
        List<Transport> list = null;
        try {
            ProtocolVersion fromString = ProtocolVersion.fromString(jSONObject.has("version") ? jSONObject.getString("version") : null);
            try {
                byte[] decode = Base64.decode(jSONObject.getString(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE), 8);
                if (jSONObject.has("transports")) {
                    list = Transport.parseTransports(jSONObject.getJSONArray("transports"));
                }
                return new KeyHandle(decode, fromString, list);
            } catch (IllegalArgumentException e) {
                throw new JSONException(e.toString());
            }
        } catch (ProtocolVersion.UnsupportedProtocolException e2) {
            throw new JSONException(e2.toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        r3 = r5.zzdvg;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            if (r5 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.Class r2 = r4.getClass()
            java.lang.Class r3 = r5.getClass()
            if (r2 == r3) goto L_0x0013
            return r1
        L_0x0013:
            com.google.android.gms.fido.u2f.api.common.KeyHandle r5 = (com.google.android.gms.fido.u2f.api.common.KeyHandle) r5
            byte[] r2 = r4.zzhgi
            byte[] r3 = r5.zzhgi
            boolean r2 = java.util.Arrays.equals(r2, r3)
            if (r2 != 0) goto L_0x0020
            return r1
        L_0x0020:
            com.google.android.gms.fido.u2f.api.common.ProtocolVersion r2 = r4.zzhgj
            com.google.android.gms.fido.u2f.api.common.ProtocolVersion r3 = r5.zzhgj
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x002b
            return r1
        L_0x002b:
            java.util.List<com.google.android.gms.fido.common.Transport> r2 = r4.zzdvg
            if (r2 != 0) goto L_0x0034
            java.util.List<com.google.android.gms.fido.common.Transport> r3 = r5.zzdvg
            if (r3 != 0) goto L_0x0034
            return r0
        L_0x0034:
            if (r2 == 0) goto L_0x004c
            java.util.List<com.google.android.gms.fido.common.Transport> r3 = r5.zzdvg
            if (r3 != 0) goto L_0x003b
            goto L_0x004c
        L_0x003b:
            boolean r2 = r2.containsAll(r3)
            if (r2 == 0) goto L_0x004c
            java.util.List<com.google.android.gms.fido.common.Transport> r5 = r5.zzdvg
            java.util.List<com.google.android.gms.fido.common.Transport> r2 = r4.zzdvg
            boolean r5 = r5.containsAll(r2)
            if (r5 == 0) goto L_0x004c
            return r0
        L_0x004c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.u2f.api.common.KeyHandle.equals(java.lang.Object):boolean");
    }

    public byte[] getBytes() {
        return this.zzhgi;
    }

    public ProtocolVersion getProtocolVersion() {
        return this.zzhgj;
    }

    public List<Transport> getTransports() {
        return this.zzdvg;
    }

    public int getVersionCode() {
        return this.zzehz;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(Arrays.hashCode(this.zzhgi)), this.zzhgj, this.zzdvg});
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            byte[] bArr = this.zzhgi;
            if (bArr != null) {
                jSONObject.put(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE, Base64.encodeToString(bArr, 11));
            }
            ProtocolVersion protocolVersion = this.zzhgj;
            if (protocolVersion != null) {
                jSONObject.put("version", protocolVersion.toString());
            }
            if (this.zzdvg != null) {
                JSONArray jSONArray = new JSONArray();
                for (Transport transport : this.zzdvg) {
                    jSONArray.put(transport.toString());
                }
                jSONObject.put("transports", jSONArray);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        List<Transport> list = this.zzdvg;
        return String.format("{keyHandle: %s, version: %s, transports: %s}", new Object[]{zzc.zzj(this.zzhgi), this.zzhgj, list == null ? "null" : list.toString()});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, getVersionCode());
        zzbgo.zza(parcel, 2, getBytes(), false);
        zzbgo.zza(parcel, 3, this.zzhgj.toString(), false);
        zzbgo.zzc(parcel, 4, getTransports(), false);
        zzbgo.zzai(parcel, zze);
    }
}
