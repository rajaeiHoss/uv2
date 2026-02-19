package com.google.android.gms.internal;

import android.accounts.Account;
import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.identity.intents.AddressConstants;
import com.google.android.gms.identity.intents.UserAddressRequest;

public final class zzcdo extends zzab<zzcds> {
    private Activity mActivity;
    private final int mTheme;
    private final String zzehk;
    private zzcdp zzilp;

    public zzcdo(Activity activity, Looper looper, zzr zzr, int i, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(activity, looper, 12, zzr, connectionCallbacks, onConnectionFailedListener);
        this.zzehk = zzr.getAccountName();
        this.mActivity = activity;
        this.mTheme = i;
    }

    public final void disconnect() {
        super.disconnect();
        zzcdp zzcdp = this.zzilp;
        if (zzcdp != null) {
            zzcdp.setActivity((Activity) null);
            this.zzilp = null;
        }
    }

    public final void zza(UserAddressRequest userAddressRequest, int i) {
        super.zzalv();
        this.zzilp = new zzcdp(i, this.mActivity);
        try {
            Bundle bundle = new Bundle();
            bundle.putString("com.google.android.gms.identity.intents.EXTRA_CALLING_PACKAGE_NAME", getContext().getPackageName());
            if (!TextUtils.isEmpty(this.zzehk)) {
                bundle.putParcelable("com.google.android.gms.identity.intents.EXTRA_ACCOUNT", new Account(this.zzehk, "com.google"));
            }
            bundle.putInt("com.google.android.gms.identity.intents.EXTRA_THEME", this.mTheme);
            ((zzcds) super.zzalw()).zza(this.zzilp, userAddressRequest, bundle);
        } catch (RemoteException e) {
            Log.e("AddressClientImpl", "Exception requesting user address", e);
            Bundle bundle2 = new Bundle();
            bundle2.putInt(AddressConstants.Extras.EXTRA_ERROR_CODE, AddressConstants.ErrorCodes.ERROR_CODE_NO_APPLICABLE_ADDRESSES);
            this.zzilp.zzi(1, bundle2);
        }
    }

    public final boolean zzalx() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final zzcds zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.identity.intents.internal.IAddressService");
        return queryLocalInterface instanceof zzcds ? (zzcds) queryLocalInterface : new zzcdt(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.identity.service.BIND";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.identity.intents.internal.IAddressService";
    }
}
