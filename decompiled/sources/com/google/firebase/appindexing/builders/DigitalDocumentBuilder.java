package com.google.firebase.appindexing.builders;

import java.util.Date;

public final class DigitalDocumentBuilder extends IndexableBuilder<DigitalDocumentBuilder> {
    DigitalDocumentBuilder() {
        super("DigitalDocument");
    }

    DigitalDocumentBuilder(String str) {
        super(str);
    }

    public final DigitalDocumentBuilder setAuthor(PersonBuilder... personBuilderArr) {
        return (DigitalDocumentBuilder) put("author", personBuilderArr);
    }

    public final DigitalDocumentBuilder setDateCreated(Date date) {
        return (DigitalDocumentBuilder) put("dateCreated", date.getTime());
    }

    public final DigitalDocumentBuilder setDateModified(Date date) {
        return (DigitalDocumentBuilder) put("dateModified", date.getTime());
    }

    public final DigitalDocumentBuilder setHasDigitalDocumentPermission(DigitalDocumentPermissionBuilder... digitalDocumentPermissionBuilderArr) {
        return (DigitalDocumentBuilder) put("hasDigitalDocumentPermission", digitalDocumentPermissionBuilderArr);
    }

    public final DigitalDocumentBuilder setText(String str) {
        return (DigitalDocumentBuilder) put("text", str);
    }
}
