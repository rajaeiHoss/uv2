package freemarker.debug.impl;

import freemarker.debug.DebugModel;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.TemplateTransformModel;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

class RmiDebugModelImpl extends UnicastRemoteObject implements DebugModel {
    private static final long serialVersionUID = 1;
    private final TemplateModel model;
    private final int type;

    RmiDebugModelImpl(TemplateModel templateModel, int i) throws RemoteException {
        this.model = templateModel;
        this.type = calculateType(templateModel) + i;
    }

    private static DebugModel getDebugModel(TemplateModel templateModel) throws RemoteException {
        return (DebugModel) RmiDebuggedEnvironmentImpl.getCachedWrapperFor(templateModel);
    }

    public String getAsString() throws TemplateModelException {
        return ((TemplateScalarModel) this.model).getAsString();
    }

    public Number getAsNumber() throws TemplateModelException {
        return ((TemplateNumberModel) this.model).getAsNumber();
    }

    public Date getAsDate() throws TemplateModelException {
        return ((TemplateDateModel) this.model).getAsDate();
    }

    public int getDateType() {
        return ((TemplateDateModel) this.model).getDateType();
    }

    public boolean getAsBoolean() throws TemplateModelException {
        return ((TemplateBooleanModel) this.model).getAsBoolean();
    }

    public int size() throws TemplateModelException {
        TemplateModel templateModel = this.model;
        if (templateModel instanceof TemplateSequenceModel) {
            return ((TemplateSequenceModel) templateModel).size();
        }
        return ((TemplateHashModelEx) templateModel).size();
    }

    public DebugModel get(int i) throws TemplateModelException, RemoteException {
        return getDebugModel(((TemplateSequenceModel) this.model).get(i));
    }

    public DebugModel[] get(int i, int i2) throws TemplateModelException, RemoteException {
        DebugModel[] debugModelArr = new DebugModel[(i2 - i)];
        TemplateSequenceModel templateSequenceModel = (TemplateSequenceModel) this.model;
        for (int i3 = i; i3 < i2; i3++) {
            debugModelArr[i3 - i] = getDebugModel(templateSequenceModel.get(i3));
        }
        return debugModelArr;
    }

    public DebugModel[] getCollection() throws TemplateModelException, RemoteException {
        ArrayList arrayList = new ArrayList();
        TemplateModelIterator it = ((TemplateCollectionModel) this.model).iterator();
        while (it.hasNext()) {
            arrayList.add(getDebugModel(it.next()));
        }
        return (DebugModel[]) arrayList.toArray(new DebugModel[arrayList.size()]);
    }

    public DebugModel get(String str) throws TemplateModelException, RemoteException {
        return getDebugModel(((TemplateHashModel) this.model).get(str));
    }

    public DebugModel[] get(String[] strArr) throws TemplateModelException, RemoteException {
        DebugModel[] debugModelArr = new DebugModel[strArr.length];
        TemplateHashModel templateHashModel = (TemplateHashModel) this.model;
        for (int i = 0; i < strArr.length; i++) {
            debugModelArr[i] = getDebugModel(templateHashModel.get(strArr[i]));
        }
        return debugModelArr;
    }

    public String[] keys() throws TemplateModelException {
        ArrayList arrayList = new ArrayList();
        TemplateModelIterator it = ((TemplateHashModelEx) this.model).keys().iterator();
        while (it.hasNext()) {
            arrayList.add(((TemplateScalarModel) it.next()).getAsString());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public int getModelTypes() {
        return this.type;
    }

    private static int calculateType(TemplateModel templateModel) {
        int i = templateModel instanceof TemplateScalarModel ? 1 : 0;
        if (templateModel instanceof TemplateNumberModel) {
            i += 2;
        }
        if (templateModel instanceof TemplateDateModel) {
            i += 4;
        }
        if (templateModel instanceof TemplateBooleanModel) {
            i += 8;
        }
        if (templateModel instanceof TemplateSequenceModel) {
            i += 16;
        }
        if (templateModel instanceof TemplateCollectionModel) {
            i += 32;
        }
        if (templateModel instanceof TemplateHashModelEx) {
            i += 128;
        } else if (templateModel instanceof TemplateHashModel) {
            i += 64;
        }
        if (templateModel instanceof TemplateMethodModelEx) {
            i += 512;
        } else if (templateModel instanceof TemplateMethodModel) {
            i += 256;
        }
        return templateModel instanceof TemplateTransformModel ? i + 1024 : i;
    }
}
