package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.wearable.Channel;
import com.google.android.gms.wearable.ChannelApi;
import com.google.android.gms.wearable.ChannelClient;
import com.streamax.config.constant.Constants;

public final class zzay extends zzbgl implements Channel, ChannelClient.Channel {
    public static final Parcelable.Creator<zzay> CREATOR = new zzbi();
    private final String mPath;
    /* access modifiers changed from: private */
    public final String zzeia;
    private final String zzlqu;

    public zzay(String str, String str2, String str3) {
        this.zzeia = (String) zzbq.checkNotNull(str);
        this.zzlqu = (String) zzbq.checkNotNull(str2);
        this.mPath = (String) zzbq.checkNotNull(str3);
    }

    public final PendingResult<Status> addListener(GoogleApiClient googleApiClient, ChannelApi.ChannelListener channelListener) {
        return zzb.zza(googleApiClient, new zzbf(this.zzeia, new IntentFilter[]{zzgj.zzoe("com.google.android.gms.wearable.CHANNEL_EVENT")}), channelListener);
    }

    public final PendingResult<Status> close(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzaz(this, googleApiClient));
    }

    public final PendingResult<Status> close(GoogleApiClient googleApiClient, int i) {
        return googleApiClient.zzd(new zzba(this, googleApiClient, i));
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzay)) {
            return false;
        }
        zzay zzay = (zzay) obj;
        return this.zzeia.equals(zzay.zzeia) && zzbg.equal(zzay.zzlqu, this.zzlqu) && zzbg.equal(zzay.mPath, this.mPath);
    }

    public final PendingResult<Channel.GetInputStreamResult> getInputStream(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzbb(this, googleApiClient));
    }

    public final String getNodeId() {
        return this.zzlqu;
    }

    public final PendingResult<Channel.GetOutputStreamResult> getOutputStream(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzbc(this, googleApiClient));
    }

    public final String getPath() {
        return this.mPath;
    }

    public final String getToken() {
        return this.zzeia;
    }

    public final int hashCode() {
        return this.zzeia.hashCode();
    }

    public final PendingResult<Status> receiveFile(GoogleApiClient googleApiClient, Uri uri, boolean z) {
        zzbq.checkNotNull(googleApiClient, "client is null");
        zzbq.checkNotNull(uri, "uri is null");
        return googleApiClient.zzd(new zzbd(this, googleApiClient, uri, z));
    }

    public final PendingResult<Status> removeListener(GoogleApiClient googleApiClient, ChannelApi.ChannelListener channelListener) {
        zzbq.checkNotNull(googleApiClient, "client is null");
        zzbq.checkNotNull(channelListener, "listener is null");
        return googleApiClient.zzd(new zzan(googleApiClient, channelListener, this.zzeia));
    }

    public final PendingResult<Status> sendFile(GoogleApiClient googleApiClient, Uri uri) {
        return sendFile(googleApiClient, uri, 0, -1);
    }

    public final PendingResult<Status> sendFile(GoogleApiClient googleApiClient, Uri uri, long j, long j2) {
        GoogleApiClient googleApiClient2 = googleApiClient;
        zzbq.checkNotNull(googleApiClient, "client is null");
        zzbq.checkNotNull(this.zzeia, "token is null");
        Uri uri2 = uri;
        zzbq.checkNotNull(uri, "uri is null");
        zzbq.zzb(j >= 0, "startOffset is negative: %s", Long.valueOf(j));
        zzbq.zzb(j2 >= 0 || j2 == -1, "invalid length: %s", Long.valueOf(j2));
        return googleApiClient.zzd(new zzbe(this, googleApiClient, uri, j, j2));
    }

    public final String toString() {
        int i = 0;
        for (char c : this.zzeia.toCharArray()) {
            i += c;
        }
        String trim = this.zzeia.trim();
        int length = trim.length();
        if (length > 25) {
            String substring = trim.substring(0, 10);
            String substring2 = trim.substring(length - 10, length);
            StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 16 + String.valueOf(substring2).length());
            sb.append(substring);
            sb.append("...");
            sb.append(substring2);
            sb.append("::");
            sb.append(i);
            trim = sb.toString();
        }
        String str = this.zzlqu;
        String str2 = this.mPath;
        StringBuilder sb2 = new StringBuilder(String.valueOf(trim).length() + 31 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb2.append("Channel{token=");
        sb2.append(trim);
        sb2.append(", nodeId=");
        sb2.append(str);
        sb2.append(", path=");
        sb2.append(str2);
        sb2.append(Constants.JsonSstringSuffix);
        return sb2.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzeia, false);
        zzbgo.zza(parcel, 3, getNodeId(), false);
        zzbgo.zza(parcel, 4, getPath(), false);
        zzbgo.zzai(parcel, zze);
    }
}
