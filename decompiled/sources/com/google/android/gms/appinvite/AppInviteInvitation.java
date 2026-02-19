package com.google.android.gms.appinvite;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

public final class AppInviteInvitation {
    private static final String[] zzegw = {"jpg", "jpeg", "png"};

    public static final class IntentBuilder {
        public static final int MAX_CALL_TO_ACTION_TEXT_LENGTH = 20;
        public static final int MAX_EMAIL_HTML_CONTENT = 512000;
        public static final int MAX_MESSAGE_LENGTH = 100;
        public static final int MIN_CALL_TO_ACTION_TEXT_LENGTH = 2;
        private final Intent mIntent;
        private String zzegx;
        private String zzegy;

        @Retention(RetentionPolicy.SOURCE)
        public @interface PlatformMode {
            public static final int PROJECT_PLATFORM_ANDROID = 2;
            public static final int PROJECT_PLATFORM_IOS = 1;
        }

        public IntentBuilder(CharSequence charSequence) {
            zzbq.checkNotNull(charSequence);
            Intent intent = new Intent("com.google.android.gms.appinvite.ACTION_APP_INVITE");
            this.mIntent = intent;
            intent.putExtra("com.google.android.gms.appinvite.TITLE", charSequence);
            intent.setPackage("com.google.android.gms");
        }

        public final Intent build() {
            if (!TextUtils.isEmpty(this.zzegx)) {
                zzbq.zzh(this.zzegy, "Email html content must be set when email subject is set.");
                zzbq.checkArgument(this.mIntent.getData() == null, "Custom image must not be set when email html content is set.");
                zzbq.checkArgument(TextUtils.isEmpty(this.mIntent.getCharSequenceExtra("com.google.android.gms.appinvite.BUTTON_TEXT")), "Call to action text must not be set when email html content is set.");
                this.mIntent.putExtra("com.google.android.gms.appinvite.EMAIL_SUBJECT", this.zzegx);
                this.mIntent.putExtra("com.google.android.gms.appinvite.EMAIL_CONTENT", this.zzegy);
            } else if (!TextUtils.isEmpty(this.zzegy)) {
                throw new IllegalArgumentException("Email subject must be set when email html content is set.");
            }
            return this.mIntent;
        }

        public final IntentBuilder setAccount(Account account) {
            if (account == null || !"com.google".equals(account.type)) {
                this.mIntent.removeExtra("com.google.android.gms.appinvite.ACCOUNT_NAME");
            } else {
                this.mIntent.putExtra("com.google.android.gms.appinvite.ACCOUNT_NAME", account);
            }
            return this;
        }

        public final IntentBuilder setAdditionalReferralParameters(Map<String, String> map) {
            if (map != null) {
                this.mIntent.putExtra("com.google.android.gms.appinvite.REFERRAL_PARAMETERS_URI", AppInviteInvitation.zzt(map));
            } else {
                this.mIntent.removeExtra("com.google.android.gms.appinvite.REFERRAL_PARAMETERS_URI");
            }
            return this;
        }

        public final IntentBuilder setAndroidMinimumVersionCode(int i) {
            this.mIntent.putExtra("com.google.android.gms.appinvite.appMinimumVersionCode", i);
            return this;
        }

        public final IntentBuilder setCallToActionText(CharSequence charSequence) {
            if (charSequence == null || charSequence.length() < 2 || charSequence.length() > 20) {
                throw new IllegalArgumentException(String.format("Text must be between %d and %d chars in length.", new Object[]{2, 20}));
            }
            this.mIntent.putExtra("com.google.android.gms.appinvite.BUTTON_TEXT", charSequence);
            return this;
        }

