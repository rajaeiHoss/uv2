package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
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
import com.google.android.gms.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.zzj;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamic.zzo;
import com.google.android.gms.internal.zzdmd;
import com.google.android.gms.internal.zzdmh;
import com.google.android.gms.internal.zzdnc;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;

public final class WalletFragment extends Fragment {
    /* access modifiers changed from: private */
    public boolean mCreated = false;
    /* access modifiers changed from: private */
    public final Fragment zzhcy = this;
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
    /* access modifiers changed from: private */
    public zzb zzlpb;
    /* access modifiers changed from: private */
    public final zzj zzlpc = zzj.zza(this);
    private final zzc zzlpd = new zzc();
    /* access modifiers changed from: private */
    public zza zzlpe = new zza(this);

    public interface OnStateChangedListener {
        void onStateChanged(WalletFragment walletFragment, int i, int i2, Bundle bundle);
    }

    static class zza extends zzdmh {
        private OnStateChangedListener zzlpf;
        private final WalletFragment zzlpg;

        zza(WalletFragment walletFragment) {
            this.zzlpg = walletFragment;
        }

        public final void zza(int i, int i2, Bundle bundle) {
            OnStateChangedListener onStateChangedListener = this.zzlpf;
            if (onStateChangedListener != null) {
                onStateChangedListener.onStateChanged(this.zzlpg, i, i2, bundle);
            }
        }

        public final void zza(OnStateChangedListener onStateChangedListener) {
            this.zzlpf = onStateChangedListener;
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
            Activity activity = WalletFragment.this.zzhcy.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity), activity, -1);
        }

        /* access modifiers changed from: protected */
        public final void zza(FrameLayout frameLayout) {
            WalletFragmentStyle fragmentStyle;
            Button button = new Button(WalletFragment.this.zzhcy.getActivity());
            button.setText(R.string.wallet_buy_button_place_holder);
            int i = -2;
            int i2 = -1;
            if (!(WalletFragment.this.zzlos == null || (fragmentStyle = WalletFragment.this.zzlos.getFragmentStyle()) == null)) {
                DisplayMetrics displayMetrics = WalletFragment.this.zzhcy.getResources().getDisplayMetrics();
                i2 = fragmentStyle.zza("buyButtonWidth", displayMetrics, -1);
                i = fragmentStyle.zza("buyButtonHeight", displayMetrics, -2);
            }
            button.setLayoutParams(new ViewGroup.LayoutParams(i2, i));
            button.setOnClickListener(this);
            frameLayout.addView(button);
        }

        /* access modifiers changed from: protected */
        public final void zza(zzo<zzb> zzo) {
            Activity activity = WalletFragment.this.zzhcy.getActivity();
            if (WalletFragment.this.zzlpb == null && WalletFragment.this.mCreated && activity != null) {
                try {
                    zzb unused = WalletFragment.this.zzlpb = new zzb(zzdnc.zza(activity, WalletFragment.this.zzlpc, WalletFragment.this.zzlos, WalletFragment.this.zzlpe));
                    WalletFragmentOptions unused2 = WalletFragment.this.zzlos = null;
                    zzo.zza(WalletFragment.this.zzlpb);
                    if (WalletFragment.this.zzlot != null) {
                        WalletFragment.this.zzlpb.initialize(WalletFragment.this.zzlot);
                        WalletFragmentInitParams unused3 = WalletFragment.this.zzlot = null;
                    }
                    if (WalletFragment.this.zzlou != null) {
                        WalletFragment.this.zzlpb.updateMaskedWalletRequest(WalletFragment.this.zzlou);
                        MaskedWalletRequest unused4 = WalletFragment.this.zzlou = null;
                    }
                    if (WalletFragment.this.zzlov != null) {
                        WalletFragment.this.zzlpb.updateMaskedWallet(WalletFragment.this.zzlov);
                        MaskedWallet unused5 = WalletFragment.this.zzlov = null;
                    }
                    if (WalletFragment.this.zzlow != null) {
                        WalletFragment.this.zzlpb.setEnabled(WalletFragment.this.zzlow.booleanValue());
                        Boolean unused6 = WalletFragment.this.zzlow = null;
                    }
                } catch (GooglePlayServicesNotAvailableException unused7) {
                }
            }
        }
    }

    public static WalletFragment newInstance(WalletFragmentOptions walletFragmentOptions) {
        WalletFragment walletFragment = new WalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extraWalletFragmentOptions", walletFragmentOptions);
        walletFragment.zzhcy.setArguments(bundle);
        return walletFragment;
    }

    public final int getState() {
        zzb zzb2 = this.zzlpb;
        if (zzb2 != null) {
            return zzb2.getState();
        }
        return 0;
    }

    public final void initialize(WalletFragmentInitParams walletFragmentInitParams) {
        zzb zzb2 = this.zzlpb;
        if (zzb2 != null) {
            zzb2.initialize(walletFragmentInitParams);
            this.zzlot = null;
        } else if (this.zzlot == null) {
            this.zzlot = walletFragmentInitParams;
            if (this.zzlou != null) {
                Log.w("WalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (this.zzlov != null) {
                Log.w("WalletFragment", "updateMaskedWallet() was called before initialize()");
            }
        } else {
            Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
        }
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        zzb zzb2 = this.zzlpb;
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
                    Log.w("WalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
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
        } else if (!(this.zzhcy.getArguments() == null || (walletFragmentOptions = (WalletFragmentOptions) this.zzhcy.getArguments().getParcelable("extraWalletFragmentOptions")) == null)) {
            walletFragmentOptions.zzet(this.zzhcy.getActivity());
            this.zzlos = walletFragmentOptions;
        }
        this.mCreated = true;
        this.zzlpd.onCreate(bundle);
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzlpd.onCreateView(layoutInflater, viewGroup, bundle);
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
        this.zzlpd.onInflate(activity, bundle2, bundle);
    }

    public final void onPause() {
        super.onPause();
        this.zzlpd.onPause();
    }

    public final void onResume() {
        super.onResume();
        this.zzlpd.onResume();
        FragmentManager fragmentManager = this.zzhcy.getActivity().getFragmentManager();
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag(GooglePlayServicesUtil.GMS_ERROR_DIALOG);
        if (findFragmentByTag != null) {
            fragmentManager.beginTransaction().remove(findFragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zzhcy.getActivity()), this.zzhcy.getActivity(), -1);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.zzlpd.onSaveInstanceState(bundle);
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
        this.zzlpd.onStart();
    }

    public final void onStop() {
        super.onStop();
        this.zzlpd.onStop();
    }

    public final void setEnabled(boolean z) {
        Boolean valueOf;
        zzb zzb2 = this.zzlpb;
        if (zzb2 != null) {
            zzb2.setEnabled(z);
            valueOf = null;
        } else {
            valueOf = Boolean.valueOf(z);
        }
        this.zzlow = valueOf;
    }

    public final void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        this.zzlpe.zza(onStateChangedListener);
    }

    public final void updateMaskedWallet(MaskedWallet maskedWallet) {
        zzb zzb2 = this.zzlpb;
        if (zzb2 != null) {
            zzb2.updateMaskedWallet(maskedWallet);
            this.zzlov = null;
            return;
        }
        this.zzlov = maskedWallet;
    }

    public final void updateMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
        zzb zzb2 = this.zzlpb;
        if (zzb2 != null) {
            zzb2.updateMaskedWalletRequest(maskedWalletRequest);
            this.zzlou = null;
            return;
        }
        this.zzlou = maskedWalletRequest;
    }
}
