package org.jivesoftware.smack.debugger;

import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.util.ObservableReader;
import org.jivesoftware.smack.util.ObservableWriter;
import org.jivesoftware.smack.util.ReaderListener;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.WriterListener;

public class ConsoleDebugger implements SmackDebugger {
    public static boolean printInterpreted = false;
    private ConnectionListener connListener = null;
    /* access modifiers changed from: private */
    public Connection connection = null;
    /* access modifiers changed from: private */
    public SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm:ss aaa");
    private PacketListener listener = null;
    private Reader reader;
    private ReaderListener readerListener;
    private Writer writer;
    private WriterListener writerListener;

    public PacketListener getWriterListener() {
        return null;
    }

    public ConsoleDebugger(Connection connection2, Writer writer2, Reader reader2) {
        this.connection = connection2;
        this.writer = writer2;
        this.reader = reader2;
        createDebug();
    }

    private void createDebug() {
        ObservableReader observableReader = new ObservableReader(this.reader);
        ReaderListener readerListener = new ReaderListener() {
            public void read(String str) {
                PrintStream printStream = System.out;
                printStream.println(ConsoleDebugger.this.dateFormatter.format(new Date()) + " RCV  (" + ConsoleDebugger.this.connection.hashCode() + "): " + str);
            }
        };
        this.readerListener = readerListener;
        observableReader.addReaderListener(readerListener);
        ObservableWriter observableWriter = new ObservableWriter(this.writer);
        WriterListener writerListener = new WriterListener() {
            public void write(String str) {
                PrintStream printStream = System.out;
                printStream.println(ConsoleDebugger.this.dateFormatter.format(new Date()) + " SENT (" + ConsoleDebugger.this.connection.hashCode() + "): " + str);
            }
        };
        this.writerListener = writerListener;
        observableWriter.addWriterListener(writerListener);
        this.reader = observableReader;
        this.writer = observableWriter;
        this.listener = new PacketListener() {
            public void processPacket(Packet packet) {
                if (ConsoleDebugger.printInterpreted) {
                    PrintStream printStream = System.out;
                    printStream.println(ConsoleDebugger.this.dateFormatter.format(new Date()) + " RCV PKT (" + ConsoleDebugger.this.connection.hashCode() + "): " + packet.toXML());
                }
            }
        };
        this.connListener = new ConnectionListener() {
            public void connectionClosed() {
                PrintStream printStream = System.out;
                printStream.println(ConsoleDebugger.this.dateFormatter.format(new Date()) + " Connection closed (" + ConsoleDebugger.this.connection.hashCode() + ")");
            }

            public void connectionClosedOnError(Exception exc) {
                PrintStream printStream = System.out;
                printStream.println(ConsoleDebugger.this.dateFormatter.format(new Date()) + " Connection closed due to an exception (" + ConsoleDebugger.this.connection.hashCode() + ")");
                exc.printStackTrace();
            }

            public void reconnectionFailed(Exception exc) {
                PrintStream printStream = System.out;
                printStream.println(ConsoleDebugger.this.dateFormatter.format(new Date()) + " Reconnection failed due to an exception (" + ConsoleDebugger.this.connection.hashCode() + ")");
                exc.printStackTrace();
            }

            public void reconnectionSuccessful() {
                PrintStream printStream = System.out;
                printStream.println(ConsoleDebugger.this.dateFormatter.format(new Date()) + " Connection reconnected (" + ConsoleDebugger.this.connection.hashCode() + ")");
            }

            public void reconnectingIn(int i) {
                PrintStream printStream = System.out;
                printStream.println(ConsoleDebugger.this.dateFormatter.format(new Date()) + " Connection (" + ConsoleDebugger.this.connection.hashCode() + ") will reconnect in " + i);
            }
        };
    }

    public Reader newConnectionReader(Reader reader2) {
        ((ObservableReader) this.reader).removeReaderListener(this.readerListener);
        ObservableReader observableReader = new ObservableReader(reader2);
        observableReader.addReaderListener(this.readerListener);
        this.reader = observableReader;
        return observableReader;
    }

    public Writer newConnectionWriter(Writer writer2) {
        ((ObservableWriter) this.writer).removeWriterListener(this.writerListener);
        ObservableWriter observableWriter = new ObservableWriter(writer2);
        observableWriter.addWriterListener(this.writerListener);
        this.writer = observableWriter;
        return observableWriter;
    }

    public void userHasLogged(String str) {
        String str2 = "";
        boolean equals = str2.equals(StringUtils.parseName(str));
        StringBuilder sb = new StringBuilder();
        sb.append("User logged (");
        sb.append(this.connection.hashCode());
        sb.append("): ");
        if (!equals) {
            str2 = StringUtils.parseBareAddress(str);
        }
        sb.append(str2);
        sb.append("@");
        sb.append(this.connection.getServiceName());
        sb.append(":");
        sb.append(this.connection.getPort());
        String sb2 = sb.toString();
        System.out.println(sb2 + "/" + StringUtils.parseResource(str));
        this.connection.addConnectionListener(this.connListener);
    }

    public Reader getReader() {
        return this.reader;
    }

    public Writer getWriter() {
        return this.writer;
    }

    public PacketListener getReaderListener() {
        return this.listener;
    }
}
