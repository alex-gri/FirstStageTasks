package com.epam.tat.framework.util;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import com.epam.tat.framework.logger.Log;
import org.testng.xml.XmlSuite.ParallelMode;

import java.util.Arrays;

public class ParallelModeValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
        try {
            ParallelMode.valueOf(value);
        } catch (IllegalArgumentException e) {
            Log.error(name + " value should be one of " + Arrays.asList(ParallelMode.values()));
        }
    }
}
