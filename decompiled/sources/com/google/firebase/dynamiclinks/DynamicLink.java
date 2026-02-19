package com.google.firebase.dynamiclinks;

import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.internal.zzepn;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.streamax.client.ui.VideoPlayActivity;

public final class DynamicLink {
    private final Bundle zznrw;

    public static final class AndroidParameters {
        final Bundle zzefr;

        public static final class Builder {
            private final Bundle zzefr;

            public Builder() {
                if (FirebaseApp.getInstance() != null) {
                    Bundle bundle = new Bundle();
                    this.zzefr = bundle;
                    bundle.putString("apn", FirebaseApp.getInstance().getApplicationContext().getPackageName());
                    return;
                }
                throw new IllegalStateException("FirebaseApp not initialized.");
            }

            public Builder(String str) {
                Bundle bundle = new Bundle();
                this.zzefr = bundle;
                bundle.putString("apn", str);
            }

            public final AndroidParameters build() {
                return new AndroidParameters(this.zzefr);
            }

            public final Builder setFallbackUrl(Uri uri) {
                this.zzefr.putParcelable("afl", uri);
                return this;
            }

            public final Builder setMinimumVersion(int i) {
                this.zzefr.putInt("amv", i);
                return this;
            }
        }

        private AndroidParameters(Bundle bundle) {
            this.zzefr = bundle;
        }
    }

    public static final class Builder {
        private final Bundle zznrw;
        private final zzepn zznrx;
        private final Bundle zznry;

        public Builder(zzepn zzepn) {
            this.zznrx = zzepn;
            Bundle bundle = new Bundle();
            this.zznrw = bundle;
            if (FirebaseApp.getInstance() != null) {
                bundle.putString("apiKey", FirebaseApp.getInstance().getOptions().getApiKey());
            }
            Bundle bundle2 = new Bundle();
            this.zznry = bundle2;
            bundle.putBundle(VideoPlayActivity.INTENT_KEY_PARAMETERS, bundle2);
        }

        private final void zzcdr() {
            if (this.zznrw.getString("apiKey") == null) {
                throw new IllegalArgumentException("Missing API key. Set with setApiKey().");
            }
        }

        public final DynamicLink buildDynamicLink() {
            zzepn.zzag(this.zznrw);
            return new DynamicLink(this.zznrw);
        }

        public final Task<ShortDynamicLink> buildShortDynamicLink() {
            zzcdr();
            return this.zznrx.zzaf(this.zznrw);
        }

        public final Task<ShortDynamicLink> buildShortDynamicLink(int i) {
            zzcdr();
            this.zznrw.putInt("suffix", i);
            return this.zznrx.zzaf(this.zznrw);
        }

        public final Builder setAndroidParameters(AndroidParameters androidParameters) {
            this.zznry.putAll(androidParameters.zzefr);
            return this;
        }

        public final Builder setDynamicLinkDomain(String str) {
            this.zznrw.putString("domain", str);
            return this;
        }

        public final Builder setGoogleAnalyticsParameters(GoogleAnalyticsParameters googleAnalyticsParameters) {
            this.zznry.putAll(googleAnalyticsParameters.zzefr);
            return this;
        }

        public final Builder setIosParameters(IosParameters iosParameters) {
            this.zznry.putAll(iosParameters.zzefr);
            return this;
        }

        public final Builder setItunesConnectAnalyticsParameters(ItunesConnectAnalyticsParameters itunesConnectAnalyticsParameters) {
            this.zznry.putAll(itunesConnectAnalyticsParameters.zzefr);
            return this;
        }

        public final Builder setLink(Uri uri) {
            this.zznry.putParcelable("link", uri);
            return this;
        }

        public final Builder setLongLink(Uri uri) {
            this.zznrw.putParcelable("dynamicLink", uri);
            return this;
        }

        public final Builder setNavigationInfoParameters(NavigationInfoParameters navigationInfoParameters) {
            this.zznry.putAll(navigationInfoParameters.zzefr);
            return this;
        }

        public final Builder setSocialMetaTagParameters(SocialMetaTagParameters socialMetaTagParameters) {
            this.zznry.putAll(socialMetaTagParameters.zzefr);
            return this;
        }
    }

    public static final class GoogleAnalyticsParameters {
        Bundle zzefr;

        public static final class Builder {
            private final Bundle zzefr;

            public Builder() {
                this.zzefr = new Bundle();
            }

            public Builder(String str, String str2, String str3) {
                Bundle bundle = new Bundle();
                this.zzefr = bundle;
                bundle.putString("utm_source", str);
                bundle.putString("utm_medium", str2);
                bundle.putString("utm_campaign", str3);
            }

