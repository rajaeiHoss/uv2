package org.jivesoftware.smack;

public interface ConnectionListener {
    void connectionClosed();

    void connectionClosedOnError(Exception exc);

    void reconnectingIn(int i);

    void reconnectionFailed(Exception exc);

    void reconnectionSuccessful();
}
