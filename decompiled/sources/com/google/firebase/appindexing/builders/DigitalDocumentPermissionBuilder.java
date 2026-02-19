package com.google.firebase.appindexing.builders;

public final class DigitalDocumentPermissionBuilder extends IndexableBuilder<DigitalDocumentPermissionBuilder> {
    public static final String COMMENT_PERMISSION = "CommentPermission";
    public static final String READ_PERMISSION = "ReadPermission";
    public static final String WRITE_PERMISSION = "WritePermission";

    DigitalDocumentPermissionBuilder() {
        super("DigitalDocumentPermission");
    }

    public final DigitalDocumentPermissionBuilder setGrantee(PersonBuilder... personBuilderArr) {
        return (DigitalDocumentPermissionBuilder) put("grantee", personBuilderArr);
    }

    public final DigitalDocumentPermissionBuilder setPermissionType(String str) {
        return (DigitalDocumentPermissionBuilder) put("permissionType", str);
    }
}
