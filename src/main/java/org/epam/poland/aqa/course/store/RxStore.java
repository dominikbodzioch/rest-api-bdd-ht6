package org.epam.poland.aqa.course.store;

import java.util.HashMap;
import java.util.Map;

public class RxStore {
    private static RxStore instance = null;
    private final Map<String, Object> rxStore = new HashMap<>();

    private RxStore() {

    }

    public static RxStore getInstance() {
        if (instance == null) {
            instance = new RxStore();
        }
        return instance;
    }

    public Object putDataIntoStore(String key, Object value) {
        return rxStore.put(key, value);
    }

    public Object getDataFromStore(String key) {
        return rxStore.get(key);
    }
}
