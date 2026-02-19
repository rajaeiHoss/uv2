package org.jivesoftware.smack;

import com.google.android.gms.nearby.messages.Strategy;
import org.jivesoftware.smack.packet.StreamError;

public class ReconnectionManager implements ConnectionListener {
    /* access modifiers changed from: private */
    public Connection connection;
    boolean done;

    public void reconnectingIn(int i) {
    }

    public void reconnectionFailed(Exception exc) {
    }

    public void reconnectionSuccessful() {
    }

    static {
        Connection.addConnectionCreationListener(new ConnectionCreationListener() {
            public void connectionCreated(Connection connection) {
                connection.addConnectionListener(new ReconnectionManager(connection));
            }
        });
    }

    private ReconnectionManager(Connection connection2) {
        this.done = false;
        this.connection = connection2;
    }

    /* access modifiers changed from: private */
    public boolean isReconnectionAllowed() {
        return !this.done && !this.connection.isConnected() && this.connection.isReconnectionAllowed();
    }

    /* access modifiers changed from: protected */
    public void reconnect() {
        if (isReconnectionAllowed()) {
            Thread reconnectThread = new Thread() {
                private int attempts = 0;

                private int timeDelay() {
                    int i = this.attempts;
                    if (i > 13) {
                        return Strategy.TTL_SECONDS_DEFAULT;
                    }
                    return i > 7 ? 60 : 10;
                }

                public void run() {
                    while (ReconnectionManager.this.isReconnectionAllowed()) {
                        int timeDelay = timeDelay();
                        while (ReconnectionManager.this.isReconnectionAllowed() && timeDelay > 0) {
                            try {
                                Thread.sleep(1000);
                                timeDelay--;
                                ReconnectionManager.this.notifyAttemptToReconnectIn(timeDelay);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                ReconnectionManager.this.notifyReconnectionFailed(e);
                            }
                        }
                        try {
                            if (ReconnectionManager.this.isReconnectionAllowed()) {
                                ReconnectionManager.this.connection.connect();
                            }
                        } catch (XMPPException e2) {
                            ReconnectionManager.this.notifyReconnectionFailed(e2);
                        }
                    }
                }
            };
            reconnectThread.setName("Smack Reconnection Manager");
            reconnectThread.setDaemon(true);
            reconnectThread.start();
        }
    }

    /* access modifiers changed from: protected */
    public void notifyReconnectionFailed(Exception exc) {
        if (isReconnectionAllowed()) {
            for (ConnectionListener reconnectionFailed : this.connection.connectionListeners) {
                reconnectionFailed.reconnectionFailed(exc);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void notifyAttemptToReconnectIn(int i) {
        if (isReconnectionAllowed()) {
            for (ConnectionListener reconnectingIn : this.connection.connectionListeners) {
                reconnectingIn.reconnectingIn(i);
            }
        }
    }

    public void connectionClosed() {
        this.done = true;
    }

    public void connectionClosedOnError(Exception exc) {
        StreamError streamError;
        this.done = false;
        if ((!(exc instanceof XMPPException) || (streamError = ((XMPPException) exc).getStreamError()) == null || !"conflict".equals(streamError.getCode())) && isReconnectionAllowed()) {
            reconnect();
        }
    }
}
