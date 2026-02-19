package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzaky;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzyq;

public class AdActivity extends Activity {
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private zzyq zzalm;

    private final void zzbd() {
        zzyq zzyq = this.zzalm;
        if (zzyq != null) {
            try {
                zzyq.zzbd();
            } catch (RemoteException e) {
                zzaky.zzc("Could not forward setContentViewSet to ad overlay:", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            this.zzalm.onActivityResult(i, i2, intent);
        } catch (Exception e) {
            zzaky.zzc("Could not forward onActivityResult to ad overlay:", e);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        boolean z = true;
        try {
            zzyq zzyq = this.zzalm;
            if (zzyq != null) {
                z = zzyq.zzni();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onBackPressed to ad overlay:", e);
        }
        if (z) {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            this.zzalm.zzk(zzn.zzz(configuration));
        } catch (RemoteException e) {
            zzaky.zzc("Failed to wrap configuration.", e);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        zzyq zzb = zzlc.zzik().zzb((Activity) this);
        this.zzalm = zzb;
        if (zzb == null) {
            zzaky.zzcz("Could not create ad overlay.");
        } else {
            try {
                zzb.onCreate(bundle);
                return;
            } catch (RemoteException e) {
                zzaky.zzc("Could not forward onCreate to ad overlay:", e);
            }
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        try {
            zzyq zzyq = this.zzalm;
            if (zzyq != null) {
                zzyq.onDestroy();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onDestroy to ad overlay:", e);
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        try {
            zzyq zzyq = this.zzalm;
            if (zzyq != null) {
                zzyq.onPause();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onPause to ad overlay:", e);
            finish();
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        try {
            zzyq zzyq = this.zzalm;
            if (zzyq != null) {
                zzyq.onRestart();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onRestart to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        try {
            zzyq zzyq = this.zzalm;
            if (zzyq != null) {
                zzyq.onResume();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onResume to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        try {
            zzyq zzyq = this.zzalm;
            if (zzyq != null) {
                zzyq.onSaveInstanceState(bundle);
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onSaveInstanceState to ad overlay:", e);
            finish();
        }
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        try {
            zzyq zzyq = this.zzalm;
            if (zzyq != null) {
                zzyq.onStart();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onStart to ad overlay:", e);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        try {
            zzyq zzyq = this.zzalm;
            if (zzyq != null) {
                zzyq.onStop();
            }
        } catch (RemoteException e) {
            zzaky.zzc("Could not forward onStop to ad overlay:", e);
            finish();
        }
        super.onStop();
    }

    public void setContentView(int i) {
        super.setContentView(i);
        zzbd();
    }

    public void setContentView(View view) {
        super.setContentView(view);
        zzbd();
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        zzbd();
    }
}
