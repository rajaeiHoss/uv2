package com.google.android.gms.internal;

import android.util.Base64;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

final class zzeol {
    private String protocol = null;
    private URI zznpy = null;
    private String zznqn = null;
    private Map<String, String> zznqo = null;

    public zzeol(URI uri, String str, Map<String, String> map) {
        this.zznpy = uri;
        this.protocol = null;
        this.zznqo = map;
        this.zznqn = zzcdi();
    }

    private static String zza(LinkedHashMap<String, String> linkedHashMap) {
        String str = new String();
        for (String next : linkedHashMap.keySet()) {
            String valueOf = String.valueOf(str);
            String str2 = linkedHashMap.get(next);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 4 + String.valueOf(next).length() + String.valueOf(str2).length());
            sb.append(valueOf);
            sb.append(next);
            sb.append(": ");
            sb.append(str2);
            sb.append("\r\n");
            str = sb.toString();
        }
        return str;
    }

    private static String zzcdi() {
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) ((int) ((Math.random() * 255.0d) + FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
        }
        return Base64.encodeToString(bArr, 2);
    }

    public final byte[] zzcdh() {
        String str;
        String path = this.zznpy.getPath();
        String query = this.zznpy.getQuery();
        String valueOf = String.valueOf(path);
        if (query == null) {
            str = "";
        } else {
            String valueOf2 = String.valueOf(query);
            str = valueOf2.length() != 0 ? "?".concat(valueOf2) : new String("?");
        }
        String valueOf3 = String.valueOf(str);
        String concat = valueOf3.length() != 0 ? valueOf.concat(valueOf3) : new String(valueOf);
        String host = this.zznpy.getHost();
        if (this.zznpy.getPort() != -1) {
            String valueOf4 = String.valueOf(host);
            int port = this.zznpy.getPort();
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf4).length() + 12);
            sb.append(valueOf4);
            sb.append(":");
            sb.append(port);
            host = sb.toString();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("Host", host);
        linkedHashMap.put("Upgrade", "websocket");
        linkedHashMap.put("Connection", "Upgrade");
        linkedHashMap.put("Sec-WebSocket-Version", "13");
        linkedHashMap.put("Sec-WebSocket-Key", this.zznqn);
        String str2 = this.protocol;
        if (str2 != null) {
            linkedHashMap.put("Sec-WebSocket-Protocol", str2);
        }
        Map<String, String> map = this.zznqo;
        if (map != null) {
            for (String next : map.keySet()) {
                if (!linkedHashMap.containsKey(next)) {
                    linkedHashMap.put(next, this.zznqo.get(next));
                }
            }
        }
        StringBuilder sb2 = new StringBuilder(String.valueOf(concat).length() + 15);
        sb2.append("GET ");
        sb2.append(concat);
        sb2.append(" HTTP/1.1\r\n");
        String valueOf5 = String.valueOf(sb2.toString());
        String valueOf6 = String.valueOf(zza(linkedHashMap));
        String concat2 = String.valueOf(valueOf6.length() != 0 ? valueOf5.concat(valueOf6) : new String(valueOf5)).concat("\r\n");
        byte[] bArr = new byte[concat2.getBytes().length];
        System.arraycopy(concat2.getBytes(), 0, bArr, 0, concat2.getBytes().length);
        return bArr;
    }
}
