package echo;

import java.util.HashMap;

public class Cache extends HashMap<String, String> {
    @Override
    synchronized public String get(Object key) {
        return super.get(key);
    }

    @Override
    synchronized public String put(String key, String value) {
        return super.put(key, value);
    }
}
