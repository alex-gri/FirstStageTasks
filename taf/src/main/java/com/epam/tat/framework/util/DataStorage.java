package com.epam.tat.framework.util;

import java.util.HashMap;

public class DataStorage {

    private static HashMap<String, Object> storage = new HashMap<>();

    public static Object getObjectByKey(String key) {
        return storage.get(key);
    }

    public static void addObject(String key, Object value) {
        storage.put(key, value);
    }
}
