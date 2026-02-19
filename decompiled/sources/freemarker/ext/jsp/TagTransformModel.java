package freemarker.ext.jsp;

import freemarker.log.Logger;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateTransformModel;
import freemarker.template.TransformControl;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

class TagTransformModel extends JspTagModelBase implements TemplateTransformModel {
    static /* synthetic */ Class class$javax$servlet$jsp$tagext$BodyTag;
    static /* synthetic */ Class class$javax$servlet$jsp$tagext$IterationTag;
    static /* synthetic */ Class class$javax$servlet$jsp$tagext$Tag;
    static /* synthetic */ Class class$javax$servlet$jsp$tagext$TryCatchFinally;
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger("freemarker.jsp");
    /* access modifiers changed from: private */
    public final boolean isBodyTag;
    /* access modifiers changed from: private */
    public final boolean isIterationTag;
    /* access modifiers changed from: private */
    public final boolean isTryCatchFinally;

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TagTransformModel(java.lang.Class r2) throws java.beans.IntrospectionException {
        /*
            r1 = this;
            r1.<init>(r2)
            java.lang.Class r0 = class$javax$servlet$jsp$tagext$IterationTag
            if (r0 != 0) goto L_0x000f
            java.lang.String r0 = "javax.servlet.jsp.tagext.IterationTag"
            java.lang.Class r0 = class$(r0)
            class$javax$servlet$jsp$tagext$IterationTag = r0
        L_0x000f:
            boolean r0 = r0.isAssignableFrom(r2)
            r1.isIterationTag = r0
            if (r0 == 0) goto L_0x002b
            java.lang.Class r0 = class$javax$servlet$jsp$tagext$BodyTag
            if (r0 != 0) goto L_0x0023
            java.lang.String r0 = "javax.servlet.jsp.tagext.BodyTag"
            java.lang.Class r0 = class$(r0)
            class$javax$servlet$jsp$tagext$BodyTag = r0
        L_0x0023:
            boolean r0 = r0.isAssignableFrom(r2)
            if (r0 == 0) goto L_0x002b
            r0 = 1
            goto L_0x002c
        L_0x002b:
            r0 = 0
        L_0x002c:
            r1.isBodyTag = r0
            java.lang.Class r0 = class$javax$servlet$jsp$tagext$TryCatchFinally
            if (r0 != 0) goto L_0x003a
            java.lang.String r0 = "javax.servlet.jsp.tagext.TryCatchFinally"
            java.lang.Class r0 = class$(r0)
            class$javax$servlet$jsp$tagext$TryCatchFinally = r0
        L_0x003a:
            boolean r2 = r0.isAssignableFrom(r2)
            r1.isTryCatchFinally = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.jsp.TagTransformModel.<init>(java.lang.Class):void");
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Writer getWriter(Writer writer, Map map) throws TemplateModelException {
        boolean z;
        JspWriter jspWriter;
        try {
            Tag tag = (Tag) getTagInstance();
            FreeMarkerPageContext currentPageContext = PageContextFactory.getCurrentPageContext();
            Class cls = class$javax$servlet$jsp$tagext$Tag;
            if (cls == null) {
                cls = class$("javax.servlet.jsp.tagext.Tag");
                class$javax$servlet$jsp$tagext$Tag = cls;
            }
            tag.setParent((Tag) currentPageContext.peekTopTag(cls));
            tag.setPageContext(currentPageContext);
            setupTag(tag, map, currentPageContext.getObjectWrapper());
            if (!(writer instanceof JspWriter)) {
                JspWriter jspWriterAdapter = new JspWriterAdapter(writer);
                currentPageContext.pushWriter(jspWriterAdapter);
                jspWriter = jspWriterAdapter;
                z = true;
            } else if (writer == currentPageContext.getOut()) {
                jspWriter = writer;
                z = false;
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("out != pageContext.getOut(). Out is ");
                stringBuffer.append(writer);
                stringBuffer.append(" pageContext.getOut() is ");
                stringBuffer.append(currentPageContext.getOut());
                throw new TemplateModelException(stringBuffer.toString());
            }
            TagWriter tagWriter = new TagWriter(this, jspWriter, tag, currentPageContext, z);
            currentPageContext.pushTopTag(tag);
            currentPageContext.pushWriter(tagWriter);
            return tagWriter;
        } catch (TemplateModelException e) {
            throw e;
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new TemplateModelException(e3);
        }
    }

    static class BodyContentImpl extends BodyContent {
        private CharArrayWriter buf;

        public void close() throws IOException {
        }

        public int getRemaining() {
            return Integer.MAX_VALUE;
        }

        BodyContentImpl(JspWriter jspWriter, boolean z) {
            super(jspWriter);
            if (z) {
                initBuffer();
            }
        }

        /* access modifiers changed from: package-private */
        public void initBuffer() {
            this.buf = new CharArrayWriter();
        }

        public void flush() throws IOException {
            if (this.buf == null) {
                getEnclosingWriter().flush();
            }
        }

        public void clear() throws IOException {
            if (this.buf != null) {
                this.buf = new CharArrayWriter();
                return;
            }
            throw new IOException("Can't clear");
        }

        public void clearBuffer() throws IOException {
            if (this.buf != null) {
                this.buf = new CharArrayWriter();
                return;
            }
            throw new IOException("Can't clear");
        }

        public void newLine() throws IOException {
            write(JspWriterAdapter.NEWLINE);
        }

        public void print(boolean z) throws IOException {
            write((z ? Boolean.TRUE : Boolean.FALSE).toString());
        }

        public void print(char c) throws IOException {
            write(c);
        }

        public void print(char[] cArr) throws IOException {
            write(cArr);
        }

        public void print(double d) throws IOException {
            write(Double.toString(d));
        }

        public void print(float f) throws IOException {
            write(Float.toString(f));
        }

        public void print(int i) throws IOException {
            write(Integer.toString(i));
        }

        public void print(long j) throws IOException {
            write(Long.toString(j));
        }

        public void print(Object obj) throws IOException {
            write(obj == null ? "null" : obj.toString());
        }

        public void print(String str) throws IOException {
            write(str);
        }

        public void println() throws IOException {
            newLine();
        }

        public void println(boolean z) throws IOException {
            print(z);
            newLine();
        }

        public void println(char c) throws IOException {
            print(c);
            newLine();
        }

        public void println(char[] cArr) throws IOException {
            print(cArr);
            newLine();
        }

        public void println(double d) throws IOException {
            print(d);
            newLine();
        }

        public void println(float f) throws IOException {
            print(f);
            newLine();
        }

        public void println(int i) throws IOException {
            print(i);
            newLine();
        }

        public void println(long j) throws IOException {
            print(j);
            newLine();
        }

        public void println(Object obj) throws IOException {
            print(obj);
            newLine();
        }

        public void println(String str) throws IOException {
            print(str);
            newLine();
        }

        public void write(int i) throws IOException {
            CharArrayWriter charArrayWriter = this.buf;
            if (charArrayWriter != null) {
                charArrayWriter.write(i);
            } else {
                getEnclosingWriter().write(i);
            }
        }

        public void write(char[] cArr, int i, int i2) throws IOException {
            CharArrayWriter charArrayWriter = this.buf;
            if (charArrayWriter != null) {
                charArrayWriter.write(cArr, i, i2);
            } else {
                getEnclosingWriter().write(cArr, i, i2);
            }
        }

        public String getString() {
            return this.buf.toString();
        }

        public Reader getReader() {
            return new CharArrayReader(this.buf.toCharArray());
        }

        public void writeOut(Writer writer) throws IOException {
            this.buf.writeTo(writer);
        }
    }

