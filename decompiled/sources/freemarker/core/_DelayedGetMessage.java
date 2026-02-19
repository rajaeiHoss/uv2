package freemarker.core;

public class _DelayedGetMessage extends _DelayedConversionToString {
    public _DelayedGetMessage(Throwable th) {
        super(th);
    }

    /* access modifiers changed from: protected */
    public String doConversion(Object obj) {
        return ((Throwable) obj).getMessage();
    }
}
