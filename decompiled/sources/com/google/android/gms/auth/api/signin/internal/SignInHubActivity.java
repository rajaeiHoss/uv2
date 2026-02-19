package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

public class SignInHubActivity extends FragmentActivity {
    private static boolean zzeod = false;
    private boolean zzeoe = false;
    private SignInConfiguration zzeof;
    private boolean zzeog;
    /* access modifiers changed from: private */
    public int zzeoh;
    /* access modifiers changed from: private */
    public Intent zzeoi;

    class zza implements LoaderManager.LoaderCallbacks<Void> {
        private zza() {
        }

        public final Loader<Void> onCreateLoader(int i, Bundle bundle) {
            return new zzc(SignInHubActivity.this, GoogleApiClient.zzahz());
        }

        public final void onLoadFinished(Loader<Void> loader, Void voidR) {
            SignInHubActivity signInHubActivity = SignInHubActivity.this;
            signInHubActivity.setResult(signInHubActivity.zzeoh, SignInHubActivity.this.zzeoi);
            SignInHubActivity.this.finish();
        }

        public final void onLoaderReset(Loader<Void> loader) {
        }
    }

    private final void zzacw() {
        getSupportLoaderManager().initLoader(0, (Bundle) null, new zza());
        zzeod = false;
    }

    private final void zzaz(int i) {
        Status status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
        zzeod = false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (!this.zzeoe) {
            setResult(0);
            if (i == 40962) {
                if (intent != null) {
                    SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                    if (signInAccount != null && signInAccount.getGoogleSignInAccount() != null) {
                        GoogleSignInAccount googleSignInAccount = signInAccount.getGoogleSignInAccount();
                        zzp.zzbq(this).zza(this.zzeof.zzacv(), googleSignInAccount);
                        intent.removeExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                        intent.putExtra("googleSignInAccount", googleSignInAccount);
                        this.zzeog = true;
                        this.zzeoh = i2;
                        this.zzeoi = intent;
                        zzacw();
                        return;
                    } else if (intent.hasExtra("errorCode")) {
                        zzaz(intent.getIntExtra("errorCode", 8));
                        return;
                    }
                }
                zzaz(8);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        if ("com.google.android.gms.auth.NO_IMPL".equals(action)) {
            zzaz(GoogleSignInStatusCodes.SIGN_IN_FAILED);
        } else if (zzeod) {
            setResult(0);
            zzaz(GoogleSignInStatusCodes.SIGN_IN_CURRENTLY_IN_PROGRESS);
        } else {
            zzeod = true;
            if (action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") || action.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN")) {
                SignInConfiguration signInConfiguration = (SignInConfiguration) intent.getBundleExtra("config").getParcelable("config");
                this.zzeof = signInConfiguration;
                if (signInConfiguration == null) {
                    Log.e("AuthSignInClient", "Activity started with invalid configuration.");
                    setResult(0);
                    finish();
                } else if (bundle == null) {
                    Intent intent2 = new Intent(action);
                    intent2.setPackage(action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") ? "com.google.android.gms" : getPackageName());
                    intent2.putExtra("config", this.zzeof);
                    try {
                        startActivityForResult(intent2, 40962);
                    } catch (ActivityNotFoundException unused) {
                        this.zzeoe = true;
                        Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
                        zzaz(17);
                    }
                } else {
                    boolean z = bundle.getBoolean("signingInGoogleApiClients");
                    this.zzeog = z;
                    if (z) {
                        this.zzeoh = bundle.getInt("signInResultCode");
                        this.zzeoi = (Intent) bundle.getParcelable("signInResultData");
                        zzacw();
                    }
                }
            } else {
                String valueOf = String.valueOf(intent.getAction());
                Log.e("AuthSignInClient", valueOf.length() != 0 ? "Unknown action: ".concat(valueOf) : new String("Unknown action: "));
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzeog);
        if (this.zzeog) {
            bundle.putInt("signInResultCode", this.zzeoh);
            bundle.putParcelable("signInResultData", this.zzeoi);
        }
    }
}
