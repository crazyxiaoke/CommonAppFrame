package com.hz.zxk.lib_commonframe.uitls.ioHandler;

import android.util.LruCache;

/**
 * @author zhengxiaoke
 * @date 2019-06-08 23:24
 */
public class MemoryIOHandler implements IOHandler {

    private LruCache<String,Object> cache=new LruCache<>(10*1024*1024);

    @Override
    public void save(String key, String value) {
        cache.put(key,value);
    }

    @Override
    public void save(String key, int value) {
        cache.put(key,value);
    }

    @Override
    public void save(String key, long value) {
        cache.put(key,value);
    }

    @Override
    public void save(String key, float value) {
        cache.put(key,value);
    }

    @Override
    public void save(String key, boolean value) {
        cache.put(key,value);
    }

    @Override
    public String getString(String key) {
        return (String)cache.get(key);
    }

    @Override
    public int getInt(String key) {
        return (int)cache.get(key);
    }

    @Override
    public long getLong(String key) {
        return (long)cache.get(key);
    }

    @Override
    public float getFloat(String key) {
        return (float)cache.get(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return (boolean)cache.get(key);
    }
}
