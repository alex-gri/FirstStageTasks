package com.epam.tat.framework.runner.cli;

import com.beust.jcommander.IStringConverter;

public class ThreadCountConverter implements IStringConverter<Integer> {
    @Override
    public Integer convert(String s) {
        return Integer.valueOf(s);
    }
}