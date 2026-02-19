package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract;
import android.text.TextUtils;
import com.google.android.gms.R;
import com.google.android.gms.ads.internal.zzbt;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;

@zzabh
public final class zzya extends zzyn {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Map<String, String> zzbwu;
    private String zzcks = zzbk(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
    private long zzckt = zzbl("start_ticks");
    private long zzcku = zzbl("end_ticks");
    private String zzckv = zzbk("summary");
    private String zzckw = zzbk(FirebaseAnalytics.Param.LOCATION);

    public zzya(zzaof zzaof, Map<String, String> map) {
        super(zzaof, "createCalendarEvent");
        this.zzbwu = map;
        this.mContext = zzaof.zztj();
    }

    private final String zzbk(String str) {
        return TextUtils.isEmpty(this.zzbwu.get(str)) ? "" : this.zzbwu.get(str);
    }

    private final long zzbl(String str) {
        String str2 = this.zzbwu.get(str);
        if (str2 == null) {
            return -1;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    /* access modifiers changed from: package-private */
    public final Intent createIntent() {
        Intent data = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
        data.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_TITLE, this.zzcks);
        data.putExtra("eventLocation", this.zzckw);
        data.putExtra(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION, this.zzckv);
        long j = this.zzckt;
        if (j > -1) {
            data.putExtra("beginTime", j);
        }
        long j2 = this.zzcku;
        if (j2 > -1) {
            data.putExtra("endTime", j2);
        }
        data.setFlags(DriveFile.MODE_READ_ONLY);
        return data;
    }

    public final void execute() {
        if (this.mContext == null) {
            zzbm("Activity context is not available.");
            return;
        }
        zzbt.zzel();
        if (!zzaij.zzal(this.mContext).zzjd()) {
            zzbm("This feature is not available on the device.");
            return;
        }
        zzbt.zzel();
        AlertDialog.Builder zzak = zzaij.zzak(this.mContext);
        Resources resources = zzbt.zzep().getResources();
        zzak.setTitle(resources != null ? resources.getString(R.string.s5) : "Create calendar event");
        zzak.setMessage(resources != null ? resources.getString(R.string.s6) : "Allow Ad to create a calendar event?");
        zzak.setPositiveButton(resources != null ? resources.getString(R.string.s3) : "Accept", new zzyb(this));
        zzak.setNegativeButton(resources != null ? resources.getString(R.string.s4) : "Decline", new zzyc(this));
        zzak.create().show();
    }
}
