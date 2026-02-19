package org.jivesoftware.smack.packet;

import com.google.android.gms.wallet.WalletConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMPPError {
    private List<PacketExtension> applicationExtensions = null;
    private int code;
    private String condition;
    private String message;
    private Type type;

    public enum Type {
        WAIT,
        CANCEL,
        MODIFY,
        AUTH,
        CONTINUE
    }

    public XMPPError(Condition condition2) {
        init(condition2);
        this.message = null;
    }

    public XMPPError(Condition condition2, String str) {
        init(condition2);
        this.message = str;
    }

    public XMPPError(int i) {
        this.code = i;
        this.message = null;
    }

    public XMPPError(int i, String str) {
        this.code = i;
        this.message = str;
    }

    public XMPPError(int i, Type type2, String str, String str2, List<PacketExtension> list) {
        this.code = i;
        this.type = type2;
        this.condition = str;
        this.message = str2;
        this.applicationExtensions = list;
    }

    private void init(Condition condition2) {
        ErrorSpecification specFor = ErrorSpecification.specFor(condition2);
        this.condition = condition2.value;
        if (specFor != null) {
            this.type = specFor.getType();
            this.code = specFor.getCode();
        }
    }

    public String getCondition() {
        return this.condition;
    }

    public Type getType() {
        return this.type;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<error code=\"");
        sb.append(this.code);
        sb.append("\"");
        if (this.type != null) {
            sb.append(" type=\"");
            sb.append(this.type.name());
            sb.append("\"");
        }
        sb.append(">");
        if (this.condition != null) {
            sb.append("<");
            sb.append(this.condition);
            sb.append(" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\"/>");
        }
        if (this.message != null) {
            sb.append("<text xml:lang=\"en\" xmlns=\"urn:ietf:params:xml:ns:xmpp-stanzas\">");
            sb.append(this.message);
            sb.append("</text>");
        }
        for (PacketExtension xml : getExtensions()) {
            sb.append(xml.toXML());
        }
        sb.append("</error>");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.condition;
        if (str != null) {
            sb.append(str);
        }
        sb.append("(");
        sb.append(this.code);
        sb.append(")");
        if (this.message != null) {
            sb.append(" ");
            sb.append(this.message);
        }
        return sb.toString();
    }

    public synchronized List<PacketExtension> getExtensions() {
        List<PacketExtension> list = this.applicationExtensions;
        if (list == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(list);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized org.jivesoftware.smack.packet.PacketExtension getExtension(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.List<org.jivesoftware.smack.packet.PacketExtension> r0 = r4.applicationExtensions     // Catch:{ all -> 0x0035 }
            r1 = 0
            if (r0 == 0) goto L_0x0033
            if (r5 == 0) goto L_0x0033
            if (r6 != 0) goto L_0x000b
            goto L_0x0033
        L_0x000b:
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0035 }
        L_0x000f:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x0035 }
            if (r2 == 0) goto L_0x0031
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x0035 }
            org.jivesoftware.smack.packet.PacketExtension r2 = (org.jivesoftware.smack.packet.PacketExtension) r2     // Catch:{ all -> 0x0035 }
            java.lang.String r3 = r2.getElementName()     // Catch:{ all -> 0x0035 }
            boolean r3 = r5.equals(r3)     // Catch:{ all -> 0x0035 }
            if (r3 == 0) goto L_0x000f
            java.lang.String r3 = r2.getNamespace()     // Catch:{ all -> 0x0035 }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x0035 }
            if (r3 == 0) goto L_0x000f
            monitor-exit(r4)
            return r2
        L_0x0031:
            monitor-exit(r4)
            return r1
        L_0x0033:
            monitor-exit(r4)
            return r1
        L_0x0035:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.packet.XMPPError.getExtension(java.lang.String, java.lang.String):org.jivesoftware.smack.packet.PacketExtension");
    }

    public synchronized void addExtension(PacketExtension packetExtension) {
        if (this.applicationExtensions == null) {
            this.applicationExtensions = new ArrayList();
        }
        this.applicationExtensions.add(packetExtension);
    }

    public synchronized void setExtension(List<PacketExtension> list) {
        this.applicationExtensions = list;
    }

    public static class Condition {
        public static final Condition bad_request = new Condition("bad-request");
        public static final Condition conflict = new Condition("conflict");
        public static final Condition feature_not_implemented = new Condition("feature-not-implemented");
        public static final Condition forbidden = new Condition("forbidden");
        public static final Condition gone = new Condition("gone");
        public static final Condition interna_server_error = new Condition("internal-server-error");
        public static final Condition item_not_found = new Condition("item-not-found");
        public static final Condition jid_malformed = new Condition("jid-malformed");
        public static final Condition no_acceptable = new Condition("not-acceptable");
        public static final Condition not_allowed = new Condition("not-allowed");
        public static final Condition not_authorized = new Condition("not-authorized");
        public static final Condition payment_required = new Condition("payment-required");
        public static final Condition recipient_unavailable = new Condition("recipient-unavailable");
        public static final Condition redirect = new Condition("redirect");
        public static final Condition registration_required = new Condition("registration-required");
        public static final Condition remote_server_error = new Condition("remote-server-error");
        public static final Condition remote_server_not_found = new Condition("remote-server-not-found");
        public static final Condition remote_server_timeout = new Condition("remote-server-timeout");
        public static final Condition request_timeout = new Condition("request-timeout");
        public static final Condition resource_constraint = new Condition("resource-constraint");
        public static final Condition service_unavailable = new Condition("service-unavailable");
        public static final Condition subscription_required = new Condition("subscription-required");
        public static final Condition undefined_condition = new Condition("undefined-condition");
        public static final Condition unexpected_request = new Condition("unexpected-request");
        /* access modifiers changed from: private */
        public String value;

        public Condition(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }
    }

    private static class ErrorSpecification {
        private static Map<Condition, ErrorSpecification> instances = errorSpecifications();
        private int code;
        private Condition condition;
        private Type type;

        private ErrorSpecification(Condition condition2, Type type2, int i) {
            this.code = i;
            this.type = type2;
            this.condition = condition2;
        }

        private static Map<Condition, ErrorSpecification> errorSpecifications() {
            HashMap hashMap = new HashMap(22);
            hashMap.put(Condition.interna_server_error, new ErrorSpecification(Condition.interna_server_error, Type.WAIT, 500));
            hashMap.put(Condition.forbidden, new ErrorSpecification(Condition.forbidden, Type.AUTH, 403));
            hashMap.put(Condition.bad_request, new ErrorSpecification(Condition.bad_request, Type.MODIFY, 400));
            hashMap.put(Condition.item_not_found, new ErrorSpecification(Condition.item_not_found, Type.CANCEL, WalletConstants.ERROR_CODE_INVALID_PARAMETERS));
            hashMap.put(Condition.conflict, new ErrorSpecification(Condition.conflict, Type.CANCEL, WalletConstants.ERROR_CODE_BUYER_ACCOUNT_ERROR));
            hashMap.put(Condition.feature_not_implemented, new ErrorSpecification(Condition.feature_not_implemented, Type.CANCEL, 501));
            hashMap.put(Condition.gone, new ErrorSpecification(Condition.gone, Type.MODIFY, 302));
            hashMap.put(Condition.jid_malformed, new ErrorSpecification(Condition.jid_malformed, Type.MODIFY, 400));
            hashMap.put(Condition.no_acceptable, new ErrorSpecification(Condition.no_acceptable, Type.MODIFY, WalletConstants.ERROR_CODE_SPENDING_LIMIT_EXCEEDED));
            hashMap.put(Condition.not_allowed, new ErrorSpecification(Condition.not_allowed, Type.CANCEL, WalletConstants.ERROR_CODE_MERCHANT_ACCOUNT_ERROR));
            hashMap.put(Condition.not_authorized, new ErrorSpecification(Condition.not_authorized, Type.AUTH, 401));
            hashMap.put(Condition.payment_required, new ErrorSpecification(Condition.payment_required, Type.AUTH, WalletConstants.ERROR_CODE_SERVICE_UNAVAILABLE));
            hashMap.put(Condition.recipient_unavailable, new ErrorSpecification(Condition.recipient_unavailable, Type.WAIT, WalletConstants.ERROR_CODE_INVALID_PARAMETERS));
            hashMap.put(Condition.redirect, new ErrorSpecification(Condition.redirect, Type.MODIFY, 302));
            hashMap.put(Condition.registration_required, new ErrorSpecification(Condition.registration_required, Type.AUTH, 407));
            hashMap.put(Condition.remote_server_not_found, new ErrorSpecification(Condition.remote_server_not_found, Type.CANCEL, WalletConstants.ERROR_CODE_INVALID_PARAMETERS));
            hashMap.put(Condition.remote_server_timeout, new ErrorSpecification(Condition.remote_server_timeout, Type.WAIT, 504));
            hashMap.put(Condition.remote_server_error, new ErrorSpecification(Condition.remote_server_error, Type.CANCEL, 502));
            hashMap.put(Condition.resource_constraint, new ErrorSpecification(Condition.resource_constraint, Type.WAIT, 500));
            hashMap.put(Condition.service_unavailable, new ErrorSpecification(Condition.service_unavailable, Type.CANCEL, 503));
            hashMap.put(Condition.subscription_required, new ErrorSpecification(Condition.subscription_required, Type.AUTH, 407));
            hashMap.put(Condition.undefined_condition, new ErrorSpecification(Condition.undefined_condition, Type.WAIT, 500));
            hashMap.put(Condition.unexpected_request, new ErrorSpecification(Condition.unexpected_request, Type.WAIT, 400));
            hashMap.put(Condition.request_timeout, new ErrorSpecification(Condition.request_timeout, Type.CANCEL, 408));
            return hashMap;
        }

        protected static ErrorSpecification specFor(Condition condition2) {
            return instances.get(condition2);
        }

        /* access modifiers changed from: protected */
        public Condition getCondition() {
            return this.condition;
        }

        /* access modifiers changed from: protected */
        public Type getType() {
            return this.type;
        }

        /* access modifiers changed from: protected */
        public int getCode() {
            return this.code;
        }
    }
}
