package com.google.android.gms.drive.events;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbrr;
import com.google.android.gms.internal.zzbuj;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class CompletionEvent extends zzbgl implements ResourceEvent {
    public static final Parcelable.Creator<CompletionEvent> CREATOR = new zzg();
    public static final int STATUS_CANCELED = 3;
    public static final int STATUS_CONFLICT = 2;
    public static final int STATUS_FAILURE = 1;
    public static final int STATUS_SUCCESS = 0;
    private static final zzal zzgpv = new zzal("CompletionEvent", "");
    private int zzcfl;
    private String zzehk;
    private DriveId zzgpe;
    private ParcelFileDescriptor zzgrs;
    private ParcelFileDescriptor zzgrt;
    private MetadataBundle zzgru;
    private List<String> zzgrv;
    private IBinder zzgrw;
    private boolean zzgrx = false;
    private boolean zzgry = false;
    private boolean zzgrz = false;

    CompletionEvent(DriveId driveId, String str, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, MetadataBundle metadataBundle, List<String> list, int i, IBinder iBinder) {
        this.zzgpe = driveId;
        this.zzehk = str;
        this.zzgrs = parcelFileDescriptor;
        this.zzgrt = parcelFileDescriptor2;
        this.zzgru = metadataBundle;
        this.zzgrv = list;
        this.zzcfl = i;
        this.zzgrw = iBinder;
    }

    private final void zzaf(boolean z) {
        zzaqa();
        this.zzgrz = true;
        zzp.zza(this.zzgrs);
        zzp.zza(this.zzgrt);
        MetadataBundle metadataBundle = this.zzgru;
        if (metadataBundle != null && metadataBundle.zzd(zzbuj.zzhac)) {
            ((BitmapTeleporter) this.zzgru.zza(zzbuj.zzhac)).release();
        }
        IBinder iBinder = this.zzgrw;
        String str = "snooze";
        if (iBinder == null) {
            if (!z) {
                str = "dismiss";
            }
            zzgpv.zzd("CompletionEvent", "No callback on %s", str);
            return;
        }
        try {
            zzbrr.zzao(iBinder).zzaf(z);
        } catch (RemoteException e) {
            if (!z) {
                str = "dismiss";
            }
            zzgpv.zzd("CompletionEvent", String.format("RemoteException on %s", new Object[]{str}), (Throwable) e);
        }
    }

    private final void zzaqa() {
        if (this.zzgrz) {
            throw new IllegalStateException("Event has already been dismissed or snoozed.");
        }
    }

    public final void dismiss() {
        zzaf(false);
    }

    public final String getAccountName() {
        zzaqa();
        return this.zzehk;
    }

    public final InputStream getBaseContentsInputStream() {
        zzaqa();
        if (this.zzgrs == null) {
            return null;
        }
        if (!this.zzgrx) {
            this.zzgrx = true;
            return new FileInputStream(this.zzgrs.getFileDescriptor());
        }
        throw new IllegalStateException("getBaseInputStream() can only be called once per CompletionEvent instance.");
    }

    public final DriveId getDriveId() {
        zzaqa();
        return this.zzgpe;
    }

    public final InputStream getModifiedContentsInputStream() {
        zzaqa();
        if (this.zzgrt == null) {
            return null;
        }
        if (!this.zzgry) {
            this.zzgry = true;
            return new FileInputStream(this.zzgrt.getFileDescriptor());
        }
        throw new IllegalStateException("getModifiedInputStream() can only be called once per CompletionEvent instance.");
    }

    public final MetadataChangeSet getModifiedMetadataChangeSet() {
        zzaqa();
        if (this.zzgru != null) {
            return new MetadataChangeSet(this.zzgru);
        }
        return null;
    }

    public final int getStatus() {
        zzaqa();
        return this.zzcfl;
    }

    public final List<String> getTrackingTags() {
        zzaqa();
        return new ArrayList(this.zzgrv);
    }

    public final int getType() {
        return 2;
    }

    public final void snooze() {
        zzaf(true);
    }

    public final String toString() {
        String str;
        List<String> list = this.zzgrv;
        if (list == null) {
            str = "<null>";
        } else {
            String join = TextUtils.join("','", list);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append("'");
            sb.append(join);
            sb.append("'");
            str = sb.toString();
        }
        return String.format(Locale.US, "CompletionEvent [id=%s, status=%s, trackingTag=%s]", new Object[]{this.zzgpe, Integer.valueOf(this.zzcfl), str});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i | 1;
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgpe, i2, false);
        zzbgo.zza(parcel, 3, this.zzehk, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzgrs, i2, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzgrt, i2, false);
        zzbgo.zza(parcel, 6, (Parcelable) this.zzgru, i2, false);
        zzbgo.zzb(parcel, 7, this.zzgrv, false);
        zzbgo.zzc(parcel, 8, this.zzcfl);
        zzbgo.zza(parcel, 9, this.zzgrw, false);
        zzbgo.zzai(parcel, zze);
    }
}