        public final IntentBuilder setCustomImage(Uri uri) {
            zzbq.checkNotNull(uri);
            zzbq.checkArgument(uri.isAbsolute(), "Image uri is not an absolute uri. Did you forget to add a scheme to the Uri?");
            String lowerCase = uri.getScheme().toLowerCase();
            boolean z = false;
            boolean z2 = lowerCase.equals("android.resource") || lowerCase.equals(FirebaseAnalytics.Param.CONTENT) || lowerCase.equals("file");
            zzbq.checkArgument(z2 || lowerCase.equals("http") || lowerCase.equals("https"), "Image uri must be a content URI with scheme \"android.resource\", \"content\" or \"file\", or a network url with scheme \"http\" or \"https\".");
            if (!z2) {
                String lastPathSegment = uri.getLastPathSegment();
                String str = null;
                if (!(lastPathSegment == null || lastPathSegment.lastIndexOf(".") == -1)) {
                    str = lastPathSegment.substring(lastPathSegment.lastIndexOf(".") + 1, lastPathSegment.length()).toLowerCase();
                }
                if (TextUtils.isEmpty(str) || AppInviteInvitation.zzew(str)) {
                    z = true;
                }
                zzbq.checkArgument(z, String.valueOf(str).concat(" images are not supported. Only jpg, jpeg, or png images are supported."));
            }
            this.mIntent.setData(uri.buildUpon().scheme(lowerCase).build());
            if (z2) {
                this.mIntent.addFlags(1);
            }
            return this;
        }

        public final IntentBuilder setDeepLink(Uri uri) {
            if (uri != null) {
                this.mIntent.putExtra("com.google.android.gms.appinvite.DEEP_LINK_URL", uri);
            } else {
                this.mIntent.removeExtra("com.google.android.gms.appinvite.DEEP_LINK_URL");
            }
            return this;
        }

        public final IntentBuilder setEmailHtmlContent(String str) {
            if (str == null || str.getBytes().length <= 512000) {
                this.zzegy = str;
                return this;
            }
            throw new IllegalArgumentException(String.format("Email html content must be %d bytes or less.", new Object[]{Integer.valueOf(MAX_EMAIL_HTML_CONTENT)}));
        }

        public final IntentBuilder setEmailSubject(String str) {
            this.zzegx = str;
            return this;
        }

        public final IntentBuilder setGoogleAnalyticsTrackingId(String str) {
            this.mIntent.putExtra("com.google.android.gms.appinvite.GOOGLE_ANALYTICS_TRACKING_ID", str);
            return this;
        }

        public final IntentBuilder setMessage(CharSequence charSequence) {
            if (charSequence == null || charSequence.length() <= 100) {
                this.mIntent.putExtra("com.google.android.gms.appinvite.MESSAGE", charSequence);
                return this;
            }
            throw new IllegalArgumentException(String.format("Message must be %d chars or less.", new Object[]{100}));
        }

        public final IntentBuilder setOtherPlatformsTargetApplication(int i, String str) throws IllegalArgumentException {
            Intent intent;
            String str2;
            if (i == 1) {
                intent = this.mIntent;
                str2 = "com.google.android.gms.appinvite.iosTargetApplication";
            } else if (i == 2) {
                intent = this.mIntent;
                str2 = "com.google.android.gms.appinvite.androidTargetApplication";
            } else {
                throw new IllegalArgumentException("targetPlatform must be either PROJECT_PLATFORM_IOS or PROJECT_PLATFORM_ANDROID.");
            }
            intent.putExtra(str2, str);
            return this;
        }
    }

    private AppInviteInvitation() {
    }

    public static String[] getInvitationIds(int i, Intent intent) {
        if (i == -1) {
            return intent.getStringArrayExtra("com.google.android.gms.appinvite.RESULT_INVITATION_IDS");
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static boolean zzew(String str) {
        int i = 0;
        while (true) {
            String[] strArr = zzegw;
            if (i >= strArr.length) {
                return false;
            }
            if (strArr[i].equals(str)) {
                return true;
            }
            i++;
        }
    }

    /* access modifiers changed from: private */
    public static Bundle zzt(Map<String, String> map) {
        Bundle bundle = new Bundle();
        for (String next : map.keySet()) {
            bundle.putString(next, map.get(next));
        }
        return bundle;
    }
}
