package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.plus.PlusShare;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.zip.CRC32;

public final class zzauo extends zzbgl {
    public static final Parcelable.Creator<zzauo> CREATOR = new zzauq();
    private String zzbkm;
    private zzaua zzegg;
    private long zzegh;
    private int zzegi;
    private zzatx zzegj;
    private boolean zzegk;
    private int zzegl;
    private int zzegm;

    zzauo(zzaua zzaua, long j, int i, String str, zzatx zzatx, boolean z, int i2, int i3) {
        this.zzegg = zzaua;
        this.zzegh = j;
        this.zzegi = i;
        this.zzbkm = str;
        this.zzegj = zzatx;
        this.zzegk = z;
        this.zzegl = i2;
        this.zzegm = i3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zzauo(String str, Intent intent, String str2, Uri uri, String str3, List<AppIndexApi.AppIndexingLink> list, int i) {
        this(zza(str, intent), System.currentTimeMillis(), 0, (String) null, zza(intent, str2, uri, (String) null, list).zzabp(), false, -1, 1);
        Intent intent2 = intent;
        String str4 = str2;
        Uri uri2 = uri;
    }

    public static zzaty zza(Intent intent, String str, Uri uri, String str2, List<AppIndexApi.AppIndexingLink> list) {
        String string;
        zzaty zzaty = new zzaty();
        zzaty.zza(new zzauc(str, new zzauk(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE).zzar(true).zzev("name").zzabq(), "text1"));
        if (uri != null) {
            zzaty.zza(new zzauc(uri.toString(), new zzauk("web_url").zzaq(true).zzev(PlusShare.KEY_CALL_TO_ACTION_URL).zzabq()));
        }
        if (list != null) {
            zzcdj zzcdj = new zzcdj();
            int size = list.size();
            zzcdk[] zzcdkArr = new zzcdk[size];
            for (int i = 0; i < size; i++) {
                zzcdkArr[i] = new zzcdk();
                AppIndexApi.AppIndexingLink appIndexingLink = list.get(i);
                zzcdkArr[i].zzild = appIndexingLink.appIndexingUrl.toString();
                zzcdkArr[i].viewId = appIndexingLink.viewId;
                if (appIndexingLink.webUrl != null) {
                    zzcdkArr[i].zzile = appIndexingLink.webUrl.toString();
                }
            }
            zzcdj.zzilb = zzcdkArr;
            zzaty.zza(new zzauc(zzfls.zzc(zzcdj), new zzauk("outlinks").zzaq(true).zzev(".private:outLinks").zzeu("blob").zzabq()));
        }
        String action = intent.getAction();
        if (action != null) {
            zzaty.zza(zzl("intent_action", action));
        }
        String dataString = intent.getDataString();
        if (dataString != null) {
            zzaty.zza(zzl("intent_data", dataString));
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            zzaty.zza(zzl("intent_activity", component.getClassName()));
        }
        Bundle extras = intent.getExtras();
        if (!(extras == null || (string = extras.getString("intent_extra_data_key")) == null)) {
            zzaty.zza(zzl("intent_extra_data", string));
        }
        return zzaty.zzes(str2).zzap(true);
    }

    public static zzaua zza(String str, Intent intent) {
        return new zzaua(str, "", zzd(intent));
    }

    private static String zzd(Intent intent) {
        String uri = intent.toUri(1);
        CRC32 crc32 = new CRC32();
        try {
            crc32.update(uri.getBytes("UTF-8"));
            return Long.toHexString(crc32.getValue());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    private static zzauc zzl(String str, String str2) {
        return new zzauc(str2, new zzauk(str).zzaq(true).zzabq(), str);
    }

    public final String toString() {
        return String.format(Locale.US, "UsageInfo[documentId=%s, timestamp=%d, usageType=%d, status=%d]", new Object[]{this.zzegg, Long.valueOf(this.zzegh), Integer.valueOf(this.zzegi), Integer.valueOf(this.zzegm)});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzegg, i, false);
        zzbgo.zza(parcel, 2, this.zzegh);
        zzbgo.zzc(parcel, 3, this.zzegi);
        zzbgo.zza(parcel, 4, this.zzbkm, false);
        zzbgo.zza(parcel, 5, (Parcelable) this.zzegj, i, false);
        zzbgo.zza(parcel, 6, this.zzegk);
        zzbgo.zzc(parcel, 7, this.zzegl);
        zzbgo.zzc(parcel, 8, this.zzegm);
        zzbgo.zzai(parcel, zze);
    }
}
