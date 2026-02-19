package com.google.android.gms.wallet;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzdmo;
import com.google.android.gms.internal.zzdmv;
import com.google.android.gms.internal.zzdnd;
import com.google.android.gms.internal.zzdne;
import com.google.android.gms.wallet.wobs.WalletObjects;
import java.util.Arrays;
import java.util.Locale;

public final class Wallet {
    public static final Api<WalletOptions> API;
    @Deprecated
    public static final Payments Payments = new zzdmo();
    private static final Api.zzf<zzdmv> zzegu;
    private static final Api.zza<zzdmv, WalletOptions> zzegv;
    private static WalletObjects zzloa = new zzdne();
    private static zzdnd zzlob = new zzdnd();

    public static final class WalletOptions implements Api.ApiOptions.HasAccountOptions {
        private Account account;
        public final int environment;
        public final int theme;
        final boolean zzloc;

        public static final class Builder {
            /* access modifiers changed from: private */
            public int mTheme = 0;
            /* access modifiers changed from: private */
            public int zzlod = 3;
            /* access modifiers changed from: private */
            public boolean zzloe = true;

            public final WalletOptions build() {
                return new WalletOptions(this, (zzap) null);
            }

            public final Builder setEnvironment(int i) {
                if (i == 0 || i == 0 || i == 2 || i == 1 || i == 3) {
                    this.zzlod = i;
                    return this;
                }
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid environment value %d", new Object[]{Integer.valueOf(i)}));
            }

            public final Builder setTheme(int i) {
                if (i == 0 || i == 1) {
                    this.mTheme = i;
                    return this;
                }
                throw new IllegalArgumentException(String.format(Locale.US, "Invalid theme value %d", new Object[]{Integer.valueOf(i)}));
            }

            @Deprecated
            public final Builder useGoogleWallet() {
                this.zzloe = false;
                return this;
            }
        }

        private WalletOptions() {
            this(new Builder());
        }

        private WalletOptions(Builder builder) {
            this.environment = builder.zzlod;
            this.theme = builder.mTheme;
            this.zzloc = builder.zzloe;
            this.account = null;
        }

        /* synthetic */ WalletOptions(Builder builder, zzap zzap) {
            this(builder);
        }

        /* synthetic */ WalletOptions(zzap zzap) {
            this();
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof WalletOptions)) {
                return false;
            }
            WalletOptions walletOptions = (WalletOptions) obj;
            return zzbg.equal(Integer.valueOf(this.environment), Integer.valueOf(walletOptions.environment)) && zzbg.equal(Integer.valueOf(this.theme), Integer.valueOf(walletOptions.theme)) && zzbg.equal((Object) null, (Object) null) && zzbg.equal(Boolean.valueOf(this.zzloc), Boolean.valueOf(walletOptions.zzloc));
        }

        public final Account getAccount() {
            return null;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{Integer.valueOf(this.environment), Integer.valueOf(this.theme), null, Boolean.valueOf(this.zzloc)});
        }
    }

    public static abstract class zza<R extends Result> extends zzm<R, zzdmv> {
        public zza(GoogleApiClient googleApiClient) {
            super((Api<?>) Wallet.API, googleApiClient);
        }

        /* access modifiers changed from: protected */
        public abstract void zza(zzdmv zzdmv) throws RemoteException;
    }

    public static abstract class zzb extends zza<Status> {
        public zzb(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        /* access modifiers changed from: protected */
        public final Status zzb(Status status) {
            return status;
        }
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.gms.internal.zzdnd, com.google.android.gms.internal.zzdlw] */
    static {
        Api.zzf<zzdmv> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzap zzap = new zzap();
        zzegv = zzap;
        API = new Api<>("Wallet.API", zzap, zzf);
    }

    private Wallet() {
    }

    public static PaymentsClient getPaymentsClient(Activity activity, WalletOptions walletOptions) {
        return new PaymentsClient(activity, walletOptions);
    }

    public static PaymentsClient getPaymentsClient(Context context, WalletOptions walletOptions) {
        return new PaymentsClient(context, walletOptions);
    }

    public static WalletObjectsClient getWalletObjectsClient(Activity activity, WalletOptions walletOptions) {
        return new WalletObjectsClient(activity, walletOptions);
    }
}