    class TagWriter extends BodyContentImpl implements TransformControl {
        private final boolean needDoublePop;
        private boolean needPop = true;
        private final FreeMarkerPageContext pageContext;
        private final Tag tag;
        private final /* synthetic */ TagTransformModel this$0;

        TagWriter(TagTransformModel tagTransformModel, Writer writer, Tag tag2, FreeMarkerPageContext freeMarkerPageContext, boolean z) {
            super((JspWriter) writer, false);
            this.this$0 = tagTransformModel;
            this.needDoublePop = z;
            this.tag = tag2;
            this.pageContext = freeMarkerPageContext;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("TagWriter for ");
            stringBuffer.append(this.tag.getClass().getName());
            stringBuffer.append(" wrapping a ");
            stringBuffer.append(getEnclosingWriter().toString());
            return stringBuffer.toString();
        }

        /* access modifiers changed from: package-private */
        public Tag getTag() {
            return this.tag;
        }

        /* access modifiers changed from: package-private */
        public FreeMarkerPageContext getPageContext() {
            return this.pageContext;
        }

        public int onStart() throws TemplateModelException {
            try {
                int doStartTag = this.tag.doStartTag();
                if (doStartTag != 0) {
                    if (doStartTag != 1) {
                        if (doStartTag != 2) {
                            if (doStartTag != 6) {
                                StringBuffer stringBuffer = new StringBuffer();
                                stringBuffer.append("Illegal return value ");
                                stringBuffer.append(doStartTag);
                                stringBuffer.append(" from ");
                                stringBuffer.append(this.tag.getClass().getName());
                                stringBuffer.append(".doStartTag()");
                                throw new RuntimeException(stringBuffer.toString());
                            }
                        } else if (this.this$0.isBodyTag) {
                            initBuffer();
                            BodyTag bodyTag = this.tag;
                            bodyTag.setBodyContent(this);
                            bodyTag.doInitBody();
                        } else {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append("Can't buffer body since ");
                            stringBuffer2.append(this.tag.getClass().getName());
                            stringBuffer2.append(" does not implement BodyTag.");
                            throw new TemplateModelException(stringBuffer2.toString());
                        }
                    }
                    return 1;
                }
                endEvaluation();
                return 0;
            } catch (JspException e) {
                throw new TemplateModelException(e.getMessage(), (Exception) e);
            }
        }

