package com.google.android.gms.internal;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzfkq {
    private static final Logger logger = Logger.getLogger(zzfkq.class.getName());
    private static final Unsafe zzmdw;
    private static final Class<?> zzpnt = zzfgo.zzcxn();
    private static final boolean zzpop = zzdcg();
    private static final boolean zzptd;
    private static final boolean zzpte;
    private static final zzd zzptf;
    private static final boolean zzptg = zzdch();
    private static final long zzpth = ((long) zzn(byte[].class));
    private static final long zzpti;
    private static final long zzptj;
    private static final long zzptk;
    private static final long zzptl;
    private static final long zzptm;
    private static final long zzptn;
    private static final long zzpto;
    private static final long zzptp;
    private static final long zzptq;
    private static final long zzptr;
    private static final long zzpts = ((long) zzn(Object[].class));
    private static final long zzptt = ((long) zzo(Object[].class));
    private static final long zzptu;
    /* access modifiers changed from: private */
    public static final boolean zzptv = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzfkq.zzptv) {
                zzfkq.zza(obj, j, b);
            } else {
                zzfkq.zzb(obj, j, b);
            }
        }

        public final byte zzf(Object obj, long j) {
            return zzfkq.zzptv ? zzfkq.zzb(obj, j) : zzfkq.zzc(obj, j);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzfkq.zzptv) {
                zzfkq.zza(obj, j, b);
            } else {
                zzfkq.zzb(obj, j, b);
            }
        }

        public final byte zzf(Object obj, long j) {
            return zzfkq.zzptv ? zzfkq.zzb(obj, j) : zzfkq.zzc(obj, j);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzptw.putByte(obj, j, b);
        }

        public final byte zzf(Object obj, long j) {
            return this.zzptw.getByte(obj, j);
        }
    }

    static abstract class zzd {
        Unsafe zzptw;

        zzd(Unsafe unsafe) {
            this.zzptw = unsafe;
        }

        public final int zza(Object obj, long j) {
            return this.zzptw.getInt(obj, j);
        }

        public abstract void zze(Object obj, long j, byte b);

        public abstract byte zzf(Object obj, long j);
    }

    static {
        Field field;
        Class<double[]> cls = double[].class;
        Class<float[]> cls2 = float[].class;
        Class<long[]> cls3 = long[].class;
        Class<int[]> cls4 = int[].class;
        Class<boolean[]> cls5 = boolean[].class;
        Unsafe zzdcf = zzdcf();
        zzmdw = zzdcf;
        boolean zzp = zzp(Long.TYPE);
        zzptd = zzp;
        boolean zzp2 = zzp(Integer.TYPE);
        zzpte = zzp2;
        zzd zzd2 = null;
        if (zzdcf != null) {
            if (!zzfgo.zzcxm()) {
                zzd2 = new zzc(zzdcf);
            } else if (zzp) {
                zzd2 = new zzb(zzdcf);
            } else if (zzp2) {
                zzd2 = new zza(zzdcf);
            }
        }
        zzptf = zzd2;
        zzpti = (long) zzn(cls5);
        zzptj = (long) zzo(cls5);
        zzptk = (long) zzn(cls4);
        zzptl = (long) zzo(cls4);
        zzptm = (long) zzn(cls3);
        zzptn = (long) zzo(cls3);
        zzpto = (long) zzn(cls2);
        zzptp = (long) zzo(cls2);
        zzptq = (long) zzn(cls);
        zzptr = (long) zzo(cls);
        if (!zzfgo.zzcxm() || (field = zza((Class<?>) Buffer.class, "effectiveDirectAddress")) == null) {
            field = zza((Class<?>) Buffer.class, "address");
        }
        zzptu = (field == null || zzd2 == null) ? -1 : zzd2.zzptw.objectFieldOffset(field);
    }

    private zzfkq() {
    }

    static int zza(Object obj, long j) {
        return zzptf.zza(obj, j);
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int zza2 = zza(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zza(obj, j2, ((255 & b) << i) | (zza2 & (~(255 << i))));
    }

    private static void zza(Object obj, long j, int i) {
        zzptf.zzptw.putInt(obj, j, i);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzptf.zze(bArr, zzpth + j, b);
    }

    /* access modifiers changed from: private */
    public static byte zzb(Object obj, long j) {
        return (byte) (zza(obj, -4 & j) >>> ((int) (((~j) & 3) << 3)));
    }

    static byte zzb(byte[] bArr, long j) {
        return zzptf.zzf(bArr, zzpth + j);
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        zza(obj, j2, ((255 & b) << i) | (zza(obj, j2) & (~(255 << i))));
    }

    /* access modifiers changed from: private */
    public static byte zzc(Object obj, long j) {
        return (byte) (zza(obj, -4 & j) >>> ((int) ((j & 3) << 3)));
    }

    static boolean zzdcd() {
        return zzpop;
    }

    static boolean zzdce() {
        return zzptg;
    }

    private static Unsafe zzdcf() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzfkr());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzdcg() {
        Unsafe unsafe = zzmdw;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzfgo.zzcxm()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzdch() {
        Unsafe unsafe = zzmdw;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzfgo.zzcxm()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static int zzn(Class<?> cls) {
        if (zzpop) {
            return zzptf.zzptw.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzo(Class<?> cls) {
        if (zzpop) {
            return zzptf.zzptw.arrayIndexScale(cls);
        }
        return -1;
    }

    private static boolean zzp(Class<?> cls) {
        Class<byte[]> cls2 = byte[].class;
        if (!zzfgo.zzcxm()) {
            return false;
        }
        try {
            Class<?> cls3 = zzpnt;
            cls3.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls3.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls3.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls3.getMethod("peekByte", new Class[]{cls});
            cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            cls3.getMethod("peekByteArray", new Class[]{cls, cls2, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
