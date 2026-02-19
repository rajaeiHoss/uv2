package freemarker.core;

public class _DelayedAOrAn extends _DelayedConversionToString {
    public _DelayedAOrAn(Object obj) {
        super(obj);
    }

    /* access modifiers changed from: protected */
    public String doConversion(Object obj) {
        String obj2 = obj.toString();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(MessageUtil.getAOrAn(obj2));
        stringBuffer.append(" ");
        stringBuffer.append(obj2);
        return stringBuffer.toString();
    }
}
