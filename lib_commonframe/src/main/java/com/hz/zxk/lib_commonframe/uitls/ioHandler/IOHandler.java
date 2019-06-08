package com.hz.zxk.lib_commonframe.uitls.ioHandler;

/**
 * @author zhengxiaoke
 * @date 2019-06-08 22:53
 */
public interface IOHandler {

    void save(String key, String value);

    void save(String key, int value);

    void save(String key, long value);

    void save(String key, float value);

    void save(String key, boolean value);


    String getString(String key);

    int getInt(String key);

    long getLong(String key);

    float getFloat(String key);

    boolean getBoolean(String key);

}
