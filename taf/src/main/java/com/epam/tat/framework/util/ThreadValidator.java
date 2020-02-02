package com.epam.tat.framework.util;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import com.epam.tat.framework.runner.Arguments;
import org.testng.xml.XmlSuite;

public class ThreadValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
        if (Arguments.instance().getParallel() == XmlSuite.ParallelMode.NONE) {
            throw new ParameterException(name + " should be used only when --parallel is specified and not NONE");
        }
    }
}
