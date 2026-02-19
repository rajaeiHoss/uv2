package com.google.android.gms.internal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@zzabh
public final class zzakz implements zzakr {
    private final String zzddt;

    public zzakz() {
        this((String) null);
    }

    public zzakz(String str) {
        this.zzddt = str;
    }

    public final void zzcp(String str) {
        String sb;
        String message;
        StringBuilder sb2;
        HttpURLConnection httpURLConnection = null;
        try {
            String valueOf = String.valueOf(str);
            zzaky.zzby(valueOf.length() != 0 ? "Pinging URL: ".concat(valueOf) : new String("Pinging URL: "));
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            zzlc.zzij();
            zzako.zza(true, httpURLConnection, this.zzddt);
            zzaks zzaks = new zzaks();
            zzaks.zza(httpURLConnection, (byte[]) null);
            int responseCode = httpURLConnection.getResponseCode();
            zzaks.zza(httpURLConnection, responseCode);
            if (responseCode < 200 || responseCode >= 300) {
                StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 65);
                sb3.append("Received non-success response code ");
                sb3.append(responseCode);
                sb3.append(" from pinging URL: ");
                sb3.append(str);
                zzaky.zzcz(sb3.toString());
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e) {
            String message2 = e.getMessage();
            StringBuilder sb4 = new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(message2).length());
            sb4.append("Error while parsing ping URL: ");
            sb4.append(str);
            sb4.append(". ");
            sb4.append(message2);
            sb = sb4.toString();
            zzaky.zzcz(sb);
        } catch (IOException e2) {
            message = e2.getMessage();
            sb2 = new StringBuilder(String.valueOf(str).length() + 27 + String.valueOf(message).length());
            sb2.append("Error while pinging URL: ");
            sb2.append(str);
            sb2.append(". ");
            sb2.append(message);
            sb = sb2.toString();
            zzaky.zzcz(sb);
        } catch (RuntimeException e3) {
            message = e3.getMessage();
            sb2 = new StringBuilder(String.valueOf(str).length() + 27 + String.valueOf(message).length());
            sb2.append("Error while pinging URL: ");
            sb2.append(str);
            sb2.append(". ");
            sb2.append(message);
            sb = sb2.toString();
            zzaky.zzcz(sb);
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
