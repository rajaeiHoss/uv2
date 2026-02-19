package com.google.android.gms.internal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.R;
import com.google.android.gms.ads.internal.zzbt;
import java.util.Map;

@zzabh
public final class zzyg extends zzyn {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Map<String, String> zzbwu;

    public zzyg(zzaof zzaof, Map<String, String> map) {
        super(zzaof, "storePicture");
        this.zzbwu = map;
        this.mContext = zzaof.zztj();
    }

    public final void execute() {
        if (this.mContext == null) {
            zzbm("Activity context is not available");
            return;
        }
        zzbt.zzel();
        if (!zzaij.zzal(this.mContext).zzjc()) {
            zzbm("Feature is not supported by the device.");
            return;
        }
        String str = this.zzbwu.get("iurl");
        if (TextUtils.isEmpty(str)) {
            zzbm("Image url cannot be empty.");
        } else if (!URLUtil.isValidUrl(str)) {
            String valueOf = String.valueOf(str);
            zzbm(valueOf.length() != 0 ? "Invalid image url: ".concat(valueOf) : new String("Invalid image url: "));
        } else {
            String lastPathSegment = Uri.parse(str).getLastPathSegment();
            zzbt.zzel();
            if (!zzaij.zzck(lastPathSegment)) {
                String valueOf2 = String.valueOf(lastPathSegment);
                zzbm(valueOf2.length() != 0 ? "Image type not recognized: ".concat(valueOf2) : new String("Image type not recognized: "));
                return;
            }
            Resources resources = zzbt.zzep().getResources();
            zzbt.zzel();
            AlertDialog.Builder zzak = zzaij.zzak(this.mContext);
            zzak.setTitle(resources != null ? resources.getString(R.string.s1) : "Save image");
            zzak.setMessage(resources != null ? resources.getString(R.string.s2) : "Allow Ad to store image in Picture gallery?");
            zzak.setPositiveButton(resources != null ? resources.getString(R.string.s3) : "Accept", new zzyh(this, str, lastPathSegment));
            zzak.setNegativeButton(resources != null ? resources.getString(R.string.s4) : "Decline", new zzyi(this));
            zzak.create().show();
        }
    }
}
