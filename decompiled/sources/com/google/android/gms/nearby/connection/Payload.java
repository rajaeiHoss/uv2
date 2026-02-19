package com.google.android.gms.nearby.connection;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.drive.DriveFile;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

public class Payload {
    private final long id;
    private final int type;
    private final byte[] zzjwl;
    private final File zzjwm;
    private final Stream zzjwn;

    public static class File {
        private final long zziql;
        private final java.io.File zzjwo;
        private final ParcelFileDescriptor zzjwp;

        private File(java.io.File file, ParcelFileDescriptor parcelFileDescriptor, long j) {
            this.zzjwo = file;
            this.zzjwp = parcelFileDescriptor;
            this.zziql = j;
        }

        public static File zza(java.io.File file, long j) throws FileNotFoundException {
            return new File((java.io.File) zzbq.checkNotNull(file, "Cannot create Payload.File from null java.io.File."), ParcelFileDescriptor.open(file, DriveFile.MODE_READ_ONLY), j);
        }

        public static File zzb(ParcelFileDescriptor parcelFileDescriptor) {
            return new File((java.io.File) null, (ParcelFileDescriptor) zzbq.checkNotNull(parcelFileDescriptor, "Cannot create Payload.File from null ParcelFileDescriptor."), parcelFileDescriptor.getStatSize());
        }

        public java.io.File asJavaFile() {
            return this.zzjwo;
        }

        public ParcelFileDescriptor asParcelFileDescriptor() {
            return this.zzjwp;
        }

        public long getSize() {
            return this.zziql;
        }
    }

    public static class Stream {
        private final ParcelFileDescriptor zzjwp;
        private InputStream zzjwq;

        private Stream(ParcelFileDescriptor parcelFileDescriptor, InputStream inputStream) {
            this.zzjwp = parcelFileDescriptor;
            this.zzjwq = inputStream;
        }

        public static Stream zzc(ParcelFileDescriptor parcelFileDescriptor) {
            zzbq.checkNotNull(parcelFileDescriptor, "Cannot create Payload.Stream from null ParcelFileDescriptor.");
            return new Stream(parcelFileDescriptor, (InputStream) null);
        }

        public static Stream zzf(InputStream inputStream) {
            zzbq.checkNotNull(inputStream, "Cannot create Payload.Stream from null InputStream.");
            return new Stream((ParcelFileDescriptor) null, inputStream);
        }

        public InputStream asInputStream() {
            if (this.zzjwq == null) {
                this.zzjwq = new ParcelFileDescriptor.AutoCloseInputStream(this.zzjwp);
            }
            return this.zzjwq;
        }

        public ParcelFileDescriptor asParcelFileDescriptor() {
            return this.zzjwp;
        }
    }

    public @interface Type {
        public static final int BYTES = 1;
        public static final int FILE = 2;
        public static final int STREAM = 3;
    }

    private Payload(long j, int i, byte[] bArr, File file, Stream stream) {
        this.id = j;
        this.type = i;
        this.zzjwl = bArr;
        this.zzjwm = file;
        this.zzjwn = stream;
    }

    public static Payload fromBytes(byte[] bArr) {
        zzbq.checkNotNull(bArr, "Cannot create a Payload from null bytes.");
        return zza(bArr, UUID.randomUUID().getLeastSignificantBits());
    }

    public static Payload fromFile(ParcelFileDescriptor parcelFileDescriptor) {
        return zza(File.zzb(parcelFileDescriptor), UUID.randomUUID().getLeastSignificantBits());
    }

    public static Payload fromFile(java.io.File file) throws FileNotFoundException {
        return zza(File.zza(file, file.length()), UUID.randomUUID().getLeastSignificantBits());
    }

    public static Payload fromStream(ParcelFileDescriptor parcelFileDescriptor) {
        return zza(Stream.zzc(parcelFileDescriptor), UUID.randomUUID().getLeastSignificantBits());
    }

    public static Payload fromStream(InputStream inputStream) {
        return zza(Stream.zzf(inputStream), UUID.randomUUID().getLeastSignificantBits());
    }

    public static Payload zza(File file, long j) {
        return new Payload(j, 2, (byte[]) null, file, (Stream) null);
    }

    public static Payload zza(Stream stream, long j) {
        return new Payload(j, 3, (byte[]) null, (File) null, stream);
    }

    public static Payload zza(byte[] bArr, long j) {
        return new Payload(j, 1, bArr, (File) null, (Stream) null);
    }

    public byte[] asBytes() {
        return this.zzjwl;
    }

    public File asFile() {
        return this.zzjwm;
    }

    public Stream asStream() {
        return this.zzjwn;
    }

    public long getId() {
        return this.id;
    }

    public int getType() {
        return this.type;
    }
}
