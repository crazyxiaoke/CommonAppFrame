package com.hz.zxk.lib_commonframe.uitls;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author zhengxiaoke
 * @date 2019-06-08 22:58
 */
public class PreferencesUtils {

    private static volatile PreferencesUtils sInstance;

    private SharedPreferences mSharePreferences;

    private SharedPreferences.Editor mEditor;

    private PreferencesUtils(){

    }

    public void init(Context context){
        init(context,context.getPackageName()+".cache");
    }

    public void init(Context context,String name){
        mSharePreferences=context.getApplicationContext().getSharedPreferences(name,Context.MODE_PRIVATE);
        mEditor=mSharePreferences.edit();
    }


    public static PreferencesUtils getInstance(){
        if(sInstance==null){
            synchronized (PreferencesUtils.class){
                if(sInstance==null){
                    sInstance=new PreferencesUtils();
                }
            }
        }
        return sInstance;
    }

    /**
     * 检测是否初始化
     */
    private void checkInit(){
        if(mSharePreferences==null || mEditor==null){
            throw new RuntimeException("请初始化PreferencesUtils,可在Application中初始化");
        }

    }

    public PreferencesUtils saveString(String key,String value){
        checkInit();
        mEditor.putString(key,value);
        return this;
    }

    public PreferencesUtils saveInt(String key,int value){
        checkInit();
        mEditor.putInt(key,value);
        return this;
    }

    public PreferencesUtils saveLong(String key,long value){
        checkInit();
        mEditor.putLong(key,value);
        return this;
    }

    public PreferencesUtils saveFloat(String key,float value){
        checkInit();
        mEditor.putFloat(key,value);
        return this;
    }

    public PreferencesUtils saveBoolean(String key,boolean value){
        checkInit();
        mEditor.putBoolean(key,value);
        return this;
    }


    public String getString(String key,String defValue){
        checkInit();
        return mSharePreferences.getString(key,defValue);
    }

    public String getString(String key){
        return getString(key,"");
    }

    public int getInt(String key,int defValue){
        checkInit();
        return mSharePreferences.getInt(key,defValue);
    }

    public int getInt(String key){
        return getInt(key,0);
    }

    public long getLong(String key,long defValue){
        checkInit();
        return mSharePreferences.getLong(key,defValue);
    }

    public long getLong(String key){
        return getLong(key,0);
    }

    public float getFloat(String key,float defValue){
        checkInit();
        return mSharePreferences.getFloat(key,defValue);
    }

    public float getFloat(String key){
        return getFloat(key,0);
    }

    public boolean getBoolean(String key,boolean defValue){
        checkInit();
        return mSharePreferences.getBoolean(key,defValue);
    }

    public boolean getBoolean(String key){
        return getBoolean(key,false);
    }

    public void apply(){
        checkInit();
        mEditor.apply();
    }

    public void commit(){
        checkInit();
        mEditor.commit();
    }
}
