package com.novell.sasl.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;
import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.auth.callback.NameCallback;
import org.apache.harmony.javax.security.auth.callback.PasswordCallback;
import org.apache.harmony.javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.harmony.javax.security.sasl.RealmCallback;
import org.apache.harmony.javax.security.sasl.RealmChoiceCallback;
import org.apache.harmony.javax.security.sasl.SaslClient;
import org.apache.harmony.javax.security.sasl.SaslException;

public class DigestMD5SaslClient implements SaslClient {
    private static final String DIGEST_METHOD = "AUTHENTICATE";
    private static final int NONCE_BYTE_COUNT = 32;
    private static final int NONCE_HEX_COUNT = 64;
    private static final int STATE_DIGEST_RESPONSE_SENT = 1;
    private static final int STATE_DISPOSED = 4;
    private static final int STATE_INITIAL = 0;
    private static final int STATE_INVALID_SERVER_RESPONSE = 3;
    private static final int STATE_VALID_SERVER_RESPONSE = 2;
    private char[] m_HA1 = null;
    private String m_authorizationId = "";
    private CallbackHandler m_cbh;
    private String m_clientNonce = "";
    private DigestChallenge m_dc;
    private String m_digestURI;
    private String m_name = "";
    private Map m_props;
    private String m_protocol = "";
    private String m_qopValue = "";
    private String m_realm = "";
    private String m_serverName = "";
    private int m_state;

    private static char getHexChar(byte b) {
        switch (b) {
            case 0:
                return '0';
            case 1:
                return '1';
            case 2:
                return '2';
            case 3:
                return '3';
            case 4:
                return '4';
            case 5:
                return '5';
            case 6:
                return '6';
            case 7:
                return '7';
            case 8:
                return '8';
            case 9:
                return '9';
            case 10:
                return 'a';
            case 11:
                return 'b';
            case 12:
                return 'c';
            case 13:
                return 'd';
            case 14:
                return 'e';
            case 15:
                return 'f';
            default:
                return 'Z';
        }
    }

    public String getMechanismName() {
        return "DIGEST-MD5";
    }

    public boolean hasInitialResponse() {
        return false;
    }

    public static SaslClient getClient(String str, String str2, String str3, Map map, CallbackHandler callbackHandler) {
        String str4 = (String) map.get("javax.security.sasl.qop");
        String str5 = (String) map.get("javax.security.sasl.strength");
        String str6 = (String) map.get("javax.security.sasl.server.authentication");
        if (str4 != null && !"auth".equals(str4)) {
            return null;
        }
        if ((str6 == null || "false".equals(str6)) && callbackHandler != null) {
            return new DigestMD5SaslClient(str, str2, str3, map, callbackHandler);
        }
        return null;
    }

    private DigestMD5SaslClient(String str, String str2, String str3, Map map, CallbackHandler callbackHandler) {
        this.m_authorizationId = str;
        this.m_protocol = str2;
        this.m_serverName = str3;
        this.m_props = map;
        this.m_cbh = callbackHandler;
        this.m_state = 0;
    }

    public boolean isComplete() {
        int i = this.m_state;
        return i == 2 || i == 3 || i == 4;
    }

    public byte[] unwrap(byte[] bArr, int i, int i2) throws SaslException {
        throw new IllegalStateException("unwrap: QOP has neither integrity nor privacy>");
    }

    public byte[] wrap(byte[] bArr, int i, int i2) throws SaslException {
        throw new IllegalStateException("wrap: QOP has neither integrity nor privacy>");
    }

    public Object getNegotiatedProperty(String str) {
        if (this.m_state != 2) {
            throw new IllegalStateException("getNegotiatedProperty: authentication exchange not complete.");
        } else if ("javax.security.sasl.qop".equals(str)) {
            return "auth";
        } else {
            return null;
        }
    }

    public void dispose() throws SaslException {
        if (this.m_state != 4) {
            this.m_state = 4;
        }
    }

