package com.epam.tat.framework.util;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileValidator implements IParameterValidator {

    @Override
    public void validate(String name, String path) {
        List<String> files = Arrays.asList(path.split(" "));
        files.forEach(file -> {
            File suiteXml = new File(file);
            if (!(suiteXml.exists() && suiteXml.isFile())) {
                throw new ParameterException(name + " should have valid path to file. '" +
                                             file + "' does not exists or is not a file.");
            }
        });
    }
}