        public int afterBody() throws TemplateModelException {
            try {
                if (this.this$0.isIterationTag) {
                    int doAfterBody = this.tag.doAfterBody();
                    if (doAfterBody == 0) {
                        endEvaluation();
                        return 1;
                    } else if (doAfterBody == 2) {
                        return 0;
                    } else {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Unexpected return value ");
                        stringBuffer.append(doAfterBody);
                        stringBuffer.append("from ");
                        stringBuffer.append(this.tag.getClass().getName());
                        stringBuffer.append(".doAfterBody()");
                        throw new TemplateModelException(stringBuffer.toString());
                    }
                } else {
                    endEvaluation();
                    return 1;
                }
            } catch (JspException e) {
                throw new TemplateModelException((Exception) e);
            }
        }

        private void endEvaluation() throws JspException {
            if (this.needPop) {
                this.pageContext.popWriter();
                this.needPop = false;
            }
            if (this.tag.doEndTag() == 5) {
                Logger access$200 = TagTransformModel.logger;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Tag.SKIP_PAGE was ignored from a ");
                stringBuffer.append(this.tag.getClass().getName());
                stringBuffer.append(" tag.");
                access$200.warn(stringBuffer.toString());
            }
        }

        public void onError(Throwable th) throws Throwable {
            if (this.this$0.isTryCatchFinally) {
                this.tag.doCatch(th);
                return;
            }
            throw th;
        }

        public void close() {
            if (this.needPop) {
                this.pageContext.popWriter();
            }
            this.pageContext.popTopTag();
            try {
                if (this.this$0.isTryCatchFinally) {
                    this.tag.doFinally();
                }
                this.tag.release();
            } finally {
                if (this.needDoublePop) {
                    this.pageContext.popWriter();
                }
            }
        }
    }
}