    public byte[] evaluateChallenge(byte[] bArr) throws SaslException {
        int i = this.m_state;
        if (i != 0) {
            if (i != 1) {
                if (i == 2 || i == 3) {
                    throw new SaslException("Authentication sequence is complete");
                } else if (i != 4) {
                    throw new SaslException("Unknown client state.");
                } else {
                    throw new SaslException("Client has been disposed");
                }
            } else if (checkServerResponseAuth(bArr)) {
                this.m_state = 2;
                return null;
            } else {
                this.m_state = 3;
                throw new SaslException("Could not validate response-auth value from server");
            }
        } else if (bArr.length != 0) {
            try {
                byte[] bytes = createDigestResponse(bArr).getBytes("UTF-8");
                this.m_state = 1;
                return bytes;
            } catch (UnsupportedEncodingException e) {
                throw new SaslException("UTF-8 encoding not suppported by platform", e);
            }
        } else {
            throw new SaslException("response = byte[0]");
        }
    }

    /* access modifiers changed from: package-private */
    public char[] convertToHex(byte[] bArr) {
        char[] cArr = new char[32];
        for (int i = 0; i < 16; i++) {
            int i2 = i * 2;
            cArr[i2] = getHexChar((byte) ((bArr[i] & 240) >> 4));
            cArr[i2 + 1] = getHexChar((byte) (bArr[i] & 15));
        }
        return cArr;
    }

