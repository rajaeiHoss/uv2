package freemarker.ext.jython;

import freemarker.ext.util.ModelFactory;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateSequenceModel;
import org.python.core.PyException;
import org.python.core.PyObject;

public class JythonSequenceModel extends JythonModel implements TemplateSequenceModel, TemplateCollectionModel {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new JythonSequenceModel((PyObject) obj, (JythonWrapper) objectWrapper);
        }
    };

    public JythonSequenceModel(PyObject pyObject, JythonWrapper jythonWrapper) {
        super(pyObject, jythonWrapper);
    }

    public TemplateModel get(int i) throws TemplateModelException {
        try {
            return this.wrapper.wrap(this.object.__finditem__(i));
        } catch (PyException e) {
            throw new TemplateModelException((Exception) e);
        }
    }

    public int size() throws TemplateModelException {
        try {
            return this.object.__len__();
        } catch (PyException e) {
            throw new TemplateModelException((Exception) e);
        }
    }

    public TemplateModelIterator iterator() {
        return new TemplateModelIterator() {
            int i = 0;

            public boolean hasNext() throws TemplateModelException {
                return this.i < JythonSequenceModel.this.size();
            }

            public TemplateModel next() throws TemplateModelException {
                JythonSequenceModel jythonSequenceModel = JythonSequenceModel.this;
                int i2 = this.i;
                this.i = i2 + 1;
                return jythonSequenceModel.get(i2);
            }
        };
    }
}
