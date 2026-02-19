package freemarker.ext.jsp;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.Map;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;

class SimpleTagDirectiveModel extends JspTagModelBase implements TemplateDirectiveModel {
    static /* synthetic */ Class class$javax$servlet$jsp$tagext$JspTag;
    static /* synthetic */ Class class$javax$servlet$jsp$tagext$SimpleTag;
    static /* synthetic */ Class class$javax$servlet$jsp$tagext$Tag;

    protected SimpleTagDirectiveModel(Class cls) throws IntrospectionException {
        super(cls);
        Class cls2 = class$javax$servlet$jsp$tagext$SimpleTag;
        if (cls2 == null) {
            cls2 = class$("javax.servlet.jsp.tagext.SimpleTag");
            class$javax$servlet$jsp$tagext$SimpleTag = cls2;
        }
        if (!cls2.isAssignableFrom(cls)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(cls.getName());
            stringBuffer.append(" does not implement either the ");
            Class cls3 = class$javax$servlet$jsp$tagext$Tag;
            if (cls3 == null) {
                cls3 = class$("javax.servlet.jsp.tagext.Tag");
                class$javax$servlet$jsp$tagext$Tag = cls3;
            }
            stringBuffer.append(cls3.getName());
            stringBuffer.append(" interface or the ");
            Class cls4 = class$javax$servlet$jsp$tagext$SimpleTag;
            if (cls4 == null) {
                cls4 = class$("javax.servlet.jsp.tagext.SimpleTag");
                class$javax$servlet$jsp$tagext$SimpleTag = cls4;
            }
            stringBuffer.append(cls4.getName());
            stringBuffer.append(" interface.");
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public void execute(Environment environment, Map map, TemplateModel[] templateModelArr, final TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        try {
            SimpleTag simpleTag = (SimpleTag) getTagInstance();
            final FreeMarkerPageContext currentPageContext = PageContextFactory.getCurrentPageContext();
            currentPageContext.pushWriter(new JspWriterAdapter(environment.getOut()));
            try {
                simpleTag.setJspContext(currentPageContext);
                Class cls = class$javax$servlet$jsp$tagext$JspTag;
                if (cls == null) {
                    cls = class$("javax.servlet.jsp.tagext.JspTag");
                    class$javax$servlet$jsp$tagext$JspTag = cls;
                }
                JspTag jspTag = (JspTag) currentPageContext.peekTopTag(cls);
                if (jspTag != null) {
                    simpleTag.setParent(jspTag);
                }
                setupTag(simpleTag, map, currentPageContext.getObjectWrapper());
                if (templateDirectiveBody != null) {
                    simpleTag.setJspBody(new JspFragment() {
                        public JspContext getJspContext() {
                            return currentPageContext;
                        }

                        public void invoke(JspWriter jspWriter) throws JspException, IOException {
                            try {
                                TemplateDirectiveBody templateDirectiveBody = templateDirectiveBody;
                                if (jspWriter == null) {
                                    jspWriter = currentPageContext.getOut();
                                }
                                templateDirectiveBody.render(jspWriter);
                            } catch (TemplateException e) {
                                throw new JspException(e);
                            }
                        }
                    });
                    currentPageContext.pushTopTag(simpleTag);
                    simpleTag.doTag();
                    currentPageContext.popTopTag();
                } else {
                    simpleTag.doTag();
                }
                currentPageContext.popWriter();
            } catch (Throwable th) {
                currentPageContext.popWriter();
                throw th;
            }
        } catch (TemplateException e) {
            throw e;
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new TemplateModelException(e3);
        }
    }
}