            public final GoogleAnalyticsParameters build() {
                return new GoogleAnalyticsParameters(this.zzefr);
            }

            public final Builder setCampaign(String str) {
                this.zzefr.putString("utm_campaign", str);
                return this;
            }

            public final Builder setContent(String str) {
                this.zzefr.putString("utm_content", str);
                return this;
            }

            public final Builder setMedium(String str) {
                this.zzefr.putString("utm_medium", str);
                return this;
            }

            public final Builder setSource(String str) {
                this.zzefr.putString("utm_source", str);
                return this;
            }

            public final Builder setTerm(String str) {
                this.zzefr.putString("utm_term", str);
                return this;
            }
        }

        private GoogleAnalyticsParameters(Bundle bundle) {
            this.zzefr = bundle;
        }
    }

    public static final class IosParameters {
        final Bundle zzefr;

        public static final class Builder {
            private final Bundle zzefr;

            public Builder(String str) {
                Bundle bundle = new Bundle();
                this.zzefr = bundle;
                bundle.putString("ibi", str);
            }

            public final IosParameters build() {
                return new IosParameters(this.zzefr);
            }

            public final Builder setAppStoreId(String str) {
                this.zzefr.putString("isi", str);
                return this;
            }

            public final Builder setCustomScheme(String str) {
                this.zzefr.putString("ius", str);
                return this;
            }

            public final Builder setFallbackUrl(Uri uri) {
                this.zzefr.putParcelable("ifl", uri);
                return this;
            }

            public final Builder setIpadBundleId(String str) {
                this.zzefr.putString("ipbi", str);
                return this;
            }

            public final Builder setIpadFallbackUrl(Uri uri) {
                this.zzefr.putParcelable("ipfl", uri);
                return this;
            }

            public final Builder setMinimumVersion(String str) {
                this.zzefr.putString("imv", str);
                return this;
            }
        }

        private IosParameters(Bundle bundle) {
            this.zzefr = bundle;
        }
    }

    public static final class ItunesConnectAnalyticsParameters {
        final Bundle zzefr;

        public static final class Builder {
            private final Bundle zzefr = new Bundle();

            public final ItunesConnectAnalyticsParameters build() {
                return new ItunesConnectAnalyticsParameters(this.zzefr);
            }

            public final Builder setAffiliateToken(String str) {
                this.zzefr.putString("at", str);
                return this;
            }

            public final Builder setCampaignToken(String str) {
                this.zzefr.putString("ct", str);
                return this;
            }

            public final Builder setProviderToken(String str) {
                this.zzefr.putString("pt", str);
                return this;
            }
        }

        private ItunesConnectAnalyticsParameters(Bundle bundle) {
            this.zzefr = bundle;
        }
    }

    public static final class NavigationInfoParameters {
        final Bundle zzefr;

        public static final class Builder {
            private final Bundle zzefr = new Bundle();

            public final NavigationInfoParameters build() {
                return new NavigationInfoParameters(this.zzefr);
            }

            public final Builder setForcedRedirectEnabled(boolean z) {
                this.zzefr.putInt("efr", z ? 1 : 0);
                return this;
            }
        }

        private NavigationInfoParameters(Bundle bundle) {
            this.zzefr = bundle;
        }
    }

    public static final class SocialMetaTagParameters {
        final Bundle zzefr;

        public static final class Builder {
            private final Bundle zzefr = new Bundle();

            public final SocialMetaTagParameters build() {
                return new SocialMetaTagParameters(this.zzefr);
            }

            public final Builder setDescription(String str) {
                this.zzefr.putString("sd", str);
                return this;
            }

            public final Builder setImageUrl(Uri uri) {
                this.zzefr.putParcelable("si", uri);
                return this;
            }

            public final Builder setTitle(String str) {
                this.zzefr.putString("st", str);
                return this;
            }
        }

        private SocialMetaTagParameters(Bundle bundle) {
            this.zzefr = bundle;
        }
    }

    DynamicLink(Bundle bundle) {
        this.zznrw = bundle;
    }

    public final Uri getUri() {
        Bundle bundle = this.zznrw;
        zzepn.zzag(bundle);
        Uri uri = (Uri) bundle.getParcelable("dynamicLink");
        if (uri != null) {
            return uri;
        }
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority(bundle.getString("domain"));
        Bundle bundle2 = bundle.getBundle(VideoPlayActivity.INTENT_KEY_PARAMETERS);
        for (String str : bundle2.keySet()) {
            Object obj = bundle2.get(str);
            if (obj != null) {
                builder.appendQueryParameter(str, obj.toString());
            }
        }
        return builder.build();
    }
}
