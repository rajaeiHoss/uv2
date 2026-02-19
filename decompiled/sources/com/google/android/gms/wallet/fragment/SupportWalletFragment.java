package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzo;
import com.google.android.gms.dynamic.zzr;
import com.google.android.gms.internal.zzdmd;
import com.google.android.gms.internal.zzdmh;
import com.google.android.gms.internal.zzdnc;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class SupportWalletFragment extends Fragment {
    /* access modifiers changed from: private */
    public boolean mCreated = false;
    /* access modifiers changed from: private */
    public final Fragment zzhdb = this;
    /* access modifiers changed from: private */
    public zzb zzloo;
    /* access modifiers changed from: private */
    public final zzr zzlop = zzr.zza(this);
    private final zzc zzloq = new zzc();
    /* access modifiers changed from: private */
    public zza zzlor = new zza(this);
    /* access modifiers changed from: private */
    public WalletFragmentOptions zzlos;
    /* access modifiers changed from: private */
    public WalletFragmentInitParams zzlot;
    /* access modifiers changed from: private */
    public MaskedWalletRequest zzlou;
    /* access modifiers changed from: private */
    public MaskedWallet zzlov;
    /* access modifiers changed from: private */
    public Boolean zzlow;

    public interface OnStateChangedListener {
        void onStateChanged(SupportWalletFragment supportWalletFragment, int i, int i2, Bundle bundle);
    }

    static class zza extends zzdmh {
        private OnStateChangedListener zzlox;
        private final SupportWalletFragment zzloy;

        zza(SupportWalletFragment supportWalletFragment) {
            this.zzloy = supportWalletFragment;
        }

        public final void zza(int i, int i2, Bundle bundle) {
            OnStateChangedListener onStateChangedListener = this.zzlox;
            if (onStateChangedListener != null) {
                onStateChangedListener.onStateChanged(this.zzloy, i, i2, bundle);
            }
        }

        public final void zza(OnStateChangedListener onStateChangedListener) {
            this.zzlox = onStateChangedListener;
        }
    }

    static class zzb implements LifecycleDelegate {
        private final zzdmd zzloz;

        private zzb(zzdmd zzdmd) {
            this.zzloz = zzdmd;
        }

        /* access modifiers changed from: private */
        public final int getState() {
            try {
                return this.zzloz.getState();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void initialize(WalletFragmentInitParams walletFragmentInitParams) {
            try {
                this.zzloz.initialize(walletFragmentInitParams);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void onActivityResult(int i, int i2, Intent intent) {
            try {
                this.zzloz.onActivityResult(i, i2, intent);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void setEnabled(boolean z) {
            try {
                this.zzloz.setEnabled(z);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void updateMaskedWallet(MaskedWallet maskedWallet) {
            try {
                this.zzloz.updateMaskedWallet(maskedWallet);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* access modifiers changed from: private */
        public final void updateMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
            try {
                this.zzloz.updateMaskedWalletRequest(maskedWalletRequest);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onCreate(Bundle bundle) {
            try {
                this.zzloz.onCreate(bundle);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                return (View) zzn.zzy(this.zzloz.onCreateView(zzn.zzz(layoutInflater), zzn.zzz(viewGroup), bundle));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onDestroy() {
        }

        public final void onDestroyView() {
        }

        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                this.zzloz.zza(zzn.zzz(activity), (WalletFragmentOptions) bundle.getParcelable("extraWalletFragmentOptions"), bundle2);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onLowMemory() {
        }

        public final void onPause() {
            try {
                this.zzloz.onPause();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onResume() {
            try {
                this.zzloz.onResume();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onSaveInstanceState(Bundle bundle) {
            try {
                this.zzloz.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onStart() {
            try {
                this.zzloz.onStart();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onStop() {
            try {
                this.zzloz.onStop();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class zzc extends com.google.android.gms.dynamic.zza<zzb> implements View.OnClickListener {
        private zzc() {
        }

        public final void onClick(View view) {
            FragmentActivity activity = SupportWalletFragment.this.zzhdb.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity), activity, -1);
        }

        /* access modifiers changed from: protected */
        public final void zza(FrameLayout frameLayout) {
            WalletFragmentStyle fragmentStyle;
            Button button = new Button(SupportWalletFragment.this.zzhdb.getActivity());
            button.setText(R.string.wallet_buy_button_place_holder);
            int i = -2;
            int i2 = -1;
            if (!(SupportWalletFragment.this.zzlos == null || (fragmentStyle = SupportWalletFragment.this.zzlos.getFragmentStyle()) == null)) {
                DisplayMetrics displayMetrics = SupportWalletFragment.this.zzhdb.getResources().getDisplayMetrics();
                i2 = fragmentStyle.zza("buyButtonWidth", displayMetrics, -1);
                i = fragmentStyle.zza("buyButtonHeight", displayMetrics, -2);
            }
            button.setLayoutParams(new ViewGroup.LayoutParams(i2, i));
            button.setOnClickListener(this);
            frameLayout.addView(button);
        }

        /* access modifiers changed from: protected */
        public final void zza(zzo<zzb> zzo) {
            FragmentActivity activity = SupportWalletFragment.this.zzhdb.getActivity();
            if (SupportWalletFragment.this.zzloo == null && SupportWalletFragment.this.mCreated && activity != null) {
                try {
                    zzb unused = SupportWalletFragment.this.zzloo = new zzb(zzdnc.zza(activity, SupportWalletFragment.this.zzlop, SupportWalletFragment.this.zzlos, SupportWalletFragment.this.zzlor));
                    WalletFragmentOptions unused2 = SupportWalletFragment.this.zzlos = null;
                    zzo.zza(SupportWalletFragment.this.zzloo);
                    if (SupportWalletFragment.this.zzlot != null) {
                        SupportWalletFragment.this.zzloo.initialize(SupportWalletFragment.this.zzlot);
                        WalletFragmentInitParams unused3 = SupportWalletFragment.this.zzlot = null;
                    }
                    if (SupportWalletFragment.this.zzlou != null) {
                        SupportWalletFragment.this.zzloo.updateMaskedWalletRequest(SupportWalletFragment.this.zzlou);
                        MaskedWalletRequest unused4 = SupportWalletFragment.this.zzlou = null;
                    }
                    if (SupportWalletFragment.this.zzlov != null) {
                        SupportWalletFragment.this.zzloo.updateMaskedWallet(SupportWalletFragment.this.zzlov);
                        MaskedWallet unused5 = SupportWalletFragment.this.zzlov = null;
                    }
                    if (SupportWalletFragment.this.zzlow != null) {
                        SupportWalletFragment.this.zzloo.setEnabled(SupportWalletFragment.this.zzlow.booleanValue());
                        Boolean unused6 = SupportWalletFragment.this.zzlow = null;
                    }
                } catch (GooglePlayServicesNotAvailableException unused7) {
                }
            }
        }
    }

    public static SupportWalletFragment newInstance(WalletFragmentOptions walletFragmentOptions) {
        SupportWalletFragment supportWalletFragment = new SupportWalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extraWalletFragmentOptions", walletFragmentOptions);
        supportWalletFragment.zzhdb.setArguments(bundle);
        return supportWalletFragment;
    }

    public final int getState() {
        zzb zzb2 = this.zzloo;
        if (zzb2 != null) {
            return zzb2.getState();
        }
        return 0;
    }

    public final void initialize(WalletFragmentInitParams walletFragmentInitParams) {
        zzb zzb2 = this.zzloo;
        if (zzb2 != null) {
            zzb2.initialize(walletFragmentInitParams);
            this.zzlot = null;
        } else if (this.zzlot == null) {
            this.zzlot = walletFragmentInitParams;
            if (this.zzlou != null) {
                Log.w("SupportWalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (this.zzlov != null) {
                Log.w("SupportWalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        } else {
            Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
        }
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        zzb zzb2 = this.zzloo;
        if (zzb2 != null) {
            zzb2.onActivityResult(i, i2, intent);
        }
    }

    public final void onCreate(Bundle bundle) {
        WalletFragmentOptions walletFragmentOptions;
        super.onCreate(bundle);
        if (bundle != null) {
            bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            WalletFragmentInitParams walletFragmentInitParams = (WalletFragmentInitParams) bundle.getParcelable("walletFragmentInitParams");
            if (walletFragmentInitParams != null) {
                if (this.zzlot != null) {
                    Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
                }
                this.zzlot = walletFragmentInitParams;
            }
            if (this.zzlou == null) {
                this.zzlou = (MaskedWalletRequest) bundle.getParcelable("maskedWalletRequest");
            }
            if (this.zzlov == null) {
                this.zzlov = (MaskedWallet) bundle.getParcelable("maskedWallet");
            }
            if (bundle.containsKey("walletFragmentOptions")) {
                this.zzlos = (WalletFragmentOptions) bundle.getParcelable("walletFragmentOptions");
            }
            if (bundle.containsKey("enabled")) {
                this.zzlow = Boolean.valueOf(bundle.getBoolean("enabled"));
            }
        } else if (!(this.zzhdb.getArguments() == null || (walletFragmentOptions = (WalletFragmentOptions) this.zzhdb.getArguments().getParcelable("extraWalletFragmentOptions")) == null)) {
            walletFragmentOptions.zzet(this.zzhdb.getActivity());
            this.zzlos = walletFragmentOptions;
        }
        this.mCreated = true;
        this.zzloq.onCreate(bundle);
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzloq.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public final void onDestroy() {
        super.onDestroy();
        this.mCreated = false;
    }

    public final void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        if (this.zzlos == null) {
            this.zzlos = WalletFragmentOptions.zza((Context) activity, attributeSet);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("attrKeyWalletFragmentOptions", this.zzlos);
        this.zzloq.onInflate(activity, bundle2, bundle);
    }

    public final void onPause() {
        super.onPause();
        this.zzloq.onPause();
    }

    public final void onResume() {
        super.onResume();
        this.zzloq.onResume();
        FragmentManager supportFragmentManager = this.zzhdb.getActivity().getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(GooglePlayServicesUtil.GMS_ERROR_DIALOG);
        if (findFragmentByTag != null) {
            supportFragmentManager.beginTransaction().remove(findFragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zzhdb.getActivity()), this.zzhdb.getActivity(), -1);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.zzloq.onSaveInstanceState(bundle);
        WalletFragmentInitParams walletFragmentInitParams = this.zzlot;
        if (walletFragmentInitParams != null) {
            bundle.putParcelable("walletFragmentInitParams", walletFragmentInitParams);
            this.zzlot = null;
        }
        MaskedWalletRequest maskedWalletRequest = this.zzlou;
        if (maskedWalletRequest != null) {
            bundle.putParcelable("maskedWalletRequest", maskedWalletRequest);
            this.zzlou = null;
        }
        MaskedWallet maskedWallet = this.zzlov;
        if (maskedWallet != null) {
            bundle.putParcelable("maskedWallet", maskedWallet);
            this.zzlov = null;
        }
        WalletFragmentOptions walletFragmentOptions = this.zzlos;
        if (walletFragmentOptions != null) {
            bundle.putParcelable("walletFragmentOptions", walletFragmentOptions);
            this.zzlos = null;
        }
        Boolean bool = this.zzlow;
        if (bool != null) {
            bundle.putBoolean("enabled", bool.booleanValue());
            this.zzlow = null;
        }
    }

    public final void onStart() {
        super.onStart();
        this.zzloq.onStart();
    }

    public final void onStop() {
        super.onStop();
        this.zzloq.onStop();
    }

    public final void setEnabled(boolean z) {
        Boolean valueOf;
        zzb zzb2 = this.zzloo;
        if (zzb2 != null) {
            zzb2.setEnabled(z);
            valueOf = null;
        } else {
            valueOf = Boolean.valueOf(z);
        }
        this.zzlow = valueOf;
    }

    public final void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        this.zzlor.zza(onStateChangedListener);
    }

    public final void updateMaskedWallet(MaskedWallet maskedWallet) {
        zzb zzb2 = this.zzloo;
        if (zzb2 != null) {
            zzb2.updateMaskedWallet(maskedWallet);
            this.zzlov = null;
            return;
        }
        this.zzlov = maskedWallet;
    }

    public final void updateMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
        zzb zzb2 = this.zzloo;
        if (zzb2 != null) {
            zzb2.updateMaskedWalletRequest(maskedWalletRequest);
            this.zzlou = null;
            return;
        }
        this.zzlou = maskedWalletRequest;
    }
}
