package com.google.android.gms.internal;

import com.google.android.gms.cast.HlsSegmentFormat;
import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smackx.packet.CapsExtension;

final class zzeem implements zzefs {
    private static long zzmzj;
    private final zzemm zzmxz;
    private zzeeu zzmzk;
    private zzefp zzmzl;
    private zzeen zzmzm;
    private int zzmzn = zzeep.zzmzr;

    public zzeem(zzees zzees, zzeeu zzeeu, String str, zzeen zzeen, String str2) {
        long j = zzmzj;
        zzmzj = 1 + j;
        this.zzmzk = zzeeu;
        this.zzmzm = zzeen;
        zzemn zzbwk = zzees.zzbwk();
        StringBuilder sb = new StringBuilder(25);
        sb.append("conn_");
        sb.append(j);
        this.zzmxz = new zzemm(zzbwk, "Connection", sb.toString());
        this.zzmzl = new zzefp(zzees, zzeeu, str, this, str2);
    }

    private final void zza(zzeeo zzeeo) {
        if (this.zzmzn != zzeep.zzmzt) {
            if (this.zzmxz.zzcbu()) {
                this.zzmxz.zzb("closing realtime connection", (Throwable) null, new Object[0]);
            }
            this.zzmzn = zzeep.zzmzt;
            zzefp zzefp = this.zzmzl;
            if (zzefp != null) {
                zzefp.close();
                this.zzmzl = null;
            }
            this.zzmzm.zzb(zzeeo);
        }
    }

    public final void close() {
        zza(zzeeo.OTHER);
    }

