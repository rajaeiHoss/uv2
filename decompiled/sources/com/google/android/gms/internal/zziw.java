package com.google.android.gms.internal;

import com.google.android.gms.internal.zzfhu;
import java.io.IOException;
import java.util.Objects;

public final class zziw {

    public static final class zza extends zzfhu<zziw.zza, zziw.zza.C0001zza> implements zzfje {
        /* access modifiers changed from: private */
        public static final zziw.zza zzbbl;
        private static volatile zzfjl<zziw.zza> zzbbm;

        /* renamed from: com.google.android.gms.internal.zziw$zza$zza  reason: collision with other inner class name */
        public static final class C0001zza extends zzfhu.zza<zziw.zza, C0001zza> implements zzfje {
            private C0001zza() {
                super(zziw.zza.zzbbl);
            }

            /* synthetic */ C0001zza(zzix zzix) {
                this();
            }
        }

        public enum zzb implements zzfia {
            UNKNOWN_EVENT_TYPE(0),
            AD_REQUEST(1),
            AD_LOADED(2),
            AD_FAILED_TO_LOAD(3),
            AD_FAILED_TO_LOAD_NO_FILL(4),
            AD_IMPRESSION(5),
            AD_FIRST_CLICK(6),
            AD_SUBSEQUENT_CLICK(7),
            REQUEST_WILL_START(8),
            REQUEST_DID_END(9),
            REQUEST_WILL_UPDATE_SIGNALS(10),
            REQUEST_DID_UPDATE_SIGNALS(11),
            REQUEST_WILL_BUILD_URL(12),
            REQUEST_DID_BUILD_URL(13),
            REQUEST_WILL_MAKE_NETWORK_REQUEST(14),
            REQUEST_DID_RECEIVE_NETWORK_RESPONSE(15),
            REQUEST_WILL_PROCESS_RESPONSE(16),
            REQUEST_DID_PROCESS_RESPONSE(17),
            REQUEST_WILL_RENDER(18),
            REQUEST_DID_RENDER(19),
            REQUEST_WILL_UPDATE_GMS_SIGNALS(1000),
            REQUEST_DID_UPDATE_GMS_SIGNALS(1001),
            REQUEST_FAILED_TO_UPDATE_GMS_SIGNALS(1002),
            REQUEST_FAILED_TO_BUILD_URL(1003),
            REQUEST_FAILED_TO_MAKE_NETWORK_REQUEST(1004),
            REQUEST_FAILED_TO_PROCESS_RESPONSE(1005);
            
            private static final zzfib<zzb> zzbcn = new zziy();
            private final int value;

            private zzb(int i) {
                this.value = i;
            }

            public final int zzhu() {
                return this.value;
            }
        }

        static {
            zziw.zza zza = new zziw.zza();
            zzbbl = zza;
            zza.zza(zzfhu.zzg.zzppw, (Object) null, (Object) null);
            zza.zzpph.zzbkr();
        }

        private zza() {
        }

        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            switch (zzix.zzbbk[i - 1]) {
                case 1:
                    return new zziw.zza();
                case 2:
                    return zziw.zza.zzbbl;
                case 3:
                    return null;
                case 4:
                    return new C0001zza((zzix) null);
                case 5:
                    return this;
                case 6:
                    zzfhb zzfhb = (zzfhb) obj;
                    Objects.requireNonNull((zzfhm) obj2);
                    boolean z = false;
                    while (!z) {
                        try {
                            int zzcxx = zzfhb.zzcxx();
                            if (zzcxx == 0 || !zza(zzcxx, zzfhb)) {
                                z = true;
                            }
                        } catch (zzfie e) {
                            throw new RuntimeException(e.zzi(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new zzfie(e2.getMessage()).zzi(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case 8:
                    if (zzbbm == null) {
                        synchronized (zziw.zza.class) {
                            if (zzbbm == null) {
                                zzbbm = new zzfhu.zzb(zzbbl);
                            }
                        }
                    }
                    return zzbbm;
                case 9:
                    return (byte) 1;
                case 10:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
            return zzbbl;
        }

        public final void zza(zzfhg zzfhg) throws IOException {
            this.zzpph.zza(zzfhg);
        }

        public final int zzhs() {
            int i = this.zzppi;
            if (i != -1) {
                return i;
            }
            int zzhs = this.zzpph.zzhs() + 0;
            this.zzppi = zzhs;
            return zzhs;
        }
    }
}
