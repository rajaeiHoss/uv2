package com.google.android.gms.drive;

import android.content.IntentSender;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.drive.query.internal.FilterHolder;
import com.google.android.gms.drive.query.internal.zzk;
import com.google.android.gms.internal.zzbnq;
import com.google.android.gms.internal.zzbrk;
import com.google.android.gms.internal.zzbtc;

@Deprecated
public class OpenFileActivityBuilder {
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private String zzesj;
    private String[] zzgqr;
    private Filter zzgqs;
    private DriveId zzgqt;

    public IntentSender build(GoogleApiClient googleApiClient) {
        zzbq.zza(googleApiClient.isConnected(), (Object) "Client must be connected");
        zzapi();
        try {
            return ((zzbrk) ((zzbnq) googleApiClient.zza(Drive.zzegu)).zzalw()).zza(new zzbtc(this.zzesj, this.zzgqr, this.zzgqt, this.zzgqs == null ? null : new FilterHolder(this.zzgqs)));
        } catch (RemoteException e) {
            throw new RuntimeException("Unable to connect Drive Play Service", e);
        }
    }

    /* access modifiers changed from: package-private */
    public final String getTitle() {
        return this.zzesj;
    }

    public OpenFileActivityBuilder setActivityStartFolder(DriveId driveId) {
        this.zzgqt = (DriveId) zzbq.checkNotNull(driveId);
        return this;
    }

    public OpenFileActivityBuilder setActivityTitle(String str) {
        this.zzesj = (String) zzbq.checkNotNull(str);
        return this;
    }

    public OpenFileActivityBuilder setMimeType(String[] strArr) {
        zzbq.checkArgument(strArr != null, "mimeTypes may not be null");
        this.zzgqr = strArr;
        return this;
    }

    public OpenFileActivityBuilder setSelectionFilter(Filter filter) {
        zzbq.checkArgument(filter != null, "filter may not be null");
        zzbq.checkArgument(true ^ zzk.zza(filter), "FullTextSearchFilter cannot be used as a selection filter");
        this.zzgqs = filter;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final void zzapi() {
        if (this.zzgqr == null) {
            this.zzgqr = new String[0];
        }
        if (this.zzgqr.length > 0 && this.zzgqs != null) {
            throw new IllegalStateException("Cannot use a selection filter and set mimetypes simultaneously");
        }
    }

    /* access modifiers changed from: package-private */
    public final String[] zzapx() {
        return this.zzgqr;
    }

    /* access modifiers changed from: package-private */
    public final Filter zzapy() {
        return this.zzgqs;
    }

    /* access modifiers changed from: package-private */
    public final DriveId zzapz() {
        return this.zzgqt;
    }
}
