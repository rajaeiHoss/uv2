package org.jivesoftware.smackx.filetransfer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.filetransfer.FileTransfer;

public class OutgoingFileTransfer extends FileTransfer {
    private static int RESPONSE_TIMEOUT = 60000;
    private NegotiationProgress callback;
    private String initiator;
    /* access modifiers changed from: private */
    public OutputStream outputStream;
    private Thread transferThread;

    public interface NegotiationProgress {
        void errorEstablishingStream(Exception exc);

        void outputStreamEstablished(OutputStream outputStream);

        void statusUpdated(FileTransfer.Status status, FileTransfer.Status status2);
    }

    public static int getResponseTimeout() {
        return RESPONSE_TIMEOUT;
    }

    public static void setResponseTimeout(int i) {
        RESPONSE_TIMEOUT = i;
    }

    protected OutgoingFileTransfer(String str, String str2, String str3, FileTransferNegotiator fileTransferNegotiator) {
        super(str2, str3, fileTransferNegotiator);
        this.initiator = str;
    }

    /* access modifiers changed from: protected */
    public void setOutputStream(OutputStream outputStream2) {
        if (this.outputStream == null) {
            this.outputStream = outputStream2;
        }
    }

    /* access modifiers changed from: protected */
    public OutputStream getOutputStream() {
        if (getStatus().equals(FileTransfer.Status.negotiated)) {
            return this.outputStream;
        }
        return null;
    }

    public synchronized OutputStream sendFile(String str, long j, String str2) throws XMPPException {
        OutputStream negotiateStream;
        if (isDone() || this.outputStream != null) {
            throw new IllegalStateException("The negotation process has already been attempted on this file transfer");
        }
        try {
            negotiateStream = negotiateStream(str, j, str2);
            this.outputStream = negotiateStream;
        } catch (XMPPException e) {
            handleXMPPException(e);
            throw e;
        }
        return negotiateStream;
    }

