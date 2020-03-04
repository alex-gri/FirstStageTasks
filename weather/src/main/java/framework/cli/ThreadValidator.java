package framework.cli;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import org.testng.xml.XmlSuite.ParallelMode;

public class ThreadValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
        if (Arguments.instance().getParallel() == ParallelMode.NONE) {
            throw new ParameterException(name + " should be used only when --parallel is specified and not NONE");
        }
    }
}
