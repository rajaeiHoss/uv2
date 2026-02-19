package org.jivesoftware.smackx.workgroup.agent;

public class UserRequest extends OfferContent {
    private static UserRequest instance = new UserRequest();

    /* access modifiers changed from: package-private */
    public boolean isInvitation() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isTransfer() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isUserRequest() {
        return true;
    }

    public static OfferContent getInstance() {
        return instance;
    }
}
