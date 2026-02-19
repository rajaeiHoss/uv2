package freemarker.core;

import com.google.firebase.analytics.FirebaseAnalytics;
import freemarker.ext.beans.CollectionModel;
import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateModelListSequence;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.Constants;
import freemarker.template.utility.StringUtil;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import org.jivesoftware.smackx.FormField;

class SequenceBuiltins {
    private SequenceBuiltins() {
    }

    private static abstract class SequenceBuiltIn extends BuiltIn {
        /* access modifiers changed from: package-private */
        public abstract TemplateModel calculateResult(TemplateSequenceModel templateSequenceModel) throws TemplateModelException;

        private SequenceBuiltIn() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            if (eval instanceof TemplateSequenceModel) {
                return calculateResult((TemplateSequenceModel) eval);
            }
            throw new UnexpectedTypeException(this.target, eval, "sequence", environment);
        }
    }

    static class firstBI extends SequenceBuiltIn {
        firstBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateSequenceModel templateSequenceModel) throws TemplateModelException {
            if (templateSequenceModel.size() == 0) {
                return null;
            }
            return templateSequenceModel.get(0);
        }
    }

    static class lastBI extends SequenceBuiltIn {
        lastBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateSequenceModel templateSequenceModel) throws TemplateModelException {
            if (templateSequenceModel.size() == 0) {
                return null;
            }
            return templateSequenceModel.get(templateSequenceModel.size() - 1);
        }
    }

    static class reverseBI extends SequenceBuiltIn {
        reverseBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateSequenceModel templateSequenceModel) {
            if (templateSequenceModel instanceof ReverseSequence) {
                return ((ReverseSequence) templateSequenceModel).seq;
            }
            return new ReverseSequence(templateSequenceModel);
        }

        private static class ReverseSequence implements TemplateSequenceModel {
            /* access modifiers changed from: private */
            public final TemplateSequenceModel seq;

            ReverseSequence(TemplateSequenceModel templateSequenceModel) {
                this.seq = templateSequenceModel;
            }

            public int size() throws TemplateModelException {
                return this.seq.size();
            }

            public TemplateModel get(int i) throws TemplateModelException {
                TemplateSequenceModel templateSequenceModel = this.seq;
                return templateSequenceModel.get((templateSequenceModel.size() - 1) - i);
            }
        }
    }

    static class sortBI extends SequenceBuiltIn {
        static final int KEY_TYPE_BOOLEAN = 4;
        static final int KEY_TYPE_DATE = 3;
        static final int KEY_TYPE_NOT_YET_DETECTED = 0;
        static final int KEY_TYPE_NUMBER = 2;
        static final int KEY_TYPE_STRING = 1;

        static Object[] startErrorMessage(int i) {
            Object[] objArr = new Object[2];
            objArr[0] = i == 0 ? "?sort" : "?sort_by(...)";
            objArr[1] = " failed: ";
            return objArr;
        }

        sortBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateSequenceModel templateSequenceModel) throws TemplateModelException {
            return sort(templateSequenceModel, (String[]) null);
        }

        static Object[] startErrorMessage(int i, int i2) {
            Object[] objArr = new Object[4];
            objArr[0] = i == 0 ? "?sort" : "?sort_by(...)";
            objArr[1] = " failed at sequence index ";
            objArr[2] = new Integer(i2);
            objArr[3] = i2 == 0 ? ": " : " (0-based): ";
            return objArr;
        }

        static TemplateModelException newInconsistentSortKeyTypeException(int i, String str, String str2, int i2, TemplateModel templateModel) {
            String str3;
            String str4;
            if (i == 0) {
                str4 = FirebaseAnalytics.Param.VALUE;
                str3 = "values";
            } else {
                str4 = "key value";
                str3 = "key values";
            }
            return new _TemplateModelException(new Object[]{startErrorMessage(i, i2), "All ", str3, " in the sequence must be ", str2, ", because the first ", str4, " was that. However, the ", str4, " of the current item isn't a ", str, " but a ", new _DelayedFTLTypeDescription(templateModel), "."});
        }

        static TemplateSequenceModel sort(TemplateSequenceModel templateSequenceModel, String[] strArr) throws TemplateModelException {
            int i;
            String str;
            TemplateSequenceModel templateSequenceModel2 = templateSequenceModel;
            String[] strArr2 = strArr;
            int size = templateSequenceModel.size();
            if (size == 0) {
                return templateSequenceModel2;
            }
            ArrayList arrayList = new ArrayList(size);
            if (strArr2 == null) {
                i = 0;
            } else {
                i = strArr2.length;
            }
            Comparator comparator = null;
            char c = 0;
            for (int i2 = 0; i2 < size; i2++) {
                TemplateModel templateModel = templateSequenceModel2.get(i2);
                TemplateModel templateModel2 = templateModel;
                int i3 = 0;
                while (i3 < i) {
                    try {
                        templateModel2 = ((TemplateHashModel) templateModel2).get(strArr2[i3]);
                        if (templateModel2 != null) {
                            i3++;
                        } else {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("The ");
                            stringBuffer.append(StringUtil.jQuote(strArr2[i3]));
                            throw new _TemplateModelException(new Object[]{startErrorMessage(i, i2), stringBuffer.toString(), " subvariable was not found."});
                        }
                    } catch (ClassCastException e) {
                        if (!(templateModel2 instanceof TemplateHashModel)) {
                            Object[] objArr = new Object[6];
                            objArr[0] = startErrorMessage(i, i2);
                            if (i3 == 0) {
                                str = "Sequence items must be hashes when using ?sort_by. ";
                            } else {
                                StringBuffer stringBuffer2 = new StringBuffer();
                                stringBuffer2.append("The ");
                                stringBuffer2.append(StringUtil.jQuote(strArr2[i3 - 1]));
                                str = stringBuffer2.toString();
                            }
                            objArr[1] = str;
                            objArr[2] = " subvariable is not a hash, so ?sort_by ";
                            objArr[3] = "can't proceed with getting the ";
                            objArr[4] = new _DelayedJQuote(strArr2[i3]);
                            objArr[5] = " subvariable.";
                            throw new _TemplateModelException(objArr);
                        }
                        throw e;
                    }
                }
                if (c == 0) {
                    if (templateModel2 instanceof TemplateScalarModel) {
                        comparator = new LexicalKVPComparator(Environment.getCurrentEnvironment().getCollator());
                        c = 1;
                    } else if (templateModel2 instanceof TemplateNumberModel) {
                        comparator = new NumericalKVPComparator(Environment.getCurrentEnvironment().getArithmeticEngine());
                        c = 2;
                    } else if (templateModel2 instanceof TemplateDateModel) {
                        comparator = new DateKVPComparator();
                        c = 3;
                    } else if (templateModel2 instanceof TemplateBooleanModel) {
                        comparator = new BooleanKVPComparator();
                        c = 4;
                    } else {
                        throw new _TemplateModelException(new Object[]{startErrorMessage(i, i2), "Values used for sorting must be numbers, strings, date/times or booleans."});
                    }
                }
                if (c == 1) {
                    try {
                        arrayList.add(new KVP(((TemplateScalarModel) templateModel2).getAsString(), templateModel));
                    } catch (ClassCastException e2) {
                        if (!(templateModel2 instanceof TemplateScalarModel)) {
                            throw newInconsistentSortKeyTypeException(i, "string", "strings", i2, templateModel2);
                        }
                        throw e2;
                    }
                } else if (c == 2) {
                    try {
                        arrayList.add(new KVP(((TemplateNumberModel) templateModel2).getAsNumber(), templateModel));
                    } catch (ClassCastException unused) {
                        if (!(templateModel2 instanceof TemplateNumberModel)) {
                            throw newInconsistentSortKeyTypeException(i, "number", "numbers", i2, templateModel2);
                        }
                    }
                } else if (c == 3) {
                    try {
                        arrayList.add(new KVP(((TemplateDateModel) templateModel2).getAsDate(), templateModel));
                    } catch (ClassCastException unused2) {
                        if (!(templateModel2 instanceof TemplateDateModel)) {
                            throw newInconsistentSortKeyTypeException(i, "date/time", "date/times", i2, templateModel2);
                        }
                    }
                } else if (c == 4) {
                    try {
                        arrayList.add(new KVP(((TemplateBooleanModel) templateModel2).getAsBoolean() ? Boolean.TRUE : Boolean.FALSE, templateModel));
                    } catch (ClassCastException unused3) {
                        if (!(templateModel2 instanceof TemplateBooleanModel)) {
                            throw newInconsistentSortKeyTypeException(i, FormField.TYPE_BOOLEAN, "booleans", i2, templateModel2);
                        }
                    }
                } else {
                    throw new RuntimeException("FreeMarker bug: Unexpected key type");
                }
            }
            try {
                Collections.sort(arrayList, comparator);
                for (int i4 = 0; i4 < size; i4++) {
                    arrayList.set(i4, ((KVP) arrayList.get(i4)).value);
                }
                return new TemplateModelListSequence(arrayList);
            } catch (Exception e3) {
                Exception exc = e3;
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Unexpected error while sorting:");
                stringBuffer3.append(exc);
                throw new _TemplateModelException((Throwable) exc, new Object[]{startErrorMessage(i), stringBuffer3.toString()});
            }
        }

        private static class KVP {
            /* access modifiers changed from: private */
            public Object key;
            /* access modifiers changed from: private */
            public Object value;

            private KVP(Object obj, Object obj2) {
                this.key = obj;
                this.value = obj2;
            }
        }

        private static class NumericalKVPComparator implements Comparator {
            private ArithmeticEngine ae;

            private NumericalKVPComparator(ArithmeticEngine arithmeticEngine) {
                this.ae = arithmeticEngine;
            }

            public int compare(Object obj, Object obj2) {
                try {
                    return this.ae.compareNumbers((Number) ((KVP) obj).key, (Number) ((KVP) obj2).key);
                } catch (TemplateException e) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Failed to compare numbers: ");
                    stringBuffer.append(e);
                    throw new ClassCastException(stringBuffer.toString());
                }
            }
        }

        private static class LexicalKVPComparator implements Comparator {
            private Collator collator;

            LexicalKVPComparator(Collator collator2) {
                this.collator = collator2;
            }

            public int compare(Object obj, Object obj2) {
                return this.collator.compare(((KVP) obj).key, ((KVP) obj2).key);
            }
        }

        private static class DateKVPComparator implements Comparator, Serializable {
            private DateKVPComparator() {
            }

            public int compare(Object obj, Object obj2) {
                return ((Date) ((KVP) obj).key).compareTo((Date) ((KVP) obj2).key);
            }
        }

        private static class BooleanKVPComparator implements Comparator, Serializable {
            private BooleanKVPComparator() {
            }

            public int compare(Object obj, Object obj2) {
                boolean booleanValue = ((Boolean) ((KVP) obj).key).booleanValue();
                boolean booleanValue2 = ((Boolean) ((KVP) obj2).key).booleanValue();
                return booleanValue ? booleanValue2 ^ true ? 1 : 0 : booleanValue2 ? -1 : 0;
            }
        }
    }

    static class sort_byBI extends sortBI {
        sort_byBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateSequenceModel templateSequenceModel) {
            return new BIMethod(templateSequenceModel);
        }

        class BIMethod implements TemplateMethodModelEx {
            TemplateSequenceModel seq;

            BIMethod(TemplateSequenceModel templateSequenceModel) {
                this.seq = templateSequenceModel;
            }

            public Object exec(List list) throws TemplateModelException {
                String[] strArr;
                if (list.size() >= 1) {
                    Object obj = list.get(0);
                    if (obj instanceof TemplateScalarModel) {
                        strArr = new String[]{((TemplateScalarModel) obj).getAsString()};
                    } else if (obj instanceof TemplateSequenceModel) {
                        TemplateSequenceModel templateSequenceModel = (TemplateSequenceModel) obj;
                        int size = templateSequenceModel.size();
                        String[] strArr2 = new String[size];
                        for (int i = 0; i < size; i++) {
                            TemplateModel templateModel = templateSequenceModel.get(i);
                            try {
                                strArr2[i] = ((TemplateScalarModel) templateModel).getAsString();
                            } catch (ClassCastException unused) {
                                if (!(templateModel instanceof TemplateScalarModel)) {
                                    throw new _TemplateModelException(new Object[]{"The argument to ?", sort_byBI.this.key, "(key), when it's a sequence, must be a sequence of strings, but the item at index ", new Integer(i), " is not a string."});
                                }
                            }
                        }
                        strArr = strArr2;
                    } else {
                        throw new _TemplateModelException(new Object[]{"The argument to ?", sort_byBI.this.key, "(key) must be a string (the name of the subvariable), or a sequence of strings (the \"path\" to the subvariable)."});
                    }
                    return sortBI.sort(this.seq, strArr);
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("?");
                stringBuffer.append(sort_byBI.this.key);
                throw MessageUtil.newArgCntError(stringBuffer.toString(), list.size(), 1);
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean isBuggySeqButGoodCollection(TemplateModel templateModel) {
        return (templateModel instanceof CollectionModel) && !((CollectionModel) templateModel).getSupportsIndexedAccess();
    }

    static class seq_containsBI extends BuiltIn {
        seq_containsBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            if ((eval instanceof TemplateSequenceModel) && !SequenceBuiltins.isBuggySeqButGoodCollection(eval)) {
                return new BIMethodForSequence((TemplateSequenceModel) eval, environment);
            }
            if (eval instanceof TemplateCollectionModel) {
                return new BIMethodForCollection((TemplateCollectionModel) eval, environment);
            }
            throw new UnexpectedTypeException(this.target, eval, "sequence or collection", environment);
        }

        private class BIMethodForSequence implements TemplateMethodModelEx {
            private Environment m_env;
            private TemplateSequenceModel m_seq;

            private BIMethodForSequence(TemplateSequenceModel templateSequenceModel, Environment environment) {
                this.m_seq = templateSequenceModel;
                this.m_env = environment;
            }

            public Object exec(List list) throws TemplateModelException {
                seq_containsBI.this.checkMethodArgCount(list, 1);
                TemplateModel templateModel = (TemplateModel) list.get(0);
                int size = this.m_seq.size();
                for (int i = 0; i < size; i++) {
                    if (SequenceBuiltins.modelsEqual(i, this.m_seq.get(i), templateModel, this.m_env)) {
                        return TemplateBooleanModel.TRUE;
                    }
                }
                return TemplateBooleanModel.FALSE;
            }
        }

        private class BIMethodForCollection implements TemplateMethodModelEx {
            private TemplateCollectionModel m_coll;
            private Environment m_env;

            private BIMethodForCollection(TemplateCollectionModel templateCollectionModel, Environment environment) {
                this.m_coll = templateCollectionModel;
                this.m_env = environment;
            }

            public Object exec(List list) throws TemplateModelException {
                seq_containsBI.this.checkMethodArgCount(list, 1);
                int i = 0;
                TemplateModel templateModel = (TemplateModel) list.get(0);
                TemplateModelIterator it = this.m_coll.iterator();
                while (it.hasNext()) {
                    if (SequenceBuiltins.modelsEqual(i, it.next(), templateModel, this.m_env)) {
                        return TemplateBooleanModel.TRUE;
                    }
                    i++;
                }
                return TemplateBooleanModel.FALSE;
            }
        }
    }

    static class seq_index_ofBI extends BuiltIn {
        /* access modifiers changed from: private */
        public int m_dir;

        public seq_index_ofBI(int i) {
            this.m_dir = i;
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            return new BIMethod(environment);
        }

        private class BIMethod implements TemplateMethodModelEx {
            protected final TemplateCollectionModel m_col;
            protected final Environment m_env;
            protected final TemplateSequenceModel m_seq;

            private BIMethod(Environment environment) throws TemplateException {
                TemplateModel eval = seq_index_ofBI.this.target.eval(environment);
                TemplateCollectionModel templateCollectionModel = null;
                TemplateSequenceModel templateSequenceModel = (!(eval instanceof TemplateSequenceModel) || SequenceBuiltins.isBuggySeqButGoodCollection(eval)) ? null : (TemplateSequenceModel) eval;
                this.m_seq = templateSequenceModel;
                if (templateSequenceModel == null && (eval instanceof TemplateCollectionModel)) {
                    templateCollectionModel = (TemplateCollectionModel) eval;
                }
                this.m_col = templateCollectionModel;
                if (templateSequenceModel == null && templateCollectionModel == null) {
                    throw new UnexpectedTypeException(seq_index_ofBI.this.target, eval, "sequence or collection", environment);
                }
                this.m_env = environment;
            }

            public final Object exec(List list) throws TemplateModelException {
                int i;
                int size = list.size();
                seq_index_ofBI.this.checkMethodArgCount(size, 1, 2);
                TemplateModel templateModel = (TemplateModel) list.get(0);
                if (size > 1) {
                    int intValue = seq_index_ofBI.this.getNumberMethodArg(list, 1).intValue();
                    i = this.m_seq != null ? findInSeq(templateModel, intValue) : findInCol(templateModel, intValue);
                } else {
                    i = this.m_seq != null ? findInSeq(templateModel) : findInCol(templateModel);
                }
                return i == -1 ? Constants.MINUS_ONE : new SimpleNumber(i);
            }

            public int findInSeq(TemplateModel templateModel) throws TemplateModelException {
                int size = this.m_seq.size();
                return findInSeq(templateModel, seq_index_ofBI.this.m_dir == 1 ? 0 : size - 1, size);
            }

            private int findInSeq(TemplateModel templateModel, int i) throws TemplateModelException {
                int size = this.m_seq.size();
                if (seq_index_ofBI.this.m_dir != 1) {
                    if (i >= size) {
                        i = size - 1;
                    }
                    if (i < 0) {
                        return -1;
                    }
                } else if (i >= size) {
                    return -1;
                } else {
                    if (i < 0) {
                        i = 0;
                    }
                }
                return findInSeq(templateModel, i, size);
            }

            private int findInSeq(TemplateModel templateModel, int i, int i2) throws TemplateModelException {
                if (seq_index_ofBI.this.m_dir == 1) {
                    while (i < i2) {
                        if (SequenceBuiltins.modelsEqual(i, this.m_seq.get(i), templateModel, this.m_env)) {
                            return i;
                        }
                        i++;
                    }
                    return -1;
                }
                while (i >= 0) {
                    if (SequenceBuiltins.modelsEqual(i, this.m_seq.get(i), templateModel, this.m_env)) {
                        return i;
                    }
                    i--;
                }
                return -1;
            }

            public int findInCol(TemplateModel templateModel) throws TemplateModelException {
                return findInCol(templateModel, 0, Integer.MAX_VALUE);
            }

            /* access modifiers changed from: protected */
            public int findInCol(TemplateModel templateModel, int i) throws TemplateModelException {
                if (seq_index_ofBI.this.m_dir == 1) {
                    return findInCol(templateModel, i, Integer.MAX_VALUE);
                }
                return findInCol(templateModel, 0, i);
            }

            /* access modifiers changed from: protected */
            public int findInCol(TemplateModel templateModel, int i, int i2) throws TemplateModelException {
                int i3 = -1;
                if (i2 < 0) {
                    return -1;
                }
                TemplateModelIterator it = this.m_col.iterator();
                int i4 = 0;
                while (it.hasNext() && i4 <= i2) {
                    TemplateModel next = it.next();
                    if (i4 >= i && SequenceBuiltins.modelsEqual(i4, next, templateModel, this.m_env)) {
                        if (seq_index_ofBI.this.m_dir == 1) {
                            return i4;
                        }
                        i3 = i4;
                    }
                    i4++;
                }
                return i3;
            }
        }
    }

    static class chunkBI extends SequenceBuiltIn {
        chunkBI() {
            super();
        }

        /* access modifiers changed from: package-private */
        public TemplateModel calculateResult(TemplateSequenceModel templateSequenceModel) throws TemplateModelException {
            return new BIMethod(templateSequenceModel);
        }

        private class BIMethod implements TemplateMethodModelEx {
            private final TemplateSequenceModel tsm;

            private BIMethod(TemplateSequenceModel templateSequenceModel) {
                this.tsm = templateSequenceModel;
            }

            public Object exec(List list) throws TemplateModelException {
                chunkBI.this.checkMethodArgCount(list, 1, 2);
                return new ChunkedSequence(this.tsm, chunkBI.this.getNumberMethodArg(list, 0).intValue(), list.size() > 1 ? (TemplateModel) list.get(1) : null);
            }
        }

        private static class ChunkedSequence implements TemplateSequenceModel {
            /* access modifiers changed from: private */
            public final int chunkSize;
            /* access modifiers changed from: private */
            public final TemplateModel fillerItem;
            /* access modifiers changed from: private */
            public final int numberOfChunks;
            /* access modifiers changed from: private */
            public final TemplateSequenceModel wrappedTsm;

            private ChunkedSequence(TemplateSequenceModel templateSequenceModel, int i, TemplateModel templateModel) throws TemplateModelException {
                if (i >= 1) {
                    this.wrappedTsm = templateSequenceModel;
                    this.chunkSize = i;
                    this.fillerItem = templateModel;
                    this.numberOfChunks = ((templateSequenceModel.size() + i) - 1) / i;
                    return;
                }
                throw new _TemplateModelException(new Object[]{"The 1st argument to ?', key, ' (...) must be at least 1."});
            }

            public TemplateModel get(int i) throws TemplateModelException {
                if (i >= this.numberOfChunks) {
                    return null;
                }
                return new TemplateSequenceModel(i) {
                    private final int baseIndex;
                    private final /* synthetic */ int val$chunkIndex;

                    {
                        this.val$chunkIndex = r2;
                        this.baseIndex = r2 * ChunkedSequence.this.chunkSize;
                    }

                    public TemplateModel get(int i) throws TemplateModelException {
                        int i2 = this.baseIndex + i;
                        if (i2 < ChunkedSequence.this.wrappedTsm.size()) {
                            return ChunkedSequence.this.wrappedTsm.get(i2);
                        }
                        if (i2 < ChunkedSequence.this.numberOfChunks * ChunkedSequence.this.chunkSize) {
                            return ChunkedSequence.this.fillerItem;
                        }
                        return null;
                    }

                    public int size() throws TemplateModelException {
                        return (ChunkedSequence.this.fillerItem != null || this.val$chunkIndex + 1 < ChunkedSequence.this.numberOfChunks) ? ChunkedSequence.this.chunkSize : ChunkedSequence.this.wrappedTsm.size() - this.baseIndex;
                    }
                };
            }

            public int size() throws TemplateModelException {
                return this.numberOfChunks;
            }
        }
    }

    public static boolean modelsEqual(int i, TemplateModel templateModel, TemplateModel templateModel2, Environment environment) throws TemplateModelException {
        try {
            return EvalUtil.compare(templateModel, (Expression) null, 1, (String) null, templateModel2, (Expression) null, (Expression) null, true, true, true, environment);
        } catch (TemplateException e) {
            throw new _TemplateModelException((Throwable) e, new Object[]{"This error has occured when comparing sequence item at 0-based index ", new Integer(i), " to the searched item:\n", new _DelayedGetMessage(e)});
        }
    }

    static class joinBI extends BuiltIn {
        joinBI() {
        }

        /* access modifiers changed from: package-private */
        public TemplateModel _eval(Environment environment) throws TemplateException {
            TemplateModel eval = this.target.eval(environment);
            if (eval instanceof TemplateCollectionModel) {
                return new BIMethodForCollection(environment, (TemplateCollectionModel) eval);
            }
            if (eval instanceof TemplateSequenceModel) {
                return new BIMethodForCollection(environment, new CollectionAndSequence((TemplateSequenceModel) eval));
            }
            throw new UnexpectedTypeException(this.target, eval, "sequence or collection", environment);
        }

        private class BIMethodForCollection implements TemplateMethodModelEx {
            private final TemplateCollectionModel coll;
            private final Environment env;

            private BIMethodForCollection(Environment environment, TemplateCollectionModel templateCollectionModel) {
                this.env = environment;
                this.coll = templateCollectionModel;
            }

            public Object exec(List list) throws TemplateModelException {
                joinBI.this.checkMethodArgCount(list, 1, 3);
                String stringMethodArg = joinBI.this.getStringMethodArg(list, 0);
                String optStringMethodArg = joinBI.this.getOptStringMethodArg(list, 1);
                String optStringMethodArg2 = joinBI.this.getOptStringMethodArg(list, 2);
                StringBuffer stringBuffer = new StringBuffer();
                TemplateModelIterator it = this.coll.iterator();
                boolean z = false;
                int i = 0;
                while (it.hasNext()) {
                    TemplateModel next = it.next();
                    if (next != null) {
                        if (z) {
                            stringBuffer.append(stringMethodArg);
                        } else {
                            z = true;
                        }
                        try {
                            stringBuffer.append(EvalUtil.coerceModelToString(next, (Expression) null, (String) null, this.env));
                        } catch (TemplateException e) {
                            throw new _TemplateModelException((Throwable) e, new Object[]{"\"?", joinBI.this.key, "\" failed at index ", new Integer(i), " with this error:\n\n", "---begin-message---\n", new _DelayedGetMessageWithoutStackTop(e), "\n---end-message---"});
                        }
                    }
                    i++;
                }
                if (z) {
                    if (optStringMethodArg2 != null) {
                        stringBuffer.append(optStringMethodArg2);
                    }
                } else if (optStringMethodArg != null) {
                    stringBuffer.append(optStringMethodArg);
                }
                return new SimpleScalar(stringBuffer.toString());
            }
        }
    }
}
