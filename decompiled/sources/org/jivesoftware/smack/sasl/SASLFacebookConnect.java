package org.jivesoftware.smack.sasl;

import com.google.firebase.analytics.FirebaseAnalytics;
import de.measite.smack.Sasl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.Base64;

public class SASLFacebookConnect extends SASLMechanism {
    private String apiKey = "";
    private String sessionKey = "";
    private String sessionSecret = "";

    /* access modifiers changed from: protected */
    public String getName() {
        return "X-FACEBOOK-PLATFORM";
    }

    static {
        SASLAuthentication.registerSASLMechanism("X-FACEBOOK-PLATFORM", SASLFacebookConnect.class);
        SASLAuthentication.supportSASLMechanism("X-FACEBOOK-PLATFORM", 0);
    }

    public SASLFacebookConnect(SASLAuthentication sASLAuthentication) {
        super(sASLAuthentication);
    }

    /* access modifiers changed from: protected */
    public void authenticate() throws IOException, XMPPException {
        final StringBuilder sb = new StringBuilder();
        sb.append("<auth mechanism=\"");
        sb.append(getName());
        sb.append("\" xmlns=\"urn:ietf:params:xml:ns:xmpp-sasl\">");
        sb.append("</auth>");
        getSASLAuthentication().send(new Packet() {
            public String toXML() {
                return sb.toString();
            }
        });
    }

    public void authenticate(String str, String str2, String str3) throws IOException, XMPPException {
        if (str == null || str3 == null) {
            throw new IllegalStateException("Invalid parameters!");
        }
        String[] split = str.split("\\|");
        if (split == null || split.length != 2) {
            throw new IllegalStateException("Api key or session key is not present!");
        }
        this.apiKey = split[0];
        String str4 = split[1];
        this.sessionKey = str4;
        this.sessionSecret = str3;
        this.authenticationId = str4;
        this.password = str3;
        this.hostname = str2;
        this.sc = Sasl.createSaslClient(new String[]{"DIGEST-MD5"}, (String) null, "xmpp", str2, new HashMap(), this);
        authenticate();
    }

    public void authenticate(String str, String str2, CallbackHandler callbackHandler) throws IOException, XMPPException {
        this.sc = Sasl.createSaslClient(new String[]{"DIGEST-MD5"}, (String) null, "xmpp", str2, new HashMap(), callbackHandler);
        authenticate();
    }

    public void challengeReceived(String str) throws IOException {
        byte[] bArr;
        final StringBuilder sb = new StringBuilder();
        if (str != null) {
            Map<String, String> queryMap = getQueryMap(new String(Base64.decode(str)));
            String str2 = queryMap.get("nonce");
            String str3 = queryMap.get(FirebaseAnalytics.Param.METHOD);
            Long valueOf = Long.valueOf(new GregorianCalendar().getTimeInMillis() / 1000);
            try {
                String MD5 = MD5("api_key=" + this.apiKey + "call_id=" + valueOf + "method=" + str3 + "nonce=" + str2 + "session_key=" + this.sessionKey + "v=" + "1.0" + this.sessionSecret);
                bArr = ("api_key=" + this.apiKey + "&" + "call_id=" + valueOf + "&" + "method=" + str3 + "&" + "nonce=" + str2 + "&" + "session_key=" + this.sessionKey + "&" + "v=" + "1.0" + "&" + "sig=" + MD5).getBytes();
            } catch (NoSuchAlgorithmException e) {
                throw new IllegalStateException(e);
            }
        } else {
            bArr = null;
        }
        String encodeBytes = bArr != null ? Base64.encodeBytes(bArr, 8) : "";
        sb.append("<response xmlns=\"urn:ietf:params:xml:ns:xmpp-sasl\">");
        sb.append(encodeBytes);
        sb.append("</response>");
        getSASLAuthentication().send(new Packet() {
            public String toXML() {
                return sb.toString();
            }
        });
    }

    private Map<String, String> getQueryMap(String str) {
        String[] split = str.split("&");
        HashMap hashMap = new HashMap();
        for (String str2 : split) {
            hashMap.put(str2.split("=")[0], str2.split("=")[1]);
        }
        return hashMap;
    }

    private String convertToHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            int b = (bArr[i] >>> 4) & 15;
            int i2 = 0;
            while (true) {
                if (b < 0 || b > 9) {
                    stringBuffer.append((char) ((b - 10) + 97));
                } else {
                    stringBuffer.append((char) (b + 48));
                }
                b = bArr[i] & 15;
                int i3 = i2 + 1;
                if (i2 >= 1) {
                    break;
                }
                i2 = i3;
            }
        }
        return stringBuffer.toString();
    }

    public String MD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(str.getBytes("iso-8859-1"), 0, str.length());
        return convertToHex(instance.digest());
    }
}
