package com.kenai.jbosh;

import com.google.android.gms.wallet.WalletConstants;
import java.util.HashMap;
import java.util.Map;

final class TerminalBindingCondition {
    static final TerminalBindingCondition BAD_REQUEST = createWithCode("bad-request", "The format of an HTTP header or binding element received from the client is unacceptable (e.g., syntax error).", 400);
    private static final Map<Integer, TerminalBindingCondition> CODE_TO_INSTANCE = new HashMap();
    private static final Map<String, TerminalBindingCondition> COND_TO_INSTANCE = new HashMap();
    static final TerminalBindingCondition HOST_GONE = create("host-gone", "The target domain specified in the 'to' attribute or the target host or port specified in the 'route' attribute is no longer serviced by the connection manager.");
    static final TerminalBindingCondition HOST_UNKNOWN = create("host-unknown", "The target domain specified in the 'to' attribute or the target host or port specified in the 'route' attribute is unknown to the connection manager.");
    static final TerminalBindingCondition IMPROPER_ADDRESSING = create("improper-addressing", "The initialization element lacks a 'to' or 'route' attribute (or the attribute has no value) but the connection manager requires one.");
    static final TerminalBindingCondition INTERNAL_SERVER_ERROR = create("internal-server-error", "The connection manager has experienced an internal error that prevents it from servicing the request.");
    static final TerminalBindingCondition ITEM_NOT_FOUND = createWithCode("item-not-found", "(1) 'sid' is not valid, (2) 'stream' is not valid, (3) 'rid' is larger than the upper limit of the expected window, (4) connection manager is unable to resend response, (5) 'key' sequence is invalid.", Integer.valueOf(WalletConstants.ERROR_CODE_INVALID_PARAMETERS));
    static final TerminalBindingCondition OTHER_REQUEST = create("other-request", "Another request being processed at the same time as this request caused the session to terminate.");
    static final TerminalBindingCondition POLICY_VIOLATION = createWithCode("policy-violation", "The client has broken the session rules (polling too frequently, requesting too frequently, sending too many simultaneous requests).", 403);
    static final TerminalBindingCondition REMOTE_CONNECTION_FAILED = create("remote-connection-failed", "The connection manager was unable to connect to, or unable to connect securely to, or has lost its connection to, the server.");
    static final TerminalBindingCondition REMOTE_STREAM_ERROR = create("remote-stream-error", "Encapsulated transport protocol error.");
    static final TerminalBindingCondition SEE_OTHER_URI = create("see-other-uri", "The connection manager does not operate at this URI (e.g., the connection manager accepts only SSL or TLS connections at some https: URI rather than the http: URI requested by the client).");
    static final TerminalBindingCondition SYSTEM_SHUTDOWN = create("system-shutdown", "The connection manager is being shut down. All active HTTP sessions are being terminated. No new sessions can be created.");
    static final TerminalBindingCondition UNDEFINED_CONDITION = create("undefined-condition", "Unknown or undefined error condition.");
    private final String cond;
    private final String msg;

    private TerminalBindingCondition(String str, String str2) {
        this.cond = str;
        this.msg = str2;
    }

    private static TerminalBindingCondition create(String str, String str2) {
        return createWithCode(str, str2, (Integer) null);
    }

    private static TerminalBindingCondition createWithCode(String str, String str2, Integer num) {
        if (str == null) {
            throw new IllegalArgumentException("condition may not be null");
        } else if (str2 != null) {
            Map<String, TerminalBindingCondition> map = COND_TO_INSTANCE;
            if (map.get(str) == null) {
                TerminalBindingCondition terminalBindingCondition = new TerminalBindingCondition(str, str2);
                map.put(str, terminalBindingCondition);
                if (num != null) {
                    Map<Integer, TerminalBindingCondition> map2 = CODE_TO_INSTANCE;
                    if (map2.get(num) == null) {
                        map2.put(num, terminalBindingCondition);
                    } else {
                        throw new IllegalStateException("Multiple definitions of code: " + num);
                    }
                }
                return terminalBindingCondition;
            }
            throw new IllegalStateException("Multiple definitions of condition: " + str);
        } else {
            throw new IllegalArgumentException("message may not be null");
        }
    }

    static TerminalBindingCondition forString(String str) {
        return COND_TO_INSTANCE.get(str);
    }

    static TerminalBindingCondition forHTTPResponseCode(int i) {
        return CODE_TO_INSTANCE.get(Integer.valueOf(i));
    }

    /* access modifiers changed from: package-private */
    public String getCondition() {
        return this.cond;
    }

    /* access modifiers changed from: package-private */
    public String getMessage() {
        return this.msg;
    }
}
