package com.google.firebase.auth.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbgq;
import com.google.android.gms.internal.zzeci;

public class FederatedSignInActivity extends FragmentActivity {
    private static boolean zzmuc = false;
    private boolean zzmud = false;

    private final void zza(int i, Intent intent) {
        zzmuc = false;
        setResult(-1, intent);
        finish();
    }

    private final void zzhf(int i) {
        zzmuc = false;
        this.zzmud = false;
        setResult(0);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String action = getIntent().getAction();
        if (!"com.google.firebase.auth.internal.SIGN_IN".equals(action) && !"com.google.firebase.auth.internal.GET_CRED".equals(action)) {
            String valueOf = String.valueOf(action);
            Log.e("IdpSignInActivity", valueOf.length() != 0 ? "Unknown action: ".concat(valueOf) : new String("Unknown action: "));
        } else if (!zzmuc) {
            zzmuc = true;
            if (bundle != null) {
                this.zzmud = bundle.getBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN");
                return;
            }
            return;
        }
        setResult(0);
        finish();
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        boolean z = true;
        if (!this.zzmud) {
            Intent intent = new Intent("com.google.firebase.auth.api.gms.ui.START_WEB_SIGN_IN");
            intent.setPackage("com.google.android.gms");
            intent.putExtras(getIntent().getExtras());
            intent.putExtra("com.google.firebase.auth.internal.OPERATION", getIntent().getAction());
            try {
                startActivityForResult(intent, 40963);
            } catch (ActivityNotFoundException unused) {
                Log.w("IdpSignInActivity", "Could not launch web sign-in Intent. Google Play service is unavailable");
                zzhf(0);
            }
            this.zzmud = true;
            return;
        }
        Intent intent2 = getIntent();
        if ("com.google.firebase.auth.internal.WEB_SIGN_IN_FAILED".equals(intent2.getAction())) {
            Log.e("IdpSignInActivity", "Web sign-in failed, finishing");
            if (zzac.zzo(intent2)) {
                Status statusFromIntent = zzac.getStatusFromIntent(intent2);
                zzmuc = false;
                Intent intent3 = new Intent();
                zzac.zza(intent3, statusFromIntent);
                setResult(-1, intent3);
                finish();
            } else {
                zzhf(0);
            }
        } else {
            if (intent2.hasExtra("com.google.firebase.auth.internal.OPERATION") && intent2.hasExtra("com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST")) {
                String stringExtra = intent2.getStringExtra("com.google.firebase.auth.internal.OPERATION");
                if ("com.google.firebase.auth.internal.SIGN_IN".equals(stringExtra)) {
                    Intent intent4 = new Intent();
                    zzbgq.zza((zzeci) zzbgq.zza(intent2, "com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST", zzeci.CREATOR), intent4, "com.google.firebase.auth.internal.CREDENTIAL_FOR_AUTH_RESULT");
                    zza(-1, intent4);
                } else if ("com.google.firebase.auth.internal.GET_CRED".equals(stringExtra)) {
                    Intent intent5 = new Intent();
                    intent5.putExtra("com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST", intent2.getByteArrayExtra("com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST"));
                    zza(-1, intent5);
                }
            }
            z = false;
        }
        if (!z) {
            zzhf(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN", this.zzmud);
    }
}
