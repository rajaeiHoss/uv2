package com.google.android.gms.fido.u2f.api.common;

import com.google.android.gms.common.internal.zzbq;
import org.json.JSONException;
import org.json.JSONObject;

public class ClientData {
    public static final String KEY_CHALLENGE = "challenge";
    public static final String KEY_CID_PUBKEY = "cid_pubkey";
    public static final String KEY_ORIGIN = "origin";
    public static final String KEY_TYPE = "typ";
    public static final String TYPE_FINISH_ENROLLMENT = "navigator.id.finishEnrollment";
    public static final String TYPE_GET_ASSERTION = "navigator.id.getAssertion";
    private final String mOrigin;
    private final String zzczr;
    private final String zzhge;
    private final ChannelIdValue zzhgf;

    public static class Builder implements Cloneable {
        private String mOrigin;
        private String zzczr;
        private String zzhge;
        private ChannelIdValue zzhgf;

        Builder() {
            this.zzhgf = ChannelIdValue.ABSENT;
        }

        private Builder(String str, String str2, String str3, ChannelIdValue channelIdValue) {
            this.zzczr = str;
            this.zzhge = str2;
            this.mOrigin = str3;
            this.zzhgf = channelIdValue;
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public ClientData build() {
            return new ClientData(this.zzczr, this.zzhge, this.mOrigin, this.zzhgf);
        }

        public Builder clone() {
            return new Builder(this.zzczr, this.zzhge, this.mOrigin, this.zzhgf);
        }

        public Builder setChallenge(String str) {
            this.zzhge = str;
            return this;
        }

        public Builder setChannelId(ChannelIdValue channelIdValue) {
            this.zzhgf = channelIdValue;
            return this;
        }

        public Builder setOrigin(String str) {
            this.mOrigin = str;
            return this;
        }

        public Builder setType(String str) {
            this.zzczr = str;
            return this;
        }
    }

    ClientData(String str, String str2, String str3, ChannelIdValue channelIdValue) {
        this.zzczr = (String) zzbq.checkNotNull(str);
        this.zzhge = (String) zzbq.checkNotNull(str2);
        this.mOrigin = (String) zzbq.checkNotNull(str3);
        this.zzhgf = (ChannelIdValue) zzbq.checkNotNull(channelIdValue);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ClientData)) {
            return false;
        }
        ClientData clientData = (ClientData) obj;
        return this.zzczr.equals(clientData.zzczr) && this.zzhge.equals(clientData.zzhge) && this.mOrigin.equals(clientData.mOrigin) && this.zzhgf.equals(clientData.zzhgf);
    }

    public int hashCode() {
        return ((((((this.zzczr.hashCode() + 31) * 31) + this.zzhge.hashCode()) * 31) + this.mOrigin.hashCode()) * 31) + this.zzhgf.hashCode();
    }

    public String toJsonString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_TYPE, this.zzczr);
            jSONObject.put(KEY_CHALLENGE, this.zzhge);
            jSONObject.put("origin", this.mOrigin);
            int i = zzd.zzhgc[this.zzhgf.getType().ordinal()];
            if (i != 1) {
                Object stringValue = null;
                if (i == 2) {
                    stringValue = this.zzhgf.getStringValue();
                } else if (i == 3) {
                    stringValue = this.zzhgf.getObjectValue();
                }
                if (stringValue != null) {
                    jSONObject.put(KEY_CID_PUBKEY, stringValue);
                }
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
