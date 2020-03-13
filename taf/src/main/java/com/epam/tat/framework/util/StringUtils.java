package com.epam.tat.framework.util;

import java.util.Arrays;

public class StringUtils {

    public static String removeCommas(String[] args) {
        return Arrays.asList(args).toString().replaceAll(",", "");
    }
}
