package com.google.android.gms.nearby.messages.audio;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.nearby.messages.Message;
import java.util.Arrays;

public final class AudioBytes {
    public static final int MAX_SIZE = 10;
    private final byte[] zzkbt;

    public AudioBytes(byte[] bArr) {
        zzbq.checkNotNull(bArr);
        boolean z = true;
        zzbq.checkArgument(bArr.length <= 10, "Given byte array longer than 10 bytes, given by AudioBytes.MAX_SIZE.");
        zzbq.checkArgument(bArr.length <= 0 ? false : z, "Given byte array is of zero length.");
        this.zzkbt = bArr;
    }

    public static AudioBytes from(Message message) {
        zzbq.checkNotNull(message);
        boolean zzkx = message.zzkx(Message.MESSAGE_TYPE_AUDIO_BYTES);
        String type = message.getType();
        StringBuilder sb = new StringBuilder(String.valueOf(type).length() + 56);
        sb.append("Message type '");
        sb.append(type);
        sb.append("' is not Message.MESSAGE_TYPE_AUDIO_BYTES.");
        zzbq.checkArgument(zzkx, sb.toString());
        return new AudioBytes(message.getContent());
    }

    public final byte[] getBytes() {
        return this.zzkbt;
    }

    public final Message toMessage() {
        return new Message(this.zzkbt, Message.MESSAGE_NAMESPACE_RESERVED, Message.MESSAGE_TYPE_AUDIO_BYTES);
    }

    public final String toString() {
        String arrays = Arrays.toString(this.zzkbt);
        StringBuilder sb = new StringBuilder(String.valueOf(arrays).length() + 14);
        sb.append("AudioBytes [");
        sb.append(arrays);
        sb.append(" ]");
        return sb.toString();
    }
}
