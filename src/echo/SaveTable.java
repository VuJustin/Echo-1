package echo;

import java.util.HashMap;
// Both CacheHandler & SecurityHandler(User/Password) will use this
// Need to rename
public class SaveTable extends HashMap<String, String> {
    // Is the search method
    @Override
    synchronized public String get(Object key) {
        return super.get(key);
    }
    // Is the update method
    @Override
    synchronized public String put(String key, String value) {
        return super.put(key, value);
    }
}
