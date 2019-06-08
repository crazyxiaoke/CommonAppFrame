package com.hz.zxk.lib_commonframe.uitls.ioHandler;

/**
 * @author zhengxiaoke
 * @date 2019-06-08 23:21
 */
public class IOHandlerFactory {

    public enum IOType{
        MEMORY,
        PREFERENCES
    }

    public static IOHandler createIOHandler(IOType ioType){
        switch (ioType){
            case MEMORY:
                return new MemoryIOHandler();
            case PREFERENCES:
                return new PreferencesIOHandler();
            default:
                return null;
        }
    }

}
