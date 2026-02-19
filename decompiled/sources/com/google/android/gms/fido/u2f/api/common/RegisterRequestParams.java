package com.google.android.gms.fido.u2f.api.common;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegisterRequestParams extends RequestParams {
    public static final Parcelable.Creator<RegisterRequestParams> CREATOR = new zzj();
    public static final int MAX_DISPLAY_HINT_LENGTH = 80;
    private final Integer zzgtd;
    private final Double zzhfe;
    private final Uri zzhgm;
    private final List<RegisterRequest> zzhgn;
    private final List<RegisteredKey> zzhgo;
    private final ChannelIdValue zzhgp;
    private final String zzhgq;
    private Set<Uri> zzhgr;

    public static final class Builder {
        private Integer zzgtd;
        private Double zzhfe;
        private Uri zzhgm;
        private List<RegisterRequest> zzhgn;
        private List<RegisteredKey> zzhgo;
        private ChannelIdValue zzhgp;
        private String zzhgq;

        public final RegisterRequestParams build() {
            return new RegisterRequestParams(this.zzgtd, this.zzhfe, this.zzhgm, this.zzhgn, this.zzhgo, this.zzhgp, this.zzhgq);
        }

        public final Builder setAppId(Uri uri) {
            this.zzhgm = uri;
            return this;
        }

        public final Builder setChannelIdValue(ChannelIdValue channelIdValue) {
            this.zzhgp = channelIdValue;
            return this;
        }

        public final Builder setDisplayHint(String str) {
            this.zzhgq = str;
            return this;
        }

        public final Builder setRegisterRequests(List<RegisterRequest> list) {
            this.zzhgn = list;
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

    RegisterRequestParams(Integer num, Double d, Uri uri, List<RegisterRequest> list, List<RegisteredKey> list2, ChannelIdValue channelIdValue, String str) {
        this.zzgtd = num;
        this.zzhfe = d;
        this.zzhgm = uri;
        boolean z = true;
        zzbq.checkArgument(list != null && !list.isEmpty(), "empty list of register requests is provided");
        this.zzhgn = list;
        this.zzhgo = list2;
        this.zzhgp = channelIdValue;
        HashSet hashSet = new HashSet();
        if (uri != null) {
            hashSet.add(uri);
        }
        for (RegisterRequest next : list) {
            zzbq.checkArgument((uri == null && next.getAppId() == null) ? false : true, "register request has null appId and no request appId is provided");
            if (next.getAppId() != null) {
                hashSet.add(Uri.parse(next.getAppId()));
            }
        }
        for (RegisteredKey next2 : list2) {
            zzbq.checkArgument((uri == null && next2.getAppId() == null) ? false : true, "registered key has null appId and no request appId is provided");
            if (next2.getAppId() != null) {
                hashSet.add(Uri.parse(next2.getAppId()));
            }
        }
        this.zzhgr = hashSet;
        if (str != null && str.length() > 80) {
            z = false;
        }
        zzbq.checkArgument(z, "Display Hint cannot be longer than 80 characters");
        this.zzhgq = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        r2 = r4.zzhgo;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        r3 = r5.zzhgo;
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
            if (r5 == 0) goto L_0x006f
            java.lang.Class r2 = r4.getClass()
            java.lang.Class r3 = r5.getClass()
            if (r2 == r3) goto L_0x0012
            goto L_0x006f
        L_0x0012:
            com.google.android.gms.fido.u2f.api.common.RegisterRequestParams r5 = (com.google.android.gms.fido.u2f.api.common.RegisterRequestParams) r5
            java.lang.Integer r2 = r4.zzgtd
            java.lang.Integer r3 = r5.zzgtd
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x006f
            java.lang.Double r2 = r4.zzhfe
            java.lang.Double r3 = r5.zzhfe
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x006f
            android.net.Uri r2 = r4.zzhgm
            android.net.Uri r3 = r5.zzhgm
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x006f
            java.util.List<com.google.android.gms.fido.u2f.api.common.RegisterRequest> r2 = r4.zzhgn
            java.util.List<com.google.android.gms.fido.u2f.api.common.RegisterRequest> r3 = r5.zzhgn
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x006f
            java.util.List<com.google.android.gms.fido.u2f.api.common.RegisteredKey> r2 = r4.zzhgo
            if (r2 != 0) goto L_0x0044
            java.util.List<com.google.android.gms.fido.u2f.api.common.RegisteredKey> r3 = r5.zzhgo
            if (r3 == 0) goto L_0x005a
        L_0x0044:
            if (r2 == 0) goto L_0x006f
            java.util.List<com.google.android.gms.fido.u2f.api.common.RegisteredKey> r3 = r5.zzhgo
            if (r3 == 0) goto L_0x006f
            boolean r2 = r2.containsAll(r3)
            if (r2 == 0) goto L_0x006f
            java.util.List<com.google.android.gms.fido.u2f.api.common.RegisteredKey> r2 = r5.zzhgo
            java.util.List<com.google.android.gms.fido.u2f.api.common.RegisteredKey> r3 = r4.zzhgo
            boolean r2 = r2.containsAll(r3)
            if (r2 == 0) goto L_0x006f
        L_0x005a:
            com.google.android.gms.fido.u2f.api.common.ChannelIdValue r2 = r4.zzhgp
            com.google.android.gms.fido.u2f.api.common.ChannelIdValue r3 = r5.zzhgp
            boolean r2 = com.google.android.gms.common.internal.zzbg.equal(r2, r3)
            if (r2 == 0) goto L_0x006f
            java.lang.String r2 = r4.zzhgq
            java.lang.String r5 = r5.zzhgq
            boolean r5 = com.google.android.gms.common.internal.zzbg.equal(r2, r5)
            if (r5 == 0) goto L_0x006f
            return r0
        L_0x006f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fido.u2f.api.common.RegisterRequestParams.equals(java.lang.Object):boolean");
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

    public String getDisplayHint() {
        return this.zzhgq;
    }

    public List<RegisterRequest> getRegisterRequests() {
        return this.zzhgn;
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
        return Arrays.hashCode(new Object[]{this.zzgtd, this.zzhgm, this.zzhfe, this.zzhgn, this.zzhgo, this.zzhgp, this.zzhgq});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getRequestId(), false);
        zzbgo.zza(parcel, 3, getTimeoutSeconds(), false);
        zzbgo.zza(parcel, 4, (Parcelable) getAppId(), i, false);
        zzbgo.zzc(parcel, 5, getRegisterRequests(), false);
        zzbgo.zzc(parcel, 6, getRegisteredKeys(), false);
        zzbgo.zza(parcel, 7, (Parcelable) getChannelIdValue(), i, false);
        zzbgo.zza(parcel, 8, getDisplayHint(), false);
        zzbgo.zzai(parcel, zze);
    }
}
