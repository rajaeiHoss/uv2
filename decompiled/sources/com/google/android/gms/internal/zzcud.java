package com.google.android.gms.internal;

import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.nearby.connection.Payload;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class zzcud {
    static Pair<zzcub, Pair<ParcelFileDescriptor, ParcelFileDescriptor>> zza(Payload payload) throws IOException {
        int type = payload.getType();
        if (type == 1) {
            return Pair.create(new zzcub(payload.getId(), payload.getType(), payload.asBytes(), (ParcelFileDescriptor) null, (String) null, -1, (ParcelFileDescriptor) null), (Pair<ParcelFileDescriptor, ParcelFileDescriptor>) null);
        }
        if (type == 2) {
            return Pair.create(new zzcub(payload.getId(), payload.getType(), (byte[]) null, payload.asFile().asParcelFileDescriptor(), payload.asFile().asJavaFile() == null ? null : payload.asFile().asJavaFile().getAbsolutePath(), payload.asFile().getSize(), (ParcelFileDescriptor) null), (Pair<ParcelFileDescriptor, ParcelFileDescriptor>) null);
        } else if (type == 3) {
            try {
                ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
                ParcelFileDescriptor[] createPipe2 = ParcelFileDescriptor.createPipe();
                return Pair.create(new zzcub(payload.getId(), payload.getType(), (byte[]) null, createPipe[0], (String) null, -1, createPipe2[0]), Pair.create(createPipe[1], createPipe2[1]));
            } catch (IOException e) {
                Log.e("NearbyConnections", String.format("Unable to create PFD pipe for streaming payload %d from client to service.", new Object[]{Long.valueOf(payload.getId())}), e);
                throw e;
            }
        } else {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException(String.format("Outgoing Payload %d has unknown type %d", new Object[]{Long.valueOf(payload.getId()), Integer.valueOf(payload.getType())}));
            Log.wtf("NearbyConnections", "Unknown payload type!", illegalArgumentException);
            throw illegalArgumentException;
        }
    }

    static Payload zza(zzcub zzcub) {
        long id = zzcub.getId();
        int type = zzcub.getType();
        if (type == 1) {
            return Payload.zza(zzcub.getBytes(), id);
        }
        if (type == 2) {
            String zzbdo = zzcub.zzbdo();
            if (zzbdo != null) {
                try {
                    return Payload.zza(Payload.File.zza(new File(zzbdo), zzcub.zzbdp()), id);
                } catch (FileNotFoundException e) {
                    String valueOf = String.valueOf(zzbdo);
                    Log.w("NearbyConnections", valueOf.length() != 0 ? "Failed to create Payload from ParcelablePayload: Java file not found at ".concat(valueOf) : new String("Failed to create Payload from ParcelablePayload: Java file not found at "), e);
                }
            }
            return Payload.zza(Payload.File.zzb(zzcub.zzbdn()), id);
        } else if (type == 3) {
            return Payload.zza(Payload.Stream.zzc(zzcub.zzbdn()), id);
        } else {
            Log.w("NearbyConnections", String.format("Incoming ParcelablePayload %d has unknown type %d", new Object[]{Long.valueOf(zzcub.getId()), Integer.valueOf(zzcub.getType())}));
            return null;
        }
    }
}
