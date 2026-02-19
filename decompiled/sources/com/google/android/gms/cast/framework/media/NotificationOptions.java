package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class NotificationOptions extends zzbgl {
    public static final Parcelable.Creator<NotificationOptions> CREATOR = new zzm();
    public static final long SKIP_STEP_TEN_SECONDS_IN_MS = 10000;
    public static final long SKIP_STEP_THIRTY_SECONDS_IN_MS = 30000;
    /* access modifiers changed from: private */
    public static final List<String> zzfee = Arrays.asList(new String[]{MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK, MediaIntentReceiver.ACTION_STOP_CASTING});
    /* access modifiers changed from: private */
    public static final int[] zzfef = {0, 1};
    private final List<String> zzfeg;
    private final int[] zzfeh;
    private final long zzfei;
    private final String zzfej;
    private final int zzfek;
    private final int zzfel;
    private final int zzfem;
    private final int zzfen;
    private final int zzfeo;
    private final int zzfep;
    private final int zzfeq;
    private final int zzfer;
    private final int zzfes;
    private final int zzfet;
    private final int zzfeu;
    private final int zzfev;
    private final int zzfew;
    private final int zzfex;
    private final int zzfey;
    private final int zzfez;
    private final int zzffa;
    private final int zzffb;
    private final int zzffc;
    private final int zzffd;
    private final int zzffe;
    private final int zzfff;
    private final int zzffg;
    private final int zzffh;
    private final int zzffi;
    private final int zzffj;
    private final int zzffk;
    private final zzf zzffl;

    public static final class Builder {
        private List<String> zzfeg = NotificationOptions.zzfee;
        private int[] zzfeh = NotificationOptions.zzfef;
        private long zzfei = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        private String zzfej;
        private int zzfek = R.drawable.cast_ic_notification_small_icon;
        private int zzfel = R.drawable.cast_ic_notification_stop_live_stream;
        private int zzfem = R.drawable.cast_ic_notification_pause;
        private int zzfen = R.drawable.cast_ic_notification_play;
        private int zzfeo = R.drawable.cast_ic_notification_skip_next;
        private int zzfep = R.drawable.cast_ic_notification_skip_prev;
        private int zzfeq = R.drawable.cast_ic_notification_forward;
        private int zzfer = R.drawable.cast_ic_notification_forward10;
        private int zzfes = R.drawable.cast_ic_notification_forward30;
        private int zzfet = R.drawable.cast_ic_notification_rewind;
        private int zzfeu = R.drawable.cast_ic_notification_rewind10;
        private int zzfev = R.drawable.cast_ic_notification_rewind30;
        private int zzfew = R.drawable.cast_ic_notification_disconnect;
        private NotificationActionsProvider zzffm;

        public final NotificationOptions build() {
            NotificationActionsProvider notificationActionsProvider = this.zzffm;
            return new NotificationOptions(this.zzfeg, this.zzfeh, this.zzfei, this.zzfej, this.zzfek, this.zzfel, this.zzfem, this.zzfen, this.zzfeo, this.zzfep, this.zzfeq, this.zzfer, this.zzfes, this.zzfet, this.zzfeu, this.zzfev, this.zzfew, R.dimen.cast_notification_image_size, R.string.cast_casting_to_device, R.string.cast_stop_live_stream, R.string.cast_pause, R.string.cast_play, R.string.cast_skip_next, R.string.cast_skip_prev, R.string.cast_forward, R.string.cast_forward_10, R.string.cast_forward_30, R.string.cast_rewind, R.string.cast_rewind_10, R.string.cast_rewind_30, R.string.cast_disconnect, notificationActionsProvider == null ? null : notificationActionsProvider.zzafi().asBinder());
        }

        public final Builder setActions(List<String> list, int[] iArr) {
            if (list == null && iArr != null) {
                throw new IllegalArgumentException("When setting actions to null, you must also set compatActionIndices to null.");
            } else if (list == null || iArr != null) {
                if (list == null || iArr == null) {
                    this.zzfeg = NotificationOptions.zzfee;
                    this.zzfeh = NotificationOptions.zzfef;
                } else {
                    int size = list.size();
                    if (iArr.length <= size) {
                        for (int i : iArr) {
                            if (i < 0 || i >= size) {
                                throw new IllegalArgumentException(String.format(Locale.ROOT, "Index %d in compatActionIndices out of range: [0, %d]", new Object[]{Integer.valueOf(i), Integer.valueOf(size - 1)}));
                            }
                        }
                        this.zzfeg = new ArrayList(list);
                        this.zzfeh = Arrays.copyOf(iArr, iArr.length);
                    } else {
                        throw new IllegalArgumentException(String.format(Locale.ROOT, "Invalid number of compat actions: %d > %d.", new Object[]{Integer.valueOf(iArr.length), Integer.valueOf(size)}));
                    }
                }
                return this;
            } else {
                throw new IllegalArgumentException("When setting compatActionIndices to null, you must also set actions to null.");
            }
        }

        public final Builder setDisconnectDrawableResId(int i) {
            this.zzfew = i;
            return this;
        }

        public final Builder setForward10DrawableResId(int i) {
            this.zzfer = i;
            return this;
        }

        public final Builder setForward30DrawableResId(int i) {
            this.zzfes = i;
            return this;
        }

        public final Builder setForwardDrawableResId(int i) {
            this.zzfeq = i;
            return this;
        }

        public final Builder setNotificationActionsProvider(NotificationActionsProvider notificationActionsProvider) {
            if (notificationActionsProvider != null) {
                this.zzffm = notificationActionsProvider;
                return this;
            }
            throw new IllegalArgumentException("notificationActionsProvider cannot be null.");
        }

        public final Builder setPauseDrawableResId(int i) {
            this.zzfem = i;
            return this;
        }

        public final Builder setPlayDrawableResId(int i) {
            this.zzfen = i;
            return this;
        }

        public final Builder setRewind10DrawableResId(int i) {
            this.zzfeu = i;
            return this;
        }

        public final Builder setRewind30DrawableResId(int i) {
            this.zzfev = i;
            return this;
        }

        public final Builder setRewindDrawableResId(int i) {
            this.zzfet = i;
            return this;
        }

        public final Builder setSkipNextDrawableResId(int i) {
            this.zzfeo = i;
            return this;
        }

        public final Builder setSkipPrevDrawableResId(int i) {
            this.zzfep = i;
            return this;
        }

        public final Builder setSkipStepMs(long j) {
            zzbq.checkArgument(j > 0, "skipStepMs must be positive.");
            this.zzfei = j;
            return this;
        }

        public final Builder setSmallIconDrawableResId(int i) {
            this.zzfek = i;
            return this;
        }

        public final Builder setStopLiveStreamDrawableResId(int i) {
            this.zzfel = i;
            return this;
        }

        public final Builder setTargetActivityClassName(String str) {
            this.zzfej = str;
            return this;
        }
    }

    /* JADX WARNING: type inference failed for: r1v31, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NotificationOptions(java.util.List<java.lang.String> r7, int[] r8, long r9, java.lang.String r11, int r12, int r13, int r14, int r15, int r16, int r17, int r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, int r27, int r28, int r29, int r30, int r31, int r32, int r33, int r34, int r35, int r36, int r37, int r38, android.os.IBinder r39) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r39
            r6.<init>()
            r4 = 0
            if (r1 == 0) goto L_0x0013
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r7)
            r0.zzfeg = r5
            goto L_0x0015
        L_0x0013:
            r0.zzfeg = r4
        L_0x0015:
            if (r2 == 0) goto L_0x001f
            int r1 = r2.length
            int[] r1 = java.util.Arrays.copyOf(r8, r1)
            r0.zzfeh = r1
            goto L_0x0021
        L_0x001f:
            r0.zzfeh = r4
        L_0x0021:
            r1 = r9
            r0.zzfei = r1
            r1 = r11
            r0.zzfej = r1
            r1 = r12
            r0.zzfek = r1
            r1 = r13
            r0.zzfel = r1
            r1 = r14
            r0.zzfem = r1
            r1 = r15
            r0.zzfen = r1
            r1 = r16
            r0.zzfeo = r1
            r1 = r17
            r0.zzfep = r1
            r1 = r18
            r0.zzfeq = r1
            r1 = r19
            r0.zzfer = r1
            r1 = r20
            r0.zzfes = r1
            r1 = r21
            r0.zzfet = r1
            r1 = r22
            r0.zzfeu = r1
            r1 = r23
            r0.zzfev = r1
            r1 = r24
            r0.zzfew = r1
            r1 = r25
            r0.zzfex = r1
            r1 = r26
            r0.zzfey = r1
            r1 = r27
            r0.zzfez = r1
            r1 = r28
            r0.zzffa = r1
            r1 = r29
            r0.zzffb = r1
            r1 = r30
            r0.zzffc = r1
            r1 = r31
            r0.zzffd = r1
            r1 = r32
            r0.zzffe = r1
            r1 = r33
            r0.zzfff = r1
            r1 = r34
            r0.zzffg = r1
            r1 = r35
            r0.zzffh = r1
            r1 = r36
            r0.zzffi = r1
            r1 = r37
            r0.zzffj = r1
            r1 = r38
            r0.zzffk = r1
            if (r3 != 0) goto L_0x0092
            goto L_0x00a5
        L_0x0092:
            java.lang.String r1 = "com.google.android.gms.cast.framework.media.INotificationActionsProvider"
            android.os.IInterface r1 = r3.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.cast.framework.media.zzf
            if (r2 == 0) goto L_0x00a0
            r4 = r1
            com.google.android.gms.cast.framework.media.zzf r4 = (com.google.android.gms.cast.framework.media.zzf) r4
            goto L_0x00a5
        L_0x00a0:
            com.google.android.gms.cast.framework.media.zzh r4 = new com.google.android.gms.cast.framework.media.zzh
            r4.<init>(r3)
        L_0x00a5:
            r0.zzffl = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.NotificationOptions.<init>(java.util.List, int[], long, java.lang.String, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, android.os.IBinder):void");
    }

    public List<String> getActions() {
        return this.zzfeg;
    }

    public int getCastingToDeviceStringResId() {
        return this.zzfey;
    }

    public int[] getCompatActionIndices() {
        int[] iArr = this.zzfeh;
        return Arrays.copyOf(iArr, iArr.length);
    }

    public int getDisconnectDrawableResId() {
        return this.zzfew;
    }

    public int getForward10DrawableResId() {
        return this.zzfer;
    }

    public int getForward30DrawableResId() {
        return this.zzfes;
    }

    public int getForwardDrawableResId() {
        return this.zzfeq;
    }

    public int getPauseDrawableResId() {
        return this.zzfem;
    }

    public int getPlayDrawableResId() {
        return this.zzfen;
    }

    public int getRewind10DrawableResId() {
        return this.zzfeu;
    }

    public int getRewind30DrawableResId() {
        return this.zzfev;
    }

    public int getRewindDrawableResId() {
        return this.zzfet;
    }

    public int getSkipNextDrawableResId() {
        return this.zzfeo;
    }

    public int getSkipPrevDrawableResId() {
        return this.zzfep;
    }

    public long getSkipStepMs() {
        return this.zzfei;
    }

    public int getSmallIconDrawableResId() {
        return this.zzfek;
    }

    public int getStopLiveStreamDrawableResId() {
        return this.zzfel;
    }

    public int getStopLiveStreamTitleResId() {
        return this.zzfez;
    }

    public String getTargetActivityClassName() {
        return this.zzfej;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzb(parcel, 2, getActions(), false);
        zzbgo.zza(parcel, 3, getCompatActionIndices(), false);
        zzbgo.zza(parcel, 4, getSkipStepMs());
        zzbgo.zza(parcel, 5, getTargetActivityClassName(), false);
        zzbgo.zzc(parcel, 6, getSmallIconDrawableResId());
        zzbgo.zzc(parcel, 7, getStopLiveStreamDrawableResId());
        zzbgo.zzc(parcel, 8, getPauseDrawableResId());
        zzbgo.zzc(parcel, 9, getPlayDrawableResId());
        zzbgo.zzc(parcel, 10, getSkipNextDrawableResId());
        zzbgo.zzc(parcel, 11, getSkipPrevDrawableResId());
        zzbgo.zzc(parcel, 12, getForwardDrawableResId());
        zzbgo.zzc(parcel, 13, getForward10DrawableResId());
        zzbgo.zzc(parcel, 14, getForward30DrawableResId());
        zzbgo.zzc(parcel, 15, getRewindDrawableResId());
        zzbgo.zzc(parcel, 16, getRewind10DrawableResId());
        zzbgo.zzc(parcel, 17, getRewind30DrawableResId());
        zzbgo.zzc(parcel, 18, getDisconnectDrawableResId());
        zzbgo.zzc(parcel, 19, this.zzfex);
        zzbgo.zzc(parcel, 20, getCastingToDeviceStringResId());
        zzbgo.zzc(parcel, 21, getStopLiveStreamTitleResId());
        zzbgo.zzc(parcel, 22, this.zzffa);
        zzbgo.zzc(parcel, 23, this.zzffb);
        zzbgo.zzc(parcel, 24, this.zzffc);
        zzbgo.zzc(parcel, 25, this.zzffd);
        zzbgo.zzc(parcel, 26, this.zzffe);
        zzbgo.zzc(parcel, 27, this.zzfff);
        zzbgo.zzc(parcel, 28, this.zzffg);
        zzbgo.zzc(parcel, 29, this.zzffh);
        zzbgo.zzc(parcel, 30, this.zzffi);
        zzbgo.zzc(parcel, 31, this.zzffj);
        zzbgo.zzc(parcel, 32, this.zzffk);
        zzf zzf = this.zzffl;
        zzbgo.zza(parcel, 33, zzf == null ? null : zzf.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}
