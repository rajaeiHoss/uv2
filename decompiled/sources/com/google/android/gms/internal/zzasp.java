package com.google.android.gms.internal;

import org.jivesoftware.smackx.GroupChatInvitation;

public final class zzasp extends zzari {
    zzasp(zzark zzark) {
        super(zzark);
    }

    public final zzaqq zzaah() {
        zzyk();
        return zzya().zzwi();
    }

    public final String zzaai() {
        zzyk();
        zzaqq zzaah = zzaah();
        int i = zzaah.zzcly;
        int i2 = zzaah.zzclz;
        StringBuilder sb = new StringBuilder(23);
        sb.append(i);
        sb.append(GroupChatInvitation.ELEMENT_NAME);
        sb.append(i2);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public final void zzwk() {
    }
}
