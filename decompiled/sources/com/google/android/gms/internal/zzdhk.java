package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.common.internal.zzbq;
import org.jivesoftware.smackx.GroupChatInvitation;

public final class zzdhk implements zzdcp {
    private Context mContext;
    private DisplayMetrics zzaxo = new DisplayMetrics();

    public zzdhk(Context context) {
        this.mContext = context;
    }

    public final zzdjq<?> zzb(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(zzdjqArr != null);
        if (zzdjqArr.length != 0) {
            z = false;
        }
        zzbq.checkArgument(z);
        ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(this.zzaxo);
        return new zzdkc(this.zzaxo.widthPixels + GroupChatInvitation.ELEMENT_NAME + this.zzaxo.heightPixels);
    }
}