    /* access modifiers changed from: package-private */
    public char[] DigestCalcHA1(String str, String str2, String str3, String str4, String str5, String str6) throws SaslException {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str2.getBytes("UTF-8"));
            instance.update(":".getBytes("UTF-8"));
            instance.update(str3.getBytes("UTF-8"));
            instance.update(":".getBytes("UTF-8"));
            instance.update(str4.getBytes("UTF-8"));
            byte[] digest = instance.digest();
            if ("md5-sess".equals(str)) {
                instance.update(digest);
                instance.update(":".getBytes("UTF-8"));
                instance.update(str5.getBytes("UTF-8"));
                instance.update(":".getBytes("UTF-8"));
                instance.update(str6.getBytes("UTF-8"));
                digest = instance.digest();
            }
            return convertToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new SaslException("No provider found for MD5 hash", e);
        } catch (UnsupportedEncodingException e2) {
            throw new SaslException("UTF-8 encoding not supported by platform.", e2);
        }
    }

    /* access modifiers changed from: package-private */
    public char[] DigestCalcResponse(char[] cArr, String str, String str2, String str3, String str4, String str5, String str6, boolean z) throws SaslException {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            if (z) {
                instance.update(str5.getBytes("UTF-8"));
            }
            instance.update(":".getBytes("UTF-8"));
            instance.update(str6.getBytes("UTF-8"));
            if ("auth-int".equals(str4)) {
                instance.update(":".getBytes("UTF-8"));
                instance.update("00000000000000000000000000000000".getBytes("UTF-8"));
            }
            char[] convertToHex = convertToHex(instance.digest());
            instance.update(new String(cArr).getBytes("UTF-8"));
            instance.update(":".getBytes("UTF-8"));
            instance.update(str.getBytes("UTF-8"));
            instance.update(":".getBytes("UTF-8"));
            if (str4.length() > 0) {
                instance.update(str2.getBytes("UTF-8"));
                instance.update(":".getBytes("UTF-8"));
                instance.update(str3.getBytes("UTF-8"));
                instance.update(":".getBytes("UTF-8"));
                instance.update(str4.getBytes("UTF-8"));
                instance.update(":".getBytes("UTF-8"));
            }
            instance.update(new String(convertToHex).getBytes("UTF-8"));
            return convertToHex(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new SaslException("No provider found for MD5 hash", e);
        } catch (UnsupportedEncodingException e2) {
            throw new SaslException("UTF-8 encoding not supported by platform.", e2);
        }
    }

    private String createDigestResponse(byte[] bArr) throws SaslException {
        StringBuffer stringBuffer = new StringBuffer(512);
        this.m_dc = new DigestChallenge(bArr);
        this.m_digestURI = this.m_protocol + "/" + this.m_serverName;
        if ((this.m_dc.getQop() & 1) == 1) {
            this.m_qopValue = "auth";
            Callback[] callbackArr = new Callback[3];
            ArrayList realms = this.m_dc.getRealms();
            int size = realms.size();
            if (size == 0) {
                callbackArr[0] = new RealmCallback("Realm");
            } else if (size == 1) {
                callbackArr[0] = new RealmCallback("Realm", (String) realms.get(0));
            } else {
                callbackArr[0] = new RealmChoiceCallback("Realm", (String[]) realms.toArray(new String[size]), 0, false);
            }
            callbackArr[1] = new PasswordCallback("Password", false);
            String str = this.m_authorizationId;
            if (str == null || str.length() == 0) {
                callbackArr[2] = new NameCallback("Name");
            } else {
                callbackArr[2] = new NameCallback("Name", this.m_authorizationId);
            }
            try {
                this.m_cbh.handle(callbackArr);
                if (size > 1) {
                    int[] selectedIndexes = ((RealmChoiceCallback) callbackArr[0]).getSelectedIndexes();
                    if (selectedIndexes.length > 0) {
                        this.m_realm = ((RealmChoiceCallback) callbackArr[0]).getChoices()[selectedIndexes[0]];
                    } else {
                        this.m_realm = ((RealmChoiceCallback) callbackArr[0]).getChoices()[0];
                    }
                } else {
                    this.m_realm = ((RealmCallback) callbackArr[0]).getText();
                }
                this.m_clientNonce = getClientNonce();
                String name = ((NameCallback) callbackArr[2]).getName();
                this.m_name = name;
                if (name == null) {
                    this.m_name = ((NameCallback) callbackArr[2]).getDefaultName();
                }
                if (this.m_name != null) {
                    char[] DigestCalcHA1 = DigestCalcHA1(this.m_dc.getAlgorithm(), this.m_name, this.m_realm, new String(((PasswordCallback) callbackArr[1]).getPassword()), this.m_dc.getNonce(), this.m_clientNonce);
                    this.m_HA1 = DigestCalcHA1;
                    char[] DigestCalcResponse = DigestCalcResponse(DigestCalcHA1, this.m_dc.getNonce(), "00000001", this.m_clientNonce, this.m_qopValue, DIGEST_METHOD, this.m_digestURI, true);
                    stringBuffer.append("username=\"");
                    stringBuffer.append(this.m_authorizationId);
                    if (this.m_realm.length() != 0) {
                        stringBuffer.append("\",realm=\"");
                        stringBuffer.append(this.m_realm);
                    }
                    stringBuffer.append("\",cnonce=\"");
                    stringBuffer.append(this.m_clientNonce);
                    stringBuffer.append("\",nc=");
                    stringBuffer.append("00000001");
                    stringBuffer.append(",qop=");
                    stringBuffer.append(this.m_qopValue);
                    stringBuffer.append(",digest-uri=\"");
                    stringBuffer.append(this.m_digestURI);
                    stringBuffer.append("\",response=");
                    stringBuffer.append(DigestCalcResponse);
                    stringBuffer.append(",charset=utf-8,nonce=\"");
                    stringBuffer.append(this.m_dc.getNonce());
                    stringBuffer.append("\"");
                    return stringBuffer.toString();
                }
                throw new SaslException("No user name was specified.");
            } catch (UnsupportedCallbackException e) {
                throw new SaslException("Handler does not support necessary callbacks", e);
            } catch (IOException e2) {
                throw new SaslException("IO exception in CallbackHandler.", e2);
            }
        } else {
            throw new SaslException("Client only supports qop of 'auth'");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean checkServerResponseAuth(byte[] bArr) throws SaslException {
        return new String(DigestCalcResponse(this.m_HA1, this.m_dc.getNonce(), "00000001", this.m_clientNonce, this.m_qopValue, DIGEST_METHOD, this.m_digestURI, false)).equals(new ResponseAuth(bArr).getResponseValue());
    }

    /* access modifiers changed from: package-private */
    public String getClientNonce() throws SaslException {
        byte[] bArr = new byte[32];
        char[] cArr = new char[64];
        try {
            SecureRandom.getInstance("SHA1PRNG").nextBytes(bArr);
            for (int i = 0; i < 32; i++) {
                int i2 = i * 2;
                cArr[i2] = getHexChar((byte) (bArr[i] & 15));
                cArr[i2 + 1] = getHexChar((byte) ((bArr[i] & 240) >> 4));
            }
            return new String(cArr);
        } catch (NoSuchAlgorithmException e) {
            throw new SaslException("No random number generator available", e);
        }
    }
}