    public final void open() {
        if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb("Opening a connection", (Throwable) null, new Object[0]);
        }
        this.zzmzl.open();
    }

    public final void zza(Map<String, Object> map, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("t", "d");
        hashMap.put("d", map);
        if (this.zzmzn != zzeep.zzmzs) {
            this.zzmxz.zzb("Tried to send on an unconnected connection", (Throwable) null, new Object[0]);
            return;
        }
        zzemm zzemm = this.zzmxz;
        if (z) {
            zzemm.zzb("Sending data (contents hidden)", (Throwable) null, new Object[0]);
        } else {
            zzemm.zzb("Sending data: %s", (Throwable) null, hashMap);
        }
        this.zzmzl.send(hashMap);
    }

    public final void zzai(Map<String, Object> map) {
        zzeeo closeReason;
        try {
            String str = (String) map.get("t");
            if (str == null) {
                if (this.zzmxz.zzcbu()) {
                    zzemm zzemm = this.zzmxz;
                    String valueOf = String.valueOf(map.toString());
                    zzemm.zzb(valueOf.length() != 0 ? "Failed to parse server message: missing message type:".concat(valueOf) : new String("Failed to parse server message: missing message type:"), (Throwable) null, new Object[0]);
                }
                zza(zzeeo.OTHER);
            } else if (str.equals("d")) {
                Map map2 = (Map) map.get("d");
                if (this.zzmxz.zzcbu()) {
                    zzemm zzemm2 = this.zzmxz;
                    String valueOf2 = String.valueOf(map2.toString());
                    zzemm2.zzb(valueOf2.length() != 0 ? "received data message: ".concat(valueOf2) : new String("received data message: "), (Throwable) null, new Object[0]);
                }
                this.zzmzm.zzaj(map2);
            } else if (str.equals(CapsExtension.NODE_NAME)) {
                Map map3 = (Map) map.get("d");
                if (this.zzmxz.zzcbu()) {
                    zzemm zzemm3 = this.zzmxz;
                    String valueOf3 = String.valueOf(map3.toString());
                    zzemm3.zzb(valueOf3.length() != 0 ? "Got control message: ".concat(valueOf3) : new String("Got control message: "), (Throwable) null, new Object[0]);
                }
                try {
                    String str2 = (String) map3.get("t");
                    if (str2 != null) {
                        if (str2.equals("s")) {
                            String str3 = (String) map3.get("d");
                            if (this.zzmxz.zzcbu()) {
                                this.zzmxz.zzb("Connection shutdown command received. Shutting down...", (Throwable) null, new Object[0]);
                            }
                            this.zzmzm.zzpq(str3);
                            closeReason = zzeeo.OTHER;
                        } else if (str2.equals("r")) {
                            String str4 = (String) map3.get("d");
                            if (this.zzmxz.zzcbu()) {
                                zzemm zzemm4 = this.zzmxz;
                                String host = this.zzmzk.getHost();
                                StringBuilder sb = new StringBuilder(String.valueOf(host).length() + 62 + String.valueOf(str4).length());
                                sb.append("Got a reset; killing connection to ");
                                sb.append(host);
                                sb.append("; Updating internalHost to ");
                                sb.append(str4);
                                zzemm4.zzb(sb.toString(), (Throwable) null, new Object[0]);
                            }
                            this.zzmzm.zzpp(str4);
                            closeReason = zzeeo.SERVER_RESET;
                        } else if (str2.equals("h")) {
                            Map map4 = (Map) map3.get("d");
                            long longValue = ((Long) map4.get(HlsSegmentFormat.TS)).longValue();
                            this.zzmzm.zzpp((String) map4.get("h"));
                            String str5 = (String) map4.get("s");
                            if (this.zzmzn == zzeep.zzmzr) {
                                if (this.zzmxz.zzcbu()) {
                                    this.zzmxz.zzb("realtime connection established", (Throwable) null, new Object[0]);
                                }
                                this.zzmzn = zzeep.zzmzs;
                                this.zzmzm.zzc(longValue, str5);
                                return;
                            }
                            return;
                        } else if (this.zzmxz.zzcbu()) {
                            zzemm zzemm5 = this.zzmxz;
                            String valueOf4 = String.valueOf(str2);
                            zzemm5.zzb(valueOf4.length() != 0 ? "Ignoring unknown control message: ".concat(valueOf4) : new String("Ignoring unknown control message: "), (Throwable) null, new Object[0]);
                            return;
                        } else {
                            return;
                        }
                        zza(closeReason);
                        return;
                    }
                    if (this.zzmxz.zzcbu()) {
                        zzemm zzemm6 = this.zzmxz;
                        String valueOf5 = String.valueOf(map3.toString());
                        zzemm6.zzb(valueOf5.length() != 0 ? "Got invalid control message: ".concat(valueOf5) : new String("Got invalid control message: "), (Throwable) null, new Object[0]);
                    }
                    zza(zzeeo.OTHER);
                } catch (ClassCastException e) {
                    if (this.zzmxz.zzcbu()) {
                        zzemm zzemm7 = this.zzmxz;
                        String valueOf6 = String.valueOf(e.toString());
                        zzemm7.zzb(valueOf6.length() != 0 ? "Failed to parse control message: ".concat(valueOf6) : new String("Failed to parse control message: "), (Throwable) null, new Object[0]);
                    }
                    zza(zzeeo.OTHER);
                }
            } else if (this.zzmxz.zzcbu()) {
                zzemm zzemm8 = this.zzmxz;
                String valueOf7 = String.valueOf(str);
                zzemm8.zzb(valueOf7.length() != 0 ? "Ignoring unknown server message type: ".concat(valueOf7) : new String("Ignoring unknown server message type: "), (Throwable) null, new Object[0]);
            }
        } catch (ClassCastException e2) {
            if (this.zzmxz.zzcbu()) {
                zzemm zzemm9 = this.zzmxz;
                String valueOf8 = String.valueOf(e2.toString());
                zzemm9.zzb(valueOf8.length() != 0 ? "Failed to parse server message: ".concat(valueOf8) : new String("Failed to parse server message: "), (Throwable) null, new Object[0]);
            }
            zza(zzeeo.OTHER);
        }
    }

    public final void zzcr(boolean z) {
        this.zzmzl = null;
        if (z || this.zzmzn != zzeep.zzmzr) {
            if (this.zzmxz.zzcbu()) {
                this.zzmxz.zzb("Realtime connection lost", (Throwable) null, new Object[0]);
            }
        } else if (this.zzmxz.zzcbu()) {
            this.zzmxz.zzb("Realtime connection failed", (Throwable) null, new Object[0]);
        }
        zza(zzeeo.OTHER);
    }
}
