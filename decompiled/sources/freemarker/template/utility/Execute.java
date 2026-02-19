package freemarker.template.utility;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Execute implements TemplateMethodModel {
    private static final int OUTPUT_BUFFER_SIZE = 1024;

    public Object exec(List list) throws TemplateModelException {
        StringBuffer stringBuffer = new StringBuffer();
        if (list.size() >= 1) {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(Runtime.getRuntime().exec((String) list.get(0)).getInputStream());
                char[] cArr = new char[1024];
                for (int read = inputStreamReader.read(cArr); read > 0; read = inputStreamReader.read(cArr)) {
                    stringBuffer.append(cArr, 0, read);
                }
                return stringBuffer.toString();
            } catch (IOException e) {
                throw new TemplateModelException(e.getMessage());
            }
        } else {
            throw new TemplateModelException("Need an argument to execute");
        }
    }
}