    public synchronized void sendFile(String str, long j, String str2, NegotiationProgress negotiationProgress) {
        if (negotiationProgress != null) {
            try {
                checkTransferThread();
                if (isDone() || this.outputStream != null) {
                    throw new IllegalStateException("The negotation process has already been attempted for this file transfer");
                }
                this.callback = negotiationProgress;
                final String str3 = str;
                final long j2 = j;
                final String str4 = str2;
                final NegotiationProgress negotiationProgress2 = negotiationProgress;
                Runnable negotiateTask = new Runnable() {
                    public void run() {
                        try {
                            OutgoingFileTransfer outgoingFileTransfer = OutgoingFileTransfer.this;
                            OutputStream unused = outgoingFileTransfer.outputStream = outgoingFileTransfer.negotiateStream(str3, j2, str4);
                            negotiationProgress2.outputStreamEstablished(OutgoingFileTransfer.this.outputStream);
                        } catch (XMPPException e) {
                            OutgoingFileTransfer.this.handleXMPPException(e);
                        }
                    }
                };
                Thread thread = new Thread(negotiateTask, "File Transfer Negotiation " + this.streamID);
                this.transferThread = thread;
                thread.start();
            } catch (Throwable th) {
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Callback progress cannot be null.");
        }
    }

    private void checkTransferThread() {
        Thread thread = this.transferThread;
        if ((thread != null && thread.isAlive()) || isDone()) {
            throw new IllegalStateException("File transfer in progress or has already completed.");
        }
    }

    public synchronized void sendFile(final File file, final String str) throws XMPPException {
        checkTransferThread();
        if (file == null || !file.exists() || !file.canRead()) {
            throw new IllegalArgumentException("Could not read file");
        }
        setFileInfo(file.getAbsolutePath(), file.getName(), file.length());
        Runnable sendTask = new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:25:0x006f A[SYNTHETIC, Splitter:B:25:0x006f] */
            /* JADX WARNING: Removed duplicated region for block: B:33:0x0097 A[SYNTHETIC, Splitter:B:33:0x0097] */
            /* JADX WARNING: Removed duplicated region for block: B:41:0x00b3 A[SYNTHETIC, Splitter:B:41:0x00b3] */
            /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0061=Splitter:B:22:0x0061, B:30:0x0082=Splitter:B:30:0x0082} */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r6 = this;
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ XMPPException -> 0x00c9 }
                    java.io.File r1 = r5     // Catch:{ XMPPException -> 0x00c9 }
                    java.lang.String r1 = r1.getName()     // Catch:{ XMPPException -> 0x00c9 }
                    java.io.File r2 = r5     // Catch:{ XMPPException -> 0x00c9 }
                    long r2 = r2.length()     // Catch:{ XMPPException -> 0x00c9 }
                    java.lang.String r4 = r6     // Catch:{ XMPPException -> 0x00c9 }
                    java.io.OutputStream r1 = r0.negotiateStream(r1, r2, r4)     // Catch:{ XMPPException -> 0x00c9 }
                    java.io.OutputStream unused = r0.outputStream = r1     // Catch:{ XMPPException -> 0x00c9 }
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this
                    java.io.OutputStream r0 = r0.outputStream
                    if (r0 != 0) goto L_0x0020
                    return
                L_0x0020:
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this
                    org.jivesoftware.smackx.filetransfer.FileTransfer$Status r1 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.negotiated
                    org.jivesoftware.smackx.filetransfer.FileTransfer$Status r2 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.in_progress
                    boolean r0 = r0.updateStatus(r1, r2)
                    if (r0 != 0) goto L_0x002d
                    return
                L_0x002d:
                    r0 = 0
                    java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x007e, XMPPException -> 0x005d, all -> 0x0058 }
                    java.io.File r2 = r5     // Catch:{ FileNotFoundException -> 0x007e, XMPPException -> 0x005d, all -> 0x0058 }
                    r1.<init>(r2)     // Catch:{ FileNotFoundException -> 0x007e, XMPPException -> 0x005d, all -> 0x0058 }
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ FileNotFoundException -> 0x0056, XMPPException -> 0x0054 }
                    java.io.OutputStream r2 = r0.outputStream     // Catch:{ FileNotFoundException -> 0x0056, XMPPException -> 0x0054 }
                    r0.writeToStream(r1, r2)     // Catch:{ FileNotFoundException -> 0x0056, XMPPException -> 0x0054 }
                    r1.close()     // Catch:{ IOException -> 0x00a6 }
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ IOException -> 0x00a6 }
                    java.io.OutputStream r0 = r0.outputStream     // Catch:{ IOException -> 0x00a6 }
                    r0.flush()     // Catch:{ IOException -> 0x00a6 }
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ IOException -> 0x00a6 }
                L_0x004c:
                    java.io.OutputStream r0 = r0.outputStream     // Catch:{ IOException -> 0x00a6 }
                    r0.close()     // Catch:{ IOException -> 0x00a6 }
                    goto L_0x00a6
                L_0x0054:
                    r0 = move-exception
                    goto L_0x0061
                L_0x0056:
                    r0 = move-exception
                    goto L_0x0082
                L_0x0058:
                    r1 = move-exception
                    r5 = r1
                    r1 = r0
                    r0 = r5
                    goto L_0x00b1
                L_0x005d:
                    r1 = move-exception
                    r5 = r1
                    r1 = r0
                    r0 = r5
                L_0x0061:
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r2 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ all -> 0x00b0 }
                    org.jivesoftware.smackx.filetransfer.FileTransfer$Status r3 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.error     // Catch:{ all -> 0x00b0 }
                    r2.setStatus(r3)     // Catch:{ all -> 0x00b0 }
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r2 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ all -> 0x00b0 }
                    r2.setException(r0)     // Catch:{ all -> 0x00b0 }
                    if (r1 == 0) goto L_0x0072
                    r1.close()     // Catch:{ IOException -> 0x00a6 }
                L_0x0072:
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ IOException -> 0x00a6 }
                    java.io.OutputStream r0 = r0.outputStream     // Catch:{ IOException -> 0x00a6 }
                    r0.flush()     // Catch:{ IOException -> 0x00a6 }
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ IOException -> 0x00a6 }
                    goto L_0x004c
                L_0x007e:
                    r1 = move-exception
                    r5 = r1
                    r1 = r0
                    r0 = r5
                L_0x0082:
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r2 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ all -> 0x00b0 }
                    org.jivesoftware.smackx.filetransfer.FileTransfer$Status r3 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.error     // Catch:{ all -> 0x00b0 }
                    r2.setStatus(r3)     // Catch:{ all -> 0x00b0 }
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r2 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ all -> 0x00b0 }
                    org.jivesoftware.smackx.filetransfer.FileTransfer$Error r3 = org.jivesoftware.smackx.filetransfer.FileTransfer.Error.bad_file     // Catch:{ all -> 0x00b0 }
                    r2.setError(r3)     // Catch:{ all -> 0x00b0 }
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r2 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ all -> 0x00b0 }
                    r2.setException(r0)     // Catch:{ all -> 0x00b0 }
                    if (r1 == 0) goto L_0x009a
                    r1.close()     // Catch:{ IOException -> 0x00a6 }
                L_0x009a:
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ IOException -> 0x00a6 }
                    java.io.OutputStream r0 = r0.outputStream     // Catch:{ IOException -> 0x00a6 }
                    r0.flush()     // Catch:{ IOException -> 0x00a6 }
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ IOException -> 0x00a6 }
                    goto L_0x004c
                L_0x00a6:
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this
                    org.jivesoftware.smackx.filetransfer.FileTransfer$Status r1 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.in_progress
                    org.jivesoftware.smackx.filetransfer.FileTransfer$Status r2 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.complete
                    r0.updateStatus(r1, r2)
                    return
                L_0x00b0:
                    r0 = move-exception
                L_0x00b1:
                    if (r1 == 0) goto L_0x00b6
                    r1.close()     // Catch:{ IOException -> 0x00c8 }
                L_0x00b6:
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r1 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ IOException -> 0x00c8 }
                    java.io.OutputStream r1 = r1.outputStream     // Catch:{ IOException -> 0x00c8 }
                    r1.flush()     // Catch:{ IOException -> 0x00c8 }
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r1 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this     // Catch:{ IOException -> 0x00c8 }
                    java.io.OutputStream r1 = r1.outputStream     // Catch:{ IOException -> 0x00c8 }
                    r1.close()     // Catch:{ IOException -> 0x00c8 }
                L_0x00c8:
                    throw r0
                L_0x00c9:
                    r0 = move-exception
                    org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer r1 = org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.this
                    r1.handleXMPPException(r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer.AnonymousClass2.run():void");
            }
        };
        Thread thread = new Thread(sendTask, "File Transfer " + this.streamID);
        this.transferThread = thread;
        thread.start();
    }

    public synchronized void sendStream(InputStream inputStream, String str, long j, String str2) {
        checkTransferThread();
        final String str3 = str;
        final long j2 = j;
        final String str4 = str2;
        final InputStream inputStream2 = inputStream;
        Runnable sendStreamTask = new Runnable() {
            public void run() {
                OutgoingFileTransfer outgoingFileTransfer;
                try {
                    OutgoingFileTransfer outgoingFileTransfer2 = OutgoingFileTransfer.this;
                    OutputStream unused = outgoingFileTransfer2.outputStream = outgoingFileTransfer2.negotiateStream(str3, j2, str4);
                    if (OutgoingFileTransfer.this.outputStream != null && OutgoingFileTransfer.this.updateStatus(FileTransfer.Status.negotiated, FileTransfer.Status.in_progress)) {
                        try {
                            OutgoingFileTransfer outgoingFileTransfer3 = OutgoingFileTransfer.this;
                            outgoingFileTransfer3.writeToStream(inputStream2, outgoingFileTransfer3.outputStream);
                            try {
                                InputStream inputStream = inputStream2;
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                OutgoingFileTransfer.this.outputStream.flush();
                                outgoingFileTransfer = OutgoingFileTransfer.this;
                                outgoingFileTransfer.outputStream.close();
                            } catch (IOException unused2) {
                            }
                        } catch (XMPPException e) {
                            OutgoingFileTransfer.this.setStatus(FileTransfer.Status.error);
                            OutgoingFileTransfer.this.setException(e);
                            try {
                                if (inputStream2 != null) {
                                    inputStream2.close();
                                }
                                if (OutgoingFileTransfer.this.outputStream != null) {
                                    OutgoingFileTransfer.this.outputStream.flush();
                                    outgoingFileTransfer = OutgoingFileTransfer.this;
                                    outgoingFileTransfer.outputStream.close();
                                }
                            } catch (IOException unused2) {
                            }
                        } catch (Throwable th) {
                            try {
                                InputStream inputStream3 = inputStream2;
                                if (inputStream3 != null) {
                                    inputStream3.close();
                                }
                                OutgoingFileTransfer.this.outputStream.flush();
                                OutgoingFileTransfer.this.outputStream.close();
                            } catch (IOException unused3) {
                            }
                            throw th;
                        }
                        OutgoingFileTransfer.this.updateStatus(FileTransfer.Status.in_progress, FileTransfer.Status.complete);
                    }
                } catch (XMPPException e2) {
                    OutgoingFileTransfer.this.handleXMPPException(e2);
                }
            }
        };
        Thread thread = new Thread(sendStreamTask, "File Transfer " + this.streamID);
        this.transferThread = thread;
        thread.start();
    }

    /* access modifiers changed from: private */
    public void handleXMPPException(XMPPException xMPPException) {
        XMPPError xMPPError = xMPPException.getXMPPError();
        if (xMPPError != null) {
            int code = xMPPError.getCode();
            if (code == 403) {
                setStatus(FileTransfer.Status.refused);
                return;
            } else if (code == 400) {
                setStatus(FileTransfer.Status.error);
                setError(FileTransfer.Error.not_acceptable);
            } else {
                setStatus(FileTransfer.Status.error);
            }
        }
        setException(xMPPException);
    }

    public long getBytesSent() {
        return this.amountWritten;
    }

    /* access modifiers changed from: private */
    public OutputStream negotiateStream(String str, long j, String str2) throws XMPPException {
        if (updateStatus(FileTransfer.Status.initial, FileTransfer.Status.negotiating_transfer)) {
            StreamNegotiator negotiateOutgoingTransfer = this.negotiator.negotiateOutgoingTransfer(getPeer(), this.streamID, str, j, str2, RESPONSE_TIMEOUT);
            if (negotiateOutgoingTransfer == null) {
                setStatus(FileTransfer.Status.error);
                setError(FileTransfer.Error.no_response);
                return null;
            } else if (updateStatus(FileTransfer.Status.negotiating_transfer, FileTransfer.Status.negotiating_stream)) {
                this.outputStream = negotiateOutgoingTransfer.createOutgoingStream(this.streamID, this.initiator, getPeer());
                if (updateStatus(FileTransfer.Status.negotiating_stream, FileTransfer.Status.negotiated)) {
                    return this.outputStream;
                }
                throw new XMPPException("Illegal state change");
            } else {
                throw new XMPPException("Illegal state change");
            }
        } else {
            throw new XMPPException("Illegal state change");
        }
    }

    public void cancel() {
        setStatus(FileTransfer.Status.cancelled);
    }

    /* access modifiers changed from: protected */
    public boolean updateStatus(FileTransfer.Status status, FileTransfer.Status status2) {
        boolean updateStatus = super.updateStatus(status, status2);
        NegotiationProgress negotiationProgress = this.callback;
        if (negotiationProgress != null && updateStatus) {
            negotiationProgress.statusUpdated(status, status2);
        }
        return updateStatus;
    }

    /* access modifiers changed from: protected */
    public void setStatus(FileTransfer.Status status) {
        FileTransfer.Status status2 = getStatus();
        super.setStatus(status);
        NegotiationProgress negotiationProgress = this.callback;
        if (negotiationProgress != null) {
            negotiationProgress.statusUpdated(status2, status);
        }
    }

    /* access modifiers changed from: protected */
    public void setException(Exception exc) {
        super.setException(exc);
        NegotiationProgress negotiationProgress = this.callback;
        if (negotiationProgress != null) {
            negotiationProgress.errorEstablishingStream(exc);
        }
    }
}
