package framework.util;

import java.util.Arrays;

public class StringUtil {

    public static String removeCommas(String[] args) {
        return Arrays.asList(args).toString().replaceAll(",", "");
    }
}
