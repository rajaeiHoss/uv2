package com.kenai.jbosh;

import java.util.HashMap;
import java.util.Map;

final class BodyParserResults {
    private final Map<BodyQName, String> attrs = new HashMap();

    BodyParserResults() {
    }

    /* access modifiers changed from: package-private */
    public void addBodyAttributeValue(BodyQName bodyQName, String str) {
        this.attrs.put(bodyQName, str);
    }

    /* access modifiers changed from: package-private */
    public Map<BodyQName, String> getAttributes() {
        return this.attrs;
    }
}
