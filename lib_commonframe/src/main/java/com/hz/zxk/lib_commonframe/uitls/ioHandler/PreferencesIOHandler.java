package com.hz.zxk.lib_commonframe.uitls.ioHandler;

import com.hz.zxk.advanced.PreferencesUtils;

/**
 * @author zhengxiaoke
 * @date 2019-06-08 22:55
 */
public class PreferencesIOHandler implements IOHandler{


    @Override
    public void save(String key, String value) {
        PreferencesUtils.getInstance().saveString(key,value)
            .apply();
    }

    @Override
    public void save(String key, int value) {
        PreferencesUtils.getInstance().saveInt(key,value)
            .apply();
    }

    @Override
    public void save(String key, long value) {
        PreferencesUtils.getInstance().saveLong(key,value)
            .apply();
    }

    @Override
    public void save(String key, float value) {
        PreferencesUtils.getInstance().saveFloat(key,value)
            .apply();
    }

    @Override
    public void save(String key, boolean value) {
        PreferencesUtils.getInstance().saveBoolean(key,value)
            .apply();
    }

    @Override
    public String getString(String key) {
        return PreferencesUtils.getInstance().getString(key);
    }

    @Override
    public int getInt(String key) {
        return PreferencesUtils.getInstance().getInt(key);
    }

    @Override
    public long getLong(String key) {
        return PreferencesUtils.getInstance().getLong(key);
    }

    @Override
    public float getFloat(String key) {
        return PreferencesUtils.getInstance().getFloat(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return PreferencesUtils.getInstance().getBoolean(key);
    }
}
