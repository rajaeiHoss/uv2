package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SignRequestParams extends RequestParams {
    public static final Parcelable.Creator<SignRequestParams> CREATOR = new zzm();
    public static final int MAX_DISPLAY_HINT_LENGTH = 80;
    private final Integer zzgtd;
    private final Double zzhfe;
    private final Uri zzhgm;
    private final List<RegisteredKey> zzhgo;
    private final ChannelIdValue zzhgp;
    private final String zzhgq;
    private final Set<Uri> zzhgr;
    private final byte[] zzhgw;

    public static final class Builder {
        private Integer zzgtd;
        private Double zzhfe;
        private Uri zzhgm;
        private List<RegisteredKey> zzhgo;
        private ChannelIdValue zzhgp;
        private String zzhgq;
        private byte[] zzhgw;

        public final SignRequestParams build() {
            return new SignRequestParams(this.zzgtd, this.zzhfe, this.zzhgm, this.zzhgw, this.zzhgo, this.zzhgp, this.zzhgq);
        }

        public final Builder setAppId(Uri uri) {
            this.zzhgm = uri;
            return this;
        }

        public final Builder setChannelIdValue(ChannelIdValue channelIdValue) {
            this.zzhgp = channelIdValue;
            return this;
        }

        public final Builder setDefaultSignChallenge(byte[] bArr) {
            this.zzhgw = bArr;
            return this;
        }

        public final Builder setDisplayHint(String str) {
            this.zzhgq = str;
            return this;
        }

        public final Builder setRegisteredKeys(List<RegisteredKey> list) {
            this.zzhgo = list;
            return this;
        }

        public final Builder setRequestId(Integer num) {
            this.zzgtd = num;
            return this;
        }

        public final Builder setTimeoutSeconds(Double d) {
            this.zzhfe = d;
            return this;
        }
    }

    SignRequestParams(Integer num, Double d, Uri uri, byte[] bArr, List<RegisteredKey> list, ChannelIdValue channelIdValue, String str) {
        this.zzgtd = num;
        this.zzhfe = d;
        this.zzhgm = uri;
        this.zzhgw = bArr;
        boolean z = true;
        zzbq.checkArgument(list != null && !list.isEmpty(), "registeredKeys must not be null or empty");
        this.zzhgo = list;
        this.zzhgp = channelIdValue;
        HashSet hashSet = new HashSet();
        if (uri != null) {
            hashSet.add(uri);
        }
        for (RegisteredKey next : list) {
            zzbq.checkArgument((next.getAppId() == null && uri == null) ? false : true, "registered key has null appId and no request appId is provided");
            zzbq.checkArgument((next.getChallengeValue() == null && list == null) ? false : true, "register request has null challenge and no default challenge isprovided");
            if (next.getAppId() != null) {
                hashSet.add(Uri.parse(next.getAppId()));
            }
        }
        this.zzhgr = hashSet;
        if (str != null && str.length() > 80) {
            z = false;
        }
        zzbq.checkArgument(z, "Display Hint cannot be longer than 80 characters");
        this.zzhgq = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            SignRequestParams signRequestParams = (SignRequestParams) obj;
            return zzbg.equal(this.zzgtd, signRequestParams.zzgtd) && zzbg.equal(this.zzhfe, signRequestParams.zzhfe) && zzbg.equal(this.zzhgm, signRequestParams.zzhgm) && Arrays.equals(this.zzhgw, signRequestParams.zzhgw) && this.zzhgo.containsAll(signRequestParams.zzhgo) && signRequestParams.zzhgo.containsAll(this.zzhgo) && zzbg.equal(this.zzhgp, signRequestParams.zzhgp) && zzbg.equal(this.zzhgq, signRequestParams.zzhgq);
        }
        return false;
    }

    public Set<Uri> getAllAppIds() {
        return this.zzhgr;
    }

    public Uri getAppId() {
        return this.zzhgm;
    }

    public ChannelIdValue getChannelIdValue() {
        return this.zzhgp;
    }

    public byte[] getDefaultSignChallenge() {
        return this.zzhgw;
    }

    public String getDisplayHint() {
        return this.zzhgq;
    }

    public List<RegisteredKey> getRegisteredKeys() {
        return this.zzhgo;
    }

    public Integer getRequestId() {
        return this.zzgtd;
    }

    public Double getTimeoutSeconds() {
        return this.zzhfe;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzgtd, this.zzhgm, this.zzhfe, this.zzhgo, this.zzhgp, this.zzhgq, Integer.valueOf(Arrays.hashCode(this.zzhgw))});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getRequestId(), false);
        zzbgo.zza(parcel, 3, getTimeoutSeconds(), false);
        zzbgo.zza(parcel, 4, (Parcelable) getAppId(), i, false);
        zzbgo.zza(parcel, 5, getDefaultSignChallenge(), false);
        zzbgo.zzc(parcel, 6, getRegisteredKeys(), false);
        zzbgo.zza(parcel, 7, (Parcelable) getChannelIdValue(), i, false);
        zzbgo.zza(parcel, 8, getDisplayHint(), false);
        zzbgo.zzai(parcel, zze);
    }
}
