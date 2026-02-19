package org.jivesoftware.smack;

public class AbstractConnectionListener implements ConnectionListener {
    public void connectionClosed() {
    }

    public void connectionClosedOnError(Exception exc) {
    }

    public void reconnectingIn(int i) {
    }

    public void reconnectionFailed(Exception exc) {
    }

    public void reconnectionSuccessful() {
    }
}
