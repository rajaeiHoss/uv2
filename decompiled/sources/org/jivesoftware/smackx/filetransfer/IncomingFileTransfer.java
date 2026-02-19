package org.jivesoftware.smackx.filetransfer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransfer;

public class IncomingFileTransfer extends FileTransfer {
    /* access modifiers changed from: private */
    public InputStream inputStream;
    /* access modifiers changed from: private */
    public FileTransferRequest recieveRequest;

    protected IncomingFileTransfer(FileTransferRequest fileTransferRequest, FileTransferNegotiator fileTransferNegotiator) {
        super(fileTransferRequest.getRequestor(), fileTransferRequest.getStreamID(), fileTransferNegotiator);
        this.recieveRequest = fileTransferRequest;
    }

    public InputStream recieveFile() throws XMPPException {
        if (this.inputStream == null) {
            try {
                InputStream negotiateStream = negotiateStream();
                this.inputStream = negotiateStream;
                return negotiateStream;
            } catch (XMPPException e) {
                setException(e);
                throw e;
            }
        } else {
            throw new IllegalStateException("Transfer already negotiated!");
        }
    }

    public void recieveFile(final File file) throws XMPPException {
        if (file != null) {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new XMPPException("Could not create file to write too", (Throwable) e);
                }
            }
            if (file.canWrite()) {
                Runnable receiveTask = new Runnable() {
                    /* JADX WARNING: Removed duplicated region for block: B:17:0x0063  */
                    /* JADX WARNING: Removed duplicated region for block: B:20:0x0072 A[SYNTHETIC, Splitter:B:20:0x0072] */
                    /* JADX WARNING: Removed duplicated region for block: B:24:0x007f A[SYNTHETIC, Splitter:B:24:0x007f] */
                    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r5 = this;
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this     // Catch:{ XMPPException -> 0x0083 }
                            java.io.InputStream r1 = r0.negotiateStream()     // Catch:{ XMPPException -> 0x0083 }
                            java.io.InputStream unused = r0.inputStream = r1     // Catch:{ XMPPException -> 0x0083 }
                            r0 = 0
                            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ XMPPException -> 0x003e, FileNotFoundException -> 0x0026 }
                            java.io.File r2 = r4     // Catch:{ XMPPException -> 0x003e, FileNotFoundException -> 0x0026 }
                            r1.<init>(r2)     // Catch:{ XMPPException -> 0x003e, FileNotFoundException -> 0x0026 }
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this     // Catch:{ XMPPException -> 0x0024, FileNotFoundException -> 0x0022 }
                            org.jivesoftware.smackx.filetransfer.FileTransfer$Status r2 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.in_progress     // Catch:{ XMPPException -> 0x0024, FileNotFoundException -> 0x0022 }
                            r0.setStatus(r2)     // Catch:{ XMPPException -> 0x0024, FileNotFoundException -> 0x0022 }
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this     // Catch:{ XMPPException -> 0x0024, FileNotFoundException -> 0x0022 }
                            java.io.InputStream r2 = r0.inputStream     // Catch:{ XMPPException -> 0x0024, FileNotFoundException -> 0x0022 }
                            r0.writeToStream(r2, r1)     // Catch:{ XMPPException -> 0x0024, FileNotFoundException -> 0x0022 }
                            goto L_0x0055
                        L_0x0022:
                            r0 = move-exception
                            goto L_0x002a
                        L_0x0024:
                            r0 = move-exception
                            goto L_0x0042
                        L_0x0026:
                            r1 = move-exception
                            r4 = r1
                            r1 = r0
                            r0 = r4
                        L_0x002a:
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r2 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this
                            org.jivesoftware.smackx.filetransfer.FileTransfer$Status r3 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.error
                            r2.setStatus(r3)
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r2 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this
                            org.jivesoftware.smackx.filetransfer.FileTransfer$Error r3 = org.jivesoftware.smackx.filetransfer.FileTransfer.Error.bad_file
                            r2.setError(r3)
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r2 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this
                            r2.setException(r0)
                            goto L_0x0055
                        L_0x003e:
                            r1 = move-exception
                            r4 = r1
                            r1 = r0
                            r0 = r4
                        L_0x0042:
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r2 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this
                            org.jivesoftware.smackx.filetransfer.FileTransfer$Status r3 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.error
                            r2.setStatus(r3)
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r2 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this
                            org.jivesoftware.smackx.filetransfer.FileTransfer$Error r3 = org.jivesoftware.smackx.filetransfer.FileTransfer.Error.stream
                            r2.setError(r3)
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r2 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this
                            r2.setException(r0)
                        L_0x0055:
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this
                            org.jivesoftware.smackx.filetransfer.FileTransfer$Status r0 = r0.getStatus()
                            org.jivesoftware.smackx.filetransfer.FileTransfer$Status r2 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.in_progress
                            boolean r0 = r0.equals(r2)
                            if (r0 == 0) goto L_0x006a
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this
                            org.jivesoftware.smackx.filetransfer.FileTransfer$Status r2 = org.jivesoftware.smackx.filetransfer.FileTransfer.Status.complete
                            r0.setStatus(r2)
                        L_0x006a:
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this
                            java.io.InputStream r0 = r0.inputStream
                            if (r0 == 0) goto L_0x007d
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r0 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this     // Catch:{ all -> 0x007c }
                            java.io.InputStream r0 = r0.inputStream     // Catch:{ all -> 0x007c }
                            r0.close()     // Catch:{ all -> 0x007c }
                            goto L_0x007d
                        L_0x007c:
                        L_0x007d:
                            if (r1 == 0) goto L_0x0082
                            r1.close()     // Catch:{ all -> 0x0082 }
                        L_0x0082:
                            return
                        L_0x0083:
                            r0 = move-exception
                            org.jivesoftware.smackx.filetransfer.IncomingFileTransfer r1 = org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.this
                            r1.handleXMPPException(r0)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smackx.filetransfer.IncomingFileTransfer.AnonymousClass1.run():void");
                    }
                };
                new Thread(receiveTask, "File Transfer " + this.streamID).start();
                return;
            }
            throw new IllegalArgumentException("Cannot write to provided file");
        }
        throw new IllegalArgumentException("File cannot be null");
    }

    /* access modifiers changed from: private */
    public void handleXMPPException(XMPPException xMPPException) {
        setStatus(FileTransfer.Status.error);
        setException(xMPPException);
    }

    /* access modifiers changed from: private */
    public InputStream negotiateStream() throws XMPPException {
        setStatus(FileTransfer.Status.negotiating_transfer);
        final StreamNegotiator selectStreamNegotiator = this.negotiator.selectStreamNegotiator(this.recieveRequest);
        setStatus(FileTransfer.Status.negotiating_stream);
        FutureTask futureTask = new FutureTask(new Callable<InputStream>() {
            public InputStream call() throws Exception {
                return selectStreamNegotiator.createIncomingStream(IncomingFileTransfer.this.recieveRequest.getStreamInitiation());
            }
        });
        futureTask.run();
        try {
            InputStream inputStream2 = (InputStream) futureTask.get(15, TimeUnit.SECONDS);
            futureTask.cancel(true);
            setStatus(FileTransfer.Status.negotiated);
            return inputStream2;
        } catch (InterruptedException e) {
            throw new XMPPException("Interruption while executing", (Throwable) e);
        } catch (ExecutionException e2) {
            throw new XMPPException("Error in execution", (Throwable) e2);
        } catch (TimeoutException e3) {
            throw new XMPPException("Request timed out", (Throwable) e3);
        } catch (Throwable th) {
            futureTask.cancel(true);
            throw th;
        }
    }

    public void cancel() {
        setStatus(FileTransfer.Status.cancelled);
    }
}
