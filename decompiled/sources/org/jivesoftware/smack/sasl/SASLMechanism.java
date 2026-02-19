package org.jivesoftware.smack.sasl;

import de.measite.smack.Sasl;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.auth.callback.NameCallback;
import org.apache.harmony.javax.security.auth.callback.PasswordCallback;
import org.apache.harmony.javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.harmony.javax.security.sasl.RealmCallback;
import org.apache.harmony.javax.security.sasl.RealmChoiceCallback;
import org.apache.harmony.javax.security.sasl.SaslClient;
import org.apache.harmony.javax.security.sasl.SaslException;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.Base64;

public abstract class SASLMechanism implements CallbackHandler {
    protected String authenticationId;
    protected String hostname;
    protected String password;
    private SASLAuthentication saslAuthentication;
    protected SaslClient sc;

    /* access modifiers changed from: protected */
    public abstract String getName();

    public SASLMechanism(SASLAuthentication sASLAuthentication) {
        this.saslAuthentication = sASLAuthentication;
    }

    public void authenticate(String str, String str2, String str3) throws IOException, XMPPException {
        this.authenticationId = str;
        this.password = str3;
        this.hostname = str2;
        this.sc = Sasl.createSaslClient(new String[]{getName()}, str, "xmpp", str2, new HashMap(), this);
        authenticate();
    }

    public void authenticate(String str, String str2, CallbackHandler callbackHandler) throws IOException, XMPPException {
        this.sc = Sasl.createSaslClient(new String[]{getName()}, str, "xmpp", str2, new HashMap(), callbackHandler);
        authenticate();
    }

    /* access modifiers changed from: protected */
    public void authenticate() throws IOException, XMPPException {
        try {
            getSASLAuthentication().send(new AuthMechanism(getName(), this.sc.hasInitialResponse() ? Base64.encodeBytes(this.sc.evaluateChallenge(new byte[0]), 8) : null));
        } catch (SaslException e) {
            throw new XMPPException("SASL authentication failed", (Throwable) e);
        }
    }

    public void challengeReceived(String str) throws IOException {
        byte[] bArr;
        Response response;
        if (str != null) {
            bArr = this.sc.evaluateChallenge(Base64.decode(str));
        } else {
            bArr = this.sc.evaluateChallenge(new byte[0]);
        }
        if (bArr == null) {
            response = new Response();
        } else {
            response = new Response(Base64.encodeBytes(bArr, 8));
        }
        getSASLAuthentication().send(response);
    }

    /* access modifiers changed from: protected */
    public SASLAuthentication getSASLAuthentication() {
        return this.saslAuthentication;
    }

    public void handle(Callback[] callbackArr) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbackArr.length; i++) {
            if (callbackArr[i] instanceof NameCallback) {
                ((NameCallback) callbackArr[i]).setName(this.authenticationId);
            } else if (callbackArr[i] instanceof PasswordCallback) {
                ((PasswordCallback) callbackArr[i]).setPassword(this.password.toCharArray());
            } else if (callbackArr[i] instanceof RealmCallback) {
                ((RealmCallback) callbackArr[i]).setText(this.hostname);
            } else if (!(callbackArr[i] instanceof RealmChoiceCallback)) {
                throw new UnsupportedCallbackException(callbackArr[i]);
            }
        }
    }

    public class AuthMechanism extends Packet {
        private final String authenticationText;
        private final String name;

        public AuthMechanism(String str, String str2) {
            Objects.requireNonNull(str, "SASL mechanism name shouldn't be null.");
            this.name = str;
            this.authenticationText = str2;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<auth mechanism=\"");
            sb.append(this.name);
            sb.append("\" xmlns=\"urn:ietf:params:xml:ns:xmpp-sasl\">");
            String str = this.authenticationText;
            if (str != null && str.trim().length() > 0) {
                sb.append(this.authenticationText);
            }
            sb.append("</auth>");
            return sb.toString();
        }
    }

    public static class Challenge extends Packet {
        private final String data;

        public Challenge(String str) {
            this.data = str;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<challenge xmlns=\"urn:ietf:params:xml:ns:xmpp-sasl\">");
            String str = this.data;
            if (str != null && str.trim().length() > 0) {
                sb.append(this.data);
            }
            sb.append("</challenge>");
            return sb.toString();
        }
    }

    public class Response extends Packet {
        private final String authenticationText;

        public Response() {
            this.authenticationText = null;
        }

        public Response(String str) {
            if (str == null || str.trim().length() == 0) {
                this.authenticationText = null;
            } else {
                this.authenticationText = str;
            }
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<response xmlns=\"urn:ietf:params:xml:ns:xmpp-sasl\">");
            String str = this.authenticationText;
            if (str != null) {
                sb.append(str);
            } else {
                sb.append("=");
            }
            sb.append("</response>");
            return sb.toString();
        }
    }

    public static class Success extends Packet {
        private final String data;

        public Success(String str) {
            this.data = str;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<success xmlns=\"urn:ietf:params:xml:ns:xmpp-sasl\">");
            String str = this.data;
            if (str != null && str.trim().length() > 0) {
                sb.append(this.data);
            }
            sb.append("</success>");
            return sb.toString();
        }
    }

    public static class Failure extends Packet {
        private final String condition;

        public Failure(String str) {
            this.condition = str;
        }

        public String getCondition() {
            return this.condition;
        }

        public String toXML() {
            StringBuilder sb = new StringBuilder();
            sb.append("<failure xmlns=\"urn:ietf:params:xml:ns:xmpp-sasl\">");
            String str = this.condition;
            if (str != null && str.trim().length() > 0) {
                sb.append("<");
                sb.append(this.condition);
                sb.append("/>");
            }
            sb.append("</failure>");
            return sb.toString();
        }
    }
}
