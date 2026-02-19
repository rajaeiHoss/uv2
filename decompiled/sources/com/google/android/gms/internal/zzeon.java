package com.google.android.gms.internal;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

final class zzeon {
    private zzeoj zznpx = null;
    private DataInputStream zznqs = null;
    private zzeoe zznqt = null;
    private byte[] zznqu = new byte[112];
    private zzenz zznqv;
    private volatile boolean zznqw = false;

    zzeon(zzeoe zzeoe) {
        this.zznqt = zzeoe;
    }

    private final int read(byte[] bArr, int i, int i2) throws IOException {
        this.zznqs.readFully(bArr, i, i2);
        return i2;
    }

    private final void zzc(zzeok zzeok) {
        this.zznqw = true;
        this.zznqt.zzb(zzeok);
    }

    /* access modifiers changed from: package-private */
    public final void run() {
        this.zznpx = this.zznqt.zzcdc();
        while (!this.zznqw) {
            try {
                int read = read(this.zznqu, 0, 1) + 0;
                byte[] bArr = this.zznqu;
                boolean z = (bArr[0] & ByteCompanionObject.MIN_VALUE) != 0;
                if (!((bArr[0] & 112) != 0)) {
                    byte b = (byte) (bArr[0] & 15);
                    int read2 = read + read(bArr, read, 1);
                    byte[] bArr2 = this.zznqu;
                    byte b2 = bArr2[1];
                    long j = 0;
                    if (b2 < 126) {
                        j = (long) b2;
                    } else if (b2 == 126) {
                        read(bArr2, read2, 2);
                        byte[] bArr3 = this.zznqu;
                        j = (long) ((bArr3[3] & UByte.MAX_VALUE) | ((bArr3[2] & UByte.MAX_VALUE) << 8));
                    } else if (b2 == Byte.MAX_VALUE) {
                        int read3 = read2 + read(bArr2, read2, 8);
                        byte[] bArr4 = this.zznqu;
                        int i = read3 - 8;
                        j = (((long) bArr4[i]) << 56) + (((long) (bArr4[i + 1] & UByte.MAX_VALUE)) << 48) + (((long) (bArr4[i + 2] & UByte.MAX_VALUE)) << 40) + (((long) (bArr4[i + 3] & UByte.MAX_VALUE)) << 32) + (((long) (bArr4[i + 4] & UByte.MAX_VALUE)) << 24) + ((long) ((bArr4[i + 5] & UByte.MAX_VALUE) << 16)) + ((long) ((bArr4[i + 6] & UByte.MAX_VALUE) << 8)) + ((long) (bArr4[i + 7] & UByte.MAX_VALUE));
                    }
                    int i2 = (int) j;
                    byte[] bArr5 = new byte[i2];
                    read(bArr5, 0, i2);
                    if (b == 8) {
                        this.zznqt.zzcdd();
                    } else if (b == 10) {
                        continue;
                    } else {
                        if (!(b == 1 || b == 2 || b == 9)) {
                            if (b != 0) {
                                StringBuilder sb = new StringBuilder(24);
                                sb.append("Unsupported opcode: ");
                                sb.append(b);
                                throw new zzeok(sb.toString());
                            }
                        }
                        if (b != 9) {
                            zzenz zzenz = this.zznqv;
                            if (zzenz != null) {
                                if (b != 0) {
                                    throw new zzeok("Failed to continue outstanding frame");
                                }
                            }
                            if (zzenz == null) {
                                if (b == 0) {
                                    throw new zzeok("Received continuing frame, but there's nothing to continue");
                                }
                            }
                            if (zzenz == null) {
                                this.zznqv = b == 2 ? new zzeny() : new zzeoa();
                            }
                            if (!this.zznqv.zzao(bArr5)) {
                                throw new zzeok("Failed to decode frame");
                            } else if (z) {
                                zzeom zzcda = this.zznqv.zzcda();
                                this.zznqv = null;
                                if (zzcda != null) {
                                    this.zznpx.zza(zzcda);
                                } else {
                                    throw new zzeok("Failed to decode whole message");
                                }
                            } else {
                                continue;
                            }
                        } else if (!z) {
                            throw new zzeok("PING must not fragment across frames");
                        } else if (i2 <= 125) {
                            this.zznqt.zzaq(bArr5);
                        } else {
                            throw new zzeok("PING frame too long");
                        }
                    }
                } else {
                    throw new zzeok("Invalid frame received");
                }
            } catch (SocketTimeoutException unused) {
            } catch (IOException e) {
                zzc(new zzeok("IO Error", e));
            } catch (zzeok e2) {
                zzc(e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(DataInputStream dataInputStream) {
        this.zznqs = dataInputStream;
    }

    /* access modifiers changed from: package-private */
    public final void zzcdj() {
        this.zznqw = true;
    }
}
