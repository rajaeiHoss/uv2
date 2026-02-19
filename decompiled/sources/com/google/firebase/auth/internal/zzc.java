package com.google.firebase.auth.internal;

import android.text.TextUtils;
import com.google.android.gms.internal.zzecc;
import com.google.firebase.auth.ActionCodeResult;

public final class zzc implements ActionCodeResult {
    private final String zzemh;
    private final int zzitp;
    private final String zzmti;

    public zzc(zzecc zzecc) {
        int i;
        this.zzemh = TextUtils.isEmpty(zzecc.zzbui()) ? zzecc.getEmail() : zzecc.zzbui();
        this.zzmti = zzecc.getEmail();
        if (!TextUtils.isEmpty(zzecc.zzbuj())) {
            if (zzecc.zzbuj().equals("PASSWORD_RESET")) {
                i = 0;
            } else if (zzecc.zzbuj().equals("VERIFY_EMAIL")) {
                i = 1;
            } else if (zzecc.zzbuj().equals("RECOVER_EMAIL")) {
                i = 2;
            } else if (zzecc.zzbuj().equals("EMAIL_SIGNIN")) {
                i = 4;
            } else {
                this.zzitp = 3;
                return;
            }
            this.zzitp = i;
            return;
        }
        this.zzitp = 3;
    }

    public final String getData(int i) {
        if (i != 0) {
            if (i != 1) {
                return null;
            }
            return this.zzmti;
        } else if (this.zzitp == 4) {
            return null;
        } else {
            return this.zzemh;
        }
    }

    public final int getOperation() {
        return this.zzitp;
    }
}
